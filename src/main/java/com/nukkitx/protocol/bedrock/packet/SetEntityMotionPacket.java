package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3f;

public class SetEntityMotionPacket implements BedrockPacket {
   private long runtimeEntityId;
   private Vector3f motion;
   private long tick;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_ENTITY_MOTION;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public Vector3f getMotion() {
      return this.motion;
   }

   public long getTick() {
      return this.tick;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setMotion(Vector3f motion) {
      this.motion = motion;
   }

   public void setTick(long tick) {
      this.tick = tick;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetEntityMotionPacket)) {
         return false;
      } else {
         SetEntityMotionPacket other = (SetEntityMotionPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else if (this.tick != other.tick) {
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
      return other instanceof SetEntityMotionPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      long $tick = this.tick;
      result = result * 59 + (int)($tick >>> 32 ^ $tick);
      Object $motion = this.motion;
      result = result * 59 + ($motion == null ? 43 : $motion.hashCode());
      return result;
   }

   public String toString() {
      return "SetEntityMotionPacket(runtimeEntityId=" + this.runtimeEntityId + ", motion=" + this.motion + ", tick=" + this.tick + ")";
   }
}
