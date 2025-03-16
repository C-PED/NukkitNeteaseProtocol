package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class TextPacket implements BedrockPacket {
   private Type type;
   private boolean needsTranslation;
   private String sourceName;
   private String message;
   private List<String> parameters = new ObjectArrayList();
   private String xuid;
   private String platformChatId = "";

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.TEXT;
   }

   public Type getType() {
      return this.type;
   }

   public boolean isNeedsTranslation() {
      return this.needsTranslation;
   }

   public String getSourceName() {
      return this.sourceName;
   }

   public String getMessage() {
      return this.message;
   }

   public List<String> getParameters() {
      return this.parameters;
   }

   public String getXuid() {
      return this.xuid;
   }

   public String getPlatformChatId() {
      return this.platformChatId;
   }

   public void setType(Type type) {
      this.type = type;
   }

   public void setNeedsTranslation(boolean needsTranslation) {
      this.needsTranslation = needsTranslation;
   }

   public void setSourceName(String sourceName) {
      this.sourceName = sourceName;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public void setParameters(List<String> parameters) {
      this.parameters = parameters;
   }

   public void setXuid(String xuid) {
      this.xuid = xuid;
   }

   public void setPlatformChatId(String platformChatId) {
      this.platformChatId = platformChatId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TextPacket)) {
         return false;
      } else {
         TextPacket other = (TextPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.needsTranslation != other.needsTranslation) {
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

            Object this$sourceName = this.sourceName;
            Object other$sourceName = other.sourceName;
            if (this$sourceName == null) {
               if (other$sourceName != null) {
                  return false;
               }
            } else if (!this$sourceName.equals(other$sourceName)) {
               return false;
            }

            Object this$message = this.message;
            Object other$message = other.message;
            if (this$message == null) {
               if (other$message != null) {
                  return false;
               }
            } else if (!this$message.equals(other$message)) {
               return false;
            }

            Object this$parameters = this.parameters;
            Object other$parameters = other.parameters;
            if (this$parameters == null) {
               if (other$parameters != null) {
                  return false;
               }
            } else if (!this$parameters.equals(other$parameters)) {
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

            Object this$platformChatId = this.platformChatId;
            Object other$platformChatId = other.platformChatId;
            if (this$platformChatId == null) {
               if (other$platformChatId != null) {
                  return false;
               }
            } else if (!this$platformChatId.equals(other$platformChatId)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof TextPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.needsTranslation ? 79 : 97);
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $sourceName = this.sourceName;
      result = result * 59 + ($sourceName == null ? 43 : $sourceName.hashCode());
      Object $message = this.message;
      result = result * 59 + ($message == null ? 43 : $message.hashCode());
      Object $parameters = this.parameters;
      result = result * 59 + ($parameters == null ? 43 : $parameters.hashCode());
      Object $xuid = this.xuid;
      result = result * 59 + ($xuid == null ? 43 : $xuid.hashCode());
      Object $platformChatId = this.platformChatId;
      result = result * 59 + ($platformChatId == null ? 43 : $platformChatId.hashCode());
      return result;
   }

   public String toString() {
      return "TextPacket(type=" + this.type + ", needsTranslation=" + this.needsTranslation + ", sourceName=" + this.sourceName + ", message=" + this.message + ", parameters=" + this.parameters + ", xuid=" + this.xuid + ", platformChatId=" + this.platformChatId + ")";
   }

   public static enum Type {
      RAW,
      CHAT,
      TRANSLATION,
      POPUP,
      JUKEBOX_POPUP,
      TIP,
      SYSTEM,
      WHISPER,
      ANNOUNCEMENT,
      WHISPER_JSON,
      JSON,
      ANNOUNCEMENT_JSON;

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{RAW, CHAT, TRANSLATION, POPUP, JUKEBOX_POPUP, TIP, SYSTEM, WHISPER, ANNOUNCEMENT, WHISPER_JSON, JSON, ANNOUNCEMENT_JSON};
      }
   }
}
