package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.command.CommandOriginData;
import com.nukkitx.protocol.bedrock.data.command.CommandOutputMessage;
import com.nukkitx.protocol.bedrock.data.command.CommandOutputType;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class CommandOutputPacket implements BedrockPacket {
   private final List<CommandOutputMessage> messages = new ObjectArrayList();
   private CommandOriginData commandOriginData;
   private CommandOutputType type;
   private int successCount;
   private String data;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.COMMAND_OUTPUT;
   }

   public List<CommandOutputMessage> getMessages() {
      return this.messages;
   }

   public CommandOriginData getCommandOriginData() {
      return this.commandOriginData;
   }

   public CommandOutputType getType() {
      return this.type;
   }

   public int getSuccessCount() {
      return this.successCount;
   }

   public String getData() {
      return this.data;
   }

   public void setCommandOriginData(CommandOriginData commandOriginData) {
      this.commandOriginData = commandOriginData;
   }

   public void setType(CommandOutputType type) {
      this.type = type;
   }

   public void setSuccessCount(int successCount) {
      this.successCount = successCount;
   }

   public void setData(String data) {
      this.data = data;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CommandOutputPacket)) {
         return false;
      } else {
         CommandOutputPacket other = (CommandOutputPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.successCount != other.successCount) {
            return false;
         } else {
            Object this$messages = this.messages;
            Object other$messages = other.messages;
            if (this$messages == null) {
               if (other$messages != null) {
                  return false;
               }
            } else if (!this$messages.equals(other$messages)) {
               return false;
            }

            Object this$commandOriginData = this.commandOriginData;
            Object other$commandOriginData = other.commandOriginData;
            if (this$commandOriginData == null) {
               if (other$commandOriginData != null) {
                  return false;
               }
            } else if (!this$commandOriginData.equals(other$commandOriginData)) {
               return false;
            }

            Object this$type = this.type;
            Object other$type = other.type;
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$data = this.data;
            Object other$data = other.data;
            if (this$data == null) {
               if (other$data != null) {
                  return false;
               }
            } else if (!this$data.equals(other$data)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CommandOutputPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.successCount;
      Object $messages = this.messages;
      result = result * 59 + ($messages == null ? 43 : $messages.hashCode());
      Object $commandOriginData = this.commandOriginData;
      result = result * 59 + ($commandOriginData == null ? 43 : $commandOriginData.hashCode());
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $data = this.data;
      result = result * 59 + ($data == null ? 43 : $data.hashCode());
      return result;
   }

   public String toString() {
      return "CommandOutputPacket(messages=" + this.messages + ", commandOriginData=" + this.commandOriginData + ", type=" + this.type + ", successCount=" + this.successCount + ", data=" + this.data + ")";
   }
}
