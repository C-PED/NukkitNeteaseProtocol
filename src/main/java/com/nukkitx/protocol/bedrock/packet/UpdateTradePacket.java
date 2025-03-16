package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.ContainerType;
import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.nbt.NbtMap;

public class UpdateTradePacket implements BedrockPacket {
   private int containerId;
   private ContainerType containerType;
   private int size;
   private int tradeTier;
   private long traderUniqueEntityId;
   private long playerUniqueEntityId;
   private String displayName;
   private NbtMap offers;
   private boolean newTradingUi;
   private boolean recipeAddedOnUpdate;
   private boolean usingEconomyTrade;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.UPDATE_TRADE;
   }

   public int getContainerId() {
      return this.containerId;
   }

   public ContainerType getContainerType() {
      return this.containerType;
   }

   public int getSize() {
      return this.size;
   }

   public int getTradeTier() {
      return this.tradeTier;
   }

   public long getTraderUniqueEntityId() {
      return this.traderUniqueEntityId;
   }

   public long getPlayerUniqueEntityId() {
      return this.playerUniqueEntityId;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public NbtMap getOffers() {
      return this.offers;
   }

   public boolean isNewTradingUi() {
      return this.newTradingUi;
   }

   public boolean isRecipeAddedOnUpdate() {
      return this.recipeAddedOnUpdate;
   }

   public boolean isUsingEconomyTrade() {
      return this.usingEconomyTrade;
   }

   public void setContainerId(int containerId) {
      this.containerId = containerId;
   }

   public void setContainerType(ContainerType containerType) {
      this.containerType = containerType;
   }

   public void setSize(int size) {
      this.size = size;
   }

   public void setTradeTier(int tradeTier) {
      this.tradeTier = tradeTier;
   }

   public void setTraderUniqueEntityId(long traderUniqueEntityId) {
      this.traderUniqueEntityId = traderUniqueEntityId;
   }

   public void setPlayerUniqueEntityId(long playerUniqueEntityId) {
      this.playerUniqueEntityId = playerUniqueEntityId;
   }

   public void setDisplayName(String displayName) {
      this.displayName = displayName;
   }

   public void setOffers(NbtMap offers) {
      this.offers = offers;
   }

   public void setNewTradingUi(boolean newTradingUi) {
      this.newTradingUi = newTradingUi;
   }

   public void setRecipeAddedOnUpdate(boolean recipeAddedOnUpdate) {
      this.recipeAddedOnUpdate = recipeAddedOnUpdate;
   }

   public void setUsingEconomyTrade(boolean usingEconomyTrade) {
      this.usingEconomyTrade = usingEconomyTrade;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UpdateTradePacket)) {
         return false;
      } else {
         UpdateTradePacket other = (UpdateTradePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.containerId != other.containerId) {
            return false;
         } else if (this.size != other.size) {
            return false;
         } else if (this.tradeTier != other.tradeTier) {
            return false;
         } else if (this.traderUniqueEntityId != other.traderUniqueEntityId) {
            return false;
         } else if (this.playerUniqueEntityId != other.playerUniqueEntityId) {
            return false;
         } else if (this.newTradingUi != other.newTradingUi) {
            return false;
         } else if (this.recipeAddedOnUpdate != other.recipeAddedOnUpdate) {
            return false;
         } else if (this.usingEconomyTrade != other.usingEconomyTrade) {
            return false;
         } else {
            Object this$containerType = this.containerType;
            Object other$containerType = other.containerType;
            if (this$containerType == null) {
               if (other$containerType != null) {
                  return false;
               }
            } else if (!this$containerType.equals(other$containerType)) {
               return false;
            }

            Object this$displayName = this.displayName;
            Object other$displayName = other.displayName;
            if (this$displayName == null) {
               if (other$displayName != null) {
                  return false;
               }
            } else if (!this$displayName.equals(other$displayName)) {
               return false;
            }

            Object this$offers = this.offers;
            Object other$offers = other.offers;
            if (this$offers == null) {
               if (other$offers != null) {
                  return false;
               }
            } else if (!this$offers.equals(other$offers)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof UpdateTradePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.containerId;
      result = result * 59 + this.size;
      result = result * 59 + this.tradeTier;
      long $traderUniqueEntityId = this.traderUniqueEntityId;
      result = result * 59 + (int)($traderUniqueEntityId >>> 32 ^ $traderUniqueEntityId);
      long $playerUniqueEntityId = this.playerUniqueEntityId;
      result = result * 59 + (int)($playerUniqueEntityId >>> 32 ^ $playerUniqueEntityId);
      result = result * 59 + (this.newTradingUi ? 79 : 97);
      result = result * 59 + (this.recipeAddedOnUpdate ? 79 : 97);
      result = result * 59 + (this.usingEconomyTrade ? 79 : 97);
      Object $containerType = this.containerType;
      result = result * 59 + ($containerType == null ? 43 : $containerType.hashCode());
      Object $displayName = this.displayName;
      result = result * 59 + ($displayName == null ? 43 : $displayName.hashCode());
      Object $offers = this.offers;
      result = result * 59 + ($offers == null ? 43 : $offers.hashCode());
      return result;
   }

   public String toString() {
      return "UpdateTradePacket(containerId=" + this.containerId + ", containerType=" + this.containerType + ", size=" + this.size + ", tradeTier=" + this.tradeTier + ", traderUniqueEntityId=" + this.traderUniqueEntityId + ", playerUniqueEntityId=" + this.playerUniqueEntityId + ", displayName=" + this.displayName + ", offers=" + this.offers + ", newTradingUi=" + this.newTradingUi + ", recipeAddedOnUpdate=" + this.recipeAddedOnUpdate + ", usingEconomyTrade=" + this.usingEconomyTrade + ")";
   }
}
