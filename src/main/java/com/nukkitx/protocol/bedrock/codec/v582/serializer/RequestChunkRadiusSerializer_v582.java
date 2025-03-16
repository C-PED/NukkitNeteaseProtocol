package com.nukkitx.protocol.bedrock.codec.v582.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.RequestChunkRadiusSerializer_v291;
import com.nukkitx.protocol.bedrock.packet.RequestChunkRadiusPacket;
import io.netty.buffer.ByteBuf;

public class RequestChunkRadiusSerializer_v582 extends RequestChunkRadiusSerializer_v291 {
   public static final RequestChunkRadiusSerializer_v582 INSTANCE = new RequestChunkRadiusSerializer_v582();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RequestChunkRadiusPacket packet) {
      super.serialize(buffer, helper, packet);
      buffer.writeByte(packet.getMaxRadius());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RequestChunkRadiusPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setMaxRadius(buffer.readUnsignedByte());
   }

   protected RequestChunkRadiusSerializer_v582() {
   }
}
