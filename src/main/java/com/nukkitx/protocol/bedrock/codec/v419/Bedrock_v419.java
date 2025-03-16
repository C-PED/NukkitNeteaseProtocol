package com.nukkitx.protocol.bedrock.codec.v419;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.LevelSoundEvent1Serializer_v291;
import com.nukkitx.protocol.bedrock.codec.v313.serializer.LevelSoundEvent2Serializer_v313;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.LevelSoundEventSerializer_v332;
import com.nukkitx.protocol.bedrock.codec.v388.serializer.AvailableCommandsSerializer_v388;
import com.nukkitx.protocol.bedrock.codec.v408.Bedrock_v408;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.AnimateEntitySerializer_v419;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.CameraShakeSerializer_v419;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.ContainerCloseSerializer_v419;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.CorrectPlayerMovePredictionSerializer_v419;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.ItemComponentSerializer_v419;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.ItemStackResponseSerializer_v419;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.MotionPredictionHintsSerializer_v419;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.MoveEntityDeltaSerializer_v419;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.MovePlayerSerializer_v419;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.PlayerAuthInputSerializer_v419;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.PlayerFogSerializer_v419;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.ResourcePackStackSerializer_v419;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.SetEntityDataSerializer_v419;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.StartGameSerializer_v419;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.UpdateAttributesSerializer_v419;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.data.SoundEvent;
import com.nukkitx.protocol.bedrock.data.command.CommandParam;
import com.nukkitx.protocol.bedrock.packet.AnimateEntityPacket;
import com.nukkitx.protocol.bedrock.packet.AvailableCommandsPacket;
import com.nukkitx.protocol.bedrock.packet.CameraShakePacket;
import com.nukkitx.protocol.bedrock.packet.ContainerClosePacket;
import com.nukkitx.protocol.bedrock.packet.CorrectPlayerMovePredictionPacket;
import com.nukkitx.protocol.bedrock.packet.ItemComponentPacket;
import com.nukkitx.protocol.bedrock.packet.ItemStackResponsePacket;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent1Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEvent2Packet;
import com.nukkitx.protocol.bedrock.packet.LevelSoundEventPacket;
import com.nukkitx.protocol.bedrock.packet.MotionPredictionHintsPacket;
import com.nukkitx.protocol.bedrock.packet.MoveEntityDeltaPacket;
import com.nukkitx.protocol.bedrock.packet.MovePlayerPacket;
import com.nukkitx.protocol.bedrock.packet.PlayerAuthInputPacket;
import com.nukkitx.protocol.bedrock.packet.PlayerFogPacket;
import com.nukkitx.protocol.bedrock.packet.ResourcePackStackPacket;
import com.nukkitx.protocol.bedrock.packet.SetEntityDataPacket;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import com.nukkitx.protocol.bedrock.packet.UpdateAttributesPacket;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v419 extends Bedrock_v408 {
   protected static final TypeMap<CommandParam> COMMAND_PARAMS;
   protected static final TypeMap<SoundEvent> SOUND_EVENTS;
   public static BedrockCodec CODEC;

   static {
      COMMAND_PARAMS = Bedrock_v408.COMMAND_PARAMS.toBuilder().shift(7, 1).shift(30, 1).build();
      SOUND_EVENTS = Bedrock_v408.SOUND_EVENTS.toBuilder().replace(317, SoundEvent.EQUIP_NETHERITE).insert(318, SoundEvent.UNDEFINED).build();
      CODEC = Bedrock_v408.CODEC.toBuilder().protocolVersion(419).minecraftVersion("1.16.100").helper(() -> new BedrockCodecHelper_v419(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES)).updateSerializer(ResourcePackStackPacket.class, ResourcePackStackSerializer_v419.INSTANCE).updateSerializer(StartGamePacket.class, StartGameSerializer_v419.INSTANCE).updateSerializer(MovePlayerPacket.class, MovePlayerSerializer_v419.INSTANCE).updateSerializer(UpdateAttributesPacket.class, UpdateAttributesSerializer_v419.INSTANCE).updateSerializer(SetEntityDataPacket.class, SetEntityDataSerializer_v419.INSTANCE).updateSerializer(ContainerClosePacket.class, ContainerCloseSerializer_v419.INSTANCE).updateSerializer(MoveEntityDeltaPacket.class, MoveEntityDeltaSerializer_v419.INSTANCE).updateSerializer(PlayerAuthInputPacket.class, PlayerAuthInputSerializer_v419.INSTANCE).updateSerializer(ItemStackResponsePacket.class, ItemStackResponseSerializer_v419.INSTANCE).updateSerializer(LevelSoundEvent1Packet.class, new LevelSoundEvent1Serializer_v291(SOUND_EVENTS)).updateSerializer(LevelSoundEvent2Packet.class, new LevelSoundEvent2Serializer_v313(SOUND_EVENTS)).updateSerializer(LevelSoundEventPacket.class, new LevelSoundEventSerializer_v332(SOUND_EVENTS)).updateSerializer(AvailableCommandsPacket.class, new AvailableCommandsSerializer_v388(COMMAND_PARAMS)).registerPacket(MotionPredictionHintsPacket::new, MotionPredictionHintsSerializer_v419.INSTANCE, 157, PacketRecipient.CLIENT).registerPacket(AnimateEntityPacket::new, AnimateEntitySerializer_v419.INSTANCE, 158, PacketRecipient.CLIENT).registerPacket(CameraShakePacket::new, CameraShakeSerializer_v419.INSTANCE, 159, PacketRecipient.CLIENT).registerPacket(PlayerFogPacket::new, PlayerFogSerializer_v419.INSTANCE, 160, PacketRecipient.CLIENT).registerPacket(CorrectPlayerMovePredictionPacket::new, CorrectPlayerMovePredictionSerializer_v419.INSTANCE, 161, PacketRecipient.CLIENT).registerPacket(ItemComponentPacket::new, ItemComponentSerializer_v419.INSTANCE, 162, PacketRecipient.CLIENT).build();
   }
}
