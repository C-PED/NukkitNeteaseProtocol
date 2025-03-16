package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.entity.EntityLinkData;
import com.nukkitx.protocol.common.PacketSignal;

public class SetEntityLinkPacket implements BedrockPacket {
   private EntityLinkData entityLink;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_ENTITY_LINK;
   }

   public EntityLinkData getEntityLink() {
      return this.entityLink;
   }

   public void setEntityLink(EntityLinkData entityLink) {
      this.entityLink = entityLink;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetEntityLinkPacket)) {
         return false;
      } else {
         SetEntityLinkPacket other = (SetEntityLinkPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$entityLink = this.entityLink;
            Object other$entityLink = other.entityLink;
            if (this$entityLink == null) {
               if (other$entityLink != null) {
                  return false;
               }
            } else if (!this$entityLink.equals(other$entityLink)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetEntityLinkPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $entityLink = this.entityLink;
      result = result * 59 + ($entityLink == null ? 43 : $entityLink.hashCode());
      return result;
   }

   public String toString() {
      return "SetEntityLinkPacket(entityLink=" + this.entityLink + ")";
   }
}
