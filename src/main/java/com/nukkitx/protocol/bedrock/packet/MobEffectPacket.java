package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class MobEffectPacket implements BedrockPacket {
   private long runtimeEntityId;
   private Event event;
   private int effectId;
   private int amplifier;
   private boolean particles;
   private int duration;
   private long tick;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.MOB_EFFECT;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public Event getEvent() {
      return this.event;
   }

   public int getEffectId() {
      return this.effectId;
   }

   public int getAmplifier() {
      return this.amplifier;
   }

   public boolean isParticles() {
      return this.particles;
   }

   public int getDuration() {
      return this.duration;
   }

   public long getTick() {
      return this.tick;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setEvent(Event event) {
      this.event = event;
   }

   public void setEffectId(int effectId) {
      this.effectId = effectId;
   }

   public void setAmplifier(int amplifier) {
      this.amplifier = amplifier;
   }

   public void setParticles(boolean particles) {
      this.particles = particles;
   }

   public void setDuration(int duration) {
      this.duration = duration;
   }

   public void setTick(long tick) {
      this.tick = tick;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MobEffectPacket)) {
         return false;
      } else {
         MobEffectPacket other = (MobEffectPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else if (this.effectId != other.effectId) {
            return false;
         } else if (this.amplifier != other.amplifier) {
            return false;
         } else if (this.particles != other.particles) {
            return false;
         } else if (this.duration != other.duration) {
            return false;
         } else if (this.tick != other.tick) {
            return false;
         } else {
            Object this$event = this.event;
            Object other$event = other.event;
            if (this$event == null) {
               if (other$event != null) {
                  return false;
               }
            } else if (!this$event.equals(other$event)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof MobEffectPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      result = result * 59 + this.effectId;
      result = result * 59 + this.amplifier;
      result = result * 59 + (this.particles ? 79 : 97);
      result = result * 59 + this.duration;
      long $tick = this.tick;
      result = result * 59 + (int)($tick >>> 32 ^ $tick);
      Object $event = this.event;
      result = result * 59 + ($event == null ? 43 : $event.hashCode());
      return result;
   }

   public String toString() {
      return "MobEffectPacket(runtimeEntityId=" + this.runtimeEntityId + ", event=" + this.event + ", effectId=" + this.effectId + ", amplifier=" + this.amplifier + ", particles=" + this.particles + ", duration=" + this.duration + ", tick=" + this.tick + ")";
   }

   public static enum Event {
      NONE,
      ADD,
      MODIFY,
      REMOVE;

      // $FF: synthetic method
      private static Event[] $values() {
         return new Event[]{NONE, ADD, MODIFY, REMOVE};
      }
   }
}
