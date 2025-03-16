package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class SetTitlePacket implements BedrockPacket {
   private Type type;
   private String text;
   private int fadeInTime;
   private int stayTime;
   private int fadeOutTime;
   private String xuid;
   private String platformOnlineId;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_TITLE;
   }

   public Type getType() {
      return this.type;
   }

   public String getText() {
      return this.text;
   }

   public int getFadeInTime() {
      return this.fadeInTime;
   }

   public int getStayTime() {
      return this.stayTime;
   }

   public int getFadeOutTime() {
      return this.fadeOutTime;
   }

   public String getXuid() {
      return this.xuid;
   }

   public String getPlatformOnlineId() {
      return this.platformOnlineId;
   }

   public void setType(Type type) {
      this.type = type;
   }

   public void setText(String text) {
      this.text = text;
   }

   public void setFadeInTime(int fadeInTime) {
      this.fadeInTime = fadeInTime;
   }

   public void setStayTime(int stayTime) {
      this.stayTime = stayTime;
   }

   public void setFadeOutTime(int fadeOutTime) {
      this.fadeOutTime = fadeOutTime;
   }

   public void setXuid(String xuid) {
      this.xuid = xuid;
   }

   public void setPlatformOnlineId(String platformOnlineId) {
      this.platformOnlineId = platformOnlineId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetTitlePacket)) {
         return false;
      } else {
         SetTitlePacket other = (SetTitlePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.fadeInTime != other.fadeInTime) {
            return false;
         } else if (this.stayTime != other.stayTime) {
            return false;
         } else if (this.fadeOutTime != other.fadeOutTime) {
            return false;
         } else {
            Object this$type = this.type;
            Object other$type = other.type;
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$text = this.text;
            Object other$text = other.text;
            if (this$text == null) {
               if (other$text != null) {
                  return false;
               }
            } else if (!this$text.equals(other$text)) {
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

            Object this$platformOnlineId = this.platformOnlineId;
            Object other$platformOnlineId = other.platformOnlineId;
            if (this$platformOnlineId == null) {
               if (other$platformOnlineId != null) {
                  return false;
               }
            } else if (!this$platformOnlineId.equals(other$platformOnlineId)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetTitlePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.fadeInTime;
      result = result * 59 + this.stayTime;
      result = result * 59 + this.fadeOutTime;
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $text = this.text;
      result = result * 59 + ($text == null ? 43 : $text.hashCode());
      Object $xuid = this.xuid;
      result = result * 59 + ($xuid == null ? 43 : $xuid.hashCode());
      Object $platformOnlineId = this.platformOnlineId;
      result = result * 59 + ($platformOnlineId == null ? 43 : $platformOnlineId.hashCode());
      return result;
   }

   public String toString() {
      return "SetTitlePacket(type=" + this.type + ", text=" + this.text + ", fadeInTime=" + this.fadeInTime + ", stayTime=" + this.stayTime + ", fadeOutTime=" + this.fadeOutTime + ", xuid=" + this.xuid + ", platformOnlineId=" + this.platformOnlineId + ")";
   }

   public static enum Type {
      CLEAR,
      RESET,
      TITLE,
      SUBTITLE,
      ACTIONBAR,
      TIMES,
      TITLE_JSON,
      SUBTITLE_JSON,
      ACTIONBAR_JSON;

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{CLEAR, RESET, TITLE, SUBTITLE, ACTIONBAR, TIMES, TITLE_JSON, SUBTITLE_JSON, ACTIONBAR_JSON};
      }
   }
}
