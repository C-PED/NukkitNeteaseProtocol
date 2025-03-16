package com.nukkitx.protocol.bedrock.codec.v407;

import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v390.BedrockCodecHelper_v390;
import com.nukkitx.protocol.bedrock.data.definitions.ItemDefinition;
import com.nukkitx.protocol.bedrock.data.entity.EntityLinkData;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.DefaultDescriptor;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.InvalidDescriptor;
import com.nukkitx.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequest;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.ItemStackRequestSlotData;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.AutoCraftRecipeAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.BeaconPaymentAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ConsumeAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.CraftCreativeAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.CraftNonImplementedAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.CraftRecipeAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.CraftResultsDeprecatedAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.CreateAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.DestroyAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.DropAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.LabTableCombineAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.PlaceAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.RecipeItemStackRequestAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.SwapAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.TakeAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.TransferItemStackRequestAction;
import com.nukkitx.protocol.bedrock.data.inventory.transaction.InventoryActionData;
import com.nukkitx.protocol.bedrock.data.inventory.transaction.InventorySource;
import com.nukkitx.protocol.common.util.Preconditions;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BedrockCodecHelper_v407 extends BedrockCodecHelper_v390 {
   protected final TypeMap<ItemStackRequestActionType> stackRequestActionTypes;
   protected final TypeMap<ContainerSlotType> containerSlotTypes;

   public BedrockCodecHelper_v407(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerSlotType> containerSlotTypes) {
      super(entityData, gameRulesTypes);
      this.stackRequestActionTypes = stackRequestActionTypes;
      this.containerSlotTypes = containerSlotTypes;
   }

   public EntityLinkData readEntityLink(ByteBuf buffer) {
      return new EntityLinkData(VarInts.readLong(buffer), VarInts.readLong(buffer), EntityLinkData.Type.byId(buffer.readUnsignedByte()), buffer.readBoolean(), buffer.readBoolean());
   }

   public void writeEntityLink(ByteBuf buffer, EntityLinkData entityLink) {
      Preconditions.checkNotNull(entityLink, "entityLink");
      VarInts.writeLong(buffer, entityLink.getFrom());
      VarInts.writeLong(buffer, entityLink.getTo());
      buffer.writeByte(entityLink.getType().ordinal());
      buffer.writeBoolean(entityLink.isImmediate());
      buffer.writeBoolean(entityLink.isRiderInitiated());
   }

   public boolean readInventoryActions(ByteBuf buffer, List<InventoryActionData> actions) {
      boolean hasNetworkIds = buffer.readBoolean();
      this.readArray(buffer, actions, (buf, helper) -> {
         InventorySource source = this.readSource(buf);
         int slot = VarInts.readUnsignedInt(buf);
         ItemData fromItem = helper.readItem(buf);
         ItemData toItem = helper.readItem(buf);
         int networkStackId = 0;
         if (hasNetworkIds) {
            networkStackId = VarInts.readInt(buf);
         }

         return new InventoryActionData(source, slot, fromItem, toItem, networkStackId);
      }, 64);
      return hasNetworkIds;
   }

   public void writeInventoryActions(ByteBuf buffer, List<InventoryActionData> actions, boolean hasNetworkIds) {
      buffer.writeBoolean(hasNetworkIds);
      this.writeArray(buffer, actions, (buf, helper, action) -> {
         this.writeSource(buffer, action.getSource());
         VarInts.writeUnsignedInt(buffer, action.getSlot());
         helper.writeItem(buffer, action.getFromItem());
         helper.writeItem(buffer, action.getToItem());
         if (hasNetworkIds) {
            VarInts.writeInt(buffer, action.getStackNetworkId());
         }

      });
   }

   public ItemData readNetItem(ByteBuf buffer) {
      int netId = VarInts.readInt(buffer);
      ItemData item = this.readItem(buffer);
      item.setNetId(netId);
      return item;
   }

   public void writeNetItem(ByteBuf buffer, ItemData item) {
      VarInts.writeInt(buffer, item.getNetId());
      this.writeItem(buffer, item);
   }

   public ItemStackRequest readItemStackRequest(ByteBuf buffer) {
      int requestId = VarInts.readInt(buffer);
      List<ItemStackRequestAction> actions = new ObjectArrayList();
      this.readArray(buffer, actions, (byteBuf) -> {
         ItemStackRequestActionType type = this.stackRequestActionTypes.getType(byteBuf.readByte());
         return this.readRequestActionData(byteBuf, type);
      }, 32);
      return new ItemStackRequest(requestId, (ItemStackRequestAction[])actions.toArray(new ItemStackRequestAction[0]), new String[0]);
   }

   public void writeItemStackRequest(ByteBuf buffer, ItemStackRequest request) {
      VarInts.writeInt(buffer, request.getRequestId());
      this.writeArray(buffer, request.getActions(), (byteBuf, action) -> {
         ItemStackRequestActionType type = action.getType();
         byteBuf.writeByte(this.stackRequestActionTypes.getId(type));
         this.writeRequestActionData(byteBuf, action);
      });
   }

   protected void writeRequestActionData(ByteBuf byteBuf, ItemStackRequestAction action) {
      switch (action.getType()) {
         case TAKE:
         case PLACE:
            byteBuf.writeByte(((TransferItemStackRequestAction)action).getCount());
            this.writeStackRequestSlotInfo(byteBuf, ((TransferItemStackRequestAction)action).getSource());
            this.writeStackRequestSlotInfo(byteBuf, ((TransferItemStackRequestAction)action).getDestination());
            break;
         case SWAP:
            this.writeStackRequestSlotInfo(byteBuf, ((SwapAction)action).getSource());
            this.writeStackRequestSlotInfo(byteBuf, ((SwapAction)action).getDestination());
            break;
         case DROP:
            byteBuf.writeByte(((DropAction)action).getCount());
            this.writeStackRequestSlotInfo(byteBuf, ((DropAction)action).getSource());
            byteBuf.writeBoolean(((DropAction)action).isRandomly());
            break;
         case DESTROY:
            byteBuf.writeByte(((DestroyAction)action).getCount());
            this.writeStackRequestSlotInfo(byteBuf, ((DestroyAction)action).getSource());
            break;
         case CONSUME:
            byteBuf.writeByte(((ConsumeAction)action).getCount());
            this.writeStackRequestSlotInfo(byteBuf, ((ConsumeAction)action).getSource());
            break;
         case CREATE:
            byteBuf.writeByte(((CreateAction)action).getSlot());
         case LAB_TABLE_COMBINE:
         case CRAFT_NON_IMPLEMENTED_DEPRECATED:
            break;
         case BEACON_PAYMENT:
            VarInts.writeInt(byteBuf, ((BeaconPaymentAction)action).getPrimaryEffect());
            VarInts.writeInt(byteBuf, ((BeaconPaymentAction)action).getSecondaryEffect());
            break;
         case CRAFT_RECIPE:
         case CRAFT_RECIPE_AUTO:
            VarInts.writeUnsignedInt(byteBuf, ((RecipeItemStackRequestAction)action).getRecipeNetworkId());
            break;
         case CRAFT_CREATIVE:
            VarInts.writeUnsignedInt(byteBuf, ((CraftCreativeAction)action).getCreativeItemNetworkId());
            break;
         case CRAFT_RESULTS_DEPRECATED:
            this.writeArray(byteBuf, ((CraftResultsDeprecatedAction)action).getResultItems(), (buf2, item) -> this.writeItem(buf2, item));
            byteBuf.writeByte(((CraftResultsDeprecatedAction)action).getTimesCrafted());
            break;
         default:
            throw new UnsupportedOperationException("Unhandled stack request action type: " + action.getType());
      }

   }

   protected ItemStackRequestAction readRequestActionData(ByteBuf byteBuf, ItemStackRequestActionType type) {
      switch (type) {
         case TAKE:
            return new TakeAction(byteBuf.readUnsignedByte(), this.readStackRequestSlotInfo(byteBuf), this.readStackRequestSlotInfo(byteBuf));
         case PLACE:
            return new PlaceAction(byteBuf.readUnsignedByte(), this.readStackRequestSlotInfo(byteBuf), this.readStackRequestSlotInfo(byteBuf));
         case SWAP:
            return new SwapAction(this.readStackRequestSlotInfo(byteBuf), this.readStackRequestSlotInfo(byteBuf));
         case DROP:
            return new DropAction(byteBuf.readUnsignedByte(), this.readStackRequestSlotInfo(byteBuf), byteBuf.readBoolean());
         case DESTROY:
            return new DestroyAction(byteBuf.readUnsignedByte(), this.readStackRequestSlotInfo(byteBuf));
         case CONSUME:
            return new ConsumeAction(byteBuf.readUnsignedByte(), this.readStackRequestSlotInfo(byteBuf));
         case CREATE:
            return new CreateAction(byteBuf.readUnsignedByte());
         case LAB_TABLE_COMBINE:
            return new LabTableCombineAction();
         case BEACON_PAYMENT:
            return new BeaconPaymentAction(VarInts.readInt(byteBuf), VarInts.readInt(byteBuf));
         case CRAFT_RECIPE:
            return new CraftRecipeAction(VarInts.readUnsignedInt(byteBuf));
         case CRAFT_RECIPE_AUTO:
            return new AutoCraftRecipeAction(VarInts.readUnsignedInt(byteBuf), 0, Collections.emptyList());
         case CRAFT_CREATIVE:
            return new CraftCreativeAction(VarInts.readUnsignedInt(byteBuf));
         case CRAFT_NON_IMPLEMENTED_DEPRECATED:
            return new CraftNonImplementedAction();
         case CRAFT_RESULTS_DEPRECATED:
            return new CraftResultsDeprecatedAction((ItemData[])this.readArray(byteBuf, new ItemData[0], this::readItem), byteBuf.readUnsignedByte());
         default:
            throw new UnsupportedOperationException("Unhandled stack request action type: " + type);
      }
   }

   protected ItemStackRequestSlotData readStackRequestSlotInfo(ByteBuf buffer) {
      return new ItemStackRequestSlotData(this.readContainerSlotType(buffer), buffer.readUnsignedByte(), VarInts.readInt(buffer));
   }

   protected void writeStackRequestSlotInfo(ByteBuf buffer, ItemStackRequestSlotData data) {
      this.writeContainerSlotType(buffer, data.getContainer());
      buffer.writeByte(data.getSlot());
      VarInts.writeInt(buffer, data.getStackNetworkId());
   }

   public ContainerSlotType readContainerSlotType(ByteBuf buffer) {
      return this.containerSlotTypes.getType(buffer.readByte());
   }

   public void writeContainerSlotType(ByteBuf buffer, ContainerSlotType slotType) {
      buffer.writeByte(this.containerSlotTypes.getId(slotType));
   }

   public ItemDescriptorWithCount readIngredient(ByteBuf buffer) {
      int runtimeId = VarInts.readInt(buffer);
      if (runtimeId == 0) {
         return ItemDescriptorWithCount.EMPTY;
      } else {
         ItemDefinition definition = (ItemDefinition)this.getItemDefinitions().getDefinition(runtimeId);
         int meta = this.fromAuxValue(VarInts.readInt(buffer));
         int count = VarInts.readInt(buffer);
         return new ItemDescriptorWithCount(new DefaultDescriptor(definition, meta), count);
      }
   }

   public void writeIngredient(ByteBuf buffer, ItemDescriptorWithCount ingredient) {
      Objects.requireNonNull(ingredient, "ingredient is null");
      if (ingredient != ItemDescriptorWithCount.EMPTY && ingredient.getDescriptor() != InvalidDescriptor.INSTANCE) {
         Preconditions.checkArgument(ingredient.getDescriptor() instanceof DefaultDescriptor, "Descriptor must be of type DefaultDescriptor");
         DefaultDescriptor descriptor = (DefaultDescriptor)ingredient.getDescriptor();
         VarInts.writeInt(buffer, descriptor.getItemId().getRuntimeId());
         VarInts.writeInt(buffer, this.toAuxValue(descriptor.getAuxValue()));
         VarInts.writeInt(buffer, ingredient.getCount());
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
}
