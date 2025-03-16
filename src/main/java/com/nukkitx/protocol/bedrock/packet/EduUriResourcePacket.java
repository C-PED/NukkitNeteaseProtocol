package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.EduSharedUriResource;
import com.nukkitx.protocol.common.PacketSignal;

public class EduUriResourcePacket implements BedrockPacket {
   private EduSharedUriResource eduSharedUriResource;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.EDU_URI_RESOURCE;
   }

   public EduSharedUriResource getEduSharedUriResource() {
      return this.eduSharedUriResource;
   }

   public void setEduSharedUriResource(EduSharedUriResource eduSharedUriResource) {
      this.eduSharedUriResource = eduSharedUriResource;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EduUriResourcePacket)) {
         return false;
      } else {
         EduUriResourcePacket other = (EduUriResourcePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$eduSharedUriResource = this.eduSharedUriResource;
            Object other$eduSharedUriResource = other.eduSharedUriResource;
            if (this$eduSharedUriResource == null) {
               if (other$eduSharedUriResource != null) {
                  return false;
               }
            } else if (!this$eduSharedUriResource.equals(other$eduSharedUriResource)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EduUriResourcePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $eduSharedUriResource = this.eduSharedUriResource;
      result = result * 59 + ($eduSharedUriResource == null ? 43 : $eduSharedUriResource.hashCode());
      return result;
   }

   public String toString() {
      return "EduUriResourcePacket(eduSharedUriResource=" + this.eduSharedUriResource + ")";
   }
}
