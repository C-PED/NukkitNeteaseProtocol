package com.nukkitx.protocol.data;

public enum Ability {
   BUILD,
   MINE,
   DOORS_AND_SWITCHES,
   OPEN_CONTAINERS,
   ATTACK_PLAYERS,
   ATTACK_MOBS,
   OPERATOR_COMMANDS,
   TELEPORT,
   INVULNERABLE,
   FLYING,
   MAY_FLY,
   INSTABUILD,
   LIGHTNING,
   FLY_SPEED,
   WALK_SPEED,
   MUTED,
   WORLD_BUILDER,
   NO_CLIP,
   PRIVILEGED_BUILDER;

   // $FF: synthetic method
   private static Ability[] $values() {
      return new Ability[]{BUILD, MINE, DOORS_AND_SWITCHES, OPEN_CONTAINERS, ATTACK_PLAYERS, ATTACK_MOBS, OPERATOR_COMMANDS, TELEPORT, INVULNERABLE, FLYING, MAY_FLY, INSTABUILD, LIGHTNING, FLY_SPEED, WALK_SPEED, MUTED, WORLD_BUILDER, NO_CLIP, PRIVILEGED_BUILDER};
   }

   public static enum Type {
      NONE,
      BOOLEAN,
      FLOAT;

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{NONE, BOOLEAN, FLOAT};
      }
   }
}
