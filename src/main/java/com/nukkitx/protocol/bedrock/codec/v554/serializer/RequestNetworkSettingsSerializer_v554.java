package com.nukkitx.protocol.bedrock.codec.v554.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.RequestNetworkSettingsPacket;
import io.netty.buffer.ByteBuf;

public class RequestNetworkSettingsSerializer_v554 implements BedrockPacketSerializer<RequestNetworkSettingsPacket> {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, RequestNetworkSettingsPacket packet) {
      buffer.writeInt(packet.getProtocolVersion());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, RequestNetworkSettingsPacket packet) {
      packet.setProtocolVersion(buffer.readInt());
   }
}
