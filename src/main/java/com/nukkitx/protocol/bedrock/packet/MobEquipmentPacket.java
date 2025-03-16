package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.common.PacketSignal;

public class MobEquipmentPacket implements BedrockPacket {
   private long runtimeEntityId;
   private ItemData item;
   private int inventorySlot;
   private int hotbarSlot;
   private int containerId;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.MOB_EQUIPMENT;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public ItemData getItem() {
      return this.item;
   }

   public int getInventorySlot() {
      return this.inventorySlot;
   }

   public int getHotbarSlot() {
      return this.hotbarSlot;
   }

   public int getContainerId() {
      return this.containerId;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setItem(ItemData item) {
      this.item = item;
   }

   public void setInventorySlot(int inventorySlot) {
      this.inventorySlot = inventorySlot;
   }

   public void setHotbarSlot(int hotbarSlot) {
      this.hotbarSlot = hotbarSlot;
   }

   public void setContainerId(int containerId) {
      this.containerId = containerId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MobEquipmentPacket)) {
         return false;
      } else {
         MobEquipmentPacket other = (MobEquipmentPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else if (this.inventorySlot != other.inventorySlot) {
            return false;
         } else if (this.hotbarSlot != other.hotbarSlot) {
            return false;
         } else if (this.containerId != other.containerId) {
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
      return other instanceof MobEquipmentPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      result = result * 59 + this.inventorySlot;
      result = result * 59 + this.hotbarSlot;
      result = result * 59 + this.containerId;
      Object $item = this.item;
      result = result * 59 + ($item == null ? 43 : $item.hashCode());
      return result;
   }

   public String toString() {
      return "MobEquipmentPacket(runtimeEntityId=" + this.runtimeEntityId + ", item=" + this.item + ", inventorySlot=" + this.inventorySlot + ", hotbarSlot=" + this.hotbarSlot + ", containerId=" + this.containerId + ")";
   }
}
