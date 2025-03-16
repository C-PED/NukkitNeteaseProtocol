package com.nukkitx.network.raknet;

import com.nukkitx.network.NetworkUtils;
import com.nukkitx.network.util.DisconnectReason;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.EventLoop;
import java.net.Inet6Address;
import java.net.InetSocketAddress;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class RakNetClientSession extends RakNetSession {
   private final RakNetClient rakNet;
   private int connectionAttempts;
   private long nextConnectionAttempt;

   RakNetClientSession(RakNetClient rakNet, InetSocketAddress address, Channel channel, EventLoop eventLoop, int mtu, int protocolVersion) {
      super(address, channel, eventLoop, mtu, protocolVersion);
      this.rakNet = rakNet;
   }

   protected void onPacket(ByteBuf buffer) {
      int packetId = buffer.readUnsignedByte();
      switch (packetId) {
         case 6:
            this.onOpenConnectionReply1(buffer);
         case 7:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         case 19:
         case 21:
         case 22:
         case 23:
         case 24:
         default:
            break;
         case 8:
            this.onOpenConnectionReply2(buffer);
            break;
         case 16:
            this.onConnectionRequestAccepted(buffer);
            break;
         case 17:
            this.close(DisconnectReason.CONNECTION_REQUEST_FAILED);
            break;
         case 18:
            this.close(DisconnectReason.ALREADY_CONNECTED);
            break;
         case 20:
            this.close(DisconnectReason.NO_FREE_INCOMING_CONNECTIONS);
            break;
         case 25:
            this.close(DisconnectReason.INCOMPATIBLE_PROTOCOL_VERSION);
            break;
         case 26:
            this.close(DisconnectReason.IP_RECENTLY_CONNECTED);
      }

   }

   protected void tick(long curTime) {
      if (this.getState() == RakNetState.UNCONNECTED) {
         if (this.connectionAttempts >= 10) {
            this.close(DisconnectReason.TIMED_OUT);
         } else if (this.nextConnectionAttempt < curTime) {
            this.attemptConnection(curTime);
         }
      }

      super.tick(curTime);
   }

   private void attemptConnection(long curTime) {
      int mtuDiff = (RakNetConstants.MAXIMUM_MTU_SIZE - 576) / 9;
      int mtuSize = RakNetConstants.MAXIMUM_MTU_SIZE - this.connectionAttempts * mtuDiff;
      if (mtuSize < 576) {
         mtuSize = 576;
      }

      this.sendOpenConnectionRequest1(mtuSize);
      this.nextConnectionAttempt = curTime + 1000L;
      ++this.connectionAttempts;
   }

   public RakNet getRakNet() {
      return this.rakNet;
   }

   private void onOpenConnectionReply1(ByteBuf buffer) {
      if (this.getState() == RakNetState.UNCONNECTED) {
         if (RakNetUtils.verifyUnconnectedMagic(buffer)) {
            this.guid = buffer.readLong();
            boolean security = buffer.readBoolean();
            int mtu = buffer.readUnsignedShort();
            this.setMtu(mtu);
            if (security) {
               this.close(DisconnectReason.CONNECTION_REQUEST_FAILED);
            } else {
               this.setState(RakNetState.INITIALIZING);
               this.sendOpenConnectionRequest2();
            }
         }
      }
   }

   private void onOpenConnectionReply2(ByteBuf buffer) {
      if (this.getState() == RakNetState.INITIALIZING) {
         if (!RakNetUtils.verifyUnconnectedMagic(buffer)) {
            this.close(DisconnectReason.CONNECTION_REQUEST_FAILED);
         } else {
            long guid = buffer.readLong();
            if (this.guid != guid) {
               this.close(DisconnectReason.CONNECTION_REQUEST_FAILED);
            } else {
               InetSocketAddress address = NetworkUtils.readAddress(buffer);
               int mtu = buffer.readUnsignedShort();
               this.setMtu(mtu);
               boolean security = buffer.readBoolean();
               this.initialize();
               this.setState(RakNetState.INITIALIZED);
               this.sendConnectionRequest();
            }
         }
      }
   }

   private void onConnectionRequestAccepted(ByteBuf buffer) {
      NetworkUtils.readAddress(buffer);
      buffer.readUnsignedShort();
      int required = 23;
      long pongTime = 0L;

      try {
         while(buffer.isReadable(23)) {
            NetworkUtils.readAddress(buffer);
         }

         pongTime = buffer.readLong();
         buffer.readLong();
      } catch (IndexOutOfBoundsException var6) {
      }

      this.sendNewIncomingConnection(pongTime);
      this.setState(RakNetState.CONNECTED);
   }

   private void sendOpenConnectionRequest1(int mtuSize) {
      ByteBuf buffer = this.allocateBuffer(mtuSize);
      buffer.writeByte(5);
      RakNetUtils.writeUnconnectedMagic(buffer);
      buffer.writeByte(this.protocolVersion);
      buffer.writeZero(mtuSize - 1 - 16 - 1 - (this.address.getAddress() instanceof Inet6Address ? 40 : 20) - 8);
      this.sendDirect(buffer);
   }

   private void sendOpenConnectionRequest2() {
      ByteBuf buffer = this.allocateBuffer(34);
      buffer.writeByte(7);
      RakNetUtils.writeUnconnectedMagic(buffer);
      NetworkUtils.writeAddress(buffer, this.address);
      buffer.writeShort(this.getMtu());
      buffer.writeLong(this.rakNet.guid);
      this.sendDirect(buffer);
   }

   private void sendConnectionRequest() {
      ByteBuf buffer = this.allocateBuffer(18);
      buffer.writeByte(9);
      buffer.writeLong(this.rakNet.guid);
      buffer.writeLong(System.currentTimeMillis());
      buffer.writeBoolean(false);
      this.send(buffer, RakNetPriority.IMMEDIATE, RakNetReliability.RELIABLE_ORDERED);
   }

   private void sendNewIncomingConnection(long pingTime) {
      boolean ipv6 = this.isIpv6Session();
      ByteBuf buffer = this.allocateBuffer(ipv6 ? 626 : 164);
      buffer.writeByte(19);
      NetworkUtils.writeAddress(buffer, this.address);

      for(InetSocketAddress address : ipv6 ? RakNetConstants.LOCAL_IP_ADDRESSES_V6 : RakNetConstants.LOCAL_IP_ADDRESSES_V4) {
         NetworkUtils.writeAddress(buffer, address);
      }

      buffer.writeLong(pingTime);
      buffer.writeLong(System.currentTimeMillis());
      this.send(buffer, RakNetPriority.IMMEDIATE, RakNetReliability.RELIABLE_ORDERED);
   }
}
