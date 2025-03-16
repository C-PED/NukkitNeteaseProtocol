package com.nukkitx.math.imaginary;

import javax.annotation.Nonnull;

public interface Imaginaryd {
   @Nonnull
   Imaginaryd mul(double var1);

   @Nonnull
   Imaginaryd div(double var1);

   @Nonnull
   Imaginaryd conjugate();

   @Nonnull
   Imaginaryd invert();

   double length();

   double lengthSquared();

   @Nonnull
   Imaginaryd normalize();

   @Nonnull
   Imaginaryf toFloat();

   @Nonnull
   Imaginaryd toDouble();
}
