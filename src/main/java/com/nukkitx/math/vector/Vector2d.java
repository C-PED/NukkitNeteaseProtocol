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
public class Vector2d implements Vectord, Comparable<Vector2d>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Vector2d ZERO = new Vector2d((double)0.0F, (double)0.0F);
   public static final Vector2d UNIT_X = new Vector2d((double)1.0F, (double)0.0F);
   public static final Vector2d UNIT_Y = new Vector2d((double)0.0F, (double)1.0F);
   public static final Vector2d ONE = new Vector2d((double)1.0F, (double)1.0F);
   private final double x;
   private final double y;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Vector2d(double x, double y) {
      this.x = x;
      this.y = y;
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public int getFloorX() {
      return GenericMath.floor(this.x);
   }

   public int getFloorY() {
      return GenericMath.floor(this.y);
   }

   @Nonnull
   public Vector2d add(Vector2d v) {
      return this.add(v.x, v.y);
   }

   @Nonnull
   public Vector2d add(float x, float y) {
      return this.add((double)x, (double)y);
   }

   @Nonnull
   public Vector2d add(double x, double y) {
      return from(this.x + x, this.y + y);
   }

   @Nonnull
   public Vector2d sub(Vector2d v) {
      return this.sub(v.x, v.y);
   }

   @Nonnull
   public Vector2d sub(float x, float y) {
      return this.sub((double)x, (double)y);
   }

   @Nonnull
   public Vector2d sub(double x, double y) {
      return from(this.x - x, this.y - y);
   }

   @Nonnull
   public Vector2d mul(float a) {
      return this.mul((double)a);
   }

   @Nonnull
   public Vector2d mul(double a) {
      return this.mul(a, a);
   }

   @Nonnull
   public Vector2d mul(Vector2d v) {
      return this.mul(v.x, v.y);
   }

   @Nonnull
   public Vector2d mul(float x, float y) {
      return this.mul((double)x, (double)y);
   }

   @Nonnull
   public Vector2d mul(double x, double y) {
      return from(this.x * x, this.y * y);
   }

   @Nonnull
   public Vector2d div(float a) {
      return this.div((double)a);
   }

   @Nonnull
   public Vector2d div(double a) {
      return this.div(a, a);
   }

   @Nonnull
   public Vector2d div(Vector2d v) {
      return this.div(v.x, v.y);
   }

   @Nonnull
   public Vector2d div(float x, float y) {
      return this.div((double)x, (double)y);
   }

   @Nonnull
   public Vector2d div(double x, double y) {
      return from(this.x / x, this.y / y);
   }

   public double dot(Vector2d v) {
      return this.dot(v.x, v.y);
   }

   public double dot(float x, float y) {
      return this.dot((double)x, (double)y);
   }

   public double dot(double x, double y) {
      return this.x * x + this.y * y;
   }

   @Nonnull
   public Vector2d project(Vector2d v) {
      return this.project(v.x, v.y);
   }

   @Nonnull
   public Vector2d project(float x, float y) {
      return this.project((double)x, (double)y);
   }

   @Nonnull
   public Vector2d project(double x, double y) {
      double lengthSquared = x * x + y * y;
      if (Math.abs(lengthSquared) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot project onto the zero vector");
      } else {
         double a = this.dot(x, y) / lengthSquared;
         return from(a * x, a * y);
      }
   }

   @Nonnull
   public Vector2d pow(float pow) {
      return this.pow((double)pow);
   }

   @Nonnull
   public Vector2d pow(double power) {
      return from(Math.pow(this.x, power), Math.pow(this.y, power));
   }

   @Nonnull
   public Vector2d ceil() {
      return from(Math.ceil(this.x), Math.ceil(this.y));
   }

   @Nonnull
   public Vector2d floor() {
      return from((float)GenericMath.floor(this.x), (float)GenericMath.floor(this.y));
   }

   @Nonnull
   public Vector2d round() {
      return from((float)Math.round(this.x), (float)Math.round(this.y));
   }

   @Nonnull
   public Vector2d abs() {
      return from(Math.abs(this.x), Math.abs(this.y));
   }

   @Nonnull
   public Vector2d negate() {
      return from(-this.x, -this.y);
   }

   @Nonnull
   public Vector2d min(Vector2d v) {
      return this.min(v.x, v.y);
   }

   @Nonnull
   public Vector2d min(float x, float y) {
      return this.min((double)x, (double)y);
   }

   @Nonnull
   public Vector2d min(double x, double y) {
      return from(Math.min(this.x, x), Math.min(this.y, y));
   }

   @Nonnull
   public Vector2d max(Vector2d v) {
      return this.max(v.x, v.y);
   }

   @Nonnull
   public Vector2d max(float x, float y) {
      return this.max((double)x, (double)y);
   }

   @Nonnull
   public Vector2d max(double x, double y) {
      return from(Math.max(this.x, x), Math.max(this.y, y));
   }

   public double distanceSquared(Vector2d v) {
      return this.distanceSquared(v.x, v.y);
   }

   public double distanceSquared(float x, float y) {
      return this.distanceSquared((double)x, (double)y);
   }

   public double distanceSquared(double x, double y) {
      double dx = this.x - x;
      double dy = this.y - y;
      return dx * dx + dy * dy;
   }

   public double distance(Vector2d v) {
      return this.distance(v.x, v.y);
   }

   public double distance(float x, float y) {
      return this.distance((double)x, (double)y);
   }

   public double distance(double x, double y) {
      return Math.sqrt(this.distanceSquared(x, y));
   }

   @Nonnull
   public Vector2d north() {
      return this.north((double)1.0F);
   }

   @Nonnull
   public Vector2d north(double v) {
      return from(this.x, this.y - v);
   }

   @Nonnull
   public Vector2d south() {
      return this.south((double)1.0F);
   }

   @Nonnull
   public Vector2d south(double v) {
      return from(this.x, this.y + v);
   }

   @Nonnull
   public Vector2d east() {
      return this.east((double)1.0F);
   }

   @Nonnull
   public Vector2d east(double v) {
      return from(this.x + v, this.y);
   }

   @Nonnull
   public Vector2d west() {
      return this.west((double)1.0F);
   }

   @Nonnull
   public Vector2d west(double v) {
      return from(this.x - v, this.y);
   }

   public double lengthSquared() {
      return this.x * this.x + this.y * this.y;
   }

   public double length() {
      return Math.sqrt(this.lengthSquared());
   }

   @Nonnull
   public Vector2d normalize() {
      double length = this.length();
      if (Math.abs(length) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot normalize the zero vector");
      } else {
         return from(this.x / length, this.y / length);
      }
   }

   public int getMinAxis() {
      return this.x < this.y ? 0 : 1;
   }

   public int getMaxAxis() {
      return this.x > this.y ? 0 : 1;
   }

   @Nonnull
   public Vector3d toVector3() {
      return this.toVector3(0.0F);
   }

   @Nonnull
   public Vector3d toVector3(float z) {
      return this.toVector3((double)z);
   }

   @Nonnull
   public Vector3d toVector3(double z) {
      return Vector3d.from(this, z);
   }

   @Nonnull
   public Vector4d toVector4() {
      return this.toVector4(0.0F, 0.0F);
   }

   @Nonnull
   public Vector4d toVector4(float z, float w) {
      return this.toVector4((double)z, (double)w);
   }

   @Nonnull
   public Vector4d toVector4(double z, double w) {
      return Vector4d.from(this, z, w);
   }

   @Nonnull
   public VectorNd toVectorN() {
      return VectorNd.from(this);
   }

   @Nonnull
   public double[] toArray() {
      return new double[]{this.x, this.y};
   }

   @Nonnull
   public Vector2i toInt() {
      return Vector2i.from(this.x, this.y);
   }

   @Nonnull
   public Vector2l toLong() {
      return Vector2l.from(this.x, this.y);
   }

   @Nonnull
   public Vector2f toFloat() {
      return Vector2f.from(this.x, this.y);
   }

   @Nonnull
   public Vector2d toDouble() {
      return from(this.x, this.y);
   }

   public int compareTo(Vector2d v) {
      return (int)Math.signum(this.lengthSquared() - v.lengthSquared());
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Vector2d)) {
         return false;
      } else {
         Vector2d vector2 = (Vector2d)o;
         if (Double.compare(vector2.x, this.x) != 0) {
            return false;
         } else {
            return Double.compare(vector2.y, this.y) == 0;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         int result = this.x != (double)0.0F ? Double.hashCode(this.x) : 0;
         this.hashCode = 31 * result + (this.y != (double)0.0F ? Double.hashCode(this.y) : 0);
         this.hashed = true;
      }

      return this.hashCode;
   }

   @Nonnull
   public Vector2d clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ")";
   }

   @Nonnull
   public static Vector2d from(double n) {
      return n == (double)0.0F ? ZERO : new Vector2d(n, n);
   }

   @Nonnull
   public static Vector2d from(Vector2d v) {
      return from(v.x, v.y);
   }

   @Nonnull
   public static Vector2d from(Vector3d v) {
      return from(v.getX(), v.getY());
   }

   @Nonnull
   public static Vector2d from(Vector4d v) {
      return from(v.getX(), v.getY());
   }

   @Nonnull
   public static Vector2d from(VectorNd v) {
      return from(v.get(0), v.get(1));
   }

   @Nonnull
   public static Vector2d from(float x, float y) {
      return from((double)x, (double)y);
   }

   @Nonnull
   public static Vector2d from(double x, double y) {
      return x == (double)0.0F && y == (double)0.0F ? ZERO : new Vector2d(x, y);
   }

   @Nonnull
   public static Vector2d createRandomDirection(Random random) {
      return createDirectionRad(random.nextDouble() * (Math.PI * 2D));
   }

   @Nonnull
   public static Vector2d createDirectionDeg(float angle) {
      return createDirectionDeg((double)angle);
   }

   @Nonnull
   public static Vector2d createDirectionDeg(double angle) {
      return createDirectionRad(Math.toRadians(angle));
   }

   @Nonnull
   public static Vector2d createDirectionRad(float angle) {
      return createDirectionRad((double)angle);
   }

   @Nonnull
   public static Vector2d createDirectionRad(double angle) {
      return from(TrigMath.cos(angle), TrigMath.sin(angle));
   }
}
