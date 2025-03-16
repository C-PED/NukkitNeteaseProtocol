package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class AddBehaviorTreePacket implements BedrockPacket {
   private String behaviorTreeJson;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ADD_BEHAVIOR_TREE;
   }

   public String getBehaviorTreeJson() {
      return this.behaviorTreeJson;
   }

   public void setBehaviorTreeJson(String behaviorTreeJson) {
      this.behaviorTreeJson = behaviorTreeJson;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AddBehaviorTreePacket)) {
         return false;
      } else {
         AddBehaviorTreePacket other = (AddBehaviorTreePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$behaviorTreeJson = this.behaviorTreeJson;
            Object other$behaviorTreeJson = other.behaviorTreeJson;
            if (this$behaviorTreeJson == null) {
               if (other$behaviorTreeJson != null) {
                  return false;
               }
            } else if (!this$behaviorTreeJson.equals(other$behaviorTreeJson)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AddBehaviorTreePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $behaviorTreeJson = this.behaviorTreeJson;
      result = result * 59 + ($behaviorTreeJson == null ? 43 : $behaviorTreeJson.hashCode());
      return result;
   }

   public String toString() {
      return "AddBehaviorTreePacket(behaviorTreeJson=" + this.behaviorTreeJson + ")";
   }
}
