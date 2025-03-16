package com.nukkitx.protocol.bedrock.data.command;

import java.util.UUID;

public final class CommandOriginData {
   private final CommandOriginType origin;
   private final UUID uuid;
   private final String requestId;
   private final long event;

   public CommandOriginData(CommandOriginType origin, UUID uuid, String requestId, long event) {
      this.origin = origin;
      this.uuid = uuid;
      this.requestId = requestId;
      this.event = event;
   }

   public CommandOriginType getOrigin() {
      return this.origin;
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public String getRequestId() {
      return this.requestId;
   }

   public long getEvent() {
      return this.event;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CommandOriginData)) {
         return false;
      } else {
         CommandOriginData other = (CommandOriginData)o;
         if (this.getEvent() != other.getEvent()) {
            return false;
         } else {
            Object this$origin = this.getOrigin();
            Object other$origin = other.getOrigin();
            if (this$origin == null) {
               if (other$origin != null) {
                  return false;
               }
            } else if (!this$origin.equals(other$origin)) {
               return false;
            }

            Object this$uuid = this.getUuid();
            Object other$uuid = other.getUuid();
            if (this$uuid == null) {
               if (other$uuid != null) {
                  return false;
               }
            } else if (!this$uuid.equals(other$uuid)) {
               return false;
            }

            Object this$requestId = this.getRequestId();
            Object other$requestId = other.getRequestId();
            if (this$requestId == null) {
               if (other$requestId != null) {
                  return false;
               }
            } else if (!this$requestId.equals(other$requestId)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $event = this.getEvent();
      result = result * 59 + (int)($event >>> 32 ^ $event);
      Object $origin = this.getOrigin();
      result = result * 59 + ($origin == null ? 43 : $origin.hashCode());
      Object $uuid = this.getUuid();
      result = result * 59 + ($uuid == null ? 43 : $uuid.hashCode());
      Object $requestId = this.getRequestId();
      result = result * 59 + ($requestId == null ? 43 : $requestId.hashCode());
      return result;
   }

   public String toString() {
      return "CommandOriginData(origin=" + this.getOrigin() + ", uuid=" + this.getUuid() + ", requestId=" + this.getRequestId() + ", event=" + this.getEvent() + ")";
   }
}
