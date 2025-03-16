package com.nukkitx.protocol.bedrock.data.camera;

import java.awt.Color;

public class CameraFadeInstruction {
   private TimeData timeData;
   private Color color;

   public TimeData getTimeData() {
      return this.timeData;
   }

   public Color getColor() {
      return this.color;
   }

   public void setTimeData(TimeData timeData) {
      this.timeData = timeData;
   }

   public void setColor(Color color) {
      this.color = color;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CameraFadeInstruction)) {
         return false;
      } else {
         CameraFadeInstruction other = (CameraFadeInstruction)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$timeData = this.getTimeData();
            Object other$timeData = other.getTimeData();
            if (this$timeData == null) {
               if (other$timeData != null) {
                  return false;
               }
            } else if (!this$timeData.equals(other$timeData)) {
               return false;
            }

            Object this$color = this.getColor();
            Object other$color = other.getColor();
            if (this$color == null) {
               if (other$color != null) {
                  return false;
               }
            } else if (!this$color.equals(other$color)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CameraFadeInstruction;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $timeData = this.getTimeData();
      result = result * 59 + ($timeData == null ? 43 : $timeData.hashCode());
      Object $color = this.getColor();
      result = result * 59 + ($color == null ? 43 : $color.hashCode());
      return result;
   }

   public String toString() {
      return "CameraFadeInstruction(timeData=" + this.getTimeData() + ", color=" + this.getColor() + ")";
   }

   public CameraFadeInstruction(TimeData timeData, Color color) {
      this.timeData = timeData;
      this.color = color;
   }

   public CameraFadeInstruction() {
   }

   public static class TimeData {
      private final float fadeInTime;
      private final float waitTime;
      private final float fadeOutTime;

      public TimeData(float fadeInTime, float waitTime, float fadeOutTime) {
         this.fadeInTime = fadeInTime;
         this.waitTime = waitTime;
         this.fadeOutTime = fadeOutTime;
      }

      public float getFadeInTime() {
         return this.fadeInTime;
      }

      public float getWaitTime() {
         return this.waitTime;
      }

      public float getFadeOutTime() {
         return this.fadeOutTime;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof TimeData)) {
            return false;
         } else {
            TimeData other = (TimeData)o;
            if (!other.canEqual(this)) {
               return false;
            } else if (Float.compare(this.getFadeInTime(), other.getFadeInTime()) != 0) {
               return false;
            } else if (Float.compare(this.getWaitTime(), other.getWaitTime()) != 0) {
               return false;
            } else {
               return Float.compare(this.getFadeOutTime(), other.getFadeOutTime()) == 0;
            }
         }
      }

      protected boolean canEqual(Object other) {
         return other instanceof TimeData;
      }

      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         result = result * 59 + Float.floatToIntBits(this.getFadeInTime());
         result = result * 59 + Float.floatToIntBits(this.getWaitTime());
         result = result * 59 + Float.floatToIntBits(this.getFadeOutTime());
         return result;
      }

      public String toString() {
         return "CameraFadeInstruction.TimeData(fadeInTime=" + this.getFadeInTime() + ", waitTime=" + this.getWaitTime() + ", fadeOutTime=" + this.getFadeOutTime() + ")";
      }
   }
}
