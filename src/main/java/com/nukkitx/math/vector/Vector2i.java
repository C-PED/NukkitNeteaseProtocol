package com.nukkitx.math.vector;

import com.nukkitx.math.GenericMath;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@ParametersAreNonnullByDefault
@Immutable
public class Vector2i implements Vectori, Comparable<Vector2i>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Vector2i ZERO = new Vector2i(0, 0);
   public static final Vector2i UNIT_X = new Vector2i(1, 0);
   public static final Vector2i UNIT_Y = new Vector2i(0, 1);
   public static final Vector2i ONE = new Vector2i(1, 1);
   private final int x;
   private final int y;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Vector2i(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   @Nonnull
   public Vector2i add(Vector2i v) {
      return this.add(v.x, v.y);
   }

   @Nonnull
   public Vector2i add(double x, double y) {
      return this.add(GenericMath.floor(x), GenericMath.floor(y));
   }

   @Nonnull
   public Vector2i add(int x, int y) {
      return from(this.x + x, this.y + y);
   }

   @Nonnull
   public Vector2i sub(Vector2i v) {
      return this.sub(v.x, v.y);
   }

   @Nonnull
   public Vector2i sub(double x, double y) {
      return this.sub(GenericMath.floor(x), GenericMath.floor(y));
   }

   @Nonnull
   public Vector2i sub(int x, int y) {
      return from(this.x - x, this.y - y);
   }

   @Nonnull
   public Vector2i mul(double a) {
      return this.mul(GenericMath.floor(a));
   }

   @Nonnull
   public Vector2i mul(int a) {
      return this.mul(a, a);
   }

   @Nonnull
   public Vector2i mul(Vector2i v) {
      return this.mul(v.x, v.y);
   }

   @Nonnull
   public Vector2i mul(double x, double y) {
      return this.mul(GenericMath.floor(x), GenericMath.floor(y));
   }

   @Nonnull
   public Vector2i mul(int x, int y) {
      return from(this.x * x, this.y * y);
   }

   @Nonnull
   public Vector2i div(double a) {
      return this.div(GenericMath.floor(a));
   }

   @Nonnull
   public Vector2i div(int a) {
      return this.div(a, a);
   }

   @Nonnull
   public Vector2i div(Vector2i v) {
      return this.div(v.x, v.y);
   }

   @Nonnull
   public Vector2i div(double x, double y) {
      return this.div(GenericMath.floor(x), GenericMath.floor(y));
   }

   @Nonnull
   public Vector2i div(int x, int y) {
      return from(this.x / x, this.y / y);
   }

   public int dot(Vector2i v) {
      return this.dot(v.x, v.y);
   }

   public int dot(double x, double y) {
      return this.dot(GenericMath.floor(x), GenericMath.floor(y));
   }

   public int dot(int x, int y) {
      return this.x * x + this.y * y;
   }

   @Nonnull
   public Vector2i project(Vector2i v) {
      return this.project(v.x, v.y);
   }

   @Nonnull
   public Vector2i project(double x, double y) {
      return this.project(GenericMath.floor(x), GenericMath.floor(y));
   }

   @Nonnull
   public Vector2i project(int x, int y) {
      int lengthSquared = x * x + y * y;
      if (lengthSquared == 0) {
         throw new ArithmeticException("Cannot project onto the zero vector");
      } else {
         float a = (float)this.dot(x, y) / (float)lengthSquared;
         return from((double)(a * (float)x), (double)(a * (float)y));
      }
   }

   @Nonnull
   public Vector2i pow(double pow) {
      return this.pow(GenericMath.floor(pow));
   }

   @Nonnull
   public Vector2i pow(int power) {
      return from(Math.pow((double)this.x, (double)power), Math.pow((double)this.y, (double)power));
   }

   @Nonnull
   public Vector2i abs() {
      return from(Math.abs(this.x), Math.abs(this.y));
   }

   @Nonnull
   public Vector2i negate() {
      return from(-this.x, -this.y);
   }

   @Nonnull
   public Vector2i min(Vector2i v) {
      return this.min(v.x, v.y);
   }

   @Nonnull
   public Vector2i min(double x, double y) {
      return this.min(GenericMath.floor(x), GenericMath.floor(y));
   }

   @Nonnull
   public Vector2i min(int x, int y) {
      return from(Math.min(this.x, x), Math.min(this.y, y));
   }

   @Nonnull
   public Vector2i max(Vector2i v) {
      return this.max(v.x, v.y);
   }

   @Nonnull
   public Vector2i max(double x, double y) {
      return this.max(GenericMath.floor(x), GenericMath.floor(y));
   }

   @Nonnull
   public Vector2i max(int x, int y) {
      return from(Math.max(this.x, x), Math.max(this.y, y));
   }

   public int distanceSquared(Vector2i v) {
      return this.distanceSquared(v.x, v.y);
   }

   public int distanceSquared(double x, double y) {
      return this.distanceSquared(GenericMath.floor(x), GenericMath.floor(y));
   }

   public int distanceSquared(int x, int y) {
      int dx = this.x - x;
      int dy = this.y - y;
      return dx * dx + dy * dy;
   }

   public float distance(Vector2i v) {
      return this.distance(v.x, v.y);
   }

   public float distance(double x, double y) {
      return this.distance(GenericMath.floor(x), GenericMath.floor(y));
   }

   public float distance(int x, int y) {
      return (float)Math.sqrt((double)this.distanceSquared(x, y));
   }

   @Nonnull
   public Vector2i north() {
      return this.north(1);
   }

   @Nonnull
   public Vector2i north(int v) {
      return from(this.x, this.y - v);
   }

   @Nonnull
   public Vector2i south() {
      return this.south(1);
   }

   @Nonnull
   public Vector2i south(int v) {
      return from(this.x, this.y + v);
   }

   @Nonnull
   public Vector2i east() {
      return this.east(1);
   }

   @Nonnull
   public Vector2i east(int v) {
      return from(this.x + v, this.y);
   }

   @Nonnull
   public Vector2i west() {
      return this.west(1);
   }

   @Nonnull
   public Vector2i west(int v) {
      return from(this.x - v, this.y);
   }

   public int lengthSquared() {
      return this.x * this.x + this.y * this.y;
   }

   public float length() {
      return (float)Math.sqrt((double)this.lengthSquared());
   }

   public int getMinAxis() {
      return this.x < this.y ? 0 : 1;
   }

   public int getMaxAxis() {
      return this.x > this.y ? 0 : 1;
   }

   @Nonnull
   public Vector3i toVector3() {
      return this.toVector3(0);
   }

   @Nonnull
   public Vector3i toVector3(double z) {
      return this.toVector3(GenericMath.floor(z));
   }

   @Nonnull
   public Vector3i toVector3(int z) {
      return Vector3i.from(this, z);
   }

   @Nonnull
   public Vector4i toVector4() {
      return this.toVector4(0, 0);
   }

   @Nonnull
   public Vector4i toVector4(double z, double w) {
      return this.toVector4(GenericMath.floor(z), GenericMath.floor(w));
   }

   @Nonnull
   public Vector4i toVector4(int z, int w) {
      return Vector4i.from(this, z, w);
   }

   @Nonnull
   public VectorNi toVectorN() {
      return VectorNi.from(this);
   }

   @Nonnull
   public int[] toArray() {
      return new int[]{this.x, this.y};
   }

   @Nonnull
   public Vector2i toInt() {
      return from(this.x, this.y);
   }

   @Nonnull
   public Vector2l toLong() {
      return Vector2l.from((long)this.x, (long)this.y);
   }

   @Nonnull
   public Vector2f toFloat() {
      return Vector2f.from((float)this.x, (float)this.y);
   }

   @Nonnull
   public Vector2d toDouble() {
      return Vector2d.from((float)this.x, (float)this.y);
   }

   public int compareTo(Vector2i v) {
      return this.lengthSquared() - v.lengthSquared();
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Vector2i)) {
         return false;
      } else {
         Vector2i vector2 = (Vector2i)o;
         if (vector2.x != this.x) {
            return false;
         } else {
            return vector2.y == this.y;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         int result = (float)this.x != 0.0F ? Integer.hashCode(this.x) : 0;
         this.hashCode = 31 * result + ((float)this.y != 0.0F ? Integer.hashCode(this.y) : 0);
         this.hashed = true;
      }

      return this.hashCode;
   }

   @Nonnull
   public Vector2i clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ")";
   }

   @Nonnull
   public static Vector2i from(int n) {
      return n == 0 ? ZERO : new Vector2i(n, n);
   }

   @Nonnull
   public static Vector2i from(Vector2i v) {
      return from(v.x, v.y);
   }

   @Nonnull
   public static Vector2i from(Vector3i v) {
      return from(v.getX(), v.getY());
   }

   @Nonnull
   public static Vector2i from(Vector4i v) {
      return from(v.getX(), v.getY());
   }

   @Nonnull
   public static Vector2i from(VectorNi v) {
      return from(v.get(0), v.get(1));
   }

   @Nonnull
   public static Vector2i from(double x, double y) {
      return from(GenericMath.floor(x), GenericMath.floor(y));
   }

   @Nonnull
   public static Vector2i from(int x, int y) {
      return x == 0 && y == 0 ? ZERO : new Vector2i(x, y);
   }
}
