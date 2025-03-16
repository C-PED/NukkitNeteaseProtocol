package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class ToastRequestPacket implements BedrockPacket {
   private String title;
   private String content;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.TOAST_REQUEST;
   }

   public String getTitle() {
      return this.title;
   }

   public String getContent() {
      return this.content;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ToastRequestPacket)) {
         return false;
      } else {
         ToastRequestPacket other = (ToastRequestPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$title = this.title;
            Object other$title = other.title;
            if (this$title == null) {
               if (other$title != null) {
                  return false;
               }
            } else if (!this$title.equals(other$title)) {
               return false;
            }

            Object this$content = this.content;
            Object other$content = other.content;
            if (this$content == null) {
               if (other$content != null) {
                  return false;
               }
            } else if (!this$content.equals(other$content)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ToastRequestPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $title = this.title;
      result = result * 59 + ($title == null ? 43 : $title.hashCode());
      Object $content = this.content;
      result = result * 59 + ($content == null ? 43 : $content.hashCode());
      return result;
   }

   public String toString() {
      return "ToastRequestPacket(title=" + this.title + ", content=" + this.content + ")";
   }
}
