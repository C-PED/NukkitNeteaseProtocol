package com.nukkitx.protocol.common;

import java.net.InetSocketAddress;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface MinecraftSession<T extends MinecraftPacket> {
   boolean isClosed();

   void disconnect();

   InetSocketAddress getAddress();

   default InetSocketAddress getRealAddress() {
      return this.getAddress();
   }

   void sendPacket(T var1);

   void sendQueued();

   long getLatency();
}
