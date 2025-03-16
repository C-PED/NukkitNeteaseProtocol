package com.nukkitx.natives.zlib;

import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;

public class Java11Inflater implements Inflater {
   private final java.util.zip.Inflater inflater;

   Java11Inflater(boolean nowrap) {
      this.inflater = new java.util.zip.Inflater(nowrap);
   }

   public void setInput(ByteBuffer input) {
      this.inflater.setInput(input);
   }

   public int inflate(ByteBuffer output) throws DataFormatException {
      return this.inflater.inflate(output);
   }

   public int getAdler() {
      return this.inflater.getAdler();
   }

   public boolean finished() {
      return this.inflater.finished();
   }

   public void reset() {
      this.inflater.reset();
   }

   public long getBytesRead() {
      return this.inflater.getBytesRead();
   }

   public void free() {
      this.inflater.end();
   }
}
