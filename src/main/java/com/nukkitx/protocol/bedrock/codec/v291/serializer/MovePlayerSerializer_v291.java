package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.MovePlayerPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class MovePlayerSerializer_v291 implements BedrockPacketSerializer<MovePlayerPacket> {
   public static final MovePlayerSerializer_v291 INSTANCE = new MovePlayerSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MovePlayerPacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      helper.writeVector3f(buffer, packet.getPosition());
      helper.writeVector3f(buffer, packet.getRotation());
      buffer.writeByte(packet.getMode().ordinal());
      buffer.writeBoolean(packet.isOnGround());
      VarInts.writeUnsignedLong(buffer, packet.getRidingRuntimeEntityId());
      if (packet.getMode() == MovePlayerPacket.Mode.TELEPORT) {
         buffer.writeIntLE(packet.getTeleportationCause().ordinal());
         buffer.writeIntLE(packet.getEntityType());
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MovePlayerPacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setPosition(helper.readVector3f(buffer));
      packet.setRotation(helper.readVector3f(buffer));
      packet.setMode(MovePlayerPacket.Mode.values()[buffer.readUnsignedByte()]);
      packet.setOnGround(buffer.readBoolean());
      packet.setRidingRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      if (packet.getMode() == MovePlayerPacket.Mode.TELEPORT) {
         packet.setTeleportationCause(MovePlayerPacket.TeleportationCause.byId(buffer.readIntLE()));
         packet.setEntityType(buffer.readIntLE());
      }

   }

   protected MovePlayerSerializer_v291() {
   }
}
