package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.common.PacketSignal;

public class MobArmorEquipmentPacket implements BedrockPacket {
   private long runtimeEntityId;
   private ItemData helmet;
   private ItemData chestplate;
   private ItemData leggings;
   private ItemData boots;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.MOB_ARMOR_EQUIPMENT;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public ItemData getHelmet() {
      return this.helmet;
   }

   public ItemData getChestplate() {
      return this.chestplate;
   }

   public ItemData getLeggings() {
      return this.leggings;
   }

   public ItemData getBoots() {
      return this.boots;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setHelmet(ItemData helmet) {
      this.helmet = helmet;
   }

   public void setChestplate(ItemData chestplate) {
      this.chestplate = chestplate;
   }

   public void setLeggings(ItemData leggings) {
      this.leggings = leggings;
   }

   public void setBoots(ItemData boots) {
      this.boots = boots;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MobArmorEquipmentPacket)) {
         return false;
      } else {
         MobArmorEquipmentPacket other = (MobArmorEquipmentPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else {
            Object this$helmet = this.helmet;
            Object other$helmet = other.helmet;
            if (this$helmet == null) {
               if (other$helmet != null) {
                  return false;
               }
            } else if (!this$helmet.equals(other$helmet)) {
               return false;
            }

            Object this$chestplate = this.chestplate;
            Object other$chestplate = other.chestplate;
            if (this$chestplate == null) {
               if (other$chestplate != null) {
                  return false;
               }
            } else if (!this$chestplate.equals(other$chestplate)) {
               return false;
            }

            Object this$leggings = this.leggings;
            Object other$leggings = other.leggings;
            if (this$leggings == null) {
               if (other$leggings != null) {
                  return false;
               }
            } else if (!this$leggings.equals(other$leggings)) {
               return false;
            }

            Object this$boots = this.boots;
            Object other$boots = other.boots;
            if (this$boots == null) {
               if (other$boots != null) {
                  return false;
               }
            } else if (!this$boots.equals(other$boots)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof MobArmorEquipmentPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      Object $helmet = this.helmet;
      result = result * 59 + ($helmet == null ? 43 : $helmet.hashCode());
      Object $chestplate = this.chestplate;
      result = result * 59 + ($chestplate == null ? 43 : $chestplate.hashCode());
      Object $leggings = this.leggings;
      result = result * 59 + ($leggings == null ? 43 : $leggings.hashCode());
      Object $boots = this.boots;
      result = result * 59 + ($boots == null ? 43 : $boots.hashCode());
      return result;
   }

   public String toString() {
      return "MobArmorEquipmentPacket(runtimeEntityId=" + this.runtimeEntityId + ", helmet=" + this.helmet + ", chestplate=" + this.chestplate + ", leggings=" + this.leggings + ", boots=" + this.boots + ")";
   }
}
