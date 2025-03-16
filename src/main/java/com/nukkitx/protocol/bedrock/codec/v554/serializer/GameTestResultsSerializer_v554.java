package com.nukkitx.protocol.bedrock.codec.v554.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.GameTestResultsPacket;
import io.netty.buffer.ByteBuf;

public class GameTestResultsSerializer_v554 implements BedrockPacketSerializer<GameTestResultsPacket> {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, GameTestResultsPacket packet) {
      buffer.writeBoolean(packet.isSuccessful());
      helper.writeString(buffer, packet.getError());
      helper.writeString(buffer, packet.getTestName());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, GameTestResultsPacket packet) {
      packet.setSuccessful(buffer.readBoolean());
      packet.setError(helper.readString(buffer));
      packet.setTestName(helper.readString(buffer));
   }
}
