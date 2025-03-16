package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.UUID;

public class EmoteListPacket implements BedrockPacket {
   private long runtimeEntityId;
   private final List<UUID> pieceIds = new ObjectArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.EMOTE_LIST;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public List<UUID> getPieceIds() {
      return this.pieceIds;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EmoteListPacket)) {
         return false;
      } else {
         EmoteListPacket other = (EmoteListPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else {
            Object this$pieceIds = this.pieceIds;
            Object other$pieceIds = other.pieceIds;
            if (this$pieceIds == null) {
               if (other$pieceIds != null) {
                  return false;
               }
            } else if (!this$pieceIds.equals(other$pieceIds)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EmoteListPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      Object $pieceIds = this.pieceIds;
      result = result * 59 + ($pieceIds == null ? 43 : $pieceIds.hashCode());
      return result;
   }

   public String toString() {
      return "EmoteListPacket(runtimeEntityId=" + this.runtimeEntityId + ", pieceIds=" + this.pieceIds + ")";
   }
}
