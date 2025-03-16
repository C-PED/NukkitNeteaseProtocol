package com.nukkitx.protocol.bedrock.codec.v544;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.v534.BedrockCodecHelper_v534;
import com.nukkitx.protocol.bedrock.codec.v534.Bedrock_v534;
import com.nukkitx.protocol.bedrock.codec.v544.serializer.ClientboundMapItemDataSerializer_v544;
import com.nukkitx.protocol.bedrock.codec.v544.serializer.FeatureRegistrySerializer_v544;
import com.nukkitx.protocol.bedrock.codec.v544.serializer.MapInfoRequestSerializer_v544;
import com.nukkitx.protocol.bedrock.codec.v544.serializer.ModalFormResponseSerializer_v544;
import com.nukkitx.protocol.bedrock.codec.v544.serializer.NetworkChunkPublisherUpdateSerializer_v544;
import com.nukkitx.protocol.bedrock.codec.v544.serializer.StartGameSerializer_v544;
import com.nukkitx.protocol.bedrock.codec.v544.serializer.UpdateAttributesSerializer_v544;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.packet.ClientboundMapItemDataPacket;
import com.nukkitx.protocol.bedrock.packet.FeatureRegistryPacket;
import com.nukkitx.protocol.bedrock.packet.MapInfoRequestPacket;
import com.nukkitx.protocol.bedrock.packet.ModalFormResponsePacket;
import com.nukkitx.protocol.bedrock.packet.NetworkChunkPublisherUpdatePacket;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import com.nukkitx.protocol.bedrock.packet.UpdateAttributesPacket;

public class Bedrock_v544 extends Bedrock_v534 {
   public static final BedrockCodec CODEC;

   static {
      CODEC = Bedrock_v534.CODEC.toBuilder().protocolVersion(544).minecraftVersion("1.19.20").helper(() -> new BedrockCodecHelper_v534(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES, PLAYER_ABILITIES)).updateSerializer(StartGamePacket.class, new StartGameSerializer_v544()).updateSerializer(UpdateAttributesPacket.class, new UpdateAttributesSerializer_v544()).updateSerializer(ClientboundMapItemDataPacket.class, new ClientboundMapItemDataSerializer_v544()).updateSerializer(MapInfoRequestPacket.class, new MapInfoRequestSerializer_v544()).updateSerializer(ModalFormResponsePacket.class, new ModalFormResponseSerializer_v544()).updateSerializer(NetworkChunkPublisherUpdatePacket.class, new NetworkChunkPublisherUpdateSerializer_v544()).registerPacket(FeatureRegistryPacket::new, new FeatureRegistrySerializer_v544(), 191, PacketRecipient.CLIENT).build();
   }
}
