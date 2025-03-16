package com.nukkitx.protocol.bedrock.data.command;

import java.util.Map;
import java.util.Set;

public final class CommandEnumData {
   private final String name;
   private final Map<String, Set<CommandEnumConstraint>> values;
   private final boolean isSoft;

   public CommandEnumData(String name, Map<String, Set<CommandEnumConstraint>> values, boolean isSoft) {
      this.name = name;
      this.values = values;
      this.isSoft = isSoft;
   }

   public String getName() {
      return this.name;
   }

   public Map<String, Set<CommandEnumConstraint>> getValues() {
      return this.values;
   }

   public boolean isSoft() {
      return this.isSoft;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CommandEnumData)) {
         return false;
      } else {
         CommandEnumData other = (CommandEnumData)o;
         if (this.isSoft() != other.isSoft()) {
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

            Object this$values = this.getValues();
            Object other$values = other.getValues();
            if (this$values == null) {
               if (other$values != null) {
                  return false;
               }
            } else if (!this$values.equals(other$values)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.isSoft() ? 79 : 97);
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $values = this.getValues();
      result = result * 59 + ($values == null ? 43 : $values.hashCode());
      return result;
   }

   public String toString() {
      return "CommandEnumData(name=" + this.getName() + ", values=" + this.getValues() + ", isSoft=" + this.isSoft() + ")";
   }
}
