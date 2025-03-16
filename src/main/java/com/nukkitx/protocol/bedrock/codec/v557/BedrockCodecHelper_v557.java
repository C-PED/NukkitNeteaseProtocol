package com.nukkitx.protocol.bedrock.codec.v557;

import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v554.BedrockCodecHelper_v554;
import com.nukkitx.protocol.bedrock.data.Ability;
import com.nukkitx.protocol.bedrock.data.entity.EntityProperties;
import com.nukkitx.protocol.bedrock.data.entity.FloatEntityProperty;
import com.nukkitx.protocol.bedrock.data.entity.IntEntityProperty;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.AutoCraftRecipeAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class BedrockCodecHelper_v557 extends BedrockCodecHelper_v554 {
   public BedrockCodecHelper_v557(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerSlotType> containerSlotTypes, TypeMap<Ability> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
      super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
   }

   public void readEntityProperties(ByteBuf buffer, EntityProperties properties) {
      this.readArray(buffer, properties.getIntProperties(), (byteBuf) -> {
         int index = VarInts.readUnsignedInt(byteBuf);
         int value = VarInts.readInt(byteBuf);
         return new IntEntityProperty(index, value);
      });
      this.readArray(buffer, properties.getFloatProperties(), (byteBuf) -> {
         int index = VarInts.readUnsignedInt(byteBuf);
         float value = byteBuf.readFloatLE();
         return new FloatEntityProperty(index, value);
      });
   }

   public void writeEntityProperties(ByteBuf buffer, EntityProperties properties) {
      this.writeArray(buffer, properties.getIntProperties(), (byteBuf, property) -> {
         VarInts.writeUnsignedInt(byteBuf, property.getIndex());
         VarInts.writeInt(byteBuf, property.getValue());
      });
      this.writeArray(buffer, properties.getFloatProperties(), (byteBuf, property) -> {
         VarInts.writeUnsignedInt(byteBuf, property.getIndex());
         byteBuf.writeFloatLE(property.getValue());
      });
   }

   protected ItemStackRequestAction readRequestActionData(ByteBuf byteBuf, ItemStackRequestActionType type) {
      if (type == ItemStackRequestActionType.CRAFT_RECIPE_AUTO) {
         int recipeId = VarInts.readUnsignedInt(byteBuf);
         int timesCrafted = byteBuf.readUnsignedByte();
         List<ItemDescriptorWithCount> ingredients = new ObjectArrayList();
         this.readArray(byteBuf, ingredients, ByteBuf::readUnsignedByte, (buf, helper) -> helper.readIngredient(buf));
         return new AutoCraftRecipeAction(recipeId, timesCrafted, ingredients);
      } else {
         return super.readRequestActionData(byteBuf, type);
      }
   }

   protected void writeRequestActionData(ByteBuf byteBuf, ItemStackRequestAction action) {
      super.writeRequestActionData(byteBuf, action);
      if (action.getType() == ItemStackRequestActionType.CRAFT_RECIPE_AUTO) {
         List<ItemDescriptorWithCount> ingredients = ((AutoCraftRecipeAction)action).getIngredients();
         byteBuf.writeByte(ingredients.size());
         this.writeArray(byteBuf, ingredients, this::writeIngredient);
      }

   }
}
