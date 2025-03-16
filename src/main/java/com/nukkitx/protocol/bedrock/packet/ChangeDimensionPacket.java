package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3f;

public class ChangeDimensionPacket implements BedrockPacket {
   private int dimension;
   private Vector3f position;
   private boolean respawn;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CHANGE_DIMENSION;
   }

   public int getDimension() {
      return this.dimension;
   }

   public Vector3f getPosition() {
      return this.position;
   }

   public boolean isRespawn() {
      return this.respawn;
   }

   public void setDimension(int dimension) {
      this.dimension = dimension;
   }

   public void setPosition(Vector3f position) {
      this.position = position;
   }

   public void setRespawn(boolean respawn) {
      this.respawn = respawn;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ChangeDimensionPacket)) {
         return false;
      } else {
         ChangeDimensionPacket other = (ChangeDimensionPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.dimension != other.dimension) {
            return false;
         } else if (this.respawn != other.respawn) {
            return false;
         } else {
            Object this$position = this.position;
            Object other$position = other.position;
            if (this$position == null) {
               if (other$position != null) {
                  return false;
               }
            } else if (!this$position.equals(other$position)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ChangeDimensionPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.dimension;
      result = result * 59 + (this.respawn ? 79 : 97);
      Object $position = this.position;
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      return result;
   }

   public String toString() {
      return "ChangeDimensionPacket(dimension=" + this.dimension + ", position=" + this.position + ", respawn=" + this.respawn + ")";
   }
}
