package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class RemoveObjectivePacket implements BedrockPacket {
   private String objectiveId;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.REMOVE_OBJECTIVE;
   }

   public String getObjectiveId() {
      return this.objectiveId;
   }

   public void setObjectiveId(String objectiveId) {
      this.objectiveId = objectiveId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RemoveObjectivePacket)) {
         return false;
      } else {
         RemoveObjectivePacket other = (RemoveObjectivePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$objectiveId = this.objectiveId;
            Object other$objectiveId = other.objectiveId;
            if (this$objectiveId == null) {
               if (other$objectiveId != null) {
                  return false;
               }
            } else if (!this$objectiveId.equals(other$objectiveId)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RemoveObjectivePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $objectiveId = this.objectiveId;
      result = result * 59 + ($objectiveId == null ? 43 : $objectiveId.hashCode());
      return result;
   }

   public String toString() {
      return "RemoveObjectivePacket(objectiveId=" + this.objectiveId + ")";
   }
}
