package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;

public class AddVolumeEntityPacket implements BedrockPacket {
   private int id;
   private NbtMap data;
   private String engineVersion;
   private String identifier;
   private String instanceName;
   private Vector3i minBounds;
   private Vector3i maxBounds;
   private int dimension;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ADD_VOLUME_ENTITY;
   }

   public int getId() {
      return this.id;
   }

   public NbtMap getData() {
      return this.data;
   }

   public String getEngineVersion() {
      return this.engineVersion;
   }

   public String getIdentifier() {
      return this.identifier;
   }

   public String getInstanceName() {
      return this.instanceName;
   }

   public Vector3i getMinBounds() {
      return this.minBounds;
   }

   public Vector3i getMaxBounds() {
      return this.maxBounds;
   }

   public int getDimension() {
      return this.dimension;
   }

   public void setId(int id) {
      this.id = id;
   }

   public void setData(NbtMap data) {
      this.data = data;
   }

   public void setEngineVersion(String engineVersion) {
      this.engineVersion = engineVersion;
   }

   public void setIdentifier(String identifier) {
      this.identifier = identifier;
   }

   public void setInstanceName(String instanceName) {
      this.instanceName = instanceName;
   }

   public void setMinBounds(Vector3i minBounds) {
      this.minBounds = minBounds;
   }

   public void setMaxBounds(Vector3i maxBounds) {
      this.maxBounds = maxBounds;
   }

   public void setDimension(int dimension) {
      this.dimension = dimension;
   }

   public String toString() {
      return "AddVolumeEntityPacket(id=" + this.getId() + ", data=" + this.getData() + ", engineVersion=" + this.getEngineVersion() + ", identifier=" + this.getIdentifier() + ", instanceName=" + this.getInstanceName() + ", minBounds=" + this.getMinBounds() + ", maxBounds=" + this.getMaxBounds() + ", dimension=" + this.getDimension() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AddVolumeEntityPacket)) {
         return false;
      } else {
         AddVolumeEntityPacket other = (AddVolumeEntityPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.id != other.id) {
            return false;
         } else if (this.dimension != other.dimension) {
            return false;
         } else {
            Object this$data = this.data;
            Object other$data = other.data;
            if (this$data == null) {
               if (other$data != null) {
                  return false;
               }
            } else if (!this$data.equals(other$data)) {
               return false;
            }

            Object this$engineVersion = this.engineVersion;
            Object other$engineVersion = other.engineVersion;
            if (this$engineVersion == null) {
               if (other$engineVersion != null) {
                  return false;
               }
            } else if (!this$engineVersion.equals(other$engineVersion)) {
               return false;
            }

            Object this$identifier = this.identifier;
            Object other$identifier = other.identifier;
            if (this$identifier == null) {
               if (other$identifier != null) {
                  return false;
               }
            } else if (!this$identifier.equals(other$identifier)) {
               return false;
            }

            Object this$instanceName = this.instanceName;
            Object other$instanceName = other.instanceName;
            if (this$instanceName == null) {
               if (other$instanceName != null) {
                  return false;
               }
            } else if (!this$instanceName.equals(other$instanceName)) {
               return false;
            }

            Object this$minBounds = this.minBounds;
            Object other$minBounds = other.minBounds;
            if (this$minBounds == null) {
               if (other$minBounds != null) {
                  return false;
               }
            } else if (!this$minBounds.equals(other$minBounds)) {
               return false;
            }

            Object this$maxBounds = this.maxBounds;
            Object other$maxBounds = other.maxBounds;
            if (this$maxBounds == null) {
               if (other$maxBounds != null) {
                  return false;
               }
            } else if (!this$maxBounds.equals(other$maxBounds)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AddVolumeEntityPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.id;
      result = result * 59 + this.dimension;
      Object $data = this.data;
      result = result * 59 + ($data == null ? 43 : $data.hashCode());
      Object $engineVersion = this.engineVersion;
      result = result * 59 + ($engineVersion == null ? 43 : $engineVersion.hashCode());
      Object $identifier = this.identifier;
      result = result * 59 + ($identifier == null ? 43 : $identifier.hashCode());
      Object $instanceName = this.instanceName;
      result = result * 59 + ($instanceName == null ? 43 : $instanceName.hashCode());
      Object $minBounds = this.minBounds;
      result = result * 59 + ($minBounds == null ? 43 : $minBounds.hashCode());
      Object $maxBounds = this.maxBounds;
      result = result * 59 + ($maxBounds == null ? 43 : $maxBounds.hashCode());
      return result;
   }
}
