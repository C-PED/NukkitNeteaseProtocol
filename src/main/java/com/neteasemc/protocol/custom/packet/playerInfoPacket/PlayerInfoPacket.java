package com.neteasemc.protocol.custom.packet.playerInfoPacket;

import com.neteasemc.protocol.custom.GeyserBasePacket;
import com.neteasemc.protocol.custom.GeyserBasePacketHandler;
import com.neteasemc.protocol.custom.GeyserPacketType;
import com.nukkitx.protocol.common.PacketSignal;
import java.util.UUID;
import org.bukkit.entity.Player;

public class PlayerInfoPacket extends GeyserBasePacket {
   private String gameId;
   private String gameKey;
   private String gasServerUrl;
   private String webServerUrl;
   private long proxyUid;
   private UUID uuid;
   private int reviewStage;

   public GeyserPacketType getPacketType() {
      return GeyserPacketType.PLAYER_INFO;
   }

   public PacketSignal handle(GeyserBasePacketHandler handler) {
      return handler.handle(this);
   }

   public PacketSignal handle(GeyserBasePacketHandler handler, Player player) {
      return handler.handle(this, player);
   }

   public String getGameId() {
      return this.gameId;
   }

   public String getGameKey() {
      return this.gameKey;
   }

   public String getGasServerUrl() {
      return this.gasServerUrl;
   }

   public String getWebServerUrl() {
      return this.webServerUrl;
   }

   public long getProxyUid() {
      return this.proxyUid;
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public int getReviewStage() {
      return this.reviewStage;
   }

   public void setGameId(String gameId) {
      this.gameId = gameId;
   }

   public void setGameKey(String gameKey) {
      this.gameKey = gameKey;
   }

   public void setGasServerUrl(String gasServerUrl) {
      this.gasServerUrl = gasServerUrl;
   }

   public void setWebServerUrl(String webServerUrl) {
      this.webServerUrl = webServerUrl;
   }

   public void setProxyUid(long proxyUid) {
      this.proxyUid = proxyUid;
   }

   public void setUuid(UUID uuid) {
      this.uuid = uuid;
   }

   public void setReviewStage(int reviewStage) {
      this.reviewStage = reviewStage;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PlayerInfoPacket)) {
         return false;
      } else {
         PlayerInfoPacket other = (PlayerInfoPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.proxyUid != other.proxyUid) {
            return false;
         } else if (this.reviewStage != other.reviewStage) {
            return false;
         } else {
            Object this$gameId = this.gameId;
            Object other$gameId = other.gameId;
            if (this$gameId == null) {
               if (other$gameId != null) {
                  return false;
               }
            } else if (!this$gameId.equals(other$gameId)) {
               return false;
            }

            Object this$gameKey = this.gameKey;
            Object other$gameKey = other.gameKey;
            if (this$gameKey == null) {
               if (other$gameKey != null) {
                  return false;
               }
            } else if (!this$gameKey.equals(other$gameKey)) {
               return false;
            }

            Object this$gasServerUrl = this.gasServerUrl;
            Object other$gasServerUrl = other.gasServerUrl;
            if (this$gasServerUrl == null) {
               if (other$gasServerUrl != null) {
                  return false;
               }
            } else if (!this$gasServerUrl.equals(other$gasServerUrl)) {
               return false;
            }

            Object this$webServerUrl = this.webServerUrl;
            Object other$webServerUrl = other.webServerUrl;
            if (this$webServerUrl == null) {
               if (other$webServerUrl != null) {
                  return false;
               }
            } else if (!this$webServerUrl.equals(other$webServerUrl)) {
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

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PlayerInfoPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $proxyUid = this.proxyUid;
      result = result * 59 + (int)($proxyUid >>> 32 ^ $proxyUid);
      result = result * 59 + this.reviewStage;
      Object $gameId = this.gameId;
      result = result * 59 + ($gameId == null ? 43 : $gameId.hashCode());
      Object $gameKey = this.gameKey;
      result = result * 59 + ($gameKey == null ? 43 : $gameKey.hashCode());
      Object $gasServerUrl = this.gasServerUrl;
      result = result * 59 + ($gasServerUrl == null ? 43 : $gasServerUrl.hashCode());
      Object $webServerUrl = this.webServerUrl;
      result = result * 59 + ($webServerUrl == null ? 43 : $webServerUrl.hashCode());
      Object $uuid = this.uuid;
      result = result * 59 + ($uuid == null ? 43 : $uuid.hashCode());
      return result;
   }

   public String toString() {
      return "PlayerInfoPacket(gameId=" + this.getGameId() + ", gameKey=" + this.getGameKey() + ", gasServerUrl=" + this.getGasServerUrl() + ", webServerUrl=" + this.getWebServerUrl() + ", proxyUid=" + this.getProxyUid() + ", uuid=" + this.getUuid() + ", reviewStage=" + this.getReviewStage() + ")";
   }
}
