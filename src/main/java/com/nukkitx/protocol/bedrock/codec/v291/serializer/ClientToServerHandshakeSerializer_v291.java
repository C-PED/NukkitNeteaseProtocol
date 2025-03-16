package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ClientToServerHandshakePacket;
import io.netty.buffer.ByteBuf;

public class ClientToServerHandshakeSerializer_v291 implements BedrockPacketSerializer<ClientToServerHandshakePacket> {
   public static final ClientToServerHandshakeSerializer_v291 INSTANCE = new ClientToServerHandshakeSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ClientToServerHandshakePacket packet) {
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ClientToServerHandshakePacket packet) {
   }

   protected ClientToServerHandshakeSerializer_v291() {
   }
}
