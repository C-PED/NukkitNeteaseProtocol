package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.SetSpawnPositionPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class SetSpawnPositionSerializer_v407 implements BedrockPacketSerializer<SetSpawnPositionPacket> {
   public static final SetSpawnPositionSerializer_v407 INSTANCE = new SetSpawnPositionSerializer_v407();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, SetSpawnPositionPacket packet) {
      VarInts.writeInt(buffer, packet.getSpawnType().ordinal());
      helper.writeBlockPosition(buffer, packet.getBlockPosition());
      VarInts.writeInt(buffer, packet.getDimensionId());
      helper.writeBlockPosition(buffer, packet.getSpawnPosition());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, SetSpawnPositionPacket packet) {
      packet.setSpawnType(SetSpawnPositionPacket.Type.values()[VarInts.readInt(buffer)]);
      packet.setBlockPosition(helper.readBlockPosition(buffer));
      packet.setDimensionId(VarInts.readInt(buffer));
      packet.setSpawnPosition(helper.readBlockPosition(buffer));
   }

   protected SetSpawnPositionSerializer_v407() {
   }
}
