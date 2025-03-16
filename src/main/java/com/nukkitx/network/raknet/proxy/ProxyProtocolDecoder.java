package com.nukkitx.network.raknet.proxy;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.ProtocolDetectionResult;
import io.netty.util.CharsetUtil;

public final class ProxyProtocolDecoder implements ProxyProtocolConstants {
   private static final ProtocolDetectionResult<HAProxyProtocolVersion> DETECTION_RESULT_V1;
   private static final ProtocolDetectionResult<HAProxyProtocolVersion> DETECTION_RESULT_V2;
   private HeaderExtractor headerExtractor;
   private boolean discarding;
   private int discardedBytes;
   private boolean finished;
   private int decodingVersion = -1;
   private final int v2MaxHeaderSize = 65551;

   private ProxyProtocolDecoder(int version) {
      this.decodingVersion = version;
   }

   public static HAProxyMessage decode(ByteBuf packet, int version) {
      if (version == -1) {
         return null;
      } else {
         ProxyProtocolDecoder decoder = new ProxyProtocolDecoder(version);
         return decoder.decodeHeader(packet);
      }
   }

   private HAProxyMessage decodeHeader(ByteBuf in) {
      ByteBuf decoded = this.decodingVersion == 1 ? this.decodeLine(in) : this.decodeStruct(in);
      if (decoded == null) {
         return null;
      } else {
         this.finished = true;

         try {
            return this.decodingVersion == 1 ? HAProxyMessage.decodeHeader(decoded.toString(CharsetUtil.US_ASCII)) : HAProxyMessage.decodeHeader(decoded);
         } catch (HAProxyProtocolException e) {
            throw this.fail((String)null, e);
         }
      }
   }

   public static int findVersion(ByteBuf buffer) {
      int n = buffer.readableBytes();
      if (n < 13) {
         return -1;
      } else {
         int idx = buffer.readerIndex();
         return match(BINARY_PREFIX, buffer, idx) ? buffer.getByte(idx + BINARY_PREFIX_LENGTH) : 1;
      }
   }

   private ByteBuf decodeStruct(ByteBuf buffer) {
      if (this.headerExtractor == null) {
         this.headerExtractor = new StructHeaderExtractor(65551);
      }

      return this.headerExtractor.extract(buffer);
   }

   private ByteBuf decodeLine(ByteBuf buffer) {
      if (this.headerExtractor == null) {
         this.headerExtractor = new LineHeaderExtractor(108);
      }

      return this.headerExtractor.extract(buffer);
   }

   private void failOverLimit(String length) {
      int maxLength = this.decodingVersion == 1 ? 108 : 65551;
      throw this.fail("header length (" + length + ") exceeds the allowed maximum (" + maxLength + ')', (Exception)null);
   }

   private HAProxyProtocolException fail(String errMsg, Exception e) {
      this.finished = true;
      HAProxyProtocolException ppex;
      if (errMsg != null && e != null) {
         ppex = new HAProxyProtocolException(errMsg, e);
      } else if (errMsg != null) {
         ppex = new HAProxyProtocolException(errMsg);
      } else if (e != null) {
         ppex = new HAProxyProtocolException(e);
      } else {
         ppex = new HAProxyProtocolException();
      }

      return ppex;
   }

   public static ProtocolDetectionResult<HAProxyProtocolVersion> detectProtocol(ByteBuf buffer) {
      if (buffer.readableBytes() < 12) {
         return ProtocolDetectionResult.needsMoreData();
      } else {
         int idx = buffer.readerIndex();
         if (match(BINARY_PREFIX, buffer, idx)) {
            return DETECTION_RESULT_V2;
         } else {
            return match(TEXT_PREFIX, buffer, idx) ? DETECTION_RESULT_V1 : ProtocolDetectionResult.invalid();
         }
      }
   }

   private static boolean match(byte[] prefix, ByteBuf buffer, int idx) {
      for(int i = 0; i < prefix.length; ++i) {
         byte b = buffer.getByte(idx + i);
         if (b != prefix[i]) {
            return false;
         }
      }

      return true;
   }

   static {
      DETECTION_RESULT_V1 = ProtocolDetectionResult.detected(HAProxyProtocolVersion.V1);
      DETECTION_RESULT_V2 = ProtocolDetectionResult.detected(HAProxyProtocolVersion.V2);
   }

   private abstract class HeaderExtractor {
      private final int maxHeaderSize;

      protected HeaderExtractor(int maxHeaderSize) {
         this.maxHeaderSize = maxHeaderSize;
      }

      public ByteBuf extract(ByteBuf buffer) {
         int eoh = this.findEndOfHeader(buffer);
         if (!ProxyProtocolDecoder.this.discarding) {
            if (eoh >= 0) {
               int length = eoh - buffer.readerIndex();
               if (length > this.maxHeaderSize) {
                  buffer.readerIndex(eoh + this.delimiterLength(buffer, eoh));
                  ProxyProtocolDecoder.this.failOverLimit(String.valueOf(length));
                  return null;
               } else {
                  ByteBuf frame = buffer.readSlice(length);
                  buffer.skipBytes(this.delimiterLength(buffer, eoh));
                  return frame;
               }
            } else {
               int length = buffer.readableBytes();
               if (length > this.maxHeaderSize) {
                  ProxyProtocolDecoder.this.discardedBytes = length;
                  buffer.skipBytes(length);
                  ProxyProtocolDecoder.this.discarding = true;
                  ProxyProtocolDecoder.this.failOverLimit("over " + ProxyProtocolDecoder.this.discardedBytes);
               }

               return null;
            }
         } else {
            if (eoh >= 0) {
               int length = ProxyProtocolDecoder.this.discardedBytes + eoh - buffer.readerIndex();
               buffer.readerIndex(eoh + this.delimiterLength(buffer, eoh));
               ProxyProtocolDecoder.this.discardedBytes = 0;
               ProxyProtocolDecoder.this.discarding = false;
               ProxyProtocolDecoder.this.failOverLimit("over " + length);
            } else {
               ProxyProtocolDecoder.this.discardedBytes = buffer.readableBytes();
               buffer.skipBytes(buffer.readableBytes());
            }

            return null;
         }
      }

      protected abstract int findEndOfHeader(ByteBuf var1);

      protected abstract int delimiterLength(ByteBuf var1, int var2);
   }

   private final class LineHeaderExtractor extends HeaderExtractor {
      LineHeaderExtractor(int maxHeaderSize) {
         super(maxHeaderSize);
      }

      protected int findEndOfHeader(ByteBuf buffer) {
         int n = buffer.writerIndex();

         for(int i = buffer.readerIndex(); i < n; ++i) {
            byte b = buffer.getByte(i);
            if (b == 13 && i < n - 1 && buffer.getByte(i + 1) == 10) {
               return i;
            }
         }

         return -1;
      }

      protected int delimiterLength(ByteBuf buffer, int eoh) {
         return buffer.getByte(eoh) == 13 ? 2 : 1;
      }
   }

   private final class StructHeaderExtractor extends HeaderExtractor {
      StructHeaderExtractor(int maxHeaderSize) {
         super(maxHeaderSize);
      }

      protected int findEndOfHeader(ByteBuf buffer) {
         int n = buffer.readableBytes();
         if (n < 16) {
            return -1;
         } else {
            int offset = buffer.readerIndex() + 14;
            int totalHeaderBytes = 16 + buffer.getUnsignedShort(offset);
            return n >= totalHeaderBytes ? totalHeaderBytes : -1;
         }
      }

      protected int delimiterLength(ByteBuf buffer, int eoh) {
         return 0;
      }
   }
}
