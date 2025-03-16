package com.nukkitx.protocol.bedrock.data;

import org.cloudburstmc.nbt.NbtMap;

public final class BlockPropertyData {
   private final String name;
   private final NbtMap properties;

   public BlockPropertyData(String name, NbtMap properties) {
      this.name = name;
      this.properties = properties;
   }

   public String getName() {
      return this.name;
   }

   public NbtMap getProperties() {
      return this.properties;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BlockPropertyData)) {
         return false;
      } else {
         BlockPropertyData other = (BlockPropertyData)o;
         Object this$name = this.getName();
         Object other$name = other.getName();
         if (this$name == null) {
            if (other$name != null) {
               return false;
            }
         } else if (!this$name.equals(other$name)) {
            return false;
         }

         Object this$properties = this.getProperties();
         Object other$properties = other.getProperties();
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

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $properties = this.getProperties();
      result = result * 59 + ($properties == null ? 43 : $properties.hashCode());
      return result;
   }

   public String toString() {
      return "BlockPropertyData(name=" + this.getName() + ", properties=" + this.getProperties() + ")";
   }
}
