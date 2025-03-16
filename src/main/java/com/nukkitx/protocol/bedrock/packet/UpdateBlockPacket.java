package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.definitions.BlockDefinition;
import com.nukkitx.protocol.common.PacketSignal;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import org.cloudburstmc.math.vector.Vector3i;

public class UpdateBlockPacket implements BedrockPacket {
   public static final Set<Flag> FLAG_ALL;
   public static final Set<Flag> FLAG_ALL_PRIORITY;
   final Set<Flag> flags = EnumSet.noneOf(Flag.class);
   Vector3i blockPosition;
   BlockDefinition definition;
   int dataLayer;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.UPDATE_BLOCK;
   }

   public Set<Flag> getFlags() {
      return this.flags;
   }

   public Vector3i getBlockPosition() {
      return this.blockPosition;
   }

   public BlockDefinition getDefinition() {
      return this.definition;
   }

   public int getDataLayer() {
      return this.dataLayer;
   }

   public void setBlockPosition(Vector3i blockPosition) {
      this.blockPosition = blockPosition;
   }

   public void setDefinition(BlockDefinition definition) {
      this.definition = definition;
   }

   public void setDataLayer(int dataLayer) {
      this.dataLayer = dataLayer;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UpdateBlockPacket)) {
         return false;
      } else {
         UpdateBlockPacket other = (UpdateBlockPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.dataLayer != other.dataLayer) {
            return false;
         } else {
            Object this$flags = this.flags;
            Object other$flags = other.flags;
            if (this$flags == null) {
               if (other$flags != null) {
                  return false;
               }
            } else if (!this$flags.equals(other$flags)) {
               return false;
            }

            Object this$blockPosition = this.blockPosition;
            Object other$blockPosition = other.blockPosition;
            if (this$blockPosition == null) {
               if (other$blockPosition != null) {
                  return false;
               }
            } else if (!this$blockPosition.equals(other$blockPosition)) {
               return false;
            }

            Object this$definition = this.definition;
            Object other$definition = other.definition;
            if (this$definition == null) {
               if (other$definition != null) {
                  return false;
               }
            } else if (!this$definition.equals(other$definition)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof UpdateBlockPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.dataLayer;
      Object $flags = this.flags;
      result = result * 59 + ($flags == null ? 43 : $flags.hashCode());
      Object $blockPosition = this.blockPosition;
      result = result * 59 + ($blockPosition == null ? 43 : $blockPosition.hashCode());
      Object $definition = this.definition;
      result = result * 59 + ($definition == null ? 43 : $definition.hashCode());
      return result;
   }

   public String toString() {
      return "UpdateBlockPacket(flags=" + this.flags + ", blockPosition=" + this.blockPosition + ", definition=" + this.definition + ", dataLayer=" + this.dataLayer + ")";
   }

   static {
      FLAG_ALL = Collections.unmodifiableSet(EnumSet.of(Flag.NEIGHBORS, Flag.NETWORK));
      FLAG_ALL_PRIORITY = Collections.unmodifiableSet(EnumSet.of(Flag.NEIGHBORS, Flag.NETWORK, Flag.PRIORITY));
   }

   public static enum Flag {
      NEIGHBORS,
      NETWORK,
      NO_GRAPHIC,
      UNUSED,
      PRIORITY;

      // $FF: synthetic method
      private static Flag[] $values() {
         return new Flag[]{NEIGHBORS, NETWORK, NO_GRAPHIC, UNUSED, PRIORITY};
      }
   }
}
