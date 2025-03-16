package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import java.util.EnumSet;
import java.util.Set;

public class MoveEntityDeltaPacket implements BedrockPacket {
   private long runtimeEntityId;
   private final Set<Flag> flags = EnumSet.noneOf(Flag.class);
   private int deltaX;
   private int deltaY;
   private int deltaZ;
   private float x;
   private float y;
   private float z;
   private float pitch;
   private float yaw;
   private float headYaw;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.MOVE_ENTITY_DELTA;
   }

   public String toString() {
      return "MoveEntityDeltaPacket(runtimeEntityId=" + this.runtimeEntityId + ", flags=" + this.flags + ", delta=(" + this.deltaX + ", " + this.deltaY + ", " + this.deltaZ + "), position=(" + this.x + ", " + this.y + ", " + this.z + "), rotation=(" + this.pitch + ", " + this.yaw + ", " + this.headYaw + "))";
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public Set<Flag> getFlags() {
      return this.flags;
   }

   public int getDeltaX() {
      return this.deltaX;
   }

   public int getDeltaY() {
      return this.deltaY;
   }

   public int getDeltaZ() {
      return this.deltaZ;
   }

   public float getX() {
      return this.x;
   }

   public float getY() {
      return this.y;
   }

   public float getZ() {
      return this.z;
   }

   public float getPitch() {
      return this.pitch;
   }

   public float getYaw() {
      return this.yaw;
   }

   public float getHeadYaw() {
      return this.headYaw;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setDeltaX(int deltaX) {
      this.deltaX = deltaX;
   }

   public void setDeltaY(int deltaY) {
      this.deltaY = deltaY;
   }

   public void setDeltaZ(int deltaZ) {
      this.deltaZ = deltaZ;
   }

   public void setX(float x) {
      this.x = x;
   }

   public void setY(float y) {
      this.y = y;
   }

   public void setZ(float z) {
      this.z = z;
   }

   public void setPitch(float pitch) {
      this.pitch = pitch;
   }

   public void setYaw(float yaw) {
      this.yaw = yaw;
   }

   public void setHeadYaw(float headYaw) {
      this.headYaw = headYaw;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MoveEntityDeltaPacket)) {
         return false;
      } else {
         MoveEntityDeltaPacket other = (MoveEntityDeltaPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else if (this.deltaX != other.deltaX) {
            return false;
         } else if (this.deltaY != other.deltaY) {
            return false;
         } else if (this.deltaZ != other.deltaZ) {
            return false;
         } else if (Float.compare(this.x, other.x) != 0) {
            return false;
         } else if (Float.compare(this.y, other.y) != 0) {
            return false;
         } else if (Float.compare(this.z, other.z) != 0) {
            return false;
         } else if (Float.compare(this.pitch, other.pitch) != 0) {
            return false;
         } else if (Float.compare(this.yaw, other.yaw) != 0) {
            return false;
         } else if (Float.compare(this.headYaw, other.headYaw) != 0) {
            return false;
         } else {
            Object this$flags = this.flags;
            Object other$flags = other.flags;
            if (this$flags == null) {
               if (other$flags != null) {
                  return false;
               }
            } else if (!this$flags.equals(other$flags)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof MoveEntityDeltaPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      result = result * 59 + this.deltaX;
      result = result * 59 + this.deltaY;
      result = result * 59 + this.deltaZ;
      result = result * 59 + Float.floatToIntBits(this.x);
      result = result * 59 + Float.floatToIntBits(this.y);
      result = result * 59 + Float.floatToIntBits(this.z);
      result = result * 59 + Float.floatToIntBits(this.pitch);
      result = result * 59 + Float.floatToIntBits(this.yaw);
      result = result * 59 + Float.floatToIntBits(this.headYaw);
      Object $flags = this.flags;
      result = result * 59 + ($flags == null ? 43 : $flags.hashCode());
      return result;
   }

   public static enum Flag {
      HAS_X,
      HAS_Y,
      HAS_Z,
      HAS_PITCH,
      HAS_YAW,
      HAS_HEAD_YAW,
      ON_GROUND,
      TELEPORTING,
      FORCE_MOVE_LOCAL_ENTITY;

      // $FF: synthetic method
      private static Flag[] $values() {
         return new Flag[]{HAS_X, HAS_Y, HAS_Z, HAS_PITCH, HAS_YAW, HAS_HEAD_YAW, ON_GROUND, TELEPORTING, FORCE_MOVE_LOCAL_ENTITY};
      }
   }
}
