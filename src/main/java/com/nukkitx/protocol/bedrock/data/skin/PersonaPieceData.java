package com.nukkitx.protocol.bedrock.data.skin;

public final class PersonaPieceData {
   private final String id;
   private final String type;
   private final String packId;
   private final boolean isDefault;
   private final String productId;

   public PersonaPieceData(String id, String type, String packId, boolean isDefault, String productId) {
      this.id = id;
      this.type = type;
      this.packId = packId;
      this.isDefault = isDefault;
      this.productId = productId;
   }

   public String getId() {
      return this.id;
   }

   public String getType() {
      return this.type;
   }

   public String getPackId() {
      return this.packId;
   }

   public boolean isDefault() {
      return this.isDefault;
   }

   public String getProductId() {
      return this.productId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PersonaPieceData)) {
         return false;
      } else {
         PersonaPieceData other = (PersonaPieceData)o;
         if (this.isDefault() != other.isDefault()) {
            return false;
         } else {
            Object this$id = this.getId();
            Object other$id = other.getId();
            if (this$id == null) {
               if (other$id != null) {
                  return false;
               }
            } else if (!this$id.equals(other$id)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$packId = this.getPackId();
            Object other$packId = other.getPackId();
            if (this$packId == null) {
               if (other$packId != null) {
                  return false;
               }
            } else if (!this$packId.equals(other$packId)) {
               return false;
            }

            Object this$productId = this.getProductId();
            Object other$productId = other.getProductId();
            if (this$productId == null) {
               if (other$productId != null) {
                  return false;
               }
            } else if (!this$productId.equals(other$productId)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.isDefault() ? 79 : 97);
      Object $id = this.getId();
      result = result * 59 + ($id == null ? 43 : $id.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $packId = this.getPackId();
      result = result * 59 + ($packId == null ? 43 : $packId.hashCode());
      Object $productId = this.getProductId();
      result = result * 59 + ($productId == null ? 43 : $productId.hashCode());
      return result;
   }

   public String toString() {
      return "PersonaPieceData(id=" + this.getId() + ", type=" + this.getType() + ", packId=" + this.getPackId() + ", isDefault=" + this.isDefault() + ", productId=" + this.getProductId() + ")";
   }
}
