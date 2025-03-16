package com.nukkitx.protocol.bedrock.data.event;

public final class AgentCommandEventData implements EventData {
   private final AgentResult result;
   private final String command;
   private final String dataKey;
   private final int dataValue;
   private final String output;

   public EventDataType getType() {
      return EventDataType.AGENT_COMMAND;
   }

   public AgentCommandEventData(AgentResult result, String command, String dataKey, int dataValue, String output) {
      this.result = result;
      this.command = command;
      this.dataKey = dataKey;
      this.dataValue = dataValue;
      this.output = output;
   }

   public AgentResult getResult() {
      return this.result;
   }

   public String getCommand() {
      return this.command;
   }

   public String getDataKey() {
      return this.dataKey;
   }

   public int getDataValue() {
      return this.dataValue;
   }

   public String getOutput() {
      return this.output;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AgentCommandEventData)) {
         return false;
      } else {
         AgentCommandEventData other = (AgentCommandEventData)o;
         if (this.getDataValue() != other.getDataValue()) {
            return false;
         } else {
            Object this$result = this.getResult();
            Object other$result = other.getResult();
            if (this$result == null) {
               if (other$result != null) {
                  return false;
               }
            } else if (!this$result.equals(other$result)) {
               return false;
            }

            Object this$command = this.getCommand();
            Object other$command = other.getCommand();
            if (this$command == null) {
               if (other$command != null) {
                  return false;
               }
            } else if (!this$command.equals(other$command)) {
               return false;
            }

            Object this$dataKey = this.getDataKey();
            Object other$dataKey = other.getDataKey();
            if (this$dataKey == null) {
               if (other$dataKey != null) {
                  return false;
               }
            } else if (!this$dataKey.equals(other$dataKey)) {
               return false;
            }

            Object this$output = this.getOutput();
            Object other$output = other.getOutput();
            if (this$output == null) {
               if (other$output != null) {
                  return false;
               }
            } else if (!this$output.equals(other$output)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getDataValue();
      Object $result = this.getResult();
      result = result * 59 + ($result == null ? 43 : $result.hashCode());
      Object $command = this.getCommand();
      result = result * 59 + ($command == null ? 43 : $command.hashCode());
      Object $dataKey = this.getDataKey();
      result = result * 59 + ($dataKey == null ? 43 : $dataKey.hashCode());
      Object $output = this.getOutput();
      result = result * 59 + ($output == null ? 43 : $output.hashCode());
      return result;
   }

   public String toString() {
      return "AgentCommandEventData(result=" + this.getResult() + ", command=" + this.getCommand() + ", dataKey=" + this.getDataKey() + ", dataValue=" + this.getDataValue() + ", output=" + this.getOutput() + ")";
   }
}
