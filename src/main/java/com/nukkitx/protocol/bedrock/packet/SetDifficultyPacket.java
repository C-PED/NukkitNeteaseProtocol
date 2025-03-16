package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class SetDifficultyPacket implements BedrockPacket {
   private int difficulty;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_DIFFICULTY;
   }

   public int getDifficulty() {
      return this.difficulty;
   }

   public void setDifficulty(int difficulty) {
      this.difficulty = difficulty;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetDifficultyPacket)) {
         return false;
      } else {
         SetDifficultyPacket other = (SetDifficultyPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return this.difficulty == other.difficulty;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetDifficultyPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.difficulty;
      return result;
   }

   public String toString() {
      return "SetDifficultyPacket(difficulty=" + this.difficulty + ")";
   }
}
