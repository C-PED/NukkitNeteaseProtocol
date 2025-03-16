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
public class Vector3f implements Vectorf, Comparable<Vector3f>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Vector3f ZERO = new Vector3f(0.0F, 0.0F, 0.0F);
   public static final Vector3f UNIT_X = new Vector3f(1.0F, 0.0F, 0.0F);
   public static final Vector3f UNIT_Y = new Vector3f(0.0F, 1.0F, 0.0F);
   public static final Vector3f UNIT_Z = new Vector3f(0.0F, 0.0F, 1.0F);
   public static final Vector3f ONE = new Vector3f(1.0F, 1.0F, 1.0F);
   public static final Vector3f RIGHT;
   public static final Vector3f UP;
   public static final Vector3f FORWARD;
   private final float x;
   private final float y;
   private final float z;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Vector3f(float x, float y, float z) {
      this.x = x;
      this.y = y;
      this.z = z;
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
   public Vector3f add(Vector3f v) {
      return this.add(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3f add(double x, double y, double z) {
      return this.add((float)x, (float)y, (float)z);
   }

   @Nonnull
   public Vector3f add(float x, float y, float z) {
      return from(this.x + x, this.y + y, this.z + z);
   }

   @Nonnull
   public Vector3f sub(Vector3f v) {
      return this.sub(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3f sub(double x, double y, double z) {
      return this.sub((float)x, (float)y, (float)z);
   }

   @Nonnull
   public Vector3f sub(float x, float y, float z) {
      return from(this.x - x, this.y - y, this.z - z);
   }

   @Nonnull
   public Vector3f mul(double a) {
      return this.mul((float)a);
   }

   @Nonnull
   public Vector3f mul(float a) {
      return this.mul(a, a, a);
   }

   @Nonnull
   public Vector3f mul(Vector3f v) {
      return this.mul(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3f mul(double x, double y, double z) {
      return this.mul((float)x, (float)y, (float)z);
   }

   @Nonnull
   public Vector3f mul(float x, float y, float z) {
      return from(this.x * x, this.y * y, this.z * z);
   }

   @Nonnull
   public Vector3f div(double a) {
      return this.div((float)a);
   }

   @Nonnull
   public Vector3f div(float a) {
      return this.div(a, a, a);
   }

   @Nonnull
   public Vector3f div(Vector3f v) {
      return this.div(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3f div(double x, double y, double z) {
      return this.div((float)x, (float)y, (float)z);
   }

   @Nonnull
   public Vector3f div(float x, float y, float z) {
      return from(this.x / x, this.y / y, this.z / z);
   }

   public float dot(Vector3f v) {
      return this.dot(v.x, v.y, v.z);
   }

   public float dot(double x, double y, double z) {
      return this.dot((float)x, (float)y, (float)z);
   }

   public float dot(float x, float y, float z) {
      return this.x * x + this.y * y + this.z * z;
   }

   @Nonnull
   public Vector3f project(Vector3f v) {
      return this.project(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3f project(double x, double y, double z) {
      return this.project((float)x, (float)y, (float)z);
   }

   @Nonnull
   public Vector3f project(float x, float y, float z) {
      float lengthSquared = x * x + y * y + z * z;
      if (Math.abs(lengthSquared) < GenericMath.FLT_EPSILON) {
         throw new ArithmeticException("Cannot project onto the zero vector");
      } else {
         float a = this.dot(x, y, z) / lengthSquared;
         return from(a * x, a * y, a * z);
      }
   }

   @Nonnull
   public Vector3f cross(Vector3f v) {
      return this.cross(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3f cross(double x, double y, double z) {
      return this.cross((float)x, (float)y, (float)z);
   }

   @Nonnull
   public Vector3f cross(float x, float y, float z) {
      return from(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
   }

   @Nonnull
   public Vector3f pow(double pow) {
      return this.pow((float)pow);
   }

   @Nonnull
   public Vector3f pow(float power) {
      return from(Math.pow((double)this.x, (double)power), Math.pow((double)this.y, (double)power), Math.pow((double)this.z, (double)power));
   }

   @Nonnull
   public Vector3f ceil() {
      return from(Math.ceil((double)this.x), Math.ceil((double)this.y), Math.ceil((double)this.z));
   }

   @Nonnull
   public Vector3f floor() {
      return from((float)GenericMath.floor(this.x), (float)GenericMath.floor(this.y), (float)GenericMath.floor(this.z));
   }

   @Nonnull
   public Vector3f round() {
      return from((float)Math.round(this.x), (float)Math.round(this.y), (float)Math.round(this.z));
   }

   @Nonnull
   public Vector3f abs() {
      return from(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
   }

   @Nonnull
   public Vector3f negate() {
      return from(-this.x, -this.y, -this.z);
   }

   @Nonnull
   public Vector3f min(Vector3f v) {
      return this.min(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3f min(double x, double y, double z) {
      return this.min((float)x, (float)y, (float)z);
   }

   @Nonnull
   public Vector3f min(float x, float y, float z) {
      return from(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z));
   }

   @Nonnull
   public Vector3f max(Vector3f v) {
      return this.max(v.x, v.y, v.z);
   }

   @Nonnull
   public Vector3f max(double x, double y, double z) {
      return this.max((float)x, (float)y, (float)z);
   }

   @Nonnull
   public Vector3f max(float x, float y, float z) {
      return from(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z));
   }

   public float distanceSquared(Vector3f v) {
      return this.distanceSquared(v.x, v.y, v.z);
   }

   public float distanceSquared(double x, double y, double z) {
      return this.distanceSquared((float)x, (float)y, (float)z);
   }

   public float distanceSquared(float x, float y, float z) {
      float dx = this.x - x;
      float dy = this.y - y;
      float dz = this.z - z;
      return dx * dx + dy * dy + dz * dz;
   }

   public float distance(Vector3f v) {
      return this.distance(v.x, v.y, v.z);
   }

   public float distance(double x, double y, double z) {
      return this.distance((float)x, (float)y, (float)z);
   }

   public float distance(float x, float y, float z) {
      return (float)Math.sqrt((double)this.distanceSquared(x, y, z));
   }

   @Nonnull
   public Vector3f up() {
      return this.up(1.0F);
   }

   @Nonnull
   public Vector3f up(float v) {
      return from(this.x, this.y + v, this.z);
   }

   @Nonnull
   public Vector3f down() {
      return this.down(1.0F);
   }

   @Nonnull
   public Vector3f down(float v) {
      return from(this.x, this.y - v, this.z);
   }

   @Nonnull
   public Vector3f north() {
      return this.north(1.0F);
   }

   @Nonnull
   public Vector3f north(float v) {
      return from(this.x, this.y, this.z - v);
   }

   @Nonnull
   public Vector3f south() {
      return this.south(1.0F);
   }

   @Nonnull
   public Vector3f south(float v) {
      return from(this.x, this.y, this.z + v);
   }

   @Nonnull
   public Vector3f east() {
      return this.east(1.0F);
   }

   @Nonnull
   public Vector3f east(float v) {
      return from(this.x + v, this.y, this.z);
   }

   @Nonnull
   public Vector3f west() {
      return this.west(1.0F);
   }

   @Nonnull
   public Vector3f west(float v) {
      return from(this.x - v, this.y, this.z);
   }

   public float lengthSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z;
   }

   public float length() {
      return (float)Math.sqrt((double)this.lengthSquared());
   }

   @Nonnull
   public Vector3f normalize() {
      float length = this.length();
      if (Math.abs(length) < GenericMath.FLT_EPSILON) {
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
   public Vector2f toVector2() {
      return Vector2f.from(this);
   }

   @Nonnull
   public Vector2f toVector2(boolean useZ) {
      return Vector2f.from(this.x, useZ ? this.z : this.y);
   }

   @Nonnull
   public Vector4f toVector4() {
      return this.toVector4(0.0F);
   }

   @Nonnull
   public Vector4f toVector4(double w) {
      return this.toVector4((float)w);
   }

   @Nonnull
   public Vector4f toVector4(float w) {
      return Vector4f.from(this, w);
   }

   @Nonnull
   public VectorNf toVectorN() {
      return VectorNf.from(this);
   }

   @Nonnull
   public float[] toArray() {
      return new float[]{this.x, this.y, this.z};
   }

   @Nonnull
   public Vector3i toInt() {
      return Vector3i.from((double)this.x, (double)this.y, (double)this.z);
   }

   @Nonnull
   public Vector3l toLong() {
      return Vector3l.from((double)this.x, (double)this.y, (double)this.z);
   }

   @Nonnull
   public Vector3f toFloat() {
      return from(this.x, this.y, this.z);
   }

   @Nonnull
   public Vector3d toDouble() {
      return Vector3d.from(this.x, this.y, this.z);
   }

   public int compareTo(Vector3f v) {
      return (int)Math.signum(this.lengthSquared() - v.lengthSquared());
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Vector3f)) {
         return false;
      } else {
         Vector3f vector3 = (Vector3f)o;
         if (Float.compare(vector3.x, this.x) != 0) {
            return false;
         } else if (Float.compare(vector3.y, this.y) != 0) {
            return false;
         } else {
            return Float.compare(vector3.z, this.z) == 0;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         int result = this.x != 0.0F ? Float.hashCode(this.x) : 0;
         result = 31 * result + (this.y != 0.0F ? Float.hashCode(this.y) : 0);
         this.hashCode = 31 * result + (this.z != 0.0F ? Float.hashCode(this.z) : 0);
         this.hashed = true;
      }

      return this.hashCode;
   }

   @Nonnull
   public Vector3f clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ", " + this.z + ")";
   }

   @Nonnull
   public static Vector3f from(float n) {
      return n == 0.0F ? ZERO : new Vector3f(n, n, n);
   }

   @Nonnull
   public static Vector3f from(Vector2f v) {
      return from(v, 0.0F);
   }

   @Nonnull
   public static Vector3f from(Vector2f v, double z) {
      return from(v, (float)z);
   }

   @Nonnull
   public static Vector3f from(Vector2f v, float z) {
      return from(v.getX(), v.getY(), z);
   }

   @Nonnull
   public static Vector3f from(Vector3f v) {
      return from(v.x, v.y, v.z);
   }

   @Nonnull
   public static Vector3f from(Vector4f v) {
      return from(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public static Vector3f from(VectorNf v) {
      return from(v.get(0), v.get(1), v.size() > 2 ? v.get(2) : 0.0F);
   }

   @Nonnull
   public static Vector3f from(double x, double y, double z) {
      return from((float)x, (float)y, (float)z);
   }

   @Nonnull
   public static Vector3f from(float x, float y, float z) {
      return x == 0.0F && y == 0.0F && z == 0.0F ? ZERO : new Vector3f(x, y, z);
   }

   @Nonnull
   public static Vector3f createRandomDirection(Random random) {
      return createDirectionRad(random.nextFloat() * ((float)Math.PI * 2F), random.nextFloat() * ((float)Math.PI * 2F));
   }

   @Nonnull
   public static Vector3f createDirectionDeg(double theta, double phi) {
      return createDirectionDeg((float)theta, (float)phi);
   }

   @Nonnull
   public static Vector3f createDirectionDeg(float theta, float phi) {
      return createDirectionRad((float)Math.toRadians((double)theta), (float)Math.toRadians((double)phi));
   }

   @Nonnull
   public static Vector3f createDirectionRad(double theta, double phi) {
      return createDirectionRad((float)theta, (float)phi);
   }

   @Nonnull
   public static Vector3f createDirectionRad(float theta, float phi) {
      float f = TrigMath.sin((double)phi);
      return from(f * TrigMath.cos((double)theta), f * TrigMath.sin((double)theta), TrigMath.cos((double)phi));
   }

   static {
      RIGHT = UNIT_X;
      UP = UNIT_Y;
      FORWARD = UNIT_Z;
   }
}
