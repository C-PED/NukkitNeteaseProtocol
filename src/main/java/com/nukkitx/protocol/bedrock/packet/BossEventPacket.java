package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class BossEventPacket implements BedrockPacket {
   private long bossUniqueEntityId;
   private Action action;
   private long playerUniqueEntityId;
   private String title;
   private float healthPercentage;
   private int darkenSky;
   private int color;
   private int overlay;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.BOSS_EVENT;
   }

   public long getBossUniqueEntityId() {
      return this.bossUniqueEntityId;
   }

   public Action getAction() {
      return this.action;
   }

   public long getPlayerUniqueEntityId() {
      return this.playerUniqueEntityId;
   }

   public String getTitle() {
      return this.title;
   }

   public float getHealthPercentage() {
      return this.healthPercentage;
   }

   public int getDarkenSky() {
      return this.darkenSky;
   }

   public int getColor() {
      return this.color;
   }

   public int getOverlay() {
      return this.overlay;
   }

   public void setBossUniqueEntityId(long bossUniqueEntityId) {
      this.bossUniqueEntityId = bossUniqueEntityId;
   }

   public void setAction(Action action) {
      this.action = action;
   }

   public void setPlayerUniqueEntityId(long playerUniqueEntityId) {
      this.playerUniqueEntityId = playerUniqueEntityId;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public void setHealthPercentage(float healthPercentage) {
      this.healthPercentage = healthPercentage;
   }

   public void setDarkenSky(int darkenSky) {
      this.darkenSky = darkenSky;
   }

   public void setColor(int color) {
      this.color = color;
   }

   public void setOverlay(int overlay) {
      this.overlay = overlay;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BossEventPacket)) {
         return false;
      } else {
         BossEventPacket other = (BossEventPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.bossUniqueEntityId != other.bossUniqueEntityId) {
            return false;
         } else if (this.playerUniqueEntityId != other.playerUniqueEntityId) {
            return false;
         } else if (Float.compare(this.healthPercentage, other.healthPercentage) != 0) {
            return false;
         } else if (this.darkenSky != other.darkenSky) {
            return false;
         } else if (this.color != other.color) {
            return false;
         } else if (this.overlay != other.overlay) {
            return false;
         } else {
            Object this$action = this.action;
            Object other$action = other.action;
            if (this$action == null) {
               if (other$action != null) {
                  return false;
               }
            } else if (!this$action.equals(other$action)) {
               return false;
            }

            Object this$title = this.title;
            Object other$title = other.title;
            if (this$title == null) {
               if (other$title != null) {
                  return false;
               }
            } else if (!this$title.equals(other$title)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof BossEventPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $bossUniqueEntityId = this.bossUniqueEntityId;
      result = result * 59 + (int)($bossUniqueEntityId >>> 32 ^ $bossUniqueEntityId);
      long $playerUniqueEntityId = this.playerUniqueEntityId;
      result = result * 59 + (int)($playerUniqueEntityId >>> 32 ^ $playerUniqueEntityId);
      result = result * 59 + Float.floatToIntBits(this.healthPercentage);
      result = result * 59 + this.darkenSky;
      result = result * 59 + this.color;
      result = result * 59 + this.overlay;
      Object $action = this.action;
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      Object $title = this.title;
      result = result * 59 + ($title == null ? 43 : $title.hashCode());
      return result;
   }

   public String toString() {
      return "BossEventPacket(bossUniqueEntityId=" + this.bossUniqueEntityId + ", action=" + this.action + ", playerUniqueEntityId=" + this.playerUniqueEntityId + ", title=" + this.title + ", healthPercentage=" + this.healthPercentage + ", darkenSky=" + this.darkenSky + ", color=" + this.color + ", overlay=" + this.overlay + ")";
   }

   public static enum Action {
      CREATE,
      REGISTER_PLAYER,
      REMOVE,
      UNREGISTER_PLAYER,
      UPDATE_PERCENTAGE,
      UPDATE_NAME,
      UPDATE_PROPERTIES,
      UPDATE_STYLE,
      QUERY;

      // $FF: synthetic method
      private static Action[] $values() {
         return new Action[]{CREATE, REGISTER_PLAYER, REMOVE, UNREGISTER_PLAYER, UPDATE_PERCENTAGE, UPDATE_NAME, UPDATE_PROPERTIES, UPDATE_STYLE, QUERY};
      }
   }
}
