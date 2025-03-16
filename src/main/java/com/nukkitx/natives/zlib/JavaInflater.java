package com.nukkitx.natives.zlib;

import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;

public class JavaInflater implements Inflater {
   private final byte[] chunkBytes = new byte[8192];
   private final java.util.zip.Inflater inflater;

   JavaInflater(boolean nowrap) {
      this.inflater = new java.util.zip.Inflater(nowrap);
   }

   public void setInput(ByteBuffer input) {
      if (input.hasArray()) {
         this.inflater.setInput(input.array(), input.arrayOffset() + input.position(), input.remaining());
      } else {
         byte[] bytes = new byte[input.remaining()];
         input.get(bytes);
         this.inflater.setInput(bytes);
      }

   }

   public int inflate(ByteBuffer output) throws DataFormatException {
      if (output.hasArray()) {
         return this.inflater.inflate(output.array(), output.arrayOffset() + output.position(), output.remaining());
      } else {
         int startPos = output.position();

         while(output.remaining() > 0 && !this.inflater.finished()) {
            int length = Math.min(output.remaining(), 8192);
            int result = this.inflater.inflate(this.chunkBytes, 0, length);
            output.put(this.chunkBytes, 0, result);
         }

         return output.position() - startPos;
      }
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
