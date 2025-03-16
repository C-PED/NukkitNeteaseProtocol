package com.nukkitx.math.vector;

import com.nukkitx.math.GenericMath;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

@ParametersAreNonnullByDefault
@Immutable
public class Vector4f implements Vectorf, Comparable<Vector4f>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Vector4f ZERO = new Vector4f(0.0F, 0.0F, 0.0F, 0.0F);
   public static final Vector4f UNIT_X = new Vector4f(1.0F, 0.0F, 0.0F, 0.0F);
   public static final Vector4f UNIT_Y = new Vector4f(0.0F, 1.0F, 0.0F, 0.0F);
   public static final Vector4f UNIT_Z = new Vector4f(0.0F, 0.0F, 1.0F, 0.0F);
   public static final Vector4f UNIT_W = new Vector4f(0.0F, 0.0F, 0.0F, 1.0F);
   public static final Vector4f ONE = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
   private final float x;
   private final float y;
   private final float z;
   private final float w;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Vector4f(float x, float y, float z, float w) {
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
   public Vector4f add(Vector4f v) {
      return this.add(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4f add(double x, double y, double z, double w) {
      return this.add((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public Vector4f add(float x, float y, float z, float w) {
      return from(this.x + x, this.y + y, this.z + z, this.w + w);
   }

   @Nonnull
   public Vector4f sub(Vector4f v) {
      return this.sub(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4f sub(double x, double y, double z, double w) {
      return this.sub((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public Vector4f sub(float x, float y, float z, float w) {
      return from(this.x - x, this.y - y, this.z - z, this.w - w);
   }

   @Nonnull
   public Vector4f mul(double a) {
      return this.mul((float)a);
   }

   @Nonnull
   public Vector4f mul(float a) {
      return this.mul(a, a, a, a);
   }

   @Nonnull
   public Vector4f mul(Vector4f v) {
      return this.mul(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4f mul(double x, double y, double z, double w) {
      return this.mul((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public Vector4f mul(float x, float y, float z, float w) {
      return from(this.x * x, this.y * y, this.z * z, this.w * w);
   }

   @Nonnull
   public Vector4f div(double a) {
      return this.div((float)a);
   }

   @Nonnull
   public Vector4f div(float a) {
      return this.div(a, a, a, a);
   }

   @Nonnull
   public Vector4f div(Vector4f v) {
      return this.div(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4f div(double x, double y, double z, double w) {
      return this.div((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public Vector4f div(float x, float y, float z, float w) {
      return from(this.x / x, this.y / y, this.z / z, this.w / w);
   }

   public float dot(Vector4f v) {
      return this.dot(v.x, v.y, v.z, v.w);
   }

   public float dot(double x, double y, double z, double w) {
      return this.dot((float)x, (float)y, (float)z, (float)w);
   }

   public float dot(float x, float y, float z, float w) {
      return this.x * x + this.y * y + this.z * z + this.w * w;
   }

   @Nonnull
   public Vector4f project(Vector4f v) {
      return this.project(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4f project(double x, double y, double z, double w) {
      return this.project((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public Vector4f project(float x, float y, float z, float w) {
      float lengthSquared = x * x + y * y + z * z + w * w;
      if (Math.abs(lengthSquared) < GenericMath.FLT_EPSILON) {
         throw new ArithmeticException("Cannot project onto the zero vector");
      } else {
         float a = this.dot(x, y, z, w) / lengthSquared;
         return from(a * x, a * y, a * z, a * w);
      }
   }

   @Nonnull
   public Vector4f pow(double pow) {
      return this.pow((float)pow);
   }

   @Nonnull
   public Vector4f pow(float power) {
      return from(Math.pow((double)this.x, (double)power), Math.pow((double)this.y, (double)power), Math.pow((double)this.z, (double)power), Math.pow((double)this.w, (double)power));
   }

   @Nonnull
   public Vector4f ceil() {
      return from(Math.ceil((double)this.x), Math.ceil((double)this.y), Math.ceil((double)this.z), Math.ceil((double)this.w));
   }

   @Nonnull
   public Vector4f floor() {
      return from((float)GenericMath.floor(this.x), (float)GenericMath.floor(this.y), (float)GenericMath.floor(this.z), (float)GenericMath.floor(this.w));
   }

   @Nonnull
   public Vector4f round() {
      return from((float)Math.round(this.x), (float)Math.round(this.y), (float)Math.round(this.z), (float)Math.round(this.w));
   }

   @Nonnull
   public Vector4f abs() {
      return from(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z), Math.abs(this.w));
   }

   @Nonnull
   public Vector4f negate() {
      return from(-this.x, -this.y, -this.z, -this.w);
   }

   @Nonnull
   public Vector4f min(Vector4f v) {
      return this.min(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4f min(double x, double y, double z, double w) {
      return this.min((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public Vector4f min(float x, float y, float z, float w) {
      return from(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z), Math.min(this.w, w));
   }

   @Nonnull
   public Vector4f max(Vector4f v) {
      return this.max(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public Vector4f max(double x, double y, double z, double w) {
      return this.max((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public Vector4f max(float x, float y, float z, float w) {
      return from(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z), Math.max(this.w, w));
   }

   public float distanceSquared(Vector4f v) {
      return this.distanceSquared(v.x, v.y, v.z, v.w);
   }

   public float distanceSquared(double x, double y, double z, double w) {
      return this.distanceSquared((float)x, (float)y, (float)z, (float)w);
   }

   public float distanceSquared(float x, float y, float z, float w) {
      float dx = this.x - x;
      float dy = this.y - y;
      float dz = this.z - z;
      float dw = this.w - w;
      return dx * dx + dy * dy + dz * dz + dw * dw;
   }

   public float distance(Vector4f v) {
      return this.distance(v.x, v.y, v.z, v.w);
   }

   public float distance(double x, double y, double z, double w) {
      return this.distance((float)x, (float)y, (float)z, (float)w);
   }

   public float distance(float x, float y, float z, float w) {
      return (float)Math.sqrt((double)this.distanceSquared(x, y, z, w));
   }

   public float lengthSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
   }

   public float length() {
      return (float)Math.sqrt((double)this.lengthSquared());
   }

   @Nonnull
   public Vector4f normalize() {
      float length = this.length();
      if (Math.abs(length) < GenericMath.FLT_EPSILON) {
         throw new ArithmeticException("Cannot normalize the zero vector");
      } else {
         return from(this.x / length, this.y / length, this.z / length, this.w / length);
      }
   }

   public int getMinAxis() {
      float value = this.x;
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
      float value = this.x;
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
   public Vector2f toVector2() {
      return Vector2f.from(this);
   }

   @Nonnull
   public Vector3f toVector3() {
      return Vector3f.from(this);
   }

   @Nonnull
   public VectorNf toVectorN() {
      return VectorNf.from(this);
   }

   @Nonnull
   public float[] toArray() {
      return new float[]{this.x, this.y, this.z, this.w};
   }

   @Nonnull
   public Vector4i toInt() {
      return Vector4i.from((double)this.x, (double)this.y, (double)this.z, (double)this.w);
   }

   @Nonnull
   public Vector4l toLong() {
      return Vector4l.from((double)this.x, (double)this.y, (double)this.z, (double)this.w);
   }

   @Nonnull
   public Vector4f toFloat() {
      return from(this.x, this.y, this.z, this.w);
   }

   @Nonnull
   public Vector4d toDouble() {
      return Vector4d.from(this.x, this.y, this.z, this.w);
   }

   public int compareTo(Vector4f v) {
      return (int)Math.signum(this.lengthSquared() - v.lengthSquared());
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Vector4f)) {
         return false;
      } else {
         Vector4f vector4 = (Vector4f)o;
         if (Float.compare(vector4.w, this.w) != 0) {
            return false;
         } else if (Float.compare(vector4.x, this.x) != 0) {
            return false;
         } else if (Float.compare(vector4.y, this.y) != 0) {
            return false;
         } else {
            return Float.compare(vector4.z, this.z) == 0;
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

   @Nonnull
   public Vector4f clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
   }

   @Nonnull
   public static Vector4f from(float n) {
      return n == 0.0F ? ZERO : new Vector4f(n, n, n, n);
   }

   @Nonnull
   public static Vector4f from(Vector2f v) {
      return from(v, 0.0F, 0.0F);
   }

   @Nonnull
   public static Vector4f from(Vector2f v, double z, double w) {
      return from(v, (float)z, (float)w);
   }

   @Nonnull
   public static Vector4f from(Vector2f v, float z, float w) {
      return from(v.getX(), v.getY(), z, w);
   }

   @Nonnull
   public static Vector4f from(Vector3f v) {
      return from(v, 0.0F);
   }

   @Nonnull
   public static Vector4f from(Vector3f v, double w) {
      return from(v, (float)w);
   }

   @Nonnull
   public static Vector4f from(Vector3f v, float w) {
      return from(v.getX(), v.getY(), v.getZ(), w);
   }

   @Nonnull
   public static Vector4f from(Vector4f v) {
      return from(v.x, v.y, v.z, v.w);
   }

   @Nonnull
   public static Vector4f from(VectorNf v) {
      return from(v.get(0), v.get(1), v.size() > 2 ? v.get(2) : 0.0F, v.size() > 3 ? v.get(3) : 0.0F);
   }

   @Nonnull
   public static Vector4f from(double x, double y, double z, double w) {
      return from((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public static Vector4f from(float x, float y, float z, float w) {
      return x == 0.0F && y == 0.0F && z == 0.0F && w == 0.0F ? ZERO : new Vector4f(x, y, z, w);
   }
}
