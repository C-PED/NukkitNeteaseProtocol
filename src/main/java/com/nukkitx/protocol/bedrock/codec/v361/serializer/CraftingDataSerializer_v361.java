package com.nukkitx.protocol.bedrock.codec.v361.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v354.serializer.CraftingDataSerializer_v354;
import com.nukkitx.protocol.bedrock.data.definitions.ItemDefinition;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe.ShapedRecipeData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe.ShapelessRecipeData;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.DefaultDescriptor;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.InvalidDescriptor;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import com.nukkitx.protocol.common.util.Preconditions;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CraftingDataSerializer_v361 extends CraftingDataSerializer_v354 {
   public static final CraftingDataSerializer_v361 INSTANCE = new CraftingDataSerializer_v361();

   protected ShapelessRecipeData readShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataType type) {
      String recipeId = helper.readString(buffer);
      List<ItemDescriptorWithCount> inputs = new ObjectArrayList();
      helper.readArray(buffer, inputs, this::readIngredient);
      List<ItemData> outputs = new ObjectArrayList();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, outputs, helper::readItem);
      UUID uuid = helper.readUuid(buffer);
      String craftingTag = helper.readString(buffer);
      int priority = VarInts.readInt(buffer);
      return ShapelessRecipeData.of(type, recipeId, inputs, outputs, uuid, craftingTag, priority, -1);
   }

   protected void writeShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipeData data) {
      helper.writeString(buffer, data.getId());
      helper.writeArray(buffer, data.getIngredients(), this::writeIngredient);
      List var10002 = data.getResults();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeItem);
      helper.writeUuid(buffer, data.getUuid());
      helper.writeString(buffer, data.getTag());
      VarInts.writeInt(buffer, data.getPriority());
   }

   protected ShapedRecipeData readShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataType type) {
      String recipeId = helper.readString(buffer);
      int width = VarInts.readInt(buffer);
      int height = VarInts.readInt(buffer);
      int inputCount = width * height;
      List<ItemDescriptorWithCount> inputs = new ObjectArrayList();

      for(int i = 0; i < inputCount; ++i) {
         inputs.add(this.readIngredient(buffer, helper));
      }

      List<ItemData> outputs = new ObjectArrayList();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, outputs, helper::readItem);
      UUID uuid = helper.readUuid(buffer);
      String craftingTag = helper.readString(buffer);
      int priority = VarInts.readInt(buffer);
      return ShapedRecipeData.of(type, recipeId, width, height, inputs, outputs, uuid, craftingTag, priority, -1);
   }

   protected void writeShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapedRecipeData data) {
      helper.writeString(buffer, data.getId());
      VarInts.writeInt(buffer, data.getWidth());
      VarInts.writeInt(buffer, data.getHeight());
      int count = data.getWidth() * data.getHeight();
      List<ItemDescriptorWithCount> inputs = data.getIngredients();

      for(int i = 0; i < count; ++i) {
         this.writeIngredient(buffer, (ItemDescriptorWithCount)inputs.get(i));
      }

      List var10002 = data.getResults();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeItem);
      helper.writeUuid(buffer, data.getUuid());
      helper.writeString(buffer, data.getTag());
      VarInts.writeInt(buffer, data.getPriority());
   }

   protected ItemDescriptorWithCount readIngredient(ByteBuf buffer, BedrockCodecHelper helper) {
      int id = VarInts.readInt(buffer);
      ItemDefinition definition = (ItemDefinition)helper.getItemDefinitions().getDefinition(id);
      if (id == 0) {
         return ItemDescriptorWithCount.EMPTY;
      } else {
         int auxValue = this.fromAuxValue(VarInts.readInt(buffer));
         int stackSize = VarInts.readInt(buffer);
         return new ItemDescriptorWithCount(new DefaultDescriptor(definition, auxValue), stackSize);
      }
   }

   protected void writeIngredient(ByteBuf buffer, ItemDescriptorWithCount ingredient) {
      Objects.requireNonNull(ingredient, "ingredient is null");
      if (ingredient != ItemDescriptorWithCount.EMPTY && ingredient.getDescriptor() != InvalidDescriptor.INSTANCE) {
         Preconditions.checkArgument(ingredient.getDescriptor() instanceof DefaultDescriptor, "Descriptor must be of type DefaultDescriptor");
         DefaultDescriptor descriptor = (DefaultDescriptor)ingredient.getDescriptor();
         int id = descriptor.getItemId().getRuntimeId();
         VarInts.writeInt(buffer, id);
         if (id != 0) {
            VarInts.writeInt(buffer, this.toAuxValue(descriptor.getAuxValue()));
            VarInts.writeInt(buffer, ingredient.getCount());
         }

      } else {
         VarInts.writeInt(buffer, 0);
      }
   }

   protected int fromAuxValue(int value) {
      return value == 32767 ? -1 : value;
   }

   protected int toAuxValue(int value) {
      return value == -1 ? 32767 : value;
   }

   protected CraftingDataSerializer_v361() {
   }
}
