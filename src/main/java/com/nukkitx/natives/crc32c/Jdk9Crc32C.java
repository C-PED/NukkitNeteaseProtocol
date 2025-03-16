package com.nukkitx.natives.crc32c;

import java.nio.ByteBuffer;
import java.util.function.Supplier;
import java.util.zip.CRC32C;

public class Jdk9Crc32C implements Crc32C {
   public static final Supplier<Crc32C> SUPPLIER = Jdk9Crc32C::new;
   private final CRC32C crc32c = new CRC32C();

   private Jdk9Crc32C() {
   }

   public void update(int b) {
      this.crc32c.update(b);
   }

   public void update(byte[] b, int off, int len) {
      this.crc32c.update(b, off, len);
   }

   public void update(ByteBuffer buffer) {
      this.crc32c.update(buffer);
   }

   public long getValue() {
      return this.crc32c.getValue();
   }

   public void reset() {
      this.crc32c.reset();
   }

   public void free() {
   }
}
