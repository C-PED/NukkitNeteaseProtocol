package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class PositionTrackingDBClientRequestPacket implements BedrockPacket {
   private Action action;
   private int trackingId;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.POSITION_TRACKING_DB_CLIENT_REQUEST;
   }

   public Action getAction() {
      return this.action;
   }

   public int getTrackingId() {
      return this.trackingId;
   }

   public void setAction(Action action) {
      this.action = action;
   }

   public void setTrackingId(int trackingId) {
      this.trackingId = trackingId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PositionTrackingDBClientRequestPacket)) {
         return false;
      } else {
         PositionTrackingDBClientRequestPacket other = (PositionTrackingDBClientRequestPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.trackingId != other.trackingId) {
            return false;
         } else {
            Object this$action = this.action;
            Object other$action = other.action;
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
      return other instanceof PositionTrackingDBClientRequestPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.trackingId;
      Object $action = this.action;
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      return result;
   }

   public String toString() {
      return "PositionTrackingDBClientRequestPacket(action=" + this.action + ", trackingId=" + this.trackingId + ")";
   }

   public static enum Action {
      QUERY;

      // $FF: synthetic method
      private static Action[] $values() {
         return new Action[]{QUERY};
      }
   }
}
