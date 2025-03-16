package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.StoreOfferRedirectType;
import com.nukkitx.protocol.common.PacketSignal;

public class ShowStoreOfferPacket implements BedrockPacket {
   private String offerId;
   /** @deprecated */
   @Deprecated
   private boolean shownToAll;
   private StoreOfferRedirectType redirectType;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SHOW_STORE_OFFER;
   }

   public String getOfferId() {
      return this.offerId;
   }

   /** @deprecated */
   @Deprecated
   public boolean isShownToAll() {
      return this.shownToAll;
   }

   public StoreOfferRedirectType getRedirectType() {
      return this.redirectType;
   }

   public void setOfferId(String offerId) {
      this.offerId = offerId;
   }

   /** @deprecated */
   @Deprecated
   public void setShownToAll(boolean shownToAll) {
      this.shownToAll = shownToAll;
   }

   public void setRedirectType(StoreOfferRedirectType redirectType) {
      this.redirectType = redirectType;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ShowStoreOfferPacket)) {
         return false;
      } else {
         ShowStoreOfferPacket other = (ShowStoreOfferPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.shownToAll != other.shownToAll) {
            return false;
         } else {
            Object this$offerId = this.offerId;
            Object other$offerId = other.offerId;
            if (this$offerId == null) {
               if (other$offerId != null) {
                  return false;
               }
            } else if (!this$offerId.equals(other$offerId)) {
               return false;
            }

            Object this$redirectType = this.redirectType;
            Object other$redirectType = other.redirectType;
            if (this$redirectType == null) {
               if (other$redirectType != null) {
                  return false;
               }
            } else if (!this$redirectType.equals(other$redirectType)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ShowStoreOfferPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.shownToAll ? 79 : 97);
      Object $offerId = this.offerId;
      result = result * 59 + ($offerId == null ? 43 : $offerId.hashCode());
      Object $redirectType = this.redirectType;
      result = result * 59 + ($redirectType == null ? 43 : $redirectType.hashCode());
      return result;
   }

   public String toString() {
      return "ShowStoreOfferPacket(offerId=" + this.offerId + ", shownToAll=" + this.shownToAll + ", redirectType=" + this.redirectType + ")";
   }
}
