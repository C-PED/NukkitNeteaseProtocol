package com.nukkitx.protocol.bedrock.data.event;

public class CarefulRestorationEventData implements EventData {
   public static final CarefulRestorationEventData INSTANCE = new CarefulRestorationEventData();

   public EventDataType getType() {
      return EventDataType.CAREFUL_RESTORATION;
   }

   private CarefulRestorationEventData() {
   }
}
