package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ResourcePackChunkRequestPacket;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class ResourcePackChunkRequestSerializer_v291 implements BedrockPacketSerializer<ResourcePackChunkRequestPacket> {
   public static final ResourcePackChunkRequestSerializer_v291 INSTANCE = new ResourcePackChunkRequestSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackChunkRequestPacket packet) {
      String packInfo = packet.getPackId().toString() + (packet.getPackVersion() == null ? "" : '_' + packet.getPackVersion());
      helper.writeString(buffer, packInfo);
      buffer.writeIntLE(packet.getChunkIndex());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackChunkRequestPacket packet) {
      String[] packInfo = helper.readString(buffer).split("_");
      packet.setPackId(UUID.fromString(packInfo[0]));
      if (packInfo.length > 1) {
         packet.setPackVersion(packInfo[1]);
      }

      packet.setChunkIndex(buffer.readIntLE());
   }

   protected ResourcePackChunkRequestSerializer_v291() {
   }
}
