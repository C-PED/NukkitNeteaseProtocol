package com.nukkitx.protocol.bedrock.codec.v557;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import com.nukkitx.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v554.Bedrock_v554;
import com.nukkitx.protocol.bedrock.codec.v557.serializer.AddEntitySerializer_v557;
import com.nukkitx.protocol.bedrock.codec.v557.serializer.AddPlayerSerializer_v557;
import com.nukkitx.protocol.bedrock.codec.v557.serializer.SetEntityDataSerializer_v557;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.packet.AddEntityPacket;
import com.nukkitx.protocol.bedrock.packet.AddPlayerPacket;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent1Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent2Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEventPacket;
import com.nukkitx.protocol.bedrock.packet.SetEntityDataPacket;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v557 extends Bedrock_v554 {
   public static final EntityDataTypeMap ENTITY_DATA;
   public static final TypeMap<SoundEvent> SOUND_EVENTS;
   public static final BedrockCodec CODEC;

   static {
      ENTITY_DATA = Bedrock_v554.ENTITY_DATA.toBuilder().remove(120).shift(121, -1).build();
      SOUND_EVENTS = Bedrock_v554.SOUND_EVENTS.toBuilder().remove(443).insert(445, SoundEvent.BUNDLE_DROP_CONTENTS).insert(446, SoundEvent.BUNDLE_INSERT).insert(447, SoundEvent.BUNDLE_REMOVE_ONE).insert(448, SoundEvent.UNDEFINED).build();
      CODEC = Bedrock_v554.CODEC.toBuilder().raknetProtocolVersion(11).protocolVersion(557).minecraftVersion("1.19.40").helper(() -> new BedrockCodecHelper_v557(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS)).updateSerializer(AddPlayerPacket.class, new AddPlayerSerializer_v557()).updateSerializer(AddEntityPacket.class, new AddEntitySerializer_v557()).updateSerializer(SetEntityDataPacket.class, new SetEntityDataSerializer_v557()).updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS)).updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS)).updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS)).build();
   }
}
