package com.nukkitx.protocol.bedrock.data.event;

public final class PlayerDiedEventData implements EventData {
   private final int attackerEntityId;
   private final int attackerVariant;
   private final int entityDamageCause;
   private final boolean inRaid;

   public EventDataType getType() {
      return EventDataType.PLAYER_DIED;
   }

   public PlayerDiedEventData(int attackerEntityId, int attackerVariant, int entityDamageCause, boolean inRaid) {
      this.attackerEntityId = attackerEntityId;
      this.attackerVariant = attackerVariant;
      this.entityDamageCause = entityDamageCause;
      this.inRaid = inRaid;
   }

   public int getAttackerEntityId() {
      return this.attackerEntityId;
   }

   public int getAttackerVariant() {
      return this.attackerVariant;
   }

   public int getEntityDamageCause() {
      return this.entityDamageCause;
   }

   public boolean isInRaid() {
      return this.inRaid;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PlayerDiedEventData)) {
         return false;
      } else {
         PlayerDiedEventData other = (PlayerDiedEventData)o;
         if (this.getAttackerEntityId() != other.getAttackerEntityId()) {
            return false;
         } else if (this.getAttackerVariant() != other.getAttackerVariant()) {
            return false;
         } else if (this.getEntityDamageCause() != other.getEntityDamageCause()) {
            return false;
         } else {
            return this.isInRaid() == other.isInRaid();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getAttackerEntityId();
      result = result * 59 + this.getAttackerVariant();
      result = result * 59 + this.getEntityDamageCause();
      result = result * 59 + (this.isInRaid() ? 79 : 97);
      return result;
   }

   public String toString() {
      return "PlayerDiedEventData(attackerEntityId=" + this.getAttackerEntityId() + ", attackerVariant=" + this.getAttackerVariant() + ", entityDamageCause=" + this.getEntityDamageCause() + ", inRaid=" + this.isInRaid() + ")";
   }
}
