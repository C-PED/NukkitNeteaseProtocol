package com.nukkitx.protocol.bedrock.data.entity;

public final class IntEntityProperty implements EntityProperty {
   private final int index;
   private final int value;

   public IntEntityProperty(int index, int value) {
      this.index = index;
      this.value = value;
   }

   public int getIndex() {
      return this.index;
   }

   public int getValue() {
      return this.value;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof IntEntityProperty)) {
         return false;
      } else {
         IntEntityProperty other = (IntEntityProperty)o;
         if (this.getIndex() != other.getIndex()) {
            return false;
         } else {
            return this.getValue() == other.getValue();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getIndex();
      result = result * 59 + this.getValue();
      return result;
   }

   public String toString() {
      return "IntEntityProperty(index=" + this.getIndex() + ", value=" + this.getValue() + ")";
   }
}
