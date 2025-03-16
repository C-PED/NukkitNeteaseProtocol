package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class RequestChunkRadiusPacket implements BedrockPacket {
   private int radius;
   private int maxRadius;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.REQUEST_CHUNK_RADIUS;
   }

   public int getRadius() {
      return this.radius;
   }

   public int getMaxRadius() {
      return this.maxRadius;
   }

   public void setRadius(int radius) {
      this.radius = radius;
   }

   public void setMaxRadius(int maxRadius) {
      this.maxRadius = maxRadius;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RequestChunkRadiusPacket)) {
         return false;
      } else {
         RequestChunkRadiusPacket other = (RequestChunkRadiusPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.radius != other.radius) {
            return false;
         } else {
            return this.maxRadius == other.maxRadius;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RequestChunkRadiusPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.radius;
      result = result * 59 + this.maxRadius;
      return result;
   }

   public String toString() {
      return "RequestChunkRadiusPacket(radius=" + this.radius + ", maxRadius=" + this.maxRadius + ")";
   }
}
