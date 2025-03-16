package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import java.util.Arrays;

public final class CraftResultsDeprecatedAction implements ItemStackRequestAction {
   private final ItemData[] resultItems;
   private final int timesCrafted;

   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.CRAFT_RESULTS_DEPRECATED;
   }

   public CraftResultsDeprecatedAction(ItemData[] resultItems, int timesCrafted) {
      this.resultItems = resultItems;
      this.timesCrafted = timesCrafted;
   }

   public ItemData[] getResultItems() {
      return this.resultItems;
   }

   public int getTimesCrafted() {
      return this.timesCrafted;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CraftResultsDeprecatedAction)) {
         return false;
      } else {
         CraftResultsDeprecatedAction other = (CraftResultsDeprecatedAction)o;
         if (this.getTimesCrafted() != other.getTimesCrafted()) {
            return false;
         } else {
            return Arrays.deepEquals(this.getResultItems(), other.getResultItems());
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getTimesCrafted();
      result = result * 59 + Arrays.deepHashCode(this.getResultItems());
      return result;
   }

   public String toString() {
      return "CraftResultsDeprecatedAction(resultItems=" + Arrays.deepToString(this.getResultItems()) + ", timesCrafted=" + this.getTimesCrafted() + ")";
   }
}
