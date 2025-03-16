package com.nukkitx.protocol.bedrock.packet;

import io.netty.buffer.ByteBuf;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.ReferenceCountUtil;

public class BedrockPacketWrapper extends AbstractReferenceCounted {
   private int packetId;
   private int senderSubClientId;
   private int targetSubClientId;
   private int headerLength;
   private BedrockPacket packet;
   private ByteBuf packetBuffer;

   public BedrockPacketWrapper(int packetId, int senderSubClientId, int targetSubClientId, BedrockPacket packet, ByteBuf packetBuffer) {
      this.packetId = packetId;
      this.senderSubClientId = senderSubClientId;
      this.targetSubClientId = targetSubClientId;
      this.packet = packet;
      this.packetBuffer = packetBuffer;
   }

   protected void deallocate() {
      ReferenceCountUtil.safeRelease(this.packet);
      ReferenceCountUtil.safeRelease(this.packetBuffer);
   }

   public BedrockPacketWrapper touch(Object hint) {
      ReferenceCountUtil.touch(this.packet);
      ReferenceCountUtil.touch(this.packetBuffer);
      return this;
   }

   public BedrockPacketWrapper retain() {
      return (BedrockPacketWrapper)super.retain();
   }

   public int getPacketId() {
      return this.packetId;
   }

   public int getSenderSubClientId() {
      return this.senderSubClientId;
   }

   public int getTargetSubClientId() {
      return this.targetSubClientId;
   }

   public int getHeaderLength() {
      return this.headerLength;
   }

   public BedrockPacket getPacket() {
      return this.packet;
   }

   public ByteBuf getPacketBuffer() {
      return this.packetBuffer;
   }

   public void setPacketId(int packetId) {
      this.packetId = packetId;
   }

   public void setSenderSubClientId(int senderSubClientId) {
      this.senderSubClientId = senderSubClientId;
   }

   public void setTargetSubClientId(int targetSubClientId) {
      this.targetSubClientId = targetSubClientId;
   }

   public void setHeaderLength(int headerLength) {
      this.headerLength = headerLength;
   }

   public void setPacket(BedrockPacket packet) {
      this.packet = packet;
   }

   public void setPacketBuffer(ByteBuf packetBuffer) {
      this.packetBuffer = packetBuffer;
   }

   public String toString() {
      return "BedrockPacketWrapper(packetId=" + this.getPacketId() + ", senderSubClientId=" + this.getSenderSubClientId() + ", targetSubClientId=" + this.getTargetSubClientId() + ", headerLength=" + this.getHeaderLength() + ", packet=" + this.getPacket() + ", packetBuffer=" + this.getPacketBuffer() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BedrockPacketWrapper)) {
         return false;
      } else {
         BedrockPacketWrapper other = (BedrockPacketWrapper)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getPacketId() != other.getPacketId()) {
            return false;
         } else if (this.getSenderSubClientId() != other.getSenderSubClientId()) {
            return false;
         } else if (this.getTargetSubClientId() != other.getTargetSubClientId()) {
            return false;
         } else if (this.getHeaderLength() != other.getHeaderLength()) {
            return false;
         } else {
            Object this$packet = this.getPacket();
            Object other$packet = other.getPacket();
            if (this$packet == null) {
               if (other$packet != null) {
                  return false;
               }
            } else if (!this$packet.equals(other$packet)) {
               return false;
            }

            Object this$packetBuffer = this.getPacketBuffer();
            Object other$packetBuffer = other.getPacketBuffer();
            if (this$packetBuffer == null) {
               if (other$packetBuffer != null) {
                  return false;
               }
            } else if (!this$packetBuffer.equals(other$packetBuffer)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof BedrockPacketWrapper;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getPacketId();
      result = result * 59 + this.getSenderSubClientId();
      result = result * 59 + this.getTargetSubClientId();
      result = result * 59 + this.getHeaderLength();
      Object $packet = this.getPacket();
      result = result * 59 + ($packet == null ? 43 : $packet.hashCode());
      Object $packetBuffer = this.getPacketBuffer();
      result = result * 59 + ($packetBuffer == null ? 43 : $packetBuffer.hashCode());
      return result;
   }

   public BedrockPacketWrapper(int packetId, int senderSubClientId, int targetSubClientId, int headerLength, BedrockPacket packet, ByteBuf packetBuffer) {
      this.packetId = packetId;
      this.senderSubClientId = senderSubClientId;
      this.targetSubClientId = targetSubClientId;
      this.headerLength = headerLength;
      this.packet = packet;
      this.packetBuffer = packetBuffer;
   }

   public BedrockPacketWrapper() {
   }
}
