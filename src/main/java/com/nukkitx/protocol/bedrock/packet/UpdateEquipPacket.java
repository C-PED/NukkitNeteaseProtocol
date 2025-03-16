package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.nbt.NbtMap;

public class UpdateEquipPacket implements BedrockPacket {
   private short windowId;
   private short windowType;
   private int size;
   private long uniqueEntityId;
   private NbtMap tag;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.UPDATE_EQUIP;
   }

   public short getWindowId() {
      return this.windowId;
   }

   public short getWindowType() {
      return this.windowType;
   }

   public int getSize() {
      return this.size;
   }

   public long getUniqueEntityId() {
      return this.uniqueEntityId;
   }

   public NbtMap getTag() {
      return this.tag;
   }

   public void setWindowId(short windowId) {
      this.windowId = windowId;
   }

   public void setWindowType(short windowType) {
      this.windowType = windowType;
   }

   public void setSize(int size) {
      this.size = size;
   }

   public void setUniqueEntityId(long uniqueEntityId) {
      this.uniqueEntityId = uniqueEntityId;
   }

   public void setTag(NbtMap tag) {
      this.tag = tag;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UpdateEquipPacket)) {
         return false;
      } else {
         UpdateEquipPacket other = (UpdateEquipPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.windowId != other.windowId) {
            return false;
         } else if (this.windowType != other.windowType) {
            return false;
         } else if (this.size != other.size) {
            return false;
         } else if (this.uniqueEntityId != other.uniqueEntityId) {
            return false;
         } else {
            Object this$tag = this.tag;
            Object other$tag = other.tag;
            if (this$tag == null) {
               if (other$tag != null) {
                  return false;
               }
            } else if (!this$tag.equals(other$tag)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof UpdateEquipPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.windowId;
      result = result * 59 + this.windowType;
      result = result * 59 + this.size;
      long $uniqueEntityId = this.uniqueEntityId;
      result = result * 59 + (int)($uniqueEntityId >>> 32 ^ $uniqueEntityId);
      Object $tag = this.tag;
      result = result * 59 + ($tag == null ? 43 : $tag.hashCode());
      return result;
   }

   public String toString() {
      return "UpdateEquipPacket(windowId=" + this.windowId + ", windowType=" + this.windowType + ", size=" + this.size + ", uniqueEntityId=" + this.uniqueEntityId + ", tag=" + this.tag + ")";
   }
}
