package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SetEntityMotionPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SetEntityMotionSerializer_v291 implements BedrockPacketSerializer<SetEntityMotionPacket> {
   public static final SetEntityMotionSerializer_v291 INSTANCE = new SetEntityMotionSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetEntityMotionPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      helper.writeVector3f(buffer, packet.getMotion());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetEntityMotionPacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setMotion(helper.readVector3f(buffer));
   }

   protected SetEntityMotionSerializer_v291() {
   }
}
