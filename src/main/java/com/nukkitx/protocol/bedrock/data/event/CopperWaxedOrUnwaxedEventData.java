package com.nukkitx.protocol.bedrock.data.event;

import com.nukkitx.protocol.bedrock.data.definitions.BlockDefinition;

public class CopperWaxedOrUnwaxedEventData implements EventData {
   private final BlockDefinition definition;

   public EventDataType getType() {
      return EventDataType.COPPER_WAXED_OR_UNWAXED;
   }

   public CopperWaxedOrUnwaxedEventData(BlockDefinition definition) {
      this.definition = definition;
   }

   public BlockDefinition getDefinition() {
      return this.definition;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CopperWaxedOrUnwaxedEventData)) {
         return false;
      } else {
         CopperWaxedOrUnwaxedEventData other = (CopperWaxedOrUnwaxedEventData)o;
         if (!other.canEqual(this)) {
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
      return other instanceof CopperWaxedOrUnwaxedEventData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $definition = this.getDefinition();
      result = result * 59 + ($definition == null ? 43 : $definition.hashCode());
      return result;
   }

   public String toString() {
      return "CopperWaxedOrUnwaxedEventData(definition=" + this.getDefinition() + ")";
   }
}
