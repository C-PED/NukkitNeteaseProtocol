package com.nukkitx.protocol.common.util;

import com.nukkitx.natives.util.Natives;
import com.nukkitx.natives.zlib.Deflater;
import com.nukkitx.natives.zlib.Inflater;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.util.concurrent.FastThreadLocal;
import java.util.zip.DataFormatException;

public class Zlib {
   public static final Zlib DEFAULT = new Zlib(false);
   public static final Zlib RAW = new Zlib(true);
   private static final int CHUNK = 8192;
   private final FastThreadLocal<Inflater> inflaterLocal;
   private final FastThreadLocal<Deflater> deflaterLocal;

   private Zlib(final boolean raw) {
      this.inflaterLocal = new FastThreadLocal<Inflater>() {
         public Inflater initialValue() {
            return ((com.nukkitx.natives.zlib.Zlib)Natives.ZLIB.get()).create(raw);
         }
      };
      this.deflaterLocal = new FastThreadLocal<Deflater>() {
         protected Deflater initialValue() {
            return ((com.nukkitx.natives.zlib.Zlib)Natives.ZLIB.get()).create(7, raw);
         }
      };
   }

   public ByteBuf inflate(ByteBuf buffer, int maxSize) throws DataFormatException {
      ByteBuf source = null;
      ByteBuf decompressed = ByteBufAllocator.DEFAULT.ioBuffer();

      ByteBuf var14;
      try {
         if (buffer.isDirect() && (!(buffer instanceof CompositeByteBuf) || ((CompositeByteBuf)buffer).numComponents() <= 1)) {
            source = buffer;
         } else {
            ByteBuf temporary = ByteBufAllocator.DEFAULT.ioBuffer();
            temporary.writeBytes(buffer);
            source = temporary;
         }

         Inflater inflater = (Inflater)this.inflaterLocal.get();
         inflater.reset();
         inflater.setInput(source.internalNioBuffer(source.readerIndex(), source.readableBytes()));
         inflater.finished();

         while(!inflater.finished()) {
            decompressed.ensureWritable(8192);
            int index = decompressed.writerIndex();
            int written = inflater.inflate(decompressed.internalNioBuffer(index, 8192));
            if (written < 1) {
               break;
            }

            decompressed.writerIndex(index + written);
            if (maxSize > 0 && decompressed.writerIndex() >= maxSize) {
               throw new DataFormatException("Inflated data exceeds maximum size");
            }
         }

         var14 = decompressed;
      } catch (DataFormatException e) {
         decompressed.release();
         throw e;
      } finally {
         if (source != null && source != buffer) {
            source.release();
         }

      }

      return var14;
   }

   public void deflate(ByteBuf uncompressed, ByteBuf compressed, int level) throws DataFormatException {
      ByteBuf destination = null;
      ByteBuf source = null;

      try {
         if (uncompressed.isDirect() && (!(uncompressed instanceof CompositeByteBuf) || ((CompositeByteBuf)uncompressed).numComponents() <= 1)) {
            source = uncompressed;
         } else {
            source = ByteBufAllocator.DEFAULT.ioBuffer();
            source.writeBytes(uncompressed);
         }

         if (!compressed.isDirect()) {
            destination = ByteBufAllocator.DEFAULT.ioBuffer();
         } else {
            destination = compressed;
         }

         Deflater deflater = (Deflater)this.deflaterLocal.get();
         deflater.reset();
         deflater.setLevel(level);
         deflater.setInput(source.internalNioBuffer(source.readerIndex(), source.readableBytes()));

         while(!deflater.finished()) {
            int index = destination.writerIndex();
            destination.ensureWritable(8192);
            int written = deflater.deflate(destination.internalNioBuffer(index, 8192));
            destination.writerIndex(index + written);
         }

         if (destination != compressed) {
            compressed.writeBytes(destination);
         }
      } finally {
         if (source != null && source != uncompressed) {
            source.release();
         }

         if (destination != null && destination != compressed) {
            destination.release();
         }

      }

   }
}
