package com.nukkitx.protocol.bedrock;

import com.nukkitx.network.raknet.RakNetPong;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.StringJoiner;

public class BedrockPong {
   private String edition;
   private String motd;
   private int protocolVersion = -1;
   private String version;
   private int playerCount = -1;
   private int maximumPlayerCount = -1;
   private long serverId;
   private String subMotd;
   private String gameType;
   private boolean nintendoLimited;
   private int ipv4Port = -1;
   private int ipv6Port = -1;
   private String[] extras;

   public String edition() {
      return this.edition;
   }

   public String motd() {
      return this.motd;
   }

   public int protocolVersion() {
      return this.protocolVersion;
   }

   public String version() {
      return this.version;
   }

   public int playerCount() {
      return this.playerCount;
   }

   public int maximumPlayerCount() {
      return this.maximumPlayerCount;
   }

   public long serverId() {
      return this.serverId;
   }

   public String subMotd() {
      return this.subMotd;
   }

   public String gameType() {
      return this.gameType;
   }

   public boolean nintendoLimited() {
      return this.nintendoLimited;
   }

   public int ipv4Port() {
      return this.ipv4Port;
   }

   public int ipv6Port() {
      return this.ipv6Port;
   }

   public String[] extras() {
      return this.extras;
   }

   static BedrockPong fromRakNet(RakNetPong pong) {
      String info = new String(pong.getUserData(), StandardCharsets.UTF_8);
      BedrockPong bedrockPong = new BedrockPong();
      String[] infos = info.split(";");
      switch (infos.length) {
         default:
            bedrockPong.extras = new String[infos.length - 12];
            System.arraycopy(infos, 12, bedrockPong.extras, 0, bedrockPong.extras.length);
         case 12:
            try {
               bedrockPong.ipv6Port = Integer.parseInt(infos[11]);
            } catch (NumberFormatException var10) {
            }
         case 11:
            try {
               bedrockPong.ipv4Port = Integer.parseInt(infos[10]);
            } catch (NumberFormatException var9) {
            }
         case 10:
            bedrockPong.nintendoLimited = !"1".equalsIgnoreCase(infos[9]);
         case 9:
            bedrockPong.gameType = infos[8];
         case 8:
            bedrockPong.subMotd = infos[7];
         case 7:
            try {
               bedrockPong.serverId = Long.parseLong(infos[6]);
            } catch (NumberFormatException var8) {
            }
         case 6:
            try {
               bedrockPong.maximumPlayerCount = Integer.parseInt(infos[5]);
            } catch (NumberFormatException var7) {
            }
         case 5:
            try {
               bedrockPong.playerCount = Integer.parseInt(infos[4]);
            } catch (NumberFormatException var6) {
            }
         case 4:
            bedrockPong.version = infos[3];
         case 3:
            try {
               bedrockPong.protocolVersion = Integer.parseInt(infos[2]);
            } catch (NumberFormatException var5) {
            }
         case 2:
            bedrockPong.motd = infos[1];
         case 1:
            bedrockPong.edition = infos[0];
         case 0:
            return bedrockPong;
      }
   }

   byte[] toRakNet() {
      StringJoiner joiner = (new StringJoiner(";", "", ";")).add(this.edition).add(toString(this.motd)).add(Integer.toString(this.protocolVersion)).add(toString(this.version)).add(Integer.toString(this.playerCount)).add(Integer.toString(this.maximumPlayerCount)).add(Long.toString(this.serverId)).add(toString(this.subMotd)).add(toString(this.gameType)).add(this.nintendoLimited ? "0" : "1").add(Integer.toString(this.ipv4Port)).add(Integer.toString(this.ipv6Port));
      if (this.extras != null) {
         for(String extra : this.extras) {
            joiner.add(extra);
         }
      }

      return joiner.toString().getBytes(StandardCharsets.UTF_8);
   }

   private static String toString(String string) {
      return string == null ? "" : string;
   }

   public BedrockPong motd(String motd) {
      this.motd = motd;
      return this;
   }

   public BedrockPong protocolVersion(int protocolVersion) {
      this.protocolVersion = protocolVersion;
      return this;
   }

   public BedrockPong version(String version) {
      this.version = version;
      return this;
   }

   public BedrockPong playerCount(int playerCount) {
      this.playerCount = playerCount;
      return this;
   }

   public BedrockPong maximumPlayerCount(int maximumPlayerCount) {
      this.maximumPlayerCount = maximumPlayerCount;
      return this;
   }

   public BedrockPong serverId(long serverId) {
      this.serverId = serverId;
      return this;
   }

   public BedrockPong subMotd(String subMotd) {
      this.subMotd = subMotd;
      return this;
   }

   public BedrockPong gameType(String gameType) {
      this.gameType = gameType;
      return this;
   }

   public BedrockPong nintendoLimited(boolean nintendoLimited) {
      this.nintendoLimited = nintendoLimited;
      return this;
   }

   public BedrockPong ipv4Port(int ipv4Port) {
      this.ipv4Port = ipv4Port;
      return this;
   }

   public BedrockPong ipv6Port(int ipv6Port) {
      this.ipv6Port = ipv6Port;
      return this;
   }

   public BedrockPong extras(String[] extras) {
      this.extras = extras;
      return this;
   }

   public String getEdition() {
      return this.edition;
   }

   public String getMotd() {
      return this.motd;
   }

   public int getProtocolVersion() {
      return this.protocolVersion;
   }

   public String getVersion() {
      return this.version;
   }

   public int getPlayerCount() {
      return this.playerCount;
   }

