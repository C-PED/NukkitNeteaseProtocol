package com.nukkitx.network.raknet;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import java.net.InetSocketAddress;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface RakNetServerListener {
   default boolean onConnectionRequest(InetSocketAddress address, InetSocketAddress realAddress) {
      return this.onConnectionRequest(address);
   }

   /** @deprecated */
   @Deprecated
   default boolean onConnectionRequest(InetSocketAddress address) {
      throw new UnsupportedOperationException("RakNetServerListener#onConnectionRequest is not implemented");
   }

   @Nullable
   byte[] onQuery(InetSocketAddress var1);

   void onSessionCreation(RakNetServerSession var1);

   void onUnhandledDatagram(ChannelHandlerContext var1, DatagramPacket var2);
}
