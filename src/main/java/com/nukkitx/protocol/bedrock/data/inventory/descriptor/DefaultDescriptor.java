package com.nukkitx.protocol.bedrock.data.inventory.descriptor;

import com.nukkitx.protocol.bedrock.data.definitions.ItemDefinition;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;

public final class DefaultDescriptor implements ItemDescriptor {
   private final ItemDefinition itemId;
   private final int auxValue;

   public ItemDescriptorType getType() {
      return ItemDescriptorType.DEFAULT;
   }

   public ItemData.Builder toItem() {
      return ItemData.builder().definition(this.itemId).damage(this.auxValue);
   }

   public DefaultDescriptor(ItemDefinition itemId, int auxValue) {
      this.itemId = itemId;
      this.auxValue = auxValue;
   }

   public ItemDefinition getItemId() {
      return this.itemId;
   }

   public int getAuxValue() {
      return this.auxValue;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DefaultDescriptor)) {
         return false;
      } else {
         DefaultDescriptor other = (DefaultDescriptor)o;
         if (this.getAuxValue() != other.getAuxValue()) {
            return false;
         } else {
            Object this$itemId = this.getItemId();
            Object other$itemId = other.getItemId();
            if (this$itemId == null) {
               if (other$itemId != null) {
                  return false;
               }
            } else if (!this$itemId.equals(other$itemId)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getAuxValue();
      Object $itemId = this.getItemId();
      result = result * 59 + ($itemId == null ? 43 : $itemId.hashCode());
      return result;
   }

   public String toString() {
      return "DefaultDescriptor(itemId=" + this.getItemId() + ", auxValue=" + this.getAuxValue() + ")";
   }
}
