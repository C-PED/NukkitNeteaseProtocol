package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.MobEffectPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class MobEffectSerializer_v291 implements BedrockPacketSerializer<MobEffectPacket> {
   public static final MobEffectSerializer_v291 INSTANCE = new MobEffectSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MobEffectPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      buffer.writeByte(packet.getEvent().ordinal());
      VarInts.writeInt(buffer, packet.getEffectId());
      VarInts.writeInt(buffer, packet.getAmplifier());
      buffer.writeBoolean(packet.isParticles());
      VarInts.writeInt(buffer, packet.getDuration());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MobEffectPacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setEvent(MobEffectPacket.Event.values()[buffer.readUnsignedByte()]);
      packet.setEffectId(VarInts.readInt(buffer));
      packet.setAmplifier(VarInts.readInt(buffer));
      packet.setParticles(buffer.readBoolean());
      packet.setDuration(VarInts.readInt(buffer));
   }

   protected MobEffectSerializer_v291() {
   }
}
