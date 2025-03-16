package com.nukkitx.protocol.bedrock.data;

public enum BuildPlatform {
   UNDEFINED,
   GOOGLE,
   IOS,
   OSX,
   AMAZON,
   GEAR_VR,
   HOLOLENS,
   UWP,
   WIN_32,
   DEDICATED,
   TV_OS,
   SONY,
   NX,
   XBOX,
   WINDOWS_PHONE,
   LINUX;

   private static final BuildPlatform[] VALUES = values();

   public static BuildPlatform from(int id) {
      return id > 0 && id < VALUES.length ? VALUES[id] : VALUES[0];
   }

   // $FF: synthetic method
   private static BuildPlatform[] $values() {
      return new BuildPlatform[]{UNDEFINED, GOOGLE, IOS, OSX, AMAZON, GEAR_VR, HOLOLENS, UWP, WIN_32, DEDICATED, TV_OS, SONY, NX, XBOX, WINDOWS_PHONE, LINUX};
   }
}
