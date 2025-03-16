package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

public final class CraftLoomAction implements ItemStackRequestAction {
   private final String patternId;

   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.CRAFT_LOOM;
   }

   public CraftLoomAction(String patternId) {
      this.patternId = patternId;
   }

   public String getPatternId() {
      return this.patternId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CraftLoomAction)) {
         return false;
      } else {
         CraftLoomAction other = (CraftLoomAction)o;
         Object this$patternId = this.getPatternId();
         Object other$patternId = other.getPatternId();
         if (this$patternId == null) {
            if (other$patternId != null) {
               return false;
            }
         } else if (!this$patternId.equals(other$patternId)) {
            return false;
         }

         return true;
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $patternId = this.getPatternId();
      result = result * 59 + ($patternId == null ? 43 : $patternId.hashCode());
      return result;
   }

   public String toString() {
      return "CraftLoomAction(patternId=" + this.getPatternId() + ")";
   }
}
