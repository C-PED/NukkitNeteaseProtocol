package com.nukkitx.math.imaginary;

import com.nukkitx.math.GenericMath;
import com.nukkitx.math.TrigMath;
import com.nukkitx.math.vector.Vector2f;
import com.nukkitx.math.vector.Vector3f;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Complexf implements Imaginaryf, Comparable<Complexf>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Complexf ZERO = new Complexf(0.0F, 0.0F);
   public static final Complexf IDENTITY = new Complexf(1.0F, 0.0F);
   private final float x;
   private final float y;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Complexf(float x, float y) {
      this.x = x;
      this.y = y;
   }

   public float getX() {
      return this.x;
   }

   public float getY() {
      return this.y;
   }

   @Nonnull
   public Complexf add(Complexf c) {
      return this.add(c.x, c.y);
   }

   @Nonnull
   public Complexf add(double x, double y) {
      return this.add((float)x, (float)y);
   }

   @Nonnull
   public Complexf add(float x, float y) {
      return from(this.x + x, this.y + y);
   }

   @Nonnull
   public Complexf sub(Complexf c) {
      return this.sub(c.x, c.y);
   }

   @Nonnull
   public Complexf sub(double x, double y) {
      return this.sub((float)x, (float)y);
   }

   @Nonnull
   public Complexf sub(float x, float y) {
      return from(this.x - x, this.y - y);
   }

   @Nonnull
   public Complexf mul(double a) {
      return this.mul((float)a);
   }

   @Nonnull
   public Complexf mul(float a) {
      return from(this.x * a, this.y * a);
   }

   @Nonnull
   public Complexf mul(Complexf c) {
      return this.mul(c.x, c.y);
   }

   @Nonnull
   public Complexf mul(double x, double y) {
      return this.mul((float)x, (float)y);
   }

   @Nonnull
   public Complexf mul(float x, float y) {
      return from(this.x * x - this.y * y, this.x * y + this.y * x);
   }

   @Nonnull
   public Complexf div(double a) {
      return this.div((float)a);
   }

   @Nonnull
   public Complexf div(float a) {
      return from(this.x / a, this.y / a);
   }

   @Nonnull
   public Complexf div(Complexf c) {
      return this.div(c.x, c.y);
   }

   @Nonnull
   public Complexf div(double x, double y) {
      return this.div((float)x, (float)y);
   }

   @Nonnull
   public Complexf div(float x, float y) {
      float d = x * x + y * y;
      return from((this.x * x + this.y * y) / d, (this.y * x - this.x * y) / d);
   }

   public float dot(Complexf c) {
      return this.dot(c.x, c.y);
   }

   public float dot(double x, double y) {
      return this.dot((float)x, (float)y);
   }

   public float dot(float x, float y) {
      return this.x * x + this.y * y;
   }

   @Nonnull
   public Vector2f rotate(Vector2f v) {
      return this.rotate(v.getX(), v.getY());
   }

   @Nonnull
   public Vector2f rotate(double x, double y) {
      return this.rotate((float)x, (float)y);
   }

   @Nonnull
   public Vector2f rotate(float x, float y) {
      float length = this.length();
      if (Math.abs(length) < GenericMath.FLT_EPSILON) {
         throw new ArithmeticException("Cannot rotate by the zero complex");
      } else {
         float nx = this.x / length;
         float ny = this.y / length;
         return Vector2f.from(x * nx - y * ny, y * nx + x * ny);
      }
   }

   @Nonnull
   public Vector2f getDirection() {
      return Vector2f.from(this.x, this.y).normalize();
   }

   public float getAngleRad() {
      return (float)TrigMath.atan2((double)this.y, (double)this.x);
   }

   public float getAngleDeg() {
      return (float)Math.toDegrees((double)this.getAngleRad());
   }

   @Nonnull
   public Complexf conjugate() {
      return from(this.x, -this.y);
   }

   @Nonnull
   public Complexf invert() {
      float lengthSquared = this.lengthSquared();
      if (Math.abs(lengthSquared) < GenericMath.FLT_EPSILON) {
         throw new ArithmeticException("Cannot invert a complex of length zero");
      } else {
         return this.conjugate().div(lengthSquared);
      }
   }

   public float lengthSquared() {
      return this.x * this.x + this.y * this.y;
   }

   public float length() {
      return (float)Math.sqrt((double)this.lengthSquared());
   }

   @Nonnull
   public Complexf normalize() {
      float length = this.length();
      if (Math.abs(length) < GenericMath.FLT_EPSILON) {
         throw new ArithmeticException("Cannot normalize the zero complex");
      } else {
         return from(this.x / length, this.y / length);
      }
   }

   @Nonnull
   public Quaternionf toQuaternion() {
      return this.toQuaternion(Vector3f.UNIT_Z);
   }

   @Nonnull
   public Quaternionf toQuaternion(Vector3f axis) {
      return this.toQuaternion(axis.getX(), axis.getY(), axis.getZ());
   }

   @Nonnull
   public Quaternionf toQuaternion(double x, double y, double z) {
      return this.toQuaternion((float)x, (float)y, (float)z);
   }

   @Nonnull
   public Quaternionf toQuaternion(float x, float y, float z) {
      return Quaternionf.fromAngleRadAxis(this.getAngleRad(), x, y, z);
   }

   @Nonnull
   public Complexf toFloat() {
      return from(this.x, this.y);
   }

   @Nonnull
   public Complexd toDouble() {
      return Complexd.from(this.x, this.y);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Complexf)) {
         return false;
      } else {
         Complexf complex = (Complexf)o;
         if (Float.compare(complex.x, this.x) != 0) {
            return false;
         } else {
            return Float.compare(complex.y, this.y) == 0;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         int result = this.x != 0.0F ? Float.hashCode(this.x) : 0;
         this.hashCode = 31 * result + (this.y != 0.0F ? Float.hashCode(this.y) : 0);
         this.hashed = true;
      }

      return this.hashCode;
   }

   public int compareTo(Complexf c) {
      return (int)Math.signum(this.lengthSquared() - c.lengthSquared());
   }

   @Nonnull
   public Complexf clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ")";
   }

   @Nonnull
   public static Complexf fromReal(float x) {
      return x == 0.0F ? ZERO : new Complexf(x, 0.0F);
   }

   @Nonnull
   public static Complexf fromImaginary(float y) {
      return y == 0.0F ? ZERO : new Complexf(0.0F, y);
   }

   @Nonnull
   public static Complexf from(Complexf c) {
      return from(c.x, c.y);
   }

   @Nonnull
   public static Complexf from(double x, double y) {
      return from((float)x, (float)y);
   }

   @Nonnull
   public static Complexf from(float x, float y) {
      return x == 0.0F && y == 0.0F ? ZERO : new Complexf(x, y);
   }

   @Nonnull
   public static Complexf fromRotationTo(Vector2f from, Vector2f to) {
      return fromAngleRad(TrigMath.acos((double)(from.dot(to) / (from.length() * to.length()))));
   }

   @Nonnull
   public static Complexf fromRotationTo(Vector3f from, Vector3f to) {
      return fromAngleRad(TrigMath.acos((double)(from.dot(to) / (from.length() * to.length()))));
   }

   @Nonnull
   public static Complexf fromAngleDeg(double angle) {
      return fromAngleRad(Math.toRadians(angle));
   }

   @Nonnull
   public static Complexf fromAngleRad(double angle) {
      return fromAngleRad((float)angle);
   }

   @Nonnull
   public static Complexf fromAngleDeg(float angle) {
      return fromAngleRad((float)Math.toRadians((double)angle));
   }

   @Nonnull
   public static Complexf fromAngleRad(float angle) {
      return from(TrigMath.cos((double)angle), TrigMath.sin((double)angle));
   }
}
