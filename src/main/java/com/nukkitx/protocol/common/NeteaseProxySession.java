package com.nukkitx.protocol.common;

import java.net.InetSocketAddress;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface NeteaseProxySession<T extends MinecraftPacket> {
   boolean isClosed();

   void disconnect();

   InetSocketAddress getAddress();

   default InetSocketAddress getRealAddress() {
      return this.getAddress();
   }

   void sendPacket(T var1);

   void sendPacketImmediately(T var1);

   long getLatency();
}
