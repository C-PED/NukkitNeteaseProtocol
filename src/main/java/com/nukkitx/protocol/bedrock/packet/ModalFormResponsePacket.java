package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.ModalFormCancelReason;
import com.nukkitx.protocol.common.PacketSignal;
import java.util.Optional;

public class ModalFormResponsePacket implements BedrockPacket {
   private int formId;
   private String formData;
   private Optional<ModalFormCancelReason> cancelReason;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.MODAL_FORM_RESPONSE;
   }

   public int getFormId() {
      return this.formId;
   }

   public String getFormData() {
      return this.formData;
   }

   public Optional<ModalFormCancelReason> getCancelReason() {
      return this.cancelReason;
   }

   public void setFormId(int formId) {
      this.formId = formId;
   }

   public void setFormData(String formData) {
      this.formData = formData;
   }

   public void setCancelReason(Optional<ModalFormCancelReason> cancelReason) {
      this.cancelReason = cancelReason;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ModalFormResponsePacket)) {
         return false;
      } else {
         ModalFormResponsePacket other = (ModalFormResponsePacket)o;
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

            Object this$cancelReason = this.cancelReason;
            Object other$cancelReason = other.cancelReason;
            if (this$cancelReason == null) {
               if (other$cancelReason != null) {
                  return false;
               }
            } else if (!this$cancelReason.equals(other$cancelReason)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ModalFormResponsePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.formId;
      Object $formData = this.formData;
      result = result * 59 + ($formData == null ? 43 : $formData.hashCode());
      Object $cancelReason = this.cancelReason;
      result = result * 59 + ($cancelReason == null ? 43 : $cancelReason.hashCode());
      return result;
   }

   public String toString() {
      return "ModalFormResponsePacket(formId=" + this.formId + ", formData=" + this.formData + ", cancelReason=" + this.cancelReason + ")";
   }
}
