package com.nukkitx.protocol.common.util;

public final class LongKeys {
   public static int high(long key) {
      return (int)(key >> 32);
   }

   public static int low(long key) {
      return (int)key;
   }

   public static long key(int high, int low) {
      return (long)high << 32 | (long)low & 4294967295L;
   }

   private LongKeys() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
