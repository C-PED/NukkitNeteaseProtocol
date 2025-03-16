package com.nukkitx.protocol.bedrock.codec.v554.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ServerStatsPacket;
import io.netty.buffer.ByteBuf;

public class ServerStatsSerializer_v554 implements BedrockPacketSerializer<ServerStatsPacket> {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerStatsPacket packet) {
      buffer.writeFloatLE(packet.getServerTime());
      buffer.writeFloatLE(packet.getNetworkTime());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerStatsPacket packet) {
      packet.setServerTime(buffer.readFloatLE());
      packet.setNetworkTime(buffer.readFloatLE());
   }
}
