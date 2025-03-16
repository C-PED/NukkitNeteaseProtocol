package com.nukkitx.protocol.bedrock.data;

public enum HeightMapDataType {
   NO_DATA,
   HAS_DATA,
   TOO_HIGH,
   TOO_LOW;

   // $FF: synthetic method
   private static HeightMapDataType[] $values() {
      return new HeightMapDataType[]{NO_DATA, HAS_DATA, TOO_HIGH, TOO_LOW};
   }
}
