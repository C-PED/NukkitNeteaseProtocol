package com.nukkitx.protocol.bedrock.data.entity;

import com.nukkitx.protocol.common.util.Preconditions;
import com.nukkitx.protocol.common.util.TypeMap;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.EnumSet;
import java.util.Set;
import javax.annotation.Nonnull;

public class EntityFlags {
   private static final InternalLogger log = InternalLoggerFactory.getInstance(EntityFlags.class);
   private final Set<EntityFlag> flags = EnumSet.noneOf(EntityFlag.class);

   public boolean setFlag(@Nonnull EntityFlag flag, boolean value) {
      Preconditions.checkNotNull(flag, "flag");
      boolean oldValue = this.flags.contains(flag);
      if (oldValue != value) {
         if (value) {
            this.flags.add(flag);
         } else {
            this.flags.remove(flag);
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean getFlag(@Nonnull EntityFlag flag) {
      Preconditions.checkNotNull(flag, "flag");
      return this.flags.contains(flag);
   }

   public long get(int index, TypeMap<EntityFlag> flagTypeMap) {
      long value = 0L;
      int lower = index * 64;
      int upper = lower + 64;

      for(EntityFlag flag : this.flags) {
         int flagIndex = flagTypeMap.getId(flag);
         if (flagIndex >= lower && flagIndex < upper) {
            value |= 1L << (flagIndex & 63);
         }
      }

      return value;
   }

   public void set(long value, int index, TypeMap<EntityFlag> flagTypeMap) {
      int lower = index * 64;
      int upper = lower + 64;

      for(int i = lower; i < upper; ++i) {
         int idx = i & 63;
         if ((value & 1L << idx) != 0L) {
            EntityFlag flag = flagTypeMap.getType(i);
            if (flag != null) {
               this.flags.add(flag);
            } else {
               log.debug("Unknown entity flag index {} detected", i);
            }
         }
      }

   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EntityFlags)) {
         return false;
      } else {
         EntityFlags that = (EntityFlags)o;
         return this.flags.equals(that.flags);
      }
   }

   public int hashCode() {
      return this.flags.hashCode();
   }

   public void merge(EntityFlags flags) {
      this.flags.addAll(flags.flags);
   }

   public EntityFlags copy() {
      EntityFlags flags = new EntityFlags();
      flags.flags.addAll(this.flags);
      return flags;
   }

   public String toString() {
      return "EntityFlags(flags=" + this.flags + ")";
   }
}
