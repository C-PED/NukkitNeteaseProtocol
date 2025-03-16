package com.nukkitx.math.imaginary;

import javax.annotation.Nonnull;

public interface Imaginaryf {
   @Nonnull
   Imaginaryf mul(float var1);

   @Nonnull
   Imaginaryf div(float var1);

   @Nonnull
   Imaginaryf conjugate();

   @Nonnull
   Imaginaryf invert();

   float length();

   float lengthSquared();

   @Nonnull
   Imaginaryf normalize();

   @Nonnull
   Imaginaryf toFloat();

   @Nonnull
   Imaginaryd toDouble();
}
