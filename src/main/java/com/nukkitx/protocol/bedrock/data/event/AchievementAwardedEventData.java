package com.nukkitx.protocol.bedrock.data.event;

public final class AchievementAwardedEventData implements EventData {
   private final int achievementId;

   public EventDataType getType() {
      return EventDataType.ACHIEVEMENT_AWARDED;
   }

   public AchievementAwardedEventData(int achievementId) {
      this.achievementId = achievementId;
   }

   public int getAchievementId() {
      return this.achievementId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AchievementAwardedEventData)) {
         return false;
      } else {
         AchievementAwardedEventData other = (AchievementAwardedEventData)o;
         return this.getAchievementId() == other.getAchievementId();
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getAchievementId();
      return result;
   }

   public String toString() {
      return "AchievementAwardedEventData(achievementId=" + this.getAchievementId() + ")";
   }
}
