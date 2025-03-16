package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.CraftingType;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.UUID;

/** @deprecated */
@Deprecated
public class CraftingEventPacket implements BedrockPacket {
   private final List<ItemData> inputs = new ObjectArrayList();
   private final List<ItemData> outputs = new ObjectArrayList();
   private byte containerId;
   private CraftingType type;
   private UUID uuid;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CRAFTING_EVENT;
   }

   public List<ItemData> getInputs() {
      return this.inputs;
   }

   public List<ItemData> getOutputs() {
      return this.outputs;
   }

   public byte getContainerId() {
      return this.containerId;
   }

   public CraftingType getType() {
      return this.type;
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public void setContainerId(byte containerId) {
      this.containerId = containerId;
   }

   public void setType(CraftingType type) {
      this.type = type;
   }

   public void setUuid(UUID uuid) {
      this.uuid = uuid;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CraftingEventPacket)) {
         return false;
      } else {
         CraftingEventPacket other = (CraftingEventPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.containerId != other.containerId) {
            return false;
         } else {
            Object this$inputs = this.inputs;
            Object other$inputs = other.inputs;
            if (this$inputs == null) {
               if (other$inputs != null) {
                  return false;
               }
            } else if (!this$inputs.equals(other$inputs)) {
               return false;
            }

            Object this$outputs = this.outputs;
            Object other$outputs = other.outputs;
            if (this$outputs == null) {
               if (other$outputs != null) {
                  return false;
               }
            } else if (!this$outputs.equals(other$outputs)) {
               return false;
            }

            Object this$type = this.type;
            Object other$type = other.type;
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$uuid = this.uuid;
            Object other$uuid = other.uuid;
            if (this$uuid == null) {
               if (other$uuid != null) {
                  return false;
               }
            } else if (!this$uuid.equals(other$uuid)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CraftingEventPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.containerId;
      Object $inputs = this.inputs;
      result = result * 59 + ($inputs == null ? 43 : $inputs.hashCode());
      Object $outputs = this.outputs;
      result = result * 59 + ($outputs == null ? 43 : $outputs.hashCode());
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $uuid = this.uuid;
      result = result * 59 + ($uuid == null ? 43 : $uuid.hashCode());
      return result;
   }

   public String toString() {
      return "CraftingEventPacket(inputs=" + this.inputs + ", outputs=" + this.outputs + ", containerId=" + this.containerId + ", type=" + this.type + ", uuid=" + this.uuid + ")";
   }
}
