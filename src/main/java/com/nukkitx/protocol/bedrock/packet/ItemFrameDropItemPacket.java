package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3i;

/** @deprecated */
@Deprecated
public class ItemFrameDropItemPacket implements BedrockPacket {
   private Vector3i blockPosition;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ITEM_FRAME_DROP_ITEM;
   }

   public Vector3i getBlockPosition() {
      return this.blockPosition;
   }

   public void setBlockPosition(Vector3i blockPosition) {
      this.blockPosition = blockPosition;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ItemFrameDropItemPacket)) {
         return false;
      } else {
         ItemFrameDropItemPacket other = (ItemFrameDropItemPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$blockPosition = this.blockPosition;
            Object other$blockPosition = other.blockPosition;
            if (this$blockPosition == null) {
               if (other$blockPosition != null) {
                  return false;
               }
            } else if (!this$blockPosition.equals(other$blockPosition)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ItemFrameDropItemPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $blockPosition = this.blockPosition;
      result = result * 59 + ($blockPosition == null ? 43 : $blockPosition.hashCode());
      return result;
   }

   public String toString() {
      return "ItemFrameDropItemPacket(blockPosition=" + this.blockPosition + ")";
   }
}
