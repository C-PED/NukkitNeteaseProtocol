package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.command.CommandEnumData;
import com.nukkitx.protocol.bedrock.data.command.SoftEnumUpdateType;
import com.nukkitx.protocol.common.PacketSignal;

public class UpdateSoftEnumPacket implements BedrockPacket {
   private CommandEnumData softEnum;
   private SoftEnumUpdateType type;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.UPDATE_SOFT_ENUM;
   }

   public CommandEnumData getSoftEnum() {
      return this.softEnum;
   }

   public SoftEnumUpdateType getType() {
      return this.type;
   }

   public void setSoftEnum(CommandEnumData softEnum) {
      this.softEnum = softEnum;
   }

   public void setType(SoftEnumUpdateType type) {
      this.type = type;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UpdateSoftEnumPacket)) {
         return false;
      } else {
         UpdateSoftEnumPacket other = (UpdateSoftEnumPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$softEnum = this.softEnum;
            Object other$softEnum = other.softEnum;
            if (this$softEnum == null) {
               if (other$softEnum != null) {
                  return false;
               }
            } else if (!this$softEnum.equals(other$softEnum)) {
               return false;
            }

            Object this$type = this.type;
            Object other$type = other.type;
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof UpdateSoftEnumPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $softEnum = this.softEnum;
      result = result * 59 + ($softEnum == null ? 43 : $softEnum.hashCode());
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      return result;
   }

   public String toString() {
      return "UpdateSoftEnumPacket(softEnum=" + this.softEnum + ", type=" + this.type + ")";
   }
}
