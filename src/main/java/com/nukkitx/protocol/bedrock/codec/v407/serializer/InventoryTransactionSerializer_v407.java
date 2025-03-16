package com.nukkitx.protocol.bedrock.codec.v407.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v291.serializer.InventoryTransactionSerializer_v291;
import com.nukkitx.protocol.bedrock.data.inventory.transaction.InventoryTransactionType;
import com.nukkitx.protocol.bedrock.data.inventory.transaction.LegacySetItemSlotData;
import com.nukkitx.protocol.bedrock.packet.InventoryTransactionPacket;
import com.nukkitx.protocol.common.util.TriConsumer;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.function.BiFunction;

public class InventoryTransactionSerializer_v407 extends InventoryTransactionSerializer_v291 {
   public static final InventoryTransactionSerializer_v407 INSTANCE = new InventoryTransactionSerializer_v407();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
      int legacyRequestId = packet.getLegacyRequestId();
      VarInts.writeInt(buffer, legacyRequestId);
      if (legacyRequestId < -1 && (legacyRequestId & 1) == 0) {
         helper.writeArray(buffer, packet.getLegacySlots(), (buf, packetHelper, data) -> {
            buf.writeByte(data.getContainerId());
            packetHelper.writeByteArray(buf, data.getSlots());
         });
      }

      InventoryTransactionType transactionType = packet.getTransactionType();
      VarInts.writeUnsignedInt(buffer, transactionType.ordinal());
      helper.writeInventoryActions(buffer, packet.getActions(), packet.isUsingNetIds());
      switch (transactionType) {
         case ITEM_USE:
            helper.writeItemUse(buffer, packet);
            break;
         case ITEM_USE_ON_ENTITY:
            this.writeItemUseOnEntity(buffer, helper, packet);
            break;
         case ITEM_RELEASE:
            this.writeItemRelease(buffer, helper, packet);
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, InventoryTransactionPacket packet) {
      int legacyRequestId = VarInts.readInt(buffer);
      packet.setLegacyRequestId(legacyRequestId);
      if (legacyRequestId < -1 && (legacyRequestId & 1) == 0) {
         helper.readArray(buffer, packet.getLegacySlots(), (buf, packetHelper) -> {
            byte containerId = buf.readByte();
            byte[] slots = packetHelper.readByteArray(buf);
            return new LegacySetItemSlotData(containerId, slots);
         });
      }

      InventoryTransactionType transactionType = InventoryTransactionType.values()[VarInts.readUnsignedInt(buffer)];
      packet.setTransactionType(transactionType);
      packet.setUsingNetIds(helper.readInventoryActions(buffer, packet.getActions()));
      switch (transactionType) {
         case ITEM_USE:
            helper.readItemUse(buffer, packet);
            break;
         case ITEM_USE_ON_ENTITY:
            this.readItemUseOnEntity(buffer, helper, packet);
            break;
         case ITEM_RELEASE:
            this.readItemRelease(buffer, helper, packet);
      }

   }

   protected InventoryTransactionSerializer_v407() {
   }
}
