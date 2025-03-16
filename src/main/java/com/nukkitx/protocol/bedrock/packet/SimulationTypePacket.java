package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.SimulationType;
import com.nukkitx.protocol.common.PacketSignal;

public class SimulationTypePacket implements BedrockPacket {
   private SimulationType type;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SIMULATION_TYPE;
   }

   public SimulationType getType() {
      return this.type;
   }

   public void setType(SimulationType type) {
      this.type = type;
   }

   public String toString() {
      return "SimulationTypePacket(type=" + this.getType() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SimulationTypePacket)) {
         return false;
      } else {
         SimulationTypePacket other = (SimulationTypePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$type = this.type;
            Object other$type = other.type;
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SimulationTypePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      return result;
   }
}
