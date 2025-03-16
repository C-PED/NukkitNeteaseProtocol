package com.nukkitx.natives.aes;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.function.Supplier;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;

public class JavaAes implements Aes {
   public static final AesFactory FACTORY = JavaAes::new;
   public static final Supplier<AesFactory> SUPPLIER = () -> FACTORY;
   private final Cipher cipher;

   private JavaAes(boolean encrypt, SecretKey key, IvParameterSpec iv) {
      try {
         this.cipher = Cipher.getInstance("AES/CFB8/NoPadding");
         int mode = encrypt ? 1 : 2;
         this.cipher.init(mode, key, iv);
      } catch (InvalidAlgorithmParameterException var5) {
         throw new IllegalArgumentException("Invalid key given");
      } catch (GeneralSecurityException var6) {
         throw new AssertionError("Expected AES to be available");
      }
   }

   public void cipher(ByteBuffer input, ByteBuffer output) throws ShortBufferException {
      this.cipher.update(input, output);
   }

   public void free() {
   }
}
