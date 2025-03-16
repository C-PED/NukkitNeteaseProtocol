package com.nukkitx.protocol.bedrock.data.event;

public final class MovementCorrectedEventData implements EventData {
   private final float positionDelta;
   private final float cheatingScore;
   private final float scoreThreshold;
   private final float distanceThreshold;
   private final int durationThreshold;

   public EventDataType getType() {
      return EventDataType.MOVEMENT_CORRECTED;
   }

   public MovementCorrectedEventData(float positionDelta, float cheatingScore, float scoreThreshold, float distanceThreshold, int durationThreshold) {
      this.positionDelta = positionDelta;
      this.cheatingScore = cheatingScore;
      this.scoreThreshold = scoreThreshold;
      this.distanceThreshold = distanceThreshold;
      this.durationThreshold = durationThreshold;
   }

   public float getPositionDelta() {
      return this.positionDelta;
   }

   public float getCheatingScore() {
      return this.cheatingScore;
   }

   public float getScoreThreshold() {
      return this.scoreThreshold;
   }

   public float getDistanceThreshold() {
      return this.distanceThreshold;
   }

   public int getDurationThreshold() {
      return this.durationThreshold;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MovementCorrectedEventData)) {
         return false;
      } else {
         MovementCorrectedEventData other = (MovementCorrectedEventData)o;
         if (Float.compare(this.getPositionDelta(), other.getPositionDelta()) != 0) {
            return false;
         } else if (Float.compare(this.getCheatingScore(), other.getCheatingScore()) != 0) {
            return false;
         } else if (Float.compare(this.getScoreThreshold(), other.getScoreThreshold()) != 0) {
            return false;
         } else if (Float.compare(this.getDistanceThreshold(), other.getDistanceThreshold()) != 0) {
            return false;
         } else {
            return this.getDurationThreshold() == other.getDurationThreshold();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getPositionDelta());
      result = result * 59 + Float.floatToIntBits(this.getCheatingScore());
      result = result * 59 + Float.floatToIntBits(this.getScoreThreshold());
      result = result * 59 + Float.floatToIntBits(this.getDistanceThreshold());
      result = result * 59 + this.getDurationThreshold();
      return result;
   }

   public String toString() {
      return "MovementCorrectedEventData(positionDelta=" + this.getPositionDelta() + ", cheatingScore=" + this.getCheatingScore() + ", scoreThreshold=" + this.getScoreThreshold() + ", distanceThreshold=" + this.getDistanceThreshold() + ", durationThreshold=" + this.getDurationThreshold() + ")";
   }
}
