package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.CameraShakeAction;
import com.nukkitx.protocol.bedrock.data.CameraShakeType;
import com.nukkitx.protocol.common.PacketSignal;

public class CameraShakePacket implements BedrockPacket {
   private float intensity;
   private float duration;
   private CameraShakeType shakeType;
   private CameraShakeAction shakeAction;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CAMERA_SHAKE;
   }

   public float getIntensity() {
      return this.intensity;
   }

   public float getDuration() {
      return this.duration;
   }

   public CameraShakeType getShakeType() {
      return this.shakeType;
   }

   public CameraShakeAction getShakeAction() {
      return this.shakeAction;
   }

   public void setIntensity(float intensity) {
      this.intensity = intensity;
   }

   public void setDuration(float duration) {
      this.duration = duration;
   }

   public void setShakeType(CameraShakeType shakeType) {
      this.shakeType = shakeType;
   }

   public void setShakeAction(CameraShakeAction shakeAction) {
      this.shakeAction = shakeAction;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CameraShakePacket)) {
         return false;
      } else {
         CameraShakePacket other = (CameraShakePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.intensity, other.intensity) != 0) {
            return false;
         } else if (Float.compare(this.duration, other.duration) != 0) {
            return false;
         } else {
            Object this$shakeType = this.shakeType;
            Object other$shakeType = other.shakeType;
            if (this$shakeType == null) {
               if (other$shakeType != null) {
                  return false;
               }
            } else if (!this$shakeType.equals(other$shakeType)) {
               return false;
            }

            Object this$shakeAction = this.shakeAction;
            Object other$shakeAction = other.shakeAction;
            if (this$shakeAction == null) {
               if (other$shakeAction != null) {
                  return false;
               }
            } else if (!this$shakeAction.equals(other$shakeAction)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CameraShakePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.intensity);
      result = result * 59 + Float.floatToIntBits(this.duration);
      Object $shakeType = this.shakeType;
      result = result * 59 + ($shakeType == null ? 43 : $shakeType.hashCode());
      Object $shakeAction = this.shakeAction;
      result = result * 59 + ($shakeAction == null ? 43 : $shakeAction.hashCode());
      return result;
   }

   public String toString() {
      return "CameraShakePacket(intensity=" + this.intensity + ", duration=" + this.duration + ", shakeType=" + this.shakeType + ", shakeAction=" + this.shakeAction + ")";
   }
}
