package com.nukkitx.protocol.bedrock.data.event;

public final class MobKilledEventData implements EventData {
   private final long killerUniqueEntityId;
   private final long victimUniqueEntityId;
   private final int killerEntityType;
   private final int entityDamageCause;
   private int villagerTradeTier = -1;
   private String villagerDisplayName = "";

   public EventDataType getType() {
      return EventDataType.MOB_KILLED;
   }

   public long getKillerUniqueEntityId() {
      return this.killerUniqueEntityId;
   }

   public long getVictimUniqueEntityId() {
      return this.victimUniqueEntityId;
   }

   public int getKillerEntityType() {
      return this.killerEntityType;
   }

   public int getEntityDamageCause() {
      return this.entityDamageCause;
   }

   public int getVillagerTradeTier() {
      return this.villagerTradeTier;
   }

   public String getVillagerDisplayName() {
      return this.villagerDisplayName;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MobKilledEventData)) {
         return false;
      } else {
         MobKilledEventData other = (MobKilledEventData)o;
         if (this.getKillerUniqueEntityId() != other.getKillerUniqueEntityId()) {
            return false;
         } else if (this.getVictimUniqueEntityId() != other.getVictimUniqueEntityId()) {
            return false;
         } else if (this.getKillerEntityType() != other.getKillerEntityType()) {
            return false;
         } else if (this.getEntityDamageCause() != other.getEntityDamageCause()) {
            return false;
         } else if (this.getVillagerTradeTier() != other.getVillagerTradeTier()) {
            return false;
         } else {
            Object this$villagerDisplayName = this.getVillagerDisplayName();
            Object other$villagerDisplayName = other.getVillagerDisplayName();
            if (this$villagerDisplayName == null) {
               if (other$villagerDisplayName != null) {
                  return false;
               }
            } else if (!this$villagerDisplayName.equals(other$villagerDisplayName)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $killerUniqueEntityId = this.getKillerUniqueEntityId();
      result = result * 59 + (int)($killerUniqueEntityId >>> 32 ^ $killerUniqueEntityId);
      long $victimUniqueEntityId = this.getVictimUniqueEntityId();
      result = result * 59 + (int)($victimUniqueEntityId >>> 32 ^ $victimUniqueEntityId);
      result = result * 59 + this.getKillerEntityType();
      result = result * 59 + this.getEntityDamageCause();
      result = result * 59 + this.getVillagerTradeTier();
      Object $villagerDisplayName = this.getVillagerDisplayName();
      result = result * 59 + ($villagerDisplayName == null ? 43 : $villagerDisplayName.hashCode());
      return result;
   }

   public String toString() {
      return "MobKilledEventData(killerUniqueEntityId=" + this.getKillerUniqueEntityId() + ", victimUniqueEntityId=" + this.getVictimUniqueEntityId() + ", killerEntityType=" + this.getKillerEntityType() + ", entityDamageCause=" + this.getEntityDamageCause() + ", villagerTradeTier=" + this.getVillagerTradeTier() + ", villagerDisplayName=" + this.getVillagerDisplayName() + ")";
   }

   public MobKilledEventData(long killerUniqueEntityId, long victimUniqueEntityId, int killerEntityType, int entityDamageCause) {
      this.killerUniqueEntityId = killerUniqueEntityId;
      this.victimUniqueEntityId = victimUniqueEntityId;
      this.killerEntityType = killerEntityType;
      this.entityDamageCause = entityDamageCause;
   }

   public MobKilledEventData(long killerUniqueEntityId, long victimUniqueEntityId, int killerEntityType, int entityDamageCause, int villagerTradeTier, String villagerDisplayName) {
      this.killerUniqueEntityId = killerUniqueEntityId;
      this.victimUniqueEntityId = victimUniqueEntityId;
      this.killerEntityType = killerEntityType;
      this.entityDamageCause = entityDamageCause;
      this.villagerTradeTier = villagerTradeTier;
      this.villagerDisplayName = villagerDisplayName;
   }
}
