package com.nukkitx.math.vector;

import com.nukkitx.math.GenericMath;
import java.io.Serializable;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class VectorNi implements Vectori, Comparable<VectorNi>, Serializable, Cloneable {
   public static VectorNi ZERO_2 = new ImmutableZeroVectorN(new int[]{0, 0});
   public static VectorNi ZERO_3 = new ImmutableZeroVectorN(new int[]{0, 0, 0});
   public static VectorNi ZERO_4 = new ImmutableZeroVectorN(new int[]{0, 0, 0, 0});
   private static final long serialVersionUID = 1L;
   private final int[] vec;

   private VectorNi(int[] v) {
      this.vec = v.clone();
   }

   public int size() {
      return this.vec.length;
   }

   public int get(int comp) {
      return this.vec[comp];
   }

   public void set(int comp, int val) {
      this.vec[comp] = val;
   }

   public void setZero() {
      Arrays.fill(this.vec, 0);
   }

   @Nonnull
   public VectorNi resize(int size) {
      VectorNi d = from(size);
      System.arraycopy(this.vec, 0, d.vec, 0, Math.min(size, this.size()));
      return d;
   }

   @Nonnull
   public VectorNi add(VectorNi v) {
      return this.add(v.vec);
   }

   @Nonnull
   public VectorNi add(int... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNi d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] + v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNi sub(VectorNi v) {
      return this.sub(v.vec);
   }

   @Nonnull
   public VectorNi sub(int... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNi d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] - v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNi mul(double a) {
      return this.mul(GenericMath.floor(a));
   }

   @Nonnull
   public VectorNi mul(int a) {
      int size = this.size();
      VectorNi d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = this.vec[comp] * a;
      }

      return d;
   }

   @Nonnull
   public VectorNi mul(VectorNi v) {
      return this.mul(v.vec);
   }

   @Nonnull
   public VectorNi mul(int... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNi d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] * v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNi div(double a) {
      return this.div(GenericMath.floor(a));
   }

   @Nonnull
   public VectorNi div(int a) {
      int size = this.size();
      VectorNi d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = this.vec[comp] / a;
      }

      return d;
   }

   @Nonnull
   public VectorNi div(VectorNi v) {
      return this.div(v.vec);
   }

   @Nonnull
   public VectorNi div(int... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNi d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] / v[comp];
         }

         return d;
      }
   }

   public int dot(VectorNi v) {
      return this.dot(v.vec);
   }

   public int dot(int... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         int d = 0;

         for(int comp = 0; comp < size; ++comp) {
            d += this.vec[comp] * v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNi project(VectorNi v) {
      return this.project(v.vec);
   }

   @Nonnull
   public VectorNi project(int... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         int lengthSquared = 0;

         for(int comp = 0; comp < size; ++comp) {
            lengthSquared += v[comp] * v[comp];
         }

         if (lengthSquared == 0) {
            throw new ArithmeticException("Cannot project onto the zero vector");
         } else {
            float a = (float)this.dot(v) / (float)lengthSquared;
            VectorNi d = from(size);

            for(int comp = 0; comp < size; ++comp) {
               d.vec[comp] = GenericMath.floor(a * (float)v[comp]);
            }

            return d;
         }
      }
   }

   @Nonnull
   public VectorNi pow(double pow) {
      return this.pow(GenericMath.floor(pow));
   }

   @Nonnull
   public VectorNi pow(int power) {
      int size = this.size();
      VectorNi d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = GenericMath.floor(Math.pow((double)this.vec[comp], (double)power));
      }

      return d;
   }

   @Nonnull
   public VectorNi abs() {
      int size = this.size();
      VectorNi d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = Math.abs(this.vec[comp]);
      }

      return d;
   }

   @Nonnull
   public VectorNi negate() {
      int size = this.size();
      VectorNi d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = -this.vec[comp];
      }

      return d;
   }

   @Nonnull
   public VectorNi min(VectorNi v) {
      return this.min(v.vec);
   }

   @Nonnull
   public VectorNi min(int... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNi d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = Math.min(this.vec[comp], v[comp]);
         }

         return d;
      }
   }

   @Nonnull
   public VectorNi max(VectorNi v) {
      return this.max(v.vec);
   }

   @Nonnull
   public VectorNi max(int... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNi d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = Math.max(this.vec[comp], v[comp]);
         }

         return d;
      }
   }

   public int distanceSquared(VectorNi v) {
      return this.distanceSquared(v.vec);
   }

   public int distanceSquared(int... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         int d = 0;

         for(int comp = 0; comp < size; ++comp) {
            int delta = this.vec[comp] - v[comp];
            d += delta * delta;
         }

         return d;
      }
   }

   public float distance(VectorNi v) {
      return this.distance(v.vec);
   }

   public float distance(int... v) {
      return (float)Math.sqrt((double)this.distanceSquared(v));
   }

   public int lengthSquared() {
      int size = this.size();
      int l = 0;

      for(int comp = 0; comp < size; ++comp) {
         l += this.vec[comp] * this.vec[comp];
      }

      return l;
   }

   public float length() {
      return (float)Math.sqrt((double)this.lengthSquared());
   }

   public int getMinAxis() {
      int axis = 0;
      int value = this.vec[axis];
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
      int value = this.vec[axis];
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
   public Vector2i toVector2() {
      return Vector2i.from(this);
   }

   @Nonnull
   public Vector3i toVector3() {
      return Vector3i.from(this);
   }

   @Nonnull
   public Vector4i toVector4() {
      return Vector4i.from(this);
   }

   @Nonnull
   public int[] toArray() {
      return (int[])this.vec.clone();
   }

   @Nonnull
   public VectorNi toInt() {
      int size = this.size();
      int[] intVec = new int[size];

      for(int comp = 0; comp < size; ++comp) {
         intVec[comp] = this.vec[comp];
      }

      return from(intVec);
   }

   @Nonnull
   public VectorNl toLong() {
      int size = this.size();
      long[] longVec = new long[size];

      for(int comp = 0; comp < size; ++comp) {
         longVec[comp] = (long)this.vec[comp];
      }

      return VectorNl.from(longVec);
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

   public int compareTo(VectorNi v) {
      return this.lengthSquared() - v.lengthSquared();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else {
         return !(obj instanceof VectorNi) ? false : Arrays.equals(this.vec, ((VectorNi)obj).vec);
      }
   }

   public int hashCode() {
      return 335 + Arrays.hashCode(this.vec);
   }

   @Nonnull
   public VectorNi clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return Arrays.toString(this.vec).replace('[', '(').replace(']', ')');
   }

   @Nonnull
   public static VectorNi from(int size) {
      return from();
   }

   @Nonnull
   public static VectorNi from(Vector2i v) {
      return from(v.getX(), v.getY());
   }

   @Nonnull
   public static VectorNi from(Vector3i v) {
      return from(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public static VectorNi from(Vector4i v) {
      return from(v.getX(), v.getY(), v.getZ(), v.getW());
   }

   @Nonnull
   public static VectorNi from(VectorNi v) {
      return from(v.vec);
   }

   @Nonnull
   public static VectorNi from(int... v) {
      if (v.length < 2) {
         throw new IllegalArgumentException("Minimum vector size is 2");
      } else {
         return new VectorNi(v.clone());
      }
   }

   @ParametersAreNonnullByDefault
   private static class ImmutableZeroVectorN extends VectorNi {
      public ImmutableZeroVectorN(int... v) {
         super(v);
      }

      public void set(int comp, int val) {
         throw new UnsupportedOperationException("You may not alter this vector");
      }
   }
}
