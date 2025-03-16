package com.nukkitx.protocol.bedrock.codec.v419.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v388.serializer.PlayerAuthInputSerializer_v388;
import com.nukkitx.protocol.bedrock.packet.PlayerAuthInputPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class PlayerAuthInputSerializer_v419 extends PlayerAuthInputSerializer_v388 {
   public static final PlayerAuthInputSerializer_v419 INSTANCE = new PlayerAuthInputSerializer_v419();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
      super.serialize(buffer, helper, packet);
      VarInts.writeUnsignedLong(buffer, packet.getTick());
      helper.writeVector3f(buffer, packet.getDelta());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setTick(VarInts.readUnsignedLong(buffer));
      packet.setDelta(helper.readVector3f(buffer));
   }

   protected PlayerAuthInputSerializer_v419() {
   }
}
