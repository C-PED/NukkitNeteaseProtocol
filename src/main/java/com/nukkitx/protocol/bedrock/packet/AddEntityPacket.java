package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.AttributeData;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataMap;
import com.nukkitx.protocol.bedrock.data.entity.EntityLinkData;
import com.nukkitx.protocol.bedrock.data.entity.EntityProperties;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;

public class AddEntityPacket implements BedrockPacket {
   private final List<AttributeData> attributes = new ObjectArrayList();
   private final EntityDataMap metadata = new EntityDataMap();
   private final List<EntityLinkData> entityLinks = new ObjectArrayList();
   private long uniqueEntityId;
   private long runtimeEntityId;
   private String identifier;
   private int entityType;
   private Vector3f position;
   private Vector3f motion;
   private Vector2f rotation;
   private float headRotation;
   private float bodyRotation;
   private final EntityProperties properties = new EntityProperties();

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ADD_ENTITY;
   }

   public List<AttributeData> getAttributes() {
      return this.attributes;
   }

   public EntityDataMap getMetadata() {
      return this.metadata;
   }

   public List<EntityLinkData> getEntityLinks() {
      return this.entityLinks;
   }

   public long getUniqueEntityId() {
      return this.uniqueEntityId;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public String getIdentifier() {
      return this.identifier;
   }

   public int getEntityType() {
      return this.entityType;
   }

   public Vector3f getPosition() {
      return this.position;
   }

   public Vector3f getMotion() {
      return this.motion;
   }

   public Vector2f getRotation() {
      return this.rotation;
   }

   public float getHeadRotation() {
      return this.headRotation;
   }

   public float getBodyRotation() {
      return this.bodyRotation;
   }

   public EntityProperties getProperties() {
      return this.properties;
   }

   public void setUniqueEntityId(long uniqueEntityId) {
      this.uniqueEntityId = uniqueEntityId;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setIdentifier(String identifier) {
      this.identifier = identifier;
   }

   public void setEntityType(int entityType) {
      this.entityType = entityType;
   }

   public void setPosition(Vector3f position) {
      this.position = position;
   }

   public void setMotion(Vector3f motion) {
      this.motion = motion;
   }

   public void setRotation(Vector2f rotation) {
      this.rotation = rotation;
   }

   public void setHeadRotation(float headRotation) {
      this.headRotation = headRotation;
   }

   public void setBodyRotation(float bodyRotation) {
      this.bodyRotation = bodyRotation;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AddEntityPacket)) {
         return false;
      } else {
         AddEntityPacket other = (AddEntityPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.uniqueEntityId != other.uniqueEntityId) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else if (this.entityType != other.entityType) {
            return false;
         } else if (Float.compare(this.headRotation, other.headRotation) != 0) {
            return false;
         } else if (Float.compare(this.bodyRotation, other.bodyRotation) != 0) {
            return false;
         } else {
            Object this$attributes = this.attributes;
            Object other$attributes = other.attributes;
            if (this$attributes == null) {
               if (other$attributes != null) {
                  return false;
               }
            } else if (!this$attributes.equals(other$attributes)) {
               return false;
            }

            Object this$metadata = this.metadata;
            Object other$metadata = other.metadata;
            if (this$metadata == null) {
               if (other$metadata != null) {
                  return false;
               }
            } else if (!this$metadata.equals(other$metadata)) {
               return false;
            }

            Object this$entityLinks = this.entityLinks;
            Object other$entityLinks = other.entityLinks;
            if (this$entityLinks == null) {
               if (other$entityLinks != null) {
                  return false;
               }
            } else if (!this$entityLinks.equals(other$entityLinks)) {
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

            Object this$position = this.position;
            Object other$position = other.position;
            if (this$position == null) {
               if (other$position != null) {
                  return false;
               }
            } else if (!this$position.equals(other$position)) {
               return false;
            }

            Object this$motion = this.motion;
            Object other$motion = other.motion;
            if (this$motion == null) {
               if (other$motion != null) {
                  return false;
               }
            } else if (!this$motion.equals(other$motion)) {
               return false;
            }

            Object this$rotation = this.rotation;
            Object other$rotation = other.rotation;
            if (this$rotation == null) {
               if (other$rotation != null) {
                  return false;
               }
            } else if (!this$rotation.equals(other$rotation)) {
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
      return other instanceof AddEntityPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $uniqueEntityId = this.uniqueEntityId;
      result = result * 59 + (int)($uniqueEntityId >>> 32 ^ $uniqueEntityId);
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      result = result * 59 + this.entityType;
      result = result * 59 + Float.floatToIntBits(this.headRotation);
      result = result * 59 + Float.floatToIntBits(this.bodyRotation);
      Object $attributes = this.attributes;
      result = result * 59 + ($attributes == null ? 43 : $attributes.hashCode());
      Object $metadata = this.metadata;
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $entityLinks = this.entityLinks;
      result = result * 59 + ($entityLinks == null ? 43 : $entityLinks.hashCode());
      Object $identifier = this.identifier;
      result = result * 59 + ($identifier == null ? 43 : $identifier.hashCode());
      Object $position = this.position;
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      Object $motion = this.motion;
      result = result * 59 + ($motion == null ? 43 : $motion.hashCode());
      Object $rotation = this.rotation;
      result = result * 59 + ($rotation == null ? 43 : $rotation.hashCode());
      Object $properties = this.properties;
      result = result * 59 + ($properties == null ? 43 : $properties.hashCode());
      return result;
   }

   public String toString() {
      return "AddEntityPacket(attributes=" + this.attributes + ", metadata=" + this.metadata + ", entityLinks=" + this.entityLinks + ", uniqueEntityId=" + this.uniqueEntityId + ", runtimeEntityId=" + this.runtimeEntityId + ", identifier=" + this.identifier + ", entityType=" + this.entityType + ", position=" + this.position + ", motion=" + this.motion + ", rotation=" + this.rotation + ", headRotation=" + this.headRotation + ", bodyRotation=" + this.bodyRotation + ", properties=" + this.properties + ")";
   }
}
