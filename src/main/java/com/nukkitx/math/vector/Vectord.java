package com.nukkitx.math.vector;

import javax.annotation.Nonnull;

public interface Vectord {
   @Nonnull
   Vectord mul(double var1);

   @Nonnull
   Vectord div(double var1);

   @Nonnull
   Vectord pow(double var1);

   @Nonnull
   Vectord ceil();

   @Nonnull
   Vectord floor();

   @Nonnull
   Vectord round();

   @Nonnull
   Vectord abs();

   @Nonnull
   Vectord negate();

   double length();

   double lengthSquared();

   @Nonnull
   Vectord normalize();

   int getMinAxis();

   int getMaxAxis();

   @Nonnull
   double[] toArray();

   @Nonnull
   Vectori toInt();

   @Nonnull
   Vectorl toLong();

   @Nonnull
   Vectorf toFloat();

   @Nonnull
   Vectord toDouble();
}
