package com.nukkitx.math.vector;

import javax.annotation.Nonnull;

public interface Vectori {
   @Nonnull
   Vectori mul(int var1);

   @Nonnull
   Vectori div(int var1);

   @Nonnull
   Vectori pow(int var1);

   @Nonnull
   Vectori abs();

   @Nonnull
   Vectori negate();

   float length();

   int lengthSquared();

   int getMinAxis();

   int getMaxAxis();

   @Nonnull
   int[] toArray();

   @Nonnull
   Vectori toInt();

   @Nonnull
   Vectorl toLong();

   @Nonnull
   Vectorf toFloat();

   @Nonnull
   Vectord toDouble();
}
