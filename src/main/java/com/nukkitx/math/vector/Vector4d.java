package com.nukkitx.math.vector;

import com.nukkitx.math.GenericMath;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@ParametersAreNonnullByDefault
@Immutable
public class Vector4d implements Vectord, Comparable<Vector4d>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Vector4d ZERO = new Vector4d((double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F);
   public static final Vector4d UNIT_X = new Vector4d((double)1.0F, (double)0.0F, (double)0.0F, (double)0.0F);
   public static final Vector4d UNIT_Y = new Vector4d((double)0.0F, (double)1.0F, (double)0.0F, (double)0.0F);
   public static final Vector4d UNIT_Z = new Vector4d((double)0.0F, (double)0.0F, (double)1.0F, (double)0.0F);
   public static final Vector4d UNIT_W = new Vector4d((double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F);
   public static final Vector4d ONE = new Vector4d((double)1.0F, (double)1.0F, (double)1.0F, (double)1.0F);
   private final double x;
   private final double y;
   private final double z;
   private final double w;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Vector4d(double x, double y, double z, double w) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.w = w;
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }

   public double getW() {
      return this.w;
   }

   public int getFloorX() {
      return GenericMath.floor(this.x);
   }

   public int getFloorY() {
      return GenericMath.floor(this.y);
   }

   public int getFloorZ() {
      return GenericMath.floor(this.z);
   }

   public int getFloorW() {
      return GenericMath.floor(this.w);
   }

   @Nonnull
   public Vector4d add(Vector4d v) {
      return this.add(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4d add(float x, float y, float z, float w) {
      return this.add((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public Vector4d add(double x, double y, double z, double w) {
      return from(this.x + x, this.y + y, this.z + z, this.w + w);
   }

   @Nonnull
   public Vector4d sub(Vector4d v) {
      return this.sub(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4d sub(float x, float y, float z, float w) {
      return this.sub((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public Vector4d sub(double x, double y, double z, double w) {
      return from(this.x - x, this.y - y, this.z - z, this.w - w);
   }

   @Nonnull
   public Vector4d mul(float a) {
      return this.mul((double)a);
   }

   @Nonnull
   public Vector4d mul(double a) {
      return this.mul(a, a, a, a);
   }

   @Nonnull
   public Vector4d mul(Vector4d v) {
      return this.mul(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4d mul(float x, float y, float z, float w) {
      return this.mul((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public Vector4d mul(double x, double y, double z, double w) {
      return from(this.x * x, this.y * y, this.z * z, this.w * w);
   }

   @Nonnull
   public Vector4d div(float a) {
      return this.div((double)a);
   }

   @Nonnull
   public Vector4d div(double a) {
      return this.div(a, a, a, a);
   }

   @Nonnull
   public Vector4d div(Vector4d v) {
      return this.div(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4d div(float x, float y, float z, float w) {
      return this.div((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public Vector4d div(double x, double y, double z, double w) {
      return from(this.x / x, this.y / y, this.z / z, this.w / w);
   }

   public double dot(Vector4d v) {
      return this.dot(v.x, v.y, v.z, v.w);
   }

   public double dot(float x, float y, float z, float w) {
      return this.dot((double)x, (double)y, (double)z, (double)w);
   }

   public double dot(double x, double y, double z, double w) {
      return this.x * x + this.y * y + this.z * z + this.w * w;
   }

   @Nonnull
   public Vector4d project(Vector4d v) {
      return this.project(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4d project(float x, float y, float z, float w) {
      return this.project((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public Vector4d project(double x, double y, double z, double w) {
      double lengthSquared = x * x + y * y + z * z + w * w;
      if (Math.abs(lengthSquared) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot project onto the zero vector");
      } else {
         double a = this.dot(x, y, z, w) / lengthSquared;
         return from(a * x, a * y, a * z, a * w);
      }
   }

   @Nonnull
   public Vector4d pow(float pow) {
      return this.pow((double)pow);
   }

   @Nonnull
   public Vector4d pow(double power) {
      return from(Math.pow(this.x, power), Math.pow(this.y, power), Math.pow(this.z, power), Math.pow(this.w, power));
   }

   @Nonnull
   public Vector4d ceil() {
      return from(Math.ceil(this.x), Math.ceil(this.y), Math.ceil(this.z), Math.ceil(this.w));
   }

   @Nonnull
   public Vector4d floor() {
      return from((float)GenericMath.floor(this.x), (float)GenericMath.floor(this.y), (float)GenericMath.floor(this.z), (float)GenericMath.floor(this.w));
   }

   @Nonnull
   public Vector4d round() {
      return from((float)Math.round(this.x), (float)Math.round(this.y), (float)Math.round(this.z), (float)Math.round(this.w));
   }

   @Nonnull
   public Vector4d abs() {
      return from(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z), Math.abs(this.w));
   }

   @Nonnull
   public Vector4d negate() {
      return from(-this.x, -this.y, -this.z, -this.w);
   }

   @Nonnull
   public Vector4d min(Vector4d v) {
      return this.min(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4d min(float x, float y, float z, float w) {
      return this.min((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public Vector4d min(double x, double y, double z, double w) {
      return from(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z), Math.min(this.w, w));
   }

   @Nonnull
   public Vector4d max(Vector4d v) {
      return this.max(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4d max(float x, float y, float z, float w) {
      return this.max((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public Vector4d max(double x, double y, double z, double w) {
      return from(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z), Math.max(this.w, w));
   }

   public double distanceSquared(Vector4d v) {
      return this.distanceSquared(v.x, v.y, v.z, v.w);
   }

   public double distanceSquared(float x, float y, float z, float w) {
      return this.distanceSquared((double)x, (double)y, (double)z, (double)w);
   }

   public double distanceSquared(double x, double y, double z, double w) {
      double dx = this.x - x;
      double dy = this.y - y;
      double dz = this.z - z;
      double dw = this.w - w;
      return dx * dx + dy * dy + dz * dz + dw * dw;
   }

   public double distance(Vector4d v) {
      return this.distance(v.x, v.y, v.z, v.w);
   }

   public double distance(float x, float y, float z, float w) {
      return this.distance((double)x, (double)y, (double)z, (double)w);
   }

   public double distance(double x, double y, double z, double w) {
      return Math.sqrt(this.distanceSquared(x, y, z, w));
   }

   public double lengthSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
   }

   public double length() {
      return Math.sqrt(this.lengthSquared());
   }

   @Nonnull
   public Vector4d normalize() {
      double length = this.length();
      if (Math.abs(length) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot normalize the zero vector");
      } else {
         return from(this.x / length, this.y / length, this.z / length, this.w / length);
      }
   }

   public int getMinAxis() {
      double value = this.x;
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
      double value = this.x;
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
   public Vector2d toVector2() {
      return Vector2d.from(this);
   }

   @Nonnull
   public Vector3d toVector3() {
      return Vector3d.from(this);
   }

   @Nonnull
   public VectorNd toVectorN() {
      return VectorNd.from(this);
   }

   @Nonnull
   public double[] toArray() {
      return new double[]{this.x, this.y, this.z, this.w};
   }

   @Nonnull
   public Vector4i toInt() {
      return Vector4i.from(this.x, this.y, this.z, this.w);
   }

   @Nonnull
   public Vector4l toLong() {
      return Vector4l.from(this.x, this.y, this.z, this.w);
   }

   @Nonnull
   public Vector4f toFloat() {
      return Vector4f.from(this.x, this.y, this.z, this.w);
   }

   @Nonnull
   public Vector4d toDouble() {
      return from(this.x, this.y, this.z, this.w);
   }

   public int compareTo(Vector4d v) {
      return (int)Math.signum(this.lengthSquared() - v.lengthSquared());
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Vector4d)) {
         return false;
      } else {
         Vector4d vector4 = (Vector4d)o;
         if (Double.compare(vector4.w, this.w) != 0) {
            return false;
         } else if (Double.compare(vector4.x, this.x) != 0) {
            return false;
         } else if (Double.compare(vector4.y, this.y) != 0) {
            return false;
         } else {
            return Double.compare(vector4.z, this.z) == 0;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         int result = this.x != (double)0.0F ? Double.hashCode(this.x) : 0;
         result = 31 * result + (this.y != (double)0.0F ? Double.hashCode(this.y) : 0);
         result = 31 * result + (this.z != (double)0.0F ? Double.hashCode(this.z) : 0);
         this.hashCode = 31 * result + (this.w != (double)0.0F ? Double.hashCode(this.w) : 0);
         this.hashed = true;
      }

      return this.hashCode;
   }

   @Nonnull
   public Vector4d clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
   }

   @Nonnull
   public static Vector4d from(double n) {
      return n == (double)0.0F ? ZERO : new Vector4d(n, n, n, n);
   }

   @Nonnull
   public static Vector4d from(Vector2d v) {
      return from(v, 0.0F, 0.0F);
   }

   @Nonnull
   public static Vector4d from(Vector2d v, float z, float w) {
      return from(v, (double)z, (double)w);
   }

   @Nonnull
   public static Vector4d from(Vector2d v, double z, double w) {
      return from(v.getX(), v.getY(), z, w);
   }

   @Nonnull
   public static Vector4d from(Vector3d v) {
      return from(v, 0.0F);
   }

   @Nonnull
   public static Vector4d from(Vector3d v, float w) {
      return from(v, (double)w);
   }

   @Nonnull
   public static Vector4d from(Vector3d v, double w) {
      return from(v.getX(), v.getY(), v.getZ(), w);
   }

   @Nonnull
   public static Vector4d from(Vector4d v) {
      return from(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public static Vector4d from(VectorNd v) {
      return from(v.get(0), v.get(1), v.size() > 2 ? v.get(2) : (double)0.0F, v.size() > 3 ? v.get(3) : (double)0.0F);
   }

   @Nonnull
   public static Vector4d from(float x, float y, float z, float w) {
      return from((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public static Vector4d from(double x, double y, double z, double w) {
      return x == (double)0.0F && y == (double)0.0F && z == (double)0.0F && w == (double)0.0F ? ZERO : new Vector4d(x, y, z, w);
   }
}
