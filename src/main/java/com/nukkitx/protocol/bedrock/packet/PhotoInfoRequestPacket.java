package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class PhotoInfoRequestPacket implements BedrockPacket {
   private long photoId;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.PHOTO_INFO_REQUEST;
   }

   public long getPhotoId() {
      return this.photoId;
   }

   public void setPhotoId(long photoId) {
      this.photoId = photoId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PhotoInfoRequestPacket)) {
         return false;
      } else {
         PhotoInfoRequestPacket other = (PhotoInfoRequestPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.photoId == other.photoId;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PhotoInfoRequestPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $photoId = this.photoId;
      result = result * 59 + (int)($photoId >>> 32 ^ $photoId);
      return result;
   }

   public String toString() {
      return "PhotoInfoRequestPacket(photoId=" + this.photoId + ")";
   }
}
