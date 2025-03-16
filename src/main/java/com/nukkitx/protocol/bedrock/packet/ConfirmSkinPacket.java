package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ConfirmSkinPacket implements BedrockPacket {
   private List<SkinEntry> entries = new ArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public void addEntry(SkinEntry skinEntry) {
      this.entries.add(skinEntry);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CONFIRM_SKIN_PACKET;
   }

   public List<SkinEntry> getEntries() {
      return this.entries;
   }

   public void setEntries(List<SkinEntry> entries) {
      this.entries = entries;
   }

   public String toString() {
      return "ConfirmSkinPacket(entries=" + this.getEntries() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ConfirmSkinPacket)) {
         return false;
      } else {
         ConfirmSkinPacket other = (ConfirmSkinPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$entries = this.entries;
            Object other$entries = other.entries;
            if (this$entries == null) {
               if (other$entries != null) {
                  return false;
               }
            } else if (!this$entries.equals(other$entries)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ConfirmSkinPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $entries = this.entries;
      result = result * 59 + ($entries == null ? 43 : $entries.hashCode());
      return result;
   }

   public static class SkinEntry {
      private boolean valid;
      private UUID uuid;
      private byte[] skinBytes;
      private String uidStr;
      private String geoStr;

      public String toString() {
         return "ConfirmSkinPacket.SkinEntry(valid=" + this.isValid() + ", uuid=" + this.getUuid() + ", skinBytes=" + Arrays.toString(this.getSkinBytes()) + ", uidStr=" + this.getUidStr() + ", geoStr=" + this.getGeoStr() + ")";
      }

      public boolean isValid() {
         return this.valid;
      }

      public UUID getUuid() {
         return this.uuid;
      }

      public byte[] getSkinBytes() {
         return this.skinBytes;
      }

      public String getUidStr() {
         return this.uidStr;
      }

      public String getGeoStr() {
         return this.geoStr;
      }

      public void setValid(boolean valid) {
         this.valid = valid;
      }

      public void setUuid(UUID uuid) {
         this.uuid = uuid;
      }

      public void setSkinBytes(byte[] skinBytes) {
         this.skinBytes = skinBytes;
      }

      public void setUidStr(String uidStr) {
         this.uidStr = uidStr;
      }

      public void setGeoStr(String geoStr) {
         this.geoStr = geoStr;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof SkinEntry)) {
            return false;
         } else {
            SkinEntry other = (SkinEntry)o;
            if (!other.canEqual(this)) {
               return false;
            } else if (this.isValid() != other.isValid()) {
               return false;
            } else {
               Object this$uuid = this.getUuid();
               Object other$uuid = other.getUuid();
               if (this$uuid == null) {
                  if (other$uuid != null) {
                     return false;
                  }
               } else if (!this$uuid.equals(other$uuid)) {
                  return false;
               }

               if (!Arrays.equals(this.getSkinBytes(), other.getSkinBytes())) {
                  return false;
               } else {
                  Object this$uidStr = this.getUidStr();
                  Object other$uidStr = other.getUidStr();
                  if (this$uidStr == null) {
                     if (other$uidStr != null) {
                        return false;
                     }
                  } else if (!this$uidStr.equals(other$uidStr)) {
                     return false;
                  }

                  Object this$geoStr = this.getGeoStr();
                  Object other$geoStr = other.getGeoStr();
                  if (this$geoStr == null) {
                     if (other$geoStr != null) {
                        return false;
                     }
                  } else if (!this$geoStr.equals(other$geoStr)) {
                     return false;
                  }

                  return true;
               }
            }
         }
      }

      protected boolean canEqual(Object other) {
         return other instanceof SkinEntry;
      }

      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         result = result * 59 + (this.isValid() ? 79 : 97);
         Object $uuid = this.getUuid();
         result = result * 59 + ($uuid == null ? 43 : $uuid.hashCode());
         result = result * 59 + Arrays.hashCode(this.getSkinBytes());
         Object $uidStr = this.getUidStr();
         result = result * 59 + ($uidStr == null ? 43 : $uidStr.hashCode());
         Object $geoStr = this.getGeoStr();
         result = result * 59 + ($geoStr == null ? 43 : $geoStr.hashCode());
         return result;
      }
   }
}
