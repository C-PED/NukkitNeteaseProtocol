package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.nbt.NbtMap;

public class PositionTrackingDBServerBroadcastPacket implements BedrockPacket {
   private Action action;
   private int trackingId;
   private NbtMap tag;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.POSITION_TRACKING_DB_SERVER_BROADCAST;
   }

   public Action getAction() {
      return this.action;
   }

   public int getTrackingId() {
      return this.trackingId;
   }

   public NbtMap getTag() {
      return this.tag;
   }

   public void setAction(Action action) {
      this.action = action;
   }

   public void setTrackingId(int trackingId) {
      this.trackingId = trackingId;
   }

   public void setTag(NbtMap tag) {
      this.tag = tag;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PositionTrackingDBServerBroadcastPacket)) {
         return false;
      } else {
         PositionTrackingDBServerBroadcastPacket other = (PositionTrackingDBServerBroadcastPacket)o;
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

            Object this$tag = this.tag;
            Object other$tag = other.tag;
            if (this$tag == null) {
               if (other$tag != null) {
                  return false;
               }
            } else if (!this$tag.equals(other$tag)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PositionTrackingDBServerBroadcastPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.trackingId;
      Object $action = this.action;
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      Object $tag = this.tag;
      result = result * 59 + ($tag == null ? 43 : $tag.hashCode());
      return result;
   }

   public String toString() {
      return "PositionTrackingDBServerBroadcastPacket(action=" + this.action + ", trackingId=" + this.trackingId + ", tag=" + this.tag + ")";
   }

   public static enum Action {
      UPDATE,
      DESTROY,
      NOT_FOUND;

      // $FF: synthetic method
      private static Action[] $values() {
         return new Action[]{UPDATE, DESTROY, NOT_FOUND};
      }
   }
}
