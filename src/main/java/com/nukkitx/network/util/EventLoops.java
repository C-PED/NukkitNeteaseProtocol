package com.nukkitx.network.util;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueDatagramChannel;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.kqueue.KQueueSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.concurrent.ThreadFactory;
import java.util.function.BiFunction;

public final class EventLoops {
   private static final ChannelType CHANNEL_TYPE;
   private static EventLoopGroup EVENT_LOOP_GROUP;
   private static final ThreadFactory EVENT_LOOP_FACTORY = NetworkThreadFactory.builder().format("Network Listener - #%d").daemon(true).build();

   public static EventLoopGroup commonGroup() {
      if (EVENT_LOOP_GROUP == null) {
         EVENT_LOOP_GROUP = newEventLoopGroup(0);
      }

      return EVENT_LOOP_GROUP;
   }

   public static EventLoopGroup newEventLoopGroup(int threads) {
      return CHANNEL_TYPE.newEventLoopGroup(threads, EVENT_LOOP_FACTORY);
   }

   public static ChannelType getChannelType() {
      return CHANNEL_TYPE;
   }

   private EventLoops() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   static {
      boolean disableNative = System.getProperties().contains("disableNativeEventLoop");
      if (!disableNative && Epoll.isAvailable()) {
         CHANNEL_TYPE = ChannelType.EPOLL;
      } else if (!disableNative && KQueue.isAvailable()) {
         CHANNEL_TYPE = ChannelType.KQUEUE;
      } else {
         CHANNEL_TYPE = ChannelType.NIO;
      }

   }

   public static enum ChannelType {
      EPOLL(EpollDatagramChannel.class, EpollSocketChannel.class, EpollServerSocketChannel.class, (threads, factory) -> new EpollEventLoopGroup(threads, factory), Epoll.isAvailable()),
      KQUEUE(KQueueDatagramChannel.class, KQueueSocketChannel.class, KQueueServerSocketChannel.class, (threads, factory) -> new KQueueEventLoopGroup(threads, factory), KQueue.isAvailable()),
      NIO(NioDatagramChannel.class, NioSocketChannel.class, NioServerSocketChannel.class, (threads, factory) -> new NioEventLoopGroup(threads, factory), true);

      private final Class<? extends DatagramChannel> datagramChannel;
      private final Class<? extends SocketChannel> socketChannel;
      private final Class<? extends ServerSocketChannel> serverSocketChannel;
      private final BiFunction<Integer, ThreadFactory, EventLoopGroup> eventLoopGroupFactory;
      private final boolean available;

      public EventLoopGroup newEventLoopGroup(int threads, ThreadFactory factory) {
         return (EventLoopGroup)this.eventLoopGroupFactory.apply(threads, factory);
      }

      public Class<? extends DatagramChannel> getDatagramChannel() {
         return this.datagramChannel;
      }

      public Class<? extends SocketChannel> getSocketChannel() {
         return this.socketChannel;
      }

      public Class<? extends ServerSocketChannel> getServerSocketChannel() {
         return this.serverSocketChannel;
      }

      public BiFunction<Integer, ThreadFactory, EventLoopGroup> getEventLoopGroupFactory() {
         return this.eventLoopGroupFactory;
      }

      public boolean isAvailable() {
         return this.available;
      }

      private ChannelType(Class<? extends DatagramChannel> datagramChannel, Class<? extends SocketChannel> socketChannel, Class<? extends ServerSocketChannel> serverSocketChannel, BiFunction<Integer, ThreadFactory, EventLoopGroup> eventLoopGroupFactory, boolean available) {
         this.datagramChannel = datagramChannel;
         this.socketChannel = socketChannel;
         this.serverSocketChannel = serverSocketChannel;
         this.eventLoopGroupFactory = eventLoopGroupFactory;
         this.available = available;
      }

      // $FF: synthetic method
      private static ChannelType[] $values() {
         return new ChannelType[]{EPOLL, KQUEUE, NIO};
      }
   }
}
