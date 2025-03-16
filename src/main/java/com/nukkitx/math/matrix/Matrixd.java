package com.nukkitx.math.matrix;

import com.nukkitx.math.vector.Vectord;
import javax.annotation.Nonnull;

public interface Matrixd {
   double get(int var1, int var2);

   @Nonnull
   Vectord getRow(int var1);

   @Nonnull
   Vectord getColumn(int var1);

   @Nonnull
   Matrixd mul(double var1);

   @Nonnull
   Matrixd div(double var1);

   @Nonnull
   Matrixd pow(double var1);

   @Nonnull
   Matrixd ceil();

   @Nonnull
   Matrixd floor();

   @Nonnull
   Matrixd round();

   @Nonnull
   Matrixd abs();

   @Nonnull
   Matrixd negate();

   @Nonnull
   Matrixd transpose();

   double trace();

   double determinant();

   @Nonnull
   Matrixd invert();

   @Nonnull
   double[] toArray(boolean var1);

   @Nonnull
   Matrixf toFloat();

   @Nonnull
   Matrixd toDouble();
}
