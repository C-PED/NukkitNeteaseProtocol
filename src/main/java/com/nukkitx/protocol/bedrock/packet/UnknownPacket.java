package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.common.PacketSignal;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.ReferenceCounted;

public final class UnknownPacket implements BedrockPacket, BedrockPacketSerializer<UnknownPacket>, ReferenceCounted {
   private int packetId;
   private ByteBuf payload;

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UnknownPacket packet) {
      buffer.writeBytes(packet.payload, packet.payload.readerIndex(), packet.payload.readableBytes());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UnknownPacket packet) {
      packet.payload = buffer.readRetainedSlice(buffer.readableBytes());
   }

   public String toString() {
      return "UNKNOWN - " + this.getPacketId() + " - Hex: " + (this.payload != null && this.payload.refCnt() != 0 ? ByteBufUtil.hexDump(this.payload) : "null");
   }

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return PacketSignal.UNHANDLED;
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.UNKNOWN;
   }

   public int refCnt() {
      return this.payload == null ? 0 : this.payload.refCnt();
   }

   public UnknownPacket retain() {
      if (this.payload != null) {
         this.payload.retain();
      }

      return this;
   }

   public UnknownPacket retain(int increment) {
      if (this.payload != null) {
         this.payload.retain(increment);
      }

      return this;
   }

   public UnknownPacket touch() {
      if (this.payload != null) {
         this.payload.touch();
      }

      return this;
   }

   public UnknownPacket touch(Object hint) {
      if (this.payload != null) {
         this.payload.touch(hint);
      }

      return this;
   }

   public boolean release() {
      return this.payload == null || this.payload.release();
   }

   public boolean release(int decrement) {
      return this.payload == null || this.payload.release(decrement);
   }

   public int getPacketId() {
      return this.packetId;
   }

   public ByteBuf getPayload() {
      return this.payload;
   }

   public void setPacketId(int packetId) {
      this.packetId = packetId;
   }

   public void setPayload(ByteBuf payload) {
      this.payload = payload;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UnknownPacket)) {
         return false;
      } else {
         UnknownPacket other = (UnknownPacket)o;
         if (this.packetId != other.packetId) {
            return false;
         } else {
            Object this$payload = this.payload;
            Object other$payload = other.payload;
            if (this$payload == null) {
               if (other$payload != null) {
                  return false;
               }
            } else if (!this$payload.equals(other$payload)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.packetId;
      Object $payload = this.payload;
      result = result * 59 + ($payload == null ? 43 : $payload.hashCode());
      return result;
   }
}
