package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3f;

public class RespawnPacket implements BedrockPacket {
   private Vector3f position;
   private State state;
   private long runtimeEntityId;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.RESPAWN;
   }

   public Vector3f getPosition() {
      return this.position;
   }

   public State getState() {
      return this.state;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public void setPosition(Vector3f position) {
      this.position = position;
   }

   public void setState(State state) {
      this.state = state;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RespawnPacket)) {
         return false;
      } else {
         RespawnPacket other = (RespawnPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else {
            Object this$position = this.position;
            Object other$position = other.position;
            if (this$position == null) {
               if (other$position != null) {
                  return false;
               }
            } else if (!this$position.equals(other$position)) {
               return false;
            }

            Object this$state = this.state;
            Object other$state = other.state;
            if (this$state == null) {
               if (other$state != null) {
                  return false;
               }
            } else if (!this$state.equals(other$state)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RespawnPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      Object $position = this.position;
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      Object $state = this.state;
      result = result * 59 + ($state == null ? 43 : $state.hashCode());
      return result;
   }

   public String toString() {
      return "RespawnPacket(position=" + this.position + ", state=" + this.state + ", runtimeEntityId=" + this.runtimeEntityId + ")";
   }

   public static enum State {
      SERVER_SEARCHING,
      SERVER_READY,
      CLIENT_READY;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{SERVER_SEARCHING, SERVER_READY, CLIENT_READY};
      }
   }
}
