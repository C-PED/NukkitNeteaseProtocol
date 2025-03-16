package com.nukkitx.protocol.bedrock.data.inventory;

import java.util.List;

public final class EnchantOptionData {
   private final int cost;
   private final int primarySlot;
   private final List<EnchantData> enchants0;
   private final List<EnchantData> enchants1;
   private final List<EnchantData> enchants2;
   private final List<EnchantData> enchants3;
   private final String enchantName;
   private final int enchantNetId;

   public EnchantOptionData(int cost, int primarySlot, List<EnchantData> enchants0, List<EnchantData> enchants1, List<EnchantData> enchants2, List<EnchantData> enchants3, String enchantName, int enchantNetId) {
      this.cost = cost;
      this.primarySlot = primarySlot;
      this.enchants0 = enchants0;
      this.enchants1 = enchants1;
      this.enchants2 = enchants2;
      this.enchants3 = enchants3;
      this.enchantName = enchantName;
      this.enchantNetId = enchantNetId;
   }

   public int getCost() {
      return this.cost;
   }

   public int getPrimarySlot() {
      return this.primarySlot;
   }

   public List<EnchantData> getEnchants0() {
      return this.enchants0;
   }

   public List<EnchantData> getEnchants1() {
      return this.enchants1;
   }

   public List<EnchantData> getEnchants2() {
      return this.enchants2;
   }

   public List<EnchantData> getEnchants3() {
      return this.enchants3;
   }

   public String getEnchantName() {
      return this.enchantName;
   }

   public int getEnchantNetId() {
      return this.enchantNetId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EnchantOptionData)) {
         return false;
      } else {
         EnchantOptionData other = (EnchantOptionData)o;
         if (this.getCost() != other.getCost()) {
            return false;
         } else if (this.getPrimarySlot() != other.getPrimarySlot()) {
            return false;
         } else if (this.getEnchantNetId() != other.getEnchantNetId()) {
            return false;
         } else {
            Object this$enchants0 = this.getEnchants0();
            Object other$enchants0 = other.getEnchants0();
            if (this$enchants0 == null) {
               if (other$enchants0 != null) {
                  return false;
               }
            } else if (!this$enchants0.equals(other$enchants0)) {
               return false;
            }

            Object this$enchants1 = this.getEnchants1();
            Object other$enchants1 = other.getEnchants1();
            if (this$enchants1 == null) {
               if (other$enchants1 != null) {
                  return false;
               }
            } else if (!this$enchants1.equals(other$enchants1)) {
               return false;
            }

            Object this$enchants2 = this.getEnchants2();
            Object other$enchants2 = other.getEnchants2();
            if (this$enchants2 == null) {
               if (other$enchants2 != null) {
                  return false;
               }
            } else if (!this$enchants2.equals(other$enchants2)) {
               return false;
            }

            Object this$enchants3 = this.getEnchants3();
            Object other$enchants3 = other.getEnchants3();
            if (this$enchants3 == null) {
               if (other$enchants3 != null) {
                  return false;
               }
            } else if (!this$enchants3.equals(other$enchants3)) {
               return false;
            }

            Object this$enchantName = this.getEnchantName();
            Object other$enchantName = other.getEnchantName();
            if (this$enchantName == null) {
               if (other$enchantName != null) {
                  return false;
               }
            } else if (!this$enchantName.equals(other$enchantName)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getCost();
      result = result * 59 + this.getPrimarySlot();
      result = result * 59 + this.getEnchantNetId();
      Object $enchants0 = this.getEnchants0();
      result = result * 59 + ($enchants0 == null ? 43 : $enchants0.hashCode());
      Object $enchants1 = this.getEnchants1();
      result = result * 59 + ($enchants1 == null ? 43 : $enchants1.hashCode());
      Object $enchants2 = this.getEnchants2();
      result = result * 59 + ($enchants2 == null ? 43 : $enchants2.hashCode());
      Object $enchants3 = this.getEnchants3();
      result = result * 59 + ($enchants3 == null ? 43 : $enchants3.hashCode());
      Object $enchantName = this.getEnchantName();
      result = result * 59 + ($enchantName == null ? 43 : $enchantName.hashCode());
      return result;
   }

   public String toString() {
      return "EnchantOptionData(cost=" + this.getCost() + ", primarySlot=" + this.getPrimarySlot() + ", enchants0=" + this.getEnchants0() + ", enchants1=" + this.getEnchants1() + ", enchants2=" + this.getEnchants2() + ", enchants3=" + this.getEnchants3() + ", enchantName=" + this.getEnchantName() + ", enchantNetId=" + this.getEnchantNetId() + ")";
   }
}
