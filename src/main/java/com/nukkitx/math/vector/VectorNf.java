package com.nukkitx.math.vector;

import com.nukkitx.math.GenericMath;
import java.io.Serializable;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class VectorNf implements Vectorf, Comparable<VectorNf>, Serializable, Cloneable {
   public static VectorNf ZERO_2 = new ImmutableZeroVectorN(new float[]{0.0F, 0.0F});
   public static VectorNf ZERO_3 = new ImmutableZeroVectorN(new float[]{0.0F, 0.0F, 0.0F});
   public static VectorNf ZERO_4 = new ImmutableZeroVectorN(new float[]{0.0F, 0.0F, 0.0F, 0.0F});
   private static final long serialVersionUID = 1L;
   private final float[] vec;

   private VectorNf(float[] v) {
      this.vec = (v).clone();
   }

   public int size() {
      return this.vec.length;
   }

   public float get(int comp) {
      return this.vec[comp];
   }

   public int getFloored(int comp) {
      return GenericMath.floor(this.get(comp));
   }

   public void set(int comp, double val) {
      this.set(comp, (float)val);
   }

   public void set(int comp, float val) {
      this.vec[comp] = val;
   }

   public void setZero() {
      Arrays.fill(this.vec, 0.0F);
   }

   @Nonnull
   public VectorNf resize(int size) {
      VectorNf d = from(size);
      System.arraycopy(this.vec, 0, d.vec, 0, Math.min(size, this.size()));
      return d;
   }

   @Nonnull
   public VectorNf add(VectorNf v) {
      return this.add(v.vec);
   }

   @Nonnull
   public VectorNf add(float... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNf d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] + v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNf sub(VectorNf v) {
      return this.sub(v.vec);
   }

   @Nonnull
   public VectorNf sub(float... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNf d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] - v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNf mul(double a) {
      return this.mul((float)a);
   }

   @Nonnull
   public VectorNf mul(float a) {
      int size = this.size();
      VectorNf d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = this.vec[comp] * a;
      }

      return d;
   }

   @Nonnull
   public VectorNf mul(VectorNf v) {
      return this.mul(v.vec);
   }

   @Nonnull
   public VectorNf mul(float... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNf d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] * v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNf div(double a) {
      return this.div((float)a);
   }

   @Nonnull
   public VectorNf div(float a) {
      int size = this.size();
      VectorNf d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = this.vec[comp] / a;
      }

      return d;
   }

   @Nonnull
   public VectorNf div(VectorNf v) {
      return this.div(v.vec);
   }

   @Nonnull
   public VectorNf div(float... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNf d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] / v[comp];
         }

         return d;
      }
   }

   public float dot(VectorNf v) {
      return this.dot(v.vec);
   }

   public float dot(float... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         float d = 0.0F;

         for(int comp = 0; comp < size; ++comp) {
            d += this.vec[comp] * v[comp];
         }

         return d;
      }
   }

   @Nonnull
   public VectorNf project(VectorNf v) {
      return this.project(v.vec);
   }

   @Nonnull
   public VectorNf project(float... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         float lengthSquared = 0.0F;

         for(int comp = 0; comp < size; ++comp) {
            lengthSquared += v[comp] * v[comp];
         }

         if (Math.abs(lengthSquared) < GenericMath.FLT_EPSILON) {
            throw new ArithmeticException("Cannot project onto the zero vector");
         } else {
            float a = this.dot(v) / lengthSquared;
            VectorNf d = from(size);

            for(int comp = 0; comp < size; ++comp) {
               d.vec[comp] = a * v[comp];
            }

            return d;
         }
      }
   }

   @Nonnull
   public VectorNf pow(double pow) {
      return this.pow((float)pow);
   }

   @Nonnull
   public VectorNf pow(float power) {
      int size = this.size();
      VectorNf d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = (float)Math.pow((double)this.vec[comp], (double)power);
      }

      return d;
   }

   @Nonnull
   public VectorNf ceil() {
      int size = this.size();
      VectorNf d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = (float)Math.ceil((double)this.vec[comp]);
      }

      return d;
   }

   @Nonnull
   public VectorNf floor() {
      int size = this.size();
      VectorNf d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = (float)GenericMath.floor(this.vec[comp]);
      }

      return d;
   }

   @Nonnull
   public VectorNf round() {
      int size = this.size();
      VectorNf d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = (float)Math.round(this.vec[comp]);
      }

      return d;
   }

   @Nonnull
   public VectorNf abs() {
      int size = this.size();
      VectorNf d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = Math.abs(this.vec[comp]);
      }

      return d;
   }

   @Nonnull
   public VectorNf negate() {
      int size = this.size();
      VectorNf d = from(size);

      for(int comp = 0; comp < size; ++comp) {
         d.vec[comp] = -this.vec[comp];
      }

      return d;
   }

   @Nonnull
   public VectorNf min(VectorNf v) {
      return this.min(v.vec);
   }

   @Nonnull
   public VectorNf min(float... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNf d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = Math.min(this.vec[comp], v[comp]);
         }

         return d;
      }
   }

   @Nonnull
   public VectorNf max(VectorNf v) {
      return this.max(v.vec);
   }

   @Nonnull
   public VectorNf max(float... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         VectorNf d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = Math.max(this.vec[comp], v[comp]);
         }

         return d;
      }
   }

   public float distanceSquared(VectorNf v) {
      return this.distanceSquared(v.vec);
   }

   public float distanceSquared(float... v) {
      int size = this.size();
      if (size != v.length) {
         throw new IllegalArgumentException("Vector sizes must be the same");
      } else {
         float d = 0.0F;

         for(int comp = 0; comp < size; ++comp) {
            float delta = this.vec[comp] - v[comp];
            d += delta * delta;
         }

         return d;
      }
   }

   public float distance(VectorNf v) {
      return this.distance(v.vec);
   }

   public float distance(float... v) {
      return (float)Math.sqrt((double)this.distanceSquared(v));
   }

   public float lengthSquared() {
      int size = this.size();
      float l = 0.0F;

      for(int comp = 0; comp < size; ++comp) {
         l += this.vec[comp] * this.vec[comp];
      }

      return l;
   }

   public float length() {
      return (float)Math.sqrt((double)this.lengthSquared());
   }

   @Nonnull
   public VectorNf normalize() {
      float length = this.length();
      if (Math.abs(length) < GenericMath.FLT_EPSILON) {
         throw new ArithmeticException("Cannot normalize the zero vector");
      } else {
         int size = this.size();
         VectorNf d = from(size);

         for(int comp = 0; comp < size; ++comp) {
            d.vec[comp] = this.vec[comp] / length;
         }

         return d;
      }
   }

   public int getMinAxis() {
      int axis = 0;
      float value = this.vec[axis];
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
      float value = this.vec[axis];
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
   public Vector2f toVector2() {
      return Vector2f.from(this);
   }

   @Nonnull
   public Vector3f toVector3() {
      return Vector3f.from(this);
   }

   @Nonnull
   public Vector4f toVector4() {
      return Vector4f.from(this);
   }

   @Nonnull
   public float[] toArray() {
      return (float[])this.vec.clone();
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
         floatVec[comp] = this.vec[comp];
      }

      return from(floatVec);
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

   public int compareTo(VectorNf v) {
      return (int)Math.signum(this.lengthSquared() - v.lengthSquared());
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else {
         return !(obj instanceof VectorNf) ? false : Arrays.equals(this.vec, ((VectorNf)obj).vec);
      }
   }

   public int hashCode() {
      return 335 + Arrays.hashCode(this.vec);
   }

   @Nonnull
   public VectorNf clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return Arrays.toString(this.vec).replace('[', '(').replace(']', ')');
   }

   @Nonnull
   public static VectorNf from(int size) {
      return from();
   }

   @Nonnull
   public static VectorNf from(Vector2f v) {
      return from(v.getX(), v.getY());
   }

   @Nonnull
   public static VectorNf from(Vector3f v) {
      return from(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public static VectorNf from(Vector4f v) {
      return from(v.getX(), v.getY(), v.getZ(), v.getW());
   }

   @Nonnull
   public static VectorNf from(VectorNf v) {
      return from(v.vec);
   }

   @Nonnull
   public static VectorNf from(float... v) {
      if (v.length < 2) {
         throw new IllegalArgumentException("Minimum vector size is 2");
      } else {
         return new VectorNf(v);
      }
   }

   private static class ImmutableZeroVectorN extends VectorNf {
      public ImmutableZeroVectorN(float... v) {
         super(v);
      }

      public void set(int comp, float val) {
         throw new UnsupportedOperationException("You may not alter this vector");
      }
   }
}
