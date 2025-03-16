package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3f;

public class PlaySoundPacket implements BedrockPacket {
   private String sound;
   private Vector3f position;
   private float volume;
   private float pitch;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.PLAY_SOUND;
   }

   public String getSound() {
      return this.sound;
   }

   public Vector3f getPosition() {
      return this.position;
   }

   public float getVolume() {
      return this.volume;
   }

   public float getPitch() {
      return this.pitch;
   }

   public void setSound(String sound) {
      this.sound = sound;
   }

   public void setPosition(Vector3f position) {
      this.position = position;
   }

   public void setVolume(float volume) {
      this.volume = volume;
   }

   public void setPitch(float pitch) {
      this.pitch = pitch;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PlaySoundPacket)) {
         return false;
      } else {
         PlaySoundPacket other = (PlaySoundPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.volume, other.volume) != 0) {
            return false;
         } else if (Float.compare(this.pitch, other.pitch) != 0) {
            return false;
         } else {
            Object this$sound = this.sound;
            Object other$sound = other.sound;
            if (this$sound == null) {
               if (other$sound != null) {
                  return false;
               }
            } else if (!this$sound.equals(other$sound)) {
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
      return other instanceof PlaySoundPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.volume);
      result = result * 59 + Float.floatToIntBits(this.pitch);
      Object $sound = this.sound;
      result = result * 59 + ($sound == null ? 43 : $sound.hashCode());
      Object $position = this.position;
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      return result;
   }

   public String toString() {
      return "PlaySoundPacket(sound=" + this.sound + ", position=" + this.position + ", volume=" + this.volume + ", pitch=" + this.pitch + ")";
   }
}
