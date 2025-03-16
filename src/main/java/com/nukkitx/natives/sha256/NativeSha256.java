package com.nukkitx.natives.sha256;

import com.nukkitx.natives.util.ByteBufferUtils;
import java.nio.ByteBuffer;
import java.util.function.Supplier;
import sun.nio.ch.DirectBuffer;

public class NativeSha256 implements Sha256 {
   public static final Supplier<Sha256> SUPPLIER = NativeSha256::new;
   private volatile long ctx = this.init();

   private NativeSha256() {
   }

   public void update(byte[] bytes, int offset, int length) {
      this.ensureOpen();
      this.update(this.ctx, bytes, offset, length);
   }

   public void update(ByteBuffer buffer) {
      this.ensureOpen();
      if (buffer.isDirect()) {
         this.update(this.ctx, ((DirectBuffer)buffer).address() + (long)buffer.arrayOffset(), buffer.remaining());
      } else {
         byte[] array = ByteBufferUtils.getBufferArray(buffer);
         int offset = ByteBufferUtils.getBufferOffset(buffer);
         this.update(this.ctx, array, offset, buffer.remaining());
      }

   }

   public byte[] digest() {
      return this.digest(this.ctx);
   }

   public void reset() {
      this.ensureOpen();
      this.reset(this.ctx);
   }

   public synchronized void free() {
      if (this.ctx != 0L) {
         this.free(this.ctx);
         this.ctx = 0L;
      }

   }

   private void ensureOpen() {
      if (this.ctx == 0L) {
         throw new IllegalStateException("Native resource has already been freed");
      }
   }

   private native long init();

   private native void free(long var1);

   private native void reset(long var1);

   private native void update(long var1, long var3, int var5);

   private native void update(long var1, byte[] var3, int var4, int var5);

   private native byte[] digest(long var1);
}
