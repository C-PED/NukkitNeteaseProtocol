package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.MapInfoRequestPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class MapInfoRequestSerializer_v291 implements BedrockPacketSerializer<MapInfoRequestPacket> {
   public static final MapInfoRequestSerializer_v291 INSTANCE = new MapInfoRequestSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MapInfoRequestPacket packet) {
      VarInts.writeLong(buffer, packet.getUniqueMapId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MapInfoRequestPacket packet) {
      packet.setUniqueMapId(VarInts.readLong(buffer));
   }

   protected MapInfoRequestSerializer_v291() {
   }
}
