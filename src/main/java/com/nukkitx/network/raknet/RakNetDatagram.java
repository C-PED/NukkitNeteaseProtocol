package com.nukkitx.network.raknet;

import io.netty.buffer.ByteBuf;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class RakNetDatagram extends AbstractReferenceCounted {
   private static final InternalLogger log = InternalLoggerFactory.getInstance(RakNetDatagram.class);
   final List<EncapsulatedPacket> packets = new ArrayList();
   final long sendTime;
   long nextSend;
   int timesSent;
   boolean isValid = true;
   boolean isContinuousSend;
   int sequenceIndex = -1;

   public RakNetDatagram retain() {
      super.retain();
      return this;
   }

   public RakNetDatagram retain(int increment) {
      super.retain(increment);
      return this;
   }

   public RakNetDatagram touch(Object hint) {
      for(EncapsulatedPacket packet : this.packets) {
         packet.touch(hint);
      }

      return this;
   }

   void decode(ByteBuf buf) {
      byte flags = buf.readByte();
      this.isValid = (flags & -128) != 0;
      this.isContinuousSend = (flags & 8) != 0;
      this.sequenceIndex = buf.readUnsignedMediumLE();

      while(buf.isReadable()) {
         EncapsulatedPacket packet = new EncapsulatedPacket();
         packet.decode(buf);
         this.packets.add(packet);
      }

   }

   public void encode(ByteBuf buf) {
      byte flags = 0;
      if (this.isContinuousSend) {
         flags = (byte)(flags | 8);
      }

      if (this.isValid) {
         flags |= -128;
      }

      buf.writeByte(flags);
      buf.writeMediumLE(this.sequenceIndex);

      for(EncapsulatedPacket packet : this.packets) {
         packet.encode(buf);
      }

   }

   boolean tryAddPacket(EncapsulatedPacket packet, int mtu) {
      if (this.getSize() + packet.getSize() > mtu - 4) {
         return false;
      } else {
         this.packets.add(packet);
         return true;
      }
   }

   public boolean release() {
      return super.release();
   }

   protected void deallocate() {
      for(EncapsulatedPacket packet : this.packets) {
         packet.release();
      }

   }

   public int getSize() {
      int size = 4;

      for(EncapsulatedPacket packet : this.packets) {
         size += packet.getSize();
      }

      return size;
   }

   public RakNetDatagram(long sendTime) {
      this.sendTime = sendTime;
   }

   public List<EncapsulatedPacket> getPackets() {
      return this.packets;
   }

   public long getSendTime() {
      return this.sendTime;
   }

   public long getNextSend() {
      return this.nextSend;
   }

   public int getTimesSent() {
      return this.timesSent;
   }

   public boolean isValid() {
      return this.isValid;
   }

   public boolean isContinuousSend() {
      return this.isContinuousSend;
   }

   public int getSequenceIndex() {
      return this.sequenceIndex;
   }

   public void setNextSend(long nextSend) {
      this.nextSend = nextSend;
   }

   public void setTimesSent(int timesSent) {
      this.timesSent = timesSent;
   }

   public void setValid(boolean isValid) {
      this.isValid = isValid;
   }

   public void setContinuousSend(boolean isContinuousSend) {
      this.isContinuousSend = isContinuousSend;
   }

   public void setSequenceIndex(int sequenceIndex) {
      this.sequenceIndex = sequenceIndex;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RakNetDatagram)) {
         return false;
      } else {
         RakNetDatagram other = (RakNetDatagram)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getSendTime() != other.getSendTime()) {
            return false;
         } else if (this.getNextSend() != other.getNextSend()) {
            return false;
         } else if (this.getTimesSent() != other.getTimesSent()) {
            return false;
         } else if (this.isValid() != other.isValid()) {
            return false;
         } else if (this.isContinuousSend() != other.isContinuousSend()) {
            return false;
         } else if (this.getSequenceIndex() != other.getSequenceIndex()) {
            return false;
         } else {
            Object this$packets = this.getPackets();
            Object other$packets = other.getPackets();
            if (this$packets == null) {
               if (other$packets != null) {
                  return false;
               }
            } else if (!this$packets.equals(other$packets)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RakNetDatagram;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $sendTime = this.getSendTime();
      result = result * 59 + (int)($sendTime >>> 32 ^ $sendTime);
      long $nextSend = this.getNextSend();
      result = result * 59 + (int)($nextSend >>> 32 ^ $nextSend);
      result = result * 59 + this.getTimesSent();
      result = result * 59 + (this.isValid() ? 79 : 97);
      result = result * 59 + (this.isContinuousSend() ? 79 : 97);
      result = result * 59 + this.getSequenceIndex();
      Object $packets = this.getPackets();
      result = result * 59 + ($packets == null ? 43 : $packets.hashCode());
      return result;
   }

   public String toString() {
      return "RakNetDatagram(packets=" + this.getPackets() + ", sendTime=" + this.getSendTime() + ", nextSend=" + this.getNextSend() + ", timesSent=" + this.getTimesSent() + ", isValid=" + this.isValid() + ", isContinuousSend=" + this.isContinuousSend() + ", sequenceIndex=" + this.getSequenceIndex() + ")";
   }
}
