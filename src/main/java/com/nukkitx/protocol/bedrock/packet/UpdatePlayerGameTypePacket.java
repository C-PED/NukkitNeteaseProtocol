package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.GameType;
import com.nukkitx.protocol.common.PacketSignal;

public class UpdatePlayerGameTypePacket implements BedrockPacket {
   private GameType gameType;
   private long entityId;
   private int tick;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.UPDATE_PLAYER_GAME_TYPE;
   }

   public GameType getGameType() {
      return this.gameType;
   }

   public long getEntityId() {
      return this.entityId;
   }

   public int getTick() {
      return this.tick;
   }

   public void setGameType(GameType gameType) {
      this.gameType = gameType;
   }

   public void setEntityId(long entityId) {
      this.entityId = entityId;
   }

   public void setTick(int tick) {
      this.tick = tick;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UpdatePlayerGameTypePacket)) {
         return false;
      } else {
         UpdatePlayerGameTypePacket other = (UpdatePlayerGameTypePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getEntityId() != other.getEntityId()) {
            return false;
         } else if (this.getTick() != other.getTick()) {
            return false;
         } else {
            Object this$gameType = this.getGameType();
            Object other$gameType = other.getGameType();
            if (this$gameType == null) {
               if (other$gameType != null) {
                  return false;
               }
            } else if (!this$gameType.equals(other$gameType)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof UpdatePlayerGameTypePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $entityId = this.getEntityId();
      result = result * 59 + (int)($entityId >>> 32 ^ $entityId);
      result = result * 59 + this.getTick();
      Object $gameType = this.getGameType();
      result = result * 59 + ($gameType == null ? 43 : $gameType.hashCode());
      return result;
   }

   public String toString() {
      return "UpdatePlayerGameTypePacket(gameType=" + this.gameType + ", entityId=" + this.entityId + ", tick=" + this.tick + ")";
   }
}
