package com.nukkitx.math.vector;

import com.nukkitx.math.GenericMath;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@ParametersAreNonnullByDefault
@Immutable
public class Vector4l implements Vectorl, Comparable<Vector4l>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Vector4l ZERO = new Vector4l(0L, 0L, 0L, 0L);
   public static final Vector4l UNIT_X = new Vector4l(1L, 0L, 0L, 0L);
   public static final Vector4l UNIT_Y = new Vector4l(0L, 1L, 0L, 0L);
   public static final Vector4l UNIT_Z = new Vector4l(0L, 0L, 1L, 0L);
   public static final Vector4l UNIT_W = new Vector4l(0L, 0L, 0L, 1L);
   public static final Vector4l ONE = new Vector4l(1L, 1L, 1L, 1L);
   private final long x;
   private final long y;
   private final long z;
   private final long w;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Vector4l(long x, long y, long z, long w) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.w = w;
   }

   public long getX() {
      return this.x;
   }

   public long getY() {
      return this.y;
   }

   public long getZ() {
      return this.z;
   }

   public long getW() {
      return this.w;
   }

   @Nonnull
   public Vector4l add(Vector4l v) {
      return this.add(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4l add(double x, double y, double z, double w) {
      return this.add(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z), GenericMath.floor64(w));
   }

   @Nonnull
   public Vector4l add(long x, long y, long z, long w) {
      return from(this.x + x, this.y + y, this.z + z, this.w + w);
   }

   @Nonnull
   public Vector4l sub(Vector4l v) {
      return this.sub(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4l sub(double x, double y, double z, double w) {
      return this.sub(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z), GenericMath.floor64(w));
   }

   @Nonnull
   public Vector4l sub(long x, long y, long z, long w) {
      return from(this.x - x, this.y - y, this.z - z, this.w - w);
   }

   @Nonnull
   public Vector4l mul(double a) {
      return this.mul(GenericMath.floor64(a));
   }

   @Nonnull
   public Vector4l mul(long a) {
      return this.mul(a, a, a, a);
   }

   @Nonnull
   public Vector4l mul(Vector4l v) {
      return this.mul(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4l mul(double x, double y, double z, double w) {
      return this.mul(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z), GenericMath.floor64(w));
   }

   @Nonnull
   public Vector4l mul(long x, long y, long z, long w) {
      return from(this.x * x, this.y * y, this.z * z, this.w * w);
   }

   @Nonnull
   public Vector4l div(double a) {
      return this.div(GenericMath.floor64(a));
   }

   @Nonnull
   public Vector4l div(long a) {
      return this.div(a, a, a, a);
   }

   @Nonnull
   public Vector4l div(Vector4l v) {
      return this.div(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4l div(double x, double y, double z, double w) {
      return this.div(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z), GenericMath.floor64(w));
   }

   @Nonnull
   public Vector4l div(long x, long y, long z, long w) {
      return from(this.x / x, this.y / y, this.z / z, this.w / w);
   }

   public long dot(Vector4l v) {
      return this.dot(v.x, v.y, v.z, v.w);
   }

   public long dot(double x, double y, double z, double w) {
      return this.dot(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z), GenericMath.floor64(w));
   }

   public long dot(long x, long y, long z, long w) {
      return this.x * x + this.y * y + this.z * z + this.w * w;
   }

   @Nonnull
   public Vector4l project(Vector4l v) {
      return this.project(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4l project(double x, double y, double z, double w) {
      return this.project(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z), GenericMath.floor64(w));
   }

   @Nonnull
   public Vector4l project(long x, long y, long z, long w) {
      long lengthSquared = x * x + y * y + z * z + w * w;
      if (lengthSquared == 0L) {
         throw new ArithmeticException("Cannot project onto the zero vector");
      } else {
         double a = (double)this.dot(x, y, z, w) / (double)lengthSquared;
         return from(a * (double)x, a * (double)y, a * (double)z, a * (double)w);
      }
   }

   @Nonnull
   public Vector4l pow(double pow) {
      return this.pow(GenericMath.floor64(pow));
   }

   @Nonnull
   public Vector4l pow(long power) {
      return from(Math.pow((double)this.x, (double)power), Math.pow((double)this.y, (double)power), Math.pow((double)this.z, (double)power), Math.pow((double)this.w, (double)power));
   }

   @Nonnull
   public Vector4l abs() {
      return from(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z), Math.abs(this.w));
   }

   @Nonnull
   public Vector4l negate() {
      return from(-this.x, -this.y, -this.z, -this.w);
   }

   @Nonnull
   public Vector4l min(Vector4l v) {
      return this.min(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4l min(double x, double y, double z, double w) {
      return this.min(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z), GenericMath.floor64(w));
   }

   @Nonnull
   public Vector4l min(long x, long y, long z, long w) {
      return from(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z), Math.min(this.w, w));
   }

   @Nonnull
   public Vector4l max(Vector4l v) {
      return this.max(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4l max(double x, double y, double z, double w) {
      return this.max(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z), GenericMath.floor64(w));
   }

   @Nonnull
   public Vector4l max(long x, long y, long z, long w) {
      return from(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z), Math.max(this.w, w));
   }

   public long distanceSquared(Vector4l v) {
      return this.distanceSquared(v.x, v.y, v.z, v.w);
   }

   public long distanceSquared(double x, double y, double z, double w) {
      return this.distanceSquared(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z), GenericMath.floor64(w));
   }

   public long distanceSquared(long x, long y, long z, long w) {
      long dx = this.x - x;
      long dy = this.y - y;
      long dz = this.z - z;
      long dw = this.w - w;
      return dx * dx + dy * dy + dz * dz + dw * dw;
   }

   public double distance(Vector4l v) {
      return this.distance(v.x, v.y, v.z, v.w);
   }

   public double distance(double x, double y, double z, double w) {
      return this.distance(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z), GenericMath.floor64(w));
   }

   public double distance(long x, long y, long z, long w) {
      return Math.sqrt((double)this.distanceSquared(x, y, z, w));
   }

   public long lengthSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
   }

   public double length() {
      return Math.sqrt((double)this.lengthSquared());
   }

   public int getMinAxis() {
      long value = this.x;
      int axis = 0;
      if (this.y < value) {
         value = this.y;
         axis = 1;
      }

      if (this.z < value) {
         value = this.z;
         axis = 2;
      }

      if (this.w < value) {
         axis = 3;
      }

      return axis;
   }

   public int getMaxAxis() {
      long value = this.x;
      int axis = 0;
      if (this.y > value) {
         value = this.y;
         axis = 1;
      }

      if (this.z > value) {
         value = this.z;
         axis = 2;
      }

      if (this.w > value) {
         axis = 3;
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
   public VectorNl toVectorN() {
      return VectorNl.from(this);
   }

   @Nonnull
   public long[] toArray() {
      return new long[]{this.x, this.y, this.z, this.w};
   }

   @Nonnull
   public Vector4i toInt() {
      return Vector4i.from((double)this.x, (double)this.y, (double)this.z, (double)this.w);
   }

   @Nonnull
   public Vector4l toLong() {
      return from(this.x, this.y, this.z, this.w);
   }

   @Nonnull
   public Vector4f toFloat() {
      return Vector4f.from((float)this.x, (float)this.y, (float)this.z, (float)this.w);
   }

   @Nonnull
   public Vector4d toDouble() {
      return Vector4d.from((float)this.x, (float)this.y, (float)this.z, (float)this.w);
   }

   public int compareTo(Vector4l v) {
      return (int)(this.lengthSquared() - v.lengthSquared());
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Vector4l)) {
         return false;
      } else {
         Vector4l vector4 = (Vector4l)o;
         if (vector4.x != this.x) {
            return false;
         } else if (vector4.y != this.y) {
            return false;
         } else if (vector4.z != this.z) {
            return false;
         } else {
            return vector4.w == this.w;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         int result = (float)this.x != 0.0F ? Long.hashCode(this.x) : 0;
         result = 31 * result + ((float)this.y != 0.0F ? Long.hashCode(this.y) : 0);
         result = 31 * result + ((float)this.z != 0.0F ? Long.hashCode(this.z) : 0);
         this.hashCode = 31 * result + ((float)this.w != 0.0F ? Long.hashCode(this.w) : 0);
         this.hashed = true;
      }

      return this.hashCode;
   }

   @Nonnull
   public Vector4l clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
   }

   @Nonnull
   public static Vector4l from(long n) {
      return n == 0L ? ZERO : new Vector4l(n, n, n, n);
   }

   @Nonnull
   public static Vector4l from(Vector2l v) {
      return from(v, 0L, 0L);
   }

   @Nonnull
   public static Vector4l from(Vector2l v, double z, double w) {
      return from(v, GenericMath.floor64(z), GenericMath.floor64(w));
   }

   @Nonnull
   public static Vector4l from(Vector2l v, long z, long w) {
      return from(v.getX(), v.getY(), z, w);
   }

   @Nonnull
   public static Vector4l from(Vector3l v) {
      return from(v, 0L);
   }

   @Nonnull
   public static Vector4l from(Vector3l v, long w) {
      return from(v.getX(), v.getY(), v.getZ(), w);
   }

   @Nonnull
   public static Vector4l from(Vector4l v) {
      return from(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public static Vector4l from(VectorNl v) {
      return from(v.get(0), v.get(1), v.size() > 2 ? v.get(2) : 0L, v.size() > 3 ? v.get(3) : 0L);
   }

   @Nonnull
   public static Vector4l from(double x, double y, double z, double w) {
      return from(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z), GenericMath.floor64(w));
   }

   @Nonnull
   public static Vector4l from(long x, long y, long z, long w) {
      return x == 0L && y == 0L && z == 0L && w == 0L ? ZERO : new Vector4l(x, y, z, w);
   }
}
