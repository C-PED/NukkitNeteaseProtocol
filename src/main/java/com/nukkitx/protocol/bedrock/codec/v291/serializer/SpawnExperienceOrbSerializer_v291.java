package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SpawnExperienceOrbPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SpawnExperienceOrbSerializer_v291 implements BedrockPacketSerializer<SpawnExperienceOrbPacket> {
   public static final SpawnExperienceOrbSerializer_v291 INSTANCE = new SpawnExperienceOrbSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SpawnExperienceOrbPacket packet) {
      helper.writeVector3f(buffer, packet.getPosition());
      VarInts.writeInt(buffer, packet.getAmount());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SpawnExperienceOrbPacket packet) {
      packet.setPosition(helper.readVector3f(buffer));
      packet.setAmount(VarInts.readInt(buffer));
   }

   protected SpawnExperienceOrbSerializer_v291() {
   }
}
