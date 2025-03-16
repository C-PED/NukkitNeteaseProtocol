package com.neteasemc.protocol.custom.packet.skinConfirmPacket;

import com.neteasemc.protocol.custom.GeyserBasePacket;
import com.neteasemc.protocol.custom.GeyserBasePacketHandler;
import com.neteasemc.protocol.custom.GeyserPacketType;
import com.nukkitx.protocol.common.PacketSignal;
import java.util.UUID;
import org.bukkit.entity.Player;

public class SkinConfirmPacket extends GeyserBasePacket {
   private String uidStr = "";
   private String geoStr = "";
   private String userName = "";
   private String skinData = "";
   private int skinWidth;
   private int skinHeight;
   private String capeIdStr = "";
   private String capeData = "";
   private int capeWidth;
   private int capeHeight;
   private String playFabIdStr = "";
   private UUID uuid;
   private UUID javaUuid;
   private boolean isOther = false;
   private String xuid = "";
   private String skinResourcePatch = "";
   private String fulllSkinId = "";
   private String neteaseUid = "";

   public GeyserPacketType getPacketType() {
      return GeyserPacketType.SKIN_CONFIRM;
   }

   public PacketSignal handle(GeyserBasePacketHandler handler) {
      return handler.handle(this);
   }

   public PacketSignal handle(GeyserBasePacketHandler handler, Player player) {
      return handler.handle(this, player);
   }

   public String getUidStr() {
      return this.uidStr;
   }

   public String getGeoStr() {
      return this.geoStr;
   }

   public String getUserName() {
      return this.userName;
   }

   public String getSkinData() {
      return this.skinData;
   }

   public int getSkinWidth() {
      return this.skinWidth;
   }

   public int getSkinHeight() {
      return this.skinHeight;
   }

   public String getCapeIdStr() {
      return this.capeIdStr;
   }

   public String getCapeData() {
      return this.capeData;
   }

   public int getCapeWidth() {
      return this.capeWidth;
   }

   public int getCapeHeight() {
      return this.capeHeight;
   }

