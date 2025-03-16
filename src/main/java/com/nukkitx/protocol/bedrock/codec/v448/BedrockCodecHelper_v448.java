package com.nukkitx.protocol.bedrock.codec.v448;

import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v440.BedrockCodecHelper_v440;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.AutoCraftRecipeAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.Collections;

public class BedrockCodecHelper_v448 extends BedrockCodecHelper_v440 {
   public BedrockCodecHelper_v448(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerSlotType> containerSlotTypes) {
      super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes);
   }

   protected ItemStackRequestAction readRequestActionData(ByteBuf byteBuf, ItemStackRequestActionType type) {
      return (ItemStackRequestAction)(type == ItemStackRequestActionType.CRAFT_RECIPE_AUTO ? new AutoCraftRecipeAction(VarInts.readUnsignedInt(byteBuf), byteBuf.readUnsignedByte(), Collections.emptyList()) : super.readRequestActionData(byteBuf, type));
   }

   protected void writeRequestActionData(ByteBuf byteBuf, ItemStackRequestAction action) {
      super.writeRequestActionData(byteBuf, action);
      if (action.getType() == ItemStackRequestActionType.CRAFT_RECIPE_AUTO) {
         byteBuf.writeByte(((AutoCraftRecipeAction)action).getTimesCrafted());
      }

   }
}
