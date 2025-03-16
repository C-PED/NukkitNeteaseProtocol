package com.nukkitx.protocol.bedrock.data.event;

public final class MovementAnomalyEventData implements EventData {
   private final int eventType;
   private final float cheatingScore;
   private final float averagePositionDelta;
   private final float totalPositionDelta;
   private final float minPositionDelta;
   private final float maxPositionDelta;

   public EventDataType getType() {
      return EventDataType.MOVEMENT_ANOMALY;
   }

   public MovementAnomalyEventData(int eventType, float cheatingScore, float averagePositionDelta, float totalPositionDelta, float minPositionDelta, float maxPositionDelta) {
      this.eventType = eventType;
      this.cheatingScore = cheatingScore;
      this.averagePositionDelta = averagePositionDelta;
      this.totalPositionDelta = totalPositionDelta;
      this.minPositionDelta = minPositionDelta;
      this.maxPositionDelta = maxPositionDelta;
   }

   public int getEventType() {
      return this.eventType;
   }

   public float getCheatingScore() {
      return this.cheatingScore;
   }

   public float getAveragePositionDelta() {
      return this.averagePositionDelta;
   }

   public float getTotalPositionDelta() {
      return this.totalPositionDelta;
   }

   public float getMinPositionDelta() {
      return this.minPositionDelta;
   }

   public float getMaxPositionDelta() {
      return this.maxPositionDelta;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MovementAnomalyEventData)) {
         return false;
      } else {
         MovementAnomalyEventData other = (MovementAnomalyEventData)o;
         if (this.getEventType() != other.getEventType()) {
            return false;
         } else if (Float.compare(this.getCheatingScore(), other.getCheatingScore()) != 0) {
            return false;
         } else if (Float.compare(this.getAveragePositionDelta(), other.getAveragePositionDelta()) != 0) {
            return false;
         } else if (Float.compare(this.getTotalPositionDelta(), other.getTotalPositionDelta()) != 0) {
            return false;
         } else if (Float.compare(this.getMinPositionDelta(), other.getMinPositionDelta()) != 0) {
            return false;
         } else {
            return Float.compare(this.getMaxPositionDelta(), other.getMaxPositionDelta()) == 0;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getEventType();
      result = result * 59 + Float.floatToIntBits(this.getCheatingScore());
      result = result * 59 + Float.floatToIntBits(this.getAveragePositionDelta());
      result = result * 59 + Float.floatToIntBits(this.getTotalPositionDelta());
      result = result * 59 + Float.floatToIntBits(this.getMinPositionDelta());
      result = result * 59 + Float.floatToIntBits(this.getMaxPositionDelta());
      return result;
   }

   public String toString() {
      return "MovementAnomalyEventData(eventType=" + this.getEventType() + ", cheatingScore=" + this.getCheatingScore() + ", averagePositionDelta=" + this.getAveragePositionDelta() + ", totalPositionDelta=" + this.getTotalPositionDelta() + ", minPositionDelta=" + this.getMinPositionDelta() + ", maxPositionDelta=" + this.getMaxPositionDelta() + ")";
   }
}
