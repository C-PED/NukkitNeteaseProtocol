package com.nukkitx.protocol.bedrock.data.command;

import java.util.Arrays;
import lombok.NonNull;

public final class CommandOutputMessage {
   private final boolean internal;
   private final @NonNull String messageId;
   private final @NonNull String[] parameters;

   public CommandOutputMessage(boolean internal, @NonNull String messageId, @NonNull String[] parameters) {
      if (messageId == null) {
         throw new NullPointerException("messageId is marked non-null but is null");
      } else if (parameters == null) {
         throw new NullPointerException("parameters is marked non-null but is null");
      } else {
         this.internal = internal;
         this.messageId = messageId;
         this.parameters = parameters;
      }
   }

   public boolean isInternal() {
      return this.internal;
   }

   public @NonNull String getMessageId() {
      return this.messageId;
   }

   public @NonNull String[] getParameters() {
      return this.parameters;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CommandOutputMessage)) {
         return false;
      } else {
         CommandOutputMessage other = (CommandOutputMessage)o;
         if (this.isInternal() != other.isInternal()) {
            return false;
         } else {
            Object this$messageId = this.getMessageId();
            Object other$messageId = other.getMessageId();
            if (this$messageId == null) {
               if (other$messageId != null) {
                  return false;
               }
            } else if (!this$messageId.equals(other$messageId)) {
               return false;
            }

            if (!Arrays.deepEquals(this.getParameters(), other.getParameters())) {
               return false;
            } else {
               return true;
            }
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.isInternal() ? 79 : 97);
      Object $messageId = this.getMessageId();
      result = result * 59 + ($messageId == null ? 43 : $messageId.hashCode());
      result = result * 59 + Arrays.deepHashCode(this.getParameters());
      return result;
   }

   public String toString() {
      return "CommandOutputMessage(internal=" + this.isInternal() + ", messageId=" + this.getMessageId() + ", parameters=" + Arrays.deepToString(this.getParameters()) + ")";
   }
}
