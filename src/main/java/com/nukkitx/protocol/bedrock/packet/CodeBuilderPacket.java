package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class CodeBuilderPacket implements BedrockPacket {
   private String url;
   private boolean opening;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CODE_BUILDER;
   }

   public String getUrl() {
      return this.url;
   }

   public boolean isOpening() {
      return this.opening;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public void setOpening(boolean opening) {
      this.opening = opening;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CodeBuilderPacket)) {
         return false;
      } else {
         CodeBuilderPacket other = (CodeBuilderPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.opening != other.opening) {
            return false;
         } else {
            Object this$url = this.url;
            Object other$url = other.url;
            if (this$url == null) {
               if (other$url != null) {
                  return false;
               }
            } else if (!this$url.equals(other$url)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CodeBuilderPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.opening ? 79 : 97);
      Object $url = this.url;
      result = result * 59 + ($url == null ? 43 : $url.hashCode());
      return result;
   }

   public String toString() {
      return "CodeBuilderPacket(url=" + this.url + ", opening=" + this.opening + ")";
   }
}
