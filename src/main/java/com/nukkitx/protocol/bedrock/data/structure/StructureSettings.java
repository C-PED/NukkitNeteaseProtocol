package com.nukkitx.protocol.bedrock.data.structure;

import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;

public final class StructureSettings {
   private final String paletteName;
   private final boolean ignoringEntities;
   private final boolean ignoringBlocks;
   private final boolean nonTickingPlayersAndTickingAreasEnabled;
   private final Vector3i size;
   private final Vector3i offset;
   private final long lastEditedByEntityId;
   private final StructureRotation rotation;
   private final StructureMirror mirror;
   private final StructureAnimationMode animationMode;
   private final float animationSeconds;
   private final float integrityValue;
   private final int integritySeed;
   private final Vector3f pivot;

   public StructureSettings(String paletteName, boolean ignoringEntities, boolean ignoringBlocks, boolean nonTickingPlayersAndTickingAreasEnabled, Vector3i size, Vector3i offset, long lastEditedByEntityId, StructureRotation rotation, StructureMirror mirror, StructureAnimationMode animationMode, float animationSeconds, float integrityValue, int integritySeed, Vector3f pivot) {
      this.paletteName = paletteName;
      this.ignoringEntities = ignoringEntities;
      this.ignoringBlocks = ignoringBlocks;
      this.nonTickingPlayersAndTickingAreasEnabled = nonTickingPlayersAndTickingAreasEnabled;
      this.size = size;
      this.offset = offset;
      this.lastEditedByEntityId = lastEditedByEntityId;
      this.rotation = rotation;
      this.mirror = mirror;
      this.animationMode = animationMode;
      this.animationSeconds = animationSeconds;
      this.integrityValue = integrityValue;
      this.integritySeed = integritySeed;
      this.pivot = pivot;
   }

   public String getPaletteName() {
      return this.paletteName;
   }

   public boolean isIgnoringEntities() {
      return this.ignoringEntities;
   }

   public boolean isIgnoringBlocks() {
      return this.ignoringBlocks;
   }

   public boolean isNonTickingPlayersAndTickingAreasEnabled() {
      return this.nonTickingPlayersAndTickingAreasEnabled;
   }

   public Vector3i getSize() {
      return this.size;
   }

   public Vector3i getOffset() {
      return this.offset;
   }

   public long getLastEditedByEntityId() {
      return this.lastEditedByEntityId;
   }

   public StructureRotation getRotation() {
      return this.rotation;
   }

   public StructureMirror getMirror() {
      return this.mirror;
   }

   public StructureAnimationMode getAnimationMode() {
      return this.animationMode;
   }

   public float getAnimationSeconds() {
      return this.animationSeconds;
   }

   public float getIntegrityValue() {
      return this.integrityValue;
   }

   public int getIntegritySeed() {
      return this.integritySeed;
   }

