package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.GameType;
import com.nukkitx.protocol.bedrock.packet.UpdatePlayerGameTypePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class UpdatePlayerGameTypeSerializer_v407 implements BedrockPacketSerializer<UpdatePlayerGameTypePacket> {
   public static final UpdatePlayerGameTypeSerializer_v407 INSTANCE = new UpdatePlayerGameTypeSerializer_v407();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdatePlayerGameTypePacket packet) {
      VarInts.writeInt(buffer, packet.getGameType().ordinal());
      VarInts.writeLong(buffer, packet.getEntityId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdatePlayerGameTypePacket packet) {
      packet.setGameType(GameType.from(VarInts.readInt(buffer)));
      packet.setEntityId(VarInts.readLong(buffer));
   }
}
