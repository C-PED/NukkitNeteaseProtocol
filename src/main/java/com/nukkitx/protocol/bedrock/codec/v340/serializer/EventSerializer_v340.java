package com.nukkitx.protocol.bedrock.codec.v340.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.EventSerializer_v332;
import com.nukkitx.protocol.bedrock.data.event.EventData;
import com.nukkitx.protocol.bedrock.data.event.PetDiedEventData;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class EventSerializer_v340 extends EventSerializer_v332 {
   public static final EventSerializer_v340 INSTANCE = new EventSerializer_v340();

   protected PetDiedEventData readPetDied(ByteBuf buffer, BedrockCodecHelper helper) {
      boolean killedByOwner = buffer.readBoolean();
      long killerUniqueEntityId = VarInts.readLong(buffer);
      long petUniqueEntityId = VarInts.readLong(buffer);
      int entityDamageCause = VarInts.readInt(buffer);
      int petEntityType = VarInts.readInt(buffer);
      return new PetDiedEventData(killedByOwner, killerUniqueEntityId, petUniqueEntityId, entityDamageCause, petEntityType);
   }

   protected void writePetDied(ByteBuf buffer, BedrockCodecHelper helper, EventData eventData) {
      super.writePetDied(buffer, helper, eventData);
      VarInts.writeInt(buffer, ((PetDiedEventData)eventData).getPetEntityType());
   }

   protected EventSerializer_v340() {
   }
}
