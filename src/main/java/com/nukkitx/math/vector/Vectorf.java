package com.nukkitx.math.vector;

import javax.annotation.Nonnull;

public interface Vectorf {
   @Nonnull
   Vectorf mul(float var1);

   @Nonnull
   Vectorf div(float var1);

   @Nonnull
   Vectorf pow(float var1);

   @Nonnull
   Vectorf ceil();

   @Nonnull
   Vectorf floor();

   @Nonnull
   Vectorf round();

   @Nonnull
   Vectorf abs();

   @Nonnull
   Vectorf negate();

   float length();

   float lengthSquared();

   @Nonnull
   Vectorf normalize();

   int getMinAxis();

   int getMaxAxis();

   @Nonnull
   float[] toArray();

   @Nonnull
   Vectori toInt();

   @Nonnull
   Vectorl toLong();

   @Nonnull
   Vectorf toFloat();

   @Nonnull
   Vectord toDouble();
}
