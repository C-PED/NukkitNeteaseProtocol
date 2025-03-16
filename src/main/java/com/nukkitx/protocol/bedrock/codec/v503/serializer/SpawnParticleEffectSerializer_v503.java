package com.nukkitx.protocol.bedrock.codec.v503.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.SpawnParticleEffectSerializer_v332;
import com.nukkitx.protocol.bedrock.packet.SpawnParticleEffectPacket;
import io.netty.buffer.ByteBuf;
import java.util.Optional;

public class SpawnParticleEffectSerializer_v503 extends SpawnParticleEffectSerializer_v332 {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SpawnParticleEffectPacket packet) {
      super.serialize(buffer, helper, packet);
      helper.writeOptional(buffer, Optional::isPresent, packet.getMolangVariablesJson(), (buf, s) -> helper.writeString(buf, (String)s.get()));
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SpawnParticleEffectPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setMolangVariablesJson((Optional)helper.readOptional(buffer, Optional.empty(), (buf) -> Optional.of(helper.readString(buf))));
   }
}
