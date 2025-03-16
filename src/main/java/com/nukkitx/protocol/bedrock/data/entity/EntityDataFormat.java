package com.nukkitx.protocol.bedrock.data.entity;

public enum EntityDataFormat {
   BYTE,
   SHORT,
   INT,
   FLOAT,
   STRING,
   NBT,
   VECTOR3I,
   LONG,
   VECTOR3F;

   // $FF: synthetic method
   private static EntityDataFormat[] $values() {
      return new EntityDataFormat[]{BYTE, SHORT, INT, FLOAT, STRING, NBT, VECTOR3I, LONG, VECTOR3F};
   }
}
