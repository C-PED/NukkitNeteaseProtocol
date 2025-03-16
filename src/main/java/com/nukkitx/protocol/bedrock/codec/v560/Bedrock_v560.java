package com.nukkitx.protocol.bedrock.codec.v560;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import com.nukkitx.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v557.BedrockCodecHelper_v557;
import com.nukkitx.protocol.bedrock.codec.v557.Bedrock_v557;
import com.nukkitx.protocol.bedrock.codec.v560.serializer.UpdateClientInputLocksSerializer_v560;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataTypes;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlag;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent1Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent2Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEventPacket;
import com.nukkitx.protocol.bedrock.packet.UpdateClientInputLocksPacket;
import com.nukkitx.protocol.bedrock.transformer.FlagTransformer;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v560 extends Bedrock_v557 {
   protected static final TypeMap<EntityFlag> ENTITY_FLAGS;
   protected static final EntityDataTypeMap ENTITY_DATA;
   protected static final TypeMap<ContainerSlotType> CONTAINER_SLOT_TYPES;
   protected static final TypeMap<SoundEvent> SOUND_EVENTS;
   public static final BedrockCodec CODEC;

   static {
      ENTITY_FLAGS = Bedrock_v557.ENTITY_FLAGS.toBuilder().shift(46, 1).insert(46, EntityFlag.CAN_DASH).insert(108, EntityFlag.HAS_DASH_COOLDOWN).insert(109, EntityFlag.PUSH_TOWARDS_CLOSEST_SPACE).build();
      ENTITY_DATA = Bedrock_v557.ENTITY_DATA.toBuilder().update(EntityDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0)).update(EntityDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1)).build();
      CONTAINER_SLOT_TYPES = Bedrock_v557.CONTAINER_SLOT_TYPES.toBuilder().shift(21, 1).insert(21, ContainerSlotType.RECIPE_BOOK).build();
      SOUND_EVENTS = Bedrock_v557.SOUND_EVENTS.toBuilder().remove(448).insert(448, SoundEvent.PRESSURE_PLATE_CLICK_OFF).insert(449, SoundEvent.PRESSURE_PLATE_CLICK_ON).insert(450, SoundEvent.BUTTON_CLICK_OFF).insert(451, SoundEvent.BUTTON_CLICK_ON).insert(452, SoundEvent.DOOR_OPEN).insert(453, SoundEvent.DOOR_CLOSE).insert(454, SoundEvent.TRAPDOOR_OPEN).insert(455, SoundEvent.TRAPDOOR_CLOSE).insert(456, SoundEvent.FENCE_GATE_OPEN).insert(457, SoundEvent.FENCE_GATE_CLOSE).insert(458, SoundEvent.UNDEFINED).build();
      CODEC = Bedrock_v557.CODEC.toBuilder().raknetProtocolVersion(11).protocolVersion(560).minecraftVersion("1.19.50").helper(() -> new BedrockCodecHelper_v557(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS)).updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS)).updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS)).updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS)).registerPacket(UpdateClientInputLocksPacket::new, new UpdateClientInputLocksSerializer_v560(), 196, PacketRecipient.CLIENT).build();
   }
}
