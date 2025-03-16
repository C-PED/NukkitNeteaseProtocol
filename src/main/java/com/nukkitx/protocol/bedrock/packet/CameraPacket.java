package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class CameraPacket implements BedrockPacket {
   private long cameraUniqueEntityId;
   private long playerUniqueEntityId;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CAMERA;
   }

   public long getCameraUniqueEntityId() {
      return this.cameraUniqueEntityId;
   }

   public long getPlayerUniqueEntityId() {
      return this.playerUniqueEntityId;
   }

   public void setCameraUniqueEntityId(long cameraUniqueEntityId) {
      this.cameraUniqueEntityId = cameraUniqueEntityId;
   }

   public void setPlayerUniqueEntityId(long playerUniqueEntityId) {
      this.playerUniqueEntityId = playerUniqueEntityId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CameraPacket)) {
         return false;
      } else {
         CameraPacket other = (CameraPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.cameraUniqueEntityId != other.cameraUniqueEntityId) {
            return false;
         } else {
            return this.playerUniqueEntityId == other.playerUniqueEntityId;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CameraPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $cameraUniqueEntityId = this.cameraUniqueEntityId;
      result = result * 59 + (int)($cameraUniqueEntityId >>> 32 ^ $cameraUniqueEntityId);
      long $playerUniqueEntityId = this.playerUniqueEntityId;
      result = result * 59 + (int)($playerUniqueEntityId >>> 32 ^ $playerUniqueEntityId);
      return result;
   }

   public String toString() {
      return "CameraPacket(cameraUniqueEntityId=" + this.cameraUniqueEntityId + ", playerUniqueEntityId=" + this.playerUniqueEntityId + ")";
   }
}
