package com.nukkitx.network.raknet.pipeline;

import com.nukkitx.network.raknet.RakNetServer;
import com.nukkitx.network.raknet.RakNetSession;
import com.nukkitx.network.raknet.proxy.HAProxyMessage;
import com.nukkitx.network.raknet.proxy.HAProxyProtocolException;
import com.nukkitx.network.raknet.proxy.ProxyProtocolDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.InetAddress;
import java.net.InetSocketAddress;

@Sharable
public class ProxyServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
   private static final InternalLogger log = InternalLoggerFactory.getInstance(ProxyServerHandler.class);
   public static final String NAME = "rak-proxy-server-handler";
   private final RakNetServer server;

   public ProxyServerHandler(RakNetServer server) {
      this.server = server;
   }

   protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
      ByteBuf content = (ByteBuf)packet.content();
      RakNetSession session = this.server.getSession((InetSocketAddress)packet.sender());
      int detectedVersion = session != null ? -1 : ProxyProtocolDecoder.findVersion(content);
      InetSocketAddress presentAddress = this.server.getProxiedAddress((InetSocketAddress)packet.sender());
      if (presentAddress != null || detectedVersion != -1) {
         if (presentAddress == null) {
            HAProxyMessage decoded;
            try {
               if ((decoded = ProxyProtocolDecoder.decode(content, detectedVersion)) == null) {
                  return;
               }
            } catch (HAProxyProtocolException e) {
               log.debug("{} sent malformed PROXY header", packet.sender(), e);
               return;
            }

            presentAddress = decoded.sourceInetSocketAddress();
            log.debug("Got PROXY header: (from {}) {}", packet.sender(), presentAddress);
            if (log.isDebugEnabled()) {
               log.debug("PROXY Headers map size: {}", this.server.getProxiedAddressSize());
            }

            this.server.addProxiedAddress((InetSocketAddress)packet.sender(), presentAddress);
         } else {
            log.trace("Reusing PROXY header: (from {}) {}", packet.sender(), presentAddress);
            InetAddress address = presentAddress.getAddress();
            if (address == null || !this.server.isBlocked(address)) {
               ctx.fireChannelRead(packet.retain());
            }

         }
      }
   }
}
