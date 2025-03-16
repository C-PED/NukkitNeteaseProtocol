package com.nukkitx.natives.util;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import sun.misc.Unsafe;

public class UnsafeUtils {
   private static final Unsafe UNSAFE;
   private static final Throwable UNSAFE_UNAVAILABILITY_CAUSE;

   public static long objectFieldOffset(Class clazz, String field) {
      try {
         return UNSAFE.objectFieldOffset(clazz.getDeclaredField(field));
      } catch (NoSuchFieldException e) {
         throw new RuntimeException(e);
      }
   }

   public static Object getObject(Object object, long offset) {
      return UNSAFE.getObject(object, offset);
   }

   public static int getInt(Object object, long offset) {
      return UNSAFE.getInt(offset, offset);
   }

   static {
      Object maybeUnsafe = AccessController.doPrivileged(new PrivilegedAction<Object>() {
         public Object run() {
            try {
               Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
               unsafeField.setAccessible(true);
               return unsafeField.get((Object)null);
            } catch (IllegalAccessException | NoClassDefFoundError | RuntimeException | NoSuchFieldException e) {
               return e;
            }
         }
      });
      if (maybeUnsafe instanceof Throwable) {
         UNSAFE = null;
         UNSAFE_UNAVAILABILITY_CAUSE = (Throwable)maybeUnsafe;
      } else {
         UNSAFE = (Unsafe)maybeUnsafe;
         UNSAFE_UNAVAILABILITY_CAUSE = null;
      }

   }
}
