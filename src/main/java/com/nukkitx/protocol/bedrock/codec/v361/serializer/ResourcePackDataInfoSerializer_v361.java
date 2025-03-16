package com.nukkitx.protocol.bedrock.codec.v361.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.ResourcePackType;
import com.nukkitx.protocol.bedrock.packet.ResourcePackDataInfoPacket;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class ResourcePackDataInfoSerializer_v361 implements BedrockPacketSerializer<ResourcePackDataInfoPacket> {
   private final TypeMap<ResourcePackType> typeMap;

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackDataInfoPacket packet) {
      String packInfo = packet.getPackId().toString() + (packet.getPackVersion() == null ? "" : '_' + packet.getPackVersion());
      helper.writeString(buffer, packInfo);
      buffer.writeIntLE((int)packet.getMaxChunkSize());
      buffer.writeIntLE((int)packet.getChunkCount());
      buffer.writeLongLE(packet.getCompressedPackSize());
      byte[] hash = packet.getHash();
      VarInts.writeUnsignedInt(buffer, hash.length);
      buffer.writeBytes(hash);
      buffer.writeBoolean(packet.isPremium());
      buffer.writeByte(this.typeMap.getId(packet.getType()));
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackDataInfoPacket packet) {
      String[] packInfo = helper.readString(buffer).split("_");
      packet.setPackId(UUID.fromString(packInfo[0]));
      if (packInfo.length > 1) {
         packet.setPackVersion(packInfo[1]);
      }

      packet.setMaxChunkSize(buffer.readUnsignedIntLE());
      packet.setChunkCount(buffer.readUnsignedIntLE());
      packet.setCompressedPackSize(buffer.readLongLE());
      byte[] hash = new byte[VarInts.readUnsignedInt(buffer)];
      buffer.readBytes(hash);
      packet.setHash(hash);
      packet.setPremium(buffer.readBoolean());
      packet.setType(this.typeMap.getType(buffer.readUnsignedByte()));
   }

   public ResourcePackDataInfoSerializer_v361(TypeMap<ResourcePackType> typeMap) {
      this.typeMap = typeMap;
   }
}
