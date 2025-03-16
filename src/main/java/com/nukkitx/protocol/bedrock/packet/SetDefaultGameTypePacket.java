package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class SetDefaultGameTypePacket implements BedrockPacket {
   private int gamemode;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_DEFAULT_GAME_TYPE;
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
      } else if (!(o instanceof SetDefaultGameTypePacket)) {
         return false;
      } else {
         SetDefaultGameTypePacket other = (SetDefaultGameTypePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.gamemode == other.gamemode;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetDefaultGameTypePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.gamemode;
      return result;
   }

   public String toString() {
      return "SetDefaultGameTypePacket(gamemode=" + this.gamemode + ")";
   }
}
