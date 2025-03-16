package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.SimpleEventType;
import com.nukkitx.protocol.common.PacketSignal;

public class SimpleEventPacket implements BedrockPacket {
   private SimpleEventType event;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SIMPLE_EVENT;
   }

   public SimpleEventType getEvent() {
      return this.event;
   }

   public void setEvent(SimpleEventType event) {
      this.event = event;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SimpleEventPacket)) {
         return false;
      } else {
         SimpleEventPacket other = (SimpleEventPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$event = this.event;
            Object other$event = other.event;
            if (this$event == null) {
               if (other$event != null) {
                  return false;
               }
            } else if (!this$event.equals(other$event)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SimpleEventPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $event = this.event;
      result = result * 59 + ($event == null ? 43 : $event.hashCode());
      return result;
   }

   public String toString() {
      return "SimpleEventPacket(event=" + this.event + ")";
   }
}
