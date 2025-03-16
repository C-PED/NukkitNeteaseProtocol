package com.nukkitx.protocol.bedrock.data;

public final class MapDecoration {
   private final int image;
   private final int rotation;
   private final int xOffset;
   private final int yOffset;
   private final String label;
   private final int color;

   public MapDecoration(int image, int rotation, int xOffset, int yOffset, String label, int color) {
      this.image = image;
      this.rotation = rotation;
      this.xOffset = xOffset;
      this.yOffset = yOffset;
      this.label = label;
      this.color = color;
   }

   public int getImage() {
      return this.image;
   }

   public int getRotation() {
      return this.rotation;
   }

   public int getXOffset() {
      return this.xOffset;
   }

   public int getYOffset() {
      return this.yOffset;
   }

   public String getLabel() {
      return this.label;
   }

   public int getColor() {
      return this.color;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MapDecoration)) {
         return false;
      } else {
         MapDecoration other = (MapDecoration)o;
         if (this.getImage() != other.getImage()) {
            return false;
         } else if (this.getRotation() != other.getRotation()) {
            return false;
         } else if (this.getXOffset() != other.getXOffset()) {
            return false;
         } else if (this.getYOffset() != other.getYOffset()) {
            return false;
         } else if (this.getColor() != other.getColor()) {
            return false;
         } else {
            Object this$label = this.getLabel();
            Object other$label = other.getLabel();
            if (this$label == null) {
               if (other$label != null) {
                  return false;
               }
            } else if (!this$label.equals(other$label)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getImage();
      result = result * 59 + this.getRotation();
      result = result * 59 + this.getXOffset();
      result = result * 59 + this.getYOffset();
      result = result * 59 + this.getColor();
      Object $label = this.getLabel();
      result = result * 59 + ($label == null ? 43 : $label.hashCode());
      return result;
   }

   public String toString() {
      return "MapDecoration(image=" + this.getImage() + ", rotation=" + this.getRotation() + ", xOffset=" + this.getXOffset() + ", yOffset=" + this.getYOffset() + ", label=" + this.getLabel() + ", color=" + this.getColor() + ")";
   }
}
