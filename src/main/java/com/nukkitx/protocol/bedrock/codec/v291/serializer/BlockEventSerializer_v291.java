package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.BlockEventPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class BlockEventSerializer_v291 implements BedrockPacketSerializer<BlockEventPacket> {
   public static final BlockEventSerializer_v291 INSTANCE = new BlockEventSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BlockEventPacket packet) {
      helper.writeBlockPosition(buffer, packet.getBlockPosition());
      VarInts.writeInt(buffer, packet.getEventType());
      VarInts.writeInt(buffer, packet.getEventData());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BlockEventPacket packet) {
      packet.setBlockPosition(helper.readBlockPosition(buffer));
      packet.setEventType(VarInts.readInt(buffer));
      packet.setEventData(VarInts.readInt(buffer));
   }

   protected BlockEventSerializer_v291() {
   }
}
