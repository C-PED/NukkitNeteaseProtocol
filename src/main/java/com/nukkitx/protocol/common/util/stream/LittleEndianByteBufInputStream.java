package com.nukkitx.protocol.common.util.stream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LittleEndianByteBufInputStream extends ByteBufInputStream {
   private final ByteBuf buffer;

   public LittleEndianByteBufInputStream(ByteBuf buffer) {
      super(buffer);
      this.buffer = buffer;
   }

   public char readChar() throws IOException {
      return Character.reverseBytes(this.buffer.readChar());
   }

   public double readDouble() throws IOException {
      return this.buffer.readDoubleLE();
   }

   public float readFloat() throws IOException {
      return this.buffer.readFloatLE();
   }

   public short readShort() throws IOException {
      return this.buffer.readShortLE();
   }

   public int readUnsignedShort() throws IOException {
      return this.buffer.readUnsignedShortLE();
   }

   public long readLong() throws IOException {
      return this.buffer.readLongLE();
   }

   public int readInt() throws IOException {
      return this.buffer.readIntLE();
   }

   public String readUTF() throws IOException {
      int length = this.readUnsignedShort();
      return (String)this.buffer.readCharSequence(length, StandardCharsets.UTF_8);
   }
}
