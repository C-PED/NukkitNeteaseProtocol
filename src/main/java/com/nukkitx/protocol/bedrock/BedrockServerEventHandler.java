package com.nukkitx.protocol.bedrock;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import java.net.InetSocketAddress;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface BedrockServerEventHandler {
   default boolean onConnectionRequest(InetSocketAddress address, InetSocketAddress realAddress) {
      return this.onConnectionRequest(address);
   }

   default boolean onConnectionRequest(InetSocketAddress address) {
      throw new UnsupportedOperationException("BedrockServerEventHandler#onConnectionRequest is not implemented");
   }

   @Nullable
   BedrockPong onQuery(InetSocketAddress var1);

   default void onSessionCreation(BedrockServerSession serverSession) {
      throw new UnsupportedOperationException("BedrockServerEventHandler#onSessionCreation bedrockServerSession is not implemented");
   }

   default void onSessionCreation(BedrockProxyServerSession proxyServerSession) {
      throw new UnsupportedOperationException("BedrockServerEventHandler#onSessionCreation bedrockProxyServerSession  is not implemented");
   }

   default void onUnhandledDatagram(ChannelHandlerContext ctx, DatagramPacket packet) {
   }
}
