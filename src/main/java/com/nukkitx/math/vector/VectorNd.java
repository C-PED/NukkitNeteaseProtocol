package com.nukkitx.math.vector;

import com.nukkitx.math.GenericMath;
import java.io.Serializable;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class VectorNd implements Vectord, Comparable<VectorNd>, Serializable, Cloneable {
   public static VectorNd ZERO_2 = new ImmutableZeroVectorN(new double[]{(double)0.0F, (double)0.0F});
   public static VectorNd ZERO_3 = new ImmutableZeroVectorN(new double[]{(double)0.0F, (double)0.0F, (double)0.0F});
   public static VectorNd ZERO_4 = new ImmutableZeroVectorN(new double[]{(double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F});
   private static final long serialVersionUID = 1L;
   private final double[] vec;

   private VectorNd(double[] v) {
      this.vec = (v).clone();
   }

   public int size() {
      return this.vec.length;
   }

   public double get(int comp) {
      return this.vec[comp];
   }

   public int getFloored(int comp) {
      return GenericMath.floor(this.get(comp));
   }

   public void set(int comp, float val) {
      this.set(comp, (double)val);
   }

   public void set(int comp, double val) {
      this.vec[comp] = val;
   }

   public void setZero() {
      Arrays.fill(this.vec, (double)0.0F);
   }

   @Nonnull
   public VectorNd resize(int size) {
      VectorNd d = from(size);
      System.arraycopy(this.vec, 0, d.vec, 0, Math.min(size, this.size()));
      return d;
   }

   @Nonnull
   public VectorNd add(VectorNd v) {
      return this.add(v.vec);
   }

   @Nonnull
   public VectorNd add(double... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNd d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] + v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNd sub(VectorNd v) {
      return this.sub(v.vec);
   }

   @Nonnull
   public VectorNd sub(double... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNd d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] - v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNd mul(float a) {
      return this.mul((double)a);
   }

   @Nonnull
   public VectorNd mul(double a) {
      int size = this.size();
      VectorNd d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = this.vec[comp] * a;
      }

      return d;
   }

   @Nonnull
   public VectorNd mul(VectorNd v) {
      return this.mul(v.vec);
   }

   @Nonnull
   public VectorNd mul(double... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNd d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] * v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNd div(float a) {
      return this.div((double)a);
   }

   @Nonnull
   public VectorNd div(double a) {
      int size = this.size();
      VectorNd d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = this.vec[comp] / a;
      }

      return d;
   }

   @Nonnull
   public VectorNd div(VectorNd v) {
      return this.div(v.vec);
   }

   @Nonnull
   public VectorNd div(double... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNd d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] / v[comp];
         }

         return d;
      }
   }

   public double dot(VectorNd v) {
      return this.dot(v.vec);
   }

   public double dot(double... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         double d = (double)0.0F;

         for(int comp = 0; comp < size; ++comp) {
            d += this.vec[comp] * v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNd project(VectorNd v) {
      return this.project(v.vec);
   }

   @Nonnull
   public VectorNd project(double... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         double lengthSquared = (double)0.0F;

         for(int comp = 0; comp < size; ++comp) {
            lengthSquared += v[comp] * v[comp];
         }

         if (Math.abs(lengthSquared) < GenericMath.DBL_EPSILON) {
            throw new ArithmeticException("Cannot project onto the zero vector");
         } else {
            double a = this.dot(v) / lengthSquared;
            VectorNd d = from(size);

            for(int comp = 0; comp < size; ++comp) {
               d.vec[comp] = a * v[comp];
            }

            return d;
         }
      }
   }

   @Nonnull
   public VectorNd pow(float pow) {
      return this.pow((double)pow);
   }

   @Nonnull
   public VectorNd pow(double power) {
      int size = this.size();
      VectorNd d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = Math.pow(this.vec[comp], power);
      }

      return d;
   }

   @Nonnull
   public VectorNd ceil() {
      int size = this.size();
      VectorNd d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = Math.ceil(this.vec[comp]);
      }

      return d;
   }

   @Nonnull
   public VectorNd floor() {
      int size = this.size();
      VectorNd d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = (double)GenericMath.floor(this.vec[comp]);
      }

      return d;
   }

   @Nonnull
   public VectorNd round() {
      int size = this.size();
      VectorNd d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = (double)Math.round(this.vec[comp]);
      }

      return d;
   }

   @Nonnull
   public VectorNd abs() {
      int size = this.size();
      VectorNd d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = Math.abs(this.vec[comp]);
      }

      return d;
   }

   @Nonnull
   public VectorNd negate() {
      int size = this.size();
      VectorNd d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = -this.vec[comp];
      }

      return d;
   }

   @Nonnull
   public VectorNd min(VectorNd v) {
      return this.min(v.vec);
   }

   @Nonnull
   public VectorNd min(double... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNd d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = Math.min(this.vec[comp], v[comp]);
         }

         return d;
      }
   }

   @Nonnull
   public VectorNd max(VectorNd v) {
      return this.max(v.vec);
   }

   @Nonnull
   public VectorNd max(double... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNd d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = Math.max(this.vec[comp], v[comp]);
         }

         return d;
      }
   }

   public double distanceSquared(VectorNd v) {
      return this.distanceSquared(v.vec);
   }

   public double distanceSquared(double... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         double d = (double)0.0F;

         for(int comp = 0; comp < size; ++comp) {
            double delta = this.vec[comp] - v[comp];
            d += delta * delta;
         }

         return d;
      }
   }

   public double distance(VectorNd v) {
      return this.distance(v.vec);
   }

   public double distance(double... v) {
      return Math.sqrt(this.distanceSquared(v));
   }

   public double lengthSquared() {
      int size = this.size();
      double l = (double)0.0F;

      for(int comp = 0; comp < size; ++comp) {
         l += this.vec[comp] * this.vec[comp];
      }

      return l;
   }

   public double length() {
      return Math.sqrt(this.lengthSquared());
   }

   @Nonnull
   public VectorNd normalize() {
      double length = this.length();
      if (Math.abs(length) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot normalize the zero vector");
      } else {
         int size = this.size();
         VectorNd d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] / length;
         }

         return d;
      }
   }

   public int getMinAxis() {
      int axis = 0;
      double value = this.vec[axis];
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
      double value = this.vec[axis];
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
   public Vector2d toVector2() {
      return Vector2d.from(this);
   }

   @Nonnull
   public Vector3d toVector3() {
      return Vector3d.from(this);
   }

   @Nonnull
   public Vector4d toVector4() {
      return Vector4d.from(this);
   }

   @Nonnull
   public double[] toArray() {
      return (double[])this.vec.clone();
   }

   @Nonnull
   public VectorNi toInt() {
      int size = this.size();
      int[] intVec = new int[size];

      for(int comp = 0; comp < size; ++comp) {
         intVec[comp] = GenericMath.floor(this.vec[comp]);
      }

      return VectorNi.from(intVec);
   }

   @Nonnull
   public VectorNl toLong() {
      int size = this.size();
      long[] longVec = new long[size];

      for(int comp = 0; comp < size; ++comp) {
         longVec[comp] = GenericMath.floor64(this.vec[comp]);
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
         doubleVec[comp] = this.vec[comp];
      }

      return from(doubleVec);
   }

   public int compareTo(VectorNd v) {
      return (int)Math.signum(this.lengthSquared() - v.lengthSquared());
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else {
         return !(obj instanceof VectorNd) ? false : Arrays.equals(this.vec, ((VectorNd)obj).vec);
      }
   }

   public int hashCode() {
      return 335 + Arrays.hashCode(this.vec);
   }

   @Nonnull
   public VectorNd clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return Arrays.toString(this.vec).replace('[', '(').replace(']', ')');
   }

   @Nonnull
   public static VectorNd from(int size) {
      return from();
   }

   @Nonnull
   public static VectorNd from(Vector2d v) {
      return from(v.getX(), v.getY());
   }

   @Nonnull
   public static VectorNd from(Vector3d v) {
      return from(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public static VectorNd from(Vector4d v) {
      return from(v.getX(), v.getY(), v.getZ(), v.getW());
   }

   @Nonnull
   public static VectorNd from(VectorNd v) {
      return from(v.vec);
   }

   @Nonnull
   public static VectorNd from(double... v) {
      if (v.length < 2) {
         throw new IllegalArgumentException("Minimum vector size is 2");
      } else {
         return new VectorNd(v);
      }
   }

   private static class ImmutableZeroVectorN extends VectorNd {
      public ImmutableZeroVectorN(double... v) {
         super(v);
      }

      public void set(int comp, double val) {
         throw new UnsupportedOperationException("You may not alter this vector");
      }
   }
}
