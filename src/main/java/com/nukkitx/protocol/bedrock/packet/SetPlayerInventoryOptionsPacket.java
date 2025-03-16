package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.InventoryLayout;
import com.nukkitx.protocol.bedrock.data.inventory.InventoryTabLeft;
import com.nukkitx.protocol.bedrock.data.inventory.InventoryTabRight;
import com.nukkitx.protocol.common.PacketSignal;

public class SetPlayerInventoryOptionsPacket implements BedrockPacket {
   private InventoryTabLeft leftTab;
   private InventoryTabRight rightTab;
   private boolean filtering;
   private InventoryLayout layout;
   private InventoryLayout craftingLayout;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_PLAYER_INVENTORY_OPTIONS;
   }

   public InventoryTabLeft getLeftTab() {
      return this.leftTab;
   }

   public InventoryTabRight getRightTab() {
      return this.rightTab;
   }

   public boolean isFiltering() {
      return this.filtering;
   }

   public InventoryLayout getLayout() {
      return this.layout;
   }

   public InventoryLayout getCraftingLayout() {
      return this.craftingLayout;
   }

   public void setLeftTab(InventoryTabLeft leftTab) {
      this.leftTab = leftTab;
   }

   public void setRightTab(InventoryTabRight rightTab) {
      this.rightTab = rightTab;
   }

   public void setFiltering(boolean filtering) {
      this.filtering = filtering;
   }

   public void setLayout(InventoryLayout layout) {
      this.layout = layout;
   }

   public void setCraftingLayout(InventoryLayout craftingLayout) {
      this.craftingLayout = craftingLayout;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetPlayerInventoryOptionsPacket)) {
         return false;
      } else {
         SetPlayerInventoryOptionsPacket other = (SetPlayerInventoryOptionsPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.filtering != other.filtering) {
            return false;
         } else {
            Object this$leftTab = this.leftTab;
            Object other$leftTab = other.leftTab;
            if (this$leftTab == null) {
               if (other$leftTab != null) {
                  return false;
               }
            } else if (!this$leftTab.equals(other$leftTab)) {
               return false;
            }

            Object this$rightTab = this.rightTab;
            Object other$rightTab = other.rightTab;
            if (this$rightTab == null) {
               if (other$rightTab != null) {
                  return false;
               }
            } else if (!this$rightTab.equals(other$rightTab)) {
               return false;
            }

            Object this$layout = this.layout;
            Object other$layout = other.layout;
            if (this$layout == null) {
               if (other$layout != null) {
                  return false;
               }
            } else if (!this$layout.equals(other$layout)) {
               return false;
            }

            Object this$craftingLayout = this.craftingLayout;
            Object other$craftingLayout = other.craftingLayout;
            if (this$craftingLayout == null) {
               if (other$craftingLayout != null) {
                  return false;
               }
            } else if (!this$craftingLayout.equals(other$craftingLayout)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetPlayerInventoryOptionsPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.filtering ? 79 : 97);
      Object $leftTab = this.leftTab;
      result = result * 59 + ($leftTab == null ? 43 : $leftTab.hashCode());
      Object $rightTab = this.rightTab;
      result = result * 59 + ($rightTab == null ? 43 : $rightTab.hashCode());
      Object $layout = this.layout;
      result = result * 59 + ($layout == null ? 43 : $layout.hashCode());
      Object $craftingLayout = this.craftingLayout;
      result = result * 59 + ($craftingLayout == null ? 43 : $craftingLayout.hashCode());
      return result;
   }

   public String toString() {
      return "SetPlayerInventoryOptionsPacket(leftTab=" + this.leftTab + ", rightTab=" + this.rightTab + ", filtering=" + this.filtering + ", layout=" + this.layout + ", craftingLayout=" + this.craftingLayout + ")";
   }
}
