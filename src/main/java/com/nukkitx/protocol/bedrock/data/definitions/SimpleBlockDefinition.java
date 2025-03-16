package com.nukkitx.protocol.bedrock.data.definitions;

import com.nukkitx.protocol.common.NamedDefinition;
import java.util.TreeMap;
import org.cloudburstmc.nbt.NbtMap;

public class SimpleBlockDefinition implements BlockDefinition, NamedDefinition {
   private final String identifier;
   private final int runtimeId;
   private final NbtMap state;
   private transient String persistentIdentifier;

   public String getPersistentIdentifier() {
      if (this.persistentIdentifier == null) {
         StringBuilder builder = new StringBuilder(this.getIdentifier());
         if (!this.getState().isEmpty()) {
            TreeMap<String, String> properties = new TreeMap();
            NbtMap states = this.getState().getCompound("states");

            for(String stateName : states.keySet()) {
               String value = states.get(stateName).toString();
               properties.put(stateName, value);
            }

            properties.forEach((name, state) -> builder.append("|").append(name).append("=").append(state));
         }

         this.persistentIdentifier = builder.toString();
      }

      return this.persistentIdentifier;
   }

   public SimpleBlockDefinition(String identifier, int runtimeId, NbtMap state) {
      this.identifier = identifier;
      this.runtimeId = runtimeId;
      this.state = state;
   }

   public String getIdentifier() {
      return this.identifier;
   }

   public int getRuntimeId() {
      return this.runtimeId;
   }

   public NbtMap getState() {
      return this.state;
   }

   public void setPersistentIdentifier(String persistentIdentifier) {
      this.persistentIdentifier = persistentIdentifier;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SimpleBlockDefinition)) {
         return false;
      } else {
         SimpleBlockDefinition other = (SimpleBlockDefinition)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getRuntimeId() != other.getRuntimeId()) {
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

            Object this$state = this.getState();
            Object other$state = other.getState();
            if (this$state == null) {
               if (other$state != null) {
                  return false;
               }
            } else if (!this$state.equals(other$state)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SimpleBlockDefinition;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getRuntimeId();
      Object $identifier = this.getIdentifier();
      result = result * 59 + ($identifier == null ? 43 : $identifier.hashCode());
      Object $state = this.getState();
      result = result * 59 + ($state == null ? 43 : $state.hashCode());
      return result;
   }

   public String toString() {
      return "SimpleBlockDefinition(identifier=" + this.getIdentifier() + ", runtimeId=" + this.getRuntimeId() + ", state=" + this.getState() + ", persistentIdentifier=" + this.getPersistentIdentifier() + ")";
   }
}
