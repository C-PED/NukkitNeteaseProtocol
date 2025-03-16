package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class RemoveVolumeEntityPacket implements BedrockPacket {
   private int id;
   private int dimension;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.REMOVE_VOLUME_ENTITY;
   }

   public int getId() {
      return this.id;
   }

   public int getDimension() {
      return this.dimension;
   }

   public void setId(int id) {
      this.id = id;
   }

   public void setDimension(int dimension) {
      this.dimension = dimension;
   }

   public String toString() {
      return "RemoveVolumeEntityPacket(id=" + this.getId() + ", dimension=" + this.getDimension() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RemoveVolumeEntityPacket)) {
         return false;
      } else {
         RemoveVolumeEntityPacket other = (RemoveVolumeEntityPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.id != other.id) {
            return false;
         } else {
            return this.dimension == other.dimension;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RemoveVolumeEntityPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.id;
      result = result * 59 + this.dimension;
      return result;
   }
}
