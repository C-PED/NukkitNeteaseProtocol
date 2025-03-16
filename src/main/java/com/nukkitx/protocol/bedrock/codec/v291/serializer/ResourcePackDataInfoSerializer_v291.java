package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ResourcePackDataInfoPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class ResourcePackDataInfoSerializer_v291 implements BedrockPacketSerializer<ResourcePackDataInfoPacket> {
   public static final ResourcePackDataInfoSerializer_v291 INSTANCE = new ResourcePackDataInfoSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackDataInfoPacket packet) {
      String packInfo = packet.getPackId().toString() + (packet.getPackVersion() == null ? "" : '_' + packet.getPackVersion());
      helper.writeString(buffer, packInfo);
      buffer.writeIntLE((int)packet.getMaxChunkSize());
      buffer.writeIntLE((int)packet.getChunkCount());
      buffer.writeLongLE(packet.getCompressedPackSize());
      byte[] hash = packet.getHash();
      VarInts.writeUnsignedInt(buffer, hash.length);
      buffer.writeBytes(hash);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackDataInfoPacket packet) {
      String[] packInfo = helper.readString(buffer).split("_");
      packet.setPackId(UUID.fromString(packInfo[0]));
      if (packInfo.length > 1) {
         packet.setPackVersion(packInfo[1]);
      }

      packet.setMaxChunkSize((long)buffer.readIntLE());
      packet.setChunkCount((long)buffer.readIntLE());
      packet.setCompressedPackSize(buffer.readLongLE());
      byte[] hash = new byte[VarInts.readUnsignedInt(buffer)];
      buffer.readBytes(hash);
      packet.setHash(hash);
   }

   protected ResourcePackDataInfoSerializer_v291() {
   }
}
