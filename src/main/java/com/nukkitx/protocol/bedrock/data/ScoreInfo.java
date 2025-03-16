package com.nukkitx.protocol.bedrock.data;

import com.nukkitx.protocol.common.util.Preconditions;

public class ScoreInfo {
   private final long scoreboardId;
   private final String objectiveId;
   private final int score;
   private final ScorerType type;
   private final String name;
   private final long entityId;

   public ScoreInfo(long scoreboardId, String objectiveId, int score) {
      this.scoreboardId = scoreboardId;
      this.objectiveId = objectiveId;
      this.score = score;
      this.type = ScorerType.INVALID;
      this.name = null;
      this.entityId = -1L;
   }

   public ScoreInfo(long scoreboardId, String objectiveId, int score, String name) {
      this.scoreboardId = scoreboardId;
      this.objectiveId = objectiveId;
      this.score = score;
      this.type = ScorerType.FAKE;
      this.name = name;
      this.entityId = -1L;
   }

   public ScoreInfo(long scoreboardId, String objectiveId, int score, ScorerType type, long entityId) {
      Preconditions.checkArgument(type == ScorerType.ENTITY || type == ScorerType.PLAYER, "Must be player or entity");
      this.scoreboardId = scoreboardId;
      this.objectiveId = objectiveId;
      this.score = score;
      this.type = type;
      this.entityId = entityId;
      this.name = null;
   }

   public long getScoreboardId() {
      return this.scoreboardId;
   }

   public String getObjectiveId() {
      return this.objectiveId;
   }

   public int getScore() {
      return this.score;
   }

   public ScorerType getType() {
      return this.type;
   }

   public String getName() {
      return this.name;
   }

   public long getEntityId() {
      return this.entityId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ScoreInfo)) {
         return false;
      } else {
         ScoreInfo other = (ScoreInfo)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getScoreboardId() != other.getScoreboardId()) {
            return false;
         } else if (this.getScore() != other.getScore()) {
            return false;
         } else if (this.getEntityId() != other.getEntityId()) {
            return false;
         } else {
            Object this$objectiveId = this.getObjectiveId();
            Object other$objectiveId = other.getObjectiveId();
            if (this$objectiveId == null) {
               if (other$objectiveId != null) {
                  return false;
               }
            } else if (!this$objectiveId.equals(other$objectiveId)) {
               return false;
            }

            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$name = this.getName();
            Object other$name = other.getName();
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ScoreInfo;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $scoreboardId = this.getScoreboardId();
      result = result * 59 + (int)($scoreboardId >>> 32 ^ $scoreboardId);
      result = result * 59 + this.getScore();
      long $entityId = this.getEntityId();
      result = result * 59 + (int)($entityId >>> 32 ^ $entityId);
      Object $objectiveId = this.getObjectiveId();
      result = result * 59 + ($objectiveId == null ? 43 : $objectiveId.hashCode());
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      return result;
   }

   public String toString() {
      return "ScoreInfo(scoreboardId=" + this.getScoreboardId() + ", objectiveId=" + this.getObjectiveId() + ", score=" + this.getScore() + ", type=" + this.getType() + ", name=" + this.getName() + ", entityId=" + this.getEntityId() + ")";
   }

   public static enum ScorerType {
      INVALID,
      PLAYER,
      ENTITY,
      FAKE;

      // $FF: synthetic method
      private static ScorerType[] $values() {
         return new ScorerType[]{INVALID, PLAYER, ENTITY, FAKE};
      }
   }
}
