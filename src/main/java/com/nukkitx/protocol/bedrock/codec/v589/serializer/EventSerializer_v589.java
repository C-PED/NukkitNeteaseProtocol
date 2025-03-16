package com.nukkitx.protocol.bedrock.codec.v589.serializer;

import com.nukkitx.protocol.bedrock.codec.v471.serializer.EventSerializer_v471;
import com.nukkitx.protocol.bedrock.data.event.CarefulRestorationEventData;
import com.nukkitx.protocol.bedrock.data.event.EventDataType;
import com.nukkitx.protocol.common.util.TriConsumer;
import java.util.function.BiFunction;

public class EventSerializer_v589 extends EventSerializer_v471 {
   public static final EventSerializer_v589 INSTANCE = new EventSerializer_v589();

   public EventSerializer_v589() {
      this.readers.put(EventDataType.CAREFUL_RESTORATION, (BiFunction)(b, h) -> CarefulRestorationEventData.INSTANCE);
      this.writers.put(EventDataType.CAREFUL_RESTORATION, (TriConsumer)(b, h, e) -> {
      });
   }
}