   public Vector3f getPivot() {
      return this.pivot;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StructureSettings)) {
         return false;
      } else {
         StructureSettings other = (StructureSettings)o;
         if (this.isIgnoringEntities() != other.isIgnoringEntities()) {
            return false;
         } else if (this.isIgnoringBlocks() != other.isIgnoringBlocks()) {
            return false;
         } else if (this.isNonTickingPlayersAndTickingAreasEnabled() != other.isNonTickingPlayersAndTickingAreasEnabled()) {
            return false;
         } else if (this.getLastEditedByEntityId() != other.getLastEditedByEntityId()) {
            return false;
         } else if (Float.compare(this.getAnimationSeconds(), other.getAnimationSeconds()) != 0) {
            return false;
         } else if (Float.compare(this.getIntegrityValue(), other.getIntegrityValue()) != 0) {
            return false;
         } else if (this.getIntegritySeed() != other.getIntegritySeed()) {
            return false;
         } else {
            Object this$paletteName = this.getPaletteName();
            Object other$paletteName = other.getPaletteName();
            if (this$paletteName == null) {
               if (other$paletteName != null) {
                  return false;
               }
            } else if (!this$paletteName.equals(other$paletteName)) {
               return false;
            }

            Object this$size = this.getSize();
            Object other$size = other.getSize();
            if (this$size == null) {
               if (other$size != null) {
                  return false;
               }
            } else if (!this$size.equals(other$size)) {
               return false;
            }

            Object this$offset = this.getOffset();
            Object other$offset = other.getOffset();
            if (this$offset == null) {
               if (other$offset != null) {
                  return false;
               }
            } else if (!this$offset.equals(other$offset)) {
               return false;
            }

            Object this$rotation = this.getRotation();
            Object other$rotation = other.getRotation();
            if (this$rotation == null) {
               if (other$rotation != null) {
                  return false;
               }
            } else if (!this$rotation.equals(other$rotation)) {
               return false;
            }

            Object this$mirror = this.getMirror();
            Object other$mirror = other.getMirror();
            if (this$mirror == null) {
               if (other$mirror != null) {
                  return false;
               }
            } else if (!this$mirror.equals(other$mirror)) {
               return false;
            }

            Object this$animationMode = this.getAnimationMode();
            Object other$animationMode = other.getAnimationMode();
            if (this$animationMode == null) {
               if (other$animationMode != null) {
                  return false;
               }
            } else if (!this$animationMode.equals(other$animationMode)) {
               return false;
            }

            Object this$pivot = this.getPivot();
            Object other$pivot = other.getPivot();
            if (this$pivot == null) {
               if (other$pivot != null) {
                  return false;
               }
            } else if (!this$pivot.equals(other$pivot)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.isIgnoringEntities() ? 79 : 97);
      result = result * 59 + (this.isIgnoringBlocks() ? 79 : 97);
      result = result * 59 + (this.isNonTickingPlayersAndTickingAreasEnabled() ? 79 : 97);
      long $lastEditedByEntityId = this.getLastEditedByEntityId();
      result = result * 59 + (int)($lastEditedByEntityId >>> 32 ^ $lastEditedByEntityId);
      result = result * 59 + Float.floatToIntBits(this.getAnimationSeconds());
      result = result * 59 + Float.floatToIntBits(this.getIntegrityValue());
      result = result * 59 + this.getIntegritySeed();
      Object $paletteName = this.getPaletteName();
      result = result * 59 + ($paletteName == null ? 43 : $paletteName.hashCode());
      Object $size = this.getSize();
      result = result * 59 + ($size == null ? 43 : $size.hashCode());
      Object $offset = this.getOffset();
      result = result * 59 + ($offset == null ? 43 : $offset.hashCode());
      Object $rotation = this.getRotation();
      result = result * 59 + ($rotation == null ? 43 : $rotation.hashCode());
      Object $mirror = this.getMirror();
      result = result * 59 + ($mirror == null ? 43 : $mirror.hashCode());
      Object $animationMode = this.getAnimationMode();
      result = result * 59 + ($animationMode == null ? 43 : $animationMode.hashCode());
      Object $pivot = this.getPivot();
      result = result * 59 + ($pivot == null ? 43 : $pivot.hashCode());
      return result;
   }

   public String toString() {
      return "StructureSettings(paletteName=" + this.getPaletteName() + ", ignoringEntities=" + this.isIgnoringEntities() + ", ignoringBlocks=" + this.isIgnoringBlocks() + ", nonTickingPlayersAndTickingAreasEnabled=" + this.isNonTickingPlayersAndTickingAreasEnabled() + ", size=" + this.getSize() + ", offset=" + this.getOffset() + ", lastEditedByEntityId=" + this.getLastEditedByEntityId() + ", rotation=" + this.getRotation() + ", mirror=" + this.getMirror() + ", animationMode=" + this.getAnimationMode() + ", animationSeconds=" + this.getAnimationSeconds() + ", integrityValue=" + this.getIntegrityValue() + ", integritySeed=" + this.getIntegritySeed() + ", pivot=" + this.getPivot() + ")";
   }
}
