package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.command.CommandData;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class AvailableCommandsPacket implements BedrockPacket {
   private final List<CommandData> commands = new ObjectArrayList();

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.AVAILABLE_COMMANDS;
   }

   public List<CommandData> getCommands() {
      return this.commands;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AvailableCommandsPacket)) {
         return false;
      } else {
         AvailableCommandsPacket other = (AvailableCommandsPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$commands = this.commands;
            Object other$commands = other.commands;
            if (this$commands == null) {
               if (other$commands != null) {
                  return false;
               }
            } else if (!this$commands.equals(other$commands)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AvailableCommandsPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $commands = this.commands;
      result = result * 59 + ($commands == null ? 43 : $commands.hashCode());
      return result;
   }

   public String toString() {
      return "AvailableCommandsPacket(commands=" + this.commands + ")";
   }
}
