package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class StopSoundPacket implements BedrockPacket {
   private String soundName;
   private boolean stoppingAllSound;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.STOP_SOUND;
   }

   public String getSoundName() {
      return this.soundName;
   }

   public boolean isStoppingAllSound() {
      return this.stoppingAllSound;
   }

   public void setSoundName(String soundName) {
      this.soundName = soundName;
   }

   public void setStoppingAllSound(boolean stoppingAllSound) {
      this.stoppingAllSound = stoppingAllSound;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StopSoundPacket)) {
         return false;
      } else {
         StopSoundPacket other = (StopSoundPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.stoppingAllSound != other.stoppingAllSound) {
            return false;
         } else {
            Object this$soundName = this.soundName;
            Object other$soundName = other.soundName;
            if (this$soundName == null) {
               if (other$soundName != null) {
                  return false;
               }
            } else if (!this$soundName.equals(other$soundName)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof StopSoundPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.stoppingAllSound ? 79 : 97);
      Object $soundName = this.soundName;
      result = result * 59 + ($soundName == null ? 43 : $soundName.hashCode());
      return result;
   }

   public String toString() {
      return "StopSoundPacket(soundName=" + this.soundName + ", stoppingAllSound=" + this.stoppingAllSound + ")";
   }
}
