package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class ScriptMessagePacket implements BedrockPacket {
   private String channel;
   private String message;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SCRIPT_MESSAGE;
   }

   public String getChannel() {
      return this.channel;
   }

   public String getMessage() {
      return this.message;
   }

   public void setChannel(String channel) {
      this.channel = channel;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ScriptMessagePacket)) {
         return false;
      } else {
         ScriptMessagePacket other = (ScriptMessagePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$channel = this.channel;
            Object other$channel = other.channel;
            if (this$channel == null) {
               if (other$channel != null) {
                  return false;
               }
            } else if (!this$channel.equals(other$channel)) {
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

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ScriptMessagePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $channel = this.channel;
      result = result * 59 + ($channel == null ? 43 : $channel.hashCode());
      Object $message = this.message;
      result = result * 59 + ($message == null ? 43 : $message.hashCode());
      return result;
   }

   public String toString() {
      return "ScriptMessagePacket(channel=" + this.channel + ", message=" + this.message + ")";
   }
}
