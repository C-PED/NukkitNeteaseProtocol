package com.nukkitx.protocol.bedrock.codec.v503.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.TickingAreasLoadStatusPacket;
import io.netty.buffer.ByteBuf;

public class TickingAreasLoadStatusSerializer_v503 implements BedrockPacketSerializer<TickingAreasLoadStatusPacket> {
   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TickingAreasLoadStatusPacket packet) {
      buffer.writeBoolean(packet.isWaitingForPreload());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TickingAreasLoadStatusPacket packet) {
      packet.setWaitingForPreload(buffer.readBoolean());
   }
}
