package com.nukkitx.protocol.bedrock.codec.v475;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import com.nukkitx.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.LevelEventGenericSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v465.BedrockCodecHelper_v465;
import com.nukkitx.protocol.bedrock.codec.v471.Bedrock_v471;
import com.nukkitx.protocol.bedrock.codec.v475.serializer.StartGameSerializer_v475;
import com.nukkitx.protocol.bedrock.codec.v475.serializer.SubChunkSerializer_v475;
import com.nukkitx.protocol.bedrock.data.LevelEvent;
import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.packet.LevelEventGenericPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventPacket;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent1Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent2Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEventPacket;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import com.nukkitx.protocol.bedrock.packet.SubChunkPacket;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v475 extends Bedrock_v471 {
   protected static final TypeMap<LevelEventType> LEVEL_EVENTS;
   protected static final TypeMap<SoundEvent> SOUND_EVENTS;
   public static final BedrockCodec CODEC;

   static {
      LEVEL_EVENTS = Bedrock_v471.LEVEL_EVENTS.toBuilder().insert(9801, LevelEvent.SLEEPING_PLAYERS).build();
      SOUND_EVENTS = Bedrock_v471.SOUND_EVENTS.toBuilder().replace(371, SoundEvent.RECORD_OTHERSIDE).insert(372, SoundEvent.UNDEFINED).build();
      CODEC = Bedrock_v471.CODEC.toBuilder().protocolVersion(475).minecraftVersion("1.18.0").helper(() -> new BedrockCodecHelper_v465(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES)).updateSerializer(StartGamePacket.class, StartGameSerializer_v475.INSTANCE).updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS)).updateSerializer(LevelEventGenericPacket.class, new LevelEventGenericSerializer_v361(LEVEL_EVENTS)).updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS)).updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS)).updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS)).updateSerializer(SubChunkPacket.class, SubChunkSerializer_v475.INSTANCE).build();
   }
}
