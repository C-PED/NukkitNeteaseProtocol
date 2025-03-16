package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class EntityPickRequestPacket implements BedrockPacket {
   private long runtimeEntityId;
   private int hotbarSlot;
   private boolean withData;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ENTITY_PICK_REQUEST;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public int getHotbarSlot() {
      return this.hotbarSlot;
   }

   public boolean isWithData() {
      return this.withData;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setHotbarSlot(int hotbarSlot) {
      this.hotbarSlot = hotbarSlot;
   }

   public void setWithData(boolean withData) {
      this.withData = withData;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EntityPickRequestPacket)) {
         return false;
      } else {
         EntityPickRequestPacket other = (EntityPickRequestPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else if (this.hotbarSlot != other.hotbarSlot) {
            return false;
         } else {
            return this.withData == other.withData;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EntityPickRequestPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      result = result * 59 + this.hotbarSlot;
      result = result * 59 + (this.withData ? 79 : 97);
      return result;
   }

   public String toString() {
      return "EntityPickRequestPacket(runtimeEntityId=" + this.runtimeEntityId + ", hotbarSlot=" + this.hotbarSlot + ", withData=" + this.withData + ")";
   }
}
