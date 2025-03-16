package com.nukkitx.protocol.common.util;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import java.util.NoSuchElementException;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public final class OptionalBoolean {
   private static final OptionalBoolean EMPTY = new OptionalBoolean();
   private final boolean isPresent;
   private final boolean value;

   private OptionalBoolean() {
      this.isPresent = false;
      this.value = false;
   }

   public static OptionalBoolean empty() {
      return EMPTY;
   }

   private OptionalBoolean(boolean value) {
      this.isPresent = true;
      this.value = value;
   }

   public static OptionalBoolean of(boolean value) {
      return new OptionalBoolean(value);
   }

   public boolean getAsBoolean() {
      if (!this.isPresent) {
         throw new NoSuchElementException("No value present");
      } else {
         return this.value;
      }
   }

   public boolean isPresent() {
      return this.isPresent;
   }

   public void ifPresent(BooleanConsumer consumer) {
      if (this.isPresent) {
         consumer.accept(this.value);
      }

   }

   public boolean orElse(boolean other) {
      return this.isPresent ? this.value : other;
   }

   public boolean orElseGet(BooleanSupplier other) {
      return this.isPresent ? this.value : other.getAsBoolean();
   }

   public <X extends Throwable> boolean orElseThrow(Supplier<X> exceptionSupplier) throws Throwable {
      if (this.isPresent) {
         return this.value;
      } else {
         throw exceptionSupplier.get();
      }
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof OptionalBoolean)) {
         return false;
      } else {
         OptionalBoolean other = (OptionalBoolean)obj;
         return this.isPresent && other.isPresent ? Boolean.compare(this.value, other.value) == 0 : this.isPresent == other.isPresent;
      }
   }

   public int hashCode() {
      return this.isPresent ? Boolean.hashCode(this.value) : 0;
   }

   public String toString() {
      return this.isPresent ? String.format("OptionalBoolean[%s]", this.value) : "OptionalBoolean.empty";
   }
}
