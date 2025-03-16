package com.nukkitx.protocol.bedrock.data.event;

public final class EntityDefinitionTriggerEventData implements EventData {
   private final String eventName;

   public EventDataType getType() {
      return EventDataType.ENTITY_DEFINITION_TRIGGER;
   }

   public EntityDefinitionTriggerEventData(String eventName) {
      this.eventName = eventName;
   }

   public String getEventName() {
      return this.eventName;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EntityDefinitionTriggerEventData)) {
         return false;
      } else {
         EntityDefinitionTriggerEventData other = (EntityDefinitionTriggerEventData)o;
         Object this$eventName = this.getEventName();
         Object other$eventName = other.getEventName();
         if (this$eventName == null) {
            if (other$eventName != null) {
               return false;
            }
         } else if (!this$eventName.equals(other$eventName)) {
            return false;
         }

         return true;
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $eventName = this.getEventName();
      result = result * 59 + ($eventName == null ? 43 : $eventName.hashCode());
      return result;
   }

   public String toString() {
      return "EntityDefinitionTriggerEventData(eventName=" + this.getEventName() + ")";
   }
}
