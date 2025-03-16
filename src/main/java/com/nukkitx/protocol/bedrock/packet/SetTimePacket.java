package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class SetTimePacket implements BedrockPacket {
   private int time;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_TIME;
   }

   public int getTime() {
      return this.time;
   }

   public void setTime(int time) {
      this.time = time;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetTimePacket)) {
         return false;
      } else {
         SetTimePacket other = (SetTimePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.time == other.time;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetTimePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.time;
      return result;
   }

   public String toString() {
      return "SetTimePacket(time=" + this.time + ")";
   }
}
