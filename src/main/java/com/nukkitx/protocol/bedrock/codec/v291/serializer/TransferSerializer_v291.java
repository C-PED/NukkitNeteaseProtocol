package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.TransferPacket;
import io.netty.buffer.ByteBuf;

public class TransferSerializer_v291 implements BedrockPacketSerializer<TransferPacket> {
   public static final TransferSerializer_v291 INSTANCE = new TransferSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TransferPacket packet) {
      helper.writeString(buffer, packet.getAddress());
      buffer.writeShortLE(packet.getPort());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TransferPacket packet) {
      packet.setAddress(helper.readString(buffer));
      packet.setPort(buffer.readShortLE());
   }

   protected TransferSerializer_v291() {
   }
}
