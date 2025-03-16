package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class EditorNetworkPacket implements BedrockPacket {
   private Object payload;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.EDITOR_NETWORK;
   }

   public Object getPayload() {
      return this.payload;
   }

   public void setPayload(Object payload) {
      this.payload = payload;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EditorNetworkPacket)) {
         return false;
      } else {
         EditorNetworkPacket other = (EditorNetworkPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$payload = this.payload;
            Object other$payload = other.payload;
            if (this$payload == null) {
               if (other$payload != null) {
                  return false;
               }
            } else if (!this$payload.equals(other$payload)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EditorNetworkPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $payload = this.payload;
      result = result * 59 + ($payload == null ? 43 : $payload.hashCode());
      return result;
   }

   public String toString() {
      return "EditorNetworkPacket(payload=" + this.payload + ")";
   }
}
