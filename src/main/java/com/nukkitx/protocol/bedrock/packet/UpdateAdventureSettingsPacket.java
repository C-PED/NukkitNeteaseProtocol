package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class UpdateAdventureSettingsPacket implements BedrockPacket {
   private boolean noPvM;
   private boolean noMvP;
   private boolean immutableWorld;
   private boolean showNameTags;
   private boolean autoJump;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.UPDATE_ADVENTURE_SETTINGS;
   }

   public boolean isNoPvM() {
      return this.noPvM;
   }

   public boolean isNoMvP() {
      return this.noMvP;
   }

   public boolean isImmutableWorld() {
      return this.immutableWorld;
   }

   public boolean isShowNameTags() {
      return this.showNameTags;
   }

   public boolean isAutoJump() {
      return this.autoJump;
   }

   public void setNoPvM(boolean noPvM) {
      this.noPvM = noPvM;
   }

   public void setNoMvP(boolean noMvP) {
      this.noMvP = noMvP;
   }

   public void setImmutableWorld(boolean immutableWorld) {
      this.immutableWorld = immutableWorld;
   }

   public void setShowNameTags(boolean showNameTags) {
      this.showNameTags = showNameTags;
   }

   public void setAutoJump(boolean autoJump) {
      this.autoJump = autoJump;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UpdateAdventureSettingsPacket)) {
         return false;
      } else {
         UpdateAdventureSettingsPacket other = (UpdateAdventureSettingsPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.noPvM != other.noPvM) {
            return false;
         } else if (this.noMvP != other.noMvP) {
            return false;
         } else if (this.immutableWorld != other.immutableWorld) {
            return false;
         } else if (this.showNameTags != other.showNameTags) {
            return false;
         } else {
            return this.autoJump == other.autoJump;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof UpdateAdventureSettingsPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.noPvM ? 79 : 97);
      result = result * 59 + (this.noMvP ? 79 : 97);
      result = result * 59 + (this.immutableWorld ? 79 : 97);
      result = result * 59 + (this.showNameTags ? 79 : 97);
      result = result * 59 + (this.autoJump ? 79 : 97);
      return result;
   }

   public String toString() {
      return "UpdateAdventureSettingsPacket(noPvM=" + this.noPvM + ", noMvP=" + this.noMvP + ", immutableWorld=" + this.immutableWorld + ", showNameTags=" + this.showNameTags + ", autoJump=" + this.autoJump + ")";
   }
}
