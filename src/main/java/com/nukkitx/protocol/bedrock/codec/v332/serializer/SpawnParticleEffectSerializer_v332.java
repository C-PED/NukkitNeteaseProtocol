package com.nukkitx.protocol.bedrock.codec.v332.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SpawnParticleEffectPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SpawnParticleEffectSerializer_v332 implements BedrockPacketSerializer<SpawnParticleEffectPacket> {
   public static final SpawnParticleEffectSerializer_v332 INSTANCE = new SpawnParticleEffectSerializer_v332();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SpawnParticleEffectPacket packet) {
      buffer.writeByte(packet.getDimensionId());
      VarInts.writeLong(buffer, packet.getUniqueEntityId());
      helper.writeVector3f(buffer, packet.getPosition());
      helper.writeString(buffer, packet.getIdentifier());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SpawnParticleEffectPacket packet) {
      packet.setDimensionId(buffer.readUnsignedByte());
      packet.setUniqueEntityId(VarInts.readLong(buffer));
      packet.setPosition(helper.readVector3f(buffer));
      packet.setIdentifier(helper.readString(buffer));
   }

   protected SpawnParticleEffectSerializer_v332() {
   }
}
