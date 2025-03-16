package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.MapDecoration;
import com.nukkitx.protocol.bedrock.data.MapTrackedObject;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Arrays;
import java.util.List;
import org.cloudburstmc.math.vector.Vector3i;

public class ClientboundMapItemDataPacket implements BedrockPacket {
   private final LongList trackedEntityIds = new LongArrayList();
   private final List<MapTrackedObject> trackedObjects = new ObjectArrayList();
   private final List<MapDecoration> decorations = new ObjectArrayList();
   private long uniqueMapId;
   private int dimensionId;
   private boolean locked;
   private Vector3i origin;
   private int scale;
   private int height;
   private int width;
   private int xOffset;
   private int yOffset;
   private int[] colors;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CLIENTBOUND_MAP_ITEM_DATA;
   }

   public LongList getTrackedEntityIds() {
      return this.trackedEntityIds;
   }

   public List<MapTrackedObject> getTrackedObjects() {
      return this.trackedObjects;
   }

   public List<MapDecoration> getDecorations() {
      return this.decorations;
   }

   public long getUniqueMapId() {
      return this.uniqueMapId;
   }

   public int getDimensionId() {
      return this.dimensionId;
   }

   public boolean isLocked() {
      return this.locked;
   }

   public Vector3i getOrigin() {
      return this.origin;
   }

   public int getScale() {
      return this.scale;
   }

   public int getHeight() {
      return this.height;
   }

   public int getWidth() {
      return this.width;
   }

   public int getXOffset() {
      return this.xOffset;
   }

   public int getYOffset() {
      return this.yOffset;
   }

   public int[] getColors() {
      return this.colors;
   }

   public void setUniqueMapId(long uniqueMapId) {
      this.uniqueMapId = uniqueMapId;
   }

   public void setDimensionId(int dimensionId) {
      this.dimensionId = dimensionId;
   }

   public void setLocked(boolean locked) {
      this.locked = locked;
   }

   public void setOrigin(Vector3i origin) {
      this.origin = origin;
   }

   public void setScale(int scale) {
      this.scale = scale;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public void setXOffset(int xOffset) {
      this.xOffset = xOffset;
   }

   public void setYOffset(int yOffset) {
      this.yOffset = yOffset;
   }

   public void setColors(int[] colors) {
      this.colors = colors;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ClientboundMapItemDataPacket)) {
         return false;
      } else {
         ClientboundMapItemDataPacket other = (ClientboundMapItemDataPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.uniqueMapId != other.uniqueMapId) {
            return false;
         } else if (this.dimensionId != other.dimensionId) {
            return false;
         } else if (this.locked != other.locked) {
            return false;
         } else if (this.scale != other.scale) {
            return false;
         } else if (this.height != other.height) {
            return false;
         } else if (this.width != other.width) {
            return false;
         } else if (this.xOffset != other.xOffset) {
            return false;
         } else if (this.yOffset != other.yOffset) {
            return false;
         } else {
            Object this$trackedEntityIds = this.trackedEntityIds;
            Object other$trackedEntityIds = other.trackedEntityIds;
            if (this$trackedEntityIds == null) {
               if (other$trackedEntityIds != null) {
                  return false;
               }
            } else if (!this$trackedEntityIds.equals(other$trackedEntityIds)) {
               return false;
            }

            Object this$trackedObjects = this.trackedObjects;
            Object other$trackedObjects = other.trackedObjects;
            if (this$trackedObjects == null) {
               if (other$trackedObjects != null) {
                  return false;
               }
            } else if (!this$trackedObjects.equals(other$trackedObjects)) {
               return false;
            }

            Object this$decorations = this.decorations;
            Object other$decorations = other.decorations;
            if (this$decorations == null) {
               if (other$decorations != null) {
                  return false;
               }
            } else if (!this$decorations.equals(other$decorations)) {
               return false;
            }

            Object this$origin = this.origin;
            Object other$origin = other.origin;
            if (this$origin == null) {
               if (other$origin != null) {
                  return false;
               }
            } else if (!this$origin.equals(other$origin)) {
               return false;
            }

            if (!Arrays.equals(this.colors, other.colors)) {
               return false;
            } else {
               return true;
            }
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ClientboundMapItemDataPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $uniqueMapId = this.uniqueMapId;
      result = result * 59 + (int)($uniqueMapId >>> 32 ^ $uniqueMapId);
      result = result * 59 + this.dimensionId;
      result = result * 59 + (this.locked ? 79 : 97);
      result = result * 59 + this.scale;
      result = result * 59 + this.height;
      result = result * 59 + this.width;
      result = result * 59 + this.xOffset;
      result = result * 59 + this.yOffset;
      Object $trackedEntityIds = this.trackedEntityIds;
      result = result * 59 + ($trackedEntityIds == null ? 43 : $trackedEntityIds.hashCode());
      Object $trackedObjects = this.trackedObjects;
      result = result * 59 + ($trackedObjects == null ? 43 : $trackedObjects.hashCode());
      Object $decorations = this.decorations;
      result = result * 59 + ($decorations == null ? 43 : $decorations.hashCode());
      Object $origin = this.origin;
      result = result * 59 + ($origin == null ? 43 : $origin.hashCode());
      result = result * 59 + Arrays.hashCode(this.colors);
      return result;
   }

   public String toString() {
      return "ClientboundMapItemDataPacket(trackedEntityIds=" + this.trackedEntityIds + ", trackedObjects=" + this.trackedObjects + ", decorations=" + this.decorations + ", uniqueMapId=" + this.uniqueMapId + ", dimensionId=" + this.dimensionId + ", locked=" + this.locked + ", origin=" + this.origin + ", scale=" + this.scale + ", height=" + this.height + ", width=" + this.width + ", xOffset=" + this.xOffset + ", yOffset=" + this.yOffset + ", colors=" + Arrays.toString(this.colors) + ")";
   }
}
