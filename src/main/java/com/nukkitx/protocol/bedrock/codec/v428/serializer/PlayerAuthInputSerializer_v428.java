package com.nukkitx.protocol.bedrock.codec.v428.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v419.serializer.PlayerAuthInputSerializer_v419;
import com.nukkitx.protocol.bedrock.data.PlayerActionType;
import com.nukkitx.protocol.bedrock.data.PlayerAuthInputData;
import com.nukkitx.protocol.bedrock.data.PlayerBlockActionData;
import com.nukkitx.protocol.bedrock.data.definitions.BlockDefinition;
import com.nukkitx.protocol.bedrock.data.inventory.transaction.ItemUseTransaction;
import com.nukkitx.protocol.bedrock.data.inventory.transaction.LegacySetItemSlotData;
import com.nukkitx.protocol.bedrock.packet.PlayerAuthInputPacket;
import com.nukkitx.protocol.common.util.TriConsumer;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.function.BiFunction;

public class PlayerAuthInputSerializer_v428 extends PlayerAuthInputSerializer_v419 {
   public static final PlayerAuthInputSerializer_v428 INSTANCE = new PlayerAuthInputSerializer_v428();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
      super.serialize(buffer, helper, packet);
      buffer.writeBoolean(packet.isCameraDeparted());
      if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_INTERACTION)) {
         ItemUseTransaction transaction = packet.getItemUseTransaction();
         int legacyRequestId = transaction.getLegacyRequestId();
         VarInts.writeInt(buffer, legacyRequestId);
         if (legacyRequestId < -1 && (legacyRequestId & 1) == 0) {
            helper.writeArray(buffer, transaction.getLegacySlots(), (buf, packetHelper, data) -> {
               buf.writeByte(data.getContainerId());
               packetHelper.writeByteArray(buf, data.getSlots());
            });
         }

         helper.writeInventoryActions(buffer, transaction.getActions(), transaction.isUsingNetIds());
         VarInts.writeUnsignedInt(buffer, transaction.getActionType());
         helper.writeBlockPosition(buffer, transaction.getBlockPosition());
         VarInts.writeInt(buffer, transaction.getBlockFace());
         VarInts.writeInt(buffer, transaction.getHotbarSlot());
         helper.writeItem(buffer, transaction.getItemInHand());
         helper.writeVector3f(buffer, transaction.getPlayerPosition());
         helper.writeVector3f(buffer, transaction.getClickPosition());
         VarInts.writeUnsignedInt(buffer, transaction.getBlockDefinition().getRuntimeId());
      }

      if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_STACK_REQUEST)) {
         helper.writeItemStackRequest(buffer, packet.getItemStackRequest());
      }

      if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_BLOCK_ACTIONS)) {
         VarInts.writeInt(buffer, packet.getPlayerActions().size());

         for(PlayerBlockActionData actionData : packet.getPlayerActions()) {
            this.writePlayerBlockActionData(buffer, helper, actionData);
         }
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, PlayerAuthInputPacket packet) {
      super.deserialize(buffer, helper, packet);
      packet.setCameraDeparted(buffer.readBoolean());
      if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_INTERACTION)) {
         ItemUseTransaction itemTransaction = new ItemUseTransaction();
         int legacyRequestId = VarInts.readInt(buffer);
         itemTransaction.setLegacyRequestId(legacyRequestId);
         if (legacyRequestId < -1 && (legacyRequestId & 1) == 0) {
            helper.readArray(buffer, itemTransaction.getLegacySlots(), (buf, packetHelper) -> {
               byte containerId = buf.readByte();
               byte[] slots = packetHelper.readByteArray(buf);
               return new LegacySetItemSlotData(containerId, slots);
            });
         }

         helper.readInventoryActions(buffer, itemTransaction.getActions());
         itemTransaction.setActionType(VarInts.readUnsignedInt(buffer));
         itemTransaction.setBlockPosition(helper.readBlockPosition(buffer));
         itemTransaction.setBlockFace(VarInts.readInt(buffer));
         itemTransaction.setHotbarSlot(VarInts.readInt(buffer));
         itemTransaction.setItemInHand(helper.readItem(buffer));
         itemTransaction.setPlayerPosition(helper.readVector3f(buffer));
         itemTransaction.setClickPosition(helper.readVector3f(buffer));
         itemTransaction.setBlockDefinition((BlockDefinition)helper.getBlockDefinitions().getDefinition(VarInts.readUnsignedInt(buffer)));
         packet.setItemUseTransaction(itemTransaction);
      }

      if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_ITEM_STACK_REQUEST)) {
         packet.setItemStackRequest(helper.readItemStackRequest(buffer));
      }

      if (packet.getInputData().contains(PlayerAuthInputData.PERFORM_BLOCK_ACTIONS)) {
         helper.readArray(buffer, packet.getPlayerActions(), VarInts::readInt, this::readPlayerBlockActionData, 32);
      }

   }

   protected void writePlayerBlockActionData(ByteBuf buffer, BedrockCodecHelper helper, PlayerBlockActionData actionData) {
      VarInts.writeInt(buffer, actionData.getAction().ordinal());
      switch (actionData.getAction()) {
         case START_BREAK:
         case ABORT_BREAK:
         case CONTINUE_BREAK:
         case BLOCK_PREDICT_DESTROY:
         case BLOCK_CONTINUE_DESTROY:
            helper.writeVector3i(buffer, actionData.getBlockPosition());
            VarInts.writeInt(buffer, actionData.getFace());
         default:
      }
   }

   protected PlayerBlockActionData readPlayerBlockActionData(ByteBuf buffer, BedrockCodecHelper helper) {
      PlayerBlockActionData actionData = new PlayerBlockActionData();
      actionData.setAction(PlayerActionType.values()[VarInts.readInt(buffer)]);
      switch (actionData.getAction()) {
         case START_BREAK:
         case ABORT_BREAK:
         case CONTINUE_BREAK:
         case BLOCK_PREDICT_DESTROY:
         case BLOCK_CONTINUE_DESTROY:
            actionData.setBlockPosition(helper.readVector3i(buffer));
            actionData.setFace(VarInts.readInt(buffer));
         default:
            return actionData;
      }
   }

   protected PlayerAuthInputSerializer_v428() {
   }
}
