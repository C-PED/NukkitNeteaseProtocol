package com.nukkitx.protocol.common.util.stream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.ByteBufUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LittleEndianByteBufOutputStream extends ByteBufOutputStream {
   private final ByteBuf buffer;

   public LittleEndianByteBufOutputStream(ByteBuf buffer) {
      super(buffer);
      this.buffer = buffer;
   }

   public void writeChar(int v) throws IOException {
      this.buffer.writeChar(Character.reverseBytes((char)v));
   }

   public void writeDouble(double v) throws IOException {
      this.buffer.writeDoubleLE(v);
   }

   public void writeFloat(float v) throws IOException {
      this.buffer.writeFloatLE(v);
   }

   public void writeShort(int val) throws IOException {
      this.buffer.writeShortLE(val);
   }

   public void writeLong(long val) throws IOException {
      this.buffer.writeLongLE(val);
   }

   public void writeInt(int val) throws IOException {
      this.buffer.writeIntLE(val);
   }

   public void writeUTF(String string) throws IOException {
      int length = ByteBufUtil.utf8Bytes(string);
      this.writeShort(length);
      this.buffer.writeCharSequence(string, StandardCharsets.UTF_8);
   }
}
