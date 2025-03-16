package com.nukkitx.math.vector;

import com.nukkitx.math.GenericMath;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@ParametersAreNonnullByDefault
@Immutable
public class Vector2l implements Vectorl, Comparable<Vector2l>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Vector2l ZERO = new Vector2l(0L, 0L);
   public static final Vector2l UNIT_X = new Vector2l(1L, 0L);
   public static final Vector2l UNIT_Y = new Vector2l(0L, 1L);
   public static final Vector2l ONE = new Vector2l(1L, 1L);
   private final long x;
   private final long y;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Vector2l(long x, long y) {
      this.x = x;
      this.y = y;
   }

   public long getX() {
      return this.x;
   }

   public long getY() {
      return this.y;
   }

   @Nonnull
   public Vector2l add(Vector2l v) {
      return this.add(v.x, v.y);
   }

   @Nonnull
   public Vector2l add(double x, double y) {
      return this.add(GenericMath.floor64(x), GenericMath.floor64(y));
   }

   @Nonnull
   public Vector2l add(long x, long y) {
      return from(this.x + x, this.y + y);
   }

   @Nonnull
   public Vector2l sub(Vector2l v) {
      return this.sub(v.x, v.y);
   }

   @Nonnull
   public Vector2l sub(double x, double y) {
      return this.sub(GenericMath.floor64(x), GenericMath.floor64(y));
   }

   @Nonnull
   public Vector2l sub(long x, long y) {
      return from(this.x - x, this.y - y);
   }

   @Nonnull
   public Vector2l mul(double a) {
      return this.mul(GenericMath.floor64(a));
   }

   @Nonnull
   public Vector2l mul(long a) {
      return this.mul(a, a);
   }

   @Nonnull
   public Vector2l mul(Vector2l v) {
      return this.mul(v.x, v.y);
   }

   @Nonnull
   public Vector2l mul(double x, double y) {
      return this.mul(GenericMath.floor64(x), GenericMath.floor64(y));
   }

   @Nonnull
   public Vector2l mul(long x, long y) {
      return from(this.x * x, this.y * y);
   }

   @Nonnull
   public Vector2l div(double a) {
      return this.div(GenericMath.floor64(a));
   }

   @Nonnull
   public Vector2l div(long a) {
      return this.div(a, a);
   }

   @Nonnull
   public Vector2l div(Vector2l v) {
      return this.div(v.x, v.y);
   }

   @Nonnull
   public Vector2l div(double x, double y) {
      return this.div(GenericMath.floor64(x), GenericMath.floor64(y));
   }

   @Nonnull
   public Vector2l div(long x, long y) {
      return from(this.x / x, this.y / y);
   }

   public long dot(Vector2l v) {
      return this.dot(v.x, v.y);
   }

   public long dot(double x, double y) {
      return this.dot(GenericMath.floor64(x), GenericMath.floor64(y));
   }

   public long dot(long x, long y) {
      return this.x * x + this.y * y;
   }

   @Nonnull
   public Vector2l project(Vector2l v) {
      return this.project(v.x, v.y);
   }

   @Nonnull
   public Vector2l project(double x, double y) {
      return this.project(GenericMath.floor64(x), GenericMath.floor64(y));
   }

   @Nonnull
   public Vector2l project(long x, long y) {
      long lengthSquared = x * x + y * y;
      if (lengthSquared == 0L) {
         throw new ArithmeticException("Cannot project onto the zero vector");
      } else {
         double a = (double)this.dot(x, y) / (double)lengthSquared;
         return from(a * (double)x, a * (double)y);
      }
   }

   @Nonnull
   public Vector2l pow(double pow) {
      return this.pow(GenericMath.floor64(pow));
   }

   @Nonnull
   public Vector2l pow(long power) {
      return from(Math.pow((double)this.x, (double)power), Math.pow((double)this.y, (double)power));
   }

   @Nonnull
   public Vector2l abs() {
      return from(Math.abs(this.x), Math.abs(this.y));
   }

   @Nonnull
   public Vector2l negate() {
      return from(-this.x, -this.y);
   }

   @Nonnull
   public Vector2l min(Vector2l v) {
      return this.min(v.x, v.y);
   }

   @Nonnull
   public Vector2l min(double x, double y) {
      return this.min(GenericMath.floor64(x), GenericMath.floor64(y));
   }

   @Nonnull
   public Vector2l min(long x, long y) {
      return from(Math.min(this.x, x), Math.min(this.y, y));
   }

   @Nonnull
   public Vector2l max(Vector2l v) {
      return this.max(v.x, v.y);
   }

   @Nonnull
   public Vector2l max(double x, double y) {
      return this.max(GenericMath.floor64(x), GenericMath.floor64(y));
   }

   @Nonnull
   public Vector2l max(long x, long y) {
      return from(Math.max(this.x, x), Math.max(this.y, y));
   }

   public long distanceSquared(Vector2l v) {
      return this.distanceSquared(v.x, v.y);
   }

   public long distanceSquared(double x, double y) {
      return this.distanceSquared(GenericMath.floor64(x), GenericMath.floor64(y));
   }

   public long distanceSquared(long x, long y) {
      long dx = this.x - x;
      long dy = this.y - y;
      return dx * dx + dy * dy;
   }

   public double distance(Vector2l v) {
      return this.distance(v.x, v.y);
   }

   public double distance(double x, double y) {
      return this.distance(GenericMath.floor64(x), GenericMath.floor64(y));
   }

   public double distance(long x, long y) {
      return Math.sqrt((double)this.distanceSquared(x, y));
   }

   @Nonnull
   public Vector2l north() {
      return this.north(1L);
   }

   @Nonnull
   public Vector2l north(long v) {
      return from(this.x, this.y - v);
   }

   @Nonnull
   public Vector2l south() {
      return this.south(1L);
   }

   @Nonnull
   public Vector2l south(long v) {
      return from(this.x, this.y + v);
   }

   @Nonnull
   public Vector2l east() {
      return this.east(1L);
   }

   @Nonnull
   public Vector2l east(long v) {
      return from(this.x + v, this.y);
   }

   @Nonnull
   public Vector2l west() {
      return this.west(1L);
   }

   @Nonnull
   public Vector2l west(long v) {
      return from(this.x - v, this.y);
   }

   public long lengthSquared() {
      return this.x * this.x + this.y * this.y;
   }

   public double length() {
      return Math.sqrt((double)this.lengthSquared());
   }

   public int getMinAxis() {
      return this.x < this.y ? 0 : 1;
   }

   public int getMaxAxis() {
      return this.x > this.y ? 0 : 1;
   }

   @Nonnull
   public Vector3l toVector3() {
      return this.toVector3(0L);
   }

   @Nonnull
   public Vector3l toVector3(double z) {
      return this.toVector3(GenericMath.floor64(z));
   }

   @Nonnull
   public Vector3l toVector3(long z) {
      return Vector3l.from(this, z);
   }

   @Nonnull
   public Vector4l toVector4() {
      return this.toVector4(0L, 0L);
   }

   @Nonnull
   public Vector4l toVector4(double z, double w) {
      return this.toVector4(GenericMath.floor64(z), GenericMath.floor64(w));
   }

   @Nonnull
   public Vector4l toVector4(long z, long w) {
      return Vector4l.from(this, z, w);
   }

   @Nonnull
   public VectorNl toVectorN() {
      return VectorNl.from(this);
   }

   @Nonnull
   public long[] toArray() {
      return new long[]{this.x, this.y};
   }

   @Nonnull
   public Vector2i toInt() {
      return Vector2i.from((double)this.x, (double)this.y);
   }

   @Nonnull
   public Vector2l toLong() {
      return from(this.x, this.y);
   }

   @Nonnull
   public Vector2f toFloat() {
      return Vector2f.from((float)this.x, (float)this.y);
   }

   @Nonnull
   public Vector2d toDouble() {
      return Vector2d.from((float)this.x, (float)this.y);
   }

   public int compareTo(Vector2l v) {
      return (int)(this.lengthSquared() - v.lengthSquared());
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Vector2l)) {
         return false;
      } else {
         Vector2l vector2 = (Vector2l)o;
         if (vector2.x != this.x) {
            return false;
         } else {
            return vector2.y == this.y;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         int result = (float)this.x != 0.0F ? Long.hashCode(this.x) : 0;
         this.hashCode = 31 * result + ((float)this.y != 0.0F ? Long.hashCode(this.y) : 0);
         this.hashed = true;
      }

      return this.hashCode;
   }

   @Nonnull
   public Vector2l clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ")";
   }

   @Nonnull
   public static Vector2l from(long n) {
      return n == 0L ? ZERO : new Vector2l(n, n);
   }

   @Nonnull
   public static Vector2l from(Vector2l v) {
      return from(v.x, v.y);
   }

   @Nonnull
   public static Vector2l from(Vector3l v) {
      return from(v.getX(), v.getY());
   }

   @Nonnull
   public static Vector2l from(Vector4l v) {
      return from(v.getX(), v.getY());
   }

   @Nonnull
   public static Vector2l from(VectorNl v) {
      return from(v.get(0), v.get(1));
   }

   @Nonnull
   public static Vector2l from(double x, double y) {
      return from(GenericMath.floor64(x), GenericMath.floor64(y));
   }

   @Nonnull
   public static Vector2l from(long x, long y) {
      return x == 0L && y == 0L ? ZERO : new Vector2l(x, y);
   }
}
