package com.nukkitx.natives.zlib;

import java.nio.ByteBuffer;

public class Java11Deflater implements Deflater {
   private final java.util.zip.Deflater deflater;

   Java11Deflater(int level, boolean nowrap) {
      this.deflater = new java.util.zip.Deflater(level, nowrap);
   }

   public void setLevel(int level) {
      this.deflater.setLevel(level);
   }

   public void setInput(ByteBuffer input) {
      this.deflater.setInput(input);
   }

   public int deflate(ByteBuffer output) {
      this.deflater.finish();
      return this.deflater.deflate(output);
   }

   public int getAdler() {
      return this.deflater.getAdler();
   }

   public void reset() {
      this.deflater.reset();
   }

   public boolean finished() {
      return this.deflater.finished();
   }

   public void free() {
      this.deflater.end();
   }
}
