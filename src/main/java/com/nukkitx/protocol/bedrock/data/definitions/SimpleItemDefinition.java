package com.nukkitx.protocol.bedrock.data.definitions;

public class SimpleItemDefinition implements ItemDefinition {
   private final String identifier;
   private final int runtimeId;
   private final boolean componentBased;

   public SimpleItemDefinition(String identifier, int runtimeId, boolean componentBased) {
      this.identifier = identifier;
      this.runtimeId = runtimeId;
      this.componentBased = componentBased;
   }

   public String getIdentifier() {
      return this.identifier;
   }

   public int getRuntimeId() {
      return this.runtimeId;
   }

   public boolean isComponentBased() {
      return this.componentBased;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SimpleItemDefinition)) {
         return false;
      } else {
         SimpleItemDefinition other = (SimpleItemDefinition)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getRuntimeId() != other.getRuntimeId()) {
            return false;
         } else if (this.isComponentBased() != other.isComponentBased()) {
            return false;
         } else {
            Object this$identifier = this.getIdentifier();
            Object other$identifier = other.getIdentifier();
            if (this$identifier == null) {
               if (other$identifier != null) {
                  return false;
               }
            } else if (!this$identifier.equals(other$identifier)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SimpleItemDefinition;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getRuntimeId();
      result = result * 59 + (this.isComponentBased() ? 79 : 97);
      Object $identifier = this.getIdentifier();
      result = result * 59 + ($identifier == null ? 43 : $identifier.hashCode());
      return result;
   }

   public String toString() {
      return "SimpleItemDefinition(identifier=" + this.getIdentifier() + ", runtimeId=" + this.getRuntimeId() + ", componentBased=" + this.isComponentBased() + ")";
   }
}
