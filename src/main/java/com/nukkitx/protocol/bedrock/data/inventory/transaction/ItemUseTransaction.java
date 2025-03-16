package com.nukkitx.protocol.bedrock.data.inventory.transaction;

import com.nukkitx.protocol.bedrock.data.definitions.BlockDefinition;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;

public class ItemUseTransaction {
   private int legacyRequestId;
   private final List<LegacySetItemSlotData> legacySlots = new ObjectArrayList();
   private boolean usingNetIds;
   private final List<InventoryActionData> actions = new ObjectArrayList();
   private int actionType;
   private Vector3i blockPosition;
   private int blockFace;
   private int hotbarSlot;
   private ItemData itemInHand;
   private Vector3f playerPosition;
   private Vector3f clickPosition;
   private BlockDefinition blockDefinition;

   public int getLegacyRequestId() {
      return this.legacyRequestId;
   }

   public List<LegacySetItemSlotData> getLegacySlots() {
      return this.legacySlots;
   }

   public boolean isUsingNetIds() {
      return this.usingNetIds;
   }

   public List<InventoryActionData> getActions() {
      return this.actions;
   }

   public int getActionType() {
      return this.actionType;
   }

   public Vector3i getBlockPosition() {
      return this.blockPosition;
   }

   public int getBlockFace() {
      return this.blockFace;
   }

   public int getHotbarSlot() {
      return this.hotbarSlot;
   }

   public ItemData getItemInHand() {
      return this.itemInHand;
   }

   public Vector3f getPlayerPosition() {
      return this.playerPosition;
   }

   public Vector3f getClickPosition() {
      return this.clickPosition;
   }

   public BlockDefinition getBlockDefinition() {
      return this.blockDefinition;
   }

   public void setLegacyRequestId(int legacyRequestId) {
      this.legacyRequestId = legacyRequestId;
   }

   public void setUsingNetIds(boolean usingNetIds) {
      this.usingNetIds = usingNetIds;
   }

   public void setActionType(int actionType) {
      this.actionType = actionType;
   }

   public void setBlockPosition(Vector3i blockPosition) {
      this.blockPosition = blockPosition;
   }

   public void setBlockFace(int blockFace) {
      this.blockFace = blockFace;
   }

   public void setHotbarSlot(int hotbarSlot) {
      this.hotbarSlot = hotbarSlot;
   }

   public void setItemInHand(ItemData itemInHand) {
      this.itemInHand = itemInHand;
   }

   public void setPlayerPosition(Vector3f playerPosition) {
      this.playerPosition = playerPosition;
   }

   public void setClickPosition(Vector3f clickPosition) {
      this.clickPosition = clickPosition;
   }

   public void setBlockDefinition(BlockDefinition blockDefinition) {
      this.blockDefinition = blockDefinition;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ItemUseTransaction)) {
         return false;
      } else {
         ItemUseTransaction other = (ItemUseTransaction)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getLegacyRequestId() != other.getLegacyRequestId()) {
            return false;
         } else if (this.isUsingNetIds() != other.isUsingNetIds()) {
            return false;
         } else if (this.getActionType() != other.getActionType()) {
            return false;
         } else if (this.getBlockFace() != other.getBlockFace()) {
            return false;
         } else if (this.getHotbarSlot() != other.getHotbarSlot()) {
            return false;
         } else {
            Object this$legacySlots = this.getLegacySlots();
            Object other$legacySlots = other.getLegacySlots();
            if (this$legacySlots == null) {
               if (other$legacySlots != null) {
                  return false;
               }
            } else if (!this$legacySlots.equals(other$legacySlots)) {
               return false;
            }

            Object this$actions = this.getActions();
            Object other$actions = other.getActions();
            if (this$actions == null) {
               if (other$actions != null) {
                  return false;
               }
            } else if (!this$actions.equals(other$actions)) {
               return false;
            }

            Object this$blockPosition = this.getBlockPosition();
            Object other$blockPosition = other.getBlockPosition();
            if (this$blockPosition == null) {
               if (other$blockPosition != null) {
                  return false;
               }
            } else if (!this$blockPosition.equals(other$blockPosition)) {
               return false;
            }

            Object this$itemInHand = this.getItemInHand();
            Object other$itemInHand = other.getItemInHand();
            if (this$itemInHand == null) {
               if (other$itemInHand != null) {
                  return false;
               }
            } else if (!this$itemInHand.equals(other$itemInHand)) {
               return false;
            }

            Object this$playerPosition = this.getPlayerPosition();
            Object other$playerPosition = other.getPlayerPosition();
            if (this$playerPosition == null) {
               if (other$playerPosition != null) {
                  return false;
               }
            } else if (!this$playerPosition.equals(other$playerPosition)) {
               return false;
            }

            Object this$clickPosition = this.getClickPosition();
            Object other$clickPosition = other.getClickPosition();
            if (this$clickPosition == null) {
               if (other$clickPosition != null) {
                  return false;
               }
            } else if (!this$clickPosition.equals(other$clickPosition)) {
               return false;
            }

            Object this$blockDefinition = this.getBlockDefinition();
            Object other$blockDefinition = other.getBlockDefinition();
            if (this$blockDefinition == null) {
               if (other$blockDefinition != null) {
                  return false;
               }
            } else if (!this$blockDefinition.equals(other$blockDefinition)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ItemUseTransaction;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getLegacyRequestId();
      result = result * 59 + (this.isUsingNetIds() ? 79 : 97);
      result = result * 59 + this.getActionType();
      result = result * 59 + this.getBlockFace();
      result = result * 59 + this.getHotbarSlot();
      Object $legacySlots = this.getLegacySlots();
      result = result * 59 + ($legacySlots == null ? 43 : $legacySlots.hashCode());
      Object $actions = this.getActions();
      result = result * 59 + ($actions == null ? 43 : $actions.hashCode());
      Object $blockPosition = this.getBlockPosition();
      result = result * 59 + ($blockPosition == null ? 43 : $blockPosition.hashCode());
      Object $itemInHand = this.getItemInHand();
      result = result * 59 + ($itemInHand == null ? 43 : $itemInHand.hashCode());
      Object $playerPosition = this.getPlayerPosition();
      result = result * 59 + ($playerPosition == null ? 43 : $playerPosition.hashCode());
      Object $clickPosition = this.getClickPosition();
      result = result * 59 + ($clickPosition == null ? 43 : $clickPosition.hashCode());
      Object $blockDefinition = this.getBlockDefinition();
      result = result * 59 + ($blockDefinition == null ? 43 : $blockDefinition.hashCode());
      return result;
   }

   public String toString() {
      return "ItemUseTransaction(legacyRequestId=" + this.getLegacyRequestId() + ", legacySlots=" + this.getLegacySlots() + ", usingNetIds=" + this.isUsingNetIds() + ", actions=" + this.getActions() + ", actionType=" + this.getActionType() + ", blockPosition=" + this.getBlockPosition() + ", blockFace=" + this.getBlockFace() + ", hotbarSlot=" + this.getHotbarSlot() + ", itemInHand=" + this.getItemInHand() + ", playerPosition=" + this.getPlayerPosition() + ", clickPosition=" + this.getClickPosition() + ", blockDefinition=" + this.getBlockDefinition() + ")";
   }
}
