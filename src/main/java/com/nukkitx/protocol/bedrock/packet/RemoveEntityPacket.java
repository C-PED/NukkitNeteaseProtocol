package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class RemoveEntityPacket implements BedrockPacket {
   private long uniqueEntityId;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.REMOVE_ENTITY;
   }

   public long getUniqueEntityId() {
      return this.uniqueEntityId;
   }

   public void setUniqueEntityId(long uniqueEntityId) {
      this.uniqueEntityId = uniqueEntityId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RemoveEntityPacket)) {
         return false;
      } else {
         RemoveEntityPacket other = (RemoveEntityPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.uniqueEntityId == other.uniqueEntityId;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RemoveEntityPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $uniqueEntityId = this.uniqueEntityId;
      result = result * 59 + (int)($uniqueEntityId >>> 32 ^ $uniqueEntityId);
      return result;
   }

   public String toString() {
      return "RemoveEntityPacket(uniqueEntityId=" + this.uniqueEntityId + ")";
   }
}
