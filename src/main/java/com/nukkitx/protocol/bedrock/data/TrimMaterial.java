package com.nukkitx.protocol.bedrock.data;

public class TrimMaterial {
   private final String materialId;
   private final String color;
   private final String itemName;

   public TrimMaterial(String materialId, String color, String itemName) {
      this.materialId = materialId;
      this.color = color;
      this.itemName = itemName;
   }

   public String getMaterialId() {
      return this.materialId;
   }

   public String getColor() {
      return this.color;
   }

   public String getItemName() {
      return this.itemName;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TrimMaterial)) {
         return false;
      } else {
         TrimMaterial other = (TrimMaterial)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$materialId = this.getMaterialId();
            Object other$materialId = other.getMaterialId();
            if (this$materialId == null) {
               if (other$materialId != null) {
                  return false;
               }
            } else if (!this$materialId.equals(other$materialId)) {
               return false;
            }

            Object this$color = this.getColor();
            Object other$color = other.getColor();
            if (this$color == null) {
               if (other$color != null) {
                  return false;
               }
            } else if (!this$color.equals(other$color)) {
               return false;
            }

            Object this$itemName = this.getItemName();
            Object other$itemName = other.getItemName();
            if (this$itemName == null) {
               if (other$itemName != null) {
                  return false;
               }
            } else if (!this$itemName.equals(other$itemName)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof TrimMaterial;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $materialId = this.getMaterialId();
      result = result * 59 + ($materialId == null ? 43 : $materialId.hashCode());
      Object $color = this.getColor();
      result = result * 59 + ($color == null ? 43 : $color.hashCode());
      Object $itemName = this.getItemName();
      result = result * 59 + ($itemName == null ? 43 : $itemName.hashCode());
      return result;
   }

   public String toString() {
      return "TrimMaterial(materialId=" + this.getMaterialId() + ", color=" + this.getColor() + ", itemName=" + this.getItemName() + ")";
   }
}
