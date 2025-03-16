package com.nukkitx.network.raknet;

import com.nukkitx.network.SessionConnection;
import com.nukkitx.network.raknet.util.BitQueue;
import com.nukkitx.network.raknet.util.FastBinaryMinHeap;
import com.nukkitx.network.raknet.util.IntRange;
import com.nukkitx.network.raknet.util.RoundRobinArray;
import com.nukkitx.network.raknet.util.SplitPacketHelper;
import com.nukkitx.network.util.DisconnectReason;
import com.nukkitx.network.util.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.EventLoop;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.Inet6Address;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import javax.annotation.Nonnegative;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class RakNetSession implements SessionConnection<ByteBuf> {
   public static final InternalLogger log = InternalLoggerFactory.getInstance(RakNetSession.class);
   final InetSocketAddress address;
   InetSocketAddress proxiedAddress = null;
   final Channel channel;
   final EventLoop eventLoop;
   final int protocolVersion;
   private int mtu;
   private int adjustedMtu;
   long guid;
   private volatile RakNetState state;
   private volatile long lastTouched;
   private volatile boolean closed;
   private RakNetSlidingWindow slidingWindow;
   private int splitIndex;
   private int reliabilityReadIndex;
   private int reliabilityWriteIndex;
   private int[] orderReadIndex;
   private int[] orderWriteIndex;
   private RoundRobinArray<SplitPacketHelper> splitPackets;
   private BitQueue reliableDatagramQueue;
   private FastBinaryMinHeap outgoingPackets;
   private long[] outgoingPacketNextWeights;
   private FastBinaryMinHeap[] orderingHeaps;
   private int[] orderingHeapsOffsets;
   private volatile RakNetSessionListener listener;
   private volatile long currentPingTime;
   private volatile long lastPingTime;
   private volatile long lastPongTime;
   private ConcurrentMap<Integer, RakNetDatagram> sentDatagrams;
   private Queue<IntRange> incomingAcks;
   private Queue<IntRange> incomingNaks;
   private Queue<IntRange> outgoingAcks;
   private Queue<IntRange> outgoingNaks;
   private int unackedBytes;
   private long lastMinWeight;
   private int sessionTimeout;
   private boolean bandwidthExceededStatistic;

   RakNetSession(InetSocketAddress address, Channel channel, EventLoop eventLoop, int mtu, int protocolVersion) {
      this.state = RakNetState.UNCONNECTED;
      this.lastTouched = System.currentTimeMillis();
      this.closed = false;
      this.listener = null;
      this.currentPingTime = -1L;
      this.lastPingTime = -1L;
      this.lastPongTime = -1L;
      this.sessionTimeout = 10000;
      this.address = address;
      this.channel = channel;
      this.eventLoop = eventLoop;
      this.setMtu(mtu);
      this.protocolVersion = protocolVersion;
   }

   final void initialize() {
      Preconditions.checkState(this.state == RakNetState.INITIALIZING);
      this.slidingWindow = new RakNetSlidingWindow(this.mtu, this.adjustedMtu);
      this.bandwidthExceededStatistic = false;
      this.reliableDatagramQueue = new BitQueue(512);
      this.orderReadIndex = new int[16];
      this.orderWriteIndex = new int[16];
      this.orderingHeaps = new FastBinaryMinHeap[16];
      this.orderingHeapsOffsets = new int[16];
      this.splitPackets = new RoundRobinArray<SplitPacketHelper>(256);
      this.sentDatagrams = new ConcurrentSkipListMap();

      for(int i = 0; i < 16; ++i) {
         this.orderingHeaps[i] = new FastBinaryMinHeap(64);
      }

      this.outgoingPackets = new FastBinaryMinHeap(8);
      this.incomingAcks = PlatformDependent.newMpscQueue();
      this.incomingNaks = PlatformDependent.newMpscQueue();
      this.outgoingAcks = PlatformDependent.newMpscQueue();
      this.outgoingNaks = PlatformDependent.newMpscQueue();
      this.outgoingPacketNextWeights = new long[4];
      this.initHeapWeights();
   }

   private void deinitialize() {
      if (this.splitPackets != null) {
         this.splitPackets.forEach(ReferenceCountUtil::release);
      }

      if (this.sentDatagrams != null) {
         this.sentDatagrams.values().forEach(ReferenceCountUtil::release);
      }

      FastBinaryMinHeap[] orderingHeaps = this.orderingHeaps;
      this.orderingHeaps = null;
      if (orderingHeaps != null) {
         EncapsulatedPacket packet;
         for(FastBinaryMinHeap orderingHeap : orderingHeaps) {
            while((packet = orderingHeap.poll()) != null) {
               packet.release();
            }
         }
      }

      FastBinaryMinHeap outgoingPackets = this.outgoingPackets;
      this.outgoingPackets = null;
      EncapsulatedPacket packet;
      if (outgoingPackets != null) {
         while((packet = outgoingPackets.poll()) != null) {
            packet.release();
         }
      }

   }

   public InetSocketAddress getAddress() {
      return this.address;
   }

   public InetSocketAddress getRealAddress() {
      InetSocketAddress proxied = this.proxiedAddress;
      return proxied == null ? this.address : proxied;
   }

   public int getMtu() {
      return this.mtu;
   }

   void setMtu(int mtu) {
      this.mtu = RakNetUtils.clamp(mtu, 576, RakNetConstants.MAXIMUM_MTU_SIZE);
      this.adjustedMtu = this.mtu - 8 - (this.address.getAddress() instanceof Inet6Address ? 40 : 20);
      this.slidingWindow = new RakNetSlidingWindow(this.mtu, this.adjustedMtu);
   }

   public int getProtocolVersion() {
      return this.protocolVersion;
   }

   public long getPing() {
      return this.lastPongTime - this.lastPingTime;
   }

   public double getRTT() {
      return this.slidingWindow.getRTT();
   }

   public ByteBuf allocateBuffer(int capacity) {
      return this.channel.alloc().ioBuffer(capacity);
   }

   private void initHeapWeights() {
      for(int priorityLevel = 0; priorityLevel < 4; ++priorityLevel) {
         this.outgoingPacketNextWeights[priorityLevel] = (1L << priorityLevel) * (long)priorityLevel + (long)priorityLevel;
      }

   }

   private long getNextWeight(RakNetPriority priority) {
      int priorityLevel = priority.ordinal();
      long next = this.outgoingPacketNextWeights[priorityLevel];
      if (!this.outgoingPackets.isEmpty()) {
         if (next >= this.lastMinWeight) {
            next = this.lastMinWeight + (1L << priorityLevel) * (long)priorityLevel + (long)priorityLevel;
            this.outgoingPacketNextWeights[priorityLevel] = next + (1L << priorityLevel) * (long)(priorityLevel + 1) + (long)priorityLevel;
         }
      } else {
         this.initHeapWeights();
      }

      this.lastMinWeight = next - (1L << priorityLevel) * (long)priorityLevel + (long)priorityLevel;
      return next;
   }

   private EncapsulatedPacket getReassembledPacket(EncapsulatedPacket splitPacket) {
      this.checkForClosed();
      SplitPacketHelper helper = this.splitPackets.get(splitPacket.getPartId());
      if (helper == null) {
         this.splitPackets.set(splitPacket.getPartId(), helper = new SplitPacketHelper((long)splitPacket.getPartCount()));
      }

      EncapsulatedPacket result = helper.add(splitPacket, this);
      if (result != null && this.splitPackets.remove(splitPacket.getPartId(), helper)) {
         helper.release();
      }

      return result;
   }

   public void onDatagram(ByteBuf buffer) {
      try {
         if (!this.isClosed()) {
            this.touch();
            byte potentialFlags = buffer.readByte();
            boolean rakNetDatagram = (potentialFlags & -128) != 0;
            if (!rakNetDatagram) {
               buffer.readerIndex(0);
               this.onPacketInternal(buffer);
               return;
            }

            if (this.state != null && this.state.ordinal() >= RakNetState.INITIALIZED.ordinal()) {
               if ((potentialFlags & 64) != 0) {
                  this.onAcknowledge(buffer, this.incomingAcks, false);
                  return;
               } else {
                  if ((potentialFlags & 32) != 0) {
                     this.onAcknowledge(buffer, this.incomingNaks, true);
                  } else {
                     buffer.readerIndex(0);
                     this.onRakNetDatagram(buffer);
                  }

                  return;
               }
            }

            return;
         }
      } finally {
         buffer.release();
      }

   }

   private void onEncapsulatedInternal(EncapsulatedPacket packet) {
      ByteBuf buffer = packet.buffer;
      short packetId = buffer.readUnsignedByte();
      switch (packetId) {
         case 0:
            this.onConnectedPing(buffer);
            break;
         case 3:
            this.onConnectedPong(buffer);
            break;
         case 21:
            this.onDisconnectionNotification();
            break;
         default:
            buffer.readerIndex(0);
            if (packetId >= 128) {
               if (this.listener != null) {
                  this.listener.onEncapsulated(packet);
               }
            } else {
               this.onPacket(buffer);
            }
      }

   }

   private void onPacketInternal(ByteBuf buffer) {
      short packetId = buffer.getUnsignedByte(buffer.readerIndex());
      buffer.readerIndex(0);
      if (packetId >= 128) {
         if (this.listener != null) {
            this.listener.onDirect(buffer);
         }
      } else {
         this.onPacket(buffer);
      }

   }

   protected abstract void onPacket(ByteBuf var1);

   private void onRakNetDatagram(ByteBuf buffer) {
      if (this.state != null && RakNetState.INITIALIZED.compareTo(this.state) <= 0) {
         RakNetDatagram datagram = new RakNetDatagram(System.currentTimeMillis());
         datagram.decode(buffer);
         int missedDatagrams = this.slidingWindow.onPacketReceived(datagram.sendTime, datagram.sequenceIndex);
         if (missedDatagrams == -1) {
            log.info("too many missed datagrams!!!!!!");
            if (log.isDebugEnabled()) {
               log.debug("Too many missed datagrams!");
            }

            this.disconnect(DisconnectReason.TIMED_OUT);
         } else {
            for(int skippedMessageOffset = missedDatagrams; skippedMessageOffset > 0; --skippedMessageOffset) {
               this.offerAcknowledge(new IntRange(datagram.sequenceIndex - skippedMessageOffset & 16777215), true);
            }

            if (this.getRakNet().getMetrics() != null) {
               this.getRakNet().getMetrics().rakDatagramsIn(1);
            }

            boolean ackImmediate = false;

            for(EncapsulatedPacket encapsulated : datagram.packets) {
               if (encapsulated.reliability.isReliable()) {
                  int missed = encapsulated.reliabilityIndex - this.reliabilityReadIndex & 16777215;
                  if (missed == 0) {
                     this.reliabilityReadIndex = this.reliabilityReadIndex + 1 & 16777215;
                     if (!this.reliableDatagramQueue.isEmpty()) {
                        this.reliableDatagramQueue.poll();
                     }
                  } else {
                     if (missed > 8388607) {
                        continue;
                     }

                     if (missed < this.reliableDatagramQueue.size()) {
                        if (!this.reliableDatagramQueue.get(missed)) {
                           continue;
                        }

                        this.reliableDatagramQueue.set(missed, false);
                     } else {
                        if (missed > 1000000) {
                           log.info("Hole count too high. See ReliabilityLayer.h!!!!!");
                           continue;
                        }

                        int count = missed - this.reliableDatagramQueue.size();

                        for(int i = 0; i < count; ++i) {
                           this.reliableDatagramQueue.add(true);
                        }

                        this.reliableDatagramQueue.add(false);
                     }
                  }

                  while(!this.reliableDatagramQueue.isEmpty() && !this.reliableDatagramQueue.peek()) {
                     this.reliableDatagramQueue.poll();
                     this.reliabilityReadIndex = this.reliabilityReadIndex + 1 & 16777215;
                  }

                  if (missed > 100) {
                     ackImmediate = true;
                  }
               }

               if (encapsulated.split) {
                  EncapsulatedPacket reassembled = this.getReassembledPacket(encapsulated);
                  if (reassembled != null) {
                     ackImmediate = true;

                     try {
                        this.checkForOrdered(reassembled);
                     } finally {
                        reassembled.release();
                     }
                  }
               } else {
                  this.checkForOrdered(encapsulated);
               }
            }

            IntRange range = new IntRange(datagram.sequenceIndex, datagram.sequenceIndex);
            if (!ackImmediate || !this.writeAcknowledge(range, false)) {
               this.offerAcknowledge(range, false);
            }

         }
      }
   }

   private void offerAcknowledge(IntRange range, boolean nack) {
      if (nack) {
         this.outgoingNaks.offer(range);
      } else {
         this.outgoingAcks.offer(range);
      }

   }

   private int flushAcknowledge(Queue<IntRange> range, boolean nack) {
      int mtu = this.adjustedMtu - 4;
      int written = 0;

      while(!range.isEmpty()) {
         ByteBuf buffer = this.allocateBuffer(mtu);
         buffer.writeByte(-128 | (nack ? 32 : 64));
         written += RakNetUtils.writeIntRanges(buffer, range, mtu - 1);
         this.sendDirect(buffer);
      }

      if (written > 0 && !nack) {
         this.slidingWindow.onSendAck();
      }

      return written;
   }

   private boolean writeAcknowledge(IntRange range, boolean nack) {
      int mtu = this.adjustedMtu - 4;
      ByteBuf buffer = this.allocateBuffer(mtu);
      buffer.writeByte(-128 | (nack ? 32 : 64));
      buffer.writeShort(1);
      if (range.start == range.end) {
         if (mtu < 4) {
            return false;
         }

         buffer.writeBoolean(true);
         buffer.writeMediumLE(range.start);
      } else {
         if (mtu < 7) {
            return false;
         }

         buffer.writeBoolean(false);
         buffer.writeMediumLE(range.start);
         buffer.writeMediumLE(range.end);
      }

      this.sendDirect(buffer);
      if (this.getRakNet().getMetrics() != null) {
         if (nack) {
            this.getRakNet().getMetrics().nackOut(range.end - range.start + 1);
         } else {
            this.getRakNet().getMetrics().ackOut(range.end - range.start + 1);
         }
      }

      if (!nack) {
         this.slidingWindow.onSendAck();
      }

      return true;
   }

   private void checkForOrdered(EncapsulatedPacket packet) {
      if (packet.getReliability().isOrdered()) {
         this.onOrderedReceived(packet);
      } else {
         this.onEncapsulatedInternal(packet);
      }

   }

   private void onOrderedReceived(EncapsulatedPacket packet) {
      FastBinaryMinHeap binaryHeap = this.orderingHeaps[packet.orderingChannel];
      int thisIndex = this.orderReadIndex[packet.orderingChannel];
      if (binaryHeap.size() == 0) {
         this.orderingHeapsOffsets[packet.orderingChannel] = thisIndex;
      }

      int packetIndex = packet.orderingIndex;
      if (packetIndex <= thisIndex && thisIndex - packetIndex <= 8388607) {
         if (thisIndex == packetIndex) {
            this.orderReadIndex[packet.orderingChannel] = thisIndex + 1 & 16777215;
            this.onEncapsulatedInternal(packet);

            EncapsulatedPacket queuedPacket;
            while((queuedPacket = binaryHeap.peek()) != null && queuedPacket.orderingIndex == this.orderReadIndex[packet.orderingChannel]) {
               try {
                  binaryHeap.remove();
                  this.orderReadIndex[packet.orderingChannel] = this.orderReadIndex[packet.orderingChannel] + 1 & 16777215;
                  this.onEncapsulatedInternal(queuedPacket);
               } finally {
                  queuedPacket.release();
               }
            }

         }
      } else {
         int heapPacketIndex = packetIndex - this.orderingHeapsOffsets[packet.orderingChannel] & 16777215;
         binaryHeap.insert((long)heapPacketIndex, packet.retain());
      }
   }

   final void onTick(long curTime) {
      if (!this.isClosed()) {
         this.tick(curTime);
      }
   }

   protected void tick(long curTime) {
      if (this.isTimedOut(curTime)) {
         this.close(DisconnectReason.TIMED_OUT);
      } else if (this.state != null && this.state.ordinal() >= RakNetState.INITIALIZED.ordinal()) {
         if (this.currentPingTime + 10000L < curTime) {
            this.sendConnectedPing(curTime);
         }

         this.handleIncomingAcknowledge(curTime, this.incomingAcks, false);
         this.handleIncomingAcknowledge(curTime, this.incomingNaks, true);
         int writtenAcks = this.flushAcknowledge(this.outgoingAcks, false);
         int writtenNacks = this.flushAcknowledge(this.outgoingNaks, true);
         RakMetrics metrics = this.getRakNet().getMetrics();
         if (metrics != null) {
            if (writtenNacks > 0) {
               metrics.nackOut(writtenNacks);
            }

            if (writtenAcks > 0) {
               metrics.ackOut(writtenAcks);
            }
         }

         boolean isContinuousSend = this.bandwidthExceededStatistic;
         this.bandwidthExceededStatistic = this.outgoingPackets.size() > 0;
         this.sendStaleDatagrams(curTime);
         this.sendDatagrams(curTime, isContinuousSend);
         this.channel.flush();
      }
   }

   private void handleIncomingAcknowledge(long curTime, Queue<IntRange> queue, boolean nack) {
      if (!queue.isEmpty()) {
         if (nack) {
            this.slidingWindow.onNak();
         }

         IntRange range;
         while((range = (IntRange)queue.poll()) != null) {
            for(int i = range.start; i <= range.end; ++i) {
               RakNetDatagram datagram = (RakNetDatagram)this.sentDatagrams.remove(i);
               if (datagram != null) {
                  if (nack) {
                     this.onIncomingNack(datagram, curTime);
                  } else {
                     this.onIncomingAck(datagram, curTime);
                  }
               }
            }
         }

      }
   }

   private void onIncomingAck(RakNetDatagram datagram, long curTime) {
      try {
         this.unackedBytes -= datagram.getSize();
         this.slidingWindow.onAck(curTime - datagram.sendTime, (long)datagram.sequenceIndex, this.bandwidthExceededStatistic);
      } finally {
         datagram.release();
      }

   }

   private void onIncomingNack(RakNetDatagram datagram, long curTime) {
      if (log.isTraceEnabled()) {
         log.trace("NAK'ed datagram {} from {}", datagram.sequenceIndex, this.address);
      }

      datagram.isContinuousSend = false;
      this.sendDatagram(datagram, curTime);
   }

   private void sendStaleDatagrams(long curTime) {
      if (!this.sentDatagrams.isEmpty()) {
         int resendCount = 0;
         int transmissionBandwidth = this.slidingWindow.getRetransmissionBandwidth(this.unackedBytes, this.bandwidthExceededStatistic);
         List<RakNetDatagram> resend = new ArrayList(this.sentDatagrams.size());

         for(RakNetDatagram datagram : this.sentDatagrams.values()) {
            if (datagram.getNextSend() <= curTime) {
               int size = datagram.getSize();
               if (transmissionBandwidth >= size) {
                  transmissionBandwidth -= size;
                  ++resendCount;
                  resend.add(datagram);
                  if (resendCount > 256) {
                     break;
                  }
               }
            }
         }

         boolean isContinuousSend = resend.size() > 1;

         for(RakNetDatagram datagram : resend) {
            datagram.isContinuousSend = isContinuousSend;
            this.sendDatagram(datagram, curTime);
         }

         if (resendCount > 0) {
            this.slidingWindow.onResend(curTime);
         }

         RakMetrics metrics = this.getRakNet().getMetrics();
         if (metrics != null) {
            metrics.rakStaleDatagrams(resendCount);
         }

      }
   }

   private void sendDatagrams(long curTime, boolean isContinuousSend) {
      if (!this.outgoingPackets.isEmpty()) {
         int transmissionBandwidth = this.slidingWindow.getTransmissionBandwidth(this.unackedBytes, this.bandwidthExceededStatistic);
         RakNetDatagram datagram = new RakNetDatagram(curTime);
         datagram.isContinuousSend = isContinuousSend;

         EncapsulatedPacket packet;
         while((packet = this.outgoingPackets.peek()) != null) {
            int size = packet.getSize();
            if (transmissionBandwidth < size) {
               break;
            }

            transmissionBandwidth -= size;
            this.outgoingPackets.remove();
            if (!datagram.tryAddPacket(packet, this.adjustedMtu)) {
               this.sendDatagram(datagram, curTime);
               datagram = new RakNetDatagram(curTime);
               datagram.isContinuousSend = isContinuousSend;
               if (!datagram.tryAddPacket(packet, this.adjustedMtu)) {
                  throw new IllegalArgumentException("Packet too large to fit in MTU (size: " + size + ", MTU: " + this.adjustedMtu + ")");
               }
            }
         }

         if (!datagram.getPackets().isEmpty()) {
            this.sendDatagram(datagram, curTime);
         }

      }
   }

   public void disconnect() {
      this.disconnect(DisconnectReason.DISCONNECTED);
   }

   public void disconnect(DisconnectReason reason) {
      if (!this.isClosed()) {
         this.eventLoop.execute(() -> this.disconnect0(reason));
      }

   }

   private void disconnect0(DisconnectReason reason) {
      if (!this.isClosed()) {
         this.sendDisconnectionNotification();
         this.close0(reason);
      }

   }

   public void close() {
      this.close(DisconnectReason.DISCONNECTED);
   }

   public void close(DisconnectReason reason) {
      if (!this.isClosed()) {
         this.eventLoop.execute(() -> this.close0(reason));
      }

   }

   private void close0(DisconnectReason reason) {
      if (!this.isClosed()) {
         this.closed = true;
         this.state = RakNetState.UNCONNECTED;
         this.onClose();
         if (log.isTraceEnabled()) {
            log.trace("RakNet Session ({} => {}) closed: {}", new Object[]{this.getRakNet().getBindAddress(), this.address, reason});
         }

         this.deinitialize();
         if (this.listener != null) {
            this.listener.onDisconnect(reason);
         }

      }
   }

   protected void onClose() {
   }

   public void sendImmediate(ByteBuf buf) {
      this.send(buf, RakNetPriority.IMMEDIATE);
   }

   public void send(ByteBuf buf) {
      this.send(buf, RakNetPriority.MEDIUM);
   }

   public void send(ByteBuf buf, RakNetPriority priority) {
      this.send(buf, priority, RakNetReliability.RELIABLE_ORDERED);
   }

   public void send(ByteBuf buf, RakNetReliability reliability) {
      this.send(buf, RakNetPriority.MEDIUM, reliability);
   }

   public void send(ByteBuf buf, RakNetPriority priority, RakNetReliability reliability) {
      this.send(buf, priority, reliability, 0);
   }

   public void send(ByteBuf buf, RakNetPriority priority, RakNetReliability reliability, @Nonnegative int orderingChannel) {
      if (this.eventLoop.inEventLoop()) {
         this.send0(buf, priority, reliability, orderingChannel);
      } else {
         this.eventLoop.execute(() -> this.send0(buf, priority, reliability, orderingChannel));
      }

   }

   private void send0(ByteBuf buf, RakNetPriority priority, RakNetReliability reliability, @Nonnegative int orderingChannel) {
      try {
         if (!this.isClosed() && this.state != null && this.state.ordinal() >= RakNetState.INITIALIZED.ordinal()) {
            EncapsulatedPacket[] packets = this.createEncapsulated(buf, priority, reliability, orderingChannel);
            if (priority == RakNetPriority.IMMEDIATE) {
               this.sendImmediate(packets);
               return;
            }

            long weight = this.getNextWeight(priority);
            if (packets.length == 1) {
               this.outgoingPackets.insert(weight, packets[0]);
            } else {
               this.outgoingPackets.insertSeries(weight, packets);
            }

            return;
         }
      } finally {
         buf.release();
      }

   }

   private void sendImmediate(EncapsulatedPacket[] packets) {
      long curTime = System.currentTimeMillis();

      for(EncapsulatedPacket packet : packets) {
         RakNetDatagram datagram = new RakNetDatagram(curTime);
         if (!datagram.tryAddPacket(packet, this.adjustedMtu)) {
            throw new IllegalArgumentException("Packet too large to fit in MTU (size: " + packet.getSize() + ", MTU: " + this.adjustedMtu + ")");
         }

         this.sendDatagram(datagram, curTime);
      }

      this.channel.flush();
   }

   private EncapsulatedPacket[] createEncapsulated(ByteBuf buffer, RakNetPriority priority, RakNetReliability reliability, int orderingChannel) {
      int maxLength = this.adjustedMtu - 28 - 4;
      int splitId = 0;
      ByteBuf[] buffers;
      if (buffer.readableBytes() > maxLength) {
         switch (reliability) {
            case UNRELIABLE:
               reliability = RakNetReliability.RELIABLE;
               break;
            case UNRELIABLE_SEQUENCED:
               reliability = RakNetReliability.RELIABLE_SEQUENCED;
               break;
            case UNRELIABLE_WITH_ACK_RECEIPT:
               reliability = RakNetReliability.RELIABLE_WITH_ACK_RECEIPT;
         }

         int split = (buffer.readableBytes() - 1) / maxLength + 1;
         buffer.retain(split);
         buffers = new ByteBuf[split];

         for(int i = 0; i < split; ++i) {
            buffers[i] = buffer.readSlice(Math.min(maxLength, buffer.readableBytes()));
         }

         if (buffer.isReadable()) {
            throw new IllegalStateException("Buffer still has bytes to read!");
         }

         splitId = this.splitIndex++;
      } else {
         buffers = new ByteBuf[]{buffer.readRetainedSlice(buffer.readableBytes())};
      }

      int orderingIndex = 0;
      if (reliability.isOrdered()) {
         orderingIndex = this.orderWriteIndex[orderingChannel];
         this.orderWriteIndex[orderingChannel] = this.orderWriteIndex[orderingChannel] + 1 & 16777215;
      }

      EncapsulatedPacket[] packets = new EncapsulatedPacket[buffers.length];
      int i = 0;

      for(int parts = buffers.length; i < parts; ++i) {
         EncapsulatedPacket packet = new EncapsulatedPacket();
         packet.buffer = buffers[i];
         packet.orderingChannel = (short)orderingChannel;
         packet.orderingIndex = orderingIndex;
         packet.reliability = reliability;
         packet.priority = priority;
         if (reliability.isReliable()) {
            packet.reliabilityIndex = this.reliabilityWriteIndex;
            this.reliabilityWriteIndex = this.reliabilityWriteIndex + 1 & 16777215;
         }

         if (parts > 1) {
            packet.split = true;
            packet.partIndex = i;
            packet.partCount = parts;
            packet.partId = splitId;
         }

         packets[i] = packet;
      }

      return packets;
   }

   private void sendDatagram(RakNetDatagram datagram, long time) {
      Preconditions.checkArgument(!datagram.packets.isEmpty(), "RakNetDatagram with no packets");
      if (this.getRakNet().getMetrics() != null) {
         this.getRakNet().getMetrics().rakDatagramsOut(1, datagram.timesSent > 0);
      }

      try {
         int previousIndex = datagram.sequenceIndex;
         datagram.sequenceIndex = this.slidingWindow.getAndIncrementNextSequenceNumber();

         for(EncapsulatedPacket packet : datagram.packets) {
            if (packet.reliability.isReliable()) {
               datagram.nextSend = time + this.slidingWindow.getRtoForRetransmission();
               if (previousIndex == -1) {
                  this.unackedBytes += datagram.getSize();
               } else {
                  this.sentDatagrams.remove(previousIndex, datagram);
               }

               this.sentDatagrams.put(datagram.sequenceIndex, datagram.retain());
               break;
            }
         }

         ++datagram.timesSent;
         ByteBuf buf = this.allocateBuffer(datagram.getSize());
         Preconditions.checkArgument(buf.writableBytes() < this.adjustedMtu, "Packet length was %s but expected %s", buf.writableBytes(), this.adjustedMtu);
         datagram.encode(buf);
         this.channel.write(new DatagramPacket(buf, this.address));
      } finally {
         datagram.release();
      }

   }

   void sendDirect(ByteBuf buffer) {
      this.channel.writeAndFlush(new DatagramPacket(buffer, this.address));
   }

   public int getSessionTimeout() {
      return this.sessionTimeout;
   }

   public void setSessionTimeout(int timeout) {
      this.sessionTimeout = timeout;
   }

   private void onAcknowledge(ByteBuf buffer, Queue<IntRange> queue, boolean nack) {
      this.checkForClosed();
      int size = buffer.readUnsignedShort();
      int length = 0;

      for(int i = 0; i < size; ++i) {
         boolean singleton = buffer.readBoolean();
         int start = buffer.readUnsignedMediumLE();
         int end = singleton ? start : buffer.readUnsignedMediumLE();
         if (start > end) {
            if (log.isTraceEnabled()) {
               log.trace("{} sent an IntRange with a start value {} greater than an end value of {}", new Object[]{this.address, start, end});
            }

            this.disconnect(DisconnectReason.BAD_PACKET);
            return;
         }

         queue.offer(new IntRange(start, end));
         length += end - start + 1;
      }

      RakMetrics metrics = this.getRakNet().getMetrics();
      if (metrics != null) {
         if (nack) {
            metrics.nackIn(length);
         } else {
            metrics.ackIn(length);
         }
      }

   }

   private void onConnectedPing(ByteBuf buffer) {
      long pingTime = buffer.readLong();
      this.sendConnectedPong(pingTime);
   }

   private void onConnectedPong(ByteBuf buffer) {
      long pingTime = buffer.readLong();
      if (this.currentPingTime == pingTime) {
         this.lastPingTime = this.currentPingTime;
         this.lastPongTime = System.currentTimeMillis();
      }

   }

   private void onDisconnectionNotification() {
      this.close(DisconnectReason.CLOSED_BY_REMOTE_PEER);
   }

   private void sendConnectedPing(long pingTime) {
      ByteBuf buffer = this.allocateBuffer(9);
      buffer.writeByte(0);
      buffer.writeLong(pingTime);
      this.send(buffer, RakNetPriority.IMMEDIATE, RakNetReliability.UNRELIABLE);
      this.currentPingTime = pingTime;
   }

   private void sendConnectedPong(long pingTime) {
      ByteBuf buffer = this.allocateBuffer(17);
      buffer.writeByte(3);
      buffer.writeLong(pingTime);
      buffer.writeLong(System.currentTimeMillis());
      this.send(buffer, RakNetPriority.IMMEDIATE, RakNetReliability.UNRELIABLE);
   }

   private void sendDisconnectionNotification() {
      ByteBuf buffer = this.allocateBuffer(1);
      buffer.writeByte(21);
      this.send(buffer, RakNetPriority.IMMEDIATE, RakNetReliability.RELIABLE_ORDERED);
   }

   private void sendDetectLostConnection() {
      ByteBuf buffer = this.allocateBuffer(1);
      buffer.writeByte(4);
      this.send(buffer, RakNetPriority.IMMEDIATE);
   }

   private void touch() {
      this.checkForClosed();
      this.lastTouched = System.currentTimeMillis();
   }

   public boolean isStale(long curTime) {
      return curTime - this.lastTouched >= 5000L;
   }

   public boolean isStale() {
      return this.isStale(System.currentTimeMillis());
   }

   public boolean isTimedOut(long curTime) {
      return curTime - this.lastTouched >= (long)this.sessionTimeout;
   }

   public boolean isTimedOut() {
      return this.isTimedOut(System.currentTimeMillis());
   }

   private void checkForClosed() {
      Preconditions.checkState(!this.isClosed(), "Session already closed");
   }

   public boolean isClosed() {
      return this.closed;
   }

   public abstract RakNet getRakNet();

   boolean isIpv6Session() {
      return this.address.getAddress() instanceof Inet6Address;
   }

   public RakNetState getState() {
      return this.state;
   }

   void setState(@Nullable RakNetState state) {
      if (this.state != state) {
         this.state = state;
         if (this.listener != null) {
            this.listener.onSessionChangeState(this.state);
         }
      }

   }

   public Channel getChannel() {
      return this.channel;
   }

   public EventLoop getEventLoop() {
      return this.eventLoop;
   }

   public RakNetSessionListener getListener() {
      return this.listener;
   }

   public void setListener(RakNetSessionListener listener) {
      this.listener = listener;
   }
}
