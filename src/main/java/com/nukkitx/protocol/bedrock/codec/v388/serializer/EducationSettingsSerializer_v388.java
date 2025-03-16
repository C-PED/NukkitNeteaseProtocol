package com.nukkitx.protocol.bedrock.codec.v388.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.EducationSettingsPacket;
import io.netty.buffer.ByteBuf;

public class EducationSettingsSerializer_v388 implements BedrockPacketSerializer<EducationSettingsPacket> {
   public static final EducationSettingsSerializer_v388 INSTANCE = new EducationSettingsSerializer_v388();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EducationSettingsPacket packet) {
      helper.writeString(buffer, packet.getCodeBuilderUri());
      buffer.writeBoolean(packet.isQuizAttached());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EducationSettingsPacket packet) {
      packet.setCodeBuilderUri(helper.readString(buffer));
      packet.setQuizAttached(buffer.readBoolean());
   }

   private EducationSettingsSerializer_v388() {
   }
}
