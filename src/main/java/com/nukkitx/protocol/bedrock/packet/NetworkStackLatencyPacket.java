package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class NetworkStackLatencyPacket implements BedrockPacket {
   private long timestamp;
   private boolean fromServer;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.NETWORK_STACK_LATENCY;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public boolean isFromServer() {
      return this.fromServer;
   }

   public void setTimestamp(long timestamp) {
      this.timestamp = timestamp;
   }

   public void setFromServer(boolean fromServer) {
      this.fromServer = fromServer;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NetworkStackLatencyPacket)) {
         return false;
      } else {
         NetworkStackLatencyPacket other = (NetworkStackLatencyPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.timestamp != other.timestamp) {
            return false;
         } else {
            return this.fromServer == other.fromServer;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof NetworkStackLatencyPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $timestamp = this.timestamp;
      result = result * 59 + (int)($timestamp >>> 32 ^ $timestamp);
      result = result * 59 + (this.fromServer ? 79 : 97);
      return result;
   }

   public String toString() {
      return "NetworkStackLatencyPacket(timestamp=" + this.timestamp + ", fromServer=" + this.fromServer + ")";
   }
}
