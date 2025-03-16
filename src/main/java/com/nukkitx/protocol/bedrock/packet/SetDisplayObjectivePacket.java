package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class SetDisplayObjectivePacket implements BedrockPacket {
   private String displaySlot;
   private String objectiveId;
   private String displayName;
   private String criteria;
   private int sortOrder;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_DISPLAY_OBJECTIVE;
   }

   public String getDisplaySlot() {
      return this.displaySlot;
   }

   public String getObjectiveId() {
      return this.objectiveId;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public String getCriteria() {
      return this.criteria;
   }

   public int getSortOrder() {
      return this.sortOrder;
   }

   public void setDisplaySlot(String displaySlot) {
      this.displaySlot = displaySlot;
   }

   public void setObjectiveId(String objectiveId) {
      this.objectiveId = objectiveId;
   }

   public void setDisplayName(String displayName) {
      this.displayName = displayName;
   }

   public void setCriteria(String criteria) {
      this.criteria = criteria;
   }

   public void setSortOrder(int sortOrder) {
      this.sortOrder = sortOrder;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetDisplayObjectivePacket)) {
         return false;
      } else {
         SetDisplayObjectivePacket other = (SetDisplayObjectivePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.sortOrder != other.sortOrder) {
            return false;
         } else {
            Object this$displaySlot = this.displaySlot;
            Object other$displaySlot = other.displaySlot;
            if (this$displaySlot == null) {
               if (other$displaySlot != null) {
                  return false;
               }
            } else if (!this$displaySlot.equals(other$displaySlot)) {
               return false;
            }

            Object this$objectiveId = this.objectiveId;
            Object other$objectiveId = other.objectiveId;
            if (this$objectiveId == null) {
               if (other$objectiveId != null) {
                  return false;
               }
            } else if (!this$objectiveId.equals(other$objectiveId)) {
               return false;
            }

            Object this$displayName = this.displayName;
            Object other$displayName = other.displayName;
            if (this$displayName == null) {
               if (other$displayName != null) {
                  return false;
               }
            } else if (!this$displayName.equals(other$displayName)) {
               return false;
            }

            Object this$criteria = this.criteria;
            Object other$criteria = other.criteria;
            if (this$criteria == null) {
               if (other$criteria != null) {
                  return false;
               }
            } else if (!this$criteria.equals(other$criteria)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetDisplayObjectivePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.sortOrder;
      Object $displaySlot = this.displaySlot;
      result = result * 59 + ($displaySlot == null ? 43 : $displaySlot.hashCode());
      Object $objectiveId = this.objectiveId;
      result = result * 59 + ($objectiveId == null ? 43 : $objectiveId.hashCode());
      Object $displayName = this.displayName;
      result = result * 59 + ($displayName == null ? 43 : $displayName.hashCode());
      Object $criteria = this.criteria;
      result = result * 59 + ($criteria == null ? 43 : $criteria.hashCode());
      return result;
   }

   public String toString() {
      return "SetDisplayObjectivePacket(displaySlot=" + this.displaySlot + ", objectiveId=" + this.objectiveId + ", displayName=" + this.displayName + ", criteria=" + this.criteria + ", sortOrder=" + this.sortOrder + ")";
   }
}
