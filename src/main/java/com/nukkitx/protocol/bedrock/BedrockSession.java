package com.nukkitx.protocol.bedrock;

import com.nukkitx.natives.sha256.Sha256;
import com.nukkitx.natives.util.Natives;
import com.nukkitx.network.SessionConnection;
import com.nukkitx.network.util.DisconnectReason;
import com.nukkitx.protocol.bedrock.annotation.NoEncryption;
import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.compat.BedrockCompat;
import com.nukkitx.protocol.bedrock.data.PacketCompressionAlgorithm;
import com.nukkitx.protocol.bedrock.exception.PacketSerializeException;
import com.nukkitx.protocol.bedrock.handler.BatchHandler;
import com.nukkitx.protocol.bedrock.handler.DefaultBatchHandler;
import com.nukkitx.protocol.bedrock.packet.BedrockPacket;
import com.nukkitx.protocol.bedrock.packet.BedrockPacketHandler;
import com.nukkitx.protocol.bedrock.util.EncryptionUtils;
import com.nukkitx.protocol.bedrock.wrapper.BedrockWrapperSerializer;
import com.nukkitx.protocol.common.MinecraftSession;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.EventLoop;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;

public abstract class BedrockSession implements MinecraftSession<BedrockPacket> {
   protected static final InternalLogger log = InternalLoggerFactory.getInstance(BedrockSession.class);
   protected static final ThreadLocal<Sha256> HASH_LOCAL = new ThreadLocal<Sha256>() {
      protected Sha256 initialValue() {
         return Natives.SHA_256.get();
      }
   };
   protected final Set<Consumer<DisconnectReason>> disconnectHandlers = Collections.newSetFromMap(new ConcurrentHashMap());
   protected final Queue<BedrockPacket> queuedPackets = PlatformDependent.newMpscQueue();
   protected final AtomicLong sentEncryptedPacketCount = new AtomicLong();
   final SessionConnection<ByteBuf> connection;
   protected BedrockPacketHandler packetHandler;
   protected Cipher encryptionCipher = null;
   protected Cipher decryptionCipher = null;
   protected SecretKey agreedKey;
   protected int compressionLevel = -1;
   protected PacketCompressionAlgorithm compression;
   protected volatile boolean closed;
   protected volatile boolean logging;
   protected final EventLoop eventLoop;
   protected final BedrockWrapperSerializer wrapperSerializer;
   protected final AtomicInteger hardcodedBlockingId;
   protected BatchHandler batchHandler;
   protected BedrockCodec packetCodec;
   protected BedrockCodec minecraftPacketCodec;
   protected final Map<Long, BedrockSession> proxySessions;
   private boolean isPeUser;
   private boolean mIsSafeAndFast;

   BedrockSession(SessionConnection<ByteBuf> connection, EventLoop eventLoop, BedrockWrapperSerializer serializer) {
      this.compression = PacketCompressionAlgorithm.ZLIB;
      this.closed = false;
      this.logging = true;
      this.hardcodedBlockingId = new AtomicInteger(-1);
      this.batchHandler = DefaultBatchHandler.INSTANCE;
      this.packetCodec = BedrockCompat.CODEC;
      this.minecraftPacketCodec = BedrockCompat.CODEC;
      this.proxySessions = new ConcurrentHashMap();
      this.isPeUser = false;
      this.mIsSafeAndFast = true;
      this.connection = connection;
      this.eventLoop = eventLoop;
      this.wrapperSerializer = serializer;
   }

   public boolean isPeUser() {
      return this.isPeUser;
   }

   public void setPeUser(boolean peUser) {
      this.isPeUser = peUser;
   }

   public boolean getIsSafeAndFast() {
      return this.mIsSafeAndFast;
   }

   public void setIsSafeAndFast(boolean safeAndFast) {
      this.mIsSafeAndFast = safeAndFast;
   }

   public BedrockSession getPlayerSession(long uuid) {
      return (BedrockSession)this.proxySessions.getOrDefault(uuid, null);
   }

   public boolean hasPlayerSession(long uuid) {
      return this.proxySessions.containsKey(uuid);
   }