   public int getMaximumPlayerCount() {
      return this.maximumPlayerCount;
   }

   public String getSubMotd() {
      return this.subMotd;
   }

   public String getGameType() {
      return this.gameType;
   }

   public boolean isNintendoLimited() {
      return this.nintendoLimited;
   }

   public int getIpv4Port() {
      return this.ipv4Port;
   }

   public int getIpv6Port() {
      return this.ipv6Port;
   }

   public String[] getExtras() {
      return this.extras;
   }

   public void setEdition(String edition) {
      this.edition = edition;
   }

   public void setMotd(String motd) {
      this.motd = motd;
   }

   public void setProtocolVersion(int protocolVersion) {
      this.protocolVersion = protocolVersion;
   }

   public void setVersion(String version) {
      this.version = version;
   }

   public void setPlayerCount(int playerCount) {
      this.playerCount = playerCount;
   }

   public void setMaximumPlayerCount(int maximumPlayerCount) {
      this.maximumPlayerCount = maximumPlayerCount;
   }

   public void setSubMotd(String subMotd) {
      this.subMotd = subMotd;
   }

   public void setGameType(String gameType) {
      this.gameType = gameType;
   }

   public void setNintendoLimited(boolean nintendoLimited) {
      this.nintendoLimited = nintendoLimited;
   }

   public void setIpv4Port(int ipv4Port) {
      this.ipv4Port = ipv4Port;
   }

   public void setIpv6Port(int ipv6Port) {
      this.ipv6Port = ipv6Port;
   }

   public void setExtras(String[] extras) {
      this.extras = extras;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BedrockPong)) {
         return false;
      } else {
         BedrockPong other = (BedrockPong)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getProtocolVersion() != other.getProtocolVersion()) {
            return false;
         } else if (this.getPlayerCount() != other.getPlayerCount()) {
            return false;
         } else if (this.getMaximumPlayerCount() != other.getMaximumPlayerCount()) {
            return false;
         } else if (this.getServerId() != other.getServerId()) {
            return false;
         } else if (this.isNintendoLimited() != other.isNintendoLimited()) {
            return false;
         } else if (this.getIpv4Port() != other.getIpv4Port()) {
            return false;
         } else if (this.getIpv6Port() != other.getIpv6Port()) {
            return false;
         } else {
            Object this$edition = this.getEdition();
            Object other$edition = other.getEdition();
            if (this$edition == null) {
               if (other$edition != null) {
                  return false;
               }
            } else if (!this$edition.equals(other$edition)) {
               return false;
            }

            Object this$motd = this.getMotd();
            Object other$motd = other.getMotd();
            if (this$motd == null) {
               if (other$motd != null) {
                  return false;
               }
            } else if (!this$motd.equals(other$motd)) {
               return false;
            }

            Object this$version = this.getVersion();
            Object other$version = other.getVersion();
            if (this$version == null) {
               if (other$version != null) {
                  return false;
               }
            } else if (!this$version.equals(other$version)) {
               return false;
            }

            Object this$subMotd = this.getSubMotd();
            Object other$subMotd = other.getSubMotd();
            if (this$subMotd == null) {
               if (other$subMotd != null) {
                  return false;
               }
            } else if (!this$subMotd.equals(other$subMotd)) {
               return false;
            }

            Object this$gameType = this.getGameType();
            Object other$gameType = other.getGameType();
            if (this$gameType == null) {
               if (other$gameType != null) {
                  return false;
               }
            } else if (!this$gameType.equals(other$gameType)) {
               return false;
            }

            if (!Arrays.deepEquals(this.getExtras(), other.getExtras())) {
               return false;
            } else {
               return true;
            }
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof BedrockPong;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getProtocolVersion();
      result = result * 59 + this.getPlayerCount();
      result = result * 59 + this.getMaximumPlayerCount();
      long $serverId = this.getServerId();
      result = result * 59 + (int)($serverId >>> 32 ^ $serverId);
      result = result * 59 + (this.isNintendoLimited() ? 79 : 97);
      result = result * 59 + this.getIpv4Port();
      result = result * 59 + this.getIpv6Port();
      Object $edition = this.getEdition();
      result = result * 59 + ($edition == null ? 43 : $edition.hashCode());
      Object $motd = this.getMotd();
      result = result * 59 + ($motd == null ? 43 : $motd.hashCode());
      Object $version = this.getVersion();
      result = result * 59 + ($version == null ? 43 : $version.hashCode());
      Object $subMotd = this.getSubMotd();
      result = result * 59 + ($subMotd == null ? 43 : $subMotd.hashCode());
      Object $gameType = this.getGameType();
      result = result * 59 + ($gameType == null ? 43 : $gameType.hashCode());
      result = result * 59 + Arrays.deepHashCode(this.getExtras());
      return result;
   }

   public String toString() {
      return "BedrockPong(edition=" + this.getEdition() + ", motd=" + this.getMotd() + ", protocolVersion=" + this.getProtocolVersion() + ", version=" + this.getVersion() + ", playerCount=" + this.getPlayerCount() + ", maximumPlayerCount=" + this.getMaximumPlayerCount() + ", serverId=" + this.getServerId() + ", subMotd=" + this.getSubMotd() + ", gameType=" + this.getGameType() + ", nintendoLimited=" + this.isNintendoLimited() + ", ipv4Port=" + this.getIpv4Port() + ", ipv6Port=" + this.getIpv6Port() + ", extras=" + Arrays.deepToString(this.getExtras()) + ")";
   }

   long getServerId() {
      return this.serverId;
   }

   void setServerId(long serverId) {
      this.serverId = serverId;
   }
}
