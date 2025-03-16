package com.nukkitx.math.vector;

import com.nukkitx.math.GenericMath;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@ParametersAreNonnullByDefault
@Immutable
public class Vector3i implements Vectori, Comparable<Vector3i>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Vector3i ZERO = new Vector3i(0, 0, 0);
   public static final Vector3i UNIT_X = new Vector3i(1, 0, 0);
   public static final Vector3i UNIT_Y = new Vector3i(0, 1, 0);
   public static final Vector3i UNIT_Z = new Vector3i(0, 0, 1);
   public static final Vector3i ONE = from(1, 1, 1);
   public static final Vector3i RIGHT;
   public static final Vector3i UP;
   public static final Vector3i FORWARD;
   private final int x;
   private final int y;
   private final int z;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Vector3i(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public int getZ() {
      return this.z;
   }

   @Nonnull
   public Vector3i add(Vector3i v) {
      return this.add(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3i add(double x, double y, double z) {
      return this.add(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
   }

   @Nonnull
   public Vector3i add(int x, int y, int z) {
      return from(this.x + x, this.y + y, this.z + z);
   }

   @Nonnull
   public Vector3i sub(Vector3i v) {
      return this.sub(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3i sub(double x, double y, double z) {
      return this.sub(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
   }

   @Nonnull
   public Vector3i sub(int x, int y, int z) {
      return from(this.x - x, this.y - y, this.z - z);
   }

   @Nonnull
   public Vector3i mul(double a) {
      return this.mul(GenericMath.floor(a));
   }

   @Nonnull
   public Vector3i mul(int a) {
      return this.mul(a, a, a);
   }

   @Nonnull
   public Vector3i mul(Vector3i v) {
      return this.mul(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3i mul(double x, double y, double z) {
      return this.mul(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
   }

   @Nonnull
   public Vector3i mul(int x, int y, int z) {
      return from(this.x * x, this.y * y, this.z * z);
   }

   @Nonnull
   public Vector3i div(double a) {
      return this.div(GenericMath.floor(a));
   }

   @Nonnull
   public Vector3i div(int a) {
      return this.div(a, a, a);
   }

   @Nonnull
   public Vector3i div(Vector3i v) {
      return this.div(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3i div(double x, double y, double z) {
      return this.div(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
   }

   @Nonnull
   public Vector3i div(int x, int y, int z) {
      return from(this.x / x, this.y / y, this.z / z);
   }

   public int dot(Vector3i v) {
      return this.dot(v.x, v.y, v.z);
   }

   public int dot(double x, double y, double z) {
      return this.dot(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
   }

   public int dot(int x, int y, int z) {
      return this.x * x + this.y * y + this.z * z;
   }

   @Nonnull
   public Vector3i project(Vector3i v) {
      return this.project(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3i project(double x, double y, double z) {
      return this.project(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
   }

   @Nonnull
   public Vector3i project(int x, int y, int z) {
      int lengthSquared = x * x + y * y + z * z;
      if (lengthSquared == 0) {
         throw new ArithmeticException("Cannot project onto the zero vector");
      } else {
         float a = (float)this.dot(x, y, z) / (float)lengthSquared;
         return from((double)(a * (float)x), (double)(a * (float)y), (double)(a * (float)z));
      }
   }

   @Nonnull
   public Vector3i cross(Vector3i v) {
      return this.cross(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3i cross(double x, double y, double z) {
      return this.cross(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
   }

   @Nonnull
   public Vector3i cross(int x, int y, int z) {
      return from(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
   }

   @Nonnull
   public Vector3i pow(double pow) {
      return this.pow(GenericMath.floor(pow));
   }

   @Nonnull
   public Vector3i pow(int power) {
      return from(Math.pow((double)this.x, (double)power), Math.pow((double)this.y, (double)power), Math.pow((double)this.z, (double)power));
   }

   @Nonnull
   public Vector3i abs() {
      return from(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
   }

   @Nonnull
   public Vector3i negate() {
      return from(-this.x, -this.y, -this.z);
   }

   @Nonnull
   public Vector3i min(Vector3i v) {
      return this.min(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3i min(double x, double y, double z) {
      return this.min(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
   }

   @Nonnull
   public Vector3i min(int x, int y, int z) {
      return from(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z));
   }

   @Nonnull
   public Vector3i max(Vector3i v) {
      return this.max(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3i max(double x, double y, double z) {
      return this.max(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
   }

   @Nonnull
   public Vector3i max(int x, int y, int z) {
      return from(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z));
   }

   public int distanceSquared(Vector3i v) {
      return this.distanceSquared(v.x, v.y, v.z);
   }

   public int distanceSquared(double x, double y, double z) {
      return this.distanceSquared(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
   }

   public int distanceSquared(int x, int y, int z) {
      int dx = this.x - x;
      int dy = this.y - y;
      int dz = this.z - z;
      return dx * dx + dy * dy + dz * dz;
   }

   public float distance(Vector3i v) {
      return this.distance(v.x, v.y, v.z);
   }

   public float distance(double x, double y, double z) {
      return this.distance(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
   }

   public float distance(int x, int y, int z) {
      return (float)Math.sqrt((double)this.distanceSquared(x, y, z));
   }

   @Nonnull
   public Vector3i up() {
      return this.up(1);
   }

   @Nonnull
   public Vector3i up(int v) {
      return from(this.x, this.y + v, this.z);
   }

   @Nonnull
   public Vector3i down() {
      return this.down(1);
   }

   @Nonnull
   public Vector3i down(int v) {
      return from(this.x, this.y - v, this.z);
   }

   @Nonnull
   public Vector3i north() {
      return this.north(1);
   }

   @Nonnull
   public Vector3i north(int v) {
      return from(this.x, this.y, this.z - v);
   }

   @Nonnull
   public Vector3i south() {
      return this.south(1);
   }

   @Nonnull
   public Vector3i south(int v) {
      return from(this.x, this.y, this.z + v);
   }

   @Nonnull
   public Vector3i east() {
      return this.east(1);
   }

   @Nonnull
   public Vector3i east(int v) {
      return from(this.x + v, this.y, this.z);
   }

   @Nonnull
   public Vector3i west() {
      return this.west(1);
   }

   @Nonnull
   public Vector3i west(int v) {
      return from(this.x - v, this.y, this.z);
   }

   public int lengthSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z;
   }

   public float length() {
      return (float)Math.sqrt((double)this.lengthSquared());
   }

   public int getMinAxis() {
      return this.x < this.y ? (this.x < this.z ? 0 : 2) : (this.y < this.z ? 1 : 2);
   }

   public int getMaxAxis() {
      return this.x < this.y ? (this.y < this.z ? 2 : 1) : (this.x < this.z ? 2 : 0);
   }

   @Nonnull
   public Vector2i toVector2() {
      return Vector2i.from(this);
   }

   @Nonnull
   public Vector2i toVector2(boolean useZ) {
      return Vector2i.from(this.x, useZ ? this.z : this.y);
   }

   @Nonnull
   public Vector4i toVector4() {
      return this.toVector4(0);
   }

   @Nonnull
   public Vector4i toVector4(double w) {
      return this.toVector4(GenericMath.floor(w));
   }

   @Nonnull
   public Vector4i toVector4(int w) {
      return Vector4i.from(this, w);
   }

   @Nonnull
   public VectorNi toVectorN() {
      return VectorNi.from(this);
   }

   @Nonnull
   public int[] toArray() {
      return new int[]{this.x, this.y, this.z};
   }

   @Nonnull
   public Vector3i toInt() {
      return from(this.x, this.y, this.z);
   }

   @Nonnull
   public Vector3l toLong() {
      return Vector3l.from((long)this.x, (long)this.y, (long)this.z);
   }

   @Nonnull
   public Vector3f toFloat() {
      return Vector3f.from((float)this.x, (float)this.y, (float)this.z);
   }

   @Nonnull
   public Vector3d toDouble() {
      return Vector3d.from((float)this.x, (float)this.y, (float)this.z);
   }

   public int compareTo(Vector3i v) {
      return this.lengthSquared() - v.lengthSquared();
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Vector3i)) {
         return false;
      } else {
         Vector3i vector3 = (Vector3i)o;
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
         this.hashCode = (Integer.hashCode(this.x) * 211 + Integer.hashCode(this.y)) * 97 + Integer.hashCode(this.z);
         this.hashed = true;
      }

      return this.hashCode;
   }

   @Nonnull
   public Vector3i clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ", " + this.z + ")";
   }

   @Nonnull
   public static Vector3i from(int n) {
      return n == 0 ? ZERO : new Vector3i(n, n, n);
   }

   @Nonnull
   public static Vector3i from(Vector2i v) {
      return from(v, 0);
   }

   @Nonnull
   public static Vector3i from(Vector2i v, double z) {
      return from(v, GenericMath.floor(z));
   }

   @Nonnull
   public static Vector3i from(Vector2i v, int z) {
      return from(v.getX(), v.getY(), z);
   }

   @Nonnull
   public static Vector3i from(Vector3i v) {
      return from(v.x, v.y, v.z);
   }

   @Nonnull
   public static Vector3i from(Vector4i v) {
      return from(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public static Vector3i from(VectorNi v) {
      return from(v.get(0), v.get(1), v.size() > 2 ? v.get(2) : 0);
   }

   @Nonnull
   public static Vector3i from(double x, double y, double z) {
      return from(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
   }

   @Nonnull
   public static Vector3i from(int x, int y, int z) {
      return x == 0 && y == 0 && z == 0 ? ZERO : new Vector3i(x, y, z);
   }

   static {
      RIGHT = UNIT_X;
      UP = UNIT_Y;
      FORWARD = UNIT_Z;
   }
}
