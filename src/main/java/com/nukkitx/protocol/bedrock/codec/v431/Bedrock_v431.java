package com.nukkitx.protocol.bedrock.codec.v431;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import com.nukkitx.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.LevelEventGenericSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v428.Bedrock_v428;
import com.nukkitx.protocol.bedrock.codec.v428.serializer.PlayerAuthInputSerializer_v428;
import com.nukkitx.protocol.bedrock.data.LevelEvent;
import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.ParticleType;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataTypes;
import com.nukkitx.protocol.bedrock.packet.LevelEventGenericPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventPacket;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent1Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent2Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEventPacket;
import com.nukkitx.protocol.bedrock.packet.PlayerAuthInputPacket;
import com.nukkitx.protocol.bedrock.transformer.TypeMapTransformer;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v431 extends Bedrock_v428 {
   protected static final TypeMap<ParticleType> PARTICLE_TYPES;
   protected static final EntityDataTypeMap ENTITY_DATA;
   protected static final TypeMap<LevelEventType> LEVEL_EVENTS;
   protected static final TypeMap<SoundEvent> SOUND_EVENTS;
   public static BedrockCodec CODEC;

   protected Bedrock_v431() {
   }

   static {
      PARTICLE_TYPES = Bedrock_v428.PARTICLE_TYPES.toBuilder().shift(29, 2).insert(29, ParticleType.STALACTITE_DRIP_WATER).insert(30, ParticleType.STALACTITE_DRIP_LAVA).build();
      ENTITY_DATA = Bedrock_v428.ENTITY_DATA.toBuilder().update(EntityDataTypes.AREA_EFFECT_CLOUD_PARTICLE, new TypeMapTransformer(PARTICLE_TYPES)).build();
      LEVEL_EVENTS = Bedrock_v428.LEVEL_EVENTS.toBuilder().insert(1064, LevelEvent.SOUND_POINTED_DRIPSTONE_LAND).insert(1065, LevelEvent.SOUND_DYE_USED).insert(1066, LevelEvent.SOUND_INK_SACE_USED).insert(2028, LevelEvent.PARTICLE_DRIPSTONE_DRIP).insert(2029, LevelEvent.PARTICLE_FIZZ_EFFECT).insert(2030, LevelEvent.PARTICLE_WAX_ON).insert(2031, LevelEvent.PARTICLE_WAX_OFF).insert(2032, LevelEvent.PARTICLE_SCRAPE).insert(2033, LevelEvent.PARTICLE_ELECTRIC_SPARK).insert(16384, PARTICLE_TYPES).build();
      SOUND_EVENTS = Bedrock_v428.SOUND_EVENTS.toBuilder().replace(332, SoundEvent.POINTED_DRIPSTONE_CAULDRON_DRIP_LAVA).insert(333, SoundEvent.POINTED_DRIPSTONE_CAULDRON_DRIP_WATER).insert(334, SoundEvent.POINTED_DRIPSTONE_DRIP_LAVA).insert(335, SoundEvent.POINTED_DRIPSTONE_DRIP_WATER).insert(336, SoundEvent.CAVE_VINES_PICK_BERRIES).insert(337, SoundEvent.BIG_DRIPLEAF_TILT_DOWN).insert(338, SoundEvent.BIG_DRIPLEAF_TILT_UP).insert(339, SoundEvent.UNDEFINED).build();
      CODEC = Bedrock_v428.CODEC.toBuilder().protocolVersion(431).minecraftVersion("1.16.220").helper(() -> new BedrockCodecHelper_v431(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES)).updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS)).updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS)).updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS)).updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS)).updateSerializer(LevelEventGenericPacket.class, new LevelEventGenericSerializer_v361(LEVEL_EVENTS)).updateSerializer(PlayerAuthInputPacket.class, PlayerAuthInputSerializer_v428.INSTANCE).build();
   }
}
