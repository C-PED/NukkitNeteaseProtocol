package com.nukkitx.protocol.bedrock.codec.v440.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v428.serializer.StartGameSerializer_v428;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import io.netty.buffer.ByteBuf;

public class StartGameSerializer_v440 extends StartGameSerializer_v428 {
   public static final StartGameSerializer_v440 INSTANCE = new StartGameSerializer_v440();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      super.serialize(buffer, helper, packet);
      helper.writeString(buffer, packet.getServerEngine());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setServerEngine(helper.readString(buffer));
   }

   protected StartGameSerializer_v440() {
   }
}
