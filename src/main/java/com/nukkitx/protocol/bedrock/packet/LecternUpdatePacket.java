package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.math.vector.Vector3i;

public class LecternUpdatePacket implements BedrockPacket {
   private int page;
   private int totalPages;
   private Vector3i blockPosition;
   /** @deprecated */
   @Deprecated
   private boolean droppingBook;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.LECTERN_UPDATE;
   }

   public int getPage() {
      return this.page;
   }

   public int getTotalPages() {
      return this.totalPages;
   }

   public Vector3i getBlockPosition() {
      return this.blockPosition;
   }

   /** @deprecated */
   @Deprecated
   public boolean isDroppingBook() {
      return this.droppingBook;
   }

   public void setPage(int page) {
      this.page = page;
   }

   public void setTotalPages(int totalPages) {
      this.totalPages = totalPages;
   }

   public void setBlockPosition(Vector3i blockPosition) {
      this.blockPosition = blockPosition;
   }

   /** @deprecated */
   @Deprecated
   public void setDroppingBook(boolean droppingBook) {
      this.droppingBook = droppingBook;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof LecternUpdatePacket)) {
         return false;
      } else {
         LecternUpdatePacket other = (LecternUpdatePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.page != other.page) {
            return false;
         } else if (this.totalPages != other.totalPages) {
            return false;
         } else if (this.droppingBook != other.droppingBook) {
            return false;
         } else {
            Object this$blockPosition = this.blockPosition;
            Object other$blockPosition = other.blockPosition;
            if (this$blockPosition == null) {
               if (other$blockPosition != null) {
                  return false;
               }
            } else if (!this$blockPosition.equals(other$blockPosition)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof LecternUpdatePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.page;
      result = result * 59 + this.totalPages;
      result = result * 59 + (this.droppingBook ? 79 : 97);
      Object $blockPosition = this.blockPosition;
      result = result * 59 + ($blockPosition == null ? 43 : $blockPosition.hashCode());
      return result;
   }

   public String toString() {
      return "LecternUpdatePacket(page=" + this.page + ", totalPages=" + this.totalPages + ", blockPosition=" + this.blockPosition + ", droppingBook=" + this.droppingBook + ")";
   }
}
