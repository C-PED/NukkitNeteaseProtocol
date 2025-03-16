package com.nukkitx.natives.aes;

import com.nukkitx.natives.util.ByteBufferUtils;
import java.nio.ByteBuffer;
import java.util.function.Supplier;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import sun.nio.ch.DirectBuffer;

public class NativeAes implements Aes {
   public static final AesFactory FACTORY = NativeAes::new;
   public static final Supplier<AesFactory> SUPPLIER = () -> FACTORY;
   private long ctx;

   private NativeAes(boolean encrypt, SecretKey key, IvParameterSpec iv) {
      if (!"AES".equals(key.getAlgorithm())) {
         throw new IllegalArgumentException("Invalid key given");
      } else {
         this.ctx = init(encrypt, key.getEncoded(), iv.getIV());
      }
   }

   private static native long init(boolean var0, byte[] var1, byte[] var2);

   private static native void free(long var0);

   public void cipher(ByteBuffer input, ByteBuffer output) {
      this.ensureOpen();
      int inPos = input.position();
      int inRem = Math.max(input.limit() - inPos, 0);
      int outPos = output.position();
      int outRem = Math.max(output.limit() - outPos, 0);
      if (inRem < 16) {
         throw new IllegalArgumentException("AES requires at least 16 bytes to encode");
      } else if (outRem < inRem) {
         throw new IllegalArgumentException("output buffer does not have enough space");
      } else {
         if (input.isDirect()) {
            long inAddress = ((DirectBuffer)input).address();
            if (output.isDirect()) {
               long outAddress = ((DirectBuffer)output).address();
               this.cipherBufferToBuffer(this.ctx, inAddress, outAddress, inRem);
            } else {
               byte[] outArray = ByteBufferUtils.getBufferArray(output);
               int outOffset = ByteBufferUtils.getBufferOffset(output);
               this.cipherBufferToBytes(this.ctx, inAddress, outArray, outOffset, inRem);
            }
         } else {
            byte[] inArray = ByteBufferUtils.getBufferArray(input);
            int inOffset = ByteBufferUtils.getBufferOffset(input);
            if (output.isDirect()) {
               long outAddress = ((DirectBuffer)output).address();
               this.cipherBytesToBuffer(this.ctx, inArray, inOffset, outAddress, inRem);
            } else {
               byte[] outArray = ByteBufferUtils.getBufferArray(output);
               int outOffset = ByteBufferUtils.getBufferOffset(output);
               this.cipherBytesToBytes(this.ctx, inArray, inOffset, outArray, outOffset, inRem);
            }
         }

      }
   }

   public synchronized void free() {
      if (this.ctx != 0L) {
         free(this.ctx);
         this.ctx = 0L;
      }

   }

   private void ensureOpen() {
      if (this.ctx == 0L) {
         throw new IllegalStateException("Native resource has already been freed");
      }
   }

   private native void cipherBytesToBytes(long var1, byte[] var3, int var4, byte[] var5, int var6, int var7);

   private native void cipherBytesToBuffer(long var1, byte[] var3, int var4, long var5, int var7);

   private native void cipherBufferToBytes(long var1, long var3, byte[] var5, int var6, int var7);

   private native void cipherBufferToBuffer(long var1, long var3, long var5, int var7);
}