   public void addPlayerSession(long uuid, BedrockSession playerSession) {
      this.proxySessions.put(uuid, playerSession);
   }

   public void setPacketHandler(@Nonnull BedrockPacketHandler packetHandler) {
      this.packetHandler = packetHandler;
   }

   public void setPacketCodec(BedrockCodec packetCodec) {
      this.packetCodec = (BedrockCodec)Objects.requireNonNull(packetCodec, "packetCodec");
   }

   public void setMinecraftPacketCodec(BedrockCodec packetCodec) {
      this.minecraftPacketCodec = (BedrockCodec)Objects.requireNonNull(packetCodec, "packetCodec");
   }

   void checkForClosed() {
      if (this.closed) {
         throw new IllegalStateException("Connection has been closed");
      }
   }

   public void sendPacket(@Nonnull BedrockPacket packet) {
      this.checkPacket(packet);
      this.sendWrapped(Collections.singletonList(packet), !packet.getClass().isAnnotationPresent(NoEncryption.class));
   }

   private void checkPacket(BedrockPacket packet) {
      this.checkForClosed();
      Objects.requireNonNull(packet, "packet");
      if (log.isTraceEnabled() && this.logging) {
         String to = this.connection.getAddress().toString();
         log.trace("Outbound {}: {}", to, packet);
      }

      this.packetCodec.getId(packet);
   }

   public void sendWrapped(Collection<BedrockPacket> packets, boolean encrypt) {
      ByteBuf compressed = ByteBufAllocator.DEFAULT.ioBuffer();

      try {
         this.wrapperSerializer.serialize(compressed, this.packetCodec, packets, this.compressionLevel, this);
         this.sendWrapped(compressed, encrypt);
      } catch (Exception e) {
         log.error("Unable to compress packets", e);
      } finally {
         if (compressed != null) {
            compressed.release();
         }

      }

   }

   public synchronized void sendWrapped(ByteBuf compressed, boolean encrypt) {
      Objects.requireNonNull(compressed, "compressed");

      try {
         ByteBuf finalPayload = ByteBufAllocator.DEFAULT.ioBuffer(1 + compressed.readableBytes() + 8);
         finalPayload.writeByte(254);
         if (this.encryptionCipher != null && encrypt) {
            ByteBuffer trailer = ByteBuffer.wrap(this.generateTrailer(compressed));
            ByteBuffer outBuffer = finalPayload.internalNioBuffer(1, compressed.readableBytes() + 8);
            ByteBuffer inBuffer = compressed.internalNioBuffer(compressed.readerIndex(), compressed.readableBytes());
            this.encryptionCipher.update(inBuffer, outBuffer);
            this.encryptionCipher.update(trailer, outBuffer);
            finalPayload.writerIndex(finalPayload.writerIndex() + compressed.readableBytes() + 8);
         } else {
            finalPayload.writeBytes(compressed);
         }

         this.connection.send(finalPayload);
      } catch (GeneralSecurityException e) {
         throw new RuntimeException("Unable to encrypt package", e);
      }
   }

   public void tick() {
      this.onTick();
   }

   public void onTick() {
      if (!this.closed) {
         this.sendQueued();
      }
   }

   public void sendQueued() {
   }

   public synchronized void enableEncryption(@Nonnull SecretKey secretKey) {
      this.checkForClosed();
      Objects.requireNonNull(secretKey, "secretKey");
      if (!secretKey.getAlgorithm().equals("AES")) {
         throw new IllegalArgumentException("Invalid key algorithm");
      } else if (this.encryptionCipher == null && this.decryptionCipher == null) {
         this.agreedKey = secretKey;
         boolean useGcm = this.packetCodec.getProtocolVersion() > 428;
         this.encryptionCipher = EncryptionUtils.createCipher(useGcm, true, secretKey);
         this.decryptionCipher = EncryptionUtils.createCipher(useGcm, false, secretKey);
      } else {
         throw new IllegalStateException("Encryption has already been enabled");
      }
   }

