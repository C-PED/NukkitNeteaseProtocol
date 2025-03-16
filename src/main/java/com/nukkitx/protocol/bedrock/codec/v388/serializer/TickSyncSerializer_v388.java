package com.nukkitx.protocol.bedrock.codec.v388.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.TickSyncPacket;
import io.netty.buffer.ByteBuf;

public class TickSyncSerializer_v388 implements BedrockPacketSerializer<TickSyncPacket> {
   public static final TickSyncSerializer_v388 INSTANCE = new TickSyncSerializer_v388();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TickSyncPacket packet) {
      buffer.writeLongLE(packet.getRequestTimestamp());
      buffer.writeLongLE(packet.getResponseTimestamp());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TickSyncPacket packet) {
      packet.setRequestTimestamp(buffer.readLongLE());
      packet.setResponseTimestamp(buffer.readLongLE());
   }

   protected TickSyncSerializer_v388() {
   }
}
