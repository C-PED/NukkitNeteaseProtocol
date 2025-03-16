package com.nukkitx.protocol.bedrock.codec.v388.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v361.serializer.CraftingDataSerializer_v361;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.ContainerMixData;
import com.nukkitx.protocol.bedrock.data.inventory.crafting.PotionMixData;
import com.nukkitx.protocol.bedrock.packet.CraftingDataPacket;
import com.nukkitx.protocol.common.util.TriConsumer;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.function.BiFunction;

public class CraftingDataSerializer_v388 extends CraftingDataSerializer_v361 {
   public static final CraftingDataSerializer_v388 INSTANCE = new CraftingDataSerializer_v388();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
      helper.writeArray(buffer, packet.getCraftingData(), this::writeEntry);
      helper.writeArray(buffer, packet.getPotionMixData(), this::writePotionMixData);
      helper.writeArray(buffer, packet.getContainerMixData(), this::writeContainerMixData);
      buffer.writeBoolean(packet.isCleanRecipes());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, CraftingDataPacket packet) {
      helper.readArray(buffer, packet.getCraftingData(), this::readEntry);
      helper.readArray(buffer, packet.getPotionMixData(), this::readPotionMixData);
      helper.readArray(buffer, packet.getContainerMixData(), this::readContainerMixData);
      packet.setCleanRecipes(buffer.readBoolean());
   }

   protected PotionMixData readPotionMixData(ByteBuf buffer, BedrockCodecHelper helper) {
      int fromPotionId = VarInts.readInt(buffer);
      int ingredient = VarInts.readInt(buffer);
      int toPotionId = VarInts.readInt(buffer);
      return new PotionMixData(fromPotionId, 0, ingredient, 0, toPotionId, 0);
   }

   protected void writePotionMixData(ByteBuf buffer, BedrockCodecHelper helper, PotionMixData potionMixData) {
      VarInts.writeInt(buffer, potionMixData.getInputId());
      VarInts.writeInt(buffer, potionMixData.getReagentId());
      VarInts.writeInt(buffer, potionMixData.getOutputId());
   }

   protected ContainerMixData readContainerMixData(ByteBuf buffer, BedrockCodecHelper helper) {
      int fromItemId = VarInts.readInt(buffer);
      int ingredient = VarInts.readInt(buffer);
      int toItemId = VarInts.readInt(buffer);
      return new ContainerMixData(fromItemId, ingredient, toItemId);
   }

   protected void writeContainerMixData(ByteBuf buffer, BedrockCodecHelper helper, ContainerMixData containerMixData) {
      VarInts.writeInt(buffer, containerMixData.getInputId());
      VarInts.writeInt(buffer, containerMixData.getReagentId());
      VarInts.writeInt(buffer, containerMixData.getOutputId());
   }

   protected CraftingDataSerializer_v388() {
   }
}
