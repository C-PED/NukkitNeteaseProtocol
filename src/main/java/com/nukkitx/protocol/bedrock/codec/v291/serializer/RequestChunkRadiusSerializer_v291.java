package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.RequestChunkRadiusPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class RequestChunkRadiusSerializer_v291 implements BedrockPacketSerializer<RequestChunkRadiusPacket> {
   public static final RequestChunkRadiusSerializer_v291 INSTANCE = new RequestChunkRadiusSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RequestChunkRadiusPacket packet) {
      VarInts.writeInt(buffer, packet.getRadius());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RequestChunkRadiusPacket packet) {
      packet.setRadius(VarInts.readInt(buffer));
   }

   protected RequestChunkRadiusSerializer_v291() {
   }
}
