package com.nukkitx.protocol.bedrock.codec.v361;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import com.nukkitx.protocol.bedrock.codec.v354.Bedrock_v354;
import com.nukkitx.protocol.bedrock.codec.v354.serializer.LecternUpdateSerializer_v354;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.AddPaintingSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.ClientCacheBlobStatusSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.ClientCacheMissResponseSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.ClientCacheStatusSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.CommandBlockUpdateSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.CraftingDataSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.LevelChunkSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.LevelEventGenericSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.ResourcePackDataInfoSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.StartGameSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.StructureBlockUpdateSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.StructureTemplateDataRequestSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.StructureTemplateDataResponseSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.UpdateBlockPropertiesSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.VideoStreamConnectSerializer_v361;
import com.nukkitx.protocol.bedrock.data.LevelEvent;
import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.data.ParticleType;
import com.nukkitx.protocol.bedrock.data.ResourcePackType;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataFormat;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataTypes;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlag;
import com.nukkitx.protocol.bedrock.packet.AddHangingEntityPacket;
import com.nukkitx.protocol.bedrock.packet.AddPaintingPacket;
import com.nukkitx.protocol.bedrock.packet.ClientCacheBlobStatusPacket;
import com.nukkitx.protocol.bedrock.packet.ClientCacheMissResponsePacket;
import com.nukkitx.protocol.bedrock.packet.ClientCacheStatusPacket;
import com.nukkitx.protocol.bedrock.packet.CommandBlockUpdatePacket;
import com.nukkitx.protocol.bedrock.packet.CraftingDataPacket;
import com.nukkitx.protocol.bedrock.packet.LecternUpdatePacket;
import com.nukkitx.protocol.bedrock.packet.LevelChunkPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventGenericPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventPacket;
import com.nukkitx.protocol.bedrock.packet.ResourcePackDataInfoPacket;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import com.nukkitx.protocol.bedrock.packet.StructureBlockUpdatePacket;
import com.nukkitx.protocol.bedrock.packet.StructureTemplateDataRequestPacket;
import com.nukkitx.protocol.bedrock.packet.StructureTemplateDataResponsePacket;
import com.nukkitx.protocol.bedrock.packet.UpdateBlockPropertiesPacket;
import com.nukkitx.protocol.bedrock.packet.VideoStreamConnectPacket;
import com.nukkitx.protocol.bedrock.transformer.BooleanTransformer;
import com.nukkitx.protocol.bedrock.transformer.FlagTransformer;
import com.nukkitx.protocol.bedrock.transformer.TypeMapTransformer;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v361 extends Bedrock_v354 {
   protected static final TypeMap<EntityFlag> ENTITY_FLAGS;
   protected static final TypeMap<ParticleType> PARTICLE_TYPES;
   protected static final EntityDataTypeMap ENTITY_DATA;
   protected static final TypeMap<LevelEventType> LEVEL_EVENTS;
   protected static final TypeMap<ResourcePackType> RESOURCE_PACK_TYPES;
   public static BedrockCodec CODEC;

   static {
      ENTITY_FLAGS = Bedrock_v354.ENTITY_FLAGS.toBuilder().insert(87, EntityFlag.HIDDEN_WHEN_INVISIBLE).build();
      PARTICLE_TYPES = Bedrock_v354.PARTICLE_TYPES.toBuilder().shift(2, 1).insert(2, ParticleType.BUBBLE_MANUAL).shift(22, 1).insert(22, ParticleType.MOB_PORTAL).shift(24, 1).insert(24, ParticleType.WATER_SPLASH_MANUAL).build();
      ENTITY_DATA = Bedrock_v354.ENTITY_DATA.toBuilder().update(EntityDataTypes.FLAGS, new FlagTransformer(ENTITY_FLAGS, 0)).update(EntityDataTypes.FLAGS_2, new FlagTransformer(ENTITY_FLAGS, 1)).update(EntityDataTypes.AREA_EFFECT_CLOUD_PARTICLE, new TypeMapTransformer(PARTICLE_TYPES)).replace(EntityDataTypes.NPC_DATA, 40, EntityDataFormat.STRING).insert(EntityDataTypes.SKIN_ID, 103, EntityDataFormat.INT).insert(EntityDataTypes.SPAWNING_FRAMES, 104, EntityDataFormat.INT).insert(EntityDataTypes.COMMAND_BLOCK_TICK_DELAY, 105, EntityDataFormat.INT).insert(EntityDataTypes.COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK, 106, EntityDataFormat.BYTE, BooleanTransformer.INSTANCE).build();
      LEVEL_EVENTS = Bedrock_v354.LEVEL_EVENTS.toBuilder().insert(2023, LevelEvent.PARTICLE_TELEPORT_TRAIL).insert(16384, PARTICLE_TYPES).build();
      RESOURCE_PACK_TYPES = TypeMap.builder(ResourcePackType.class).insert(0, ResourcePackType.INVALID).insert(1, ResourcePackType.RESOURCES).insert(2, ResourcePackType.DATA_ADD_ON).insert(3, ResourcePackType.WORLD_TEMPLATE).insert(4, ResourcePackType.ADDON).insert(5, ResourcePackType.SKINS).insert(6, ResourcePackType.CACHED).insert(7, ResourcePackType.COPY_PROTECTED).build();
      CODEC = Bedrock_v354.CODEC.toBuilder().protocolVersion(361).minecraftVersion("1.12.0").helper(() -> new BedrockCodecHelper_v361(ENTITY_DATA, GAME_RULE_TYPES)).deregisterPacket(AddHangingEntityPacket.class).deregisterPacket(LecternUpdatePacket.class).deregisterPacket(VideoStreamConnectPacket.class).updateSerializer(StartGamePacket.class, StartGameSerializer_v361.INSTANCE).updateSerializer(AddPaintingPacket.class, AddPaintingSerializer_v361.INSTANCE).updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS)).updateSerializer(CraftingDataPacket.class, CraftingDataSerializer_v361.INSTANCE).updateSerializer(LevelChunkPacket.class, LevelChunkSerializer_v361.INSTANCE).updateSerializer(CommandBlockUpdatePacket.class, CommandBlockUpdateSerializer_v361.INSTANCE).updateSerializer(ResourcePackDataInfoPacket.class, new ResourcePackDataInfoSerializer_v361(RESOURCE_PACK_TYPES)).updateSerializer(StructureBlockUpdatePacket.class, StructureBlockUpdateSerializer_v361.INSTANCE).registerPacket(LevelEventGenericPacket::new, new LevelEventGenericSerializer_v361(LEVEL_EVENTS), 124, PacketRecipient.CLIENT).registerPacket(LecternUpdatePacket::new, LecternUpdateSerializer_v354.INSTANCE, 125, PacketRecipient.SERVER).registerPacket(VideoStreamConnectPacket::new, VideoStreamConnectSerializer_v361.INSTANCE, 126, PacketRecipient.CLIENT).registerPacket(ClientCacheStatusPacket::new, ClientCacheStatusSerializer_v361.INSTANCE, 129, PacketRecipient.SERVER).registerPacket(StructureTemplateDataRequestPacket::new, StructureTemplateDataRequestSerializer_v361.INSTANCE, 132, PacketRecipient.SERVER).registerPacket(StructureTemplateDataResponsePacket::new, StructureTemplateDataResponseSerializer_v361.INSTANCE, 133, PacketRecipient.CLIENT).registerPacket(UpdateBlockPropertiesPacket::new, UpdateBlockPropertiesSerializer_v361.INSTANCE, 134, PacketRecipient.CLIENT).registerPacket(ClientCacheBlobStatusPacket::new, ClientCacheBlobStatusSerializer_v361.INSTANCE, 135, PacketRecipient.SERVER).registerPacket(ClientCacheMissResponsePacket::new, ClientCacheMissResponseSerializer_v361.INSTANCE, 136, PacketRecipient.CLIENT).build();
   }
}
