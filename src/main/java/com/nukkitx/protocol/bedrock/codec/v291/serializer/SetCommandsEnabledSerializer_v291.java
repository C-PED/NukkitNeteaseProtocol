package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SetCommandsEnabledPacket;
import io.netty.buffer.ByteBuf;

public class SetCommandsEnabledSerializer_v291 implements BedrockPacketSerializer<SetCommandsEnabledPacket> {
   public static final SetCommandsEnabledSerializer_v291 INSTANCE = new SetCommandsEnabledSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetCommandsEnabledPacket packet) {
      buffer.writeBoolean(packet.isCommandsEnabled());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetCommandsEnabledPacket packet) {
      packet.setCommandsEnabled(buffer.readBoolean());
   }

   protected SetCommandsEnabledSerializer_v291() {
   }
}
