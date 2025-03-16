package com.nukkitx.protocol.bedrock.codec.v389.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v388.serializer.EventSerializer_v388;
import com.nukkitx.protocol.bedrock.data.event.EventData;
import com.nukkitx.protocol.bedrock.data.event.EventDataType;
import com.nukkitx.protocol.bedrock.data.event.ExtractHoneyEventData;
import com.nukkitx.protocol.bedrock.data.event.PlayerDiedEventData;
import com.nukkitx.protocol.common.util.TriConsumer;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.function.BiFunction;

public class EventSerializer_v389 extends EventSerializer_v388 {
   public static final EventSerializer_v389 INSTANCE = new EventSerializer_v389();

   protected EventSerializer_v389() {
      this.readers.put(EventDataType.EXTRACT_HONEY, (BiFunction)(b, h) -> ExtractHoneyEventData.INSTANCE);
      this.writers.put(EventDataType.EXTRACT_HONEY, (TriConsumer)(b, h, e) -> {
      });
      this.writers.put(EventDataType.PLAYER_DIED, this::writePlayerDied);
      this.readers.put(EventDataType.PLAYER_DIED, this::readPlayerDied);
   }

   protected PlayerDiedEventData readPlayerDied(ByteBuf buffer, BedrockCodecHelper helper) {
      int attackerEntityId = VarInts.readInt(buffer);
      int attackerVariant = VarInts.readInt(buffer);
      int entityDamageCause = VarInts.readInt(buffer);
      boolean inRaid = buffer.readBoolean();
      return new PlayerDiedEventData(attackerEntityId, attackerVariant, entityDamageCause, inRaid);
   }

   protected void writePlayerDied(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
      PlayerDiedEventData event = (PlayerDiedEventData)eventData;
      VarInts.writeInt(buffer, event.getAttackerEntityId());
      VarInts.writeInt(buffer, event.getAttackerVariant());
      VarInts.writeInt(buffer, event.getEntityDamageCause());
      buffer.writeBoolean(event.isInRaid());
   }
}
