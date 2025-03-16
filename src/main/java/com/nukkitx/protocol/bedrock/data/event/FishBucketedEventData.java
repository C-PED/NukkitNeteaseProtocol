package com.nukkitx.protocol.bedrock.data.event;

public final class FishBucketedEventData implements EventData {
   private final int pattern;
   private final int preset;
   private final int bucketedEntityType;
   private final boolean releaseEvent;

   public EventDataType getType() {
      return EventDataType.FISH_BUCKETED;
   }

   public FishBucketedEventData(int pattern, int preset, int bucketedEntityType, boolean releaseEvent) {
      this.pattern = pattern;
      this.preset = preset;
      this.bucketedEntityType = bucketedEntityType;
      this.releaseEvent = releaseEvent;
   }

   public int getPattern() {
      return this.pattern;
   }

   public int getPreset() {
      return this.preset;
   }

   public int getBucketedEntityType() {
      return this.bucketedEntityType;
   }

   public boolean isReleaseEvent() {
      return this.releaseEvent;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FishBucketedEventData)) {
         return false;
      } else {
         FishBucketedEventData other = (FishBucketedEventData)o;
         if (this.getPattern() != other.getPattern()) {
            return false;
         } else if (this.getPreset() != other.getPreset()) {
            return false;
         } else if (this.getBucketedEntityType() != other.getBucketedEntityType()) {
            return false;
         } else {
            return this.isReleaseEvent() == other.isReleaseEvent();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getPattern();
      result = result * 59 + this.getPreset();
      result = result * 59 + this.getBucketedEntityType();
      result = result * 59 + (this.isReleaseEvent() ? 79 : 97);
      return result;
   }

   public String toString() {
      return "FishBucketedEventData(pattern=" + this.getPattern() + ", preset=" + this.getPreset() + ", bucketedEntityType=" + this.getBucketedEntityType() + ", releaseEvent=" + this.isReleaseEvent() + ")";
   }
}
