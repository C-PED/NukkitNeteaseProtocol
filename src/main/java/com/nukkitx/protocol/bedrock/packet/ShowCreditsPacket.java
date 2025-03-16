package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class ShowCreditsPacket implements BedrockPacket {
   private long runtimeEntityId;
   private Status status;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SHOW_CREDITS;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public Status getStatus() {
      return this.status;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setStatus(Status status) {
      this.status = status;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ShowCreditsPacket)) {
         return false;
      } else {
         ShowCreditsPacket other = (ShowCreditsPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else {
            Object this$status = this.status;
            Object other$status = other.status;
            if (this$status == null) {
               if (other$status != null) {
                  return false;
               }
            } else if (!this$status.equals(other$status)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ShowCreditsPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      Object $status = this.status;
      result = result * 59 + ($status == null ? 43 : $status.hashCode());
      return result;
   }

   public String toString() {
      return "ShowCreditsPacket(runtimeEntityId=" + this.runtimeEntityId + ", status=" + this.status + ")";
   }

   public static enum Status {
      START_CREDITS,
      END_CREDITS;

      // $FF: synthetic method
      private static Status[] $values() {
         return new Status[]{START_CREDITS, END_CREDITS};
      }
   }
}
