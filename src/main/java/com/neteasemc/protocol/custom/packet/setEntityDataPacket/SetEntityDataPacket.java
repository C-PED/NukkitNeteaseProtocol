package com.neteasemc.protocol.custom.packet.setEntityDataPacket;

import com.neteasemc.protocol.custom.GeyserBasePacket;
import com.neteasemc.protocol.custom.GeyserBasePacketHandler;
import com.neteasemc.protocol.custom.GeyserPacketType;
import com.nukkitx.protocol.common.PacketSignal;
import org.bukkit.entity.Player;

public class SetEntityDataPacket extends GeyserBasePacket {
   public int entityId;
   public float height = -1.0F;
   public float width = -1.0F;
   public float scale = -1.0F;

   public SetEntityDataPacket() {
   }

   public SetEntityDataPacket(int entityId, float scale) {
      this.entityId = entityId;
      this.scale = scale;
   }

   public SetEntityDataPacket(int entityId, float height, float width) {
      this.entityId = entityId;
      this.height = height;
      this.width = width;
   }

   public GeyserPacketType getPacketType() {
      return GeyserPacketType.SET_ENTITY_DATA;
   }

   public PacketSignal handle(GeyserBasePacketHandler handler) {
      return handler.handle(this);
   }

   public PacketSignal handle(GeyserBasePacketHandler handler, Player player) {
      return handler.handle(this, player);
   }

   public int getEntityId() {
      return this.entityId;
   }

   public float getHeight() {
      return this.height;
   }

   public float getWidth() {
      return this.width;
   }

   public float getScale() {
      return this.scale;
   }

   public void setEntityId(int entityId) {
      this.entityId = entityId;
   }

   public void setHeight(float height) {
      this.height = height;
   }

   public void setWidth(float width) {
      this.width = width;
   }

   public void setScale(float scale) {
      this.scale = scale;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetEntityDataPacket)) {
         return false;
      } else {
         SetEntityDataPacket other = (SetEntityDataPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.entityId != other.entityId) {
            return false;
         } else if (Float.compare(this.height, other.height) != 0) {
            return false;
         } else if (Float.compare(this.width, other.width) != 0) {
            return false;
         } else {
            return Float.compare(this.scale, other.scale) == 0;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetEntityDataPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.entityId;
      result = result * 59 + Float.floatToIntBits(this.height);
      result = result * 59 + Float.floatToIntBits(this.width);
      result = result * 59 + Float.floatToIntBits(this.scale);
      return result;
   }

   public String toString() {
      return "SetEntityDataPacket(entityId=" + this.getEntityId() + ", height=" + this.getHeight() + ", width=" + this.getWidth() + ", scale=" + this.getScale() + ")";
   }
}
