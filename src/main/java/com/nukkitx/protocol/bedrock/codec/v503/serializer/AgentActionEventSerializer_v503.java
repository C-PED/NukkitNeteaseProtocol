package com.nukkitx.protocol.bedrock.codec.v503.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.ee.AgentActionType;
import com.nukkitx.protocol.bedrock.packet.AgentActionEventPacket;
import io.netty.buffer.ByteBuf;

public class AgentActionEventSerializer_v503 implements BedrockPacketSerializer<AgentActionEventPacket> {
   private static final AgentActionType[] VALUES = AgentActionType.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, AgentActionEventPacket packet) {
      helper.writeString(buffer, packet.getRequestId());
      buffer.writeIntLE(packet.getActionType().ordinal());
      helper.writeString(buffer, packet.getResponseJson());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, AgentActionEventPacket packet) {
      packet.setRequestId(helper.readString(buffer));
      packet.setActionType(VALUES[buffer.readIntLE()]);
      packet.setResponseJson(helper.readString(buffer));
   }
}
