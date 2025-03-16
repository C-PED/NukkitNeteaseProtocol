package com.nukkitx.network.util.bitset;

public class ByteBitSet implements BitSet {
   private byte bitset;

   public ByteBitSet() {
      this.bitset = 0;
   }

   public ByteBitSet(byte bitset) {
      this.bitset = bitset;
   }

   public ByteBitSet(int bitset) {
      this.bitset = (byte)bitset;
   }

   public ByteBitSet(ByteBitSet bitSet) {
      this.bitset = bitSet.bitset;
   }

   private static void checkIndex(int index) {
      if (index < 0 || index >= 8) {
         throw new IndexOutOfBoundsException("Expected value 0-7");
      }
   }

   public void flip(int index) {
      this.bitset = BitUtil.flipBit(this.bitset, index);
   }

   public void set(int index, boolean value) {
      this.bitset = BitUtil.setBit(this.bitset, index, value);
   }

   public boolean get(int index) {
      return BitUtil.getBit((long)this.bitset, index);
   }

   public long[] getLongs() {
      return new long[]{(long)this.bitset};
   }

   public int[] getInts() {
      return new int[]{this.bitset};
   }

   public short[] getShorts() {
      return new short[]{(short)this.bitset};
   }

   public byte[] getBytes() {
      return new byte[]{this.bitset};
   }

   public void clear() {
      this.bitset = 0;
   }

   public byte get() {
      return this.bitset;
   }

   public void set(byte bitset) {
      this.bitset = bitset;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ByteBitSet)) {
         return false;
      } else {
         ByteBitSet that = (ByteBitSet)o;
         return this.bitset == that.get();
      }
   }
}
