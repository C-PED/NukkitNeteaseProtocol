package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ChunkRadiusUpdatedPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class ChunkRadiusUpdatedSerializer_v291 implements BedrockPacketSerializer<ChunkRadiusUpdatedPacket> {
   public static final ChunkRadiusUpdatedSerializer_v291 INSTANCE = new ChunkRadiusUpdatedSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ChunkRadiusUpdatedPacket packet) {
      VarInts.writeInt(buffer, packet.getRadius());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ChunkRadiusUpdatedPacket packet) {
      packet.setRadius(VarInts.readInt(buffer));
   }

   protected ChunkRadiusUpdatedSerializer_v291() {
   }
}
