package com.nukkitx.protocol.bedrock.codec.v332;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.AvailableCommandsSerializer_v291;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import com.nukkitx.protocol.bedrock.codec.v313.Bedrock_v313;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.AddPaintingSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.EventSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.NetworkStackLatencySerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.ResourcePacksInfoSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.SpawnParticleEffectSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.StartGameSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.TextSerializer_v332;
import com.nukkitx.protocol.bedrock.data.LevelEvent;
import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.data.command.CommandParam;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataFormat;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataTypes;
import com.nukkitx.protocol.bedrock.packet.AddPaintingPacket;
import com.nukkitx.protocol.bedrock.packet.AvailableCommandsPacket;
import com.nukkitx.protocol.bedrock.packet.EventPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventPacket;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEventPacket;
import com.nukkitx.protocol.bedrock.packet.NetworkStackLatencyPacket;
import com.nukkitx.protocol.bedrock.packet.ResourcePacksInfoPacket;
import com.nukkitx.protocol.bedrock.packet.SpawnParticleEffectPacket;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import com.nukkitx.protocol.bedrock.packet.TextPacket;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v332 extends Bedrock_v313 {
   protected static final TypeMap<CommandParam> COMMAND_PARAMS;
   protected static final EntityDataTypeMap ENTITY_DATA;
   protected static final TypeMap<LevelEventType> LEVEL_EVENTS;
   public static final BedrockCodec CODEC;

   static {
      COMMAND_PARAMS = Bedrock_v313.COMMAND_PARAMS.toBuilder().insert(15, CommandParam.FILE_PATH).shift(26, 2).build();
      ENTITY_DATA = Bedrock_v313.ENTITY_DATA.toBuilder().insert(EntityDataTypes.AREA_EFFECT_CLOUD_DURATION, 94, EntityDataFormat.INT).insert(EntityDataTypes.AREA_EFFECT_CLOUD_SPAWN_TIME, 95, EntityDataFormat.INT).insert(EntityDataTypes.AREA_EFFECT_CLOUD_CHANGE_RATE, 96, EntityDataFormat.FLOAT).insert(EntityDataTypes.AREA_EFFECT_CLOUD_CHANGE_ON_PICKUP, 97, EntityDataFormat.FLOAT).insert(EntityDataTypes.AREA_EFFECT_CLOUD_PICKUP_COUNT, 98, EntityDataFormat.INT).build();
      LEVEL_EVENTS = Bedrock_v313.LEVEL_EVENTS.toBuilder().insert(3512, LevelEvent.CAULDRON_FILL_LAVA).insert(3513, LevelEvent.CAULDRON_TAKE_LAVA).build();
      CODEC = Bedrock_v313.CODEC.toBuilder().protocolVersion(332).minecraftVersion("1.9.0").helper(() -> new BedrockCodecHelper_v332(ENTITY_DATA, GAME_RULE_TYPES)).updateSerializer(ResourcePacksInfoPacket.class, ResourcePacksInfoSerializer_v332.INSTANCE).updateSerializer(TextPacket.class, TextSerializer_v332.INSTANCE).updateSerializer(StartGamePacket.class, StartGameSerializer_v332.INSTANCE).updateSerializer(AddPaintingPacket.class, AddPaintingSerializer_v332.INSTANCE).updateSerializer(EventPacket.class, EventSerializer_v332.INSTANCE).updateSerializer(NetworkStackLatencyPacket.class, NetworkStackLatencySerializer_v332.INSTANCE).updateSerializer(SpawnParticleEffectPacket.class, SpawnParticleEffectSerializer_v332.INSTANCE).updateSerializer(AvailableCommandsPacket.class, new AvailableCommandsSerializer_v291(COMMAND_PARAMS)).updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS)).registerPacket(LevelSoundEventPacket::new, new LevelSoundEventSerializer_v332(SOUND_EVENTS), 123, PacketRecipient.BOTH).build();
   }
}
