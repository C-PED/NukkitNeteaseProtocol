package com.nukkitx.protocol.bedrock.data.inventory.descriptor;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;

public final class DeferredDescriptor implements ItemDescriptor {
   private final String fullName;
   private final int auxValue;

   public ItemDescriptorType getType() {
      return ItemDescriptorType.DEFERRED;
   }

   public ItemData.Builder toItem() {
      throw new UnsupportedOperationException();
   }

   public DeferredDescriptor(String fullName, int auxValue) {
      this.fullName = fullName;
      this.auxValue = auxValue;
   }

   public String getFullName() {
      return this.fullName;
   }

   public int getAuxValue() {
      return this.auxValue;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DeferredDescriptor)) {
         return false;
      } else {
         DeferredDescriptor other = (DeferredDescriptor)o;
         if (this.getAuxValue() != other.getAuxValue()) {
            return false;
         } else {
            Object this$fullName = this.getFullName();
            Object other$fullName = other.getFullName();
            if (this$fullName == null) {
               if (other$fullName != null) {
                  return false;
               }
            } else if (!this$fullName.equals(other$fullName)) {
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
      Object $fullName = this.getFullName();
      result = result * 59 + ($fullName == null ? 43 : $fullName.hashCode());
      return result;
   }

   public String toString() {
      return "DeferredDescriptor(fullName=" + this.getFullName() + ", auxValue=" + this.getAuxValue() + ")";
   }
}
