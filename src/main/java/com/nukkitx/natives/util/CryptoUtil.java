package com.nukkitx.natives.util;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;

public class CryptoUtil {
   private CryptoUtil() {
   }

   public static boolean isJCEUnlimitedStrength() {
      try {
         return Cipher.getMaxAllowedKeyLength("AES") == Integer.MAX_VALUE;
      } catch (NoSuchAlgorithmException e) {
         throw new AssertionError(e);
      }
   }
}
