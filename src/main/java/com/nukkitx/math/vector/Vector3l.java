package com.nukkitx.math.vector;

import com.nukkitx.math.GenericMath;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@ParametersAreNonnullByDefault
@Immutable
public class Vector3l implements Vectorl, Comparable<Vector3l>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Vector3l ZERO = new Vector3l(0L, 0L, 0L);
   public static final Vector3l UNIT_X = new Vector3l(1L, 0L, 0L);
   public static final Vector3l UNIT_Y = new Vector3l(0L, 1L, 0L);
   public static final Vector3l UNIT_Z = new Vector3l(0L, 0L, 1L);
   public static final Vector3l ONE = from(1L, 1L, 1L);
   public static final Vector3l RIGHT;
   public static final Vector3l UP;
   public static final Vector3l FORWARD;
   private final long x;
   private final long y;
   private final long z;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Vector3l(long x, long y, long z) {
      this.x = x;
      this.y = y;
      this.z = z;
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

   @Nonnull
   public Vector3l add(Vector3l v) {
      return this.add(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3l add(double x, double y, double z) {
      return this.add(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z));
   }

   @Nonnull
   public Vector3l add(long x, long y, long z) {
      return from(this.x + x, this.y + y, this.z + z);
   }

   @Nonnull
   public Vector3l sub(Vector3l v) {
      return this.sub(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3l sub(double x, double y, double z) {
      return this.sub(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z));
   }

   @Nonnull
   public Vector3l sub(long x, long y, long z) {
      return from(this.x - x, this.y - y, this.z - z);
   }

   @Nonnull
   public Vector3l mul(double a) {
      return this.mul(GenericMath.floor64(a));
   }

   @Nonnull
   public Vector3l mul(long a) {
      return this.mul(a, a, a);
   }

   @Nonnull
   public Vector3l mul(Vector3l v) {
      return this.mul(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3l mul(double x, double y, double z) {
      return this.mul(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z));
   }

   @Nonnull
   public Vector3l mul(long x, long y, long z) {
      return from(this.x * x, this.y * y, this.z * z);
   }

   @Nonnull
   public Vector3l div(double a) {
      return this.div(GenericMath.floor64(a));
   }

   @Nonnull
   public Vector3l div(long a) {
      return this.div(a, a, a);
   }

   @Nonnull
   public Vector3l div(Vector3l v) {
      return this.div(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3l div(double x, double y, double z) {
      return this.div(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z));
   }

   @Nonnull
   public Vector3l div(long x, long y, long z) {
      return from(this.x / x, this.y / y, this.z / z);
   }

   public long dot(Vector3l v) {
      return this.dot(v.x, v.y, v.z);
   }

   public long dot(double x, double y, double z) {
      return this.dot(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z));
   }

   public long dot(long x, long y, long z) {
      return this.x * x + this.y * y + this.z * z;
   }

   @Nonnull
   public Vector3l project(Vector3l v) {
      return this.project(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3l project(double x, double y, double z) {
      return this.project(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z));
   }

   @Nonnull
   public Vector3l project(long x, long y, long z) {
      long lengthSquared = x * x + y * y + z * z;
      if (lengthSquared == 0L) {
         throw new ArithmeticException("Cannot project onto the zero vector");
      } else {
         double a = (double)this.dot(x, y, z) / (double)lengthSquared;
         return from(a * (double)x, a * (double)y, a * (double)z);
      }
   }

   @Nonnull
   public Vector3l cross(Vector3l v) {
      return this.cross(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3l cross(double x, double y, double z) {
      return this.cross(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z));
   }

   @Nonnull
   public Vector3l cross(long x, long y, long z) {
      return from(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
   }

   @Nonnull
   public Vector3l pow(double pow) {
      return this.pow(GenericMath.floor64(pow));
   }

   @Nonnull
   public Vector3l pow(long power) {
      return from(Math.pow((double)this.x, (double)power), Math.pow((double)this.y, (double)power), Math.pow((double)this.z, (double)power));
   }

   @Nonnull
   public Vector3l abs() {
      return from(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
   }

   @Nonnull
   public Vector3l negate() {
      return from(-this.x, -this.y, -this.z);
   }

   @Nonnull
   public Vector3l min(Vector3l v) {
      return this.min(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3l min(double x, double y, double z) {
      return this.min(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z));
   }

   @Nonnull
   public Vector3l min(long x, long y, long z) {
      return from(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z));
   }

   @Nonnull
   public Vector3l max(Vector3l v) {
      return this.max(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3l max(double x, double y, double z) {
      return this.max(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z));
   }

   @Nonnull
   public Vector3l max(long x, long y, long z) {
      return from(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z));
   }

   public long distanceSquared(Vector3l v) {
      return this.distanceSquared(v.x, v.y, v.z);
   }

   public long distanceSquared(double x, double y, double z) {
      return this.distanceSquared(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z));
   }

   public long distanceSquared(long x, long y, long z) {
      long dx = this.x - x;
      long dy = this.y - y;
      long dz = this.z - z;
      return dx * dx + dy * dy + dz * dz;
   }

   public double distance(Vector3l v) {
      return this.distance(v.x, v.y, v.z);
   }

   public double distance(double x, double y, double z) {
      return this.distance(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z));
   }

   public double distance(long x, long y, long z) {
      return Math.sqrt((double)this.distanceSquared(x, y, z));
   }

   @Nonnull
   public Vector3l up() {
      return this.up(1L);
   }

   @Nonnull
   public Vector3l up(long v) {
      return from(this.x, this.y + v, this.z);
   }

   @Nonnull
   public Vector3l down() {
      return this.down(1L);
   }

   @Nonnull
   public Vector3l down(long v) {
      return from(this.x, this.y - v, this.z);
   }

   @Nonnull
   public Vector3l north() {
      return this.north(1L);
   }

   @Nonnull
   public Vector3l north(long v) {
      return from(this.x, this.y, this.z - v);
   }

   @Nonnull
   public Vector3l south() {
      return this.south(1L);
   }

   @Nonnull
   public Vector3l south(long v) {
      return from(this.x, this.y, this.z + v);
   }

   @Nonnull
   public Vector3l east() {
      return this.east(1L);
   }

   @Nonnull
   public Vector3l east(long v) {
      return from(this.x + v, this.y, this.z);
   }

   @Nonnull
   public Vector3l west() {
      return this.west(1L);
   }

   @Nonnull
   public Vector3l west(long v) {
      return from(this.x - v, this.y, this.z);
   }

   public long lengthSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z;
   }

   public double length() {
      return Math.sqrt((double)this.lengthSquared());
   }

   public int getMinAxis() {
      return this.x < this.y ? (this.x < this.z ? 0 : 2) : (this.y < this.z ? 1 : 2);
   }

   public int getMaxAxis() {
      return this.x < this.y ? (this.y < this.z ? 2 : 1) : (this.x < this.z ? 2 : 0);
   }

   @Nonnull
   public Vector2l toVector2() {
      return Vector2l.from(this);
   }

   @Nonnull
   public Vector2l toVector2(boolean useZ) {
      return Vector2l.from(this.x, useZ ? this.z : this.y);
   }

   @Nonnull
   public Vector4l toVector4() {
      return this.toVector4(0L);
   }

   @Nonnull
   public Vector4l toVector4(double w) {
      return this.toVector4(GenericMath.floor64(w));
   }

   @Nonnull
   public Vector4l toVector4(long w) {
      return Vector4l.from(this, w);
   }

   @Nonnull
   public VectorNl toVectorN() {
      return VectorNl.from(this);
   }

   @Nonnull
   public long[] toArray() {
      return new long[]{this.x, this.y, this.z};
   }

   @Nonnull
   public Vector3i toInt() {
      return Vector3i.from((double)this.x, (double)this.y, (double)this.z);
   }

   @Nonnull
   public Vector3l toLong() {
      return from(this.x, this.y, this.z);
   }

   @Nonnull
   public Vector3f toFloat() {
      return Vector3f.from((float)this.x, (float)this.y, (float)this.z);
   }

   @Nonnull
   public Vector3d toDouble() {
      return Vector3d.from((float)this.x, (float)this.y, (float)this.z);
   }

   public int compareTo(Vector3l v) {
      return (int)(this.lengthSquared() - v.lengthSquared());
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Vector3l)) {
         return false;
      } else {
         Vector3l vector3 = (Vector3l)o;
         if (vector3.x != this.x) {
            return false;
         } else if (vector3.y != this.y) {
            return false;
         } else {
            return vector3.z == this.z;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         this.hashCode = (Long.hashCode(this.x) * 211 + Long.hashCode(this.y)) * 97 + Long.hashCode(this.z);
         this.hashed = true;
      }

      return this.hashCode;
   }

   @Nonnull
   public Vector3l clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ", " + this.z + ")";
   }

   @Nonnull
   public static Vector3l from(long n) {
      return n == 0L ? ZERO : new Vector3l(n, n, n);
   }

   @Nonnull
   public static Vector3l from(Vector2l v) {
      return from(v, 0L);
   }

   @Nonnull
   public static Vector3l from(Vector2l v, double z) {
      return from(v, GenericMath.floor64(z));
   }

   @Nonnull
   public static Vector3l from(Vector2l v, long z) {
      return from(v.getX(), v.getY(), z);
   }

   @Nonnull
   public static Vector3l from(Vector3l v) {
      return from(v.x, v.y, v.z);
   }

   @Nonnull
   public static Vector3l from(Vector4l v) {
      return from(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public static Vector3l from(VectorNl v) {
      return from(v.get(0), v.get(1), v.size() > 2 ? v.get(2) : 0L);
   }

   @Nonnull
   public static Vector3l from(double x, double y, double z) {
      return from(GenericMath.floor64(x), GenericMath.floor64(y), GenericMath.floor64(z));
   }

   @Nonnull
   public static Vector3l from(long x, long y, long z) {
      return x == 0L && y == 0L && z == 0L ? ZERO : new Vector3l(x, y, z);
   }

   static {
      RIGHT = UNIT_X;
      UP = UNIT_Y;
      FORWARD = UNIT_Z;
   }
}
