package com.nukkitx.protocol.bedrock.codec.v554;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import com.nukkitx.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v545.Bedrock_v545;
import com.nukkitx.protocol.bedrock.codec.v554.serializer.GameTestRequestSerializer_v554;
import com.nukkitx.protocol.bedrock.codec.v554.serializer.GameTestResultsSerializer_v554;
import com.nukkitx.protocol.bedrock.codec.v554.serializer.NetworkSettingsSerializer_v554;
import com.nukkitx.protocol.bedrock.codec.v554.serializer.RequestNetworkSettingsSerializer_v554;
import com.nukkitx.protocol.bedrock.codec.v554.serializer.ServerStatsSerializer_v554;
import com.nukkitx.protocol.bedrock.codec.v554.serializer.StructureBlockUpdateSerializer_v554;
import com.nukkitx.protocol.bedrock.codec.v554.serializer.TextSerializer_v554;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import com.nukkitx.protocol.bedrock.packet.GameTestRequestPacket;
import com.nukkitx.protocol.bedrock.packet.GameTestResultsPacket;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent1Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent2Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEventPacket;
import com.nukkitx.protocol.bedrock.packet.NetworkSettingsPacket;
import com.nukkitx.protocol.bedrock.packet.RequestNetworkSettingsPacket;
import com.nukkitx.protocol.bedrock.packet.ServerStatsPacket;
import com.nukkitx.protocol.bedrock.packet.StructureBlockUpdatePacket;
import com.nukkitx.protocol.bedrock.packet.TextPacket;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v554 extends Bedrock_v545 {
   protected static final TypeMap<SoundEvent> SOUND_EVENTS;
   protected static final TypeMap<TextProcessingEventOrigin> TEXT_PROCESSING_ORIGINS;
   public static final BedrockCodec CODEC;

   static {
      SOUND_EVENTS = Bedrock_v545.SOUND_EVENTS.toBuilder().replace(442, SoundEvent.ENCHANTING_TABLE_USE).insert(443, SoundEvent.UNDEFINED).build();
      TEXT_PROCESSING_ORIGINS = TypeMap.<TextProcessingEventOrigin>fromEnum(TextProcessingEventOrigin.class, 13);
      CODEC = Bedrock_v545.CODEC.toBuilder().raknetProtocolVersion(11).protocolVersion(554).minecraftVersion("1.19.30").helper(() -> new BedrockCodecHelper_v554(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS)).updateSerializer(TextPacket.class, new TextSerializer_v554()).updateSerializer(NetworkSettingsPacket.class, new NetworkSettingsSerializer_v554()).updateSerializer(StructureBlockUpdatePacket.class, new StructureBlockUpdateSerializer_v554()).updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS)).updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS)).updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS)).registerPacket(ServerStatsPacket::new, new ServerStatsSerializer_v554(), 192, PacketRecipient.CLIENT).registerPacket(RequestNetworkSettingsPacket::new, new RequestNetworkSettingsSerializer_v554(), 193, PacketRecipient.SERVER).registerPacket(GameTestRequestPacket::new, new GameTestRequestSerializer_v554(), 194, PacketRecipient.SERVER).registerPacket(GameTestResultsPacket::new, new GameTestResultsSerializer_v554(), 195, PacketRecipient.CLIENT).build();
   }
}
