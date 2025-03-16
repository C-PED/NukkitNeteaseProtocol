package com.nukkitx.math.imaginary;

import com.nukkitx.math.GenericMath;
import com.nukkitx.math.TrigMath;
import com.nukkitx.math.matrix.Matrix3d;
import com.nukkitx.math.vector.Vector3d;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Quaterniond implements Imaginaryd, Comparable<Quaterniond>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Quaterniond ZERO = new Quaterniond((double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F);
   public static final Quaterniond IDENTITY = new Quaterniond((double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F);
   private final double x;
   private final double y;
   private final double z;
   private final double w;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Quaterniond(double x, double y, double z, double w) {
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

   @Nonnull
   public Quaterniond add(Quaterniond q) {
      return this.add(q.x, q.y, q.z, q.w);
   }

   @Nonnull
   public Quaterniond add(float x, float y, float z, float w) {
      return this.add((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public Quaterniond add(double x, double y, double z, double w) {
      return from(this.x + x, this.y + y, this.z + z, this.w + w);
   }

   @Nonnull
   public Quaterniond sub(Quaterniond q) {
      return this.sub(q.x, q.y, q.z, q.w);
   }

   @Nonnull
   public Quaterniond sub(float x, float y, float z, float w) {
      return this.sub((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public Quaterniond sub(double x, double y, double z, double w) {
      return from(this.x - x, this.y - y, this.z - z, this.w - w);
   }

   @Nonnull
   public Quaterniond mul(float a) {
      return this.mul((double)a);
   }

   @Nonnull
   public Quaterniond mul(double a) {
      return from(this.x * a, this.y * a, this.z * a, this.w * a);
   }

   @Nonnull
   public Quaterniond mul(Quaterniond q) {
      return this.mul(q.x, q.y, q.z, q.w);
   }

   @Nonnull
   public Quaterniond mul(float x, float y, float z, float w) {
      return this.mul((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public Quaterniond mul(double x, double y, double z, double w) {
      return from(this.w * x + this.x * w + this.y * z - this.z * y, this.w * y + this.y * w + this.z * x - this.x * z, this.w * z + this.z * w + this.x * y - this.y * x, this.w * w - this.x * x - this.y * y - this.z * z);
   }

   @Nonnull
   public Quaterniond div(float a) {
      return this.div((double)a);
   }

   @Nonnull
   public Quaterniond div(double a) {
      return from(this.x / a, this.y / a, this.z / a, this.w / a);
   }

   @Nonnull
   public Quaterniond div(Quaterniond q) {
      return this.div(q.x, q.y, q.z, q.w);
   }

   @Nonnull
   public Quaterniond div(float x, float y, float z, float w) {
      return this.div((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public Quaterniond div(double x, double y, double z, double w) {
      double d = x * x + y * y + z * z + w * w;
      return from((this.x * w - this.w * x - this.z * y + this.y * z) / d, (this.y * w + this.z * x - this.w * y - this.x * z) / d, (this.z * w - this.y * x + this.x * y - this.w * z) / d, (this.w * w + this.x * x + this.y * y + this.z * z) / d);
   }

   public double dot(Quaterniond q) {
      return this.dot(q.x, q.y, q.z, q.w);
   }

   public double dot(float x, float y, float z, float w) {
      return this.dot((double)x, (double)y, (double)z, (double)w);
   }

   public double dot(double x, double y, double z, double w) {
      return this.x * x + this.y * y + this.z * z + this.w * w;
   }

   @Nonnull
   public Vector3d rotate(Vector3d v) {
      return this.rotate(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public Vector3d rotate(float x, float y, float z) {
      return this.rotate((double)x, (double)y, (double)z);
   }

   @Nonnull
   public Vector3d rotate(double x, double y, double z) {
      double length = this.length();
      if (Math.abs(length) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot rotate by the zero quaternion");
      } else {
         double nx = this.x / length;
         double ny = this.y / length;
         double nz = this.z / length;
         double nw = this.w / length;
         double px = nw * x + ny * z - nz * y;
         double py = nw * y + nz * x - nx * z;
         double pz = nw * z + nx * y - ny * x;
         double pw = -nx * x - ny * y - nz * z;
         return Vector3d.from(pw * -nx + px * nw - py * nz + pz * ny, pw * -ny + py * nw - pz * nx + px * nz, pw * -nz + pz * nw - px * ny + py * nx);
      }
   }

   @Nonnull
   public Vector3d getDirection() {
      return this.rotate(Vector3d.FORWARD);
   }

   @Nonnull
   public Vector3d getAxis() {
      double q = Math.sqrt((double)1.0F - this.w * this.w);
      return Vector3d.from(this.x / q, this.y / q, this.z / q);
   }

   @Nonnull
   public Vector3d getAxesAnglesDeg() {
      return this.getAxesAnglesRad().mul((180D / Math.PI));
   }

   @Nonnull
   public Vector3d getAxesAnglesRad() {
      double test = this.w * this.x - this.y * this.z;
      double roll;
      double pitch;
      double yaw;
      if (Math.abs(test) < 0.4999) {
         roll = TrigMath.atan2((double)2.0F * (this.w * this.z + this.x * this.y), (double)1.0F - (double)2.0F * (this.x * this.x + this.z * this.z));
         pitch = TrigMath.asin((double)2.0F * test);
         yaw = TrigMath.atan2((double)2.0F * (this.w * this.y + this.z * this.x), (double)1.0F - (double)2.0F * (this.x * this.x + this.y * this.y));
      } else {
         int sign = test < (double)0.0F ? -1 : 1;
         roll = (double)0.0F;
         pitch = (double)sign * Math.PI / (double)2.0F;
         yaw = (double)(-sign * 2) * TrigMath.atan2(this.z, this.w);
      }

      return Vector3d.from(pitch, yaw, roll);
   }

   @Nonnull
   public Quaterniond conjugate() {
      return from(-this.x, -this.y, -this.z, this.w);
   }

   @Nonnull
   public Quaterniond invert() {
      double lengthSquared = this.lengthSquared();
      if (Math.abs(lengthSquared) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot invert a quaternion of length zero");
      } else {
         return this.conjugate().div(lengthSquared);
      }
   }

   public double lengthSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
   }

   public double length() {
      return Math.sqrt(this.lengthSquared());
   }

   @Nonnull
   public Quaterniond normalize() {
      double length = this.length();
      if (Math.abs(length) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot normalize the zero quaternion");
      } else {
         return from(this.x / length, this.y / length, this.z / length, this.w / length);
      }
   }

   @Nonnull
   public Complexd toComplex() {
      double w2 = this.w * this.w;
      return Complexd.from((double)2.0F * w2 - (double)1.0F, (double)2.0F * this.w * Math.sqrt((double)1.0F - w2));
   }

   @Nonnull
   public Quaternionf toFloat() {
      return Quaternionf.from(this.x, this.y, this.z, this.w);
   }

   @Nonnull
   public Quaterniond toDouble() {
      return from(this.x, this.y, this.z, this.w);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Quaterniond)) {
         return false;
      } else {
         Quaterniond quaternion = (Quaterniond)o;
         if (Double.compare(quaternion.w, this.w) != 0) {
            return false;
         } else if (Double.compare(quaternion.x, this.x) != 0) {
            return false;
         } else if (Double.compare(quaternion.y, this.y) != 0) {
            return false;
         } else {
            return Double.compare(quaternion.z, this.z) == 0;
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

   public int compareTo(Quaterniond q) {
      return (int)Math.signum(this.lengthSquared() - q.lengthSquared());
   }

   @Nonnull
   public Quaterniond clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
   }

   @Nonnull
   public static Quaterniond fromReal(double w) {
      return w == (double)0.0F ? ZERO : from((double)0.0F, (double)0.0F, (double)0.0F, w);
   }

   @Nonnull
   public static Quaterniond fromImaginary(double x, double y, double z) {
      return x == (double)0.0F && y == (double)0.0F && z == (double)0.0F ? ZERO : new Quaterniond(x, y, z, (double)0.0F);
   }

   public static Quaterniond from(Quaterniond q) {
      return from(q.x, q.y, q.z, q.w);
   }

   @Nonnull
   public static Quaterniond from(float x, float y, float z, float w) {
      return from((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public static Quaterniond from(double x, double y, double z, double w) {
      return x == (double)0.0F && y == (double)0.0F && z == (double)0.0F && w == (double)0.0F ? ZERO : new Quaterniond(x, y, z, w);
   }

   @Nonnull
   public static Quaterniond fromAxesAnglesDeg(float pitch, float yaw, float roll) {
      return fromAxesAnglesDeg((double)pitch, (double)yaw, (double)roll);
   }

   @Nonnull
   public static Quaterniond fromAxesAnglesRad(float pitch, float yaw, float roll) {
      return fromAxesAnglesRad((double)pitch, (double)yaw, (double)roll);
   }

   @Nonnull
   public static Quaterniond fromAxesAnglesDeg(double pitch, double yaw, double roll) {
      return fromAngleDegAxis(yaw, Vector3d.UNIT_Y).mul(fromAngleDegAxis(pitch, Vector3d.UNIT_X)).mul(fromAngleDegAxis(roll, Vector3d.UNIT_Z));
   }

   @Nonnull
   public static Quaterniond fromAxesAnglesRad(double pitch, double yaw, double roll) {
      return fromAngleRadAxis(yaw, Vector3d.UNIT_Y).mul(fromAngleRadAxis(pitch, Vector3d.UNIT_X)).mul(fromAngleRadAxis(roll, Vector3d.UNIT_Z));
   }

   @Nonnull
   public static Quaterniond fromRotationTo(Vector3d from, Vector3d to) {
      return fromAngleRadAxis(TrigMath.acos(from.dot(to) / (from.length() * to.length())), from.cross(to));
   }

   @Nonnull
   public static Quaterniond fromAngleDegAxis(float angle, Vector3d axis) {
      return fromAngleRadAxis(Math.toRadians((double)angle), axis);
   }

   @Nonnull
   public static Quaterniond fromAngleRadAxis(float angle, Vector3d axis) {
      return fromAngleRadAxis((double)angle, axis);
   }

   @Nonnull
   public static Quaterniond fromAngleDegAxis(double angle, Vector3d axis) {
      return fromAngleRadAxis(Math.toRadians(angle), axis);
   }

   @Nonnull
   public static Quaterniond fromAngleRadAxis(double angle, Vector3d axis) {
      return fromAngleRadAxis(angle, axis.getX(), axis.getY(), axis.getZ());
   }

   @Nonnull
   public static Quaterniond fromAngleDegAxis(float angle, float x, float y, float z) {
      return fromAngleRadAxis(Math.toRadians((double)angle), (double)x, (double)y, (double)z);
   }

   @Nonnull
   public static Quaterniond fromAngleRadAxis(float angle, float x, float y, float z) {
      return fromAngleRadAxis((double)angle, (double)x, (double)y, (double)z);
   }

   @Nonnull
   public static Quaterniond fromAngleDegAxis(double angle, double x, double y, double z) {
      return fromAngleRadAxis(Math.toRadians(angle), x, y, z);
   }

   @Nonnull
   public static Quaterniond fromAngleRadAxis(double angle, double x, double y, double z) {
      double halfAngle = angle / (double)2.0F;
      double q = (double)TrigMath.sin(halfAngle) / Math.sqrt(x * x + y * y + z * z);
      return from(x * q, y * q, z * q, (double)TrigMath.cos(halfAngle));
   }

   @Nonnull
   public static Quaterniond fromRotationMatrix(Matrix3d matrix) {
      double trace = matrix.trace();
      if (trace < (double)0.0F) {
         if (matrix.get(1, 1) > matrix.get(0, 0)) {
            if (matrix.get(2, 2) > matrix.get(1, 1)) {
               double r = Math.sqrt(matrix.get(2, 2) - matrix.get(0, 0) - matrix.get(1, 1) + (double)1.0F);
               double s = (double)0.5F / r;
               return from((matrix.get(2, 0) + matrix.get(0, 2)) * s, (matrix.get(1, 2) + matrix.get(2, 1)) * s, (double)0.5F * r, (matrix.get(1, 0) - matrix.get(0, 1)) * s);
            } else {
               double r = Math.sqrt(matrix.get(1, 1) - matrix.get(2, 2) - matrix.get(0, 0) + (double)1.0F);
               double s = (double)0.5F / r;
               return from((matrix.get(0, 1) + matrix.get(1, 0)) * s, (double)0.5F * r, (matrix.get(1, 2) + matrix.get(2, 1)) * s, (matrix.get(0, 2) - matrix.get(2, 0)) * s);
            }
         } else if (matrix.get(2, 2) > matrix.get(0, 0)) {
            double r = Math.sqrt(matrix.get(2, 2) - matrix.get(0, 0) - matrix.get(1, 1) + (double)1.0F);
            double s = (double)0.5F / r;
            return from((matrix.get(2, 0) + matrix.get(0, 2)) * s, (matrix.get(1, 2) + matrix.get(2, 1)) * s, (double)0.5F * r, (matrix.get(1, 0) - matrix.get(0, 1)) * s);
         } else {
            double r = Math.sqrt(matrix.get(0, 0) - matrix.get(1, 1) - matrix.get(2, 2) + (double)1.0F);
            double s = (double)0.5F / r;
            return from((double)0.5F * r, (matrix.get(0, 1) + matrix.get(1, 0)) * s, (matrix.get(2, 0) - matrix.get(0, 2)) * s, (matrix.get(2, 1) - matrix.get(1, 2)) * s);
         }
      } else {
         double r = Math.sqrt(trace + (double)1.0F);
         double s = (double)0.5F / r;
         return from((matrix.get(2, 1) - matrix.get(1, 2)) * s, (matrix.get(0, 2) - matrix.get(2, 0)) * s, (matrix.get(1, 0) - matrix.get(0, 1)) * s, (double)0.5F * r);
      }
   }
}
