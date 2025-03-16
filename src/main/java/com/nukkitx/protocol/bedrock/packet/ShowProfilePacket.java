package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class ShowProfilePacket implements BedrockPacket {
   private String xuid;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SHOW_PROFILE;
   }

   public String getXuid() {
      return this.xuid;
   }

   public void setXuid(String xuid) {
      this.xuid = xuid;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ShowProfilePacket)) {
         return false;
      } else {
         ShowProfilePacket other = (ShowProfilePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$xuid = this.xuid;
            Object other$xuid = other.xuid;
            if (this$xuid == null) {
               if (other$xuid != null) {
                  return false;
               }
            } else if (!this$xuid.equals(other$xuid)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ShowProfilePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $xuid = this.xuid;
      result = result * 59 + ($xuid == null ? 43 : $xuid.hashCode());
      return result;
   }

   public String toString() {
      return "ShowProfilePacket(xuid=" + this.xuid + ")";
   }
}
