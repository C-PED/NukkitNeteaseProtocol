package com.nukkitx.network.util.bitset;

public final class BitUtil {
   public static boolean getBit(long bitset, int index) {
      return (bitset >>> index & 1L) != 0L;
   }

   public static byte setBit(byte bitset, int index, boolean value) {
      return (byte)((int)setBit((long)bitset, index, value));
   }

   public static short setBit(short bitset, int index, boolean value) {
      return (short)((int)setBit((long)bitset, index, value));
   }

   public static int setBit(int bitset, int index, boolean value) {
      return (int)setBit((long)bitset, index, value);
   }

   public static long setBit(long bitset, int index, boolean value) {
      return value ? bitset | (long)(1 << index) : bitset & (long)(~(1 << index));
   }

   public static byte flipBit(byte bitset, int index) {
      return (byte)((int)flipBit((long)bitset, index));
   }

   public static short flipBit(short bitset, int index) {
      return (short)((int)flipBit((long)bitset, index));
   }

   public static int flipBit(int bitset, int index) {
      return (int)flipBit((long)bitset, index);
   }

   public static long flipBit(long bitset, int index) {
      return bitset ^ (long)(1 << index);
   }

   private BitUtil() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
