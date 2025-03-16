package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class SettingsCommandPacket implements BedrockPacket {
   private String command;
   private boolean suppressingOutput;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SETTINGS_COMMAND;
   }

   public String getCommand() {
      return this.command;
   }

   public boolean isSuppressingOutput() {
      return this.suppressingOutput;
   }

   public void setCommand(String command) {
      this.command = command;
   }

   public void setSuppressingOutput(boolean suppressingOutput) {
      this.suppressingOutput = suppressingOutput;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SettingsCommandPacket)) {
         return false;
      } else {
         SettingsCommandPacket other = (SettingsCommandPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.suppressingOutput != other.suppressingOutput) {
            return false;
         } else {
            Object this$command = this.command;
            Object other$command = other.command;
            if (this$command == null) {
               if (other$command != null) {
                  return false;
               }
            } else if (!this$command.equals(other$command)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SettingsCommandPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.suppressingOutput ? 79 : 97);
      Object $command = this.command;
      result = result * 59 + ($command == null ? 43 : $command.hashCode());
      return result;
   }

   public String toString() {
      return "SettingsCommandPacket(command=" + this.command + ", suppressingOutput=" + this.suppressingOutput + ")";
   }
}
