package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.ee.LessonAction;
import com.nukkitx.protocol.common.PacketSignal;

public class LessonProgressPacket implements BedrockPacket {
   private LessonAction action;
   private int score;
   private String activityId;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.LESSON_PROGRESS;
   }

   public LessonAction getAction() {
      return this.action;
   }

   public int getScore() {
      return this.score;
   }

   public String getActivityId() {
      return this.activityId;
   }

   public void setAction(LessonAction action) {
      this.action = action;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public void setActivityId(String activityId) {
      this.activityId = activityId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LessonProgressPacket)) {
         return false;
      } else {
         LessonProgressPacket other = (LessonProgressPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.score != other.score) {
            return false;
         } else {
            Object this$action = this.action;
            Object other$action = other.action;
            if (this$action == null) {
               if (other$action != null) {
                  return false;
               }
            } else if (!this$action.equals(other$action)) {
               return false;
            }

            Object this$activityId = this.activityId;
            Object other$activityId = other.activityId;
            if (this$activityId == null) {
               if (other$activityId != null) {
                  return false;
               }
            } else if (!this$activityId.equals(other$activityId)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof LessonProgressPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.score;
      Object $action = this.action;
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      Object $activityId = this.activityId;
      result = result * 59 + ($activityId == null ? 43 : $activityId.hashCode());
      return result;
   }

   public String toString() {
      return "LessonProgressPacket(action=" + this.action + ", score=" + this.score + ", activityId=" + this.activityId + ")";
   }
}
