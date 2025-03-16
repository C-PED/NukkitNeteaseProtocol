package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.camera.CameraPreset;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;

public class CameraPresetsPacket implements BedrockPacket {
   private final List<CameraPreset> presets = new ObjectArrayList();

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CAMERA_PRESETS;
   }

   public List<CameraPreset> getPresets() {
      return this.presets;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CameraPresetsPacket)) {
         return false;
      } else {
         CameraPresetsPacket other = (CameraPresetsPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$presets = this.presets;
            Object other$presets = other.presets;
            if (this$presets == null) {
               if (other$presets != null) {
                  return false;
               }
            } else if (!this$presets.equals(other$presets)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CameraPresetsPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $presets = this.presets;
      result = result * 59 + ($presets == null ? 43 : $presets.hashCode());
      return result;
   }

   public String toString() {
      return "CameraPresetsPacket(presets=" + this.presets + ")";
   }
}
