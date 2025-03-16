package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.DisconnectFailReason;
import com.nukkitx.protocol.common.PacketSignal;

public class DisconnectPacket implements BedrockPacket {
   private DisconnectFailReason reason;
   private boolean messageSkipped;
   private String kickMessage;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.DISCONNECT;
   }

   public DisconnectPacket() {
      this.reason = DisconnectFailReason.UNKNOWN;
   }

   public DisconnectFailReason getReason() {
      return this.reason;
   }

   public boolean isMessageSkipped() {
      return this.messageSkipped;
   }

   public String getKickMessage() {
      return this.kickMessage;
   }

   public void setReason(DisconnectFailReason reason) {
      this.reason = reason;
   }

   public void setMessageSkipped(boolean messageSkipped) {
      this.messageSkipped = messageSkipped;
   }

   public void setKickMessage(String kickMessage) {
      this.kickMessage = kickMessage;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DisconnectPacket)) {
         return false;
      } else {
         DisconnectPacket other = (DisconnectPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.messageSkipped != other.messageSkipped) {
            return false;
         } else {
            Object this$reason = this.reason;
            Object other$reason = other.reason;
            if (this$reason == null) {
               if (other$reason != null) {
                  return false;
               }
            } else if (!this$reason.equals(other$reason)) {
               return false;
            }

            Object this$kickMessage = this.kickMessage;
            Object other$kickMessage = other.kickMessage;
            if (this$kickMessage == null) {
               if (other$kickMessage != null) {
                  return false;
               }
            } else if (!this$kickMessage.equals(other$kickMessage)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof DisconnectPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.messageSkipped ? 79 : 97);
      Object $reason = this.reason;
      result = result * 59 + ($reason == null ? 43 : $reason.hashCode());
      Object $kickMessage = this.kickMessage;
      result = result * 59 + ($kickMessage == null ? 43 : $kickMessage.hashCode());
      return result;
   }

   public String toString() {
      return "DisconnectPacket(reason=" + this.reason + ", messageSkipped=" + this.messageSkipped + ", kickMessage=" + this.kickMessage + ")";
   }
}
