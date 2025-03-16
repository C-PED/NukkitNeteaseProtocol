package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ServerSettingsRequestPacket;
import io.netty.buffer.ByteBuf;

public class ServerSettingsRequestSerializer_v291 implements BedrockPacketSerializer<ServerSettingsRequestPacket> {
   public static final ServerSettingsRequestSerializer_v291 INSTANCE = new ServerSettingsRequestSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerSettingsRequestPacket packet) {
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerSettingsRequestPacket packet) {
   }

   protected ServerSettingsRequestSerializer_v291() {
   }
}
