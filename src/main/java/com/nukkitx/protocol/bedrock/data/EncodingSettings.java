package com.nukkitx.protocol.bedrock.data;

public class EncodingSettings {
   public static final EncodingSettings DEFAULT = builder().maxListSize(1536).maxByteArraySize(1048576).maxNetworkNBTSize(536870912).maxItemNBTSize(102400).maxStringLength(32768).build();
   public static final EncodingSettings CLIENT = builder().maxListSize(10240).maxByteArraySize(20971520).maxNetworkNBTSize(10485760).maxItemNBTSize(5242880).maxStringLength(1048576).build();
   public static final EncodingSettings SERVER = builder().maxListSize(1024).maxByteArraySize(524288).maxNetworkNBTSize(524288).maxItemNBTSize(102400).maxStringLength(32768).build();
   private final int maxListSize;
   private final int maxByteArraySize;
   private final int maxNetworkNBTSize;
   private final int maxItemNBTSize;
   private final int maxStringLength;

   EncodingSettings(int maxListSize, int maxByteArraySize, int maxNetworkNBTSize, int maxItemNBTSize, int maxStringLength) {
      this.maxListSize = maxListSize;
      this.maxByteArraySize = maxByteArraySize;
      this.maxNetworkNBTSize = maxNetworkNBTSize;
      this.maxItemNBTSize = maxItemNBTSize;
      this.maxStringLength = maxStringLength;
   }

   public static Builder builder() {
      return new Builder();
   }

   public Builder toBuilder() {
      return (new Builder()).maxListSize(this.maxListSize).maxByteArraySize(this.maxByteArraySize).maxNetworkNBTSize(this.maxNetworkNBTSize).maxItemNBTSize(this.maxItemNBTSize).maxStringLength(this.maxStringLength);
   }

   public int maxListSize() {
      return this.maxListSize;
   }

   public int maxByteArraySize() {
      return this.maxByteArraySize;
   }

   public int maxNetworkNBTSize() {
      return this.maxNetworkNBTSize;
   }

   public int maxItemNBTSize() {
      return this.maxItemNBTSize;
   }

   public int maxStringLength() {
      return this.maxStringLength;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EncodingSettings)) {
         return false;
      } else {
         EncodingSettings other = (EncodingSettings)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.maxListSize() != other.maxListSize()) {
            return false;
         } else if (this.maxByteArraySize() != other.maxByteArraySize()) {
            return false;
         } else if (this.maxNetworkNBTSize() != other.maxNetworkNBTSize()) {
            return false;
         } else if (this.maxItemNBTSize() != other.maxItemNBTSize()) {
            return false;
         } else {
            return this.maxStringLength() == other.maxStringLength();
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EncodingSettings;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.maxListSize();
      result = result * 59 + this.maxByteArraySize();
      result = result * 59 + this.maxNetworkNBTSize();
      result = result * 59 + this.maxItemNBTSize();
      result = result * 59 + this.maxStringLength();
      return result;
   }

   public String toString() {
      return "EncodingSettings(maxListSize=" + this.maxListSize() + ", maxByteArraySize=" + this.maxByteArraySize() + ", maxNetworkNBTSize=" + this.maxNetworkNBTSize() + ", maxItemNBTSize=" + this.maxItemNBTSize() + ", maxStringLength=" + this.maxStringLength() + ")";
   }

   public static class Builder {
      private int maxListSize;
      private int maxByteArraySize;
      private int maxNetworkNBTSize;
      private int maxItemNBTSize;
      private int maxStringLength;

      Builder() {
      }

      public Builder maxListSize(int maxListSize) {
         this.maxListSize = maxListSize;
         return this;
      }

      public Builder maxByteArraySize(int maxByteArraySize) {
         this.maxByteArraySize = maxByteArraySize;
         return this;
      }

      public Builder maxNetworkNBTSize(int maxNetworkNBTSize) {
         this.maxNetworkNBTSize = maxNetworkNBTSize;
         return this;
      }

      public Builder maxItemNBTSize(int maxItemNBTSize) {
         this.maxItemNBTSize = maxItemNBTSize;
         return this;
      }

      public Builder maxStringLength(int maxStringLength) {
         this.maxStringLength = maxStringLength;
         return this;
      }

      public EncodingSettings build() {
         return new EncodingSettings(this.maxListSize, this.maxByteArraySize, this.maxNetworkNBTSize, this.maxItemNBTSize, this.maxStringLength);
      }

      public String toString() {
         return "EncodingSettings.Builder(maxListSize=" + this.maxListSize + ", maxByteArraySize=" + this.maxByteArraySize + ", maxNetworkNBTSize=" + this.maxNetworkNBTSize + ", maxItemNBTSize=" + this.maxItemNBTSize + ", maxStringLength=" + this.maxStringLength + ")";
      }
   }
}
