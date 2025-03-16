package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class ChunkRadiusUpdatedPacket implements BedrockPacket {
   private int radius;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CHUNK_RADIUS_UPDATED;
   }

   public int getRadius() {
      return this.radius;
   }

   public void setRadius(int radius) {
      this.radius = radius;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ChunkRadiusUpdatedPacket)) {
         return false;
      } else {
         ChunkRadiusUpdatedPacket other = (ChunkRadiusUpdatedPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.radius == other.radius;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ChunkRadiusUpdatedPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.radius;
      return result;
   }

   public String toString() {
      return "ChunkRadiusUpdatedPacket(radius=" + this.radius + ")";
   }
}
