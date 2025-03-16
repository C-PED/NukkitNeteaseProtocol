package com.nukkitx.protocol.bedrock.codec.v575.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v527.serializer.PlayerAuthInputSerializer_v527;
import com.nukkitx.protocol.bedrock.packet.PlayerAuthInputPacket;
import io.netty.buffer.ByteBuf;

public class PlayerAuthInputSerializer_v575 extends PlayerAuthInputSerializer_v527 {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
      super.serialize(buffer, helper, packet);
      helper.writeVector2f(buffer, packet.getAnalogMoveVector());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setAnalogMoveVector(helper.readVector2f(buffer));
   }
}
