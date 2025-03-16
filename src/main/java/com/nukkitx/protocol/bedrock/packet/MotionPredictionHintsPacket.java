package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3f;

public class MotionPredictionHintsPacket implements BedrockPacket {
   private long runtimeEntityId;
   private Vector3f motion;
   private boolean onGround;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_ENTITY_MOTION_PLUS;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public Vector3f getMotion() {
      return this.motion;
   }

   public boolean isOnGround() {
      return this.onGround;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setMotion(Vector3f motion) {
      this.motion = motion;
   }

   public void setOnGround(boolean onGround) {
      this.onGround = onGround;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MotionPredictionHintsPacket)) {
         return false;
      } else {
         MotionPredictionHintsPacket other = (MotionPredictionHintsPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else if (this.onGround != other.onGround) {
            return false;
         } else {
            Object this$motion = this.motion;
            Object other$motion = other.motion;
            if (this$motion == null) {
               if (other$motion != null) {
                  return false;
               }
            } else if (!this$motion.equals(other$motion)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof MotionPredictionHintsPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      result = result * 59 + (this.onGround ? 79 : 97);
      Object $motion = this.motion;
      result = result * 59 + ($motion == null ? 43 : $motion.hashCode());
      return result;
   }

   public String toString() {
      return "MotionPredictionHintsPacket(runtimeEntityId=" + this.runtimeEntityId + ", motion=" + this.motion + ", onGround=" + this.onGround + ")";
   }
}
