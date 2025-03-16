package com.nukkitx.protocol.bedrock.handler;

import com.nukkitx.protocol.bedrock.BedrockSession;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.BedrockPacketHandler;
import com.nukkitx.protocol.common.PacketSignal;
import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Collection;

public class DefaultBatchHandler implements BatchHandler {
   private static final InternalLogger log = InternalLoggerFactory.getInstance(DefaultBatchHandler.class);
   public static final DefaultBatchHandler INSTANCE = new DefaultBatchHandler();

   public void handle(BedrockSession session, ByteBuf compressed, Collection<BedrockPacket> packets) {
      for(BedrockPacket packet : packets) {
         if (session.isLogging() && log.isTraceEnabled()) {
            log.trace("Inbound {}: {}", session.getAddress(), packet);
         }

         BedrockPacketHandler handler = session.getPacketHandler();
         boolean release = true;

         try {
            if (handler != null && packet.handle(handler) == PacketSignal.HANDLED) {
               release = false;
            } else {
               log.debug("Unhandled packet for {}: {}", session.getAddress(), packet);
            }
         } finally {
            if (release) {
               ReferenceCountUtil.release(packet);
            }

         }
      }

   }

   private DefaultBatchHandler() {
   }
}
