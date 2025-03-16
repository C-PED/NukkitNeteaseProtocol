package com.nukkitx.protocol.bedrock.data;

public final class ExperimentData {
   private final String name;
   private final boolean enabled;

   public ExperimentData(String name, boolean enabled) {
      this.name = name;
      this.enabled = enabled;
   }

   public String getName() {
      return this.name;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ExperimentData)) {
         return false;
      } else {
         ExperimentData other = (ExperimentData)o;
         if (this.isEnabled() != other.isEnabled()) {
            return false;
         } else {
            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.isEnabled() ? 79 : 97);
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      return result;
   }

   public String toString() {
      return "ExperimentData(name=" + this.getName() + ", enabled=" + this.isEnabled() + ")";
   }
}
