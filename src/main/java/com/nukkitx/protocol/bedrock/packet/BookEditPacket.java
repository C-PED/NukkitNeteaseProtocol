package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;

public class BookEditPacket implements BedrockPacket {
   private Action action;
   private int inventorySlot;
   private int pageNumber;
   private int secondaryPageNumber;
   private String text;
   private String photoName;
   private String title;
   private String author;
   private String xuid;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.BOOK_EDIT;
   }

   public Action getAction() {
      return this.action;
   }

   public int getInventorySlot() {
      return this.inventorySlot;
   }

   public int getPageNumber() {
      return this.pageNumber;
   }

   public int getSecondaryPageNumber() {
      return this.secondaryPageNumber;
   }

   public String getText() {
      return this.text;
   }

   public String getPhotoName() {
      return this.photoName;
   }

   public String getTitle() {
      return this.title;
   }

   public String getAuthor() {
      return this.author;
   }

   public String getXuid() {
      return this.xuid;
   }

   public void setAction(Action action) {
      this.action = action;
   }

   public void setInventorySlot(int inventorySlot) {
      this.inventorySlot = inventorySlot;
   }

   public void setPageNumber(int pageNumber) {
      this.pageNumber = pageNumber;
   }

   public void setSecondaryPageNumber(int secondaryPageNumber) {
      this.secondaryPageNumber = secondaryPageNumber;
   }

   public void setText(String text) {
      this.text = text;
   }

   public void setPhotoName(String photoName) {
      this.photoName = photoName;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   public void setXuid(String xuid) {
      this.xuid = xuid;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof BookEditPacket)) {
         return false;
      } else {
         BookEditPacket other = (BookEditPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.inventorySlot != other.inventorySlot) {
            return false;
         } else if (this.pageNumber != other.pageNumber) {
            return false;
         } else if (this.secondaryPageNumber != other.secondaryPageNumber) {
            return false;
         } else {
            Object this$action = this.action;
            Object other$action = other.action;
            if (this$action == null) {
               if (other$action != null) {
                  return false;
               }
            } else if (!this$action.equals(other$action)) {
               return false;
            }

            Object this$text = this.text;
            Object other$text = other.text;
            if (this$text == null) {
               if (other$text != null) {
                  return false;
               }
            } else if (!this$text.equals(other$text)) {
               return false;
            }

            Object this$photoName = this.photoName;
            Object other$photoName = other.photoName;
            if (this$photoName == null) {
               if (other$photoName != null) {
                  return false;
               }
            } else if (!this$photoName.equals(other$photoName)) {
               return false;
            }

            Object this$title = this.title;
            Object other$title = other.title;
            if (this$title == null) {
               if (other$title != null) {
                  return false;
               }
            } else if (!this$title.equals(other$title)) {
               return false;
            }

            Object this$author = this.author;
            Object other$author = other.author;
            if (this$author == null) {
               if (other$author != null) {
                  return false;
               }
            } else if (!this$author.equals(other$author)) {
               return false;
            }

            Object this$xuid = this.xuid;
            Object other$xuid = other.xuid;
            if (this$xuid == null) {
               if (other$xuid != null) {
                  return false;
               }
            } else if (!this$xuid.equals(other$xuid)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof BookEditPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.inventorySlot;
      result = result * 59 + this.pageNumber;
      result = result * 59 + this.secondaryPageNumber;
      Object $action = this.action;
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      Object $text = this.text;
      result = result * 59 + ($text == null ? 43 : $text.hashCode());
      Object $photoName = this.photoName;
      result = result * 59 + ($photoName == null ? 43 : $photoName.hashCode());
      Object $title = this.title;
      result = result * 59 + ($title == null ? 43 : $title.hashCode());
      Object $author = this.author;
      result = result * 59 + ($author == null ? 43 : $author.hashCode());
      Object $xuid = this.xuid;
      result = result * 59 + ($xuid == null ? 43 : $xuid.hashCode());
      return result;
   }

   public String toString() {
      return "BookEditPacket(action=" + this.action + ", inventorySlot=" + this.inventorySlot + ", pageNumber=" + this.pageNumber + ", secondaryPageNumber=" + this.secondaryPageNumber + ", text=" + this.text + ", photoName=" + this.photoName + ", title=" + this.title + ", author=" + this.author + ", xuid=" + this.xuid + ")";
   }

   public static enum Action {
      REPLACE_PAGE,
      ADD_PAGE,
      DELETE_PAGE,
      SWAP_PAGES,
      SIGN_BOOK;

      // $FF: synthetic method
      private static Action[] $values() {
         return new Action[]{REPLACE_PAGE, ADD_PAGE, DELETE_PAGE, SWAP_PAGES, SIGN_BOOK};
      }
   }
}
