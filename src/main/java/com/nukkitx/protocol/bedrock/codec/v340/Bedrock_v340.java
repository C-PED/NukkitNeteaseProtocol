package com.nukkitx.protocol.bedrock.codec.v340;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import com.nukkitx.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import com.nukkitx.protocol.bedrock.codec.v332.Bedrock_v332;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v340.serializer.AvailableCommandsSerializer_v340;
import com.nukkitx.protocol.bedrock.codec.v340.serializer.EventSerializer_v340;
import com.nukkitx.protocol.bedrock.codec.v340.serializer.LecternUpdateSerializer_v340;
import com.nukkitx.protocol.bedrock.codec.v340.serializer.StructureBlockUpdateSerializer_v340;
import com.nukkitx.protocol.bedrock.codec.v340.serializer.VideoStreamConnectSerializer_v340;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.data.command.CommandParam;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataFormat;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataTypes;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlag;
import com.nukkitx.protocol.bedrock.packet.AvailableCommandsPacket;
import com.nukkitx.protocol.bedrock.packet.EventPacket;
import com.nukkitx.protocol.bedrock.packet.LecternUpdatePacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventPacket;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent1Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent2Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEventPacket;
import com.nukkitx.protocol.bedrock.packet.StructureBlockUpdatePacket;
import com.nukkitx.protocol.bedrock.packet.VideoStreamConnectPacket;
import com.nukkitx.protocol.bedrock.transformer.BooleanTransformer;
import com.nukkitx.protocol.bedrock.transformer.FlagTransformer;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v340 extends Bedrock_v332 {
   protected static final TypeMap<CommandParam> COMMAND_PARAMS;
   protected static final TypeMap<EntityFlag> ENTITY_FLAGS;
   protected static final EntityDataTypeMap ENTITY_DATA;
   protected static final TypeMap<SoundEvent> SOUND_EVENTS;
   public static final BedrockCodec CODEC;

   static {
      COMMAND_PARAMS = Bedrock_v332.COMMAND_PARAMS.toBuilder().shift(15, -1).build();
      ENTITY_FLAGS = Bedrock_v332.ENTITY_FLAGS.toBuilder().insert(71, EntityFlag.BLOCKING).insert(72, EntityFlag.TRANSITION_BLOCKING).insert(73, EntityFlag.BLOCKED_USING_SHIELD).insert(74, EntityFlag.SLEEPING).insert(75, EntityFlag.WANTS_TO_WAKE).insert(76, EntityFlag.TRADE_INTEREST).insert(77, EntityFlag.DOOR_BREAKER).insert(78, EntityFlag.BREAKING_OBSTRUCTION).insert(79, EntityFlag.DOOR_OPENER).build();
      ENTITY_DATA = Bedrock_v332.ENTITY_DATA.toBuilder().update(EntityDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0)).update(EntityDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1)).replace(EntityDataTypes.HAS_NPC, 39, EntityDataFormat.BYTE, BooleanTransformer.INSTANCE).insert(EntityDataTypes.INTERACT_TEXT, 99, EntityDataFormat.STRING).insert(EntityDataTypes.TRADE_TIER, 100, EntityDataFormat.INT).insert(EntityDataTypes.MAX_TRADE_TIER, 101, EntityDataFormat.INT).build();
      SOUND_EVENTS = Bedrock_v332.SOUND_EVENTS.toBuilder().replace(255, SoundEvent.SHIELD_BLOCK).insert(256, SoundEvent.LECTERN_BOOK_PLACE).insert(257, SoundEvent.UNDEFINED).build();
      CODEC = Bedrock_v332.CODEC.toBuilder().protocolVersion(340).minecraftVersion("1.10.0").helper(() -> new BedrockCodecHelper_v340(ENTITY_DATA, GAME_RULE_TYPES)).updateSerializer(EventPacket.class, EventSerializer_v340.INSTANCE).updateSerializer(AvailableCommandsPacket.class, new AvailableCommandsSerializer_v340(COMMAND_PARAMS)).updateSerializer(StructureBlockUpdatePacket.class, StructureBlockUpdateSerializer_v340.INSTANCE).updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS)).updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS)).updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS)).updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS)).registerPacket(LecternUpdatePacket::new, LecternUpdateSerializer_v340.INSTANCE, 124, PacketRecipient.SERVER).registerPacket(VideoStreamConnectPacket::new, VideoStreamConnectSerializer_v340.INSTANCE, 125, PacketRecipient.CLIENT).build();
   }
}
