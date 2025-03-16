package com.nukkitx.protocol.bedrock.codec.v388.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.NetworkSettingsPacket;
import io.netty.buffer.ByteBuf;

public class NetworkSettingsSerializer_v388 implements BedrockPacketSerializer<NetworkSettingsPacket> {
   public static final NetworkSettingsSerializer_v388 INSTANCE = new NetworkSettingsSerializer_v388();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkSettingsPacket packet) {
      buffer.writeShortLE(packet.getCompressionThreshold());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, NetworkSettingsPacket packet) {
      packet.setCompressionThreshold(buffer.readUnsignedShortLE());
   }

   protected NetworkSettingsSerializer_v388() {
   }
}