   public String getPlayFabIdStr() {
      return this.playFabIdStr;
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public UUID getJavaUuid() {
      return this.javaUuid;
   }

   public boolean isOther() {
      return this.isOther;
   }

   public String getXuid() {
      return this.xuid;
   }

   public String getSkinResourcePatch() {
      return this.skinResourcePatch;
   }

   public String getFulllSkinId() {
      return this.fulllSkinId;
   }

   public String getNeteaseUid() {
      return this.neteaseUid;
   }

   public void setUidStr(String uidStr) {
      this.uidStr = uidStr;
   }

   public void setGeoStr(String geoStr) {
      this.geoStr = geoStr;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public void setSkinData(String skinData) {
      this.skinData = skinData;
   }

   public void setSkinWidth(int skinWidth) {
      this.skinWidth = skinWidth;
   }

   public void setSkinHeight(int skinHeight) {
      this.skinHeight = skinHeight;
   }

   public void setCapeIdStr(String capeIdStr) {
      this.capeIdStr = capeIdStr;
   }

   public void setCapeData(String capeData) {
      this.capeData = capeData;
   }

   public void setCapeWidth(int capeWidth) {
      this.capeWidth = capeWidth;
   }

   public void setCapeHeight(int capeHeight) {
      this.capeHeight = capeHeight;
   }

   public void setPlayFabIdStr(String playFabIdStr) {
      this.playFabIdStr = playFabIdStr;
   }

   public void setUuid(UUID uuid) {
      this.uuid = uuid;
   }

   public void setJavaUuid(UUID javaUuid) {
      this.javaUuid = javaUuid;
   }

   public void setOther(boolean isOther) {
      this.isOther = isOther;
   }

   public void setXuid(String xuid) {
      this.xuid = xuid;
   }

   public void setSkinResourcePatch(String skinResourcePatch) {
      this.skinResourcePatch = skinResourcePatch;
   }

   public void setFulllSkinId(String fulllSkinId) {
      this.fulllSkinId = fulllSkinId;
   }

   public void setNeteaseUid(String neteaseUid) {
      this.neteaseUid = neteaseUid;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SkinConfirmPacket)) {
         return false;
      } else {
         SkinConfirmPacket other = (SkinConfirmPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.skinWidth != other.skinWidth) {
            return false;
         } else if (this.skinHeight != other.skinHeight) {
            return false;
         } else if (this.capeWidth != other.capeWidth) {
            return false;
         } else if (this.capeHeight != other.capeHeight) {
            return false;
         } else if (this.isOther != other.isOther) {
            return false;
         } else {
            Object this$uidStr = this.uidStr;
            Object other$uidStr = other.uidStr;
            if (this$uidStr == null) {
               if (other$uidStr != null) {
                  return false;
               }
            } else if (!this$uidStr.equals(other$uidStr)) {
               return false;
            }

            Object this$geoStr = this.geoStr;
            Object other$geoStr = other.geoStr;
            if (this$geoStr == null) {
               if (other$geoStr != null) {
                  return false;
               }
            } else if (!this$geoStr.equals(other$geoStr)) {
               return false;
            }

            Object this$userName = this.userName;
            Object other$userName = other.userName;
            if (this$userName == null) {
               if (other$userName != null) {
                  return false;
               }
            } else if (!this$userName.equals(other$userName)) {
               return false;
            }

            Object this$skinData = this.skinData;
            Object other$skinData = other.skinData;
            if (this$skinData == null) {
               if (other$skinData != null) {
                  return false;
               }
            } else if (!this$skinData.equals(other$skinData)) {
               return false;
            }

            Object this$capeIdStr = this.capeIdStr;
            Object other$capeIdStr = other.capeIdStr;
            if (this$capeIdStr == null) {
               if (other$capeIdStr != null) {
                  return false;
               }
            } else if (!this$capeIdStr.equals(other$capeIdStr)) {
               return false;
            }

            Object this$capeData = this.capeData;
            Object other$capeData = other.capeData;
            if (this$capeData == null) {
               if (other$capeData != null) {
                  return false;
               }
            } else if (!this$capeData.equals(other$capeData)) {
               return false;
            }

            Object this$playFabIdStr = this.playFabIdStr;
            Object other$playFabIdStr = other.playFabIdStr;
            if (this$playFabIdStr == null) {
               if (other$playFabIdStr != null) {
                  return false;
               }
            } else if (!this$playFabIdStr.equals(other$playFabIdStr)) {
               return false;
            }

            Object this$uuid = this.uuid;
            Object other$uuid = other.uuid;
            if (this$uuid == null) {
               if (other$uuid != null) {
                  return false;
               }
            } else if (!this$uuid.equals(other$uuid)) {
               return false;
            }

            Object this$javaUuid = this.javaUuid;
            Object other$javaUuid = other.javaUuid;
            if (this$javaUuid == null) {
               if (other$javaUuid != null) {
                  return false;
               }
            } else if (!this$javaUuid.equals(other$javaUuid)) {
               return false;
            }

            Object this$xuid = this.xuid;
            Object other$xuid = other.xuid;
            if (this$xuid == null) {
               if (other$xuid != null) {
                  return false;
               }
            } else if (!this$xuid.equals(other$xuid)) {
               return false;
            }

            Object this$skinResourcePatch = this.skinResourcePatch;
            Object other$skinResourcePatch = other.skinResourcePatch;
            if (this$skinResourcePatch == null) {
               if (other$skinResourcePatch != null) {
                  return false;
               }
            } else if (!this$skinResourcePatch.equals(other$skinResourcePatch)) {
               return false;
            }

            Object this$fulllSkinId = this.fulllSkinId;
            Object other$fulllSkinId = other.fulllSkinId;
            if (this$fulllSkinId == null) {
               if (other$fulllSkinId != null) {
                  return false;
               }
            } else if (!this$fulllSkinId.equals(other$fulllSkinId)) {
               return false;
            }

            Object this$neteaseUid = this.neteaseUid;
            Object other$neteaseUid = other.neteaseUid;
            if (this$neteaseUid == null) {
               if (other$neteaseUid != null) {
                  return false;
               }
            } else if (!this$neteaseUid.equals(other$neteaseUid)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SkinConfirmPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.skinWidth;
      result = result * 59 + this.skinHeight;
      result = result * 59 + this.capeWidth;
      result = result * 59 + this.capeHeight;
      result = result * 59 + (this.isOther ? 79 : 97);
      Object $uidStr = this.uidStr;
      result = result * 59 + ($uidStr == null ? 43 : $uidStr.hashCode());
      Object $geoStr = this.geoStr;
      result = result * 59 + ($geoStr == null ? 43 : $geoStr.hashCode());
      Object $userName = this.userName;
      result = result * 59 + ($userName == null ? 43 : $userName.hashCode());
      Object $skinData = this.skinData;
      result = result * 59 + ($skinData == null ? 43 : $skinData.hashCode());
      Object $capeIdStr = this.capeIdStr;
      result = result * 59 + ($capeIdStr == null ? 43 : $capeIdStr.hashCode());
      Object $capeData = this.capeData;
      result = result * 59 + ($capeData == null ? 43 : $capeData.hashCode());
      Object $playFabIdStr = this.playFabIdStr;
      result = result * 59 + ($playFabIdStr == null ? 43 : $playFabIdStr.hashCode());
      Object $uuid = this.uuid;
      result = result * 59 + ($uuid == null ? 43 : $uuid.hashCode());
      Object $javaUuid = this.javaUuid;
      result = result * 59 + ($javaUuid == null ? 43 : $javaUuid.hashCode());
      Object $xuid = this.xuid;
      result = result * 59 + ($xuid == null ? 43 : $xuid.hashCode());
      Object $skinResourcePatch = this.skinResourcePatch;
      result = result * 59 + ($skinResourcePatch == null ? 43 : $skinResourcePatch.hashCode());
      Object $fulllSkinId = this.fulllSkinId;
      result = result * 59 + ($fulllSkinId == null ? 43 : $fulllSkinId.hashCode());
      Object $neteaseUid = this.neteaseUid;
      result = result * 59 + ($neteaseUid == null ? 43 : $neteaseUid.hashCode());
      return result;
   }

   public String toString() {
      return "SkinConfirmPacket(uidStr=" + this.getUidStr() + ", geoStr=" + this.getGeoStr() + ", userName=" + this.getUserName() + ", skinData=" + this.getSkinData() + ", skinWidth=" + this.getSkinWidth() + ", skinHeight=" + this.getSkinHeight() + ", capeIdStr=" + this.getCapeIdStr() + ", capeData=" + this.getCapeData() + ", capeWidth=" + this.getCapeWidth() + ", capeHeight=" + this.getCapeHeight() + ", playFabIdStr=" + this.getPlayFabIdStr() + ", uuid=" + this.getUuid() + ", javaUuid=" + this.getJavaUuid() + ", isOther=" + this.isOther() + ", xuid=" + this.getXuid() + ", skinResourcePatch=" + this.getSkinResourcePatch() + ", fulllSkinId=" + this.getFulllSkinId() + ", neteaseUid=" + this.getNeteaseUid() + ")";
   }
}
