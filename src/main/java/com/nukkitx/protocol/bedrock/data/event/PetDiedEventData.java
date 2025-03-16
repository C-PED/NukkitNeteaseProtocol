package com.nukkitx.protocol.bedrock.data.event;

public final class PetDiedEventData implements EventData {
   private final boolean ownerKilled;
   private final long killerUniqueEntityId;
   private final long petUniqueEntityId;
   private final int entityDamageCause;
   private final int petEntityType;

   public EventDataType getType() {
      return EventDataType.PET_DIED;
   }

   public PetDiedEventData(boolean ownerKilled, long killerUniqueEntityId, long petUniqueEntityId, int entityDamageCause, int petEntityType) {
      this.ownerKilled = ownerKilled;
      this.killerUniqueEntityId = killerUniqueEntityId;
      this.petUniqueEntityId = petUniqueEntityId;
      this.entityDamageCause = entityDamageCause;
      this.petEntityType = petEntityType;
   }

   public boolean isOwnerKilled() {
      return this.ownerKilled;
   }

   public long getKillerUniqueEntityId() {
      return this.killerUniqueEntityId;
   }

   public long getPetUniqueEntityId() {
      return this.petUniqueEntityId;
   }

   public int getEntityDamageCause() {
      return this.entityDamageCause;
   }

   public int getPetEntityType() {
      return this.petEntityType;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PetDiedEventData)) {
         return false;
      } else {
         PetDiedEventData other = (PetDiedEventData)o;
         if (this.isOwnerKilled() != other.isOwnerKilled()) {
            return false;
         } else if (this.getKillerUniqueEntityId() != other.getKillerUniqueEntityId()) {
            return false;
         } else if (this.getPetUniqueEntityId() != other.getPetUniqueEntityId()) {
            return false;
         } else if (this.getEntityDamageCause() != other.getEntityDamageCause()) {
            return false;
         } else {
            return this.getPetEntityType() == other.getPetEntityType();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.isOwnerKilled() ? 79 : 97);
      long $killerUniqueEntityId = this.getKillerUniqueEntityId();
      result = result * 59 + (int)($killerUniqueEntityId >>> 32 ^ $killerUniqueEntityId);
      long $petUniqueEntityId = this.getPetUniqueEntityId();
      result = result * 59 + (int)($petUniqueEntityId >>> 32 ^ $petUniqueEntityId);
      result = result * 59 + this.getEntityDamageCause();
      result = result * 59 + this.getPetEntityType();
      return result;
   }

   public String toString() {
      return "PetDiedEventData(ownerKilled=" + this.isOwnerKilled() + ", killerUniqueEntityId=" + this.getKillerUniqueEntityId() + ", petUniqueEntityId=" + this.getPetUniqueEntityId() + ", entityDamageCause=" + this.getEntityDamageCause() + ", petEntityType=" + this.getPetEntityType() + ")";
   }
}
