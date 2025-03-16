package com.nukkitx.protocol.bedrock.codec.v486.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ScriptMessagePacket;
import io.netty.buffer.ByteBuf;

public class ScriptMessageSerializer_v486 implements BedrockPacketSerializer<ScriptMessagePacket> {
   public static final ScriptMessageSerializer_v486 INSTANCE = new ScriptMessageSerializer_v486();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ScriptMessagePacket packet) {
      helper.writeString(buffer, packet.getChannel());
      helper.writeString(buffer, packet.getMessage());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ScriptMessagePacket packet) {
      packet.setChannel(helper.readString(buffer));
      packet.setMessage(helper.readString(buffer));
   }

   protected ScriptMessageSerializer_v486() {
   }
}
