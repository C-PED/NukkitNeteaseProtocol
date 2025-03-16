package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class SetLocalPlayerAsInitializedPacket implements BedrockPacket {
   private long runtimeEntityId;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_LOCAL_PLAYER_AS_INITIALIZED;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetLocalPlayerAsInitializedPacket)) {
         return false;
      } else {
         SetLocalPlayerAsInitializedPacket other = (SetLocalPlayerAsInitializedPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.runtimeEntityId == other.runtimeEntityId;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetLocalPlayerAsInitializedPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      return result;
   }

   public String toString() {
      return "SetLocalPlayerAsInitializedPacket(runtimeEntityId=" + this.runtimeEntityId + ")";
   }
}
