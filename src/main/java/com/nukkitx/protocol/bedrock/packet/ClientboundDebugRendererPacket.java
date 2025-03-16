package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.ClientboundDebugRendererType;
import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3f;

public class ClientboundDebugRendererPacket implements BedrockPacket {
   private ClientboundDebugRendererType debugMarkerType;
   private String markerText;
   private Vector3f markerPosition;
   private float markerColorRed;
   private float markerColorGreen;
   private float markerColorBlue;
   private float markerColorAlpha;
   private long markerDuration;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CLIENTBOUND_DEBUG_RENDERER;
   }

   public ClientboundDebugRendererType getDebugMarkerType() {
      return this.debugMarkerType;
   }

   public String getMarkerText() {
      return this.markerText;
   }

   public Vector3f getMarkerPosition() {
      return this.markerPosition;
   }

   public float getMarkerColorRed() {
      return this.markerColorRed;
   }

   public float getMarkerColorGreen() {
      return this.markerColorGreen;
   }

   public float getMarkerColorBlue() {
      return this.markerColorBlue;
   }

   public float getMarkerColorAlpha() {
      return this.markerColorAlpha;
   }

   public long getMarkerDuration() {
      return this.markerDuration;
   }

   public void setDebugMarkerType(ClientboundDebugRendererType debugMarkerType) {
      this.debugMarkerType = debugMarkerType;
   }

   public void setMarkerText(String markerText) {
      this.markerText = markerText;
   }

   public void setMarkerPosition(Vector3f markerPosition) {
      this.markerPosition = markerPosition;
   }

   public void setMarkerColorRed(float markerColorRed) {
      this.markerColorRed = markerColorRed;
   }

   public void setMarkerColorGreen(float markerColorGreen) {
      this.markerColorGreen = markerColorGreen;
   }

   public void setMarkerColorBlue(float markerColorBlue) {
      this.markerColorBlue = markerColorBlue;
   }

   public void setMarkerColorAlpha(float markerColorAlpha) {
      this.markerColorAlpha = markerColorAlpha;
   }

   public void setMarkerDuration(long markerDuration) {
      this.markerDuration = markerDuration;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ClientboundDebugRendererPacket)) {
         return false;
      } else {
         ClientboundDebugRendererPacket other = (ClientboundDebugRendererPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.markerColorRed, other.markerColorRed) != 0) {
            return false;
         } else if (Float.compare(this.markerColorGreen, other.markerColorGreen) != 0) {
            return false;
         } else if (Float.compare(this.markerColorBlue, other.markerColorBlue) != 0) {
            return false;
         } else if (Float.compare(this.markerColorAlpha, other.markerColorAlpha) != 0) {
            return false;
         } else if (this.markerDuration != other.markerDuration) {
            return false;
         } else {
            Object this$debugMarkerType = this.debugMarkerType;
            Object other$debugMarkerType = other.debugMarkerType;
            if (this$debugMarkerType == null) {
               if (other$debugMarkerType != null) {
                  return false;
               }
            } else if (!this$debugMarkerType.equals(other$debugMarkerType)) {
               return false;
            }

            Object this$markerText = this.markerText;
            Object other$markerText = other.markerText;
            if (this$markerText == null) {
               if (other$markerText != null) {
                  return false;
               }
            } else if (!this$markerText.equals(other$markerText)) {
               return false;
            }

            Object this$markerPosition = this.markerPosition;
            Object other$markerPosition = other.markerPosition;
            if (this$markerPosition == null) {
               if (other$markerPosition != null) {
                  return false;
               }
            } else if (!this$markerPosition.equals(other$markerPosition)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ClientboundDebugRendererPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.markerColorRed);
      result = result * 59 + Float.floatToIntBits(this.markerColorGreen);
      result = result * 59 + Float.floatToIntBits(this.markerColorBlue);
      result = result * 59 + Float.floatToIntBits(this.markerColorAlpha);
      long $markerDuration = this.markerDuration;
      result = result * 59 + (int)($markerDuration >>> 32 ^ $markerDuration);
      Object $debugMarkerType = this.debugMarkerType;
      result = result * 59 + ($debugMarkerType == null ? 43 : $debugMarkerType.hashCode());
      Object $markerText = this.markerText;
      result = result * 59 + ($markerText == null ? 43 : $markerText.hashCode());
      Object $markerPosition = this.markerPosition;
      result = result * 59 + ($markerPosition == null ? 43 : $markerPosition.hashCode());
      return result;
   }

   public String toString() {
      return "ClientboundDebugRendererPacket(debugMarkerType=" + this.debugMarkerType + ", markerText=" + this.markerText + ", markerPosition=" + this.markerPosition + ", markerColorRed=" + this.markerColorRed + ", markerColorGreen=" + this.markerColorGreen + ", markerColorBlue=" + this.markerColorBlue + ", markerColorAlpha=" + this.markerColorAlpha + ", markerDuration=" + this.markerDuration + ")";
   }
}
