package com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action;

public final class BeaconPaymentAction implements ItemStackRequestAction {
   private final int primaryEffect;
   private final int secondaryEffect;

   public ItemStackRequestActionType getType() {
      return ItemStackRequestActionType.BEACON_PAYMENT;
   }

   public BeaconPaymentAction(int primaryEffect, int secondaryEffect) {
      this.primaryEffect = primaryEffect;
      this.secondaryEffect = secondaryEffect;
   }

   public int getPrimaryEffect() {
      return this.primaryEffect;
   }

   public int getSecondaryEffect() {
      return this.secondaryEffect;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BeaconPaymentAction)) {
         return false;
      } else {
         BeaconPaymentAction other = (BeaconPaymentAction)o;
         if (this.getPrimaryEffect() != other.getPrimaryEffect()) {
            return false;
         } else {
            return this.getSecondaryEffect() == other.getSecondaryEffect();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getPrimaryEffect();
      result = result * 59 + this.getSecondaryEffect();
      return result;
   }

   public String toString() {
      return "BeaconPaymentAction(primaryEffect=" + this.getPrimaryEffect() + ", secondaryEffect=" + this.getSecondaryEffect() + ")";
   }
}
