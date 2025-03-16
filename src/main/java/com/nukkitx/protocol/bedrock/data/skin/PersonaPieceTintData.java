package com.nukkitx.protocol.bedrock.data.skin;

import java.util.List;

public final class PersonaPieceTintData {
   private final String type;
   private final List<String> colors;

   public PersonaPieceTintData(String type, List<String> colors) {
      this.type = type;
      this.colors = colors;
   }

   public String getType() {
      return this.type;
   }

   public List<String> getColors() {
      return this.colors;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PersonaPieceTintData)) {
         return false;
      } else {
         PersonaPieceTintData other = (PersonaPieceTintData)o;
         Object this$type = this.getType();
         Object other$type = other.getType();
         if (this$type == null) {
            if (other$type != null) {
               return false;
            }
         } else if (!this$type.equals(other$type)) {
            return false;
         }

         Object this$colors = this.getColors();
         Object other$colors = other.getColors();
         if (this$colors == null) {
            if (other$colors != null) {
               return false;
            }
         } else if (!this$colors.equals(other$colors)) {
            return false;
         }

         return true;
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $colors = this.getColors();
      result = result * 59 + ($colors == null ? 43 : $colors.hashCode());
      return result;
   }

   public String toString() {
      return "PersonaPieceTintData(type=" + this.getType() + ", colors=" + this.getColors() + ")";
   }
}
