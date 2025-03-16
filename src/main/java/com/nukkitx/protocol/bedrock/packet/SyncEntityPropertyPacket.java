package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.nbt.NbtMap;

public class SyncEntityPropertyPacket implements BedrockPacket {
   private NbtMap data;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SYNC_ENTITY_PROPERTY;
   }

   public NbtMap getData() {
      return this.data;
   }

   public void setData(NbtMap data) {
      this.data = data;
   }

   public String toString() {
      return "SyncEntityPropertyPacket(data=" + this.getData() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SyncEntityPropertyPacket)) {
         return false;
      } else {
         SyncEntityPropertyPacket other = (SyncEntityPropertyPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$data = this.data;
            Object other$data = other.data;
            if (this$data == null) {
               if (other$data != null) {
                  return false;
               }
            } else if (!this$data.equals(other$data)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SyncEntityPropertyPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $data = this.data;
      result = result * 59 + ($data == null ? 43 : $data.hashCode());
      return result;
   }
}
