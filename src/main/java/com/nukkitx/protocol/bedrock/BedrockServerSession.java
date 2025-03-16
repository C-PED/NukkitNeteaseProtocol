package com.nukkitx.protocol.bedrock;

import com.nukkitx.network.SessionConnection;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.DisconnectPacket;
import com.nukkitx.protocol.bedrock.wrapper.BedrockWrapperSerializer;
import com.nukkitx.protocol.common.MinecraftServerSession;
import io.netty.buffer.ByteBuf;
import io.netty.channel.EventLoop;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;

public class BedrockServerSession extends BedrockSession implements MinecraftServerSession<BedrockPacket> {
   public BedrockServerSession(SessionConnection<ByteBuf> connection, EventLoop eventLoop, BedrockWrapperSerializer serializer) {
      super(connection, eventLoop, serializer);
   }

   public void disconnect() {
      this.disconnect((String)null, true);
   }

   public void disconnect(@Nullable String reason) {
      this.disconnect(reason, false);
   }

   public void disconnect(@Nullable String reason, boolean hideReason) {
      EventLoop eventLoop = this.getEventLoop();
      if (eventLoop.inEventLoop()) {
         this.disconnect0(reason, hideReason);
      } else {
         eventLoop.submit(() -> this.disconnect0(reason, hideReason));
      }

   }

   public CompletableFuture<Void> disconnectFuture(@Nullable String reason, boolean hideReason) {
      this.checkForClosed();
      EventLoop eventLoop = this.getEventLoop();
      if (eventLoop.inEventLoop()) {
         this.disconnect0(reason, hideReason);
         return CompletableFuture.completedFuture(null);
      } else {
         CompletableFuture<Void> future = new CompletableFuture<>();
         eventLoop.submit(() -> {
            this.disconnect0(reason, hideReason);
            future.complete(null);
         });
         return future;
      }
   }

   private void disconnect0(@Nullable String reason, boolean hideReason) {
      DisconnectPacket packet = new DisconnectPacket();
      if (reason == null || hideReason) {
         packet.setMessageSkipped(true);
         reason = "disconnect.disconnected";
      }

      packet.setKickMessage(reason);
      this.sendPacket(packet);
   }
}
