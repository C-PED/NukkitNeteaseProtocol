package com.neteasemc.protocol.custom.packet.formPacket;

import com.neteasemc.protocol.custom.GeyserPacketSerializer;
import io.netty.buffer.ByteBuf;

public class FormPacketSerializer implements GeyserPacketSerializer<FormPacket> {
   public static final FormPacketSerializer INSTANCE = new FormPacketSerializer();

   public void serialize(ByteBuf buffer, FormPacket packet) {
      buffer.writeBytes(packet.getFormData());
   }

   public void deserialize(ByteBuf buffer, FormPacket packet) {
      byte[] packetData = new byte[buffer.readableBytes()];
      buffer.readBytes(packetData);
      packet.setFormData(packetData);
   }
}
