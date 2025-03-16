package com.nukkitx.network.raknet;

import com.nukkitx.network.raknet.util.IntRange;
import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import java.util.Queue;

public final class RakNetUtils {
   public static int writeIntRanges(ByteBuf buffer, Queue<IntRange> ackQueue, int mtu) {
      int lengthIndex = buffer.writerIndex();
      buffer.writeZero(2);
      mtu -= 2;

      int count;
      IntRange ackRange;
      for(count = 0; (ackRange = (IntRange)ackQueue.peek()) != null; ++count) {
         if (ackRange.start == ackRange.end) {
            if (mtu < 4) {
               break;
            }

            mtu -= 4;
            buffer.writeBoolean(true);
            buffer.writeMediumLE(ackRange.start);
         } else {
            if (mtu < 7) {
               break;
            }

            mtu -= 7;
            buffer.writeBoolean(false);
            buffer.writeMediumLE(ackRange.start);
            buffer.writeMediumLE(ackRange.end);
         }

         ackQueue.remove();
      }

      int finalIndex = buffer.writerIndex();
      buffer.writerIndex(lengthIndex);
      buffer.writeShort(count);
      buffer.writerIndex(finalIndex);
      return count;
   }

   public static boolean verifyUnconnectedMagic(ByteBuf buffer) {
      byte[] readMagic = new byte[RakNetConstants.RAKNET_UNCONNECTED_MAGIC.length];
      buffer.readBytes(readMagic);
      return Arrays.equals(readMagic, RakNetConstants.RAKNET_UNCONNECTED_MAGIC);
   }

   public static void writeUnconnectedMagic(ByteBuf buffer) {
      buffer.writeBytes(RakNetConstants.RAKNET_UNCONNECTED_MAGIC);
   }

   public static int clamp(int value, int low, int high) {
      return value < low ? low : (value > high ? high : value);
   }

   public static int powerOfTwoCeiling(int value) {
      --value;
      value |= value >> 1;
      value |= value >> 2;
      value |= value >> 4;
      value |= value >> 8;
      value |= value >> 16;
      ++value;
      return value;
   }

   private RakNetUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
