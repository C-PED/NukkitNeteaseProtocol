package com.nukkitx.protocol.common.util;

import io.netty.buffer.ByteBuf;

public final class VarInts {
   public static void writeInt(ByteBuf buffer, int value) {
      encode(buffer, (long)(value << 1 ^ value >> 31) & 4294967295L);
   }

   public static int readInt(ByteBuf buffer) {
      int n = (int)decode(buffer, 32);
      return n >>> 1 ^ -(n & 1);
   }

   public static void writeUnsignedInt(ByteBuf buffer, int value) {
      encode(buffer, (long)value & 4294967295L);
   }

   public static int readUnsignedInt(ByteBuf buffer) {
      return (int)decode(buffer, 32);
   }

   public static void writeLong(ByteBuf buffer, long value) {
      encode(buffer, value << 1 ^ value >> 63);
   }

   public static long readLong(ByteBuf buffer) {
      long n = decode(buffer, 64);
      return n >>> 1 ^ -(n & 1L);
   }

   public static void writeUnsignedLong(ByteBuf buffer, long value) {
      encode(buffer, value);
   }

   public static long readUnsignedLong(ByteBuf buffer) {
      return decode(buffer, 64);
   }

   private static void encode(ByteBuf buf, long value) {
      if ((value & -128L) == 0L) {
         buf.writeByte((byte)((int)value));
      } else if ((value & -16384L) == 0L) {
         int w = (int)((value & 127L | 128L) << 8 | value >>> 7);
         buf.writeShort(w);
      } else {
         encodeFull(buf, value);
      }

   }

   private static void encodeFull(ByteBuf buf, long value) {
      if ((value & -128L) == 0L) {
         buf.writeByte((byte)((int)value));
      } else if ((value & -16384L) == 0L) {
         int w = (int)((value & 127L | 128L) << 8 | value >>> 7);
         buf.writeShort(w);
      } else if ((value & -2097152L) == 0L) {
         int w = (int)((value & 127L | 128L) << 16 | (value >>> 7 & 127L | 128L) << 8 | value >>> 14);
         buf.writeMedium(w);
      } else if ((value & -268435456L) == 0L) {
         int w = (int)((value & 127L | 128L) << 24 | (value >>> 7 & 127L | 128L) << 16 | (value >>> 14 & 127L | 128L) << 8 | value >>> 21);
         buf.writeInt(w);
      } else if ((value & -34359738368L) == 0L) {
         int w = (int)((value & 127L | 128L) << 24 | (value >>> 7 & 127L | 128L) << 16 | (value >>> 14 & 127L | 128L) << 8 | value >>> 21 & 127L | 128L);
         buf.writeInt(w);
         buf.writeByte((int)(value >>> 28));
      } else if ((value & -4398046511104L) == 0L) {
         int w = (int)((value & 127L | 128L) << 24 | (value >>> 7 & 127L | 128L) << 16 | (value >>> 14 & 127L | 128L) << 8 | value >>> 21 & 127L | 128L);
         int w2 = (int)((value >>> 28 & 127L | 128L) << 8 | value >>> 35);
         buf.writeInt(w);
         buf.writeShort(w2);
      } else if ((value & -562949953421312L) == 0L) {
         int w = (int)((value & 127L | 128L) << 24 | (value >>> 7 & 127L | 128L) << 16 | (value >>> 14 & 127L | 128L) << 8 | value >>> 21 & 127L | 128L);
         int w2 = (int)((value >>> 28 & 127L | 128L) << 16 | (value >>> 35 & 127L | 128L) << 8 | value >>> 42);
         buf.writeInt(w);
         buf.writeMedium(w2);
      } else if ((value & -72057594037927936L) == 0L) {
         long w = (value & 127L | 128L) << 56 | (value >>> 7 & 127L | 128L) << 48 | (value >>> 14 & 127L | 128L) << 40 | (value >>> 21 & 127L | 128L) << 32 | (value >>> 28 & 127L | 128L) << 24 | (value >>> 35 & 127L | 128L) << 16 | (value >>> 42 & 127L | 128L) << 8 | value >>> 49;
         buf.writeLong(w);
      } else if ((value & Long.MIN_VALUE) == 0L) {
         long w = (value & 127L | 128L) << 56 | (value >>> 7 & 127L | 128L) << 48 | (value >>> 14 & 127L | 128L) << 40 | (value >>> 21 & 127L | 128L) << 32 | (value >>> 28 & 127L | 128L) << 24 | (value >>> 35 & 127L | 128L) << 16 | (value >>> 42 & 127L | 128L) << 8 | value >>> 49 & 127L | 128L;
         buf.writeLong(w);
         buf.writeByte((byte)((int)(value >>> 56)));
      } else {
         long w = (value & 127L | 128L) << 56 | (value >>> 7 & 127L | 128L) << 48 | (value >>> 14 & 127L | 128L) << 40 | (value >>> 21 & 127L | 128L) << 32 | (value >>> 28 & 127L | 128L) << 24 | (value >>> 35 & 127L | 128L) << 16 | (value >>> 42 & 127L | 128L) << 8 | value >>> 49 & 127L | 128L;
         long w2 = (value >>> 56 & 127L | 128L) << 8 | value >>> 63;
         buf.writeLong(w);
         buf.writeShort((int)w2);
      }

   }

   private static long decode(ByteBuf buf, int maxBits) {
      long result = 0L;

      for(int shift = 0; shift < maxBits; shift += 7) {
         byte b = buf.readByte();
         result |= ((long)b & 127L) << shift;
         if ((b & 128) == 0) {
            return result;
         }
      }

      throw new ArithmeticException("VarInt was too large");
   }

   private VarInts() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
