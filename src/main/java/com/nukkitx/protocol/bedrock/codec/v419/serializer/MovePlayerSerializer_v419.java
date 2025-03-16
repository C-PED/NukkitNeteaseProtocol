package com.nukkitx.protocol.bedrock.codec.v419.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.MovePlayerSerializer_v291;
import com.nukkitx.protocol.bedrock.packet.MovePlayerPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class MovePlayerSerializer_v419 extends MovePlayerSerializer_v291 {
   public static final MovePlayerSerializer_v419 INSTANCE = new MovePlayerSerializer_v419();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MovePlayerPacket packet) {
      super.serialize(buffer, helper, packet);
      VarInts.writeUnsignedLong(buffer, packet.getTick());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MovePlayerPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setTick(VarInts.readUnsignedLong(buffer));
   }

   protected MovePlayerSerializer_v419() {
   }
}
