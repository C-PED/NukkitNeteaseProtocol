package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ChangeDimensionPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class ChangeDimensionSerializer_v291 implements BedrockPacketSerializer<ChangeDimensionPacket> {
   public static final ChangeDimensionSerializer_v291 INSTANCE = new ChangeDimensionSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ChangeDimensionPacket packet) {
      VarInts.writeInt(buffer, packet.getDimension());
      helper.writeVector3f(buffer, packet.getPosition());
      buffer.writeBoolean(packet.isRespawn());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ChangeDimensionPacket packet) {
      packet.setDimension(VarInts.readInt(buffer));
      packet.setPosition(helper.readVector3f(buffer));
      packet.setRespawn(buffer.readBoolean());
   }

   protected ChangeDimensionSerializer_v291() {
   }
}
