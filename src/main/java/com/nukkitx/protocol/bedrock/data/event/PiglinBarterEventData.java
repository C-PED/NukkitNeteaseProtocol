package com.nukkitx.protocol.bedrock.data.event;

import com.nukkitx.protocol.bedrock.data.definitions.ItemDefinition;

public class PiglinBarterEventData implements EventData {
   private final ItemDefinition definition;
   private final boolean targetingPlayer;

   public EventDataType getType() {
      return EventDataType.PIGLIN_BARTER;
   }

   public PiglinBarterEventData(ItemDefinition definition, boolean targetingPlayer) {
      this.definition = definition;
      this.targetingPlayer = targetingPlayer;
   }

   public ItemDefinition getDefinition() {
      return this.definition;
   }

   public boolean isTargetingPlayer() {
      return this.targetingPlayer;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PiglinBarterEventData)) {
         return false;
      } else {
         PiglinBarterEventData other = (PiglinBarterEventData)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isTargetingPlayer() != other.isTargetingPlayer()) {
            return false;
         } else {
            Object this$definition = this.getDefinition();
            Object other$definition = other.getDefinition();
            if (this$definition == null) {
               if (other$definition != null) {
                  return false;
               }
            } else if (!this$definition.equals(other$definition)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PiglinBarterEventData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.isTargetingPlayer() ? 79 : 97);
      Object $definition = this.getDefinition();
      result = result * 59 + ($definition == null ? 43 : $definition.hashCode());
      return result;
   }

   public String toString() {
      return "PiglinBarterEventData(definition=" + this.getDefinition() + ", targetingPlayer=" + this.isTargetingPlayer() + ")";
   }
}
