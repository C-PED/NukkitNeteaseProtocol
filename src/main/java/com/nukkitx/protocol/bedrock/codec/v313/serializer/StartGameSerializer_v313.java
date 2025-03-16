package com.nukkitx.protocol.bedrock.codec.v313.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.StartGameSerializer_v291;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import io.netty.buffer.ByteBuf;

public class StartGameSerializer_v313 extends StartGameSerializer_v291 {
   public static final StartGameSerializer_v313 INSTANCE = new StartGameSerializer_v313();

   protected void writeLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      super.writeLevelSettings(buffer, helper, packet);
      buffer.writeBoolean(packet.isFromWorldTemplate());
      buffer.writeBoolean(packet.isFromLockedWorldTemplate());
   }

   protected void readLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      super.readLevelSettings(buffer, helper, packet);
      packet.setFromWorldTemplate(buffer.readBoolean());
      packet.setFromLockedWorldTemplate(buffer.readBoolean());
   }

   protected StartGameSerializer_v313() {
   }
}
