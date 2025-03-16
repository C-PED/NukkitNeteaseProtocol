package com.nukkitx.network.raknet.pipeline;

import com.nukkitx.network.raknet.RakNetServer;
import com.nukkitx.network.raknet.RakNetServerSession;
import com.nukkitx.network.raknet.RakNetUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.socket.DatagramPacket;
import java.net.InetSocketAddress;

@Sharable
public class ServerDatagramHandler extends SimpleChannelInboundHandler<DatagramPacket> {
   public static final String NAME = "rak-server-datagram-handler";
   private final RakNetServer server;

   public ServerDatagramHandler(RakNetServer server) {
      this.server = server;
   }

   protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
      ByteBuf buffer = (ByteBuf)packet.content();
      short packetId = (short)buffer.readByte();
      switch (packetId) {
         case 1:
            this.onUnconnectedPing(ctx, packet);
            return;
         case 5:
            this.server.onOpenConnectionRequest1(ctx, packet);
            return;
         default:
            buffer.readerIndex(0);
            RakNetServerSession session = this.server.getSession((InetSocketAddress)packet.sender());
            if (session != null) {
               if (session.getEventLoop().inEventLoop()) {
                  session.onDatagram(buffer.retain());
               } else {
                  ByteBuf buf = buffer.retain();
                  session.getEventLoop().execute(() -> session.onDatagram(buf));
               }
            } else if (this.server.getListener() != null) {
               this.server.getListener().onUnhandledDatagram(ctx, packet);
            }

      }
   }

   private void onUnconnectedPing(ChannelHandlerContext ctx, DatagramPacket packet) {
      if (((ByteBuf)packet.content()).isReadable(24)) {
         long pingTime = ((ByteBuf)packet.content()).readLong();
         if (RakNetUtils.verifyUnconnectedMagic((ByteBuf)packet.content())) {
            byte[] userData = null;
            if (this.server.getListener() != null) {
               userData = this.server.getListener().onQuery((InetSocketAddress)packet.sender());
            }

            if (userData == null) {
               userData = new byte[0];
            }

            int packetLength = 35 + userData.length;
            ByteBuf buffer = ctx.alloc().ioBuffer(packetLength, packetLength);
            buffer.writeByte(28);
            buffer.writeLong(pingTime);
            buffer.writeLong(this.server.getGuid());
            RakNetUtils.writeUnconnectedMagic(buffer);
            buffer.writeShort(userData.length);
            buffer.writeBytes(userData);
            ctx.writeAndFlush(new DatagramPacket(buffer, (InetSocketAddress)packet.sender()));
         }
      }
   }
}
