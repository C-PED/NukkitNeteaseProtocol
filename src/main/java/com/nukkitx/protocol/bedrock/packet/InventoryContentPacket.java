package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class InventoryContentPacket implements BedrockPacket {
   private List<ItemData> contents = new ObjectArrayList();
   private int containerId;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.INVENTORY_CONTENT;
   }

   public List<ItemData> getContents() {
      return this.contents;
   }

   public int getContainerId() {
      return this.containerId;
   }

   public void setContents(List<ItemData> contents) {
      this.contents = contents;
   }

   public void setContainerId(int containerId) {
      this.containerId = containerId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof InventoryContentPacket)) {
         return false;
      } else {
         InventoryContentPacket other = (InventoryContentPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.containerId != other.containerId) {
            return false;
         } else {
            Object this$contents = this.contents;
            Object other$contents = other.contents;
            if (this$contents == null) {
               if (other$contents != null) {
                  return false;
               }
            } else if (!this$contents.equals(other$contents)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof InventoryContentPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.containerId;
      Object $contents = this.contents;
      result = result * 59 + ($contents == null ? 43 : $contents.hashCode());
      return result;
   }

   public String toString() {
      return "InventoryContentPacket(contents=" + this.contents + ", containerId=" + this.containerId + ")";
   }
}
