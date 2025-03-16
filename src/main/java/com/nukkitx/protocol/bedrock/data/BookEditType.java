package com.nukkitx.protocol.bedrock.data;

public enum BookEditType {
   REPLACE_PAGE,
   ADD_PAGE,
   DELETE_PAGE,
   SWAP_PAGES,
   SIGN_BOOK;

   // $FF: synthetic method
   private static BookEditType[] $values() {
      return new BookEditType[]{REPLACE_PAGE, ADD_PAGE, DELETE_PAGE, SWAP_PAGES, SIGN_BOOK};
   }
}
