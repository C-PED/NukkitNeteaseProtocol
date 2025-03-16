package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class AddPaintingPacket extends AddHangingEntityPacket {
   private String motive;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ADD_PAINTING;
   }

   public String getMotive() {
      return this.motive;
   }

   public void setMotive(String motive) {
      this.motive = motive;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AddPaintingPacket)) {
         return false;
      } else {
         AddPaintingPacket other = (AddPaintingPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (!super.equals(o)) {
            return false;
         } else {
            Object this$motive = this.motive;
            Object other$motive = other.motive;
            if (this$motive == null) {
               if (other$motive != null) {
                  return false;
               }
            } else if (!this$motive.equals(other$motive)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AddPaintingPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = super.hashCode();
      Object $motive = this.motive;
      result = result * 59 + ($motive == null ? 43 : $motive.hashCode());
      return result;
   }

   public String toString() {
      return "AddPaintingPacket(motive=" + this.motive + ")";
   }
}
