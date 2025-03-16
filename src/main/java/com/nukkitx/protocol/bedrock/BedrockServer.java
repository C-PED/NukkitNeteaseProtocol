package com.nukkitx.protocol.bedrock;

import com.nukkitx.network.raknet.RakNetServer;
import com.nukkitx.network.raknet.RakNetServerListener;
import com.nukkitx.network.raknet.RakNetServerSession;
import com.nukkitx.network.util.EventLoops;
import com.nukkitx.protocol.bedrock.wrapper.BedrockWrapperSerializer;
import com.nukkitx.protocol.bedrock.wrapper.BedrockWrapperSerializers;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class BedrockServer extends Bedrock {
   protected static final InternalLogger log = InternalLoggerFactory.getInstance(BedrockServer.class);
   private final RakNetServer rakNetServer;
   final Set<BedrockServerSession> sessions;
   final Set<BedrockProxyServerSession> proxySessions;
   final Map<Integer, BedrockProxyServerSession> proxyIdToSession;
   private BedrockServerEventHandler handler;
   private boolean mIsEnableNeteaseProxy;
   private boolean mIsEnableSafeAndFast;
   private boolean isOpenPacketStatistics;

   public BedrockServer(InetSocketAddress bindAddress) {
      this(bindAddress, 1);
   }

   public BedrockServer(InetSocketAddress bindAddress, int bindThreads) {
      this(bindAddress, bindThreads, EventLoops.commonGroup());
   }

   public BedrockServer(InetSocketAddress bindAddress, int bindThreads, EventLoopGroup eventLoopGroup) {
      this(bindAddress, bindThreads, eventLoopGroup, false);
   }

   public BedrockServer(InetSocketAddress bindAddress, int bindThreads, EventLoopGroup group, boolean allowProxyProtocol) {
      this(bindAddress, bindThreads, group, group, group, allowProxyProtocol);
   }

   public BedrockServer(InetSocketAddress bindAddress, int bindThreads, EventLoopGroup bossGroup, EventLoopGroup workerGroup, EventLoopGroup workerGroup2, boolean allowProxyProtocol) {
      super(bossGroup, workerGroup2);
      this.sessions = Collections.newSetFromMap(new ConcurrentHashMap());
      this.proxySessions = Collections.newSetFromMap(new ConcurrentHashMap());
      this.proxyIdToSession = new ConcurrentHashMap();
      this.mIsEnableNeteaseProxy = false;
      this.mIsEnableSafeAndFast = true;
      this.isOpenPacketStatistics = false;
      this.rakNetServer = new RakNetServer(bindAddress, bindThreads, bossGroup, workerGroup, allowProxyProtocol);
      this.rakNetServer.setProtocolVersion(-1);
      this.rakNetServer.setListener(new BedrockServerListener());
   }

   public void addProxy(int proxyId, BedrockProxyServerSession proxySession) {
      this.proxyIdToSession.put(proxyId, proxySession);
   }

   public int getOnlinePlayerNum() {
      int res = 0;

      for(BedrockProxyServerSession proxySess : this.proxySessions) {
         res += proxySess.getOnlinePlayerNum();
      }

      return res;
   }

   public int getPeUserNum() {
      int res = 0;

      for(BedrockProxyServerSession proxySess : this.proxySessions) {
         res += proxySess.getPeUserNum();
      }

      return res;
   }

   public BedrockProxyServerSession getProxyById(int proxyId) {
      return (BedrockProxyServerSession)this.proxyIdToSession.getOrDefault(proxyId, null);
   }

   public void setEnableNeteaseProxy(boolean isEnable) {
      this.mIsEnableNeteaseProxy = isEnable;
   }

   public void setEnableSafeAndFast(boolean isEnable) {
      this.mIsEnableSafeAndFast = isEnable;
   }

   public void setOpenPacketStatistics(boolean isEnable) {
      this.isOpenPacketStatistics = isEnable;
   }

   public BedrockServerEventHandler getHandler() {
      return this.handler;
   }

   public void setHandler(BedrockServerEventHandler handler) {
      this.handler = handler;
   }

   public RakNetServer getRakNet() {
      return this.rakNetServer;
   }

   public void close(boolean force) {
      this.close("disconnect.disconnected");
   }

   public void close(String reason) {
      List<CompletableFuture<Void>> futures = new ArrayList(this.sessions.size());

      for(BedrockServerSession session : this.sessions) {
         futures.add(session.disconnectFuture(reason, false));
      }

      CompletableFuture.allOf((CompletableFuture[])futures.toArray(new CompletableFuture[0])).join();
      this.rakNetServer.close();
      this.tickFuture.cancel(false);
   }

   public boolean isClosed() {
      return this.rakNetServer.isClosed();
   }

   protected void onTick() {
      for(BedrockServerSession session : this.sessions) {
         session.tick();
      }

      for(BedrockProxyServerSession session : this.proxySessions) {
         session.tick();
      }

   }

   @ParametersAreNonnullByDefault
   private class BedrockServerListener implements RakNetServerListener {
      private BedrockServerListener() {
      }

      public boolean onConnectionRequest(InetSocketAddress address, InetSocketAddress realAddress) {
         return BedrockServer.this.handler == null || BedrockServer.this.handler.onConnectionRequest(address, realAddress);
      }

      @Nullable
      public byte[] onQuery(InetSocketAddress address) {
         if (BedrockServer.this.handler != null) {
            BedrockPong pong = BedrockServer.this.handler.onQuery(address);
            if (pong != null) {
               pong.setServerId(BedrockServer.this.rakNetServer.getGuid());
               return pong.toRakNet();
            }
         }

         return null;
      }

      public void onSessionCreation(RakNetServerSession connection) {
         if (BedrockServer.this.mIsEnableNeteaseProxy) {
            BedrockWrapperSerializer serializer = BedrockWrapperSerializers.getSerializer(connection.getProtocolVersion());
            BedrockProxyServerSession session = new BedrockProxyServerSession(connection, BedrockServer.this.workerGroup.next(), serializer);
            session.setIsSafeAndFast(BedrockServer.this.mIsEnableSafeAndFast);
            session.setOpenPacketStatistics(BedrockServer.this.isOpenPacketStatistics);
            session.setAddress(connection.getAddress());
            connection.setListener(new BedrockRakNetSessionListener.ProxyServer(session, connection, BedrockServer.this));
         } else {
            BedrockWrapperSerializer serializer = BedrockWrapperSerializers.getSerializer(connection.getProtocolVersion());
            BedrockServerSession session = new BedrockServerSession(connection, BedrockServer.this.workerGroup.next(), serializer);
            session.setIsSafeAndFast(BedrockServer.this.mIsEnableSafeAndFast);
            connection.setListener(new BedrockRakNetSessionListener.Server(session, connection, BedrockServer.this));
         }

      }

      public void onUnhandledDatagram(ChannelHandlerContext ctx, DatagramPacket packet) {
         if (BedrockServer.this.handler != null) {
            BedrockServer.this.handler.onUnhandledDatagram(ctx, packet);
         }

      }
   }
}
