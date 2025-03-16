package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class PlayStatusPacket implements BedrockPacket {
   private Status status;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.PLAY_STATUS;
   }

   public Status getStatus() {
      return this.status;
   }

   public void setStatus(Status status) {
      this.status = status;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PlayStatusPacket)) {
         return false;
      } else {
         PlayStatusPacket other = (PlayStatusPacket)o;
         if (!other.canEqual(this)) {
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
      return other instanceof PlayStatusPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $status = this.status;
      result = result * 59 + ($status == null ? 43 : $status.hashCode());
      return result;
   }

   public String toString() {
      return "PlayStatusPacket(status=" + this.status + ")";
   }

   public static enum Status {
      LOGIN_SUCCESS,
      LOGIN_FAILED_CLIENT_OLD,
      LOGIN_FAILED_SERVER_OLD,
      PLAYER_SPAWN,
      LOGIN_FAILED_INVALID_TENANT,
      LOGIN_FAILED_EDITION_MISMATCH_EDU_TO_VANILLA,
      LOGIN_FAILED_EDITION_MISMATCH_VANILLA_TO_EDU,
      FAILED_SERVER_FULL_SUB_CLIENT,
      EDITOR_TO_VANILLA_MISMATCH,
      VANILLA_TO_EDITOR_MISMATCH;

      // $FF: synthetic method
      private static Status[] $values() {
         return new Status[]{LOGIN_SUCCESS, LOGIN_FAILED_CLIENT_OLD, LOGIN_FAILED_SERVER_OLD, PLAYER_SPAWN, LOGIN_FAILED_INVALID_TENANT, LOGIN_FAILED_EDITION_MISMATCH_EDU_TO_VANILLA, LOGIN_FAILED_EDITION_MISMATCH_VANILLA_TO_EDU, FAILED_SERVER_FULL_SUB_CLIENT, EDITOR_TO_VANILLA_MISMATCH, VANILLA_TO_EDITOR_MISMATCH};
      }
   }
}
