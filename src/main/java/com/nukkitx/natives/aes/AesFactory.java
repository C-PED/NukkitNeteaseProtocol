package com.nukkitx.natives.aes;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

@FunctionalInterface
public interface AesFactory {
   Aes get(boolean var1, SecretKey var2, IvParameterSpec var3);
}
