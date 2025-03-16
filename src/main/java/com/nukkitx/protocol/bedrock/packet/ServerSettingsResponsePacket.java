package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class ServerSettingsResponsePacket implements BedrockPacket {
   private int formId;
   private String formData;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SERVER_SETTINGS_RESPONSE;
   }

   public int getFormId() {
      return this.formId;
   }

   public String getFormData() {
      return this.formData;
   }

   public void setFormId(int formId) {
      this.formId = formId;
   }

   public void setFormData(String formData) {
      this.formData = formData;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ServerSettingsResponsePacket)) {
         return false;
      } else {
         ServerSettingsResponsePacket other = (ServerSettingsResponsePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.formId != other.formId) {
            return false;
         } else {
            Object this$formData = this.formData;
            Object other$formData = other.formData;
            if (this$formData == null) {
               if (other$formData != null) {
                  return false;
               }
            } else if (!this$formData.equals(other$formData)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ServerSettingsResponsePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.formId;
      Object $formData = this.formData;
      result = result * 59 + ($formData == null ? 43 : $formData.hashCode());
      return result;
   }

   public String toString() {
      return "ServerSettingsResponsePacket(formId=" + this.formId + ", formData=" + this.formData + ")";
   }
}