   protected byte[] generateTrailer(ByteBuf buf) {
      Sha256 hash = (Sha256)HASH_LOCAL.get();
      ByteBuf counterBuf = ByteBufAllocator.DEFAULT.directBuffer(8);

      byte[] var6;
      try {
         counterBuf.writeLongLE(this.sentEncryptedPacketCount.getAndIncrement());
         ByteBuffer keyBuffer = ByteBuffer.wrap(this.agreedKey.getEncoded());
         hash.update(counterBuf.internalNioBuffer(0, 8));
         hash.update(buf.internalNioBuffer(buf.readerIndex(), buf.readableBytes()));
         hash.update(keyBuffer);
         byte[] digested = hash.digest();
         var6 = Arrays.copyOf(digested, 8);
      } finally {
         counterBuf.release();
         hash.reset();
      }

      return var6;
   }

   public boolean isEncrypted() {
      return this.encryptionCipher != null;
   }

   public boolean isDecrpyted() {
      return this.decryptionCipher != null;
   }

   public abstract void disconnect();

   public void close(DisconnectReason reason) {
      this.checkForClosed();
      this.closed = true;
      if (this.agreedKey != null && !this.agreedKey.isDestroyed()) {
         try {
            this.agreedKey.destroy();
         } catch (DestroyFailedException var4) {
         }
      }

      for(Consumer<DisconnectReason> disconnectHandler : this.disconnectHandlers) {
         disconnectHandler.accept(reason);
      }

   }

   public void onWrappedPacket(ByteBuf batched) {
      try {
         if (this.isEncrypted()) {
            ByteBuffer inBuffer = batched.internalNioBuffer(batched.readerIndex(), batched.readableBytes());
            ByteBuffer outBuffer = inBuffer.duplicate();
            this.decryptionCipher.update(inBuffer, outBuffer);
            batched.writerIndex(batched.writerIndex() - 8);
         }

         batched.markReaderIndex();
         if (batched.isReadable()) {
            List<BedrockPacket> packets = new ObjectArrayList();
            this.wrapperSerializer.deserialize(batched, this.packetCodec, packets, this);
            this.batchHandler.handle(this, batched, packets);
         }
      } catch (GeneralSecurityException var4) {
      } catch (PacketSerializeException e) {
         log.warn("Error whilst decoding packets", e);
      }

   }

   public InetSocketAddress getAddress() {
      return this.connection.getAddress();
   }

   public InetSocketAddress getRealAddress() {
      return this.connection.getRealAddress();
   }

   public boolean isClosed() {
      return this.connection.isClosed();
   }

   public BedrockCodec getPacketCodec() {
      return this.packetCodec;
   }

   public BedrockCodec getMinecraftPacketCodec() {
      return this.minecraftPacketCodec;
   }

   public BedrockPacketHandler getPacketHandler() {
      return this.packetHandler;
   }

   public BatchHandler getBatchHandler() {
      return this.batchHandler;
   }

   public void setBatchHandler(BatchHandler batchHandler) {
      this.batchHandler = (BatchHandler)Objects.requireNonNull(batchHandler, "batchHandler");
   }

   public void setCompressionLevel(int compressionLevel) {
      this.compressionLevel = compressionLevel;
   }

   public void setCompression(PacketCompressionAlgorithm algorithm) {
      this.compression = algorithm;
   }

   public int getCompressionLevel() {
      return this.compressionLevel;
   }

   public boolean isLogging() {
      return this.logging;
   }

   public void setLogging(boolean logging) {
      this.logging = logging;
   }

   public void addDisconnectHandler(Consumer<DisconnectReason> disconnectHandler) {
      Objects.requireNonNull(disconnectHandler, "disconnectHandler");
      this.disconnectHandlers.add(disconnectHandler);
   }

   public AtomicInteger getHardcodedBlockingId() {
      return this.hardcodedBlockingId;
   }

   public long getLatency() {
      return this.connection.getPing();
   }

   public EventLoop getEventLoop() {
      return this.eventLoop;
   }

   public BedrockWrapperSerializer getWrapperSerializer() {
      return this.wrapperSerializer;
   }

   public SessionConnection<ByteBuf> getConnection() {
      return this.connection;
   }

   public Set<Consumer<DisconnectReason>> getDisconnectHandlers() {
      return this.disconnectHandlers;
   }
}
