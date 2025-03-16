package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.annotation.Incompressible;
import com.nukkitx.protocol.bedrock.data.PacketCompressionAlgorithm;
import com.nukkitx.protocol.common.PacketSignal;

@Incompressible
public class NetworkSettingsPacket implements BedrockPacket {
   private int compressionThreshold;
   private PacketCompressionAlgorithm compressionAlgorithm;
   private boolean clientThrottleEnabled;
   private int clientThrottleThreshold;
   private float clientThrottleScalar;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.NETWORK_SETTINGS;
   }

   public int getCompressionThreshold() {
      return this.compressionThreshold;
   }

   public PacketCompressionAlgorithm getCompressionAlgorithm() {
      return this.compressionAlgorithm;
   }

   public boolean isClientThrottleEnabled() {
      return this.clientThrottleEnabled;
   }

   public int getClientThrottleThreshold() {
      return this.clientThrottleThreshold;
   }

   public float getClientThrottleScalar() {
      return this.clientThrottleScalar;
   }

   public void setCompressionThreshold(int compressionThreshold) {
      this.compressionThreshold = compressionThreshold;
   }

   public void setCompressionAlgorithm(PacketCompressionAlgorithm compressionAlgorithm) {
      this.compressionAlgorithm = compressionAlgorithm;
   }

   public void setClientThrottleEnabled(boolean clientThrottleEnabled) {
      this.clientThrottleEnabled = clientThrottleEnabled;
   }

   public void setClientThrottleThreshold(int clientThrottleThreshold) {
      this.clientThrottleThreshold = clientThrottleThreshold;
   }

   public void setClientThrottleScalar(float clientThrottleScalar) {
      this.clientThrottleScalar = clientThrottleScalar;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NetworkSettingsPacket)) {
         return false;
      } else {
         NetworkSettingsPacket other = (NetworkSettingsPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.compressionThreshold != other.compressionThreshold) {
            return false;
         } else if (this.clientThrottleEnabled != other.clientThrottleEnabled) {
            return false;
         } else if (this.clientThrottleThreshold != other.clientThrottleThreshold) {
            return false;
         } else if (Float.compare(this.clientThrottleScalar, other.clientThrottleScalar) != 0) {
            return false;
         } else {
            Object this$compressionAlgorithm = this.compressionAlgorithm;
            Object other$compressionAlgorithm = other.compressionAlgorithm;
            if (this$compressionAlgorithm == null) {
               if (other$compressionAlgorithm != null) {
                  return false;
               }
            } else if (!this$compressionAlgorithm.equals(other$compressionAlgorithm)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof NetworkSettingsPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.compressionThreshold;
      result = result * 59 + (this.clientThrottleEnabled ? 79 : 97);
      result = result * 59 + this.clientThrottleThreshold;
      result = result * 59 + Float.floatToIntBits(this.clientThrottleScalar);
      Object $compressionAlgorithm = this.compressionAlgorithm;
      result = result * 59 + ($compressionAlgorithm == null ? 43 : $compressionAlgorithm.hashCode());
      return result;
   }

   public String toString() {
      return "NetworkSettingsPacket(compressionThreshold=" + this.compressionThreshold + ", compressionAlgorithm=" + this.compressionAlgorithm + ", clientThrottleEnabled=" + this.clientThrottleEnabled + ", clientThrottleThreshold=" + this.clientThrottleThreshold + ", clientThrottleScalar=" + this.clientThrottleScalar + ")";
   }
}
