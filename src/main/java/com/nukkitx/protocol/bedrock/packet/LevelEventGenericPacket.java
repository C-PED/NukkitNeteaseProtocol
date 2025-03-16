package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.common.PacketSignal;

public class LevelEventGenericPacket implements BedrockPacket {
   private LevelEventType type;
   private Object tag;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.LEVEL_EVENT_GENERIC;
   }

   public LevelEventType getType() {
      return this.type;
   }

   public Object getTag() {
      return this.tag;
   }

   public void setType(LevelEventType type) {
      this.type = type;
   }

   public void setTag(Object tag) {
      this.tag = tag;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LevelEventGenericPacket)) {
         return false;
      } else {
         LevelEventGenericPacket other = (LevelEventGenericPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$type = this.type;
            Object other$type = other.type;
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

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
      return other instanceof LevelEventGenericPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $tag = this.tag;
      result = result * 59 + ($tag == null ? 43 : $tag.hashCode());
      return result;
   }

   public String toString() {
      return "LevelEventGenericPacket(type=" + this.type + ", tag=" + this.tag + ")";
   }
}
