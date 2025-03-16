package com.nukkitx.protocol.bedrock.data.entity;

public final class FloatEntityProperty implements EntityProperty {
   private final int index;
   private final float value;

   public FloatEntityProperty(int index, float value) {
      this.index = index;
      this.value = value;
   }

   public int getIndex() {
      return this.index;
   }

   public float getValue() {
      return this.value;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FloatEntityProperty)) {
         return false;
      } else {
         FloatEntityProperty other = (FloatEntityProperty)o;
         if (this.getIndex() != other.getIndex()) {
            return false;
         } else {
            return Float.compare(this.getValue(), other.getValue()) == 0;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getIndex();
      result = result * 59 + Float.floatToIntBits(this.getValue());
      return result;
   }

   public String toString() {
      return "FloatEntityProperty(index=" + this.getIndex() + ", value=" + this.getValue() + ")";
   }
}
