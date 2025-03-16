package com.nukkitx.protocol.bedrock.data.event;

public final class RaidUpdateEventData implements EventData {
   private final int currentWave;
   private final int totalWaves;
   private final boolean winner;

   public EventDataType getType() {
      return EventDataType.RAID_UPDATE;
   }

   public RaidUpdateEventData(int currentWave, int totalWaves, boolean winner) {
      this.currentWave = currentWave;
      this.totalWaves = totalWaves;
      this.winner = winner;
   }

   public int getCurrentWave() {
      return this.currentWave;
   }

   public int getTotalWaves() {
      return this.totalWaves;
   }

   public boolean isWinner() {
      return this.winner;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RaidUpdateEventData)) {
         return false;
      } else {
         RaidUpdateEventData other = (RaidUpdateEventData)o;
         if (this.getCurrentWave() != other.getCurrentWave()) {
            return false;
         } else if (this.getTotalWaves() != other.getTotalWaves()) {
            return false;
         } else {
            return this.isWinner() == other.isWinner();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getCurrentWave();
      result = result * 59 + this.getTotalWaves();
      result = result * 59 + (this.isWinner() ? 79 : 97);
      return result;
   }

   public String toString() {
      return "RaidUpdateEventData(currentWave=" + this.getCurrentWave() + ", totalWaves=" + this.getTotalWaves() + ", winner=" + this.isWinner() + ")";
   }
}
