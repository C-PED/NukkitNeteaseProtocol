package com.nukkitx.protocol.bedrock.data.event;

public class CodeBuilderActionEventData implements EventData {
   private final String action;

   public EventDataType getType() {
      return EventDataType.CODE_BUILDER_ACTION;
   }

   public CodeBuilderActionEventData(String action) {
      this.action = action;
   }

   public String getAction() {
      return this.action;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CodeBuilderActionEventData)) {
         return false;
      } else {
         CodeBuilderActionEventData other = (CodeBuilderActionEventData)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$action = this.getAction();
            Object other$action = other.getAction();
            if (this$action == null) {
               if (other$action != null) {
                  return false;
               }
            } else if (!this$action.equals(other$action)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CodeBuilderActionEventData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $action = this.getAction();
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      return result;
   }

   public String toString() {
      return "CodeBuilderActionEventData(action=" + this.getAction() + ")";
   }
}
