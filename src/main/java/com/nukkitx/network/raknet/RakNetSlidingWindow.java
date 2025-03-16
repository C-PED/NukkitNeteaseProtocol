package com.nukkitx.network.raknet;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class RakNetSlidingWindow {
   private static final InternalLogger log = InternalLoggerFactory.getInstance(RakNetSession.class);
   private final int mtu;
   private final int adjustedMtu;
   private double cwnd;
   private double ssThresh;
   private double estimatedRTT = (double)-1.0F;
   private double lastRTT = (double)-1.0F;
   private double deviationRTT = (double)-1.0F;
   private long oldestUnsentAck;
   private long nextCongestionControlBlock;
   private boolean backoffThisBlock;
   private boolean isContinuousSend;
   private int expectedNextSequenceNumber;
   private int nextSequenceNumber;

   public RakNetSlidingWindow(int mtu, int adjustedMtu) {
      this.mtu = mtu;
      this.cwnd = (double)mtu;
      this.adjustedMtu = adjustedMtu;
   }

   public int getRetransmissionBandwidth(int unAckedBytes, boolean isContinuousSend) {
      return unAckedBytes;
   }

   public int getTransmissionBandwidth(int unAckedBytes, boolean isContinuousSend) {
      this.isContinuousSend = isContinuousSend;
      return (double)unAckedBytes <= this.cwnd ? (int)(this.cwnd - (double)unAckedBytes) : 0;
   }

   public int onPacketReceived(long curTime, int sequenceNumber) {
      if (this.oldestUnsentAck == 0L) {
         this.oldestUnsentAck = curTime;
      }

      int skippedMessageCount = 0;
      if (sequenceNumber == this.expectedNextSequenceNumber) {
         this.expectedNextSequenceNumber = sequenceNumber + 1 & 16777215;
      } else if (sequenceNumber > this.expectedNextSequenceNumber || this.expectedNextSequenceNumber - sequenceNumber > 8388607) {
         skippedMessageCount = sequenceNumber - this.expectedNextSequenceNumber & 16777215;
         if (skippedMessageCount > 1000) {
            if (skippedMessageCount > 50000) {
               log.info("too many stale data:" + skippedMessageCount);
               return -1;
            }

            skippedMessageCount = 1000;
         }

         this.expectedNextSequenceNumber = sequenceNumber + 1 & 16777215;
      }

      return skippedMessageCount;
   }

   public void onResend(long nextActionTime) {
      if (this.isContinuousSend && !this.backoffThisBlock && this.cwnd > (double)(this.adjustedMtu * 2)) {
         this.ssThresh = this.cwnd / (double)2.0F;
         if (this.ssThresh < (double)this.adjustedMtu) {
            this.ssThresh = (double)this.adjustedMtu;
         }

         this.cwnd = (double)this.adjustedMtu;
         this.nextCongestionControlBlock = (long)this.nextSequenceNumber;
         this.backoffThisBlock = true;
      }

   }

   public void onNak() {
      if (this.isContinuousSend && !this.backoffThisBlock) {
         this.ssThresh = this.cwnd / (double)2.0F;
         this.nextCongestionControlBlock = (long)this.nextSequenceNumber;
         this.backoffThisBlock = true;
      }

   }

   public synchronized int getAndIncrementNextSequenceNumber() {
      int r = this.nextSequenceNumber;
      this.nextSequenceNumber = this.nextSequenceNumber + 1 & 16777215;
      return r;
   }

   public void onAck(long rtt, long sequenceIndex, boolean isContinuousSend) {
      this.lastRTT = (double)rtt;
      if (this.estimatedRTT == (double)-1.0F) {
         this.estimatedRTT = (double)rtt;
         this.deviationRTT = (double)rtt;
      } else {
         double d = 0.05;
         double difference = (double)rtt - this.estimatedRTT;
         this.estimatedRTT += d * difference;
         this.deviationRTT += d * (Math.abs(difference) - this.deviationRTT);
      }

      this.isContinuousSend = isContinuousSend;
      if (isContinuousSend) {
         boolean isNewCongestionControlPeriod = sequenceIndex > this.nextCongestionControlBlock || this.nextCongestionControlBlock - sequenceIndex > 8388607L;
         if (isNewCongestionControlPeriod) {
            this.backoffThisBlock = false;
            this.nextCongestionControlBlock = (long)this.nextSequenceNumber;
         }

         if (this.isInSlowStart()) {
            this.cwnd += (double)this.mtu;
            if (this.cwnd > this.ssThresh && this.ssThresh != (double)0.0F) {
               this.cwnd = this.ssThresh + (double)(this.mtu * this.mtu) / this.cwnd;
            }
         } else {
            this.cwnd += (double)(this.mtu * this.mtu) / this.cwnd;
         }

         this.cwnd = this.cwnd > (double)Integer.MAX_VALUE ? (double)Integer.MAX_VALUE : this.cwnd;
      }
   }

   public boolean isInSlowStart() {
      return this.cwnd <= this.ssThresh || this.ssThresh == (double)0.0F;
   }

   public void onSendAck() {
      this.oldestUnsentAck = 0L;
   }

   public long getRtoForRetransmission() {
      if (this.estimatedRTT == (double)-1.0F) {
         return 2000L;
      } else {
         long threshold = (long)((double)2.0F * this.estimatedRTT + (double)4.0F * this.deviationRTT + (double)30.0F);
         return threshold > 2000L ? 2000L : threshold;
      }
   }

   public double getRTT() {
      return this.estimatedRTT;
   }

   public boolean shouldSendAcks(long curTime) {
      long rto = this.getSenderRtoForAck();
      return rto == -1L || curTime >= this.oldestUnsentAck + 10L;
   }

   public long getSenderRtoForAck() {
      return this.lastRTT == (double)-1.0F ? -1L : (long)(this.lastRTT + (double)10.0F);
   }

   public double getCwnd() {
      return this.cwnd;
   }
}
