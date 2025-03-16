package com.nukkitx.protocol.bedrock.data;

import java.util.EnumSet;
import java.util.Set;

public class AbilityLayer {
   private Type layerType;
   private final Set<Ability> abilitiesSet = EnumSet.noneOf(Ability.class);
   private final Set<Ability> abilityValues = EnumSet.noneOf(Ability.class);
   private float flySpeed;
   private float walkSpeed;

   public Type getLayerType() {
      return this.layerType;
   }

   public Set<Ability> getAbilitiesSet() {
      return this.abilitiesSet;
   }

   public Set<Ability> getAbilityValues() {
      return this.abilityValues;
   }

   public float getFlySpeed() {
      return this.flySpeed;
   }

   public float getWalkSpeed() {
      return this.walkSpeed;
   }

   public void setLayerType(Type layerType) {
      this.layerType = layerType;
   }

   public void setFlySpeed(float flySpeed) {
      this.flySpeed = flySpeed;
   }

   public void setWalkSpeed(float walkSpeed) {
      this.walkSpeed = walkSpeed;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AbilityLayer)) {
         return false;
      } else {
         AbilityLayer other = (AbilityLayer)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getFlySpeed(), other.getFlySpeed()) != 0) {
            return false;
         } else if (Float.compare(this.getWalkSpeed(), other.getWalkSpeed()) != 0) {
            return false;
         } else {
            Object this$layerType = this.getLayerType();
            Object other$layerType = other.getLayerType();
            if (this$layerType == null) {
               if (other$layerType != null) {
                  return false;
               }
            } else if (!this$layerType.equals(other$layerType)) {
               return false;
            }

            Object this$abilitiesSet = this.getAbilitiesSet();
            Object other$abilitiesSet = other.getAbilitiesSet();
            if (this$abilitiesSet == null) {
               if (other$abilitiesSet != null) {
                  return false;
               }
            } else if (!this$abilitiesSet.equals(other$abilitiesSet)) {
               return false;
            }

            Object this$abilityValues = this.getAbilityValues();
            Object other$abilityValues = other.getAbilityValues();
            if (this$abilityValues == null) {
               if (other$abilityValues != null) {
                  return false;
               }
            } else if (!this$abilityValues.equals(other$abilityValues)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AbilityLayer;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getFlySpeed());
      result = result * 59 + Float.floatToIntBits(this.getWalkSpeed());
      Object $layerType = this.getLayerType();
      result = result * 59 + ($layerType == null ? 43 : $layerType.hashCode());
      Object $abilitiesSet = this.getAbilitiesSet();
      result = result * 59 + ($abilitiesSet == null ? 43 : $abilitiesSet.hashCode());
      Object $abilityValues = this.getAbilityValues();
      result = result * 59 + ($abilityValues == null ? 43 : $abilityValues.hashCode());
      return result;
   }

   public String toString() {
      return "AbilityLayer(layerType=" + this.getLayerType() + ", abilitiesSet=" + this.getAbilitiesSet() + ", abilityValues=" + this.getAbilityValues() + ", flySpeed=" + this.getFlySpeed() + ", walkSpeed=" + this.getWalkSpeed() + ")";
   }

   public static enum Type {
      CACHE,
      BASE,
      SPECTATOR,
      COMMANDS,
      EDITOR;

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{CACHE, BASE, SPECTATOR, COMMANDS, EDITOR};
      }
   }
}
