package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class PurchaseReceiptPacket implements BedrockPacket {
   private final List<String> receipts = new ObjectArrayList();

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.PURCHASE_RECEIPT;
   }

   public List<String> getReceipts() {
      return this.receipts;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PurchaseReceiptPacket)) {
         return false;
      } else {
         PurchaseReceiptPacket other = (PurchaseReceiptPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$receipts = this.receipts;
            Object other$receipts = other.receipts;
            if (this$receipts == null) {
               if (other$receipts != null) {
                  return false;
               }
            } else if (!this$receipts.equals(other$receipts)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PurchaseReceiptPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $receipts = this.receipts;
      result = result * 59 + ($receipts == null ? 43 : $receipts.hashCode());
      return result;
   }

   public String toString() {
      return "PurchaseReceiptPacket(receipts=" + this.receipts + ")";
   }
}
