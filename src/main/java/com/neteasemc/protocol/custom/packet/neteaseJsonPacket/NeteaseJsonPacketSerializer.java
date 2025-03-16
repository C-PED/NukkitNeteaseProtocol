package com.neteasemc.protocol.custom.packet.neteaseJsonPacket;

import com.neteasemc.protocol.custom.GeyserPacketSerializer;
import com.nukkitx.protocol.common.util.CommonWriteUtil;
import io.netty.buffer.ByteBuf;

public class NeteaseJsonPacketSerializer implements GeyserPacketSerializer<NeteaseJsonPacket> {
   public static final NeteaseJsonPacketSerializer INSTANCE = new NeteaseJsonPacketSerializer();

   public void serialize(ByteBuf buffer, NeteaseJsonPacket packet) {
      CommonWriteUtil.writeString(buffer, packet.getJsonString());
   }

   public void deserialize(ByteBuf buffer, NeteaseJsonPacket packet) {
      packet.setJsonString(CommonWriteUtil.readString(buffer));
   }
}
