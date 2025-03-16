package com.nukkitx.protocol.bedrock.data.entity;

public final class EntityLinkData {
   private final long from;
   private final long to;
   private final Type type;
   private final boolean immediate;
   private final boolean riderInitiated;

   /** @deprecated */
   @Deprecated
   public EntityLinkData(long from, long to, Type type, boolean immediate) {
      this(from, to, type, immediate, false);
   }

   public long getFrom() {
      return this.from;
   }

   public long getTo() {
      return this.to;
   }

   public Type getType() {
      return this.type;
   }

   public boolean isImmediate() {
      return this.immediate;
   }

   public boolean isRiderInitiated() {
      return this.riderInitiated;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EntityLinkData)) {
         return false;
      } else {
         EntityLinkData other = (EntityLinkData)o;
         if (this.getFrom() != other.getFrom()) {
            return false;
         } else if (this.getTo() != other.getTo()) {
            return false;
         } else if (this.isImmediate() != other.isImmediate()) {
            return false;
         } else if (this.isRiderInitiated() != other.isRiderInitiated()) {
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

            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $from = this.getFrom();
      result = result * 59 + (int)($from >>> 32 ^ $from);
      long $to = this.getTo();
      result = result * 59 + (int)($to >>> 32 ^ $to);
      result = result * 59 + (this.isImmediate() ? 79 : 97);
      result = result * 59 + (this.isRiderInitiated() ? 79 : 97);
      Object $type = this.getType();
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      return result;
   }

   public String toString() {
      return "EntityLinkData(from=" + this.getFrom() + ", to=" + this.getTo() + ", type=" + this.getType() + ", immediate=" + this.isImmediate() + ", riderInitiated=" + this.isRiderInitiated() + ")";
   }

   public EntityLinkData(long from, long to, Type type, boolean immediate, boolean riderInitiated) {
      this.from = from;
      this.to = to;
      this.type = type;
      this.immediate = immediate;
      this.riderInitiated = riderInitiated;
   }

   public static enum Type {
      REMOVE,
      RIDER,
      PASSENGER;

      private static final Type[] VALUES = values();

      public static Type byId(int id) {
         if (id >= 0 && id < VALUES.length) {
            return VALUES[id];
         } else {
            throw new UnsupportedOperationException("Unknown EntityLinkData.Type ID: " + id);
         }
      }

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{REMOVE, RIDER, PASSENGER};
      }
   }
}
