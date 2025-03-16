package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.PlaySoundPacket;
import io.netty.buffer.ByteBuf;

public class PlaySoundSerializer_v291 implements BedrockPacketSerializer<PlaySoundPacket> {
   public static final PlaySoundSerializer_v291 INSTANCE = new PlaySoundSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlaySoundPacket packet) {
      helper.writeString(buffer, packet.getSound());
      helper.writeBlockPosition(buffer, packet.getPosition().mul(8.0F).toInt());
      buffer.writeFloatLE(packet.getVolume());
      buffer.writeFloatLE(packet.getPitch());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlaySoundPacket packet) {
      packet.setSound(helper.readString(buffer));
      packet.setPosition(helper.readBlockPosition(buffer).toFloat().div(8.0F));
      packet.setVolume(buffer.readFloatLE());
      packet.setPitch(buffer.readFloatLE());
   }

   protected PlaySoundSerializer_v291() {
   }
}
