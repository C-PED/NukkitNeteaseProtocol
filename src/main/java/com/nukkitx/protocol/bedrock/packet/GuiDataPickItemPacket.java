package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class GuiDataPickItemPacket implements BedrockPacket {
   private String description;
   private String itemEffects;
   private int hotbarSlot;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.GUI_DATA_PICK_ITEM;
   }

   public String getDescription() {
      return this.description;
   }

   public String getItemEffects() {
      return this.itemEffects;
   }

   public int getHotbarSlot() {
      return this.hotbarSlot;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setItemEffects(String itemEffects) {
      this.itemEffects = itemEffects;
   }

   public void setHotbarSlot(int hotbarSlot) {
      this.hotbarSlot = hotbarSlot;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GuiDataPickItemPacket)) {
         return false;
      } else {
         GuiDataPickItemPacket other = (GuiDataPickItemPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.hotbarSlot != other.hotbarSlot) {
            return false;
         } else {
            Object this$description = this.description;
            Object other$description = other.description;
            if (this$description == null) {
               if (other$description != null) {
                  return false;
               }
            } else if (!this$description.equals(other$description)) {
               return false;
            }

            Object this$itemEffects = this.itemEffects;
            Object other$itemEffects = other.itemEffects;
            if (this$itemEffects == null) {
               if (other$itemEffects != null) {
                  return false;
               }
            } else if (!this$itemEffects.equals(other$itemEffects)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof GuiDataPickItemPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.hotbarSlot;
      Object $description = this.description;
      result = result * 59 + ($description == null ? 43 : $description.hashCode());
      Object $itemEffects = this.itemEffects;
      result = result * 59 + ($itemEffects == null ? 43 : $itemEffects.hashCode());
      return result;
   }

   public String toString() {
      return "GuiDataPickItemPacket(description=" + this.description + ", itemEffects=" + this.itemEffects + ", hotbarSlot=" + this.hotbarSlot + ")";
   }
}
