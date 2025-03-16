package com.nukkitx.natives.zlib;

import com.nukkitx.natives.Native;
import java.nio.ByteBuffer;

public interface Deflater extends Native {
   void setLevel(int var1);

   void setInput(ByteBuffer var1);

   int deflate(ByteBuffer var1);

   int getAdler();

   void reset();

   boolean finished();

   public interface Factory {
      Deflater newInstance(int var1, boolean var2);
   }
}
