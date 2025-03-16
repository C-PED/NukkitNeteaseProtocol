package com.nukkitx.protocol.bedrock;

import com.neteasemc.protocol.netgame.NeteaseNetGameTransferBatchPacket;
import com.neteasemc.protocol.netgame.NetworkGamePacket;
import com.neteasemc.protocol.netgame.subpacket.SubPacket;
import com.nukkitx.network.SessionConnection;
import com.nukkitx.network.util.DisconnectReason;
import com.nukkitx.protocol.bedrock.annotation.NoEncryption;
import com.nukkitx.protocol.bedrock.exception.PacketSerializeException;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.DisconnectPacket;
import com.nukkitx.protocol.bedrock.util.NeteasePacketStatistics;
import com.nukkitx.protocol.bedrock.wrapper.BedrockWrapperSerializer;
import com.nukkitx.protocol.common.MinecraftServerSession;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.EventLoop;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.crypto.SecretKey;

public class BedrockProxyServerSession extends BedrockSession implements MinecraftServerSession<BedrockPacket> {
   private int mProxyId;
   private volatile long lastPing = System.currentTimeMillis();
   private InetSocketAddress address;
   private boolean isOpenPacketStatistics = false;
   private NeteasePacketStatistics packetStatistics = new NeteasePacketStatistics();
   private final ArrayList<Long> packetQueue_uid = new ArrayList(1024);
   private final ArrayList<BedrockPacket> packetQueue_packet = new ArrayList(1024);

   public int getOnlinePlayerNum() {
      return this.proxySessions.size();
   }

   public int getPeUserNum() {
      int peNum = 0;

      for(BedrockSession session : this.proxySessions.values()) {
         if (session.isPeUser()) {
            ++peNum;
         }
      }

      return peNum;
   }

   public int getProxyId() {
      return this.mProxyId;
   }

   public void setProxyId(int proxyId) {
      this.mProxyId = proxyId;
   }

   public void setLastPintTime() {
      this.setLastPingTime(System.currentTimeMillis());
   }

   public void setLastPingTime(long lastTime) {
      this.lastPing = lastTime;
   }

   public BedrockProxyServerSession(SessionConnection<ByteBuf> connection, EventLoop eventLoop, BedrockWrapperSerializer serializer) {
      super(connection, eventLoop, serializer);
   }

   public void sendPacket(@Nonnull BedrockPacket packet) {
      this.queuePacket(packet, 0L);
   }

   public void sendPacket(BedrockPacket packet, long userId) {
      if (this.eventLoop.inEventLoop()) {
         this.queuePacket(packet, userId);
      } else {
         this.eventLoop.execute(() -> this.queuePacket(packet, userId));
      }

   }

   private void queuePacket(BedrockPacket packet, long userId) {
      if (this.isOpenPacketStatistics) {
         this.packetStatistics.addSendStatistics(packet.getClass().getSimpleName(), 0, userId);
      }

      this.packetQueue_uid.add(userId);
      this.packetQueue_packet.add(packet);
   }

   public void sendNetgamePacket(SubPacket packet, long userId) {
      NetworkGamePacket netgamePacket = new NetworkGamePacket(packet);
      this.checkPacket(netgamePacket);
      if (this.isOpenPacketStatistics) {
         this.packetStatistics.addSendStatistics(packet.getClass().getSimpleName(), 0, userId);
      }

      this.sendWrapped(Collections.singletonList(netgamePacket), !packet.getClass().isAnnotationPresent(NoEncryption.class));
   }

   private void checkPacket(BedrockPacket packet) {
      this.checkForClosed();
      Objects.requireNonNull(packet, "packet");
      if (log.isTraceEnabled() && this.isLogging()) {
         String to = this.connection.getAddress().toString();
         log.trace("Outbound {}: {}", to, packet);
      }

      this.packetCodec.getId(packet);
   }

