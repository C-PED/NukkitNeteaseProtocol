package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;

public class AnimateEntityPacket implements BedrockPacket {
   private String animation;
   private String nextState;
   private String stopExpression;
   private int stopExpressionVersion;
   private String controller;
   private float blendOutTime;
   private final LongList runtimeEntityIds = new LongArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ANIMATE_ENTITY;
   }

   public String getAnimation() {
      return this.animation;
   }

   public String getNextState() {
      return this.nextState;
   }

   public String getStopExpression() {
      return this.stopExpression;
   }

   public int getStopExpressionVersion() {
      return this.stopExpressionVersion;
   }

   public String getController() {
      return this.controller;
   }

   public float getBlendOutTime() {
      return this.blendOutTime;
   }

   public LongList getRuntimeEntityIds() {
      return this.runtimeEntityIds;
   }

   public void setAnimation(String animation) {
      this.animation = animation;
   }

   public void setNextState(String nextState) {
      this.nextState = nextState;
   }

   public void setStopExpression(String stopExpression) {
      this.stopExpression = stopExpression;
   }

   public void setStopExpressionVersion(int stopExpressionVersion) {
      this.stopExpressionVersion = stopExpressionVersion;
   }

   public void setController(String controller) {
      this.controller = controller;
   }

   public void setBlendOutTime(float blendOutTime) {
      this.blendOutTime = blendOutTime;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AnimateEntityPacket)) {
         return false;
      } else {
         AnimateEntityPacket other = (AnimateEntityPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.stopExpressionVersion != other.stopExpressionVersion) {
            return false;
         } else if (Float.compare(this.blendOutTime, other.blendOutTime) != 0) {
            return false;
         } else {
            Object this$animation = this.animation;
            Object other$animation = other.animation;
            if (this$animation == null) {
               if (other$animation != null) {
                  return false;
               }
            } else if (!this$animation.equals(other$animation)) {
               return false;
            }

            Object this$nextState = this.nextState;
            Object other$nextState = other.nextState;
            if (this$nextState == null) {
               if (other$nextState != null) {
                  return false;
               }
            } else if (!this$nextState.equals(other$nextState)) {
               return false;
            }

            Object this$stopExpression = this.stopExpression;
            Object other$stopExpression = other.stopExpression;
            if (this$stopExpression == null) {
               if (other$stopExpression != null) {
                  return false;
               }
            } else if (!this$stopExpression.equals(other$stopExpression)) {
               return false;
            }

            Object this$controller = this.controller;
            Object other$controller = other.controller;
            if (this$controller == null) {
               if (other$controller != null) {
                  return false;
               }
            } else if (!this$controller.equals(other$controller)) {
               return false;
            }

            Object this$runtimeEntityIds = this.runtimeEntityIds;
            Object other$runtimeEntityIds = other.runtimeEntityIds;
            if (this$runtimeEntityIds == null) {
               if (other$runtimeEntityIds != null) {
                  return false;
               }
            } else if (!this$runtimeEntityIds.equals(other$runtimeEntityIds)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AnimateEntityPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.stopExpressionVersion;
      result = result * 59 + Float.floatToIntBits(this.blendOutTime);
      Object $animation = this.animation;
      result = result * 59 + ($animation == null ? 43 : $animation.hashCode());
      Object $nextState = this.nextState;
      result = result * 59 + ($nextState == null ? 43 : $nextState.hashCode());
      Object $stopExpression = this.stopExpression;
      result = result * 59 + ($stopExpression == null ? 43 : $stopExpression.hashCode());
      Object $controller = this.controller;
      result = result * 59 + ($controller == null ? 43 : $controller.hashCode());
      Object $runtimeEntityIds = this.runtimeEntityIds;
      result = result * 59 + ($runtimeEntityIds == null ? 43 : $runtimeEntityIds.hashCode());
      return result;
   }

   public String toString() {
      return "AnimateEntityPacket(animation=" + this.animation + ", nextState=" + this.nextState + ", stopExpression=" + this.stopExpression + ", stopExpressionVersion=" + this.stopExpressionVersion + ", controller=" + this.controller + ", blendOutTime=" + this.blendOutTime + ", runtimeEntityIds=" + this.runtimeEntityIds + ")";
   }
}
