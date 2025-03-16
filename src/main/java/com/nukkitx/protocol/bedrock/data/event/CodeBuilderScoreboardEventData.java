package com.nukkitx.protocol.bedrock.data.event;

public class CodeBuilderScoreboardEventData implements EventData {
   private final String objectiveName;
   private final int score;

   public EventDataType getType() {
      return EventDataType.CODE_BUILDER_SCOREBOARD;
   }

   public CodeBuilderScoreboardEventData(String objectiveName, int score) {
      this.objectiveName = objectiveName;
      this.score = score;
   }

   public String getObjectiveName() {
      return this.objectiveName;
   }

   public int getScore() {
      return this.score;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CodeBuilderScoreboardEventData)) {
         return false;
      } else {
         CodeBuilderScoreboardEventData other = (CodeBuilderScoreboardEventData)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getScore() != other.getScore()) {
            return false;
         } else {
            Object this$objectiveName = this.getObjectiveName();
            Object other$objectiveName = other.getObjectiveName();
            if (this$objectiveName == null) {
               if (other$objectiveName != null) {
                  return false;
               }
            } else if (!this$objectiveName.equals(other$objectiveName)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CodeBuilderScoreboardEventData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getScore();
      Object $objectiveName = this.getObjectiveName();
      result = result * 59 + ($objectiveName == null ? 43 : $objectiveName.hashCode());
      return result;
   }

   public String toString() {
      return "CodeBuilderScoreboardEventData(objectiveName=" + this.getObjectiveName() + ", score=" + this.getScore() + ")";
   }
}
