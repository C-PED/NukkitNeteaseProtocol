package com.nukkitx.protocol.bedrock.codec.v471.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SubChunkRequestPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SubChunkRequestSerializer_v471 implements BedrockPacketSerializer<SubChunkRequestPacket> {
   public static final SubChunkRequestSerializer_v471 INSTANCE = new SubChunkRequestSerializer_v471();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkRequestPacket packet) {
      VarInts.writeInt(buffer, packet.getDimension());
      helper.writeVector3i(buffer, packet.getSubChunkPosition());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SubChunkRequestPacket packet) {
      packet.setDimension(VarInts.readInt(buffer));
      packet.setSubChunkPosition(helper.readVector3i(buffer));
   }

   protected SubChunkRequestSerializer_v471() {
   }
}
