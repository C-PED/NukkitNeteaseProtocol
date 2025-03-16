package com.nukkitx.protocol.bedrock.codec.v389;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelEventSerializer_v291;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.LevelEventGenericSerializer_v361;
import com.nukkitx.protocol.bedrock.codec.v388.BedrockCodecHelper_v388;
import com.nukkitx.protocol.bedrock.codec.v388.Bedrock_v388;
import com.nukkitx.protocol.bedrock.codec.v389.serializer.EventSerializer_v389;
import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.bedrock.data.ParticleType;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataTypes;
import com.nukkitx.protocol.bedrock.packet.EventPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventGenericPacket;
import com.nukkitx.protocol.bedrock.packet.LevelEventPacket;
import com.nukkitx.protocol.bedrock.transformer.TypeMapTransformer;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v389 extends Bedrock_v388 {
   protected static final TypeMap<ParticleType> PARTICLE_TYPES;
   protected static final TypeMap<LevelEventType> LEVEL_EVENTS;
   protected static final EntityDataTypeMap ENTITY_DATA;
   public static BedrockCodec CODEC;

   static {
      PARTICLE_TYPES = Bedrock_v388.PARTICLE_TYPES.toBuilder().shift(28, 1).insert(28, ParticleType.DRIP_HONEY).insert(59, ParticleType.SHULKER_BULLET).insert(60, ParticleType.BLEACH).insert(61, ParticleType.DRAGON_DESTROY_BLOCK).insert(62, ParticleType.MYCELIUM_DUST).insert(63, ParticleType.FALLING_BORDER_DUST).insert(64, ParticleType.CAMPFIRE_SMOKE).insert(65, ParticleType.CAMPFIRE_SMOKE_TALL).insert(66, ParticleType.DRAGON_BREATH_FIRE).insert(67, ParticleType.DRAGON_BREATH_TRAIL).build();
      LEVEL_EVENTS = Bedrock_v388.LEVEL_EVENTS.toBuilder().insert(16384, PARTICLE_TYPES).build();
      ENTITY_DATA = Bedrock_v388.ENTITY_DATA.toBuilder().update(EntityDataTypes.AREA_EFFECT_CLOUD_PARTICLE, new TypeMapTransformer(PARTICLE_TYPES)).build();
      CODEC = Bedrock_v388.CODEC.toBuilder().protocolVersion(389).minecraftVersion("1.14.0").helper(() -> new BedrockCodecHelper_v388(ENTITY_DATA, GAME_RULE_TYPES)).updateSerializer(EventPacket.class, EventSerializer_v389.INSTANCE).updateSerializer(LevelEventPacket.class, new LevelEventSerializer_v291(LEVEL_EVENTS)).updateSerializer(LevelEventGenericPacket.class, new LevelEventGenericSerializer_v361(LEVEL_EVENTS)).build();
   }
}
