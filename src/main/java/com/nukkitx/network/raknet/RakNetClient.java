package com.nukkitx.network.raknet;

import com.nukkitx.network.raknet.pipeline.ClientMessageHandler;
import com.nukkitx.network.raknet.pipeline.RakExceptionHandler;
import com.nukkitx.network.raknet.pipeline.RakOutboundHandler;
import com.nukkitx.network.util.EventLoops;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class RakNetClient extends RakNet {
   private static final InternalLogger log = InternalLoggerFactory.getInstance(RakNetClient.class);
   private final Map<InetSocketAddress, PingEntry> pings;
   protected InetSocketAddress bindAddress;
   protected RakNetClientSession session;
   private Channel channel;
   private EventLoop tickingEventLoop;

   public RakNetClient() {
      this(null, EventLoops.commonGroup());
   }

   public RakNetClient(InetSocketAddress bindAddress) {
      this(bindAddress, EventLoops.commonGroup());
   }

   public RakNetClient(@Nullable InetSocketAddress bindAddress, EventLoopGroup eventLoopGroup) {
      super(eventLoopGroup, eventLoopGroup);
      this.pings = new ConcurrentHashMap();
      this.bindAddress = bindAddress;
      this.exceptionHandlers.put("DEFAULT", (Consumer)(t) -> log.error("An exception occurred in RakNet Client, address=" + bindAddress, t));
   }

   @Override
   protected CompletableFuture<Void> bindInternal() {
      this.bootstrap.handler(new ClientChannelInitializer());
      ChannelFuture channelFuture = this.bindAddress == null? this.bootstrap.bind() : this.bootstrap.bind(this.bindAddress);

      CompletableFuture<Void> future = new CompletableFuture<>();
      channelFuture.addListener((ChannelFuture promise) -> {
         if (promise.cause() != null) {
            future.completeExceptionally(promise.cause());
            return;
         }

         SocketAddress address = promise.channel().localAddress();
         if (!(address instanceof InetSocketAddress)) {
            future.completeExceptionally(new IllegalArgumentException("Excepted InetSocketAddress but got "+address.getClass().getSimpleName()));
            return;
         }
         this.bindAddress = (InetSocketAddress) address;
         future.complete(null);
      });
      return future;
   }

   public RakNetClientSession connect(InetSocketAddress address) {
      if (!this.isRunning()) {
         throw new IllegalStateException("RakNet has not been started");
      } else if (this.session != null) {
         throw new IllegalStateException("Session has already been created");
      } else {
         this.session = new RakNetClientSession(this, address, this.channel, this.channel.eventLoop(), RakNetConstants.MAXIMUM_MTU_SIZE, this.protocolVersion);
         return this.session;
      }
   }

   public CompletableFuture<RakNetPong> ping(InetSocketAddress address, long timeout, TimeUnit unit) {
      if (!this.isRunning()) {
         throw new IllegalStateException("RakNet has not been started");
      } else if (this.session != null && this.session.address.equals(address)) {
         throw new IllegalArgumentException("Cannot ping connected address");
      } else if (this.pings.containsKey(address)) {
         return ((PingEntry)this.pings.get(address)).future;
      } else {
         long curTime = System.currentTimeMillis();
         CompletableFuture<RakNetPong> pongFuture = new CompletableFuture();
         PingEntry entry = new PingEntry(pongFuture, curTime + unit.toMillis(timeout));
         entry.sendTime = curTime;
         this.pings.put(address, entry);
         this.sendUnconnectedPing(address);
         return pongFuture;
      }
   }

   protected void onTick() {
      long curTime = System.currentTimeMillis();
      RakNetClientSession session = this.session;
      if (session != null && !session.isClosed()) {
         session.eventLoop.execute(() -> session.onTick(curTime));
      }

      Iterator<Map.Entry<InetSocketAddress, PingEntry>> iterator = this.pings.entrySet().iterator();

      while(iterator.hasNext()) {
         Map.Entry<InetSocketAddress, PingEntry> entry = (Map.Entry)iterator.next();
         PingEntry ping = (PingEntry)entry.getValue();
         if (curTime >= ping.timeout) {
            ping.future.completeExceptionally(new TimeoutException());
            iterator.remove();
         } else if (curTime - ping.sendTime >= 1000L) {
            ping.sendTime = curTime;
            this.sendUnconnectedPing((InetSocketAddress)entry.getKey());
         }
      }

   }

   public void onUnconnectedPong(PongEntry entry) {
      EventLoop eventLoop = this.nextEventLoop();
      if (eventLoop.inEventLoop()) {
         this.onUnconnectedPong0(entry);
      } else {
         eventLoop.execute(() -> this.onUnconnectedPong0(entry));
      }

   }

   private void onUnconnectedPong0(PongEntry pong) {
      PingEntry ping = (PingEntry)this.pings.remove(pong.address);
      if (ping != null) {
         ping.future.complete(new RakNetPong(pong.pingTime, System.currentTimeMillis(), pong.guid, pong.userData));
      } else {
         if (log.isDebugEnabled()) {
            log.debug("Received unexcepted pong from " + pong.address);
         }

      }
   }

   public void close(boolean force) {
      super.close(force);
      if (this.session != null && !this.session.isClosed()) {
         this.session.close();
      }

      if (this.channel != null) {
         ChannelFuture future = this.channel.close();
         if (force) {
            future.syncUninterruptibly();
         }
      }

   }

   private void sendUnconnectedPing(InetSocketAddress recipient) {
      ByteBuf buffer = this.channel.alloc().ioBuffer(23);
      buffer.writeByte(1);
      buffer.writeLong(System.currentTimeMillis());
      RakNetUtils.writeUnconnectedMagic(buffer);
      buffer.writeLong(this.guid);
      this.channel.writeAndFlush(new DatagramPacket(buffer, recipient));
   }

   public InetSocketAddress getBindAddress() {
      return this.bindAddress;
   }

   public RakNetClientSession getSession() {
      return this.session;
   }

   protected EventLoop nextEventLoop() {
      if (this.tickingEventLoop == null) {
         this.tickingEventLoop = super.nextEventLoop();
      }

      return this.tickingEventLoop;
   }

   public static class PingEntry {
      private final CompletableFuture<RakNetPong> future;
      private final long timeout;
      private long sendTime;

      public PingEntry(CompletableFuture<RakNetPong> future, long timeout) {
         this.future = future;
         this.timeout = timeout;
      }
   }

   public static class PongEntry {
      private final InetSocketAddress address;
      private final long pingTime;
      private final long guid;
      private final byte[] userData;

      public PongEntry(InetSocketAddress address, long pingTime, long guid, byte[] userData) {
         this.address = address;
         this.pingTime = pingTime;
         this.guid = guid;
         this.userData = userData;
      }
   }

   private class ClientChannelInitializer extends ChannelInitializer<Channel> {
      private ClientChannelInitializer() {
      }

      protected void initChannel(Channel channel) throws Exception {
         ChannelPipeline pipeline = channel.pipeline();
         pipeline.addLast("rak-outbound-handler", new RakOutboundHandler(RakNetClient.this));
         pipeline.addLast("rak-client-message-handler", new ClientMessageHandler(RakNetClient.this));
         pipeline.addLast("rak-exception-handler", new RakExceptionHandler(RakNetClient.this));
         RakNetClient.this.channel = channel;
      }
   }
}
