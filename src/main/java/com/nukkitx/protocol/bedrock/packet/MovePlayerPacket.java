package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import org.cloudburstmc.math.vector.Vector3f;

public class MovePlayerPacket implements BedrockPacket {
   private long runtimeEntityId;
   private Vector3f position;
   private Vector3f rotation;
   private Mode mode;
   private boolean onGround;
   private long ridingRuntimeEntityId;
   private TeleportationCause teleportationCause;
   private int entityType;
   private long tick;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.MOVE_PLAYER;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public Vector3f getPosition() {
      return this.position;
   }

   public Vector3f getRotation() {
      return this.rotation;
   }

   public Mode getMode() {
      return this.mode;
   }

   public boolean isOnGround() {
      return this.onGround;
   }

   public long getRidingRuntimeEntityId() {
      return this.ridingRuntimeEntityId;
   }

   public TeleportationCause getTeleportationCause() {
      return this.teleportationCause;
   }

   public int getEntityType() {
      return this.entityType;
   }

   public long getTick() {
      return this.tick;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setPosition(Vector3f position) {
      this.position = position;
   }

   public void setRotation(Vector3f rotation) {
      this.rotation = rotation;
   }

   public void setMode(Mode mode) {
      this.mode = mode;
   }

   public void setOnGround(boolean onGround) {
      this.onGround = onGround;
   }

   public void setRidingRuntimeEntityId(long ridingRuntimeEntityId) {
      this.ridingRuntimeEntityId = ridingRuntimeEntityId;
   }

   public void setTeleportationCause(TeleportationCause teleportationCause) {
      this.teleportationCause = teleportationCause;
   }

   public void setEntityType(int entityType) {
      this.entityType = entityType;
   }

   public void setTick(long tick) {
      this.tick = tick;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MovePlayerPacket)) {
         return false;
      } else {
         MovePlayerPacket other = (MovePlayerPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else if (this.onGround != other.onGround) {
            return false;
         } else if (this.ridingRuntimeEntityId != other.ridingRuntimeEntityId) {
            return false;
         } else if (this.entityType != other.entityType) {
            return false;
         } else if (this.tick != other.tick) {
            return false;
         } else {
            Object this$position = this.position;
            Object other$position = other.position;
            if (this$position == null) {
               if (other$position != null) {
                  return false;
               }
            } else if (!this$position.equals(other$position)) {
               return false;
            }

            Object this$rotation = this.rotation;
            Object other$rotation = other.rotation;
            if (this$rotation == null) {
               if (other$rotation != null) {
                  return false;
               }
            } else if (!this$rotation.equals(other$rotation)) {
               return false;
            }

            Object this$mode = this.mode;
            Object other$mode = other.mode;
            if (this$mode == null) {
               if (other$mode != null) {
                  return false;
               }
            } else if (!this$mode.equals(other$mode)) {
               return false;
            }

            Object this$teleportationCause = this.teleportationCause;
            Object other$teleportationCause = other.teleportationCause;
            if (this$teleportationCause == null) {
               if (other$teleportationCause != null) {
                  return false;
               }
            } else if (!this$teleportationCause.equals(other$teleportationCause)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof MovePlayerPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      result = result * 59 + (this.onGround ? 79 : 97);
      long $ridingRuntimeEntityId = this.ridingRuntimeEntityId;
      result = result * 59 + (int)($ridingRuntimeEntityId >>> 32 ^ $ridingRuntimeEntityId);
      result = result * 59 + this.entityType;
      long $tick = this.tick;
      result = result * 59 + (int)($tick >>> 32 ^ $tick);
      Object $position = this.position;
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      Object $rotation = this.rotation;
      result = result * 59 + ($rotation == null ? 43 : $rotation.hashCode());
      Object $mode = this.mode;
      result = result * 59 + ($mode == null ? 43 : $mode.hashCode());
      Object $teleportationCause = this.teleportationCause;
      result = result * 59 + ($teleportationCause == null ? 43 : $teleportationCause.hashCode());
      return result;
   }

   public String toString() {
      return "MovePlayerPacket(runtimeEntityId=" + this.runtimeEntityId + ", position=" + this.position + ", rotation=" + this.rotation + ", mode=" + this.mode + ", onGround=" + this.onGround + ", ridingRuntimeEntityId=" + this.ridingRuntimeEntityId + ", teleportationCause=" + this.teleportationCause + ", entityType=" + this.entityType + ", tick=" + this.tick + ")";
   }

   public static enum Mode {
      NORMAL,
      RESPAWN,
      TELEPORT,
      HEAD_ROTATION;

      // $FF: synthetic method
      private static Mode[] $values() {
         return new Mode[]{NORMAL, RESPAWN, TELEPORT, HEAD_ROTATION};
      }
   }

   public static enum TeleportationCause {
      UNKNOWN,
      PROJECTILE,
      CHORUS_FRUIT,
      COMMAND,
      BEHAVIOR;

      private static final InternalLogger log = InternalLoggerFactory.getInstance(TeleportationCause.class);
      private static final TeleportationCause[] VALUES = values();

      public static TeleportationCause byId(int id) {
         if (id >= 0 && id < VALUES.length) {
            return VALUES[id];
         } else {
            log.debug("Unknown teleportation cause ID: {}", id);
            return UNKNOWN;
         }
      }

      // $FF: synthetic method
      private static TeleportationCause[] $values() {
         return new TeleportationCause[]{UNKNOWN, PROJECTILE, CHORUS_FRUIT, COMMAND, BEHAVIOR};
      }
   }
}
