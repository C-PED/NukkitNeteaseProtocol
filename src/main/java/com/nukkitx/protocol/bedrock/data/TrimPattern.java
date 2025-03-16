package com.nukkitx.protocol.bedrock.data;

public class TrimPattern {
   private final String itemName;
   private final String patternId;

   public TrimPattern(String itemName, String patternId) {
      this.itemName = itemName;
      this.patternId = patternId;
   }

   public String getItemName() {
      return this.itemName;
   }

   public String getPatternId() {
      return this.patternId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TrimPattern)) {
         return false;
      } else {
         TrimPattern other = (TrimPattern)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$itemName = this.getItemName();
            Object other$itemName = other.getItemName();
            if (this$itemName == null) {
               if (other$itemName != null) {
                  return false;
               }
            } else if (!this$itemName.equals(other$itemName)) {
               return false;
            }

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
   }

   protected boolean canEqual(Object other) {
      return other instanceof TrimPattern;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $itemName = this.getItemName();
      result = result * 59 + ($itemName == null ? 43 : $itemName.hashCode());
      Object $patternId = this.getPatternId();
      result = result * 59 + ($patternId == null ? 43 : $patternId.hashCode());
      return result;
   }

   public String toString() {
      return "TrimPattern(itemName=" + this.getItemName() + ", patternId=" + this.getPatternId() + ")";
   }
}
