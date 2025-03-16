package com.neteasemc.protocol.custom.packet.geyserTransferPacket;

import com.neteasemc.protocol.custom.GeyserPacketSerializer;
import io.netty.buffer.ByteBuf;

public class GeyserTransferPacketSerializer implements GeyserPacketSerializer<GeyserTransferPacket> {
   public static final GeyserTransferPacketSerializer INSTANCE = new GeyserTransferPacketSerializer();

   public void serialize(ByteBuf buffer, GeyserTransferPacket packet) {
      buffer.writeBytes(packet.getTransferData());
   }

   public void deserialize(ByteBuf buffer, GeyserTransferPacket packet) {
      byte[] packetData = new byte[buffer.readableBytes()];
      buffer.readBytes(packetData);
      packet.setTransferData(packetData);
   }
}
