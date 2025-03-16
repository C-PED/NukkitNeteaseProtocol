package com.nukkitx.protocol.bedrock.data.inventory.descriptor;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;

public final class MolangDescriptor implements ItemDescriptor {
   private final String tagExpression;
   private final int molangVersion;

   public ItemDescriptorType getType() {
      return ItemDescriptorType.MOLANG;
   }

   public ItemData.Builder toItem() {
      throw new UnsupportedOperationException();
   }

   public MolangDescriptor(String tagExpression, int molangVersion) {
      this.tagExpression = tagExpression;
      this.molangVersion = molangVersion;
   }

   public String getTagExpression() {
      return this.tagExpression;
   }

   public int getMolangVersion() {
      return this.molangVersion;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MolangDescriptor)) {
         return false;
      } else {
         MolangDescriptor other = (MolangDescriptor)o;
         if (this.getMolangVersion() != other.getMolangVersion()) {
            return false;
         } else {
            Object this$tagExpression = this.getTagExpression();
            Object other$tagExpression = other.getTagExpression();
            if (this$tagExpression == null) {
               if (other$tagExpression != null) {
                  return false;
               }
            } else if (!this$tagExpression.equals(other$tagExpression)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getMolangVersion();
      Object $tagExpression = this.getTagExpression();
      result = result * 59 + ($tagExpression == null ? 43 : $tagExpression.hashCode());
      return result;
   }

   public String toString() {
      return "MolangDescriptor(tagExpression=" + this.getTagExpression() + ", molangVersion=" + this.getMolangVersion() + ")";
   }
}
