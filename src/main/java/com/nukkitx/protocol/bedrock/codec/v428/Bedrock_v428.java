package com.nukkitx.protocol.bedrock.codec.v428;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import com.nukkitx.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.LevelEventGenericSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v388.serializer.AvailableCommandsSerializer_v388;
import com.nukkitx.protocol.bedrock.codec.v422.Bedrock_v422;
import com.nukkitx.protocol.bedrock.codec.v428.serializer.CameraShakeSerializer_v428;
import com.nukkitx.protocol.bedrock.codec.v428.serializer.ClientboundDebugRendererSerializer_v428;
import com.nukkitx.protocol.bedrock.codec.v428.serializer.ItemStackResponseSerializer_v428;
import com.nukkitx.protocol.bedrock.codec.v428.serializer.PlayerAuthInputSerializer_v428;
import com.nukkitx.protocol.bedrock.codec.v428.serializer.StartGameSerializer_v428;
import com.nukkitx.protocol.bedrock.data.LevelEvent;
import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.data.command.CommandParam;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataFormat;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataTypes;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlag;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import com.nukkitx.protocol.bedrock.packet.AvailableCommandsPacket;
import com.nukkitx.protocol.bedrock.packet.CameraShakePacket;
import com.nukkitx.protocol.bedrock.packet.ClientboundDebugRendererPacket;
import com.nukkitx.protocol.bedrock.packet.ItemStackResponsePacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventGenericPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventPacket;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent1Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent2Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEventPacket;
import com.nukkitx.protocol.bedrock.packet.PlayerAuthInputPacket;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import com.nukkitx.protocol.bedrock.transformer.FlagTransformer;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v428 extends Bedrock_v422 {
   protected static final TypeMap<EntityFlag> ENTITY_FLAGS;
   protected static final EntityDataTypeMap ENTITY_DATA;
   protected static final TypeMap<LevelEventType> LEVEL_EVENTS;
   protected static final TypeMap<CommandParam> COMMAND_PARAMS;
   protected static final TypeMap<SoundEvent> SOUND_EVENTS;
   protected static final TypeMap<ItemStackRequestActionType> ITEM_STACK_REQUEST_TYPES;
   public static final BedrockCodec CODEC;

   protected Bedrock_v428() {
   }

   static {
      ENTITY_FLAGS = Bedrock_v422.ENTITY_FLAGS.toBuilder().insert(96, EntityFlag.RAM_ATTACK).build();
      ENTITY_DATA = Bedrock_v422.ENTITY_DATA.toBuilder().update(EntityDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0)).update(EntityDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1)).shift(60, 1).insert(EntityDataTypes.SEAT_ROTATION_OFFSET_DEGREES, 60, EntityDataFormat.FLOAT).shift(120, 1).insert(EntityDataTypes.FREEZING_EFFECT_STRENGTH, 120, EntityDataFormat.FLOAT).insert(EntityDataTypes.GOAT_HORN_COUNT, 122, EntityDataFormat.INT).insert(EntityDataTypes.BASE_RUNTIME_ID, 123, EntityDataFormat.STRING).build();
      LEVEL_EVENTS = Bedrock_v422.LEVEL_EVENTS.toBuilder().insert(2027, LevelEvent.PARTICLE_VIBRATION_SIGNAL).insert(3514, LevelEvent.CAULDRON_FILL_POWDER_SNOW).insert(3515, LevelEvent.CAULDRON_TAKE_POWDER_SNOW).build();
      COMMAND_PARAMS = Bedrock_v422.COMMAND_PARAMS.toBuilder().shift(2, 1).shift(57, 6).insert(60, CommandParam.BLOCK_STATES).build();
      SOUND_EVENTS = Bedrock_v422.SOUND_EVENTS.toBuilder().replace(318, SoundEvent.AMBIENT_LOOP_WARPED_FOREST).insert(319, SoundEvent.AMBIENT_LOOP_SOULSAND_VALLEY).insert(320, SoundEvent.AMBIENT_LOOP_NETHER_WASTES).insert(321, SoundEvent.AMBIENT_LOOP_BASALT_DELTAS).insert(322, SoundEvent.AMBIENT_LOOP_CRIMSON_FOREST).insert(323, SoundEvent.AMBIENT_ADDITION_WARPED_FOREST).insert(324, SoundEvent.AMBIENT_ADDITION_SOULSAND_VALLEY).insert(325, SoundEvent.AMBIENT_ADDITION_NETHER_WASTES).insert(326, SoundEvent.AMBIENT_ADDITION_BASALT_DELTAS).insert(327, SoundEvent.AMBIENT_ADDITION_CRIMSON_FOREST).insert(328, SoundEvent.SCULK_SENSOR_POWER_ON).insert(329, SoundEvent.SCULK_SENSOR_POWER_OFF).insert(330, SoundEvent.BUCKET_FILL_POWDER_SNOW).insert(331, SoundEvent.BUCKET_EMPTY_POWDER_SNOW).insert(332, SoundEvent.UNDEFINED).build();
      ITEM_STACK_REQUEST_TYPES = Bedrock_v422.ITEM_STACK_REQUEST_TYPES.toBuilder().shift(9, 1).insert(9, ItemStackRequestActionType.MINE_BLOCK).build();
      CODEC = Bedrock_v422.CODEC.toBuilder().protocolVersion(428).minecraftVersion("1.16.210").helper(() -> new BedrockCodecHelper_v428(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES)).updateSerializer(StartGamePacket.class, StartGameSerializer_v428.INSTANCE).updateSerializer(PlayerAuthInputPacket.class, PlayerAuthInputSerializer_v428.INSTANCE).updateSerializer(ItemStackResponsePacket.class, ItemStackResponseSerializer_v428.INSTANCE).updateSerializer(CameraShakePacket.class, CameraShakeSerializer_v428.INSTANCE).updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS)).updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS)).updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS)).updateSerializer(AvailableCommandsPacket.class, new AvailableCommandsSerializer_v388(COMMAND_PARAMS)).updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS)).updateSerializer(LevelEventGenericPacket.class, new LevelEventGenericSerializer_v361(LEVEL_EVENTS)).registerPacket(ClientboundDebugRendererPacket::new, ClientboundDebugRendererSerializer_v428.INSTANCE, 164, PacketRecipient.CLIENT).build();
   }
}
