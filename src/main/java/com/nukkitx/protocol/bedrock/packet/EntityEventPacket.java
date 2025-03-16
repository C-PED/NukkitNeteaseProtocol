package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.entity.EntityEventType;
import com.nukkitx.protocol.common.PacketSignal;

public class EntityEventPacket implements BedrockPacket {
   private long runtimeEntityId;
   private EntityEventType type;
   private int data;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ENTITY_EVENT;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public EntityEventType getType() {
      return this.type;
   }

   public int getData() {
      return this.data;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setType(EntityEventType type) {
      this.type = type;
   }

   public void setData(int data) {
      this.data = data;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EntityEventPacket)) {
         return false;
      } else {
         EntityEventPacket other = (EntityEventPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else if (this.data != other.data) {
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
      return other instanceof EntityEventPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      result = result * 59 + this.data;
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      return result;
   }

   public String toString() {
      return "EntityEventPacket(runtimeEntityId=" + this.runtimeEntityId + ", type=" + this.type + ", data=" + this.data + ")";
   }
}
