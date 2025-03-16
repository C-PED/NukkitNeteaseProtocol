package com.nukkitx.protocol.bedrock.codec.v486;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import com.nukkitx.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v465.BedrockCodecHelper_v465;
import com.nukkitx.protocol.bedrock.codec.v475.Bedrock_v475;
import com.nukkitx.protocol.bedrock.codec.v486.serializer.AddVolumeEntitySerializer_v486;
import com.nukkitx.protocol.bedrock.codec.v486.serializer.BossEventSerializer_v486;
import com.nukkitx.protocol.bedrock.codec.v486.serializer.CodeBuilderSourceSerializer_v486;
import com.nukkitx.protocol.bedrock.codec.v486.serializer.LevelChunkSerializer_v486;
import com.nukkitx.protocol.bedrock.codec.v486.serializer.PlayerStartItemCooldownSerializer_v486;
import com.nukkitx.protocol.bedrock.codec.v486.serializer.ScriptMessageSerializer_v486;
import com.nukkitx.protocol.bedrock.codec.v486.serializer.SubChunkRequestSerializer_v486;
import com.nukkitx.protocol.bedrock.codec.v486.serializer.SubChunkSerializer_v486;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataTypes;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlag;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import com.nukkitx.protocol.bedrock.packet.AddVolumeEntityPacket;
import com.nukkitx.protocol.bedrock.packet.BossEventPacket;
import com.nukkitx.protocol.bedrock.packet.CodeBuilderSourcePacket;
import com.nukkitx.protocol.bedrock.packet.LevelChunkPacket;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent1Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent2Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEventPacket;
import com.nukkitx.protocol.bedrock.packet.PlayerStartItemCooldownPacket;
import com.nukkitx.protocol.bedrock.packet.ScriptMessagePacket;
import com.nukkitx.protocol.bedrock.packet.SubChunkPacket;
import com.nukkitx.protocol.bedrock.packet.SubChunkRequestPacket;
import com.nukkitx.protocol.bedrock.transformer.FlagTransformer;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v486 extends Bedrock_v475 {
   protected static final TypeMap<EntityFlag> ENTITY_FLAGS;
   protected static final EntityDataTypeMap ENTITY_DATA;
   protected static final TypeMap<ItemStackRequestActionType> ITEM_STACK_REQUEST_TYPES;
   protected static final TypeMap<SoundEvent> SOUND_EVENTS;
   public static final BedrockCodec CODEC;

   static {
      ENTITY_FLAGS = Bedrock_v475.ENTITY_FLAGS.toBuilder().insert(100, EntityFlag.CROAKING).insert(101, EntityFlag.EAT_MOB).build();
      ENTITY_DATA = Bedrock_v475.ENTITY_DATA.toBuilder().update(EntityDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0)).update(EntityDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1)).build();
      ITEM_STACK_REQUEST_TYPES = Bedrock_v475.ITEM_STACK_REQUEST_TYPES.toBuilder().shift(7, 2).insert(7, ItemStackRequestActionType.PLACE_IN_ITEM_CONTAINER).insert(8, ItemStackRequestActionType.TAKE_FROM_ITEM_CONTAINER).build();
      SOUND_EVENTS = Bedrock_v475.SOUND_EVENTS.toBuilder().replace(372, SoundEvent.TONGUE).insert(373, SoundEvent.CRACK_IRON_GOLEM).insert(374, SoundEvent.REPAIR_IRON_GOLEM).insert(375, SoundEvent.UNDEFINED).build();
      CODEC = Bedrock_v475.CODEC.toBuilder().protocolVersion(486).minecraftVersion("1.18.10").helper(() -> new BedrockCodecHelper_v465(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES)).updateSerializer(AddVolumeEntityPacket.class, AddVolumeEntitySerializer_v486.INSTANCE).updateSerializer(BossEventPacket.class, BossEventSerializer_v486.INSTANCE).updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS)).updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS)).updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS)).updateSerializer(LevelChunkPacket.class, LevelChunkSerializer_v486.INSTANCE).updateSerializer(SubChunkPacket.class, SubChunkSerializer_v486.INSTANCE).updateSerializer(SubChunkRequestPacket.class, SubChunkRequestSerializer_v486.INSTANCE).registerPacket(PlayerStartItemCooldownPacket::new, PlayerStartItemCooldownSerializer_v486.INSTANCE, 176, PacketRecipient.CLIENT).registerPacket(ScriptMessagePacket::new, ScriptMessageSerializer_v486.INSTANCE, 177, PacketRecipient.BOTH).registerPacket(CodeBuilderSourcePacket::new, CodeBuilderSourceSerializer_v486.INSTANCE, 178, PacketRecipient.SERVER).build();
   }
}
