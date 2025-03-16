package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.annotation.NoEncryption;
import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ServerToClientHandshakePacket;
import io.netty.buffer.ByteBuf;

@NoEncryption
public class ServerToClientHandshakeSerializer_v291 implements BedrockPacketSerializer<ServerToClientHandshakePacket> {
   public static final ServerToClientHandshakeSerializer_v291 INSTANCE = new ServerToClientHandshakeSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ServerToClientHandshakePacket packet) {
      helper.writeString(buffer, packet.getJwt());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ServerToClientHandshakePacket packet) {
      packet.setJwt(helper.readString(buffer));
   }

   protected ServerToClientHandshakeSerializer_v291() {
   }
}
