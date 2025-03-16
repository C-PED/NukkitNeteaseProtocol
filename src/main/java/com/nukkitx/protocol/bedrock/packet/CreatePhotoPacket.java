package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class CreatePhotoPacket implements BedrockPacket {
   private long id;
   private String photoName;
   private String photoItemName;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CREATE_PHOTO;
   }

   public long getId() {
      return this.id;
   }

   public String getPhotoName() {
      return this.photoName;
   }

   public String getPhotoItemName() {
      return this.photoItemName;
   }

   public void setId(long id) {
      this.id = id;
   }

   public void setPhotoName(String photoName) {
      this.photoName = photoName;
   }

   public void setPhotoItemName(String photoItemName) {
      this.photoItemName = photoItemName;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CreatePhotoPacket)) {
         return false;
      } else {
         CreatePhotoPacket other = (CreatePhotoPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.id != other.id) {
            return false;
         } else {
            Object this$photoName = this.photoName;
            Object other$photoName = other.photoName;
            if (this$photoName == null) {
               if (other$photoName != null) {
                  return false;
               }
            } else if (!this$photoName.equals(other$photoName)) {
               return false;
            }

            Object this$photoItemName = this.photoItemName;
            Object other$photoItemName = other.photoItemName;
            if (this$photoItemName == null) {
               if (other$photoItemName != null) {
                  return false;
               }
            } else if (!this$photoItemName.equals(other$photoItemName)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CreatePhotoPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $id = this.id;
      result = result * 59 + (int)($id >>> 32 ^ $id);
      Object $photoName = this.photoName;
      result = result * 59 + ($photoName == null ? 43 : $photoName.hashCode());
      Object $photoItemName = this.photoItemName;
      result = result * 59 + ($photoItemName == null ? 43 : $photoItemName.hashCode());
      return result;
   }

   public String toString() {
      return "CreatePhotoPacket(id=" + this.id + ", photoName=" + this.photoName + ", photoItemName=" + this.photoItemName + ")";
   }
}
