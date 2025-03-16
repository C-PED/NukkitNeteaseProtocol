package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.PlayerActionType;
import com.nukkitx.protocol.bedrock.packet.PlayerActionPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class PlayerActionSerializer_v291 implements BedrockPacketSerializer<PlayerActionPacket> {
   public static final PlayerActionSerializer_v291 INSTANCE = new PlayerActionSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerActionPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      VarInts.writeInt(buffer, packet.getAction().ordinal());
      helper.writeBlockPosition(buffer, packet.getBlockPosition());
      VarInts.writeInt(buffer, packet.getFace());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerActionPacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setAction(PlayerActionType.values()[VarInts.readInt(buffer)]);
      packet.setBlockPosition(helper.readBlockPosition(buffer));
      packet.setFace(VarInts.readInt(buffer));
   }

   protected PlayerActionSerializer_v291() {
   }
}
