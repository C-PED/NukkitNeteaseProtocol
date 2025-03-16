package com.nukkitx.protocol.bedrock.data.skin;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Objects;

public class ImageData {
   public static final ImageData EMPTY = new ImageData(0, 0, new byte[0]);
   private static final int PIXEL_SIZE = 4;
   public static final int SINGLE_SKIN_SIZE = 8192;
   public static final int DOUBLE_SKIN_SIZE = 16384;
   public static final int SKIN_128_64_SIZE = 32768;
   public static final int SKIN_128_128_SIZE = 65536;
   public static final int SKIN_PERSONA_SIZE = 262144;
   private final int width;
   private final int height;
   private final byte[] image;

   public static ImageData of(int width, int height, byte[] image) {
      Objects.requireNonNull(image, "image");
      return new ImageData(width, height, image);
   }

   public static ImageData of(byte[] image) {
      Objects.requireNonNull(image, "image");
      switch (image.length) {
         case 0:
            return EMPTY;
         case 8192:
            return new ImageData(64, 32, image);
         case 16384:
            return new ImageData(64, 64, image);
         case 32768:
            return new ImageData(128, 64, image);
         case 65536:
            return new ImageData(128, 128, image);
         case 262144:
            return new ImageData(256, 256, image);
         default:
            throw new IllegalArgumentException("Invalid skin length");
      }
   }

   public void checkLegacySkinSize() {
      switch (this.image.length) {
         case 8192:
         case 16384:
         case 32768:
         case 65536:
            return;
         default:
            throw new IllegalArgumentException("Invalid legacy skin");
      }
   }

   public void checkPersonaSkinSize() {
      switch (this.image.length) {
         case 262144:
            return;
         default:
            throw new IllegalArgumentException("Invalid persona skin");
      }
   }

   public void checkLegacyCapeSize() {
      if (this.image.length != 0 && this.image.length != 8192) {
         throw new IllegalArgumentException("Invalid legacy cape");
      }
   }

   public static ImageData from(BufferedImage image) {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

      for(int y = 0; y < image.getHeight(); ++y) {
         for(int x = 0; x < image.getWidth(); ++x) {
            Color color = new Color(image.getRGB(x, y), true);
            outputStream.write(color.getRed());
            outputStream.write(color.getGreen());
            outputStream.write(color.getBlue());
            outputStream.write(color.getAlpha());
         }
      }

      image.flush();
      return new ImageData(image.getWidth(), image.getHeight(), outputStream.toByteArray());
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   public byte[] getImage() {
      return this.image;
   }

   public String toString() {
      return "ImageData(width=" + this.getWidth() + ", height=" + this.getHeight() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ImageData)) {
         return false;
      } else {
         ImageData other = (ImageData)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.width != other.width) {
            return false;
         } else if (this.height != other.height) {
            return false;
         } else {
            return Arrays.equals(this.image, other.image);
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ImageData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.width;
      result = result * 59 + this.height;
      result = result * 59 + Arrays.hashCode(this.image);
      return result;
   }

   ImageData(int width, int height, byte[] image) {
      this.width = width;
      this.height = height;
      this.image = image;
   }
}
