package com.nukkitx.protocol.bedrock.codec.v471;

import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v465.BedrockCodecHelper_v465;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.CraftGrindstoneAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.CraftLoomAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;

public class BedrockCodecHelper_v471 extends BedrockCodecHelper_v465 {
   public BedrockCodecHelper_v471(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerSlotType> containerSlotTypes) {
      super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes);
   }

   protected ItemStackRequestAction readRequestActionData(ByteBuf byteBuf, ItemStackRequestActionType type) {
      switch (type) {
         case CRAFT_REPAIR_AND_DISENCHANT:
            return new CraftGrindstoneAction(VarInts.readUnsignedInt(byteBuf), VarInts.readInt(byteBuf));
         case CRAFT_LOOM:
            return new CraftLoomAction(this.readString(byteBuf));
         default:
            return super.readRequestActionData(byteBuf, type);
      }
   }

   protected void writeRequestActionData(ByteBuf byteBuf, ItemStackRequestAction action) {
      switch (action.getType()) {
         case CRAFT_REPAIR_AND_DISENCHANT:
            CraftGrindstoneAction actionData = (CraftGrindstoneAction)action;
            VarInts.writeUnsignedInt(byteBuf, actionData.getRecipeNetworkId());
            VarInts.writeInt(byteBuf, actionData.getRepairCost());
            return;
         case CRAFT_LOOM:
            this.writeString(byteBuf, ((CraftLoomAction)action).getPatternId());
            return;
         default:
            super.writeRequestActionData(byteBuf, action);
      }
   }
}
