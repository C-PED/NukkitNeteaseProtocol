package com.nukkitx.protocol.bedrock.codec.v554.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.GameTestRequestPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class GameTestRequestSerializer_v554 implements BedrockPacketSerializer<GameTestRequestPacket> {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, GameTestRequestPacket packet) {
      VarInts.writeInt(buffer, packet.getMaxTestsPerBatch());
      VarInts.writeInt(buffer, packet.getRepeatCount());
      buffer.writeByte(packet.getRotation());
      buffer.writeBoolean(packet.isStoppingOnFailure());
      helper.writeVector3i(buffer, packet.getTestPos());
      VarInts.writeInt(buffer, packet.getTestsPerRow());
      helper.writeString(buffer, packet.getTestName());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, GameTestRequestPacket packet) {
      packet.setMaxTestsPerBatch(VarInts.readInt(buffer));
      packet.setRepeatCount(VarInts.readInt(buffer));
      packet.setRotation(buffer.readByte());
      packet.setStoppingOnFailure(buffer.readBoolean());
      packet.setTestPos(helper.readVector3i(buffer));
      packet.setTestsPerRow(VarInts.readInt(buffer));
      packet.setTestName(helper.readString(buffer));
   }
}
