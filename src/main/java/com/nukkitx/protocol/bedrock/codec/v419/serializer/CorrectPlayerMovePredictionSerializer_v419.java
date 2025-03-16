package com.nukkitx.protocol.bedrock.codec.v419.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.CorrectPlayerMovePredictionPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class CorrectPlayerMovePredictionSerializer_v419 implements BedrockPacketSerializer<CorrectPlayerMovePredictionPacket> {
   public static final CorrectPlayerMovePredictionSerializer_v419 INSTANCE = new CorrectPlayerMovePredictionSerializer_v419();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CorrectPlayerMovePredictionPacket packet) {
      helper.writeVector3f(buffer, packet.getPosition());
      helper.writeVector3f(buffer, packet.getDelta());
      buffer.writeBoolean(packet.isOnGround());
      VarInts.writeUnsignedLong(buffer, packet.getTick());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CorrectPlayerMovePredictionPacket packet) {
      packet.setPosition(helper.readVector3f(buffer));
      packet.setDelta(helper.readVector3f(buffer));
      packet.setOnGround(buffer.readBoolean());
      packet.setTick((long)VarInts.readUnsignedInt(buffer));
   }

   protected CorrectPlayerMovePredictionSerializer_v419() {
   }
}
