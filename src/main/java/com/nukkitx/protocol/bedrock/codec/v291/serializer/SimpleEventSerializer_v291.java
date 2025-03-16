package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.SimpleEventType;
import com.nukkitx.protocol.bedrock.packet.SimpleEventPacket;
import io.netty.buffer.ByteBuf;

public class SimpleEventSerializer_v291 implements BedrockPacketSerializer<SimpleEventPacket> {
   public static final SimpleEventSerializer_v291 INSTANCE = new SimpleEventSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SimpleEventPacket packet) {
      buffer.writeShortLE(packet.getEvent().ordinal());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SimpleEventPacket packet) {
      packet.setEvent(SimpleEventType.values()[buffer.readUnsignedShortLE()]);
   }

   protected SimpleEventSerializer_v291() {
   }
}
