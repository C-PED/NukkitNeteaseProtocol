package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseSlot;
import com.nukkitx.protocol.bedrock.packet.ItemStackResponsePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ItemStackResponseSerializer_v407 implements BedrockPacketSerializer<ItemStackResponsePacket> {
   public static final ItemStackResponseSerializer_v407 INSTANCE = new ItemStackResponseSerializer_v407();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ItemStackResponsePacket packet) {
      helper.writeArray(buffer, packet.getEntries(), (buf, response) -> {
         buf.writeBoolean(response.isSuccess());
         VarInts.writeInt(buffer, response.getRequestId());

         if (!response.isSuccess())
            return;

         helper.writeArray(buf, response.getContainers(), (buf2, containerEntry) -> {
            helper.writeContainerSlotType(buf2, containerEntry.getContainer());
            helper.writeArray(buf2, containerEntry.getItems(), this::writeItemEntry);
         });
      });
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ItemStackResponsePacket packet) {
      List<ItemStackResponse> entries = packet.getEntries();
      helper.readArray(buffer, entries, buf -> {
         boolean success = buf.readBoolean();
         int requestId = VarInts.readInt(buf);

         if (!success)
            return new ItemStackResponse(success, requestId, Collections.emptyList());

         List<ItemStackResponseContainer> containerEntries = new ArrayList<>();
         helper.readArray(buf, containerEntries, buf2 -> {
            ContainerSlotType container = helper.readContainerSlotType(buf2);

            List<ItemStackResponseSlot> itemEntries = new ArrayList<>();
            helper.readArray(buf2, itemEntries, byteBuf -> this.readItemEntry(byteBuf, helper));
            return new ItemStackResponseContainer(container, itemEntries);
         });
         return new ItemStackResponse(success, requestId, containerEntries);
      });
   }

   protected ItemStackResponseSlot readItemEntry(ByteBuf buffer, BedrockCodecHelper helper) {
      return new ItemStackResponseSlot(buffer.readUnsignedByte(), buffer.readUnsignedByte(), buffer.readUnsignedByte(), VarInts.readInt(buffer), "", 0);
   }

   protected void writeItemEntry(ByteBuf buffer, BedrockCodecHelper helper, ItemStackResponseSlot itemEntry) {
      buffer.writeByte(itemEntry.getSlot());
      buffer.writeByte(itemEntry.getHotbarSlot());
      buffer.writeByte(itemEntry.getCount());
      VarInts.writeInt(buffer, itemEntry.getStackNetworkId());
   }

   protected ItemStackResponseSerializer_v407() {
   }
}
