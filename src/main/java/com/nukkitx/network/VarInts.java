package com.nukkitx.network;

import io.netty.buffer.ByteBuf;

public final class VarInts {
   public static void writeInt(ByteBuf buffer, int value) {
      encode(buffer, (long)(value << 1 ^ value >> 31) & 4294967295L);
   }

   public static int readInt(ByteBuf buffer) {
      int n = (int)decode(buffer);
      return n >>> 1 ^ -(n & 1);
   }

   public static void writeUnsignedInt(ByteBuf buffer, int value) {
      encode(buffer, (long)value & 4294967295L);
   }

   public static int readUnsignedInt(ByteBuf buffer) {
      return (int)decode(buffer);
   }

   public static void writeLong(ByteBuf buffer, long value) {
      encode(buffer, value << 1 ^ value >> 63);
   }

   public static long readLong(ByteBuf buffer) {
      long n = decode(buffer);
      return n >>> 1 ^ -(n & 1L);
   }

   public static void writeUnsignedLong(ByteBuf buffer, long value) {
      encode(buffer, value);
   }

   public static long readUnsignedLong(ByteBuf buffer) {
      return decode(buffer);
   }

   private static long decode(ByteBuf buffer) {
      long result = 0L;

      for(int shift = 0; shift < 64; shift += 7) {
         byte b = buffer.readByte();
         result |= ((long)b & 127L) << shift;
         if ((b & 128) == 0) {
            return result;
         }
      }

      throw new ArithmeticException("Varint was too large");
   }

   private static void encode(ByteBuf buffer, long value) {
      while((value & -128L) != 0L) {
         buffer.writeByte((byte)((int)value & 127 | 128));
         value >>>= 7;
      }

      buffer.writeByte((int)value);
   }

   private VarInts() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
