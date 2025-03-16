package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class ContainerSetDataPacket implements BedrockPacket {
   public static final int FURNACE_TICK_COUNT = 0;
   public static final int FURNACE_LIT_TIME = 1;
   public static final int FURNACE_LIT_DURATION = 2;
   public static final int FURNACE_STORED_XP = 3;
   public static final int FURNACE_FUEL_AUX = 4;
   public static final int BREWING_STAND_BREW_TIME = 0;
   public static final int BREWING_STAND_FUEL_AMOUNT = 1;
   public static final int BREWING_STAND_FUEL_TOTAL = 2;
   private byte windowId;
   private int property;
   private int value;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CONTAINER_SET_DATA;
   }

   public byte getWindowId() {
      return this.windowId;
   }

   public int getProperty() {
      return this.property;
   }

   public int getValue() {
      return this.value;
   }

   public void setWindowId(byte windowId) {
      this.windowId = windowId;
   }

   public void setProperty(int property) {
      this.property = property;
   }

   public void setValue(int value) {
      this.value = value;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ContainerSetDataPacket)) {
         return false;
      } else {
         ContainerSetDataPacket other = (ContainerSetDataPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.windowId != other.windowId) {
            return false;
         } else if (this.property != other.property) {
            return false;
         } else {
            return this.value == other.value;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ContainerSetDataPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.windowId;
      result = result * 59 + this.property;
      result = result * 59 + this.value;
      return result;
   }

   public String toString() {
      return "ContainerSetDataPacket(windowId=" + this.windowId + ", property=" + this.property + ", value=" + this.value + ")";
   }
}
