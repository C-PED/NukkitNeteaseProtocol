package com.nukkitx.protocol.bedrock.data;

import org.cloudburstmc.math.vector.Vector3i;

public class MapTrackedObject {
   private final Type type;
   private long entityId;
   private Vector3i position;

   public MapTrackedObject(long entityId) {
      this.type = Type.ENTITY;
      this.entityId = entityId;
   }

   public MapTrackedObject(Vector3i position) {
      this.type = Type.BLOCK;
      this.position = position;
   }

   public Type getType() {
      return this.type;
   }

   public long getEntityId() {
      return this.entityId;
   }

   public Vector3i getPosition() {
      return this.position;
   }

   public String toString() {
      return "MapTrackedObject(type=" + this.getType() + ", entityId=" + this.getEntityId() + ", position=" + this.getPosition() + ")";
   }

   public static enum Type {
      ENTITY,
      BLOCK;

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{ENTITY, BLOCK};
      }
   }
}
