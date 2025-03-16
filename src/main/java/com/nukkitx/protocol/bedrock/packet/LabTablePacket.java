package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.LabTableReactionType;
import com.nukkitx.protocol.bedrock.data.inventory.LabTableType;
import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3i;

public class LabTablePacket implements BedrockPacket {
   private LabTableType type;
   private Vector3i position;
   private LabTableReactionType reactionType;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.LAB_TABLE;
   }

   public LabTableType getType() {
      return this.type;
   }

   public Vector3i getPosition() {
      return this.position;
   }

   public LabTableReactionType getReactionType() {
      return this.reactionType;
   }

   public void setType(LabTableType type) {
      this.type = type;
   }

   public void setPosition(Vector3i position) {
      this.position = position;
   }

   public void setReactionType(LabTableReactionType reactionType) {
      this.reactionType = reactionType;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LabTablePacket)) {
         return false;
      } else {
         LabTablePacket other = (LabTablePacket)o;
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

            Object this$position = this.position;
            Object other$position = other.position;
            if (this$position == null) {
               if (other$position != null) {
                  return false;
               }
            } else if (!this$position.equals(other$position)) {
               return false;
            }

            Object this$reactionType = this.reactionType;
            Object other$reactionType = other.reactionType;
            if (this$reactionType == null) {
               if (other$reactionType != null) {
                  return false;
               }
            } else if (!this$reactionType.equals(other$reactionType)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof LabTablePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $position = this.position;
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      Object $reactionType = this.reactionType;
      result = result * 59 + ($reactionType == null ? 43 : $reactionType.hashCode());
      return result;
   }

   public String toString() {
      return "LabTablePacket(type=" + this.type + ", position=" + this.position + ", reactionType=" + this.reactionType + ")";
   }
}
