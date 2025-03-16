package com.nukkitx.protocol.bedrock;

import com.nukkitx.network.raknet.EncapsulatedPacket;
import com.nukkitx.network.raknet.RakNetSession;
import com.nukkitx.network.raknet.RakNetSessionListener;
import com.nukkitx.network.raknet.RakNetState;
import com.nukkitx.network.util.DisconnectReason;
import com.nukkitx.protocol.bedrock.exception.ConnectionFailedException;
import io.netty.buffer.ByteBuf;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.concurrent.CompletableFuture;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class BedrockRakNetSessionListener implements RakNetSessionListener {
   final BedrockSession session;
   protected final RakNetSession connection;
   private static final InternalLogger log = InternalLoggerFactory.getInstance("");

   public void onEncapsulated(EncapsulatedPacket packet) {
      if (this.connection.getState() == RakNetState.CONNECTED) {
         ByteBuf buffer = packet.getBuffer();
         int packetId = buffer.readUnsignedByte();
         if (packetId == 254 && buffer.isReadable()) {
            if (this.session.getEventLoop().inEventLoop()) {
               try {
                  this.session.onWrappedPacket(buffer);
               } catch (Exception e) {
                  log.error("wrapped pakcet error:", e);
               }
            } else {
               buffer.retain();
               this.session.getEventLoop().execute(() -> {
                  try {
                     this.session.onWrappedPacket(buffer);
                  } catch (Exception e) {
                     log.error("wrapped pakcet error:", e);
                  } finally {
                     buffer.release();
                  }

               });
            }
         }

      }
   }

   public void onDirect(ByteBuf buf) {
   }

   BedrockRakNetSessionListener(BedrockSession session, RakNetSession connection) {
      this.session = session;
      this.connection = connection;
   }

   @ParametersAreNonnullByDefault
   public static class Client extends BedrockRakNetSessionListener {
      CompletableFuture<BedrockClientSession> future;
      private final BedrockClient client;

      Client(BedrockClientSession session, RakNetSession connection, BedrockClient client, CompletableFuture<BedrockClientSession> future) {
         super(session, connection);
         this.client = client;
         this.future = future;
      }

      public void onSessionChangeState(RakNetState state) {
         if (state == RakNetState.CONNECTED && this.future != null) {
            this.future.complete((BedrockClientSession)this.session);
            this.future = null;
         }

      }

      public void onDisconnect(DisconnectReason reason) {
         this.session.close(reason);
         if (this.future != null && !this.future.isDone()) {
            this.future.completeExceptionally(new ConnectionFailedException(reason));
         }

         this.client.session = null;
      }
   }

   @ParametersAreNonnullByDefault
   public static class Server extends BedrockRakNetSessionListener {
      private final BedrockServer server;

      Server(BedrockServerSession session, RakNetSession connection, BedrockServer server) {
         super(session, connection);
         this.server = server;
      }

      public void onSessionChangeState(RakNetState state) {
         if (state == RakNetState.CONNECTED) {
            this.server.sessions.add((BedrockServerSession)this.session);
            BedrockServerEventHandler handler = this.server.getHandler();
            if (handler != null) {
               handler.onSessionCreation((BedrockServerSession)this.session);
            }
         }

      }

      public void onDisconnect(DisconnectReason reason) {
         this.session.close(reason);
         this.server.sessions.remove((BedrockServerSession)this.session);
      }
   }

   @ParametersAreNonnullByDefault
   public static class ProxyServer extends BedrockRakNetSessionListener {
      private final BedrockServer server;

      ProxyServer(BedrockProxyServerSession session, RakNetSession connection, BedrockServer server) {
         super(session, connection);
         this.server = server;
      }

      public void onSessionChangeState(RakNetState state) {
         if (state == RakNetState.CONNECTED) {
            this.server.proxySessions.add((BedrockProxyServerSession)this.session);
            BedrockServerEventHandler handler = this.server.getHandler();
            if (handler != null) {
               handler.onSessionCreation((BedrockProxyServerSession)this.session);
            }
         }

      }

      public void onDisconnect(DisconnectReason reason) {
         BedrockProxyServerSession proxySess = (BedrockProxyServerSession)this.session;
         int proxyId = proxySess.getProxyId();
         BedrockRakNetSessionListener.log.info("proxy connection disconnect:" + proxyId);
         this.session.close(reason);
         this.server.proxySessions.remove(proxySess);
         if (proxyId != 0) {
            if (proxySess.getAddress() != ((BedrockProxyServerSession)this.server.proxyIdToSession.get(proxyId)).getAddress()) {
               BedrockRakNetSessionListener.log.info("raknet and proxy is not the same:" + ((BedrockProxyServerSession)this.server.proxyIdToSession.get(proxyId)).getAddress() + " raknet:" + this.connection.getAddress());
               return;
            }

            this.server.proxyIdToSession.remove(proxyId);
         }

      }
   }
}
