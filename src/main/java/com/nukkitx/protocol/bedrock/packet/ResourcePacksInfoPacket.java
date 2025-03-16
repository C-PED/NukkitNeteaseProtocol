package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class ResourcePacksInfoPacket implements BedrockPacket {
   private final List<Entry> behaviorPackInfos = new ObjectArrayList();
   private final List<Entry> resourcePackInfos = new ObjectArrayList();
   private boolean forcedToAccept;
   private boolean hasAddonPacks;
   private boolean scriptingEnabled;
   private boolean forcingServerPacksEnabled;
   private List<CDNEntry> CDNEntries = new ObjectArrayList();

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.RESOURCE_PACKS_INFO;
   }

   public List<Entry> getBehaviorPackInfos() {
      return this.behaviorPackInfos;
   }

   public List<Entry> getResourcePackInfos() {
      return this.resourcePackInfos;
   }

   public boolean isForcedToAccept() {
      return this.forcedToAccept;
   }

   public boolean isHasAddonPacks() {
      return this.hasAddonPacks;
   }

   public boolean isScriptingEnabled() {
      return this.scriptingEnabled;
   }

   public boolean isForcingServerPacksEnabled() {
      return this.forcingServerPacksEnabled;
   }

   public List<CDNEntry> getCDNEntries() {
      return this.CDNEntries;
   }

   public void setForcedToAccept(boolean forcedToAccept) {
      this.forcedToAccept = forcedToAccept;
   }

   public void setHasAddonPacks(boolean hasAddonPacks) {
      this.hasAddonPacks = hasAddonPacks;
   }

   public void setScriptingEnabled(boolean scriptingEnabled) {
      this.scriptingEnabled = scriptingEnabled;
   }

   public void setForcingServerPacksEnabled(boolean forcingServerPacksEnabled) {
      this.forcingServerPacksEnabled = forcingServerPacksEnabled;
   }

   public void setCDNEntries(List<CDNEntry> CDNEntries) {
      this.CDNEntries = CDNEntries;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ResourcePacksInfoPacket)) {
         return false;
      } else {
         ResourcePacksInfoPacket other = (ResourcePacksInfoPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.forcedToAccept != other.forcedToAccept) {
            return false;
         } else if (this.hasAddonPacks != other.hasAddonPacks) {
            return false;
         } else if (this.scriptingEnabled != other.scriptingEnabled) {
            return false;
         } else if (this.forcingServerPacksEnabled != other.forcingServerPacksEnabled) {
            return false;
         } else {
            Object this$behaviorPackInfos = this.behaviorPackInfos;
            Object other$behaviorPackInfos = other.behaviorPackInfos;
            if (this$behaviorPackInfos == null) {
               if (other$behaviorPackInfos != null) {
                  return false;
               }
            } else if (!this$behaviorPackInfos.equals(other$behaviorPackInfos)) {
               return false;
            }

            Object this$resourcePackInfos = this.resourcePackInfos;
            Object other$resourcePackInfos = other.resourcePackInfos;
            if (this$resourcePackInfos == null) {
               if (other$resourcePackInfos != null) {
                  return false;
               }
            } else if (!this$resourcePackInfos.equals(other$resourcePackInfos)) {
               return false;
            }

            Object this$CDNEntries = this.CDNEntries;
            Object other$CDNEntries = other.CDNEntries;
            if (this$CDNEntries == null) {
               if (other$CDNEntries != null) {
                  return false;
               }
            } else if (!this$CDNEntries.equals(other$CDNEntries)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ResourcePacksInfoPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.forcedToAccept ? 79 : 97);
      result = result * 59 + (this.hasAddonPacks ? 79 : 97);
      result = result * 59 + (this.scriptingEnabled ? 79 : 97);
      result = result * 59 + (this.forcingServerPacksEnabled ? 79 : 97);
      Object $behaviorPackInfos = this.behaviorPackInfos;
      result = result * 59 + ($behaviorPackInfos == null ? 43 : $behaviorPackInfos.hashCode());
      Object $resourcePackInfos = this.resourcePackInfos;
      result = result * 59 + ($resourcePackInfos == null ? 43 : $resourcePackInfos.hashCode());
      Object $CDNEntries = this.CDNEntries;
      result = result * 59 + ($CDNEntries == null ? 43 : $CDNEntries.hashCode());
      return result;
   }

   public String toString() {
      return "ResourcePacksInfoPacket(behaviorPackInfos=" + this.behaviorPackInfos + ", resourcePackInfos=" + this.resourcePackInfos + ", forcedToAccept=" + this.forcedToAccept + ", hasAddonPacks=" + this.hasAddonPacks + ", scriptingEnabled=" + this.scriptingEnabled + ", forcingServerPacksEnabled=" + this.forcingServerPacksEnabled + ", CDNEntries=" + this.CDNEntries + ")";
   }

   public static final class Entry {
      private final String packId;
      private final String packVersion;
      private final long packSize;
      private final String contentKey;
      private final String subPackName;
      private final String contentId;
      private final boolean scripting;
      private final boolean raytracingCapable;

      public Entry(String packId, String packVersion, long packSize, String contentKey, String subPackName, String contentId, boolean scripting, boolean raytracingCapable) {
         this.packId = packId;
         this.packVersion = packVersion;
         this.packSize = packSize;
         this.contentKey = contentKey;
         this.subPackName = subPackName;
         this.contentId = contentId;
         this.scripting = scripting;
         this.raytracingCapable = raytracingCapable;
      }

      public String getPackId() {
         return this.packId;
      }

      public String getPackVersion() {
         return this.packVersion;
      }

      public long getPackSize() {
         return this.packSize;
      }

      public String getContentKey() {
         return this.contentKey;
      }

      public String getSubPackName() {
         return this.subPackName;
      }

      public String getContentId() {
         return this.contentId;
      }

      public boolean isScripting() {
         return this.scripting;
      }

      public boolean isRaytracingCapable() {
         return this.raytracingCapable;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof Entry)) {
            return false;
         } else {
            Entry other = (Entry)o;
            if (this.getPackSize() != other.getPackSize()) {
               return false;
            } else if (this.isScripting() != other.isScripting()) {
               return false;
            } else if (this.isRaytracingCapable() != other.isRaytracingCapable()) {
               return false;
            } else {
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

               Object this$contentKey = this.getContentKey();
               Object other$contentKey = other.getContentKey();
               if (this$contentKey == null) {
                  if (other$contentKey != null) {
                     return false;
                  }
               } else if (!this$contentKey.equals(other$contentKey)) {
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

               Object this$contentId = this.getContentId();
               Object other$contentId = other.getContentId();
               if (this$contentId == null) {
                  if (other$contentId != null) {
                     return false;
                  }
               } else if (!this$contentId.equals(other$contentId)) {
                  return false;
               }

               return true;
            }
         }
      }

      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         long $packSize = this.getPackSize();
         result = result * 59 + (int)($packSize >>> 32 ^ $packSize);
         result = result * 59 + (this.isScripting() ? 79 : 97);
         result = result * 59 + (this.isRaytracingCapable() ? 79 : 97);
         Object $packId = this.getPackId();
         result = result * 59 + ($packId == null ? 43 : $packId.hashCode());
         Object $packVersion = this.getPackVersion();
         result = result * 59 + ($packVersion == null ? 43 : $packVersion.hashCode());
         Object $contentKey = this.getContentKey();
         result = result * 59 + ($contentKey == null ? 43 : $contentKey.hashCode());
         Object $subPackName = this.getSubPackName();
         result = result * 59 + ($subPackName == null ? 43 : $subPackName.hashCode());
         Object $contentId = this.getContentId();
         result = result * 59 + ($contentId == null ? 43 : $contentId.hashCode());
         return result;
      }

      public String toString() {
         return "ResourcePacksInfoPacket.Entry(packId=" + this.getPackId() + ", packVersion=" + this.getPackVersion() + ", packSize=" + this.getPackSize() + ", contentKey=" + this.getContentKey() + ", subPackName=" + this.getSubPackName() + ", contentId=" + this.getContentId() + ", scripting=" + this.isScripting() + ", raytracingCapable=" + this.isRaytracingCapable() + ")";
      }
   }

   public static final class CDNEntry {
      private final String packId;
      private final String remoteUrl;

      public CDNEntry(String packId, String remoteUrl) {
         this.packId = packId;
         this.remoteUrl = remoteUrl;
      }

      public String getPackId() {
         return this.packId;
      }

      public String getRemoteUrl() {
         return this.remoteUrl;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof CDNEntry)) {
            return false;
         } else {
            CDNEntry other = (CDNEntry)o;
            Object this$packId = this.getPackId();
            Object other$packId = other.getPackId();
            if (this$packId == null) {
               if (other$packId != null) {
                  return false;
               }
            } else if (!this$packId.equals(other$packId)) {
               return false;
            }

            Object this$remoteUrl = this.getRemoteUrl();
            Object other$remoteUrl = other.getRemoteUrl();
            if (this$remoteUrl == null) {
               if (other$remoteUrl != null) {
                  return false;
               }
            } else if (!this$remoteUrl.equals(other$remoteUrl)) {
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
         Object $remoteUrl = this.getRemoteUrl();
         result = result * 59 + ($remoteUrl == null ? 43 : $remoteUrl.hashCode());
         return result;
      }

      public String toString() {
         return "ResourcePacksInfoPacket.CDNEntry(packId=" + this.getPackId() + ", remoteUrl=" + this.getRemoteUrl() + ")";
      }
   }
}
