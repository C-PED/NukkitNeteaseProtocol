package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.common.PacketSignal;

public class InventorySlotPacket implements BedrockPacket {
   private int containerId;
   private int slot;
   private ItemData item;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.INVENTORY_SLOT;
   }

   public int getContainerId() {
      return this.containerId;
   }

   public int getSlot() {
      return this.slot;
   }

   public ItemData getItem() {
      return this.item;
   }

   public void setContainerId(int containerId) {
      this.containerId = containerId;
   }

   public void setSlot(int slot) {
      this.slot = slot;
   }

   public void setItem(ItemData item) {
      this.item = item;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof InventorySlotPacket)) {
         return false;
      } else {
         InventorySlotPacket other = (InventorySlotPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.containerId != other.containerId) {
            return false;
         } else if (this.slot != other.slot) {
            return false;
         } else {
            Object this$item = this.item;
            Object other$item = other.item;
            if (this$item == null) {
               if (other$item != null) {
                  return false;
               }
            } else if (!this$item.equals(other$item)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof InventorySlotPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.containerId;
      result = result * 59 + this.slot;
      Object $item = this.item;
      result = result * 59 + ($item == null ? 43 : $item.hashCode());
      return result;
   }

   public String toString() {
      return "InventorySlotPacket(containerId=" + this.containerId + ", slot=" + this.slot + ", item=" + this.item + ")";
   }
}
