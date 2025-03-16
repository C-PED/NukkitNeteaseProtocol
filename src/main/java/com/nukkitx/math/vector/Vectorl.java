package com.nukkitx.math.vector;

import javax.annotation.Nonnull;

public interface Vectorl {
   @Nonnull
   Vectorl mul(long var1);

   @Nonnull
   Vectorl div(long var1);

   @Nonnull
   Vectorl pow(long var1);

   @Nonnull
   Vectorl abs();

   @Nonnull
   Vectorl negate();

   double length();

   long lengthSquared();

   int getMinAxis();

   int getMaxAxis();

   @Nonnull
   long[] toArray();

   @Nonnull
   Vectori toInt();

   @Nonnull
   Vectorl toLong();

   @Nonnull
   Vectorf toFloat();

   @Nonnull
   Vectord toDouble();
}
