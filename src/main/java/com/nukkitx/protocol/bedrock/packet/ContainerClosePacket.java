package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class ContainerClosePacket implements BedrockPacket {
   private byte id;
   private boolean serverInitiated;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CONTAINER_CLOSE;
   }

   public byte getId() {
      return this.id;
   }

   public boolean isServerInitiated() {
      return this.serverInitiated;
   }

   public void setId(byte id) {
      this.id = id;
   }

   public void setServerInitiated(boolean serverInitiated) {
      this.serverInitiated = serverInitiated;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ContainerClosePacket)) {
         return false;
      } else {
         ContainerClosePacket other = (ContainerClosePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.id != other.id) {
            return false;
         } else {
            return this.serverInitiated == other.serverInitiated;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ContainerClosePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.id;
      result = result * 59 + (this.serverInitiated ? 79 : 97);
      return result;
   }

   public String toString() {
      return "ContainerClosePacket(id=" + this.id + ", serverInitiated=" + this.serverInitiated + ")";
   }
}
