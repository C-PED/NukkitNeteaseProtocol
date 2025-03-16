package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3f;

public class InteractPacket implements BedrockPacket {
   private Action action;
   private long runtimeEntityId;
   private Vector3f mousePosition;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.INTERACT;
   }

   public Action getAction() {
      return this.action;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public Vector3f getMousePosition() {
      return this.mousePosition;
   }

   public void setAction(Action action) {
      this.action = action;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setMousePosition(Vector3f mousePosition) {
      this.mousePosition = mousePosition;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof InteractPacket)) {
         return false;
      } else {
         InteractPacket other = (InteractPacket)o;
         if (!other.canEqual(this)) {
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

            Object this$mousePosition = this.mousePosition;
            Object other$mousePosition = other.mousePosition;
            if (this$mousePosition == null) {
               if (other$mousePosition != null) {
                  return false;
               }
            } else if (!this$mousePosition.equals(other$mousePosition)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof InteractPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      Object $action = this.action;
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      Object $mousePosition = this.mousePosition;
      result = result * 59 + ($mousePosition == null ? 43 : $mousePosition.hashCode());
      return result;
   }

   public String toString() {
      return "InteractPacket(action=" + this.action + ", runtimeEntityId=" + this.runtimeEntityId + ", mousePosition=" + this.mousePosition + ")";
   }

   public static enum Action {
      NONE,
      INTERACT,
      DAMAGE,
      LEAVE_VEHICLE,
      MOUSEOVER,
      NPC_OPEN,
      OPEN_INVENTORY;

      // $FF: synthetic method
      private static Action[] $values() {
         return new Action[]{NONE, INTERACT, DAMAGE, LEAVE_VEHICLE, MOUSEOVER, NPC_OPEN, OPEN_INVENTORY};
      }
   }
}