   public void sendQueued() {
      if (this.packetQueue_uid.size() > 0) {
         ByteBuf finalPayload = ByteBufAllocator.DEFAULT.ioBuffer();
         finalPayload.writeByte(254);
         NeteaseNetGameTransferBatchPacket packet = new NeteaseNetGameTransferBatchPacket(this.packetQueue_uid, this.packetQueue_packet);

         try {
            this.wrapperSerializer.serialize(finalPayload, this.packetCodec, Collections.singletonList(packet), this.compressionLevel, this);
         } catch (Exception var4) {
            finalPayload.release();
            log.error("Unable to compress Batched packets");
            return;
         }

         this.connection.send(finalPayload);
         this.packetQueue_uid.clear();
         this.packetQueue_packet.clear();
      }

   }

   public void enableEncryption(@Nonnull SecretKey secretKey) {
   }

   public void sendWrapped(Collection<BedrockPacket> packets, boolean encrypt) {
      ByteBuf finalPayload = ByteBufAllocator.DEFAULT.ioBuffer();
      finalPayload.writeByte(254);

      try {
         this.wrapperSerializer.serialize(finalPayload, this.packetCodec, packets, this.compressionLevel, this);
      } catch (Exception e) {
         finalPayload.release();
         log.error("Unable to compress packets", e);
         return;
      }

      this.connection.send(finalPayload);
   }

   public void onWrappedPacket(ByteBuf batched) {
      try {
         batched.markReaderIndex();
         if (batched.isReadable()) {
            List<BedrockPacket> packets = new ObjectArrayList();
            this.getWrapperSerializer().deserialize(batched, this.packetCodec, packets, this);
            this.batchHandler.handle(this, batched, packets);
         }
      } catch (PacketSerializeException e) {
         log.error("Error whilst decoding packets", e);
      } catch (Exception e) {
         log.error("unknown error:", e);
      }

   }

   public void disconnect() {
      log.info("start disconnect all sub session");

      for(Map.Entry<Long, BedrockSession> entry : this.proxySessions.entrySet()) {
         log.info("disconnect sub session:" + entry.getKey());
         this.disconnectAndRemove((String)null, (Long)entry.getKey());
      }

      log.info("end disconnect all sub session");
   }

   public void disconnect(@Nullable String reason) {
      log.info("netease proxy session disconnect" + reason);
      this.disconnect();
   }

   public void disconnect(String reason, long userId) {
      DisconnectPacket packet = new DisconnectPacket();
      if (reason == null) {
         packet.setMessageSkipped(true);
         reason = "disconnect.disconnected";
      }

      packet.setKickMessage(reason);
      this.sendPacket(packet, userId);
      this.disconnectAndRemove(reason, userId);
   }

   public void disconnectAndRemove(String reason, long userId) {
      if (!this.proxySessions.containsKey(userId)) {
         log.error("can't not find sub session while disconnecting:" + userId);
      } else {
         this.removePlayerSession(userId);
      }
   }

   public void close(DisconnectReason reason) {
      this.checkForClosed();
      log.info("close proxy session:" + reason);

      try {
         for(BedrockSession playerSess : this.proxySessions.values()) {
            playerSess.close(reason);
         }
      } catch (Exception e) {
         log.debug("multi close:" + e);
      } finally {
         log.info("close all sub session finish");
      }

      this.closed = true;
      this.proxySessions.clear();
      this.packetHandler = null;
   }

   private void removePlayerSession(long userId) {
      BedrockSession playerSess = (BedrockSession)this.proxySessions.getOrDefault(userId, null);
      if (playerSess != null) {
         playerSess.close(DisconnectReason.DISCONNECTED);
         this.proxySessions.remove(userId);
         log.info("absolutly remove player session:{}", userId);
      }

      log.info("absolutly remove player session done:{}", userId);
   }

   public long getLastPing() {
      return this.lastPing;
   }

   public InetSocketAddress getAddress() {
      return this.address;
   }

   public void setAddress(InetSocketAddress address) {
      this.address = address;
   }

   public boolean isOpenPacketStatistics() {
      return this.isOpenPacketStatistics;
   }

   public void setOpenPacketStatistics(boolean isOpenPacketStatistics) {
      this.isOpenPacketStatistics = isOpenPacketStatistics;
   }

   public NeteasePacketStatistics getPacketStatistics() {
      return this.packetStatistics;
   }
}
