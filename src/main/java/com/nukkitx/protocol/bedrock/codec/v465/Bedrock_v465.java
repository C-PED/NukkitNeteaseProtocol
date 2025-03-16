package com.nukkitx.protocol.bedrock.codec.v465;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.EntityEventSerializer_v291;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import com.nukkitx.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.LevelEventGenericSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v448.Bedrock_v448;
import com.nukkitx.protocol.bedrock.codec.v465.serializer.AddVolumeEntitySerializer_v465;
import com.nukkitx.protocol.bedrock.codec.v465.serializer.AnimateEntitySerializer_v465;
import com.nukkitx.protocol.bedrock.codec.v465.serializer.CraftingDataSerializer_v465;
import com.nukkitx.protocol.bedrock.codec.v465.serializer.CreatePhotoSerializer_v465;
import com.nukkitx.protocol.bedrock.codec.v465.serializer.EduUriResourceSerializer_v465;
import com.nukkitx.protocol.bedrock.codec.v465.serializer.EducationSettingsSerializer_v465;
import com.nukkitx.protocol.bedrock.codec.v465.serializer.EntityPickRequestSerializer_v465;
import com.nukkitx.protocol.bedrock.codec.v465.serializer.HurtArmorSerializer_v465;
import com.nukkitx.protocol.bedrock.codec.v465.serializer.PhotoTransferSerializer_v465;
import com.nukkitx.protocol.bedrock.codec.v465.serializer.StartGameSerializer_v465;
import com.nukkitx.protocol.bedrock.codec.v465.serializer.UpdateSubChunkBlocksSerializer_v465;
import com.nukkitx.protocol.bedrock.data.LevelEvent;
import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.data.ParticleType;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataTypes;
import com.nukkitx.protocol.bedrock.data.entity.EntityEventType;
import com.nukkitx.protocol.bedrock.packet.AddVolumeEntityPacket;
import com.nukkitx.protocol.bedrock.packet.AnimateEntityPacket;
import com.nukkitx.protocol.bedrock.packet.CraftingDataPacket;
import com.nukkitx.protocol.bedrock.packet.CreatePhotoPacket;
import com.nukkitx.protocol.bedrock.packet.EduUriResourcePacket;
import com.nukkitx.protocol.bedrock.packet.EducationSettingsPacket;
import com.nukkitx.protocol.bedrock.packet.EntityEventPacket;
import com.nukkitx.protocol.bedrock.packet.EntityPickRequestPacket;
import com.nukkitx.protocol.bedrock.packet.HurtArmorPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventGenericPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventPacket;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent1Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent2Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEventPacket;
import com.nukkitx.protocol.bedrock.packet.PhotoTransferPacket;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import com.nukkitx.protocol.bedrock.packet.UpdateSubChunkBlocksPacket;
import com.nukkitx.protocol.bedrock.transformer.TypeMapTransformer;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v465 extends Bedrock_v448 {
   protected static final TypeMap<ParticleType> PARTICLE_TYPES;
   protected static final EntityDataTypeMap ENTITY_DATA;
   protected static final TypeMap<EntityEventType> ENTITY_EVENTS;
   protected static final TypeMap<LevelEventType> LEVEL_EVENTS;
   protected static final TypeMap<SoundEvent> SOUND_EVENTS;
   public static final BedrockCodec CODEC;

   protected Bedrock_v465() {
   }

   static {
      PARTICLE_TYPES = Bedrock_v448.PARTICLE_TYPES.toBuilder().insert(82, ParticleType.SHRIEK).build();
      ENTITY_DATA = Bedrock_v448.ENTITY_DATA.toBuilder().update(EntityDataTypes.AREA_EFFECT_CLOUD_PARTICLE, new TypeMapTransformer(PARTICLE_TYPES)).build();
      ENTITY_EVENTS = Bedrock_v448.ENTITY_EVENTS.toBuilder().insert(76, EntityEventType.ENTITY_GROW_UP).build();
      LEVEL_EVENTS = Bedrock_v448.LEVEL_EVENTS.toBuilder().insert(2034, LevelEvent.PARTICLE_TURTLE_EGG).insert(2035, LevelEvent.PARTICLE_SCULK_SHRIEK).insert(16384, PARTICLE_TYPES).build();
      SOUND_EVENTS = Bedrock_v448.SOUND_EVENTS.toBuilder().replace(363, SoundEvent.BLOCK_CLICK).insert(364, SoundEvent.BLOCK_CLICK_FAIL).insert(366, SoundEvent.SCULK_SHRIEKER_SHRIEK).insert(367, SoundEvent.WARDEN_NEARBY_CLOSE).insert(368, SoundEvent.WARDEN_NEARBY_CLOSER).insert(369, SoundEvent.WARDEN_NEARBY_CLOSEST).insert(370, SoundEvent.WARDEN_SLIGHTLY_ANGRY).insert(371, SoundEvent.UNDEFINED).build();
      CODEC = Bedrock_v448.CODEC.toBuilder().protocolVersion(465).minecraftVersion("1.17.30").helper(() -> new BedrockCodecHelper_v465(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES)).updateSerializer(StartGamePacket.class, StartGameSerializer_v465.INSTANCE).updateSerializer(EntityEventPacket.class, new EntityEventSerializer_v291(ENTITY_EVENTS)).updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS)).updateSerializer(LevelEventGenericPacket.class, new LevelEventGenericSerializer_v361(LEVEL_EVENTS)).updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS)).updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS)).updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS)).updateSerializer(EntityPickRequestPacket.class, EntityPickRequestSerializer_v465.INSTANCE).updateSerializer(AddVolumeEntityPacket.class, AddVolumeEntitySerializer_v465.INSTANCE).updateSerializer(AnimateEntityPacket.class, AnimateEntitySerializer_v465.INSTANCE).updateSerializer(PhotoTransferPacket.class, PhotoTransferSerializer_v465.INSTANCE).updateSerializer(EducationSettingsPacket.class, EducationSettingsSerializer_v465.INSTANCE).updateSerializer(HurtArmorPacket.class, HurtArmorSerializer_v465.INSTANCE).updateSerializer(CraftingDataPacket.class, CraftingDataSerializer_v465.INSTANCE).registerPacket(EduUriResourcePacket::new, EduUriResourceSerializer_v465.INSTANCE, 170, PacketRecipient.CLIENT).registerPacket(CreatePhotoPacket::new, CreatePhotoSerializer_v465.INSTANCE, 171, PacketRecipient.SERVER).registerPacket(UpdateSubChunkBlocksPacket::new, UpdateSubChunkBlocksSerializer_v465.INSTANCE, 172, PacketRecipient.CLIENT).build();
   }
}
