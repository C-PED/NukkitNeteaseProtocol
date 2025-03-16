package com.nukkitx.protocol.bedrock.data;

public enum InputMode {
   UNDEFINED,
   MOUSE,
   TOUCH,
   GAMEPAD,
   MOTION_CONTROLLER;

   private static final InputMode[] VALUES = values();

   public static InputMode from(int id) {
      return VALUES[id];
   }

   // $FF: synthetic method
   private static InputMode[] $values() {
      return new InputMode[]{UNDEFINED, MOUSE, TOUCH, GAMEPAD, MOTION_CONTROLLER};
   }
}
