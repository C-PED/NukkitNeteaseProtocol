package com.nukkitx.protocol.bedrock.data.event;

public class AgentCreatedEventData implements EventData {
   public static final AgentCreatedEventData INSTANCE = new AgentCreatedEventData();

   public EventDataType getType() {
      return EventDataType.AGENT_CREATED;
   }

   private AgentCreatedEventData() {
   }
}
