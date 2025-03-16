package com.nukkitx.protocol.bedrock.codec.v582.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.CompressedBiomeDefinitionListPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtMap;

public class CompressedBiomeDefinitionListSerializer_v582 implements BedrockPacketSerializer<CompressedBiomeDefinitionListPacket> {
   public static final CompressedBiomeDefinitionListSerializer_v582 INSTANCE = new CompressedBiomeDefinitionListSerializer_v582();
   protected static final byte[] COMPRESSED_INDICATOR = new byte[]{67, 79, 77, 80, 82, 69, 83, 83, 69, 68};

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CompressedBiomeDefinitionListPacket packet) {
      ByteBuf compressed = buffer.alloc().ioBuffer();
      this.writeCompressed(packet.getDefinitions(), compressed, helper);
      VarInts.writeUnsignedInt(buffer, compressed.readableBytes());
      buffer.writeBytes(compressed);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CompressedBiomeDefinitionListPacket packet) {
      int length = VarInts.readUnsignedInt(buffer);
      packet.setDefinitions(this.readCompressed(buffer.readBytes(length), helper, COMPRESSED_INDICATOR.length));
   }

   protected NbtMap readCompressed(ByteBuf buffer, BedrockCodecHelper helper, int length) {
      buffer.skipBytes(length);
      ByteBuf[] dictionary = new ByteBuf[buffer.readUnsignedShortLE()];
      ByteBuf decompressed = buffer.alloc().ioBuffer();
      boolean var17 = false;

      NbtMap var20;
      try {
         var17 = true;

         for(int key = 0; key < dictionary.length; ++key) {
            dictionary[key] = buffer.readBytes(buffer.readUnsignedByte());
         }

         while(buffer.isReadable()) {
            int key = buffer.readUnsignedByte();
            if (key != 255) {
               decompressed.writeByte(key);
            } else {
               int index = buffer.readUnsignedShortLE();
               if (index >= 0 && index < dictionary.length) {
                  decompressed.writeBytes(dictionary[index].slice());
               } else {
                  decompressed.writeByte(key);
               }
            }
         }

         var20 = (NbtMap)helper.readTag(decompressed, NbtMap.class);
         var17 = false;
      } finally {
         if (var17) {
            decompressed.release();
            ByteBuf[] var12 = dictionary;
            int var13 = dictionary.length;
            int var14 = 0;

            while(true) {
               if (var14 >= var13) {
                  ;
               } else {
                  ByteBuf buf = var12[var14];
                  if (buf != null) {
                     buf.release();
                  }

                  ++var14;
               }
            }
         }
      }

      decompressed.release();

      for(ByteBuf buf : dictionary) {
         if (buf != null) {
            buf.release();
         }
      }

      return var20;
   }

   private void writeCompressed(NbtMap nbtMap, ByteBuf buffer, BedrockCodecHelper helper) {
      buffer.writeBytes(COMPRESSED_INDICATOR);
      buffer.writeShortLE(0);
      ByteBuf serialized = buffer.alloc().ioBuffer();
      helper.writeTag(serialized, nbtMap);

      while(serialized.isReadable()) {
         int key = serialized.readUnsignedByte();
         buffer.writeByte(key);
         if (key == 255) {
            buffer.writeShortLE(1);
         }
      }

   }

   protected CompressedBiomeDefinitionListSerializer_v582() {
   }
}
