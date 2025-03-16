package com.nukkitx.network;

import io.netty.buffer.ByteBuf;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public final class NetworkUtils {
   private static final int AF_INET6 = 23;

   public static InetSocketAddress readAddress(ByteBuf buffer) {
      short type = (short)buffer.readByte();

      InetAddress address;
      int port;
      try {
         if (type == 4) {
            byte[] addressBytes = new byte[4];
            buffer.readBytes(addressBytes);
            flip(addressBytes);
            address = Inet4Address.getByAddress(addressBytes);
            port = buffer.readUnsignedShort();
         } else {
            if (type != 6) {
               throw new UnsupportedOperationException("Unknown Internet Protocol version.");
            }

            buffer.readShortLE();
            port = buffer.readUnsignedShort();
            buffer.readInt();
            byte[] addressBytes = new byte[16];
            buffer.readBytes(addressBytes);
            int scopeId = buffer.readInt();
            address = Inet6Address.getByAddress((String)null, addressBytes, scopeId);
         }
      } catch (UnknownHostException e) {
         throw new IllegalArgumentException(e);
      }

      return new InetSocketAddress(address, port);
   }

   public static void writeAddress(ByteBuf buffer, InetSocketAddress address) {
      byte[] addressBytes = address.getAddress().getAddress();
      if (address.getAddress() instanceof Inet4Address) {
         buffer.writeByte(4);
         flip(addressBytes);
         buffer.writeBytes(addressBytes);
         buffer.writeShort(address.getPort());
      } else {
         if (!(address.getAddress() instanceof Inet6Address)) {
            throw new UnsupportedOperationException("Unknown InetAddress instance");
         }

         buffer.writeByte(6);
         buffer.writeShortLE(23);
         buffer.writeShort(address.getPort());
         buffer.writeInt(0);
         buffer.writeBytes(addressBytes);
         buffer.writeInt(((Inet6Address)address.getAddress()).getScopeId());
      }

   }

   private static void flip(byte[] bytes) {
      for(int i = 0; i < bytes.length; ++i) {
         bytes[i] = (byte)(~bytes[i] & 255);
      }

   }

   private NetworkUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
