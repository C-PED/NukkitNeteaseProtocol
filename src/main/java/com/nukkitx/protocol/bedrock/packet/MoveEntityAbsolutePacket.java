package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3f;

public class MoveEntityAbsolutePacket implements BedrockPacket {
   private long runtimeEntityId;
   private Vector3f position;
   private Vector3f rotation;
   private boolean onGround;
   private boolean teleported;
   private boolean forceMove;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.MOVE_ENTITY_ABSOLUTE;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public Vector3f getPosition() {
      return this.position;
   }

   public Vector3f getRotation() {
      return this.rotation;
   }

   public boolean isOnGround() {
      return this.onGround;
   }

   public boolean isTeleported() {
      return this.teleported;
   }

   public boolean isForceMove() {
      return this.forceMove;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setPosition(Vector3f position) {
      this.position = position;
   }

   public void setRotation(Vector3f rotation) {
      this.rotation = rotation;
   }

   public void setOnGround(boolean onGround) {
      this.onGround = onGround;
   }

   public void setTeleported(boolean teleported) {
      this.teleported = teleported;
   }

   public void setForceMove(boolean forceMove) {
      this.forceMove = forceMove;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MoveEntityAbsolutePacket)) {
         return false;
      } else {
         MoveEntityAbsolutePacket other = (MoveEntityAbsolutePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else if (this.onGround != other.onGround) {
            return false;
         } else if (this.teleported != other.teleported) {
            return false;
         } else if (this.forceMove != other.forceMove) {
            return false;
         } else {
            Object this$position = this.position;
            Object other$position = other.position;
            if (this$position == null) {
               if (other$position != null) {
                  return false;
               }
            } else if (!this$position.equals(other$position)) {
               return false;
            }

            Object this$rotation = this.rotation;
            Object other$rotation = other.rotation;
            if (this$rotation == null) {
               if (other$rotation != null) {
                  return false;
               }
            } else if (!this$rotation.equals(other$rotation)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof MoveEntityAbsolutePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      result = result * 59 + (this.onGround ? 79 : 97);
      result = result * 59 + (this.teleported ? 79 : 97);
      result = result * 59 + (this.forceMove ? 79 : 97);
      Object $position = this.position;
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      Object $rotation = this.rotation;
      result = result * 59 + ($rotation == null ? 43 : $rotation.hashCode());
      return result;
   }

   public String toString() {
      return "MoveEntityAbsolutePacket(runtimeEntityId=" + this.runtimeEntityId + ", position=" + this.position + ", rotation=" + this.rotation + ", onGround=" + this.onGround + ", teleported=" + this.teleported + ", forceMove=" + this.forceMove + ")";
   }
}
