package com.neteasemc.protocol.custom.packet.spigotInfoPacket;

import com.neteasemc.protocol.custom.GeyserPacketSerializer;
import com.nukkitx.protocol.common.util.CommonWriteUtil;
import io.netty.buffer.ByteBuf;

public class SpigotInfoPacketSerializer implements GeyserPacketSerializer<SpigotInfoPacket> {
   public static final SpigotInfoPacketSerializer INSTANCE = new SpigotInfoPacketSerializer();

   public void serialize(ByteBuf buffer, SpigotInfoPacket packet) {
      CommonWriteUtil.writeString(buffer, packet.getSpigotVersion());
   }

   public void deserialize(ByteBuf buffer, SpigotInfoPacket packet) {
      String spigotVersion = CommonWriteUtil.readString(buffer);
      packet.setSpigotVersion(spigotVersion);
   }
}
