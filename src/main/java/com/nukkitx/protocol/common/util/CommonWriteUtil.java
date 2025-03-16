package com.nukkitx.protocol.common.util;

import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class CommonWriteUtil {
   public static void writeString(ByteBuf buffer, String string) {
      com.nukkitx.network.util.Preconditions.checkNotNull(buffer, "buffer");
      com.nukkitx.network.util.Preconditions.checkNotNull(string, "string");
      writeByteArray(buffer, string.getBytes(StandardCharsets.UTF_8));
   }

   public static void writeByteArray(ByteBuf buffer, byte[] bytes) {
      com.nukkitx.network.util.Preconditions.checkNotNull(buffer, "buffer");
      com.nukkitx.network.util.Preconditions.checkNotNull(bytes, "bytes");
      com.nukkitx.network.VarInts.writeUnsignedInt(buffer, bytes.length);
      buffer.writeBytes(bytes);
   }

   public static String readString(ByteBuf buffer) {
      com.nukkitx.network.util.Preconditions.checkNotNull(buffer, "buffer");
      return new String(readByteArray(buffer), StandardCharsets.UTF_8);
   }

   public static byte[] readByteArray(ByteBuf buffer) {
      com.nukkitx.network.util.Preconditions.checkNotNull(buffer, "buffer");
      int length = com.nukkitx.network.VarInts.readUnsignedInt(buffer);
      com.nukkitx.network.util.Preconditions.checkArgument(buffer.isReadable(length), "Tried to read %s bytes but only has %s readable", length, buffer.readableBytes());
      byte[] bytes = new byte[length];
      buffer.readBytes(bytes);
      return bytes;
   }

   public static void writeUuid(ByteBuf buffer, UUID uuid) {
      com.nukkitx.network.util.Preconditions.checkNotNull(buffer, "buffer");
      com.nukkitx.network.util.Preconditions.checkNotNull(uuid, "uuid");
      buffer.writeLongLE(uuid.getMostSignificantBits());
      buffer.writeLongLE(uuid.getLeastSignificantBits());
   }

   public static UUID readUuid(ByteBuf buffer) {
      com.nukkitx.network.util.Preconditions.checkNotNull(buffer, "buffer");
      return new UUID(buffer.readLongLE(), buffer.readLongLE());
   }
}
