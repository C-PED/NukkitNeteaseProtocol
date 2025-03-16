package com.nukkitx.protocol.bedrock.data.inventory;

import org.cloudburstmc.nbt.NbtMap;

public final class ComponentItemData {
   private final String name;
   private final NbtMap data;

   public ComponentItemData(String name, NbtMap data) {
      this.name = name;
      this.data = data;
   }

   public String getName() {
      return this.name;
   }

   public NbtMap getData() {
      return this.data;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ComponentItemData)) {
         return false;
      } else {
         ComponentItemData other = (ComponentItemData)o;
         Object this$name = this.getName();
         Object other$name = other.getName();
         if (this$name == null) {
            if (other$name != null) {
               return false;
            }
         } else if (!this$name.equals(other$name)) {
            return false;
         }

         Object this$data = this.getData();
         Object other$data = other.getData();
         if (this$data == null) {
            if (other$data != null) {
               return false;
            }
         } else if (!this$data.equals(other$data)) {
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
      Object $data = this.getData();
      result = result * 59 + ($data == null ? 43 : $data.hashCode());
      return result;
   }

   public String toString() {
      return "ComponentItemData(name=" + this.getName() + ", data=" + this.getData() + ")";
   }
}
