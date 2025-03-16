package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.definitions.FeatureDefinition;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class FeatureRegistryPacket implements BedrockPacket {
   private final List<FeatureDefinition> features = new ObjectArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.FEATURE_REGISTRY;
   }

   public List<FeatureDefinition> getFeatures() {
      return this.features;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FeatureRegistryPacket)) {
         return false;
      } else {
         FeatureRegistryPacket other = (FeatureRegistryPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$features = this.features;
            Object other$features = other.features;
            if (this$features == null) {
               if (other$features != null) {
                  return false;
               }
            } else if (!this$features.equals(other$features)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof FeatureRegistryPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $features = this.features;
      result = result * 59 + ($features == null ? 43 : $features.hashCode());
      return result;
   }

   public String toString() {
      return "FeatureRegistryPacket(features=" + this.features + ")";
   }
}
