package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class VideoStreamConnectPacket implements BedrockPacket {
   private String address;
   private float screenshotFrequency;
   private Action action;
   private int width;
   private int height;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.VIDEO_STREAM_CONNECT;
   }

   public String getAddress() {
      return this.address;
   }

   public float getScreenshotFrequency() {
      return this.screenshotFrequency;
   }

   public Action getAction() {
      return this.action;
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public void setScreenshotFrequency(float screenshotFrequency) {
      this.screenshotFrequency = screenshotFrequency;
   }

   public void setAction(Action action) {
      this.action = action;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof VideoStreamConnectPacket)) {
         return false;
      } else {
         VideoStreamConnectPacket other = (VideoStreamConnectPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.screenshotFrequency, other.screenshotFrequency) != 0) {
            return false;
         } else if (this.width != other.width) {
            return false;
         } else if (this.height != other.height) {
            return false;
         } else {
            Object this$address = this.address;
            Object other$address = other.address;
            if (this$address == null) {
               if (other$address != null) {
                  return false;
               }
            } else if (!this$address.equals(other$address)) {
               return false;
            }

            Object this$action = this.action;
            Object other$action = other.action;
            if (this$action == null) {
               if (other$action != null) {
                  return false;
               }
            } else if (!this$action.equals(other$action)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof VideoStreamConnectPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.screenshotFrequency);
      result = result * 59 + this.width;
      result = result * 59 + this.height;
      Object $address = this.address;
      result = result * 59 + ($address == null ? 43 : $address.hashCode());
      Object $action = this.action;
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      return result;
   }

   public String toString() {
      return "VideoStreamConnectPacket(address=" + this.address + ", screenshotFrequency=" + this.screenshotFrequency + ", action=" + this.action + ", width=" + this.width + ", height=" + this.height + ")";
   }

   public static enum Action {
      OPEN,
      CLOSE;

      // $FF: synthetic method
      private static Action[] $values() {
         return new Action[]{OPEN, CLOSE};
      }
   }
}
