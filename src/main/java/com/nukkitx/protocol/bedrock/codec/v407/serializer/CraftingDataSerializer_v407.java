package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v388.serializer.CraftingDataSerializer_v388;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.ContainerMixData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.PotionMixData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe.FurnaceRecipeData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe.MultiRecipeData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe.ShapedRecipeData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe.ShapelessRecipeData;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import com.nukkitx.protocol.common.util.Preconditions;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CraftingDataSerializer_v407 extends CraftingDataSerializer_v388 {
   public static final CraftingDataSerializer_v407 INSTANCE = new CraftingDataSerializer_v407();

   protected RecipeData readEntry(ByteBuf buffer, BedrockCodecHelper helper) {
      int typeInt = VarInts.readInt(buffer);
      CraftingDataType type = CraftingDataType.byId(typeInt);
      switch (type) {
         case SHAPELESS:
         case SHAPELESS_CHEMISTRY:
         case SHULKER_BOX:
            return this.readShapelessRecipe(buffer, helper, type);
         case SHAPED:
         case SHAPED_CHEMISTRY:
            return this.readShapedRecipe(buffer, helper, type);
         case FURNACE:
            return this.readFurnaceRecipe(buffer, helper, type);
         case FURNACE_DATA:
            return this.readFurnaceDataRecipe(buffer, helper, type);
         case MULTI:
            return this.readMultiRecipe(buffer, helper, type);
         default:
            throw new IllegalArgumentException("Unhandled crafting data type: " + type);
      }
   }

   protected void writeEntry(ByteBuf buffer, BedrockCodecHelper helper, RecipeData craftingData) {
      VarInts.writeInt(buffer, craftingData.getType().ordinal());
      switch (craftingData.getType()) {
         case SHAPELESS:
         case SHAPELESS_CHEMISTRY:
         case SHULKER_BOX:
            this.writeShapelessRecipe(buffer, helper, (ShapelessRecipeData)craftingData);
            break;
         case SHAPED:
         case SHAPED_CHEMISTRY:
            this.writeShapedRecipe(buffer, helper, (ShapedRecipeData)craftingData);
            break;
         case FURNACE:
            this.writeFurnaceRecipe(buffer, helper, (FurnaceRecipeData)craftingData);
            break;
         case FURNACE_DATA:
            this.writeFurnaceDataRecipe(buffer, helper, (FurnaceRecipeData)craftingData);
            break;
         case MULTI:
            this.writeMultiRecipe(buffer, helper, (MultiRecipeData)craftingData);
      }

   }

   protected ShapelessRecipeData readShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataType type) {
      String recipeId = helper.readString(buffer);
      List<ItemDescriptorWithCount> inputs = new ObjectArrayList();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, inputs, helper::readIngredient);
      List<ItemData> outputs = new ObjectArrayList();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, outputs, helper::readItemInstance);
      UUID uuid = helper.readUuid(buffer);
      String craftingTag = helper.readString(buffer);
      int priority = VarInts.readInt(buffer);
      int networkId = VarInts.readUnsignedInt(buffer);
      return ShapelessRecipeData.of(type, recipeId, inputs, outputs, uuid, craftingTag, priority, networkId);
   }

   protected void writeShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipeData data) {
      helper.writeString(buffer, data.getId());
      List var10002 = data.getIngredients();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeIngredient);
      var10002 = data.getResults();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeItemInstance);
      helper.writeUuid(buffer, data.getUuid());
      helper.writeString(buffer, data.getTag());
      VarInts.writeInt(buffer, data.getPriority());
      VarInts.writeUnsignedInt(buffer, data.getNetId());
   }

   protected ShapedRecipeData readShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataType type) {
      String recipeId = helper.readString(buffer);
      int width = VarInts.readInt(buffer);
      int height = VarInts.readInt(buffer);
      int inputCount = width * height;
      List<ItemDescriptorWithCount> inputs = new ObjectArrayList();

      for(int i = 0; i < inputCount; ++i) {
         inputs.add(helper.readIngredient(buffer));
      }

      List<ItemData> outputs = new ObjectArrayList();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, outputs, helper::readItemInstance);
      UUID uuid = helper.readUuid(buffer);
      String craftingTag = helper.readString(buffer);
      int priority = VarInts.readInt(buffer);
      int networkId = VarInts.readUnsignedInt(buffer);
      return ShapedRecipeData.of(type, recipeId, width, height, inputs, outputs, uuid, craftingTag, priority, networkId);
   }

   protected void writeShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapedRecipeData data) {
      helper.writeString(buffer, data.getId());
      VarInts.writeInt(buffer, data.getWidth());
      VarInts.writeInt(buffer, data.getHeight());
      int count = data.getWidth() * data.getHeight();
      List<ItemDescriptorWithCount> inputs = data.getIngredients();

      for(int i = 0; i < count; ++i) {
         helper.writeIngredient(buffer, (ItemDescriptorWithCount)inputs.get(i));
      }

      List var10002 = data.getResults();
      Objects.requireNonNull(helper);
      helper.writeArray(buffer, var10002, helper::writeItemInstance);
      helper.writeUuid(buffer, data.getUuid());
      helper.writeString(buffer, data.getTag());
      VarInts.writeInt(buffer, data.getPriority());
      VarInts.writeUnsignedInt(buffer, data.getNetId());
   }

   protected FurnaceRecipeData readFurnaceRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataType type) {
      int inputId = VarInts.readInt(buffer);
      ItemData result = helper.readItemInstance(buffer);
      String craftingTag = helper.readString(buffer);
      return FurnaceRecipeData.of(type, inputId, -1, result, craftingTag);
   }

   protected void writeFurnaceRecipe(ByteBuf buffer, BedrockCodecHelper helper, FurnaceRecipeData data) {
      VarInts.writeInt(buffer, data.getInputId());
      helper.writeItemInstance(buffer, data.getResult());
      helper.writeString(buffer, data.getTag());
   }

   protected FurnaceRecipeData readFurnaceDataRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataType type) {
      int inputId = VarInts.readInt(buffer);
      int inputDamage = VarInts.readInt(buffer);
      ItemData result = helper.readItemInstance(buffer);
      String craftingTag = helper.readString(buffer);
      return FurnaceRecipeData.of(type, inputId, inputDamage, result, craftingTag);
   }

   protected void writeFurnaceDataRecipe(ByteBuf buffer, BedrockCodecHelper helper, FurnaceRecipeData data) {
      VarInts.writeInt(buffer, data.getInputId());
      VarInts.writeInt(buffer, data.getInputData());
      helper.writeItemInstance(buffer, data.getResult());
      helper.writeString(buffer, data.getTag());
   }

   protected MultiRecipeData readMultiRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataType type) {
      UUID uuid = helper.readUuid(buffer);
      int networkId = VarInts.readUnsignedInt(buffer);
      return MultiRecipeData.of(uuid, networkId);
   }

   protected void writeMultiRecipe(ByteBuf buffer, BedrockCodecHelper helper, MultiRecipeData data) {
      helper.writeUuid(buffer, data.getUuid());
      VarInts.writeUnsignedInt(buffer, data.getNetId());
   }

   protected PotionMixData readPotionMixData(ByteBuf buffer, BedrockCodecHelper helper) {
      return new PotionMixData(VarInts.readInt(buffer), VarInts.readInt(buffer), VarInts.readInt(buffer), VarInts.readInt(buffer), VarInts.readInt(buffer), VarInts.readInt(buffer));
   }

   protected void writePotionMixData(ByteBuf buffer, BedrockCodecHelper helper, PotionMixData data) {
      Preconditions.checkNotNull(data, "data is null");
      VarInts.writeInt(buffer, data.getInputId());
      VarInts.writeInt(buffer, data.getInputMeta());
      VarInts.writeInt(buffer, data.getReagentId());
      VarInts.writeInt(buffer, data.getReagentMeta());
      VarInts.writeInt(buffer, data.getOutputId());
      VarInts.writeInt(buffer, data.getOutputMeta());
   }

   protected ContainerMixData readContainerMixData(ByteBuf buffer, BedrockCodecHelper helper) {
      return new ContainerMixData(VarInts.readInt(buffer), VarInts.readInt(buffer), VarInts.readInt(buffer));
   }

   protected void writeContainerMixData(ByteBuf buffer, BedrockCodecHelper helper, ContainerMixData data) {
      Preconditions.checkNotNull(data, "data is null");
      VarInts.writeInt(buffer, data.getInputId());
      VarInts.writeInt(buffer, data.getReagentId());
      VarInts.writeInt(buffer, data.getOutputId());
   }

   protected CraftingDataSerializer_v407() {
   }
}
