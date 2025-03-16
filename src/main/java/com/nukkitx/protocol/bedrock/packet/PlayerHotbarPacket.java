package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class PlayerHotbarPacket implements BedrockPacket {
   private int selectedHotbarSlot;
   private int containerId;
   private boolean selectHotbarSlot;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.PLAYER_HOTBAR;
   }

   public int getSelectedHotbarSlot() {
      return this.selectedHotbarSlot;
   }

   public int getContainerId() {
      return this.containerId;
   }

   public boolean isSelectHotbarSlot() {
      return this.selectHotbarSlot;
   }

   public void setSelectedHotbarSlot(int selectedHotbarSlot) {
      this.selectedHotbarSlot = selectedHotbarSlot;
   }

   public void setContainerId(int containerId) {
      this.containerId = containerId;
   }

   public void setSelectHotbarSlot(boolean selectHotbarSlot) {
      this.selectHotbarSlot = selectHotbarSlot;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PlayerHotbarPacket)) {
         return false;
      } else {
         PlayerHotbarPacket other = (PlayerHotbarPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.selectedHotbarSlot != other.selectedHotbarSlot) {
            return false;
         } else if (this.containerId != other.containerId) {
            return false;
         } else {
            return this.selectHotbarSlot == other.selectHotbarSlot;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PlayerHotbarPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.selectedHotbarSlot;
      result = result * 59 + this.containerId;
      result = result * 59 + (this.selectHotbarSlot ? 79 : 97);
      return result;
   }

   public String toString() {
      return "PlayerHotbarPacket(selectedHotbarSlot=" + this.selectedHotbarSlot + ", containerId=" + this.containerId + ", selectHotbarSlot=" + this.selectHotbarSlot + ")";
   }
}
