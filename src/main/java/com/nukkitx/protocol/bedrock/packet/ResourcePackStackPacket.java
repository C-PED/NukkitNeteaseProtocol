package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.ExperimentData;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class ResourcePackStackPacket implements BedrockPacket {
   private boolean forcedToAccept;
   private final List<Entry> behaviorPacks = new ObjectArrayList();
   private final List<Entry> resourcePacks = new ObjectArrayList();
   private String gameVersion;
   private final List<ExperimentData> experiments = new ObjectArrayList();
   private boolean experimentsPreviouslyToggled;
   private boolean hasEditorPacks;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.RESOURCE_PACK_STACK;
   }

   public boolean isForcedToAccept() {
      return this.forcedToAccept;
   }

   public List<Entry> getBehaviorPacks() {
      return this.behaviorPacks;
   }

   public List<Entry> getResourcePacks() {
      return this.resourcePacks;
   }

   public String getGameVersion() {
      return this.gameVersion;
   }

   public List<ExperimentData> getExperiments() {
      return this.experiments;
   }

   public boolean isExperimentsPreviouslyToggled() {
      return this.experimentsPreviouslyToggled;
   }

   public boolean isHasEditorPacks() {
      return this.hasEditorPacks;
   }

   public void setForcedToAccept(boolean forcedToAccept) {
      this.forcedToAccept = forcedToAccept;
   }

   public void setGameVersion(String gameVersion) {
      this.gameVersion = gameVersion;
   }

   public void setExperimentsPreviouslyToggled(boolean experimentsPreviouslyToggled) {
      this.experimentsPreviouslyToggled = experimentsPreviouslyToggled;
   }

   public void setHasEditorPacks(boolean hasEditorPacks) {
      this.hasEditorPacks = hasEditorPacks;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourcePackStackPacket)) {
         return false;
      } else {
         ResourcePackStackPacket other = (ResourcePackStackPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.forcedToAccept != other.forcedToAccept) {
            return false;
         } else if (this.experimentsPreviouslyToggled != other.experimentsPreviouslyToggled) {
            return false;
         } else if (this.hasEditorPacks != other.hasEditorPacks) {
            return false;
         } else {
            Object this$behaviorPacks = this.behaviorPacks;
            Object other$behaviorPacks = other.behaviorPacks;
            if (this$behaviorPacks == null) {
               if (other$behaviorPacks != null) {
                  return false;
               }
            } else if (!this$behaviorPacks.equals(other$behaviorPacks)) {
               return false;
            }

            Object this$resourcePacks = this.resourcePacks;
            Object other$resourcePacks = other.resourcePacks;
            if (this$resourcePacks == null) {
               if (other$resourcePacks != null) {
                  return false;
               }
            } else if (!this$resourcePacks.equals(other$resourcePacks)) {
               return false;
            }

            Object this$gameVersion = this.gameVersion;
            Object other$gameVersion = other.gameVersion;
            if (this$gameVersion == null) {
               if (other$gameVersion != null) {
                  return false;
               }
            } else if (!this$gameVersion.equals(other$gameVersion)) {
               return false;
            }

            Object this$experiments = this.experiments;
            Object other$experiments = other.experiments;
            if (this$experiments == null) {
               if (other$experiments != null) {
                  return false;
               }
            } else if (!this$experiments.equals(other$experiments)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ResourcePackStackPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.forcedToAccept ? 79 : 97);
      result = result * 59 + (this.experimentsPreviouslyToggled ? 79 : 97);
      result = result * 59 + (this.hasEditorPacks ? 79 : 97);
      Object $behaviorPacks = this.behaviorPacks;
      result = result * 59 + ($behaviorPacks == null ? 43 : $behaviorPacks.hashCode());
      Object $resourcePacks = this.resourcePacks;
      result = result * 59 + ($resourcePacks == null ? 43 : $resourcePacks.hashCode());
      Object $gameVersion = this.gameVersion;
      result = result * 59 + ($gameVersion == null ? 43 : $gameVersion.hashCode());
      Object $experiments = this.experiments;
      result = result * 59 + ($experiments == null ? 43 : $experiments.hashCode());
      return result;
   }

   public String toString() {
      return "ResourcePackStackPacket(forcedToAccept=" + this.forcedToAccept + ", behaviorPacks=" + this.behaviorPacks + ", resourcePacks=" + this.resourcePacks + ", gameVersion=" + this.gameVersion + ", experiments=" + this.experiments + ", experimentsPreviouslyToggled=" + this.experimentsPreviouslyToggled + ", hasEditorPacks=" + this.hasEditorPacks + ")";
   }

   public static final class Entry {
      private final String packId;
      private final String packVersion;
      private final String subPackName;

      public Entry(String packId, String packVersion, String subPackName) {
         this.packId = packId;
         this.packVersion = packVersion;
         this.subPackName = subPackName;
      }

      public String getPackId() {
         return this.packId;
      }

      public String getPackVersion() {
         return this.packVersion;
      }

      public String getSubPackName() {
         return this.subPackName;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof Entry)) {
            return false;
         } else {
            Entry other = (Entry)o;
            Object this$packId = this.getPackId();
            Object other$packId = other.getPackId();
            if (this$packId == null) {
               if (other$packId != null) {
                  return false;
               }
            } else if (!this$packId.equals(other$packId)) {
               return false;
            }

            Object this$packVersion = this.getPackVersion();
            Object other$packVersion = other.getPackVersion();
            if (this$packVersion == null) {
               if (other$packVersion != null) {
                  return false;
               }
            } else if (!this$packVersion.equals(other$packVersion)) {
               return false;
            }

            Object this$subPackName = this.getSubPackName();
            Object other$subPackName = other.getSubPackName();
            if (this$subPackName == null) {
               if (other$subPackName != null) {
                  return false;
               }
            } else if (!this$subPackName.equals(other$subPackName)) {
               return false;
            }

            return true;
         }
      }

      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         Object $packId = this.getPackId();
         result = result * 59 + ($packId == null ? 43 : $packId.hashCode());
         Object $packVersion = this.getPackVersion();
         result = result * 59 + ($packVersion == null ? 43 : $packVersion.hashCode());
         Object $subPackName = this.getSubPackName();
         result = result * 59 + ($subPackName == null ? 43 : $subPackName.hashCode());
         return result;
      }

      public String toString() {
         return "ResourcePackStackPacket.Entry(packId=" + this.getPackId() + ", packVersion=" + this.getPackVersion() + ", subPackName=" + this.getSubPackName() + ")";
      }
   }
}
