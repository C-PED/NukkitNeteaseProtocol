package com.nukkitx.math.matrix;

import com.nukkitx.math.vector.Vectorf;
import javax.annotation.Nonnull;

public interface Matrixf {
   float get(int var1, int var2);

   @Nonnull
   Vectorf getRow(int var1);

   @Nonnull
   Vectorf getColumn(int var1);

   @Nonnull
   Matrixf mul(float var1);

   @Nonnull
   Matrixf div(float var1);

   @Nonnull
   Matrixf pow(float var1);

   @Nonnull
   Matrixf ceil();

   @Nonnull
   Matrixf floor();

   @Nonnull
   Matrixf round();

   @Nonnull
   Matrixf abs();

   @Nonnull
   Matrixf negate();

   @Nonnull
   Matrixf transpose();

   float trace();

   float determinant();

   @Nonnull
   Matrixf invert();

   @Nonnull
   float[] toArray(boolean var1);

   @Nonnull
   Matrixf toFloat();

   @Nonnull
   Matrixd toDouble();
}
