package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.event.EventData;
import com.nukkitx.protocol.common.PacketSignal;

public class EventPacket implements BedrockPacket {
   private long uniqueEntityId;
   private byte usePlayerId;
   private EventData eventData;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.EVENT;
   }

   public long getUniqueEntityId() {
      return this.uniqueEntityId;
   }

   public byte getUsePlayerId() {
      return this.usePlayerId;
   }

   public EventData getEventData() {
      return this.eventData;
   }

   public void setUniqueEntityId(long uniqueEntityId) {
      this.uniqueEntityId = uniqueEntityId;
   }

   public void setUsePlayerId(byte usePlayerId) {
      this.usePlayerId = usePlayerId;
   }

   public void setEventData(EventData eventData) {
      this.eventData = eventData;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EventPacket)) {
         return false;
      } else {
         EventPacket other = (EventPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.uniqueEntityId != other.uniqueEntityId) {
            return false;
         } else if (this.usePlayerId != other.usePlayerId) {
            return false;
         } else {
            Object this$eventData = this.eventData;
            Object other$eventData = other.eventData;
            if (this$eventData == null) {
               if (other$eventData != null) {
                  return false;
               }
            } else if (!this$eventData.equals(other$eventData)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EventPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $uniqueEntityId = this.uniqueEntityId;
      result = result * 59 + (int)($uniqueEntityId >>> 32 ^ $uniqueEntityId);
      result = result * 59 + this.usePlayerId;
      Object $eventData = this.eventData;
      result = result * 59 + ($eventData == null ? 43 : $eventData.hashCode());
      return result;
   }

   public String toString() {
      return "EventPacket(uniqueEntityId=" + this.uniqueEntityId + ", usePlayerId=" + this.usePlayerId + ", eventData=" + this.eventData + ")";
   }

   public static enum Event {
      ACHIEVEMENT_AWARDED,
      ENTITY_INTERACT,
      PORTAL_BUILT,
      PORTAL_USED,
      MOB_KILLED,
      CAULDRON_USED,
      PLAYER_DEATH,
      BOSS_KILLED,
      /** @deprecated */
      @Deprecated
      AGENT_COMMAND,
      AGENT_CREATED,
      PATTERN_REMOVED,
      SLASH_COMMAND_EXECUTED,
      FISH_BUCKETED,
      MOB_BORN,
      PET_DIED,
      CAULDRON_BLOCK_USED,
      COMPOSTER_BLOCK_USED,
      BELL_BLOCK_USED;

      // $FF: synthetic method
      private static Event[] $values() {
         return new Event[]{ACHIEVEMENT_AWARDED, ENTITY_INTERACT, PORTAL_BUILT, PORTAL_USED, MOB_KILLED, CAULDRON_USED, PLAYER_DEATH, BOSS_KILLED, AGENT_COMMAND, AGENT_CREATED, PATTERN_REMOVED, SLASH_COMMAND_EXECUTED, FISH_BUCKETED, MOB_BORN, PET_DIED, CAULDRON_BLOCK_USED, COMPOSTER_BLOCK_USED, BELL_BLOCK_USED};
      }
   }
}
