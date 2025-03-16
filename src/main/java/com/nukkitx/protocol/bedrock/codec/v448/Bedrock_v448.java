package com.nukkitx.protocol.bedrock.codec.v448;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import com.nukkitx.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.LevelEventGenericSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v440.Bedrock_v440;
import com.nukkitx.protocol.bedrock.codec.v448.serializer.AvailableCommandsSerializer_v448;
import com.nukkitx.protocol.bedrock.codec.v448.serializer.NpcDialogueSerializer_v448;
import com.nukkitx.protocol.bedrock.codec.v448.serializer.NpcRequestSerializer_v448;
import com.nukkitx.protocol.bedrock.codec.v448.serializer.ResourcePacksInfoSerializer_v448;
import com.nukkitx.protocol.bedrock.codec.v448.serializer.SetTitleSerializer_v448;
import com.nukkitx.protocol.bedrock.codec.v448.serializer.SimulationTypeSerializer_v448;
import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.data.ParticleType;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataTypes;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlag;
import com.nukkitx.protocol.bedrock.packet.AvailableCommandsPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventGenericPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventPacket;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent1Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent2Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEventPacket;
import com.nukkitx.protocol.bedrock.packet.NpcDialoguePacket;
import com.nukkitx.protocol.bedrock.packet.NpcRequestPacket;
import com.nukkitx.protocol.bedrock.packet.ResourcePacksInfoPacket;
import com.nukkitx.protocol.bedrock.packet.SetTitlePacket;
import com.nukkitx.protocol.bedrock.packet.SimulationTypePacket;
import com.nukkitx.protocol.bedrock.transformer.FlagTransformer;
import com.nukkitx.protocol.bedrock.transformer.TypeMapTransformer;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v448 extends Bedrock_v440 {
   protected static final TypeMap<EntityFlag> ENTITY_FLAGS;
   protected static final TypeMap<ParticleType> PARTICLE_TYPES;
   protected static final EntityDataTypeMap ENTITY_DATA;
   protected static final TypeMap<LevelEventType> LEVEL_EVENTS;
   protected static final TypeMap<SoundEvent> SOUND_EVENTS;
   public static final BedrockCodec CODEC;

   protected Bedrock_v448() {
   }

   static {
      ENTITY_FLAGS = Bedrock_v440.ENTITY_FLAGS.toBuilder().insert(98, EntityFlag.IN_ASCENDABLE_BLOCK).insert(99, EntityFlag.OVER_DESCENDABLE_BLOCK).build();
      PARTICLE_TYPES = Bedrock_v440.PARTICLE_TYPES.toBuilder().shift(9, 1).insert(9, ParticleType.CANDLE_FLAME).build();
      ENTITY_DATA = Bedrock_v440.ENTITY_DATA.toBuilder().update(EntityDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0)).update(EntityDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1)).update(EntityDataTypes.AREA_EFFECT_CLOUD_PARTICLE, new TypeMapTransformer(PARTICLE_TYPES)).build();
      LEVEL_EVENTS = Bedrock_v440.LEVEL_EVENTS.toBuilder().insert(16384, PARTICLE_TYPES).build();
      SOUND_EVENTS = Bedrock_v440.SOUND_EVENTS.toBuilder().replace(360, SoundEvent.CAKE_ADD_CANDLE).insert(361, SoundEvent.EXTINGUISH_CANDLE).insert(362, SoundEvent.AMBIENT_CANDLE).insert(363, SoundEvent.UNDEFINED).build();
      CODEC = Bedrock_v440.CODEC.toBuilder().protocolVersion(448).minecraftVersion("1.17.10").helper(() -> new BedrockCodecHelper_v448(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES)).updateSerializer(AvailableCommandsPacket.class, new AvailableCommandsSerializer_v448(COMMAND_PARAMS)).updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS)).updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS)).updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS)).updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS)).updateSerializer(LevelEventGenericPacket.class, new LevelEventGenericSerializer_v361(LEVEL_EVENTS)).updateSerializer(NpcRequestPacket.class, NpcRequestSerializer_v448.INSTANCE).updateSerializer(SetTitlePacket.class, SetTitleSerializer_v448.INSTANCE).updateSerializer(ResourcePacksInfoPacket.class, ResourcePacksInfoSerializer_v448.INSTANCE).registerPacket(SimulationTypePacket::new, SimulationTypeSerializer_v448.INSTANCE, 168, PacketRecipient.CLIENT).registerPacket(NpcDialoguePacket::new, NpcDialogueSerializer_v448.INSTANCE, 169, PacketRecipient.CLIENT).build();
   }
}
