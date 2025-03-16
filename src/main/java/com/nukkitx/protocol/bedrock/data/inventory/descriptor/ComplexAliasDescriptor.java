package com.nukkitx.protocol.bedrock.data.inventory.descriptor;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;

public final class ComplexAliasDescriptor implements ItemDescriptor {
   private final String name;

   public ItemDescriptorType getType() {
      return ItemDescriptorType.COMPLEX_ALIAS;
   }

   public ItemData.Builder toItem() {
      throw new UnsupportedOperationException();
   }

   public ComplexAliasDescriptor(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ComplexAliasDescriptor)) {
         return false;
      } else {
         ComplexAliasDescriptor other = (ComplexAliasDescriptor)o;
         Object this$name = this.getName();
         Object other$name = other.getName();
         if (this$name == null) {
            if (other$name != null) {
               return false;
            }
         } else if (!this$name.equals(other$name)) {
            return false;
         }

         return true;
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      return result;
   }

   public String toString() {
      return "ComplexAliasDescriptor(name=" + this.getName() + ")";
   }
}
