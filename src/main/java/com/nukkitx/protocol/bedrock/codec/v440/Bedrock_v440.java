package com.nukkitx.protocol.bedrock.codec.v440;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import com.nukkitx.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.LevelEventGenericSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v431.Bedrock_v431;
import com.nukkitx.protocol.bedrock.codec.v440.serializer.AddVolumeEntitySerializer_v440;
import com.nukkitx.protocol.bedrock.codec.v440.serializer.RemoveVolumeEntitySerializer_v440;
import com.nukkitx.protocol.bedrock.codec.v440.serializer.StartGameSerializer_v440;
import com.nukkitx.protocol.bedrock.codec.v440.serializer.SyncEntityPropertySerializer_v440;
import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.data.ParticleType;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataFormat;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataTypes;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlag;
import com.nukkitx.protocol.bedrock.packet.AddVolumeEntityPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventGenericPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventPacket;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent1Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent2Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEventPacket;
import com.nukkitx.protocol.bedrock.packet.RemoveVolumeEntityPacket;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import com.nukkitx.protocol.bedrock.packet.SyncEntityPropertyPacket;
import com.nukkitx.protocol.bedrock.transformer.FlagTransformer;
import com.nukkitx.protocol.bedrock.transformer.TypeMapTransformer;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v440 extends Bedrock_v431 {
   protected static final TypeMap<EntityFlag> ENTITY_FLAGS;
   protected static final TypeMap<ParticleType> PARTICLE_TYPES;
   protected static final EntityDataTypeMap ENTITY_DATA;
   protected static final TypeMap<LevelEventType> LEVEL_EVENTS;
   protected static final TypeMap<SoundEvent> SOUND_EVENTS;
   public static final BedrockCodec CODEC;

   protected Bedrock_v440() {
   }

   static {
      ENTITY_FLAGS = Bedrock_v431.ENTITY_FLAGS.toBuilder().insert(97, EntityFlag.PLAYING_DEAD).build();
      PARTICLE_TYPES = Bedrock_v431.PARTICLE_TYPES.toBuilder().insert(73, ParticleType.PORTAL_REVERSE).insert(74, ParticleType.SNOWFLAKE).insert(75, ParticleType.VIBRATION_SIGNAL).insert(76, ParticleType.SCULK_SENSOR_REDSTONE).insert(77, ParticleType.SPORE_BLOSSOM_SHOWER).insert(78, ParticleType.SPORE_BLOSSOM_AMBIENT).insert(79, ParticleType.WAX).insert(80, ParticleType.ELECTRIC_SPARK).build();
      ENTITY_DATA = Bedrock_v431.ENTITY_DATA.toBuilder().update(EntityDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0)).update(EntityDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1)).update(EntityDataTypes.AREA_EFFECT_CLOUD_PARTICLE, new TypeMapTransformer(PARTICLE_TYPES)).shift(120, 1).insert(EntityDataTypes.UPDATE_PROPERTIES, 120, EntityDataFormat.NBT).build();
      LEVEL_EVENTS = Bedrock_v431.LEVEL_EVENTS.toBuilder().insert(16384, PARTICLE_TYPES).build();
      SOUND_EVENTS = Bedrock_v431.SOUND_EVENTS.toBuilder().replace(339, SoundEvent.COPPER_WAX_ON).insert(340, SoundEvent.COPPER_WAX_OFF).insert(341, SoundEvent.SCRAPE).insert(342, SoundEvent.PLAYER_HURT_DROWN).insert(343, SoundEvent.PLAYER_HURT_ON_FIRE).insert(344, SoundEvent.PLAYER_HURT_FREEZE).insert(345, SoundEvent.USE_SPYGLASS).insert(346, SoundEvent.STOP_USING_SPYGLASS).insert(347, SoundEvent.AMETHYST_BLOCK_CHIME).insert(348, SoundEvent.AMBIENT_SCREAMER).insert(349, SoundEvent.HURT_SCREAMER).insert(350, SoundEvent.DEATH_SCREAMER).insert(351, SoundEvent.MILK_SCREAMER).insert(352, SoundEvent.JUMP_TO_BLOCK).insert(353, SoundEvent.PRE_RAM).insert(354, SoundEvent.PRE_RAM_SCREAMER).insert(355, SoundEvent.RAM_IMPACT).insert(356, SoundEvent.RAM_IMPACT_SCREAMER).insert(357, SoundEvent.SQUID_INK_SQUIRT).insert(358, SoundEvent.GLOW_SQUID_INK_SQUIRT).insert(359, SoundEvent.CONVERT_TO_STRAY).insert(360, SoundEvent.UNDEFINED).build();
      CODEC = Bedrock_v431.CODEC.toBuilder().protocolVersion(440).minecraftVersion("1.17.0").helper(() -> new BedrockCodecHelper_v440(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES)).updateSerializer(StartGamePacket.class, StartGameSerializer_v440.INSTANCE).updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS)).updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS)).updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS)).updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS)).updateSerializer(LevelEventGenericPacket.class, new LevelEventGenericSerializer_v361(LEVEL_EVENTS)).registerPacket(SyncEntityPropertyPacket::new, SyncEntityPropertySerializer_v440.INSTANCE, 165, PacketRecipient.CLIENT).registerPacket(AddVolumeEntityPacket::new, AddVolumeEntitySerializer_v440.INSTANCE, 166, PacketRecipient.CLIENT).registerPacket(RemoveVolumeEntityPacket::new, RemoveVolumeEntitySerializer_v440.INSTANCE, 167, PacketRecipient.CLIENT).build();
   }
}
