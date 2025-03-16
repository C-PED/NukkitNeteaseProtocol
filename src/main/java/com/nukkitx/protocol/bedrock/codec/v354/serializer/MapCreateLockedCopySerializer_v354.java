package com.nukkitx.protocol.bedrock.codec.v354.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.MapCreateLockedCopyPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class MapCreateLockedCopySerializer_v354 implements BedrockPacketSerializer<MapCreateLockedCopyPacket> {
   public static final MapCreateLockedCopySerializer_v354 INSTANCE = new MapCreateLockedCopySerializer_v354();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MapCreateLockedCopyPacket packet) {
      VarInts.writeLong(buffer, packet.getOriginalMapId());
      VarInts.writeLong(buffer, packet.getNewMapId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MapCreateLockedCopyPacket packet) {
      packet.setOriginalMapId(VarInts.readLong(buffer));
      packet.setNewMapId(VarInts.readLong(buffer));
   }

   protected MapCreateLockedCopySerializer_v354() {
   }
}
