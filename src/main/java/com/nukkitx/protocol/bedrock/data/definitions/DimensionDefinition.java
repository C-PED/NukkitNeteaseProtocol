package com.nukkitx.protocol.bedrock.data.definitions;

public final class DimensionDefinition {
   private final String id;
   private final int maximumHeight;
   private final int minimumHeight;
   private final int generatorType;

   public DimensionDefinition(String id, int maximumHeight, int minimumHeight, int generatorType) {
      this.id = id;
      this.maximumHeight = maximumHeight;
      this.minimumHeight = minimumHeight;
      this.generatorType = generatorType;
   }

   public String getId() {
      return this.id;
   }

   public int getMaximumHeight() {
      return this.maximumHeight;
   }

   public int getMinimumHeight() {
      return this.minimumHeight;
   }

   public int getGeneratorType() {
      return this.generatorType;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DimensionDefinition)) {
         return false;
      } else {
         DimensionDefinition other = (DimensionDefinition)o;
         if (this.getMaximumHeight() != other.getMaximumHeight()) {
            return false;
         } else if (this.getMinimumHeight() != other.getMinimumHeight()) {
            return false;
         } else if (this.getGeneratorType() != other.getGeneratorType()) {
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

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMaximumHeight();
      result = result * 59 + this.getMinimumHeight();
      result = result * 59 + this.getGeneratorType();
      Object $id = this.getId();
      result = result * 59 + ($id == null ? 43 : $id.hashCode());
      return result;
   }

   public String toString() {
      return "DimensionDefinition(id=" + this.getId() + ", maximumHeight=" + this.getMaximumHeight() + ", minimumHeight=" + this.getMinimumHeight() + ", generatorType=" + this.getGeneratorType() + ")";
   }
}
