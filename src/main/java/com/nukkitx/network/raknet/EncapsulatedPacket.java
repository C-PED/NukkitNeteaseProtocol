package com.nukkitx.network.raknet;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCounted;

public class EncapsulatedPacket implements ReferenceCounted {
   RakNetReliability reliability;
   RakNetPriority priority;
   int reliabilityIndex;
   int sequenceIndex;
   int orderingIndex;
   short orderingChannel;
   boolean split;
   int partCount;
   int partId;
   int partIndex;
   ByteBuf buffer;
   boolean needsBAS;

   public void encode(ByteBuf buf) {
      int flags = this.reliability.ordinal() << 5;
      if (this.split) {
         flags |= 16;
      }

      if (this.needsBAS) {
         flags |= 4;
      }

      buf.writeByte(flags);
      buf.writeShort(this.buffer.readableBytes() << 3);
      if (this.reliability.isReliable()) {
         buf.writeMediumLE(this.reliabilityIndex);
      }

      if (this.reliability.isSequenced()) {
         buf.writeMediumLE(this.sequenceIndex);
      }

      if (this.reliability.isOrdered() || this.reliability.isSequenced()) {
         buf.writeMediumLE(this.orderingIndex);
         buf.writeByte(this.orderingChannel);
      }

      if (this.split) {
         buf.writeInt(this.partCount);
         buf.writeShort(this.partId);
         buf.writeInt(this.partIndex);
      }

      buf.writeBytes(this.buffer, this.buffer.readerIndex(), this.buffer.readableBytes());
   }

   public void decode(ByteBuf buf) {
      byte flags = buf.readByte();
      this.reliability = RakNetReliability.fromId((flags & 224) >> 5);
      this.split = (flags & 16) != 0;
      this.needsBAS = (flags & 4) != 0;
      int size = buf.readUnsignedShort() + 7 >> 3;
      if (this.reliability.isReliable()) {
         this.reliabilityIndex = buf.readUnsignedMediumLE();
      }

      if (this.reliability.isSequenced()) {
         this.sequenceIndex = buf.readUnsignedMediumLE();
      }

      if (this.reliability.isOrdered() || this.reliability.isSequenced()) {
         this.orderingIndex = buf.readUnsignedMediumLE();
         this.orderingChannel = buf.readUnsignedByte();
      }

      if (this.split) {
         this.partCount = buf.readInt();
         this.partId = buf.readUnsignedShort();
         this.partIndex = buf.readInt();
      }

      this.buffer = buf.readSlice(size);
   }

   public int getSize() {
      return 3 + this.reliability.getSize() + (this.split ? 10 : 0) + this.buffer.readableBytes();
   }

   public EncapsulatedPacket fromSplit(ByteBuf reassembled) {
      EncapsulatedPacket packet = new EncapsulatedPacket();
      packet.reliability = this.reliability;
      packet.reliabilityIndex = this.reliabilityIndex;
      packet.sequenceIndex = this.sequenceIndex;
      packet.orderingIndex = this.orderingIndex;
      packet.orderingChannel = this.orderingChannel;
      packet.buffer = reassembled;
      return packet;
   }

   public int refCnt() {
      return this.buffer.refCnt();
   }

   public EncapsulatedPacket retain() {
      this.buffer.retain();
      return this;
   }

   public EncapsulatedPacket retain(int i) {
      this.buffer.retain(i);
      return this;
   }

   public EncapsulatedPacket touch() {
      this.buffer.touch();
      return this;
   }

   public EncapsulatedPacket touch(Object o) {
      this.buffer.touch(o);
      return this;
   }

   public boolean release() {
      return this.buffer.release();
   }

   public boolean release(int i) {
      return this.buffer.release(i);
   }

   public RakNetReliability getReliability() {
      return this.reliability;
   }

   public RakNetPriority getPriority() {
      return this.priority;
   }

   public int getReliabilityIndex() {
      return this.reliabilityIndex;
   }

   public int getSequenceIndex() {
      return this.sequenceIndex;
   }

   public int getOrderingIndex() {
      return this.orderingIndex;
   }

   public short getOrderingChannel() {
      return this.orderingChannel;
   }

   public boolean isSplit() {
      return this.split;
   }

   public int getPartCount() {
      return this.partCount;
   }

   public int getPartId() {
      return this.partId;
   }

   public int getPartIndex() {
      return this.partIndex;
   }

   public ByteBuf getBuffer() {
      return this.buffer;
   }

   public boolean isNeedsBAS() {
      return this.needsBAS;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EncapsulatedPacket)) {
         return false;
      } else {
         EncapsulatedPacket other = (EncapsulatedPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getReliabilityIndex() != other.getReliabilityIndex()) {
            return false;
         } else if (this.getSequenceIndex() != other.getSequenceIndex()) {
            return false;
         } else if (this.getOrderingIndex() != other.getOrderingIndex()) {
            return false;
         } else if (this.getOrderingChannel() != other.getOrderingChannel()) {
            return false;
         } else if (this.isSplit() != other.isSplit()) {
            return false;
         } else if (this.getPartCount() != other.getPartCount()) {
            return false;
         } else if (this.getPartId() != other.getPartId()) {
            return false;
         } else if (this.getPartIndex() != other.getPartIndex()) {
            return false;
         } else if (this.isNeedsBAS() != other.isNeedsBAS()) {
            return false;
         } else {
            Object this$reliability = this.getReliability();
            Object other$reliability = other.getReliability();
            if (this$reliability == null) {
               if (other$reliability != null) {
                  return false;
               }
            } else if (!this$reliability.equals(other$reliability)) {
               return false;
            }

            Object this$priority = this.getPriority();
            Object other$priority = other.getPriority();
            if (this$priority == null) {
               if (other$priority != null) {
                  return false;
               }
            } else if (!this$priority.equals(other$priority)) {
               return false;
            }

            Object this$buffer = this.getBuffer();
            Object other$buffer = other.getBuffer();
            if (this$buffer == null) {
               if (other$buffer != null) {
                  return false;
               }
            } else if (!this$buffer.equals(other$buffer)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EncapsulatedPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getReliabilityIndex();
      result = result * 59 + this.getSequenceIndex();
      result = result * 59 + this.getOrderingIndex();
      result = result * 59 + this.getOrderingChannel();
      result = result * 59 + (this.isSplit() ? 79 : 97);
      result = result * 59 + this.getPartCount();
      result = result * 59 + this.getPartId();
      result = result * 59 + this.getPartIndex();
      result = result * 59 + (this.isNeedsBAS() ? 79 : 97);
      Object $reliability = this.getReliability();
      result = result * 59 + ($reliability == null ? 43 : $reliability.hashCode());
      Object $priority = this.getPriority();
      result = result * 59 + ($priority == null ? 43 : $priority.hashCode());
      Object $buffer = this.getBuffer();
      result = result * 59 + ($buffer == null ? 43 : $buffer.hashCode());
      return result;
   }

   public String toString() {
      return "EncapsulatedPacket(reliability=" + this.getReliability() + ", priority=" + this.getPriority() + ", reliabilityIndex=" + this.getReliabilityIndex() + ", sequenceIndex=" + this.getSequenceIndex() + ", orderingIndex=" + this.getOrderingIndex() + ", orderingChannel=" + this.getOrderingChannel() + ", split=" + this.isSplit() + ", partCount=" + this.getPartCount() + ", partId=" + this.getPartId() + ", partIndex=" + this.getPartIndex() + ", buffer=" + this.getBuffer() + ", needsBAS=" + this.isNeedsBAS() + ")";
   }
}
