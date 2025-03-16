package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.common.PacketSignal;
import java.util.Arrays;

public class CreativeContentPacket implements BedrockPacket {
   private ItemData[] contents;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.CREATIVE_CONTENT;
   }

   public ItemData[] getContents() {
      return this.contents;
   }

   public void setContents(ItemData[] contents) {
      this.contents = contents;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CreativeContentPacket)) {
         return false;
      } else {
         CreativeContentPacket other = (CreativeContentPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            return Arrays.deepEquals(this.contents, other.contents);
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CreativeContentPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + Arrays.deepHashCode(this.contents);
      return result;
   }

   public String toString() {
      return "CreativeContentPacket(contents=" + Arrays.deepToString(this.contents) + ")";
   }
}
