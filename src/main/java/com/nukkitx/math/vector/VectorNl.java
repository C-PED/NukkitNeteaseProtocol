package com.nukkitx.math.vector;

import com.nukkitx.math.GenericMath;
import java.io.Serializable;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class VectorNl implements Vectorl, Comparable<VectorNl>, Serializable, Cloneable {
   public static VectorNl ZERO_2 = new ImmutableZeroVectorN(new long[]{0L, 0L});
   public static VectorNl ZERO_3 = new ImmutableZeroVectorN(new long[]{0L, 0L, 0L});
   public static VectorNl ZERO_4 = new ImmutableZeroVectorN(new long[]{0L, 0L, 0L, 0L});
   private static final long serialVersionUID = 1L;
   private final long[] vec;

   private VectorNl(long[] v) {
      this.vec = v.clone();
   }

   public int size() {
      return this.vec.length;
   }

   public long get(int comp) {
      return this.vec[comp];
   }

   public void set(int comp, long val) {
      this.vec[comp] = val;
   }

   public void setZero() {
      Arrays.fill(this.vec, 0L);
   }

   @Nonnull
   public VectorNl resize(int size) {
      VectorNl d = from(size);
      System.arraycopy(this.vec, 0, d.vec, 0, Math.min(size, this.size()));
      return d;
   }

   @Nonnull
   public VectorNl add(VectorNl v) {
      return this.add(v.vec);
   }

   @Nonnull
   public VectorNl add(long... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNl d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] + v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNl sub(VectorNl v) {
      return this.sub(v.vec);
   }

   @Nonnull
   public VectorNl sub(long... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNl d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] - v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNl mul(double a) {
      return this.mul(GenericMath.floor64(a));
   }

   @Nonnull
   public VectorNl mul(long a) {
      int size = this.size();
      VectorNl d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = this.vec[comp] * a;
      }

      return d;
   }

   @Nonnull
   public VectorNl mul(VectorNl v) {
      return this.mul(v.vec);
   }

   @Nonnull
   public VectorNl mul(long... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNl d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] * v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNl div(double a) {
      return this.div(GenericMath.floor64(a));
   }

   @Nonnull
   public VectorNl div(long a) {
      int size = this.size();
      VectorNl d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = this.vec[comp] / a;
      }

      return d;
   }

   @Nonnull
   public VectorNl div(VectorNl v) {
      return this.div(v.vec);
   }

   @Nonnull
   public VectorNl div(long... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNl d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] / v[comp];
         }

         return d;
      }
   }

   public long dot(VectorNl v) {
      return this.dot(v.vec);
   }

   public long dot(long... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         long d = 0L;

         for(int comp = 0; comp < size; ++comp) {
            d += this.vec[comp] * v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNl project(VectorNl v) {
      return this.project(v.vec);
   }

   @Nonnull
   public VectorNl project(long... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         long lengthSquared = 0L;

         for(int comp = 0; comp < size; ++comp) {
            lengthSquared += v[comp] * v[comp];
         }

         if (lengthSquared == 0L) {
            throw new ArithmeticException("Cannot project onto the zero vector");
         } else {
            double a = (double)this.dot(v) / (double)lengthSquared;
            VectorNl d = from(size);

            for(int comp = 0; comp < size; ++comp) {
               d.vec[comp] = GenericMath.floor64(a * (double)v[comp]);
            }

            return d;
         }
      }
   }

   @Nonnull
   public VectorNl pow(double pow) {
      return this.pow(GenericMath.floor64(pow));
   }

   @Nonnull
   public VectorNl pow(long power) {
      int size = this.size();
      VectorNl d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = GenericMath.floor64(Math.pow((double)this.vec[comp], (double)power));
      }

      return d;
   }

   @Nonnull
   public VectorNl abs() {
      int size = this.size();
      VectorNl d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = Math.abs(this.vec[comp]);
      }

      return d;
   }

   @Nonnull
   public VectorNl negate() {
      int size = this.size();
      VectorNl d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = -this.vec[comp];
      }

      return d;
   }

   @Nonnull
   public VectorNl min(VectorNl v) {
      return this.min(v.vec);
   }

   @Nonnull
   public VectorNl min(long... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNl d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = Math.min(this.vec[comp], v[comp]);
         }

         return d;
      }
   }

   @Nonnull
   public VectorNl max(VectorNl v) {
      return this.max(v.vec);
   }

   @Nonnull
   public VectorNl max(long... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNl d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = Math.max(this.vec[comp], v[comp]);
         }

         return d;
      }
   }

   public long distanceSquared(VectorNl v) {
      return this.distanceSquared(v.vec);
   }

   public long distanceSquared(long... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         long d = 0L;

         for(int comp = 0; comp < size; ++comp) {
            long delta = this.vec[comp] - v[comp];
            d += delta * delta;
         }

         return d;
      }
   }

   public double distance(VectorNl v) {
      return this.distance(v.vec);
   }

   public double distance(long... v) {
      return Math.sqrt((double)this.distanceSquared(v));
   }

   public long lengthSquared() {
      int size = this.size();
      long l = 0L;

      for(int comp = 0; comp < size; ++comp) {
         l += this.vec[comp] * this.vec[comp];
      }

      return l;
   }

   public double length() {
      return Math.sqrt((double)this.lengthSquared());
   }

   public int getMinAxis() {
      int axis = 0;
      long value = this.vec[axis];
      int size = this.size();

      for(int comp = 1; comp < size; ++comp) {
         if (this.vec[comp] < value) {
            value = this.vec[comp];
            axis = comp;
         }
      }

      return axis;
   }

   public int getMaxAxis() {
      int axis = 0;
      long value = this.vec[axis];
      int size = this.size();

      for(int comp = 1; comp < size; ++comp) {
         if (this.vec[comp] > value) {
            value = this.vec[comp];
            axis = comp;
         }
      }

      return axis;
   }

   @Nonnull
   public Vector2l toVector2() {
      return Vector2l.from(this);
   }

   @Nonnull
   public Vector3l toVector3() {
      return Vector3l.from(this);
   }

   @Nonnull
   public Vector4l toVector4() {
      return Vector4l.from(this);
   }

   @Nonnull
   public long[] toArray() {
      return (long[])this.vec.clone();
   }

   @Nonnull
   public VectorNi toInt() {
      int size = this.size();
      int[] intVec = new int[size];

      for(int comp = 0; comp < size; ++comp) {
         intVec[comp] = (int)this.vec[comp];
      }

      return VectorNi.from(intVec);
   }

   @Nonnull
   public VectorNl toLong() {
      int size = this.size();
      long[] longVec = new long[size];

      for(int comp = 0; comp < size; ++comp) {
         longVec[comp] = this.vec[comp];
      }

      return from(longVec);
   }

   @Nonnull
   public VectorNf toFloat() {
      int size = this.size();
      float[] floatVec = new float[size];

      for(int comp = 0; comp < size; ++comp) {
         floatVec[comp] = (float)this.vec[comp];
      }

      return VectorNf.from(floatVec);
   }

   @Nonnull
   public VectorNd toDouble() {
      int size = this.size();
      double[] doubleVec = new double[size];

      for(int comp = 0; comp < size; ++comp) {
         doubleVec[comp] = (double)this.vec[comp];
      }

      return VectorNd.from(doubleVec);
   }

   public int compareTo(VectorNl v) {
      return (int)(this.lengthSquared() - v.lengthSquared());
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else {
         return !(obj instanceof VectorNl) ? false : Arrays.equals(this.vec, ((VectorNl)obj).vec);
      }
   }

   public int hashCode() {
      return 335 + Arrays.hashCode(this.vec);
   }

   @Nonnull
   public VectorNl clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return Arrays.toString(this.vec).replace('[', '(').replace(']', ')');
   }

   @Nonnull
   public static VectorNl from(int size) {
      return from();
   }

   @Nonnull
   public static VectorNl from(Vector2l v) {
      return from(v.getX(), v.getY());
   }

   @Nonnull
   public static VectorNl from(Vector3l v) {
      return from(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public static VectorNl from(Vector4l v) {
      return from(v.getX(), v.getY(), v.getZ(), v.getW());
   }

   @Nonnull
   public static VectorNl from(VectorNl v) {
      return from(v.vec);
   }

   @Nonnull
   public static VectorNl from(long... v) {
      if (v.length < 2) {
         throw new IllegalArgumentException("Minimum vector size is 2");
      } else {
         return new VectorNl(v.clone());
      }
   }

   @ParametersAreNonnullByDefault
   private static class ImmutableZeroVectorN extends VectorNl {
      public ImmutableZeroVectorN(long... v) {
         super(v);
      }

      public void set(int comp, long val) {
         throw new UnsupportedOperationException("You may not alter this vector");
      }
   }
}
