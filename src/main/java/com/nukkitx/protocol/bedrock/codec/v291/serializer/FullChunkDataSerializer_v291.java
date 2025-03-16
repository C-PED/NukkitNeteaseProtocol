package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.LevelChunkPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class FullChunkDataSerializer_v291 implements BedrockPacketSerializer<LevelChunkPacket> {
   public static final FullChunkDataSerializer_v291 INSTANCE = new FullChunkDataSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, LevelChunkPacket packet) {
      VarInts.writeInt(buffer, packet.getChunkX());
      VarInts.writeInt(buffer, packet.getChunkZ());
      helper.writeByteBuf(buffer, packet.getData());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, LevelChunkPacket packet) {
      packet.setChunkX(VarInts.readInt(buffer));
      packet.setChunkZ(VarInts.readInt(buffer));
      packet.setData(helper.readByteBuf(buffer));
   }

   protected FullChunkDataSerializer_v291() {
   }
}
