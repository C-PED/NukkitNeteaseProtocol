package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class SetPlayerGameTypePacket implements BedrockPacket {
   private int gamemode;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_PLAYER_GAME_TYPE;
   }

   public int getGamemode() {
      return this.gamemode;
   }

   public void setGamemode(int gamemode) {
      this.gamemode = gamemode;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetPlayerGameTypePacket)) {
         return false;
      } else {
         SetPlayerGameTypePacket other = (SetPlayerGameTypePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.gamemode == other.gamemode;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetPlayerGameTypePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.gamemode;
      return result;
   }

   public String toString() {
      return "SetPlayerGameTypePacket(gamemode=" + this.gamemode + ")";
   }
}
