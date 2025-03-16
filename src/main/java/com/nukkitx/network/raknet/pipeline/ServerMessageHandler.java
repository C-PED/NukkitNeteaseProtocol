package com.nukkitx.network.raknet.pipeline;

import com.nukkitx.network.raknet.RakNetServer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.socket.DatagramPacket;
import java.net.InetSocketAddress;

@Sharable
public class ServerMessageHandler extends SimpleChannelInboundHandler<DatagramPacket> {
   public static final String NAME = "rak-server-message-handler";
   private final RakNetServer server;

   public ServerMessageHandler(RakNetServer server) {
      this.server = server;
   }

   protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
      if (!this.server.isBlocked(((InetSocketAddress)packet.sender()).getAddress())) {
         ByteBuf buffer = (ByteBuf)packet.content();
         if (buffer.isReadable()) {
            if (this.server.getMetrics() != null) {
               this.server.getMetrics().bytesIn(buffer.readableBytes());
            }

            ctx.fireChannelRead(packet.retain());
         }
      }
   }
}
