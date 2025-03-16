package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.LevelEventType;
import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3f;

public class LevelEventPacket implements BedrockPacket {
   private LevelEventType type;
   private Vector3f position;
   private int data;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.LEVEL_EVENT;
   }

   public LevelEventType getType() {
      return this.type;
   }

   public Vector3f getPosition() {
      return this.position;
   }

   public int getData() {
      return this.data;
   }

   public void setType(LevelEventType type) {
      this.type = type;
   }

   public void setPosition(Vector3f position) {
      this.position = position;
   }

   public void setData(int data) {
      this.data = data;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LevelEventPacket)) {
         return false;
      } else {
         LevelEventPacket other = (LevelEventPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.data != other.data) {
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

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof LevelEventPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.data;
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $position = this.position;
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      return result;
   }

   public String toString() {
      return "LevelEventPacket(type=" + this.type + ", position=" + this.position + ", data=" + this.data + ")";
   }
}
