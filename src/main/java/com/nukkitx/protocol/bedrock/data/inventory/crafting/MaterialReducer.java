package com.nukkitx.protocol.bedrock.data.inventory.crafting;

import com.nukkitx.protocol.bedrock.data.definitions.ItemDefinition;
import it.unimi.dsi.fastutil.objects.Object2IntMap;

public final class MaterialReducer {
   private final int inputId;
   private final Object2IntMap<ItemDefinition> itemCounts;

   public MaterialReducer(int inputId, Object2IntMap<ItemDefinition> itemCounts) {
      this.inputId = inputId;
      this.itemCounts = itemCounts;
   }

   public int getInputId() {
      return this.inputId;
   }

   public Object2IntMap<ItemDefinition> getItemCounts() {
      return this.itemCounts;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MaterialReducer)) {
         return false;
      } else {
         MaterialReducer other = (MaterialReducer)o;
         if (this.getInputId() != other.getInputId()) {
            return false;
         } else {
            Object this$itemCounts = this.getItemCounts();
            Object other$itemCounts = other.getItemCounts();
            if (this$itemCounts == null) {
               if (other$itemCounts != null) {
                  return false;
               }
            } else if (!this$itemCounts.equals(other$itemCounts)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getInputId();
      Object $itemCounts = this.getItemCounts();
      result = result * 59 + ($itemCounts == null ? 43 : $itemCounts.hashCode());
      return result;
   }

   public String toString() {
      return "MaterialReducer(inputId=" + this.getInputId() + ", itemCounts=" + this.getItemCounts() + ")";
   }
}
