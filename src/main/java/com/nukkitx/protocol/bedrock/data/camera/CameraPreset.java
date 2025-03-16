package com.nukkitx.protocol.bedrock.data.camera;

import com.nukkitx.protocol.common.util.OptionalBoolean;
import org.cloudburstmc.math.vector.Vector3f;

public class CameraPreset {
   private String identifier;
   private String parentPreset = "";
   private Vector3f pos;
   private Float yaw;
   private Float pitch;
   private CameraAudioListener listener;
   private OptionalBoolean playEffect;

   public String getIdentifier() {
      return this.identifier;
   }

   public String getParentPreset() {
      return this.parentPreset;
   }

   public Vector3f getPos() {
      return this.pos;
   }

   public Float getYaw() {
      return this.yaw;
   }

   public Float getPitch() {
      return this.pitch;
   }

   public CameraAudioListener getListener() {
      return this.listener;
   }

   public OptionalBoolean getPlayEffect() {
      return this.playEffect;
   }

   public void setIdentifier(String identifier) {
      this.identifier = identifier;
   }

   public void setParentPreset(String parentPreset) {
      this.parentPreset = parentPreset;
   }

   public void setPos(Vector3f pos) {
      this.pos = pos;
   }

   public void setYaw(Float yaw) {
      this.yaw = yaw;
   }

   public void setPitch(Float pitch) {
      this.pitch = pitch;
   }

   public void setListener(CameraAudioListener listener) {
      this.listener = listener;
   }

   public void setPlayEffect(OptionalBoolean playEffect) {
      this.playEffect = playEffect;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CameraPreset)) {
         return false;
      } else {
         CameraPreset other = (CameraPreset)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$yaw = this.getYaw();
            Object other$yaw = other.getYaw();
            if (this$yaw == null) {
               if (other$yaw != null) {
                  return false;
               }
            } else if (!this$yaw.equals(other$yaw)) {
               return false;
            }

            Object this$pitch = this.getPitch();
            Object other$pitch = other.getPitch();
            if (this$pitch == null) {
               if (other$pitch != null) {
                  return false;
               }
            } else if (!this$pitch.equals(other$pitch)) {
               return false;
            }

            Object this$identifier = this.getIdentifier();
            Object other$identifier = other.getIdentifier();
            if (this$identifier == null) {
               if (other$identifier != null) {
                  return false;
               }
            } else if (!this$identifier.equals(other$identifier)) {
               return false;
            }

            Object this$parentPreset = this.getParentPreset();
            Object other$parentPreset = other.getParentPreset();
            if (this$parentPreset == null) {
               if (other$parentPreset != null) {
                  return false;
               }
            } else if (!this$parentPreset.equals(other$parentPreset)) {
               return false;
            }

            Object this$pos = this.getPos();
            Object other$pos = other.getPos();
            if (this$pos == null) {
               if (other$pos != null) {
                  return false;
               }
            } else if (!this$pos.equals(other$pos)) {
               return false;
            }

            Object this$listener = this.getListener();
            Object other$listener = other.getListener();
            if (this$listener == null) {
               if (other$listener != null) {
                  return false;
               }
            } else if (!this$listener.equals(other$listener)) {
               return false;
            }

            Object this$playEffect = this.getPlayEffect();
            Object other$playEffect = other.getPlayEffect();
            if (this$playEffect == null) {
               if (other$playEffect != null) {
                  return false;
               }
            } else if (!this$playEffect.equals(other$playEffect)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CameraPreset;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $yaw = this.getYaw();
      result = result * 59 + ($yaw == null ? 43 : $yaw.hashCode());
      Object $pitch = this.getPitch();
      result = result * 59 + ($pitch == null ? 43 : $pitch.hashCode());
      Object $identifier = this.getIdentifier();
      result = result * 59 + ($identifier == null ? 43 : $identifier.hashCode());
      Object $parentPreset = this.getParentPreset();
      result = result * 59 + ($parentPreset == null ? 43 : $parentPreset.hashCode());
      Object $pos = this.getPos();
      result = result * 59 + ($pos == null ? 43 : $pos.hashCode());
      Object $listener = this.getListener();
      result = result * 59 + ($listener == null ? 43 : $listener.hashCode());
      Object $playEffect = this.getPlayEffect();
      result = result * 59 + ($playEffect == null ? 43 : $playEffect.hashCode());
      return result;
   }

   public String toString() {
      return "CameraPreset(identifier=" + this.getIdentifier() + ", parentPreset=" + this.getParentPreset() + ", pos=" + this.getPos() + ", yaw=" + this.getYaw() + ", pitch=" + this.getPitch() + ", listener=" + this.getListener() + ", playEffect=" + this.getPlayEffect() + ")";
   }

   public CameraPreset(String identifier, String parentPreset, Vector3f pos, Float yaw, Float pitch, CameraAudioListener listener, OptionalBoolean playEffect) {
      this.identifier = identifier;
      this.parentPreset = parentPreset;
      this.pos = pos;
      this.yaw = yaw;
      this.pitch = pitch;
      this.listener = listener;
      this.playEffect = playEffect;
   }

   public CameraPreset() {
   }
}
