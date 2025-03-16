package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import java.util.Arrays;

public class GeyserTransferPacket implements BedrockPacket {
   private byte[] transferData;

   public GeyserTransferPacket() {
   }

   public GeyserTransferPacket(byte[] data) {
      this.transferData = data;
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.GEYSER_TRANSFER;
   }

   public PacketSignal handle(BedrockPacketHandler handler) {
      throw new UnsupportedOperationException("Unimplemented method 'handle'");
   }

   public byte[] getTransferData() {
      return this.transferData;
   }

   public void setTransferData(byte[] transferData) {
      this.transferData = transferData;
   }

   public String toString() {
      return "GeyserTransferPacket(transferData=" + Arrays.toString(this.getTransferData()) + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GeyserTransferPacket)) {
         return false;
      } else {
         GeyserTransferPacket other = (GeyserTransferPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return Arrays.equals(this.transferData, other.transferData);
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof GeyserTransferPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + Arrays.hashCode(this.transferData);
      return result;
   }
}
