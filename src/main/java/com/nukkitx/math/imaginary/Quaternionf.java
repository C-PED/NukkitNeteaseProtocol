package com.nukkitx.math.imaginary;

import com.nukkitx.math.GenericMath;
import com.nukkitx.math.TrigMath;
import com.nukkitx.math.matrix.Matrix3f;
import com.nukkitx.math.vector.Vector3f;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Quaternionf implements Imaginaryf, Comparable<Quaternionf>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Quaternionf ZERO = new Quaternionf(0.0F, 0.0F, 0.0F, 0.0F);
   public static final Quaternionf IDENTITY = new Quaternionf(0.0F, 0.0F, 0.0F, 1.0F);
   private final float x;
   private final float y;
   private final float z;
   private final float w;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Quaternionf(float x, float y, float z, float w) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.w = w;
   }

   public float getX() {
      return this.x;
   }

   public float getY() {
      return this.y;
   }

   public float getZ() {
      return this.z;
   }

   public float getW() {
      return this.w;
   }

   @Nonnull
   public Quaternionf add(Quaternionf q) {
      return this.add(q.x, q.y, q.z, q.w);
   }

   @Nonnull
   public Quaternionf add(double x, double y, double z, double w) {
      return this.add((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public Quaternionf add(float x, float y, float z, float w) {
      return from(this.x + x, this.y + y, this.z + z, this.w + w);
   }

   @Nonnull
   public Quaternionf sub(Quaternionf q) {
      return this.sub(q.x, q.y, q.z, q.w);
   }

   @Nonnull
   public Quaternionf sub(double x, double y, double z, double w) {
      return this.sub((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public Quaternionf sub(float x, float y, float z, float w) {
      return from(this.x - x, this.y - y, this.z - z, this.w - w);
   }

   @Nonnull
   public Quaternionf mul(double a) {
      return this.mul((float)a);
   }

   @Nonnull
   public Quaternionf mul(float a) {
      return from(this.x * a, this.y * a, this.z * a, this.w * a);
   }

   @Nonnull
   public Quaternionf mul(Quaternionf q) {
      return this.mul(q.x, q.y, q.z, q.w);
   }

   @Nonnull
   public Quaternionf mul(double x, double y, double z, double w) {
      return this.mul((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public Quaternionf mul(float x, float y, float z, float w) {
      return from(this.w * x + this.x * w + this.y * z - this.z * y, this.w * y + this.y * w + this.z * x - this.x * z, this.w * z + this.z * w + this.x * y - this.y * x, this.w * w - this.x * x - this.y * y - this.z * z);
   }

   @Nonnull
   public Quaternionf div(double a) {
      return this.div((float)a);
   }

   @Nonnull
   public Quaternionf div(float a) {
      return from(this.x / a, this.y / a, this.z / a, this.w / a);
   }

   @Nonnull
   public Quaternionf div(Quaternionf q) {
      return this.div(q.x, q.y, q.z, q.w);
   }

   @Nonnull
   public Quaternionf div(double x, double y, double z, double w) {
      return this.div((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public Quaternionf div(float x, float y, float z, float w) {
      float d = x * x + y * y + z * z + w * w;
      return from((this.x * w - this.w * x - this.z * y + this.y * z) / d, (this.y * w + this.z * x - this.w * y - this.x * z) / d, (this.z * w - this.y * x + this.x * y - this.w * z) / d, (this.w * w + this.x * x + this.y * y + this.z * z) / d);
   }

   public float dot(Quaternionf q) {
      return this.dot(q.x, q.y, q.z, q.w);
   }

   public float dot(double x, double y, double z, double w) {
      return this.dot((float)x, (float)y, (float)z, (float)w);
   }

   public float dot(float x, float y, float z, float w) {
      return this.x * x + this.y * y + this.z * z + this.w * w;
   }

   @Nonnull
   public Vector3f rotate(Vector3f v) {
      return this.rotate(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public Vector3f rotate(double x, double y, double z) {
      return this.rotate((float)x, (float)y, (float)z);
   }

   @Nonnull
   public Vector3f rotate(float x, float y, float z) {
      float length = this.length();
      if (Math.abs(length) < GenericMath.FLT_EPSILON) {
         throw new ArithmeticException("Cannot rotate by the zero quaternion");
      } else {
         float nx = this.x / length;
         float ny = this.y / length;
         float nz = this.z / length;
         float nw = this.w / length;
         float px = nw * x + ny * z - nz * y;
         float py = nw * y + nz * x - nx * z;
         float pz = nw * z + nx * y - ny * x;
         float pw = -nx * x - ny * y - nz * z;
         return Vector3f.from(pw * -nx + px * nw - py * nz + pz * ny, pw * -ny + py * nw - pz * nx + px * nz, pw * -nz + pz * nw - px * ny + py * nx);
      }
   }

   @Nonnull
   public Vector3f getDirection() {
      return this.rotate(Vector3f.FORWARD);
   }

   @Nonnull
   public Vector3f getAxis() {
      float q = (float)Math.sqrt((double)(1.0F - this.w * this.w));
      return Vector3f.from(this.x / q, this.y / q, this.z / q);
   }

   @Nonnull
   public Vector3f getAxesAnglesDeg() {
      return this.getAxesAnglesRad().mul((180D / Math.PI));
   }

   @Nonnull
   public Vector3f getAxesAnglesRad() {
      double test = (double)(this.w * this.x - this.y * this.z);
      double roll;
      double pitch;
      double yaw;
      if (Math.abs(test) < 0.4999) {
         roll = TrigMath.atan2((double)(2.0F * (this.w * this.z + this.x * this.y)), (double)(1.0F - 2.0F * (this.x * this.x + this.z * this.z)));
         pitch = TrigMath.asin((double)2.0F * test);
         yaw = TrigMath.atan2((double)(2.0F * (this.w * this.y + this.z * this.x)), (double)(1.0F - 2.0F * (this.x * this.x + this.y * this.y)));
      } else {
         int sign = test < (double)0.0F ? -1 : 1;
         roll = (double)0.0F;
         pitch = (double)sign * Math.PI / (double)2.0F;
         yaw = (double)(-sign * 2) * TrigMath.atan2((double)this.z, (double)this.w);
      }

      return Vector3f.from(pitch, yaw, roll);
   }

   @Nonnull
   public Quaternionf conjugate() {
      return from(-this.x, -this.y, -this.z, this.w);
   }

   @Nonnull
   public Quaternionf invert() {
      float lengthSquared = this.lengthSquared();
      if (Math.abs(lengthSquared) < GenericMath.FLT_EPSILON) {
         throw new ArithmeticException("Cannot invert a quaternion of length zero");
      } else {
         return this.conjugate().div(lengthSquared);
      }
   }

   public float lengthSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
   }

   public float length() {
      return (float)Math.sqrt((double)this.lengthSquared());
   }

   @Nonnull
   public Quaternionf normalize() {
      float length = this.length();
      if (Math.abs(length) < GenericMath.FLT_EPSILON) {
         throw new ArithmeticException("Cannot normalize the zero quaternion");
      } else {
         return from(this.x / length, this.y / length, this.z / length, this.w / length);
      }
   }

   @Nonnull
   public Complexf toComplex() {
      float w2 = this.w * this.w;
      return Complexf.from(2.0F * w2 - 1.0F, 2.0F * this.w * (float)Math.sqrt((double)(1.0F - w2)));
   }

   @Nonnull
   public Quaternionf toFloat() {
      return from(this.x, this.y, this.z, this.w);
   }

   @Nonnull
   public Quaterniond toDouble() {
      return Quaterniond.from(this.x, this.y, this.z, this.w);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Quaternionf)) {
         return false;
      } else {
         Quaternionf quaternion = (Quaternionf)o;
         if (Float.compare(quaternion.w, this.w) != 0) {
            return false;
         } else if (Float.compare(quaternion.x, this.x) != 0) {
            return false;
         } else if (Float.compare(quaternion.y, this.y) != 0) {
            return false;
         } else {
            return Float.compare(quaternion.z, this.z) == 0;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         int result = this.x != 0.0F ? Float.hashCode(this.x) : 0;
         result = 31 * result + (this.y != 0.0F ? Float.hashCode(this.y) : 0);
         result = 31 * result + (this.z != 0.0F ? Float.hashCode(this.z) : 0);
         this.hashCode = 31 * result + (this.w != 0.0F ? Float.hashCode(this.w) : 0);
         this.hashed = true;
      }

      return this.hashCode;
   }

   public int compareTo(Quaternionf q) {
      return (int)Math.signum(this.lengthSquared() - q.lengthSquared());
   }

   @Nonnull
   public Quaternionf clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
   }

   @Nonnull
   public static Quaternionf fromReal(float w) {
      return w == 0.0F ? ZERO : from(0.0F, 0.0F, 0.0F, w);
   }

   @Nonnull
   public static Quaternionf fromImaginary(float x, float y, float z) {
      return x == 0.0F && y == 0.0F && z == 0.0F ? ZERO : new Quaternionf(x, y, z, 0.0F);
   }

   public static Quaternionf from(Quaternionf q) {
      return from(q.x, q.y, q.z, q.w);
   }

   @Nonnull
   public static Quaternionf from(double x, double y, double z, double w) {
      return from((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public static Quaternionf from(float x, float y, float z, float w) {
      return x == 0.0F && y == 0.0F && z == 0.0F && w == 0.0F ? ZERO : new Quaternionf(x, y, z, w);
   }

   @Nonnull
   public static Quaternionf fromAxesAnglesDeg(double pitch, double yaw, double roll) {
      return fromAxesAnglesDeg((float)pitch, (float)yaw, (float)roll);
   }

   @Nonnull
   public static Quaternionf fromAxesAnglesRad(double pitch, double yaw, double roll) {
      return fromAxesAnglesRad((float)pitch, (float)yaw, (float)roll);
   }

   @Nonnull
   public static Quaternionf fromAxesAnglesDeg(float pitch, float yaw, float roll) {
      return fromAngleDegAxis(yaw, Vector3f.UNIT_Y).mul(fromAngleDegAxis(pitch, Vector3f.UNIT_X)).mul(fromAngleDegAxis(roll, Vector3f.UNIT_Z));
   }

   @Nonnull
   public static Quaternionf fromAxesAnglesRad(float pitch, float yaw, float roll) {
      return fromAngleRadAxis(yaw, Vector3f.UNIT_Y).mul(fromAngleRadAxis(pitch, Vector3f.UNIT_X)).mul(fromAngleRadAxis(roll, Vector3f.UNIT_Z));
   }

   @Nonnull
   public static Quaternionf fromRotationTo(Vector3f from, Vector3f to) {
      return fromAngleRadAxis(TrigMath.acos((double)(from.dot(to) / (from.length() * to.length()))), from.cross(to));
   }

   @Nonnull
   public static Quaternionf fromAngleDegAxis(double angle, Vector3f axis) {
      return fromAngleRadAxis(Math.toRadians(angle), axis);
   }

   @Nonnull
   public static Quaternionf fromAngleRadAxis(double angle, Vector3f axis) {
      return fromAngleRadAxis((float)angle, axis);
   }

   @Nonnull
   public static Quaternionf fromAngleDegAxis(float angle, Vector3f axis) {
      return fromAngleRadAxis((float)Math.toRadians((double)angle), axis);
   }

   @Nonnull
   public static Quaternionf fromAngleRadAxis(float angle, Vector3f axis) {
      return fromAngleRadAxis(angle, axis.getX(), axis.getY(), axis.getZ());
   }

   @Nonnull
   public static Quaternionf fromAngleDegAxis(double angle, double x, double y, double z) {
      return fromAngleRadAxis(Math.toRadians(angle), x, y, z);
   }

   @Nonnull
   public static Quaternionf fromAngleRadAxis(double angle, double x, double y, double z) {
      return fromAngleRadAxis((float)angle, (float)x, (float)y, (float)z);
   }

   @Nonnull
   public static Quaternionf fromAngleDegAxis(float angle, float x, float y, float z) {
      return fromAngleRadAxis((float)Math.toRadians((double)angle), x, y, z);
   }

   @Nonnull
   public static Quaternionf fromAngleRadAxis(float angle, float x, float y, float z) {
      float halfAngle = angle / 2.0F;
      float q = TrigMath.sin((double)halfAngle) / (float)Math.sqrt((double)(x * x + y * y + z * z));
      return from(x * q, y * q, z * q, TrigMath.cos((double)halfAngle));
   }

   @Nonnull
   public static Quaternionf fromRotationMatrix(Matrix3f matrix) {
      float trace = matrix.trace();
      if (trace < 0.0F) {
         if (matrix.get(1, 1) > matrix.get(0, 0)) {
            if (matrix.get(2, 2) > matrix.get(1, 1)) {
               float r = (float)Math.sqrt((double)(matrix.get(2, 2) - matrix.get(0, 0) - matrix.get(1, 1) + 1.0F));
               float s = 0.5F / r;
               return from((matrix.get(2, 0) + matrix.get(0, 2)) * s, (matrix.get(1, 2) + matrix.get(2, 1)) * s, 0.5F * r, (matrix.get(1, 0) - matrix.get(0, 1)) * s);
            } else {
               float r = (float)Math.sqrt((double)(matrix.get(1, 1) - matrix.get(2, 2) - matrix.get(0, 0) + 1.0F));
               float s = 0.5F / r;
               return from((matrix.get(0, 1) + matrix.get(1, 0)) * s, 0.5F * r, (matrix.get(1, 2) + matrix.get(2, 1)) * s, (matrix.get(0, 2) - matrix.get(2, 0)) * s);
            }
         } else if (matrix.get(2, 2) > matrix.get(0, 0)) {
            float r = (float)Math.sqrt((double)(matrix.get(2, 2) - matrix.get(0, 0) - matrix.get(1, 1) + 1.0F));
            float s = 0.5F / r;
            return from((matrix.get(2, 0) + matrix.get(0, 2)) * s, (matrix.get(1, 2) + matrix.get(2, 1)) * s, 0.5F * r, (matrix.get(1, 0) - matrix.get(0, 1)) * s);
         } else {
            float r = (float)Math.sqrt((double)(matrix.get(0, 0) - matrix.get(1, 1) - matrix.get(2, 2) + 1.0F));
            float s = 0.5F / r;
            return from(0.5F * r, (matrix.get(0, 1) + matrix.get(1, 0)) * s, (matrix.get(2, 0) - matrix.get(0, 2)) * s, (matrix.get(2, 1) - matrix.get(1, 2)) * s);
         }
      } else {
         float r = (float)Math.sqrt((double)(trace + 1.0F));
         float s = 0.5F / r;
         return from((matrix.get(2, 1) - matrix.get(1, 2)) * s, (matrix.get(0, 2) - matrix.get(2, 0)) * s, (matrix.get(1, 0) - matrix.get(0, 1)) * s, 0.5F * r);
      }
   }
}
