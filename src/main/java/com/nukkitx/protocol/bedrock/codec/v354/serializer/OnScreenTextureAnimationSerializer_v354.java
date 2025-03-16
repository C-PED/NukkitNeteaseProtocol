package com.nukkitx.protocol.bedrock.codec.v354.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.OnScreenTextureAnimationPacket;
import io.netty.buffer.ByteBuf;

public class OnScreenTextureAnimationSerializer_v354 implements BedrockPacketSerializer<OnScreenTextureAnimationPacket> {
   public static final OnScreenTextureAnimationSerializer_v354 INSTANCE = new OnScreenTextureAnimationSerializer_v354();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, OnScreenTextureAnimationPacket packet) {
      buffer.writeIntLE((int)packet.getEffectId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, OnScreenTextureAnimationPacket packet) {
      packet.setEffectId(buffer.readUnsignedIntLE());
   }

   protected OnScreenTextureAnimationSerializer_v354() {
   }
}
