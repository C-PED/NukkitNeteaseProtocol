package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3i;

public class OpenSignPacket implements BedrockPacket {
   private Vector3i position;
   private boolean frontSide;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.OPEN_SIGN;
   }

   public Vector3i getPosition() {
      return this.position;
   }

   public boolean isFrontSide() {
      return this.frontSide;
   }

   public void setPosition(Vector3i position) {
      this.position = position;
   }

   public void setFrontSide(boolean frontSide) {
      this.frontSide = frontSide;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof OpenSignPacket)) {
         return false;
      } else {
         OpenSignPacket other = (OpenSignPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.frontSide != other.frontSide) {
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
      return other instanceof OpenSignPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.frontSide ? 79 : 97);
      Object $position = this.position;
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      return result;
   }

   public String toString() {
      return "OpenSignPacket(position=" + this.position + ", frontSide=" + this.frontSide + ")";
   }
}
