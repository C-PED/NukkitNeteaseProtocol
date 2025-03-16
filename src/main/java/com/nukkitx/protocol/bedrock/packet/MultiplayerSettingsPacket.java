package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.MultiplayerMode;
import com.nukkitx.protocol.common.PacketSignal;

public class MultiplayerSettingsPacket implements BedrockPacket {
   private MultiplayerMode mode;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.MULTIPLAYER_SETTINGS;
   }

   public MultiplayerMode getMode() {
      return this.mode;
   }

   public void setMode(MultiplayerMode mode) {
      this.mode = mode;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MultiplayerSettingsPacket)) {
         return false;
      } else {
         MultiplayerSettingsPacket other = (MultiplayerSettingsPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$mode = this.mode;
            Object other$mode = other.mode;
            if (this$mode == null) {
               if (other$mode != null) {
                  return false;
               }
            } else if (!this$mode.equals(other$mode)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof MultiplayerSettingsPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $mode = this.mode;
      result = result * 59 + ($mode == null ? 43 : $mode.hashCode());
      return result;
   }

   public String toString() {
      return "MultiplayerSettingsPacket(mode=" + this.mode + ")";
   }
}
