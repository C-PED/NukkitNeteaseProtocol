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
public class Vector2f implements Vectorf, Comparable<Vector2f>, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Vector2f ZERO = new Vector2f(0.0F, 0.0F);
   public static final Vector2f UNIT_X = new Vector2f(1.0F, 0.0F);
   public static final Vector2f UNIT_Y = new Vector2f(0.0F, 1.0F);
   public static final Vector2f ONE = new Vector2f(1.0F, 1.0F);
   private final float x;
   private final float y;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Vector2f(float x, float y) {
      this.x = x;
      this.y = y;
   }

   public float getX() {
      return this.x;
   }

   public float getY() {
      return this.y;
   }

   public int getFloorX() {
      return GenericMath.floor(this.x);
   }

   public int getFloorY() {
      return GenericMath.floor(this.y);
   }

   @Nonnull
   public Vector2f add(Vector2f v) {
      return this.add(v.x, v.y);
   }

   @Nonnull
   public Vector2f add(double x, double y) {
      return this.add((float)x, (float)y);
   }

   @Nonnull
   public Vector2f add(float x, float y) {
      return from(this.x + x, this.y + y);
   }

   @Nonnull
   public Vector2f sub(Vector2f v) {
      return this.sub(v.x, v.y);
   }

   @Nonnull
   public Vector2f sub(double x, double y) {
      return this.sub((float)x, (float)y);
   }

   @Nonnull
   public Vector2f sub(float x, float y) {
      return from(this.x - x, this.y - y);
   }

   @Nonnull
   public Vector2f mul(double a) {
      return this.mul((float)a);
   }

   @Nonnull
   public Vector2f mul(float a) {
      return this.mul(a, a);
   }

   @Nonnull
   public Vector2f mul(Vector2f v) {
      return this.mul(v.x, v.y);
   }

   @Nonnull
   public Vector2f mul(double x, double y) {
      return this.mul((float)x, (float)y);
   }

   @Nonnull
   public Vector2f mul(float x, float y) {
      return from(this.x * x, this.y * y);
   }

   @Nonnull
   public Vector2f div(double a) {
      return this.div((float)a);
   }

   @Nonnull
   public Vector2f div(float a) {
      return this.div(a, a);
   }

   @Nonnull
   public Vector2f div(Vector2f v) {
      return this.div(v.x, v.y);
   }

   @Nonnull
   public Vector2f div(double x, double y) {
      return this.div((float)x, (float)y);
   }

   @Nonnull
   public Vector2f div(float x, float y) {
      return from(this.x / x, this.y / y);
   }

   public float dot(Vector2f v) {
      return this.dot(v.x, v.y);
   }

   public float dot(double x, double y) {
      return this.dot((float)x, (float)y);
   }

   public float dot(float x, float y) {
      return this.x * x + this.y * y;
   }

   @Nonnull
   public Vector2f project(Vector2f v) {
      return this.project(v.x, v.y);
   }

   @Nonnull
   public Vector2f project(double x, double y) {
      return this.project((float)x, (float)y);
   }

   @Nonnull
   public Vector2f project(float x, float y) {
      float lengthSquared = x * x + y * y;
      if (Math.abs(lengthSquared) < GenericMath.FLT_EPSILON) {
         throw new ArithmeticException("Cannot project onto the zero vector");
      } else {
         float a = this.dot(x, y) / lengthSquared;
         return from(a * x, a * y);
      }
   }

   @Nonnull
   public Vector2f pow(double pow) {
      return this.pow((float)pow);
   }

   @Nonnull
   public Vector2f pow(float power) {
      return from(Math.pow((double)this.x, (double)power), Math.pow((double)this.y, (double)power));
   }

   @Nonnull
   public Vector2f ceil() {
      return from(Math.ceil((double)this.x), Math.ceil((double)this.y));
   }

   @Nonnull
   public Vector2f floor() {
      return from((float)GenericMath.floor(this.x), (float)GenericMath.floor(this.y));
   }

   @Nonnull
   public Vector2f round() {
      return from((float)Math.round(this.x), (float)Math.round(this.y));
   }

   @Nonnull
   public Vector2f abs() {
      return from(Math.abs(this.x), Math.abs(this.y));
   }

   @Nonnull
   public Vector2f negate() {
      return from(-this.x, -this.y);
   }

   @Nonnull
   public Vector2f min(Vector2f v) {
      return this.min(v.x, v.y);
   }

   @Nonnull
   public Vector2f min(double x, double y) {
      return this.min((float)x, (float)y);
   }

   @Nonnull
   public Vector2f min(float x, float y) {
      return from(Math.min(this.x, x), Math.min(this.y, y));
   }

   @Nonnull
   public Vector2f max(Vector2f v) {
      return this.max(v.x, v.y);
   }

   @Nonnull
   public Vector2f max(double x, double y) {
      return this.max((float)x, (float)y);
   }

   @Nonnull
   public Vector2f max(float x, float y) {
      return from(Math.max(this.x, x), Math.max(this.y, y));
   }

   public float distanceSquared(Vector2f v) {
      return this.distanceSquared(v.x, v.y);
   }

   public float distanceSquared(double x, double y) {
      return this.distanceSquared((float)x, (float)y);
   }

   public float distanceSquared(float x, float y) {
      float dx = this.x - x;
      float dy = this.y - y;
      return dx * dx + dy * dy;
   }

   public float distance(Vector2f v) {
      return this.distance(v.x, v.y);
   }

   public float distance(double x, double y) {
      return this.distance((float)x, (float)y);
   }

   public float distance(float x, float y) {
      return (float)Math.sqrt((double)this.distanceSquared(x, y));
   }

   @Nonnull
   public Vector2f north() {
      return this.north(1.0F);
   }

   @Nonnull
   public Vector2f north(float v) {
      return from(this.x, this.y - v);
   }

   @Nonnull
   public Vector2f south() {
      return this.south(1.0F);
   }

   @Nonnull
   public Vector2f south(float v) {
      return from(this.x, this.y + v);
   }

   @Nonnull
   public Vector2f east() {
      return this.east(1.0F);
   }

   @Nonnull
   public Vector2f east(float v) {
      return from(this.x + v, this.y);
   }

   @Nonnull
   public Vector2f west() {
      return this.west(1.0F);
   }

   @Nonnull
   public Vector2f west(float v) {
      return from(this.x - v, this.y);
   }

   public float lengthSquared() {
      return this.x * this.x + this.y * this.y;
   }

   public float length() {
      return (float)Math.sqrt((double)this.lengthSquared());
   }

   @Nonnull
   public Vector2f normalize() {
      float length = this.length();
      if (Math.abs(length) < GenericMath.FLT_EPSILON) {
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
   public Vector3f toVector3() {
      return this.toVector3(0.0F);
   }

   @Nonnull
   public Vector3f toVector3(double z) {
      return this.toVector3((float)z);
   }

   @Nonnull
   public Vector3f toVector3(float z) {
      return Vector3f.from(this, z);
   }

   @Nonnull
   public Vector4f toVector4() {
      return this.toVector4(0.0F, 0.0F);
   }

   @Nonnull
   public Vector4f toVector4(double z, double w) {
      return this.toVector4((float)z, (float)w);
   }

   @Nonnull
   public Vector4f toVector4(float z, float w) {
      return Vector4f.from(this, z, w);
   }

   @Nonnull
   public VectorNf toVectorN() {
      return VectorNf.from(this);
   }

   @Nonnull
   public float[] toArray() {
      return new float[]{this.x, this.y};
   }

   @Nonnull
   public Vector2i toInt() {
      return Vector2i.from((double)this.x, (double)this.y);
   }

   @Nonnull
   public Vector2l toLong() {
      return Vector2l.from((double)this.x, (double)this.y);
   }

   @Nonnull
   public Vector2f toFloat() {
      return from(this.x, this.y);
   }

   @Nonnull
   public Vector2d toDouble() {
      return Vector2d.from(this.x, this.y);
   }

   public int compareTo(Vector2f v) {
      return (int)Math.signum(this.lengthSquared() - v.lengthSquared());
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Vector2f)) {
         return false;
      } else {
         Vector2f vector2 = (Vector2f)o;
         if (Float.compare(vector2.x, this.x) != 0) {
            return false;
         } else {
            return Float.compare(vector2.y, this.y) == 0;
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

   @Nonnull
   public Vector2f clone() {
      return from(this);
   }

   @Nonnull
   public String toString() {
      return "(" + this.x + ", " + this.y + ")";
   }

   @Nonnull
   public static Vector2f from(float n) {
      return n == 0.0F ? ZERO : new Vector2f(n, n);
   }

   @Nonnull
   public static Vector2f from(Vector2f v) {
      return from(v.x, v.y);
   }

   @Nonnull
   public static Vector2f from(Vector3f v) {
      return from(v.getX(), v.getY());
   }

   @Nonnull
   public static Vector2f from(Vector4f v) {
      return from(v.getX(), v.getY());
   }

   @Nonnull
   public static Vector2f from(VectorNf v) {
      return from(v.get(0), v.get(1));
   }

   @Nonnull
   public static Vector2f from(double x, double y) {
      return from((float)x, (float)y);
   }

   @Nonnull
   public static Vector2f from(float x, float y) {
      return x == 0.0F && y == 0.0F ? ZERO : new Vector2f(x, y);
   }

   @Nonnull
   public static Vector2f createRandomDirection(Random random) {
      return createDirectionRad(random.nextFloat() * ((float)Math.PI * 2F));
   }

   @Nonnull
   public static Vector2f createDirectionDeg(double angle) {
      return createDirectionDeg((float)angle);
   }

   @Nonnull
   public static Vector2f createDirectionDeg(float angle) {
      return createDirectionRad((float)Math.toRadians((double)angle));
   }

   @Nonnull
   public static Vector2f createDirectionRad(double angle) {
      return createDirectionRad((float)angle);
   }

   @Nonnull
   public static Vector2f createDirectionRad(float angle) {
      return from(TrigMath.cos((double)angle), TrigMath.sin((double)angle));
   }
}
