package com.nukkitx.natives;

import java.nio.ByteBuffer;
import java.security.DigestException;

public interface NativeDigest extends Native {
   default void update(byte[] input) {
      this.update(input, 0, input.length);
   }

   void update(byte[] var1, int var2, int var3);

   void update(ByteBuffer var1);

   byte[] digest();

   default int digest(byte[] input, int offset, int len) throws DigestException {
      if (input == null) {
         throw new IllegalArgumentException("No output buffer given");
      } else if (input.length - offset < len) {
         throw new IllegalArgumentException("Output buffer too small for specified offset and length");
      } else {
         byte[] digest = this.digest();
         if (len < digest.length) {
            throw new DigestException("partial digests not returned");
         } else if (input.length - offset < digest.length) {
            throw new DigestException("insufficient space in the output buffer to store the digest");
         } else {
            System.arraycopy(digest, 0, input, offset, digest.length);
            return digest.length;
         }
      }
   }

   default void digest(ByteBuffer buffer) {
      buffer.put(this.digest());
   }

   void reset();
}
