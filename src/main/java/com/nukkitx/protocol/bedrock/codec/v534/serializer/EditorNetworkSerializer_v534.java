package com.nukkitx.protocol.bedrock.codec.v534.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.EditorNetworkPacket;
import io.netty.buffer.ByteBuf;

public class EditorNetworkSerializer_v534 implements BedrockPacketSerializer<EditorNetworkPacket> {
   public static final EditorNetworkSerializer_v534 INSTANCE = new EditorNetworkSerializer_v534();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EditorNetworkPacket packet) {
      helper.writeTag(buffer, packet.getPayload());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EditorNetworkPacket packet) {
      packet.setPayload(helper.readTag(buffer, Object.class));
   }

   protected EditorNetworkSerializer_v534() {
   }
}
