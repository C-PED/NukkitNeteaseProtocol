package com.nukkitx.protocol.bedrock.codec.v527.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.PlayerActionType;
import com.nukkitx.protocol.bedrock.packet.PlayerActionPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class PlayerActionSerializer_v527 implements BedrockPacketSerializer<PlayerActionPacket> {
   private static final PlayerActionType[] TYPES = PlayerActionType.values();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerActionPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      VarInts.writeInt(buffer, packet.getAction().ordinal());
      helper.writeBlockPosition(buffer, packet.getBlockPosition());
      helper.writeBlockPosition(buffer, packet.getResultPosition());
      VarInts.writeInt(buffer, packet.getFace());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerActionPacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setAction(TYPES[VarInts.readInt(buffer)]);
      packet.setBlockPosition(helper.readBlockPosition(buffer));
      packet.setResultPosition(helper.readBlockPosition(buffer));
      packet.setFace(VarInts.readInt(buffer));
   }
}
