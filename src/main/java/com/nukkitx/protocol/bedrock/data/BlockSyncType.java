package com.nukkitx.protocol.bedrock.data;

public enum BlockSyncType {
   NONE,
   CREATE,
   DESTROY;

   // $FF: synthetic method
   private static BlockSyncType[] $values() {
      return new BlockSyncType[]{NONE, CREATE, DESTROY};
   }
}
