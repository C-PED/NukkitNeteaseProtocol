package com.nukkitx.math.imaginary;

import com.nukkitx.math.GenericMath;
import com.nukkitx.math.TrigMath;
import com.nukkitx.math.vector.Vector2d;
import com.nukkitx.math.vector.Vector3d;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Complexd implements Imaginaryd, Comparable<Complexd>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Complexd ZERO = new Complexd((double)0.0F, (double)0.0F);
   public static final Complexd IDENTITY = new Complexd((double)1.0F, (double)0.0F);
   private final double x;
   private final double y;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Complexd(double x, double y) {
      this.x = x;
      this.y = y;
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   @Nonnull
   public Complexd add(Complexd c) {
      return this.add(c.x, c.y);
   }

   @Nonnull
   public Complexd add(float x, float y) {
      return this.add((double)x, (double)y);
   }

   @Nonnull
   public Complexd add(double x, double y) {
      return from(this.x + x, this.y + y);
   }

   @Nonnull
   public Complexd sub(Complexd c) {
      return this.sub(c.x, c.y);
   }

   @Nonnull
   public Complexd sub(float x, float y) {
      return this.sub((double)x, (double)y);
   }

   @Nonnull
   public Complexd sub(double x, double y) {
      return from(this.x - x, this.y - y);
   }

   @Nonnull
   public Complexd mul(float a) {
      return this.mul((double)a);
   }

   @Nonnull
   public Complexd mul(double a) {
      return from(this.x * a, this.y * a);
   }

   @Nonnull
   public Complexd mul(Complexd c) {
      return this.mul(c.x, c.y);
   }

   @Nonnull
   public Complexd mul(float x, float y) {
      return this.mul((double)x, (double)y);
   }

   @Nonnull
   public Complexd mul(double x, double y) {
      return from(this.x * x - this.y * y, this.x * y + this.y * x);
   }

   @Nonnull
   public Complexd div(float a) {
      return this.div((double)a);
   }

   @Nonnull
   public Complexd div(double a) {
      return from(this.x / a, this.y / a);
   }

   @Nonnull
   public Complexd div(Complexd c) {
      return this.div(c.x, c.y);
   }

   @Nonnull
   public Complexd div(float x, float y) {
      return this.div((double)x, (double)y);
   }

   @Nonnull
   public Complexd div(double x, double y) {
      double d = x * x + y * y;
      return from((this.x * x + this.y * y) / d, (this.y * x - this.x * y) / d);
   }

   public double dot(Complexd c) {
      return this.dot(c.x, c.y);
   }

   public double dot(float x, float y) {
      return this.dot((double)x, (double)y);
   }

   public double dot(double x, double y) {
      return this.x * x + this.y * y;
   }

   @Nonnull
   public Vector2d rotate(Vector2d v) {
      return this.rotate(v.getX(), v.getY());
   }

   @Nonnull
   public Vector2d rotate(float x, float y) {
      return this.rotate((double)x, (double)y);
   }

   @Nonnull
   public Vector2d rotate(double x, double y) {
      double length = this.length();
      if (Math.abs(length) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot rotate by the zero complex");
      } else {
         double nx = this.x / length;
         double ny = this.y / length;
         return Vector2d.from(x * nx - y * ny, y * nx + x * ny);
      }
   }

   @Nonnull
   public Vector2d getDirection() {
      return Vector2d.from(this.x, this.y).normalize();
   }

   public double getAngleRad() {
      return TrigMath.atan2(this.y, this.x);
   }

   public double getAngleDeg() {
      return Math.toDegrees(this.getAngleRad());
   }

   @Nonnull
   public Complexd conjugate() {
      return from(this.x, -this.y);
   }

   @Nonnull
   public Complexd invert() {
      double lengthSquared = this.lengthSquared();
      if (Math.abs(lengthSquared) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot invert a complex of length zero");
      } else {
         return this.conjugate().div(lengthSquared);
      }
   }

   public double lengthSquared() {
      return this.x * this.x + this.y * this.y;
   }

   public double length() {
      return Math.sqrt(this.lengthSquared());
   }

   @Nonnull
   public Complexd normalize() {
      double length = this.length();
      if (Math.abs(length) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot normalize the zero complex");
      } else {
         return from(this.x / length, this.y / length);
      }
   }

   @Nonnull
   public Quaterniond toQuaternion() {
      return this.toQuaternion(Vector3d.UNIT_Z);
   }

   @Nonnull
   public Quaterniond toQuaternion(Vector3d axis) {
      return this.toQuaternion(axis.getX(), axis.getY(), axis.getZ());
   }

   @Nonnull
   public Quaterniond toQuaternion(float x, float y, float z) {
      return this.toQuaternion((double)x, (double)y, (double)z);
   }

   @Nonnull
   public Quaterniond toQuaternion(double x, double y, double z) {
      return Quaterniond.fromAngleRadAxis(this.getAngleRad(), x, y, z);
   }

   @Nonnull
   public Complexf toFloat() {
      return Complexf.from(this.x, this.y);
   }

   @Nonnull
   public Complexd toDouble() {
      return from(this.x, this.y);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Complexd)) {
         return false;
      } else {
         Complexd complex = (Complexd)o;
         if (Double.compare(complex.x, this.x) != 0) {
            return false;
         } else {
            return Double.compare(complex.y, this.y) == 0;
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

   public int compareTo(Complexd c) {
      return (int)Math.signum(this.lengthSquared() - c.lengthSquared());
   }

   @Nonnull
   public Complexd clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ")";
   }

   @Nonnull
   public static Complexd fromReal(double x) {
      return x == (double)0.0F ? ZERO : new Complexd(x, (double)0.0F);
   }

   @Nonnull
   public static Complexd fromImaginary(double y) {
      return y == (double)0.0F ? ZERO : new Complexd((double)0.0F, y);
   }

   @Nonnull
   public static Complexd from(Complexd c) {
      return from(c.x, c.y);
   }

   @Nonnull
   public static Complexd from(float x, float y) {
      return from((double)x, (double)y);
   }

   @Nonnull
   public static Complexd from(double x, double y) {
      return x == (double)0.0F && y == (double)0.0F ? ZERO : new Complexd(x, y);
   }

   @Nonnull
   public static Complexd fromRotationTo(Vector2d from, Vector2d to) {
      return fromAngleRad(TrigMath.acos(from.dot(to) / (from.length() * to.length())));
   }

   @Nonnull
   public static Complexd fromRotationTo(Vector3d from, Vector3d to) {
      return fromAngleRad(TrigMath.acos(from.dot(to) / (from.length() * to.length())));
   }

   @Nonnull
   public static Complexd fromAngleDeg(float angle) {
      return fromAngleRad(Math.toRadians((double)angle));
   }

   @Nonnull
   public static Complexd fromAngleRad(float angle) {
      return fromAngleRad((double)angle);
   }

   @Nonnull
   public static Complexd fromAngleDeg(double angle) {
      return fromAngleRad(Math.toRadians(angle));
   }

   @Nonnull
   public static Complexd fromAngleRad(double angle) {
      return from(TrigMath.cos(angle), TrigMath.sin(angle));
   }
}
