package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.MoveEntityAbsolutePacket;
import com.nukkitx.protocol.common.util.Preconditions;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import org.cloudburstmc.math.vector.Vector3f;

public class MoveEntityAbsoluteSerializer_v291 implements BedrockPacketSerializer<MoveEntityAbsolutePacket> {
   public static final MoveEntityAbsoluteSerializer_v291 INSTANCE = new MoveEntityAbsoluteSerializer_v291();
   private static final int FLAG_ON_GROUND = 1;
   private static final int FLAG_TELEPORTED = 2;
   private static final int FLAG_FORCE_MOVE = 4;

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, MoveEntityAbsolutePacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      int flags = 0;
      if (packet.isOnGround()) {
         flags |= 1;
      }

      if (packet.isTeleported()) {
         flags |= 2;
      }

      if (packet.isForceMove()) {
         flags |= 4;
      }

      buffer.writeByte(flags);
      helper.writeVector3f(buffer, packet.getPosition());
      this.writeByteRotation(buffer, helper, packet.getRotation());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, MoveEntityAbsolutePacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      int flags = buffer.readUnsignedByte();
      packet.setOnGround((flags & 1) != 0);
      packet.setTeleported((flags & 2) != 0);
      packet.setForceMove((flags & 4) != 0);
      packet.setPosition(helper.readVector3f(buffer));
      packet.setRotation(this.readByteRotation(buffer, helper));
   }

   protected Vector3f readByteRotation(ByteBuf buffer, BedrockCodecHelper helper) {
      float pitch = helper.readByteAngle(buffer);
      float yaw = helper.readByteAngle(buffer);
      float roll = helper.readByteAngle(buffer);
      return Vector3f.from(pitch, yaw, roll);
   }

   protected void writeByteRotation(ByteBuf buffer, BedrockCodecHelper helper, Vector3f rotation) {
      Preconditions.checkNotNull(rotation, "rotation");
      helper.writeByteAngle(buffer, rotation.getX());
      helper.writeByteAngle(buffer, rotation.getY());
      helper.writeByteAngle(buffer, rotation.getZ());
   }

   protected MoveEntityAbsoluteSerializer_v291() {
   }
}
