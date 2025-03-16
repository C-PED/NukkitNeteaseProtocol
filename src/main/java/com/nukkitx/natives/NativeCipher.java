package com.nukkitx.natives;

import java.nio.ByteBuffer;
import javax.crypto.ShortBufferException;

public interface NativeCipher extends Native {
   void cipher(ByteBuffer var1, ByteBuffer var2) throws ShortBufferException, IllegalArgumentException;
}
