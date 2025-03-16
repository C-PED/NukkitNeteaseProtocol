package com.nukkitx.natives.zlib;

import com.nukkitx.natives.Native;
import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;

public interface Inflater extends Native {
   void setInput(ByteBuffer var1);

   int inflate(ByteBuffer var1) throws DataFormatException;

   int getAdler();

   boolean finished();

   void reset();

   long getBytesRead();

   public interface Factory {
      Inflater newInstance(boolean var1);
   }
}
