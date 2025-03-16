package com.nukkitx.protocol.bedrock;

import com.neteasemc.protocol.netgame.NeteaseNetGameTransferPacket;
import com.nukkitx.network.SessionConnection;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import com.nukkitx.protocol.bedrock.wrapper.BedrockWrapperSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.EventLoop;
import java.util.Collection;
import java.util.Collections;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EmbeddedProxyServerSession extends BedrockProxyServerSession {
   private final ByteBuf buf;

   public EmbeddedProxyServerSession(SessionConnection<ByteBuf> connection, EventLoop eventLoop, BedrockWrapperSerializer serializer) {
      super(connection, eventLoop, serializer);
      this.buf = ByteBufAllocator.DEFAULT.ioBuffer();
   }

   public void receive(byte[] data) {
      ByteBuf buf = Unpooled.wrappedBuffer(data);
      this.eventLoop.execute(() -> this.onWrappedPacket(buf));
   }

   public void sendPacket(@Nonnull BedrockPacket packet) {
      this.sendPacket(packet, 0L);
   }

   public void sendPacket(BedrockPacket packet, long userId) {
      NeteaseNetGameTransferPacket transferPacket = new NeteaseNetGameTransferPacket(userId, packet);
      this.sendWrapped(Collections.singletonList(transferPacket), false);
   }

   public void sendWrapped(Collection<BedrockPacket> packets, boolean encrypt) {
      if (this.eventLoop.inEventLoop()) {
         this.send(packets);
      } else {
         this.eventLoop.execute(() -> this.send(packets));
      }

   }

   private void send(Collection<BedrockPacket> packets) {
      this.buf.clear();

      try {
         this.wrapperSerializer.serialize(this.buf, this.packetCodec, packets, this.compressionLevel, this);
      } catch (Exception e) {
         log.error("Unable to compress packets", e);
         return;
      }

      this.connection.send(this.buf);
   }

   public void sendQueued() {
   }

   public void disconnect() {
      log.error("EmbeddedProxyServerSession should NOT disconnect!");
   }

   public void disconnect(@Nullable String reason) {
      log.error("EmbeddedProxyServerSession should NOT disconnect!");
   }
}
