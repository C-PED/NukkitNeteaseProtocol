package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class AnimatePacket implements BedrockPacket {
   private float rowingTime;
   private Action action;
   private long runtimeEntityId;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ANIMATE;
   }

   public float getRowingTime() {
      return this.rowingTime;
   }

   public Action getAction() {
      return this.action;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public void setRowingTime(float rowingTime) {
      this.rowingTime = rowingTime;
   }

   public void setAction(Action action) {
      this.action = action;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AnimatePacket)) {
         return false;
      } else {
         AnimatePacket other = (AnimatePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.rowingTime, other.rowingTime) != 0) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else {
            Object this$action = this.action;
            Object other$action = other.action;
            if (this$action == null) {
               if (other$action != null) {
                  return false;
               }
            } else if (!this$action.equals(other$action)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AnimatePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.rowingTime);
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      Object $action = this.action;
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      return result;
   }

   public String toString() {
      return "AnimatePacket(rowingTime=" + this.rowingTime + ", action=" + this.action + ", runtimeEntityId=" + this.runtimeEntityId + ")";
   }

   public static enum Action {
      NO_ACTION,
      SWING_ARM,
      WAKE_UP,
      CRITICAL_HIT,
      MAGIC_CRITICAL_HIT,
      ROW_RIGHT,
      ROW_LEFT;

      // $FF: synthetic method
      private static Action[] $values() {
         return new Action[]{NO_ACTION, SWING_ARM, WAKE_UP, CRITICAL_HIT, MAGIC_CRITICAL_HIT, ROW_RIGHT, ROW_LEFT};
      }
   }
}
