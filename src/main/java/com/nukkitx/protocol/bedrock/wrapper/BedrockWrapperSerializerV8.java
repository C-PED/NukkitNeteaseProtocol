package com.nukkitx.protocol.bedrock.wrapper;

import com.nukkitx.network.VarInts;
import com.nukkitx.protocol.bedrock.BedrockSession;
import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.compat.BedrockCompat;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.exception.PacketSerializeException;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import com.nukkitx.protocol.common.util.Zlib;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import java.util.Collection;
import java.util.zip.DataFormatException;

public class BedrockWrapperSerializerV8 extends BedrockWrapperSerializer {
   public static final BedrockWrapperSerializerV8 INSTANCE = new BedrockWrapperSerializerV8();
   private static final Zlib ZLIB;
   private BedrockCodec codec;

   public void serialize(ByteBuf buffer, BedrockCodec codec, Collection<BedrockPacket> packets, int level, BedrockSession session) {
      ByteBuf uncompressed = ByteBufAllocator.DEFAULT.ioBuffer(packets.size() << 3);
      log.error("serialize ---- 222 ");

      try {
         for(BedrockPacket packet : packets) {
            ByteBuf packetBuffer = ByteBufAllocator.DEFAULT.ioBuffer();

            try {
               int id = codec.getId(packet);
               packetBuffer.writeByte(id);
               packetBuffer.writeByte(0);
               packetBuffer.writeByte(0);
               codec.tryEncode(codec.getHelper(), packetBuffer, packet, session);
               VarInts.writeUnsignedInt(uncompressed, packetBuffer.readableBytes());
               uncompressed.writeBytes(packetBuffer);
            } catch (PacketSerializeException e) {
               log.error("Error occurred whilst encoding " + packet.getClass().getSimpleName(), e);
            } finally {
               packetBuffer.release();
            }
         }

         ZLIB.deflate(uncompressed, buffer, level);
      } catch (DataFormatException e) {
         throw new RuntimeException("Unable to deflate buffer data", e);
      } finally {
         uncompressed.release();
      }

   }

   public void deserialize(ByteBuf compressed, BedrockCodec codec, Collection<BedrockPacket> packets, BedrockSession session) {
      ByteBuf decompressed = ByteBufAllocator.DEFAULT.ioBuffer();

      try {
         log.info("deserialize ,...... ");
         decompressed = ZLIB.inflate(compressed, 2097152);

         while(decompressed.isReadable()) {
            int length = VarInts.readUnsignedInt(decompressed);
            ByteBuf packetBuffer = decompressed.readSlice(length);
            if (!packetBuffer.isReadable()) {
               throw new DataFormatException("Packet cannot be empty");
            }

            try {
               int packetId = packetBuffer.readUnsignedByte();
               log.info("deserialize  packet id ------  " + packetId);
               BedrockPacket packet = codec.tryDecode(codec.getHelper(), packetBuffer, packetId, PacketRecipient.BOTH, session);
               packetBuffer.readUnsignedByte();
               packetBuffer.readUnsignedByte();
               packets.add(packet);
            } catch (PacketSerializeException e) {
               log.debug("Error occurred whilst decoding packet", e);
               if (log.isTraceEnabled()) {
                  log.trace("Packet contents\n{}", ByteBufUtil.prettyHexDump(packetBuffer.readerIndex(0)));
               }
            }
         }
      } catch (DataFormatException e) {
         throw new RuntimeException("Unable to inflate buffer data 222", e);
      } finally {
         decompressed.release();
      }

   }

   private BedrockWrapperSerializerV8() {
      this.codec = BedrockCompat.CODEC;
   }

   static {
      ZLIB = Zlib.DEFAULT;
   }
}
