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

public class BedrockWrapperSerializerV9_10 extends BedrockWrapperSerializer {
   public static final BedrockWrapperSerializerV9_10 V9;
   public static final BedrockWrapperSerializerV9_10 V10;
   private final Zlib zlib;
   private BedrockCodec codec;

   private BedrockWrapperSerializerV9_10(Zlib zlib) {
      this.codec = BedrockCompat.CODEC;
      this.zlib = zlib;
   }

   public void serialize(ByteBuf buffer, BedrockCodec codec, Collection<BedrockPacket> packets, int level, BedrockSession session) {
      if (session.getIsSafeAndFast()) {
         for(BedrockPacket packet : packets) {
            try {
               int id = codec.getId(packet);
               int header = 0;
               header |= id & 1023;
               header |= 0;
               header |= 0;
               VarInts.writeUnsignedInt(buffer, header);
               codec.tryEncode(codec.getHelper(), buffer, packet, session);
            } catch (PacketSerializeException e) {
               log.error("Error occurred whilst encoding " + packet.getClass().getSimpleName(), e);
            }
         }
      } else {
         ByteBuf uncompressed = ByteBufAllocator.DEFAULT.ioBuffer(packets.size() << 3);

         try {
            for(BedrockPacket packet : packets) {
               ByteBuf packetBuffer = ByteBufAllocator.DEFAULT.ioBuffer();

               try {
                  int id = codec.getId(packet);
                  int header = 0;
                  header |= id & 1023;
                  header |= 0;
                  header |= 0;
                  VarInts.writeUnsignedInt(packetBuffer, header);
                  codec.tryEncode(codec.getHelper(), packetBuffer, packet, session);
                  VarInts.writeUnsignedInt(uncompressed, packetBuffer.readableBytes());
                  uncompressed.writeBytes(packetBuffer);
               } catch (PacketSerializeException e) {
                  log.debug("Error occurred whilst encoding " + packet.getClass().getSimpleName(), e);
               } finally {
                  packetBuffer.release();
               }
            }

            this.zlib.deflate(uncompressed, buffer, level);
         } catch (DataFormatException e) {
            throw new RuntimeException("Unable to deflate buffer data", e);
         } finally {
            uncompressed.release();
         }
      }

   }

   public void deserialize(ByteBuf compressed, BedrockCodec codec, Collection<BedrockPacket> packets, BedrockSession session) {
      if (session.getIsSafeAndFast()) {
         try {
            if (!compressed.isReadable()) {
               throw new DataFormatException("Packet cannot be empty");
            }

            while(compressed.isReadable()) {
               int header = VarInts.readUnsignedInt(compressed);
               int packetId = header & 1023;
               BedrockPacket packet = codec.tryDecode(codec.getHelper(), compressed, packetId, PacketRecipient.BOTH, session);
               int senderId = header >>> 10 & 3;
               int clientId = header >>> 12 & 3;
               packets.add(packet);
            }
         } catch (PacketSerializeException e) {
            log.error("Error occurred whilst decoding packet", e);
            if (log.isTraceEnabled()) {
               log.trace("Packet contents\n{}", ByteBufUtil.prettyHexDump(compressed.readerIndex(0)));
            }
         } catch (DataFormatException e) {
            throw new RuntimeException("Unable to inflate buffer data", e);
         }
      } else {
         ByteBuf decompressed = ByteBufAllocator.DEFAULT.ioBuffer();

         try {
            decompressed = this.zlib.inflate(compressed, 12582912);

            while(decompressed.isReadable()) {
               int length = VarInts.readUnsignedInt(decompressed);
               ByteBuf packetBuffer = decompressed.readSlice(length);
               if (!packetBuffer.isReadable()) {
                  throw new DataFormatException("Packet cannot be empty");
               }

               try {
                  int header = VarInts.readUnsignedInt(packetBuffer);
                  int packetId = header & 1023;
                  BedrockPacket packet = codec.tryDecode(codec.getHelper(), packetBuffer, packetId, PacketRecipient.BOTH, session);
                  int senderId = header >>> 10 & 3;
                  int clientId = header >>> 12 & 3;
                  packets.add(packet);
               } catch (PacketSerializeException e) {
                  log.debug("Error occurred whilst decoding packet", e);
                  if (log.isTraceEnabled()) {
                     log.trace("Packet contents\n{}", ByteBufUtil.prettyHexDump(packetBuffer.readerIndex(0)));
                  }
               }
            }
         } catch (DataFormatException e) {
            log.debug("Error occurred whilst decoding packet", e);
            throw new RuntimeException("Unable to inflate buffer data", e);
         } finally {
            decompressed.release();
         }
      }

   }

   static {
      V9 = new BedrockWrapperSerializerV9_10(Zlib.DEFAULT);
      V10 = new BedrockWrapperSerializerV9_10(Zlib.RAW);
   }
}
