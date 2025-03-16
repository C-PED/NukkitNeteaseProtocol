package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class SetCommandsEnabledPacket implements BedrockPacket {
   private boolean commandsEnabled;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_COMMANDS_ENABLED;
   }

   public boolean isCommandsEnabled() {
      return this.commandsEnabled;
   }

   public void setCommandsEnabled(boolean commandsEnabled) {
      this.commandsEnabled = commandsEnabled;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetCommandsEnabledPacket)) {
         return false;
      } else {
         SetCommandsEnabledPacket other = (SetCommandsEnabledPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.commandsEnabled == other.commandsEnabled;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetCommandsEnabledPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.commandsEnabled ? 79 : 97);
      return result;
   }

   public String toString() {
      return "SetCommandsEnabledPacket(commandsEnabled=" + this.commandsEnabled + ")";
   }
}
