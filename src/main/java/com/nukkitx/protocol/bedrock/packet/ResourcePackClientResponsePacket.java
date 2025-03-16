package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class ResourcePackClientResponsePacket implements BedrockPacket {
   private final List<String> packIds = new ObjectArrayList();
   private Status status;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.RESOURCE_PACK_CLIENT_RESPONSE;
   }

   public List<String> getPackIds() {
      return this.packIds;
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
      } else if (!(o instanceof ResourcePackClientResponsePacket)) {
         return false;
      } else {
         ResourcePackClientResponsePacket other = (ResourcePackClientResponsePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$packIds = this.packIds;
            Object other$packIds = other.packIds;
            if (this$packIds == null) {
               if (other$packIds != null) {
                  return false;
               }
            } else if (!this$packIds.equals(other$packIds)) {
               return false;
            }

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
      return other instanceof ResourcePackClientResponsePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $packIds = this.packIds;
      result = result * 59 + ($packIds == null ? 43 : $packIds.hashCode());
      Object $status = this.status;
      result = result * 59 + ($status == null ? 43 : $status.hashCode());
      return result;
   }

   public String toString() {
      return "ResourcePackClientResponsePacket(packIds=" + this.packIds + ", status=" + this.status + ")";
   }

   public static enum Status {
      NONE,
      REFUSED,
      SEND_PACKS,
      HAVE_ALL_PACKS,
      COMPLETED;

      // $FF: synthetic method
      private static Status[] $values() {
         return new Status[]{NONE, REFUSED, SEND_PACKS, HAVE_ALL_PACKS, COMPLETED};
      }
   }
}
