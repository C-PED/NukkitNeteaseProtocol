package com.nukkitx.protocol.bedrock.data.event;

import java.util.List;

public final class SlashCommandExecutedEventData implements EventData {
   private final String commandName;
   private final int successCount;
   private final List<String> outputMessages;

   public EventDataType getType() {
      return EventDataType.SLASH_COMMAND_EXECUTED;
   }

   public SlashCommandExecutedEventData(String commandName, int successCount, List<String> outputMessages) {
      this.commandName = commandName;
      this.successCount = successCount;
      this.outputMessages = outputMessages;
   }

   public String getCommandName() {
      return this.commandName;
   }

   public int getSuccessCount() {
      return this.successCount;
   }

   public List<String> getOutputMessages() {
      return this.outputMessages;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SlashCommandExecutedEventData)) {
         return false;
      } else {
         SlashCommandExecutedEventData other = (SlashCommandExecutedEventData)o;
         if (this.getSuccessCount() != other.getSuccessCount()) {
            return false;
         } else {
            Object this$commandName = this.getCommandName();
            Object other$commandName = other.getCommandName();
            if (this$commandName == null) {
               if (other$commandName != null) {
                  return false;
               }
            } else if (!this$commandName.equals(other$commandName)) {
               return false;
            }

            Object this$outputMessages = this.getOutputMessages();
            Object other$outputMessages = other.getOutputMessages();
            if (this$outputMessages == null) {
               if (other$outputMessages != null) {
                  return false;
               }
            } else if (!this$outputMessages.equals(other$outputMessages)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getSuccessCount();
      Object $commandName = this.getCommandName();
      result = result * 59 + ($commandName == null ? 43 : $commandName.hashCode());
      Object $outputMessages = this.getOutputMessages();
      result = result * 59 + ($outputMessages == null ? 43 : $outputMessages.hashCode());
      return result;
   }

   public String toString() {
      return "SlashCommandExecutedEventData(commandName=" + this.getCommandName() + ", successCount=" + this.getSuccessCount() + ", outputMessages=" + this.getOutputMessages() + ")";
   }
}
