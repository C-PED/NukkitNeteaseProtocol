package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.PlayerActionType;
import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3i;

public class PlayerActionPacket implements BedrockPacket {
   private long runtimeEntityId;
   private PlayerActionType action;
   private Vector3i blockPosition;
   private Vector3i resultPosition;
   private int face;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.PLAYER_ACTION;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public PlayerActionType getAction() {
      return this.action;
   }

   public Vector3i getBlockPosition() {
      return this.blockPosition;
   }

   public Vector3i getResultPosition() {
      return this.resultPosition;
   }

   public int getFace() {
      return this.face;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setAction(PlayerActionType action) {
      this.action = action;
   }

   public void setBlockPosition(Vector3i blockPosition) {
      this.blockPosition = blockPosition;
   }

   public void setResultPosition(Vector3i resultPosition) {
      this.resultPosition = resultPosition;
   }

   public void setFace(int face) {
      this.face = face;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PlayerActionPacket)) {
         return false;
      } else {
         PlayerActionPacket other = (PlayerActionPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else if (this.face != other.face) {
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

            Object this$blockPosition = this.blockPosition;
            Object other$blockPosition = other.blockPosition;
            if (this$blockPosition == null) {
               if (other$blockPosition != null) {
                  return false;
               }
            } else if (!this$blockPosition.equals(other$blockPosition)) {
               return false;
            }

            Object this$resultPosition = this.resultPosition;
            Object other$resultPosition = other.resultPosition;
            if (this$resultPosition == null) {
               if (other$resultPosition != null) {
                  return false;
               }
            } else if (!this$resultPosition.equals(other$resultPosition)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PlayerActionPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      result = result * 59 + this.face;
      Object $action = this.action;
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      Object $blockPosition = this.blockPosition;
      result = result * 59 + ($blockPosition == null ? 43 : $blockPosition.hashCode());
      Object $resultPosition = this.resultPosition;
      result = result * 59 + ($resultPosition == null ? 43 : $resultPosition.hashCode());
      return result;
   }

   public String toString() {
      return "PlayerActionPacket(runtimeEntityId=" + this.runtimeEntityId + ", action=" + this.action + ", blockPosition=" + this.blockPosition + ", resultPosition=" + this.resultPosition + ", face=" + this.face + ")";
   }
}
