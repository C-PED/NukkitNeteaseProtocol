package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3i;

public class ToggleCrafterSlotRequestPacket implements BedrockPacket {
   private Vector3i blockPosition;
   private byte slot;
   private boolean disabled;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.TOGGLE_CRAFTER_SLOT_REQUEST;
   }

   public Vector3i getBlockPosition() {
      return this.blockPosition;
   }

   public byte getSlot() {
      return this.slot;
   }

   public boolean isDisabled() {
      return this.disabled;
   }

   public void setBlockPosition(Vector3i blockPosition) {
      this.blockPosition = blockPosition;
   }

   public void setSlot(byte slot) {
      this.slot = slot;
   }

   public void setDisabled(boolean disabled) {
      this.disabled = disabled;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ToggleCrafterSlotRequestPacket)) {
         return false;
      } else {
         ToggleCrafterSlotRequestPacket other = (ToggleCrafterSlotRequestPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.slot != other.slot) {
            return false;
         } else if (this.disabled != other.disabled) {
            return false;
         } else {
            Object this$blockPosition = this.blockPosition;
            Object other$blockPosition = other.blockPosition;
            if (this$blockPosition == null) {
               if (other$blockPosition != null) {
                  return false;
               }
            } else if (!this$blockPosition.equals(other$blockPosition)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ToggleCrafterSlotRequestPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.slot;
      result = result * 59 + (this.disabled ? 79 : 97);
      Object $blockPosition = this.blockPosition;
      result = result * 59 + ($blockPosition == null ? 43 : $blockPosition.hashCode());
      return result;
   }

   public String toString() {
      return "ToggleCrafterSlotRequestPacket(blockPosition=" + this.blockPosition + ", slot=" + this.slot + ", disabled=" + this.disabled + ")";
   }
}
