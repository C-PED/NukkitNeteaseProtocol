package com.nukkitx.protocol.bedrock.codec.v419.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.MotionPredictionHintsPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class MotionPredictionHintsSerializer_v419 implements BedrockPacketSerializer<MotionPredictionHintsPacket> {
   public static final MotionPredictionHintsSerializer_v419 INSTANCE = new MotionPredictionHintsSerializer_v419();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MotionPredictionHintsPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      helper.writeVector3f(buffer, packet.getMotion());
      buffer.writeBoolean(packet.isOnGround());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MotionPredictionHintsPacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setMotion(helper.readVector3f(buffer));
      packet.setOnGround(buffer.readBoolean());
   }

   protected MotionPredictionHintsSerializer_v419() {
   }
}
