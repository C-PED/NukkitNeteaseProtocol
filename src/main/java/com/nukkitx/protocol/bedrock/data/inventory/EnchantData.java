package com.nukkitx.protocol.bedrock.data.inventory;

public final class EnchantData {
   private final int type;
   private final int level;
   private final String modEhchantIdentifier;

   public EnchantData(int type, int level, String modEhchantIdentifier) {
      this.type = type;
      this.level = level;
      this.modEhchantIdentifier = modEhchantIdentifier;
   }

   public int getType() {
      return this.type;
   }

   public int getLevel() {
      return this.level;
   }

   public String getModEhchantIdentifier() {
      return this.modEhchantIdentifier;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EnchantData)) {
         return false;
      } else {
         EnchantData other = (EnchantData)o;
         if (this.getType() != other.getType()) {
            return false;
         } else if (this.getLevel() != other.getLevel()) {
            return false;
         } else {
            Object this$modEhchantIdentifier = this.getModEhchantIdentifier();
            Object other$modEhchantIdentifier = other.getModEhchantIdentifier();
            if (this$modEhchantIdentifier == null) {
               if (other$modEhchantIdentifier != null) {
                  return false;
               }
            } else if (!this$modEhchantIdentifier.equals(other$modEhchantIdentifier)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getType();
      result = result * 59 + this.getLevel();
      Object $modEhchantIdentifier = this.getModEhchantIdentifier();
      result = result * 59 + ($modEhchantIdentifier == null ? 43 : $modEhchantIdentifier.hashCode());
      return result;
   }

   public String toString() {
      return "EnchantData(type=" + this.getType() + ", level=" + this.getLevel() + ", modEhchantIdentifier=" + this.getModEhchantIdentifier() + ")";
   }
}
