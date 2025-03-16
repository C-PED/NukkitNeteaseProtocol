package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3i;

public class BlockPickRequestPacket implements BedrockPacket {
   private Vector3i blockPosition;
   private boolean addUserData;
   private int hotbarSlot;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.BLOCK_PICK_REQUEST;
   }

   public Vector3i getBlockPosition() {
      return this.blockPosition;
   }

   public boolean isAddUserData() {
      return this.addUserData;
   }

   public int getHotbarSlot() {
      return this.hotbarSlot;
   }

   public void setBlockPosition(Vector3i blockPosition) {
      this.blockPosition = blockPosition;
   }

   public void setAddUserData(boolean addUserData) {
      this.addUserData = addUserData;
   }

   public void setHotbarSlot(int hotbarSlot) {
      this.hotbarSlot = hotbarSlot;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BlockPickRequestPacket)) {
         return false;
      } else {
         BlockPickRequestPacket other = (BlockPickRequestPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.addUserData != other.addUserData) {
            return false;
         } else if (this.hotbarSlot != other.hotbarSlot) {
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
      return other instanceof BlockPickRequestPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.addUserData ? 79 : 97);
      result = result * 59 + this.hotbarSlot;
      Object $blockPosition = this.blockPosition;
      result = result * 59 + ($blockPosition == null ? 43 : $blockPosition.hashCode());
      return result;
   }

   public String toString() {
      return "BlockPickRequestPacket(blockPosition=" + this.blockPosition + ", addUserData=" + this.addUserData + ", hotbarSlot=" + this.hotbarSlot + ")";
   }
}
