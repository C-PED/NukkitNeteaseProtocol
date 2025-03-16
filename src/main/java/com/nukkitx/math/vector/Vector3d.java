package com.nukkitx.math.vector;

import com.nukkitx.math.GenericMath;
import com.nukkitx.math.TrigMath;
import java.io.Serializable;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@ParametersAreNonnullByDefault
@Immutable
public class Vector3d implements Vectord, Comparable<Vector3d>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Vector3d ZERO = new Vector3d((double)0.0F, (double)0.0F, (double)0.0F);
   public static final Vector3d UNIT_X = new Vector3d((double)1.0F, (double)0.0F, (double)0.0F);
   public static final Vector3d UNIT_Y = new Vector3d((double)0.0F, (double)1.0F, (double)0.0F);
   public static final Vector3d UNIT_Z = new Vector3d((double)0.0F, (double)0.0F, (double)1.0F);
   public static final Vector3d ONE = new Vector3d((double)1.0F, (double)1.0F, (double)1.0F);
   public static final Vector3d RIGHT;
   public static final Vector3d UP;
   public static final Vector3d FORWARD;
   private final double x;
   private final double y;
   private final double z;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Vector3d(double x, double y, double z) {
      this.x = x;
      this.y = y;
      this.z = z;
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

   public int getFloorX() {
      return GenericMath.floor(this.x);
   }

   public int getFloorY() {
      return GenericMath.floor(this.y);
   }

   public int getFloorZ() {
      return GenericMath.floor(this.z);
   }

   @Nonnull
   public Vector3d add(Vector3d v) {
      return this.add(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3d add(float x, float y, float z) {
      return this.add((double)x, (double)y, (double)z);
   }

   @Nonnull
   public Vector3d add(double x, double y, double z) {
      return from(this.x + x, this.y + y, this.z + z);
   }

   @Nonnull
   public Vector3d sub(Vector3d v) {
      return this.sub(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3d sub(float x, float y, float z) {
      return this.sub((double)x, (double)y, (double)z);
   }

   @Nonnull
   public Vector3d sub(double x, double y, double z) {
      return from(this.x - x, this.y - y, this.z - z);
   }

   @Nonnull
   public Vector3d mul(float a) {
      return this.mul((double)a);
   }

   @Nonnull
   public Vector3d mul(double a) {
      return this.mul(a, a, a);
   }

   @Nonnull
   public Vector3d mul(Vector3d v) {
      return this.mul(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3d mul(float x, float y, float z) {
      return this.mul((double)x, (double)y, (double)z);
   }

   @Nonnull
   public Vector3d mul(double x, double y, double z) {
      return from(this.x * x, this.y * y, this.z * z);
   }

   @Nonnull
   public Vector3d div(float a) {
      return this.div((double)a);
   }

   @Nonnull
   public Vector3d div(double a) {
      return this.div(a, a, a);
   }

   @Nonnull
   public Vector3d div(Vector3d v) {
      return this.div(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3d div(float x, float y, float z) {
      return this.div((double)x, (double)y, (double)z);
   }

   @Nonnull
   public Vector3d div(double x, double y, double z) {
      return from(this.x / x, this.y / y, this.z / z);
   }

   public double dot(Vector3d v) {
      return this.dot(v.x, v.y, v.z);
   }

   public double dot(float x, float y, float z) {
      return this.dot((double)x, (double)y, (double)z);
   }

   public double dot(double x, double y, double z) {
      return this.x * x + this.y * y + this.z * z;
   }

   @Nonnull
   public Vector3d project(Vector3d v) {
      return this.project(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3d project(float x, float y, float z) {
      return this.project((double)x, (double)y, (double)z);
   }

   @Nonnull
   public Vector3d project(double x, double y, double z) {
      double lengthSquared = x * x + y * y + z * z;
      if (Math.abs(lengthSquared) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot project onto the zero vector");
      } else {
         double a = this.dot(x, y, z) / lengthSquared;
         return from(a * x, a * y, a * z);
      }
   }

   @Nonnull
   public Vector3d cross(Vector3d v) {
      return this.cross(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3d cross(float x, float y, float z) {
      return this.cross((double)x, (double)y, (double)z);
   }

   @Nonnull
   public Vector3d cross(double x, double y, double z) {
      return from(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
   }

   @Nonnull
   public Vector3d pow(float pow) {
      return this.pow((double)pow);
   }

   @Nonnull
   public Vector3d pow(double power) {
      return from(Math.pow(this.x, power), Math.pow(this.y, power), Math.pow(this.z, power));
   }

   @Nonnull
   public Vector3d ceil() {
      return from(Math.ceil(this.x), Math.ceil(this.y), Math.ceil(this.z));
   }

   @Nonnull
   public Vector3d floor() {
      return from((float)GenericMath.floor(this.x), (float)GenericMath.floor(this.y), (float)GenericMath.floor(this.z));
   }

   @Nonnull
   public Vector3d round() {
      return from((float)Math.round(this.x), (float)Math.round(this.y), (float)Math.round(this.z));
   }

   @Nonnull
   public Vector3d abs() {
      return from(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
   }

   @Nonnull
   public Vector3d negate() {
      return from(-this.x, -this.y, -this.z);
   }

   @Nonnull
   public Vector3d min(Vector3d v) {
      return this.min(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3d min(float x, float y, float z) {
      return this.min((double)x, (double)y, (double)z);
   }

   @Nonnull
   public Vector3d min(double x, double y, double z) {
      return from(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z));
   }

   @Nonnull
   public Vector3d max(Vector3d v) {
      return this.max(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3d max(float x, float y, float z) {
      return this.max((double)x, (double)y, (double)z);
   }

   @Nonnull
   public Vector3d max(double x, double y, double z) {
      return from(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z));
   }

   public double distanceSquared(Vector3d v) {
      return this.distanceSquared(v.x, v.y, v.z);
   }

   public double distanceSquared(float x, float y, float z) {
      return this.distanceSquared((double)x, (double)y, (double)z);
   }

   public double distanceSquared(double x, double y, double z) {
      double dx = this.x - x;
      double dy = this.y - y;
      double dz = this.z - z;
      return dx * dx + dy * dy + dz * dz;
   }

   public double distance(Vector3d v) {
      return this.distance(v.x, v.y, v.z);
   }

   public double distance(float x, float y, float z) {
      return this.distance((double)x, (double)y, (double)z);
   }

   public double distance(double x, double y, double z) {
      return Math.sqrt(this.distanceSquared(x, y, z));
   }

   @Nonnull
   public Vector3d up() {
      return this.up((double)1.0F);
   }

   @Nonnull
   public Vector3d up(double v) {
      return from(this.x, this.y + v, this.z);
   }

   @Nonnull
   public Vector3d down() {
      return this.down((double)1.0F);
   }

   @Nonnull
   public Vector3d down(double v) {
      return from(this.x, this.y - v, this.z);
   }

   @Nonnull
   public Vector3d north() {
      return this.north((double)1.0F);
   }

   @Nonnull
   public Vector3d north(double v) {
      return from(this.x, this.y, this.z - v);
   }

   @Nonnull
   public Vector3d south() {
      return this.south((double)1.0F);
   }

   @Nonnull
   public Vector3d south(double v) {
      return from(this.x, this.y, this.z + v);
   }

   @Nonnull
   public Vector3d east() {
      return this.east((double)1.0F);
   }

   @Nonnull
   public Vector3d east(double v) {
      return from(this.x + v, this.y, this.z);
   }

   @Nonnull
   public Vector3d west() {
      return this.west((double)1.0F);
   }

   @Nonnull
   public Vector3d west(double v) {
      return from(this.x - v, this.y, this.z);
   }

   public double lengthSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z;
   }

   public double length() {
      return Math.sqrt(this.lengthSquared());
   }

   @Nonnull
   public Vector3d normalize() {
      double length = this.length();
      if (Math.abs(length) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot normalize the zero vector");
      } else {
         return from(this.x / length, this.y / length, this.z / length);
      }
   }

   public int getMinAxis() {
      return this.x < this.y ? (this.x < this.z ? 0 : 2) : (this.y < this.z ? 1 : 2);
   }

   public int getMaxAxis() {
      return this.x < this.y ? (this.y < this.z ? 2 : 1) : (this.x < this.z ? 2 : 0);
   }

   @Nonnull
   public Vector2d toVector2() {
      return Vector2d.from(this);
   }

   @Nonnull
   public Vector2d toVector2(boolean useZ) {
      return Vector2d.from(this.x, useZ ? this.z : this.y);
   }

   @Nonnull
   public Vector4d toVector4() {
      return this.toVector4(0.0F);
   }

   @Nonnull
   public Vector4d toVector4(float w) {
      return this.toVector4((double)w);
   }

   @Nonnull
   public Vector4d toVector4(double w) {
      return Vector4d.from(this, w);
   }

   @Nonnull
   public VectorNd toVectorN() {
      return VectorNd.from(this);
   }

   @Nonnull
   public double[] toArray() {
      return new double[]{this.x, this.y, this.z};
   }

   @Nonnull
   public Vector3i toInt() {
      return Vector3i.from(this.x, this.y, this.z);
   }

   @Nonnull
   public Vector3l toLong() {
      return Vector3l.from(this.x, this.y, this.z);
   }

   @Nonnull
   public Vector3f toFloat() {
      return Vector3f.from(this.x, this.y, this.z);
   }

   @Nonnull
   public Vector3d toDouble() {
      return from(this.x, this.y, this.z);
   }

   public int compareTo(Vector3d v) {
      return (int)Math.signum(this.lengthSquared() - v.lengthSquared());
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Vector3d)) {
         return false;
      } else {
         Vector3d vector3 = (Vector3d)o;
         if (Double.compare(vector3.x, this.x) != 0) {
            return false;
         } else if (Double.compare(vector3.y, this.y) != 0) {
            return false;
         } else {
            return Double.compare(vector3.z, this.z) == 0;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         int result = this.x != (double)0.0F ? Double.hashCode(this.x) : 0;
         result = 31 * result + (this.y != (double)0.0F ? Double.hashCode(this.y) : 0);
         this.hashCode = 31 * result + (this.z != (double)0.0F ? Double.hashCode(this.z) : 0);
         this.hashed = true;
      }

      return this.hashCode;
   }

   @Nonnull
   public Vector3d clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ", " + this.z + ")";
   }

   @Nonnull
   public static Vector3d from(double n) {
      return n == (double)0.0F ? ZERO : new Vector3d(n, n, n);
   }

   @Nonnull
   public static Vector3d from(Vector2d v) {
      return from(v, 0.0F);
   }

   @Nonnull
   public static Vector3d from(Vector2d v, float z) {
      return from(v, (double)z);
   }

   @Nonnull
   public static Vector3d from(Vector2d v, double z) {
      return from(v.getX(), v.getY(), z);
   }

   @Nonnull
   public static Vector3d from(Vector3d v) {
      return from(v.x, v.y, v.z);
   }

   @Nonnull
   public static Vector3d from(Vector4d v) {
      return from(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public static Vector3d from(VectorNd v) {
      return from(v.get(0), v.get(1), v.size() > 2 ? v.get(2) : (double)0.0F);
   }

   @Nonnull
   public static Vector3d from(float x, float y, float z) {
      return from((double)x, (double)y, (double)z);
   }

   @Nonnull
   public static Vector3d from(double x, double y, double z) {
      return x == (double)0.0F && y == (double)0.0F && z == (double)0.0F ? ZERO : new Vector3d(x, y, z);
   }

   @Nonnull
   public static Vector3d createRandomDirection(Random random) {
      return createDirectionRad(random.nextDouble() * (Math.PI * 2D), random.nextDouble() * (Math.PI * 2D));
   }

   @Nonnull
   public static Vector3d createDirectionDeg(float theta, float phi) {
      return createDirectionDeg((double)theta, (double)phi);
   }

   @Nonnull
   public static Vector3d createDirectionDeg(double theta, double phi) {
      return createDirectionRad(Math.toRadians(theta), Math.toRadians(phi));
   }

   @Nonnull
   public static Vector3d createDirectionRad(float theta, float phi) {
      return createDirectionRad((double)theta, (double)phi);
   }

   @Nonnull
   public static Vector3d createDirectionRad(double theta, double phi) {
      double f = (double)TrigMath.sin(phi);
      return from(f * (double)TrigMath.cos(theta), f * (double)TrigMath.sin(theta), (double)TrigMath.cos(phi));
   }

   static {
      RIGHT = UNIT_X;
      UP = UNIT_Y;
      FORWARD = UNIT_Z;
   }
}
