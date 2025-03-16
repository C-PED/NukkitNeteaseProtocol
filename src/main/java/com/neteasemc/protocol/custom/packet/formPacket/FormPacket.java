package com.neteasemc.protocol.custom.packet.formPacket;

import com.neteasemc.protocol.custom.GeyserBasePacket;
import com.neteasemc.protocol.custom.GeyserBasePacketHandler;
import com.neteasemc.protocol.custom.GeyserPacketType;
import com.nukkitx.protocol.common.PacketSignal;
import java.util.Arrays;
import org.bukkit.entity.Player;

public class FormPacket extends GeyserBasePacket {
   byte[] formData;

   public FormPacket() {
   }

   public FormPacket(byte[] formData) {
      this.formData = formData;
   }

   public GeyserPacketType getPacketType() {
      return GeyserPacketType.FORM;
   }

   public PacketSignal handle(GeyserBasePacketHandler handler) {
      return handler.handle(this);
   }

   public PacketSignal handle(GeyserBasePacketHandler handler, Player player) {
      return handler.handle(this, player);
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FormPacket)) {
         return false;
      } else {
         FormPacket other = (FormPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return Arrays.equals(this.formData, other.formData);
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof FormPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + Arrays.hashCode(this.formData);
      return result;
   }

   public String toString() {
      return "FormPacket(formData=" + Arrays.toString(this.getFormData()) + ")";
   }

   public byte[] getFormData() {
      return this.formData;
   }

   public void setFormData(byte[] formData) {
      this.formData = formData;
   }
}
