package com.nukkitx.network.util.bitset;

public interface BitSet {
   void flip(int var1);

   void set(int var1, boolean var2);

   boolean get(int var1);

   long[] getLongs();

   int[] getInts();

   short[] getShorts();

   byte[] getBytes();

   void clear();
}
