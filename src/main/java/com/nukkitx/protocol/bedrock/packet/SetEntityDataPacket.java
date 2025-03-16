package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.entity.EntityDataMap;
import com.nukkitx.protocol.bedrock.data.entity.EntityProperties;
import com.nukkitx.protocol.common.PacketSignal;

public class SetEntityDataPacket implements BedrockPacket {
   private final EntityDataMap metadata = new EntityDataMap();
   private long runtimeEntityId;
   private long tick;
   private final EntityProperties properties = new EntityProperties();

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_ENTITY_DATA;
   }

   public EntityDataMap getMetadata() {
      return this.metadata;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public long getTick() {
      return this.tick;
   }

   public EntityProperties getProperties() {
      return this.properties;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setTick(long tick) {
      this.tick = tick;
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
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else if (this.tick != other.tick) {
            return false;
         } else {
            Object this$metadata = this.metadata;
            Object other$metadata = other.metadata;
            if (this$metadata == null) {
               if (other$metadata != null) {
                  return false;
               }
            } else if (!this$metadata.equals(other$metadata)) {
               return false;
            }

            Object this$properties = this.properties;
            Object other$properties = other.properties;
            if (this$properties == null) {
               if (other$properties != null) {
                  return false;
               }
            } else if (!this$properties.equals(other$properties)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetEntityDataPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      long $tick = this.tick;
      result = result * 59 + (int)($tick >>> 32 ^ $tick);
      Object $metadata = this.metadata;
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $properties = this.properties;
      result = result * 59 + ($properties == null ? 43 : $properties.hashCode());
      return result;
   }

   public String toString() {
      return "SetEntityDataPacket(metadata=" + this.metadata + ", runtimeEntityId=" + this.runtimeEntityId + ", tick=" + this.tick + ", properties=" + this.properties + ")";
   }
}
