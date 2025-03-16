package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class SetLastHurtByPacket implements BedrockPacket {
   private int entityTypeId;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_LAST_HURT_BY;
   }

   public int getEntityTypeId() {
      return this.entityTypeId;
   }

   public void setEntityTypeId(int entityTypeId) {
      this.entityTypeId = entityTypeId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetLastHurtByPacket)) {
         return false;
      } else {
         SetLastHurtByPacket other = (SetLastHurtByPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.entityTypeId == other.entityTypeId;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetLastHurtByPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.entityTypeId;
      return result;
   }

   public String toString() {
      return "SetLastHurtByPacket(entityTypeId=" + this.entityTypeId + ")";
   }
}
