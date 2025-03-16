package com.nukkitx.network;

import com.nukkitx.network.util.DisconnectReason;
import java.net.InetSocketAddress;

public interface SessionConnection<T> {
   InetSocketAddress getAddress();

   default InetSocketAddress getRealAddress() {
      return this.getAddress();
   }

   void close();

   void close(DisconnectReason var1);

   void disconnect();

   void disconnect(DisconnectReason var1);

   void send(T var1);

   void sendImmediate(T var1);

   boolean isClosed();

   long getPing();
}
