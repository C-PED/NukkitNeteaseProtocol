package com.nukkitx.protocol.bedrock.codec.v354.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.CraftingDataSerializer_v291;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe.FurnaceRecipeData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe.ShapedRecipeData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.recipe.ShapelessRecipeData;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

public class CraftingDataSerializer_v354 extends CraftingDataSerializer_v291 {
   public static final CraftingDataSerializer_v354 INSTANCE = new CraftingDataSerializer_v354();

   protected ShapelessRecipeData readShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataType type) {
      List<ItemDescriptorWithCount> inputs = new ObjectArrayList();
      helper.readArray(buffer, inputs, buf -> ItemDescriptorWithCount.fromItem(helper.readItem(buf)));
      List<ItemData> outputs = new ObjectArrayList();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, outputs, helper::readItem);
      UUID uuid = helper.readUuid(buffer);
      String craftingTag = helper.readString(buffer);
      return ShapelessRecipeData.of(type, "", inputs, outputs, uuid, craftingTag, 0, -1);
   }

   protected void writeShapelessRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapelessRecipeData data) {
      super.writeShapelessRecipe(buffer, helper, data);
      helper.writeString(buffer, data.getTag());
   }

   protected ShapedRecipeData readShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataType type) {
      int width = VarInts.readInt(buffer);
      int height = VarInts.readInt(buffer);
      int inputCount = width * height;
      List<ItemDescriptorWithCount> inputs = new ObjectArrayList();

      for(int i = 0; i < inputCount; ++i) {
         inputs.add(ItemDescriptorWithCount.fromItem(helper.readItem(buffer)));
      }

      List<ItemData> outputs = new ObjectArrayList();
      Objects.requireNonNull(helper);
      helper.readArray(buffer, outputs, helper::readItem);
      UUID uuid = helper.readUuid(buffer);
      String craftingTag = helper.readString(buffer);
      return ShapedRecipeData.of(type, "", width, height, inputs, outputs, uuid, craftingTag, 0, -1);
   }

   protected void writeShapedRecipe(ByteBuf buffer, BedrockCodecHelper helper, ShapedRecipeData data) {
      super.writeShapedRecipe(buffer, helper, data);
      helper.writeString(buffer, data.getTag());
   }

   protected FurnaceRecipeData readFurnaceRecipe(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataType type) {
      int inputId = VarInts.readInt(buffer);
      int inputData = type == CraftingDataType.FURNACE_DATA ? VarInts.readInt(buffer) : -1;
      ItemData result = helper.readItem(buffer);
      String craftingTag = helper.readString(buffer);
      return FurnaceRecipeData.of(type, inputId, inputData, result, craftingTag);
   }

   protected void writeFurnaceRecipe(ByteBuf buffer, BedrockCodecHelper helper, FurnaceRecipeData data) {
      super.writeFurnaceRecipe(buffer, helper, data);
      helper.writeString(buffer, data.getTag());
   }

   protected CraftingDataSerializer_v354() {
   }
}
