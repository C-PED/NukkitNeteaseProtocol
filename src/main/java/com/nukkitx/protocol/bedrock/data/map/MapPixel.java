package com.nukkitx.protocol.bedrock.data.map;

public final class MapPixel {
   private final int pixel;
   private final int index;

   public MapPixel(int pixel, int index) {
      this.pixel = pixel;
      this.index = index;
   }

   public int getPixel() {
      return this.pixel;
   }

   public int getIndex() {
      return this.index;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MapPixel)) {
         return false;
      } else {
         MapPixel other = (MapPixel)o;
         if (this.getPixel() != other.getPixel()) {
            return false;
         } else {
            return this.getIndex() == other.getIndex();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getPixel();
      result = result * 59 + this.getIndex();
      return result;
   }

   public String toString() {
      return "MapPixel(pixel=" + this.getPixel() + ", index=" + this.getIndex() + ")";
   }
}
