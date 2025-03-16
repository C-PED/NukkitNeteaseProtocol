package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.StopSoundPacket;
import io.netty.buffer.ByteBuf;

public class StopSoundSerializer_v291 implements BedrockPacketSerializer<StopSoundPacket> {
   public static final StopSoundSerializer_v291 INSTANCE = new StopSoundSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StopSoundPacket packet) {
      helper.writeString(buffer, packet.getSoundName());
      buffer.writeBoolean(packet.isStoppingAllSound());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StopSoundPacket packet) {
      packet.setSoundName(helper.readString(buffer));
      packet.setStoppingAllSound(buffer.readBoolean());
   }

   protected StopSoundSerializer_v291() {
   }
}
