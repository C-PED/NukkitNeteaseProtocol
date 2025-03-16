package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class TickingAreasLoadStatusPacket implements BedrockPacket {
   boolean waitingForPreload;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.TICKING_AREAS_LOAD_STATUS;
   }

   public boolean isWaitingForPreload() {
      return this.waitingForPreload;
   }

   public void setWaitingForPreload(boolean waitingForPreload) {
      this.waitingForPreload = waitingForPreload;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TickingAreasLoadStatusPacket)) {
         return false;
      } else {
         TickingAreasLoadStatusPacket other = (TickingAreasLoadStatusPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.waitingForPreload == other.waitingForPreload;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof TickingAreasLoadStatusPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.waitingForPreload ? 79 : 97);
      return result;
   }

   public String toString() {
      return "TickingAreasLoadStatusPacket(waitingForPreload=" + this.waitingForPreload + ")";
   }
}
