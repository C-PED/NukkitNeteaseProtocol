package com.nukkitx.protocol.common.util;

import java.util.Objects;
import java.util.function.BiConsumer;

@FunctionalInterface
public interface TriConsumer<T, U, R> {
   void accept(T var1, U var2, R var3);

   default TriConsumer<T, U, R> andThen(TriConsumer<? super T, ? super U, ? super R> after) {
      Objects.requireNonNull(after);
      return (l, m, r) -> {
         this.accept(l, m, r);
         after.accept(l, m, r);
      };
   }

   static <T, U, R> TriConsumer<T, U, R> from(BiConsumer<? super T, ? super R> consumer) {
      Objects.requireNonNull(consumer);
      return (l, m, r) -> consumer.accept(l, r);
   }
}
