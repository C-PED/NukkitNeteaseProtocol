package com.nukkitx.protocol.bedrock.codec.v422;

import com.nukkitx.protocol.bedrock.codec.BedrockCodec;
import com.nukkitx.protocol.bedrock.codec.v419.Bedrock_v419;
import com.nukkitx.protocol.bedrock.codec.v422.serializer.FilterTextSerializer_v422;
import com.nukkitx.protocol.bedrock.codec.v422.serializer.ItemStackResponseSerializer_v422;
import com.nukkitx.protocol.bedrock.codec.v422.serializer.ResourcePacksInfoSerializer_v422;
import com.nukkitx.protocol.bedrock.data.PacketRecipient;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import com.nukkitx.protocol.bedrock.packet.FilterTextPacket;
import com.nukkitx.protocol.bedrock.packet.ItemStackResponsePacket;
import com.nukkitx.protocol.bedrock.packet.ResourcePacksInfoPacket;
import com.nukkitx.protocol.common.util.TypeMap;

public class Bedrock_v422 extends Bedrock_v419 {
   protected static final TypeMap<ItemStackRequestActionType> ITEM_STACK_REQUEST_TYPES;
   public static BedrockCodec CODEC;

   static {
      ITEM_STACK_REQUEST_TYPES = Bedrock_v419.ITEM_STACK_REQUEST_TYPES.toBuilder().shift(12, 1).insert(12, ItemStackRequestActionType.CRAFT_RECIPE_OPTIONAL).build();
      CODEC = Bedrock_v419.CODEC.toBuilder().protocolVersion(422).minecraftVersion("1.16.200").helper(() -> new BedrockCodecHelper_v422(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES)).updateSerializer(ResourcePacksInfoPacket.class, ResourcePacksInfoSerializer_v422.INSTANCE).updateSerializer(ItemStackResponsePacket.class, ItemStackResponseSerializer_v422.INSTANCE).registerPacket(FilterTextPacket::new, FilterTextSerializer_v422.INSTANCE, 163, PacketRecipient.BOTH).build();
   }
}
