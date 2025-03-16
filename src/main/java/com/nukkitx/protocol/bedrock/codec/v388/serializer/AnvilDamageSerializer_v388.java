package com.nukkitx.protocol.bedrock.codec.v388.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.AnvilDamagePacket;
import io.netty.buffer.ByteBuf;

public class AnvilDamageSerializer_v388 implements BedrockPacketSerializer<AnvilDamagePacket> {
   public static final AnvilDamageSerializer_v388 INSTANCE = new AnvilDamageSerializer_v388();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AnvilDamagePacket packet) {
      buffer.writeByte(packet.getDamage());
      helper.writeBlockPosition(buffer, packet.getPosition());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AnvilDamagePacket packet) {
      packet.setDamage(buffer.readByte());
      packet.setPosition(helper.readBlockPosition(buffer));
   }

   private AnvilDamageSerializer_v388() {
   }
}
