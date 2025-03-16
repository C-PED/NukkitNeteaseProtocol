package com.nukkitx.protocol.bedrock.codec.v313.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SpawnParticleEffectPacket;
import io.netty.buffer.ByteBuf;

public class SpawnParticleEffectSerializer_v313 implements BedrockPacketSerializer<SpawnParticleEffectPacket> {
   public static final SpawnParticleEffectSerializer_v313 INSTANCE = new SpawnParticleEffectSerializer_v313();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SpawnParticleEffectPacket packet) {
      buffer.writeByte(packet.getDimensionId());
      helper.writeVector3f(buffer, packet.getPosition());
      helper.writeString(buffer, packet.getIdentifier());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SpawnParticleEffectPacket packet) {
      packet.setDimensionId(buffer.readUnsignedByte());
      packet.setPosition(helper.readVector3f(buffer));
      packet.setIdentifier(helper.readString(buffer));
   }

   protected SpawnParticleEffectSerializer_v313() {
   }
}
