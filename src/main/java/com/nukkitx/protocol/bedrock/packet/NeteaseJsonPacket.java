package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class NeteaseJsonPacket implements BedrockPacket {
   private String jsonString;

   public NeteaseJsonPacket() {
   }

   public NeteaseJsonPacket(String jsoString) {
      this.jsonString = jsoString;
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.NETEASE_JSON;
   }

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public String getJsonString() {
      return this.jsonString;
   }

   public void setJsonString(String jsonString) {
      this.jsonString = jsonString;
   }

   public String toString() {
      return "NeteaseJsonPacket(jsonString=" + this.getJsonString() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NeteaseJsonPacket)) {
         return false;
      } else {
         NeteaseJsonPacket other = (NeteaseJsonPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$jsonString = this.jsonString;
            Object other$jsonString = other.jsonString;
            if (this$jsonString == null) {
               if (other$jsonString != null) {
                  return false;
               }
            } else if (!this$jsonString.equals(other$jsonString)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof NeteaseJsonPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $jsonString = this.jsonString;
      result = result * 59 + ($jsonString == null ? 43 : $jsonString.hashCode());
      return result;
   }
}
