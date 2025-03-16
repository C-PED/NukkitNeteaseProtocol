package com.nukkitx.network.raknet;

import com.nukkitx.network.NetworkUtils;
import com.nukkitx.network.util.DisconnectReason;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.EventLoop;
import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class RakNetServerSession extends RakNetSession {
   private final RakNetServer rakNet;
   private ScheduledFuture<?> tickFuture;

   RakNetServerSession(RakNetServer rakNet, InetSocketAddress remoteAddress, Channel channel, EventLoop eventLoop, int mtu, int protocolVersion) {
      super(remoteAddress, channel, eventLoop, mtu, protocolVersion);
      this.rakNet = rakNet;
   }

   protected void onPacket(ByteBuf buffer) {
      short packetId = buffer.readUnsignedByte();
      switch (packetId) {
         case 7:
            this.onOpenConnectionRequest2(buffer);
            break;
         case 9:
            this.onConnectionRequest(buffer);
            break;
         case 19:
            this.onNewIncomingConnection();
      }

   }

   protected void onClose() {
      if (this.tickFuture != null) {
         this.tickFuture.cancel(false);
      }

      if (!this.rakNet.sessionsByAddress.remove(this.address, this)) {
         throw new IllegalStateException("Session was not found in session map");
      }
   }

   public RakNet getRakNet() {
      return this.rakNet;
   }

   private void onTick() {
      long curTime = System.currentTimeMillis();

      try {
         this.onTick(curTime);
      } catch (Exception e) {
         log.info("!!!!!!!!! raknet server tick exception:" + e);
      }

   }

   private void onOpenConnectionRequest2(ByteBuf buffer) {
      if (this.getState() == RakNetState.INITIALIZING) {
         if (RakNetUtils.verifyUnconnectedMagic(buffer)) {
            NetworkUtils.readAddress(buffer);
            int mtu = buffer.readUnsignedShort();
            this.setMtu(mtu);
            this.guid = buffer.readLong();
            this.initialize();
            this.sendOpenConnectionReply2();
            this.setState(RakNetState.INITIALIZED);
         }
      }
   }

   private void onConnectionRequest(ByteBuf buffer) {
      long guid = buffer.readLong();
      long time = buffer.readLong();
      boolean security = buffer.readBoolean();
      if (this.guid == guid && !security) {
         this.setState(RakNetState.CONNECTING);
         this.sendConnectionRequestAccepted(time);
      } else {
         this.sendConnectionFailure((short)17);
         this.close(DisconnectReason.CONNECTION_REQUEST_FAILED);
      }
   }

   private void onNewIncomingConnection() {
      if (this.getState() == RakNetState.CONNECTING) {
         this.setState(RakNetState.CONNECTED);
      }
   }

   void sendOpenConnectionReply1() {
      this.tickFuture = this.eventLoop.scheduleAtFixedRate(this::onTick, 0L, 10L, TimeUnit.MILLISECONDS);
      ByteBuf buffer = this.allocateBuffer(28);
      buffer.writeByte(6);
      RakNetUtils.writeUnconnectedMagic(buffer);
      buffer.writeLong(this.rakNet.guid);
      buffer.writeBoolean(false);
      buffer.writeShort(this.getMtu());
      this.sendDirect(buffer);
   }

   private void sendOpenConnectionReply2() {
      ByteBuf buffer = this.allocateBuffer(31);
      buffer.writeByte(8);
      RakNetUtils.writeUnconnectedMagic(buffer);
      buffer.writeLong(this.rakNet.guid);
      NetworkUtils.writeAddress(buffer, this.address);
      buffer.writeShort(this.getMtu());
      buffer.writeBoolean(false);
      this.sendDirect(buffer);
   }

   private void sendConnectionFailure(short id) {
      ByteBuf buffer = this.allocateBuffer(21);
      buffer.writeByte(id);
      RakNetUtils.writeUnconnectedMagic(buffer);
      buffer.writeLong(this.rakNet.guid);
      this.sendDirect(buffer);
   }

   private void sendConnectionRequestAccepted(long time) {
      boolean ipv6 = this.isIpv6Session();
      ByteBuf buffer = this.allocateBuffer(ipv6 ? 628 : 166);
      buffer.writeByte(16);
      NetworkUtils.writeAddress(buffer, this.address);
      buffer.writeShort(0);

      for(InetSocketAddress socketAddress : ipv6 ? RakNetConstants.LOCAL_IP_ADDRESSES_V6 : RakNetConstants.LOCAL_IP_ADDRESSES_V4) {
         NetworkUtils.writeAddress(buffer, socketAddress);
      }

      buffer.writeLong(time);
      buffer.writeLong(System.currentTimeMillis());
      this.send(buffer, RakNetPriority.IMMEDIATE, RakNetReliability.RELIABLE);
   }
}
