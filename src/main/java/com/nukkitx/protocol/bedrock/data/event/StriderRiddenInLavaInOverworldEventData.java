package com.nukkitx.protocol.bedrock.data.event;

public class StriderRiddenInLavaInOverworldEventData implements EventData {
   public static final StriderRiddenInLavaInOverworldEventData INSTANCE = new StriderRiddenInLavaInOverworldEventData();

   public EventDataType getType() {
      return EventDataType.STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD;
   }

   private StriderRiddenInLavaInOverworldEventData() {
   }
}
