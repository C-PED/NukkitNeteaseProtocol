package com.nukkitx.math.matrix;

import com.nukkitx.math.GenericMath;
import com.nukkitx.math.imaginary.Complexf;
import com.nukkitx.math.imaginary.Quaternionf;
import com.nukkitx.math.vector.Vector2f;
import com.nukkitx.math.vector.Vector3f;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Matrix3f implements Matrixf, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Matrix3f ZERO = new Matrix3f(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
   public static final Matrix3f IDENTITY = new Matrix3f(1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F);
   private final float m00;
   private final float m01;
   private final float m02;
   private final float m10;
   private final float m11;
   private final float m12;
   private final float m20;
   private final float m21;
   private final float m22;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Matrix3f(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
      this.m00 = m00;
      this.m01 = m01;
      this.m02 = m02;
      this.m10 = m10;
      this.m11 = m11;
      this.m12 = m12;
      this.m20 = m20;
      this.m21 = m21;
      this.m22 = m22;
   }

   public float get(int row, int col) {
      switch (row) {
         case 0:
            switch (col) {
               case 0:
                  return this.m00;
               case 1:
                  return this.m01;
               case 2:
                  return this.m02;
            }
         case 1:
            switch (col) {
               case 0:
                  return this.m10;
               case 1:
                  return this.m11;
               case 2:
                  return this.m12;
            }
         case 2:
            switch (col) {
               case 0:
                  return this.m20;
               case 1:
                  return this.m21;
               case 2:
                  return this.m22;
            }
      }

      throw new IllegalArgumentException((row >= 0 && row <= 2 ? "" : "row must be greater than zero and smaller than 3. ") + (col >= 0 && col <= 2 ? "" : "col must be greater than zero and smaller than 3."));
   }

   @Nonnull
   public Vector3f getRow(int row) {
      return Vector3f.from(this.get(row, 0), this.get(row, 1), this.get(row, 2));
   }

   @Nonnull
   public Vector3f getColumn(int col) {
      return Vector3f.from(this.get(0, col), this.get(1, col), this.get(2, col));
   }

   @Nonnull
   public Matrix3f add(Matrix3f m) {
      return from(this.m00 + m.m00, this.m01 + m.m01, this.m02 + m.m02, this.m10 + m.m10, this.m11 + m.m11, this.m12 + m.m12, this.m20 + m.m20, this.m21 + m.m21, this.m22 + m.m22);
   }

   @Nonnull
   public Matrix3f sub(Matrix3f m) {
      return from(this.m00 - m.m00, this.m01 - m.m01, this.m02 - m.m02, this.m10 - m.m10, this.m11 - m.m11, this.m12 - m.m12, this.m20 - m.m20, this.m21 - m.m21, this.m22 - m.m22);
   }

   @Nonnull
   public Matrix3f mul(double a) {
      return this.mul((float)a);
   }

   @Nonnull
   public Matrix3f mul(float a) {
      return from(this.m00 * a, this.m01 * a, this.m02 * a, this.m10 * a, this.m11 * a, this.m12 * a, this.m20 * a, this.m21 * a, this.m22 * a);
   }

   @Nonnull
   public Matrix3f mul(Matrix3f m) {
      return from(this.m00 * m.m00 + this.m01 * m.m10 + this.m02 * m.m20, this.m00 * m.m01 + this.m01 * m.m11 + this.m02 * m.m21, this.m00 * m.m02 + this.m01 * m.m12 + this.m02 * m.m22, this.m10 * m.m00 + this.m11 * m.m10 + this.m12 * m.m20, this.m10 * m.m01 + this.m11 * m.m11 + this.m12 * m.m21, this.m10 * m.m02 + this.m11 * m.m12 + this.m12 * m.m22, this.m20 * m.m00 + this.m21 * m.m10 + this.m22 * m.m20, this.m20 * m.m01 + this.m21 * m.m11 + this.m22 * m.m21, this.m20 * m.m02 + this.m21 * m.m12 + this.m22 * m.m22);
   }

   @Nonnull
   public Matrix3f div(double a) {
      return this.div((float)a);
   }

   @Nonnull
   public Matrix3f div(float a) {
      return from(this.m00 / a, this.m01 / a, this.m02 / a, this.m10 / a, this.m11 / a, this.m12 / a, this.m20 / a, this.m21 / a, this.m22 / a);
   }

   @Nonnull
   public Matrix3f div(Matrix3f m) {
      return this.mul(m.invert());
   }

   @Nonnull
   public Matrix3f pow(double pow) {
      return this.pow((float)pow);
   }

   @Nonnull
   public Matrix3f pow(float pow) {
      return from(Math.pow((double)this.m00, (double)pow), Math.pow((double)this.m01, (double)pow), Math.pow((double)this.m02, (double)pow), Math.pow((double)this.m10, (double)pow), Math.pow((double)this.m11, (double)pow), Math.pow((double)this.m12, (double)pow), Math.pow((double)this.m20, (double)pow), Math.pow((double)this.m21, (double)pow), Math.pow((double)this.m22, (double)pow));
   }

   @Nonnull
   public Matrix3f translate(Vector2f v) {
      return this.translate(v.getX(), v.getY());
   }

   @Nonnull
   public Matrix3f translate(double x, double y) {
      return this.translate((float)x, (float)y);
   }

   @Nonnull
   public Matrix3f translate(float x, float y) {
      return createTranslation(x, y).mul(this);
   }

   @Nonnull
   public Matrix3f scale(double scale) {
      return this.scale((float)scale);
   }

   @Nonnull
   public Matrix3f scale(float scale) {
      return this.scale(scale, scale, scale);
   }

   @Nonnull
   public Matrix3f scale(Vector3f v) {
      return this.scale(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public Matrix3f scale(double x, double y, double z) {
      return this.scale((float)x, (float)y, (float)z);
   }

   @Nonnull
   public Matrix3f scale(float x, float y, float z) {
      return createScaling(x, y, z).mul(this);
   }

   @Nonnull
   public Matrix3f rotate(Complexf rot) {
      return createRotation(rot).mul(this);
   }

   @Nonnull
   public Matrix3f rotate(Quaternionf rot) {
      return createRotation(rot).mul(this);
   }

   @Nonnull
   public Vector3f transform(Vector3f v) {
      return this.transform(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public Vector3f transform(double x, double y, double z) {
      return this.transform((float)x, (float)y, (float)z);
   }

   @Nonnull
   public Vector3f transform(float x, float y, float z) {
      return Vector3f.from(this.m00 * x + this.m01 * y + this.m02 * z, this.m10 * x + this.m11 * y + this.m12 * z, this.m20 * x + this.m21 * y + this.m22 * z);
   }

   @Nonnull
   public Matrix3f floor() {
      return from((float)GenericMath.floor(this.m00), (float)GenericMath.floor(this.m01), (float)GenericMath.floor(this.m02), (float)GenericMath.floor(this.m10), (float)GenericMath.floor(this.m11), (float)GenericMath.floor(this.m12), (float)GenericMath.floor(this.m20), (float)GenericMath.floor(this.m21), (float)GenericMath.floor(this.m22));
   }

   @Nonnull
   public Matrix3f ceil() {
      return from(Math.ceil((double)this.m00), Math.ceil((double)this.m01), Math.ceil((double)this.m02), Math.ceil((double)this.m10), Math.ceil((double)this.m11), Math.ceil((double)this.m12), Math.ceil((double)this.m20), Math.ceil((double)this.m21), Math.ceil((double)this.m22));
   }

   @Nonnull
   public Matrix3f round() {
      return from((float)Math.round(this.m00), (float)Math.round(this.m01), (float)Math.round(this.m02), (float)Math.round(this.m10), (float)Math.round(this.m11), (float)Math.round(this.m12), (float)Math.round(this.m20), (float)Math.round(this.m21), (float)Math.round(this.m22));
   }

   @Nonnull
   public Matrix3f abs() {
      return from(Math.abs(this.m00), Math.abs(this.m01), Math.abs(this.m02), Math.abs(this.m10), Math.abs(this.m11), Math.abs(this.m12), Math.abs(this.m20), Math.abs(this.m21), Math.abs(this.m22));
   }

   @Nonnull
   public Matrix3f negate() {
      return from(-this.m00, -this.m01, -this.m02, -this.m10, -this.m11, -this.m12, -this.m20, -this.m21, -this.m22);
   }

   @Nonnull
   public Matrix3f transpose() {
      return from(this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, this.m02, this.m12, this.m22);
   }

   public float trace() {
      return this.m00 + this.m11 + this.m22;
   }

   public float determinant() {
      return this.m00 * (this.m11 * this.m22 - this.m12 * this.m21) - this.m01 * (this.m10 * this.m22 - this.m12 * this.m20) + this.m02 * (this.m10 * this.m21 - this.m11 * this.m20);
   }

   @Nonnull
   public Matrix3f invert() {
      float det = this.determinant();
      if (Math.abs(det) < GenericMath.FLT_EPSILON) {
         throw new ArithmeticException("Cannot inverse a matrix with a zero determinant");
      } else {
         return from((this.m11 * this.m22 - this.m21 * this.m12) / det, -(this.m01 * this.m22 - this.m21 * this.m02) / det, (this.m01 * this.m12 - this.m02 * this.m11) / det, -(this.m10 * this.m22 - this.m20 * this.m12) / det, (this.m00 * this.m22 - this.m20 * this.m02) / det, -(this.m00 * this.m12 - this.m10 * this.m02) / det, (this.m10 * this.m21 - this.m20 * this.m11) / det, -(this.m00 * this.m21 - this.m20 * this.m01) / det, (this.m00 * this.m11 - this.m01 * this.m10) / det);
      }
   }

   @Nonnull
   public Matrix2f toMatrix2() {
      return Matrix2f.from(this);
   }

   @Nonnull
   public Matrix4f toMatrix4() {
      return Matrix4f.from(this);
   }

   @Nonnull
   public MatrixNf toMatrixN() {
      return MatrixNf.from(this);
   }

   @Nonnull
   public float[] toArray() {
      return this.toArray(false);
   }

   @Nonnull
   public float[] toArray(boolean columnMajor) {
      return columnMajor ? new float[]{this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, this.m02, this.m12, this.m22} : new float[]{this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22};
   }

   @Nonnull
   public Matrix3f toFloat() {
      return from(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22);
   }

   @Nonnull
   public Matrix3d toDouble() {
      return Matrix3d.from(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22);
   }

   @Nonnull
   public String toString() {
      return this.m00 + " " + this.m01 + " " + this.m02 + "\n" + this.m10 + " " + this.m11 + " " + this.m12 + "\n" + this.m20 + " " + this.m21 + " " + this.m22 + "\n";
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Matrix3f)) {
         return false;
      } else {
         Matrix3f matrix3 = (Matrix3f)o;
         if (Float.compare(matrix3.m00, this.m00) != 0) {
            return false;
         } else if (Float.compare(matrix3.m01, this.m01) != 0) {
            return false;
         } else if (Float.compare(matrix3.m02, this.m02) != 0) {
            return false;
         } else if (Float.compare(matrix3.m10, this.m10) != 0) {
            return false;
         } else if (Float.compare(matrix3.m11, this.m11) != 0) {
            return false;
         } else if (Float.compare(matrix3.m12, this.m12) != 0) {
            return false;
         } else if (Float.compare(matrix3.m20, this.m20) != 0) {
            return false;
         } else if (Float.compare(matrix3.m21, this.m21) != 0) {
            return false;
         } else {
            return Float.compare(matrix3.m22, this.m22) == 0;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         int result = this.m00 != 0.0F ? Float.hashCode(this.m00) : 0;
         result = 31 * result + (this.m01 != 0.0F ? Float.hashCode(this.m01) : 0);
         result = 31 * result + (this.m02 != 0.0F ? Float.hashCode(this.m02) : 0);
         result = 31 * result + (this.m10 != 0.0F ? Float.hashCode(this.m10) : 0);
         result = 31 * result + (this.m11 != 0.0F ? Float.hashCode(this.m11) : 0);
         result = 31 * result + (this.m12 != 0.0F ? Float.hashCode(this.m12) : 0);
         result = 31 * result + (this.m20 != 0.0F ? Float.hashCode(this.m20) : 0);
         result = 31 * result + (this.m21 != 0.0F ? Float.hashCode(this.m21) : 0);
         this.hashCode = 31 * result + (this.m22 != 0.0F ? Float.hashCode(this.m22) : 0);
         this.hashed = true;
      }

      return this.hashCode;
   }

   @Nonnull
   public Matrix3f clone() {
      return from(this);
   }

   @Nonnull
   public static Matrix3f from(float n) {
      return n == 0.0F ? ZERO : new Matrix3f(n, n, n, n, n, n, n, n, n);
   }

   @Nonnull
   public static Matrix3f from(Matrix2f m) {
      return from(m.get(0, 0), m.get(0, 1), 0.0F, m.get(1, 0), m.get(1, 1), 0.0F, 0.0F, 0.0F, 0.0F);
   }

   @Nonnull
   public static Matrix3f from(Matrix3f m) {
      return from(m.m00, m.m01, m.m02, m.m10, m.m11, m.m12, m.m20, m.m21, m.m22);
   }

   @Nonnull
   public static Matrix3f from(Matrix4f m) {
      return from(m.get(0, 0), m.get(0, 1), m.get(0, 2), m.get(1, 0), m.get(1, 1), m.get(1, 2), m.get(2, 0), m.get(2, 1), m.get(2, 2));
   }

   @Nonnull
   public static Matrix3f from(MatrixNf m) {
      float m00 = m.get(0, 0);
      float m01 = m.get(0, 1);
      float m10 = m.get(1, 0);
      float m11 = m.get(1, 1);
      float m02;
      float m12;
      float m20;
      float m21;
      float m22;
      if (m.size() > 2) {
         m02 = m.get(0, 2);
         m12 = m.get(1, 2);
         m20 = m.get(2, 0);
         m21 = m.get(2, 1);
         m22 = m.get(2, 2);
      } else {
         m02 = 0.0F;
         m12 = 0.0F;
         m20 = 0.0F;
         m21 = 0.0F;
         m22 = 0.0F;
      }

      return from(m00, m01, m02, m10, m11, m12, m20, m21, m22);
   }

   @Nonnull
   public static Matrix3f from(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
      return from((float)m00, (float)m01, (float)m02, (float)m10, (float)m11, (float)m12, (float)m20, (float)m21, (float)m22);
   }

   @Nonnull
   public static Matrix3f from(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
      return m00 == 0.0F && m01 == 0.0F && m02 == 0.0F && m10 == 0.0F && m11 == 0.0F && m12 == 0.0F && m20 == 0.0F && m21 == 0.0F && m22 == 0.0F ? ZERO : new Matrix3f(m00, m01, m02, m10, m11, m12, m20, m21, m22);
   }

   @Nonnull
   public static Matrix3f fromDiagonal(float m00, float m11, float m22) {
      return m00 == 0.0F && m11 == 0.0F && m22 == 0.0F ? ZERO : new Matrix3f(m00, 0.0F, 0.0F, 0.0F, m11, 0.0F, 0.0F, 0.0F, m22);
   }

   @Nonnull
   public static Matrix3f createScaling(double scale) {
      return createScaling((float)scale);
   }

   @Nonnull
   public static Matrix3f createScaling(float scale) {
      return createScaling(scale, scale, scale);
   }

   @Nonnull
   public static Matrix3f createScaling(Vector3f v) {
      return createScaling(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public static Matrix3f createScaling(double x, double y, double z) {
      return createScaling((float)x, (float)y, (float)z);
   }

   @Nonnull
   public static Matrix3f createScaling(float x, float y, float z) {
      return from(x, 0.0F, 0.0F, 0.0F, y, 0.0F, 0.0F, 0.0F, z);
   }

   @Nonnull
   public static Matrix3f createTranslation(Vector2f v) {
      return createTranslation(v.getX(), v.getY());
   }

   @Nonnull
   public static Matrix3f createTranslation(double x, double y) {
      return createTranslation((float)x, (float)y);
   }

   @Nonnull
   public static Matrix3f createTranslation(float x, float y) {
      return from(1.0F, 0.0F, x, 0.0F, 1.0F, y, 0.0F, 0.0F, 1.0F);
   }

   @Nonnull
   public static Matrix3f createRotation(Complexf rot) {
      rot = rot.normalize();
      return from(rot.getX(), -rot.getY(), 0.0F, rot.getY(), rot.getX(), 0.0F, 0.0F, 0.0F, 1.0F);
   }

   @Nonnull
   public static Matrix3f createRotation(Quaternionf rot) {
      rot = rot.normalize();
      return from(1.0F - 2.0F * rot.getY() * rot.getY() - 2.0F * rot.getZ() * rot.getZ(), 2.0F * rot.getX() * rot.getY() - 2.0F * rot.getW() * rot.getZ(), 2.0F * rot.getX() * rot.getZ() + 2.0F * rot.getW() * rot.getY(), 2.0F * rot.getX() * rot.getY() + 2.0F * rot.getW() * rot.getZ(), 1.0F - 2.0F * rot.getX() * rot.getX() - 2.0F * rot.getZ() * rot.getZ(), 2.0F * rot.getY() * rot.getZ() - 2.0F * rot.getW() * rot.getX(), 2.0F * rot.getX() * rot.getZ() - 2.0F * rot.getW() * rot.getY(), 2.0F * rot.getY() * rot.getZ() + 2.0F * rot.getX() * rot.getW(), 1.0F - 2.0F * rot.getX() * rot.getX() - 2.0F * rot.getY() * rot.getY());
   }
}
