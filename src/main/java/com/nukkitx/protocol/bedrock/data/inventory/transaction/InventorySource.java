package com.nukkitx.protocol.bedrock.data.inventory.transaction;

import com.nukkitx.protocol.common.util.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class InventorySource {
   private static final InventorySource CREATIVE_SOURCE;
   private static final InventorySource GLOBAL_SOURCE;
   private static final InventorySource INVALID_SOURCE;
   private final Type type;
   private final int containerId;
   private final Flag flag;

   public static InventorySource fromContainerWindowId(int containerId) {
      Preconditions.checkNotNull(containerId, "containerId");
      return new InventorySource(Type.CONTAINER, containerId, Flag.NONE);
   }

   public static InventorySource fromCreativeInventory() {
      return CREATIVE_SOURCE;
   }

   public static InventorySource fromGlobalInventory() {
      return GLOBAL_SOURCE;
   }

   public static InventorySource fromInvalid() {
      return INVALID_SOURCE;
   }

   public static InventorySource fromNonImplementedTodo(int containerId) {
      Preconditions.checkNotNull(containerId, "containerId");
      return new InventorySource(Type.NON_IMPLEMENTED_TODO, containerId, Flag.NONE);
   }

   public static InventorySource fromUntrackedInteractionUI(int containerId) {
      Preconditions.checkNotNull(containerId, "containerId");
      return new InventorySource(Type.UNTRACKED_INTERACTION_UI, containerId, Flag.NONE);
   }

   public static InventorySource fromWorldInteraction(@NonNull Flag flag) {
      Preconditions.checkNotNull(flag, "flag");
      return new InventorySource(Type.WORLD_INTERACTION, -1, flag);
   }

   public Type getType() {
      return this.type;
   }

   public int getContainerId() {
      return this.containerId;
   }

   public Flag getFlag() {
      return this.flag;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof InventorySource)) {
         return false;
      } else {
         InventorySource other = (InventorySource)o;
         if (this.getContainerId() != other.getContainerId()) {
            return false;
         } else {
            Object this$type = this.getType();
            Object other$type = other.getType();
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            Object this$flag = this.getFlag();
            Object other$flag = other.getFlag();
            if (this$flag == null) {
               if (other$flag != null) {
                  return false;
               }
            } else if (!this$flag.equals(other$flag)) {
               return false;
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getContainerId();
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      Object $flag = this.getFlag();
      result = result * 59 + ($flag == null ? 43 : $flag.hashCode());
      return result;
   }

   public String toString() {
      return "InventorySource(type=" + this.getType() + ", containerId=" + this.getContainerId() + ", flag=" + this.getFlag() + ")";
   }

   private InventorySource(Type type, int containerId, Flag flag) {
      this.type = type;
      this.containerId = containerId;
      this.flag = flag;
   }

   static {
      CREATIVE_SOURCE = new InventorySource(Type.CREATIVE, -1, Flag.NONE);
      GLOBAL_SOURCE = new InventorySource(Type.GLOBAL, -1, Flag.NONE);
      INVALID_SOURCE = new InventorySource(Type.INVALID, -1, Flag.NONE);
   }

   public static enum Type {
      INVALID(-1),
      CONTAINER(0),
      GLOBAL(1),
      WORLD_INTERACTION(2),
      CREATIVE(3),
      UNTRACKED_INTERACTION_UI(100),
      NON_IMPLEMENTED_TODO(99999);

      private static final Int2ObjectMap<Type> BY_ID = new Int2ObjectOpenHashMap(6);
      private final int id;

      private Type(int id) {
         this.id = id;
      }

      public static Type byId(int id) {
         Type type = (Type)BY_ID.get(id);
         return type == null ? INVALID : type;
      }

      public int id() {
         return this.id;
      }

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{INVALID, CONTAINER, GLOBAL, WORLD_INTERACTION, CREATIVE, UNTRACKED_INTERACTION_UI, NON_IMPLEMENTED_TODO};
      }

      static {
         for(Type type : values()) {
            BY_ID.put(type.id, type);
         }

      }
   }

   public static enum Flag {
      DROP_ITEM,
      PICKUP_ITEM,
      NONE;

      // $FF: synthetic method
      private static Flag[] $values() {
         return new Flag[]{DROP_ITEM, PICKUP_ITEM, NONE};
      }
   }
}
