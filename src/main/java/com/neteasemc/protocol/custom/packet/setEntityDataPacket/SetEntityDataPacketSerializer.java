package com.neteasemc.protocol.custom.packet.setEntityDataPacket;

import com.neteasemc.protocol.custom.GeyserPacketSerializer;
import io.netty.buffer.ByteBuf;

public class SetEntityDataPacketSerializer implements GeyserPacketSerializer<SetEntityDataPacket> {
   public static final SetEntityDataPacketSerializer INSTANCE = new SetEntityDataPacketSerializer();

   public void serialize(ByteBuf buffer, SetEntityDataPacket packet) {
      buffer.writeInt(packet.getEntityId());
      buffer.writeFloat(packet.getHeight());
      buffer.writeFloat(packet.getWidth());
      buffer.writeFloat(packet.getScale());
   }

   public void deserialize(ByteBuf buffer, SetEntityDataPacket packet) {
      packet.setEntityId(buffer.readInt());
      packet.setHeight(buffer.readFloat());
      packet.setWidth(buffer.readFloat());
      packet.setScale(buffer.readFloat());
   }
}
