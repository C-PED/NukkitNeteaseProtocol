package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class FilterTextPacket implements BedrockPacket {
   private String text;
   private boolean fromServer;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.FILTER_TEXT;
   }

   public String getText() {
      return this.text;
   }

   public boolean isFromServer() {
      return this.fromServer;
   }

   public void setText(String text) {
      this.text = text;
   }

   public void setFromServer(boolean fromServer) {
      this.fromServer = fromServer;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FilterTextPacket)) {
         return false;
      } else {
         FilterTextPacket other = (FilterTextPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.fromServer != other.fromServer) {
            return false;
         } else {
            Object this$text = this.text;
            Object other$text = other.text;
            if (this$text == null) {
               if (other$text != null) {
                  return false;
               }
            } else if (!this$text.equals(other$text)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof FilterTextPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.fromServer ? 79 : 97);
      Object $text = this.text;
      result = result * 59 + ($text == null ? 43 : $text.hashCode());
      return result;
   }

   public String toString() {
      return "FilterTextPacket(text=" + this.text + ", fromServer=" + this.fromServer + ")";
   }
}
