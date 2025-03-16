package com.nukkitx.protocol.bedrock.data.entity;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public final class EntityProperties {
   private final List<IntEntityProperty> intProperties = new ObjectArrayList();
   private final List<FloatEntityProperty> floatProperties = new ObjectArrayList();

   public List<IntEntityProperty> getIntProperties() {
      return this.intProperties;
   }

   public List<FloatEntityProperty> getFloatProperties() {
      return this.floatProperties;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EntityProperties)) {
         return false;
      } else {
         EntityProperties other = (EntityProperties)o;
         Object this$intProperties = this.getIntProperties();
         Object other$intProperties = other.getIntProperties();
         if (this$intProperties == null) {
            if (other$intProperties != null) {
               return false;
            }
         } else if (!this$intProperties.equals(other$intProperties)) {
            return false;
         }

         Object this$floatProperties = this.getFloatProperties();
         Object other$floatProperties = other.getFloatProperties();
         if (this$floatProperties == null) {
            if (other$floatProperties != null) {
               return false;
            }
         } else if (!this$floatProperties.equals(other$floatProperties)) {
            return false;
         }

         return true;
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $intProperties = this.getIntProperties();
      result = result * 59 + ($intProperties == null ? 43 : $intProperties.hashCode());
      Object $floatProperties = this.getFloatProperties();
      result = result * 59 + ($floatProperties == null ? 43 : $floatProperties.hashCode());
      return result;
   }

   public String toString() {
      return "EntityProperties(intProperties=" + this.getIntProperties() + ", floatProperties=" + this.getFloatProperties() + ")";
   }
}
