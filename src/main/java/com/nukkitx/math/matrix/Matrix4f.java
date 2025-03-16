package com.nukkitx.math.matrix;

import com.nukkitx.math.GenericMath;
import com.nukkitx.math.TrigMath;
import com.nukkitx.math.imaginary.Complexf;
import com.nukkitx.math.imaginary.Quaternionf;
import com.nukkitx.math.vector.Vector3f;
import com.nukkitx.math.vector.Vector4f;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Matrix4f implements Matrixf, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Matrix4f ZERO = new Matrix4f(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
   public static final Matrix4f IDENTITY = new Matrix4f(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
   private final float m00;
   private final float m01;
   private final float m02;
   private final float m03;
   private final float m10;
   private final float m11;
   private final float m12;
   private final float m13;
   private final float m20;
   private final float m21;
   private final float m22;
   private final float m23;
   private final float m30;
   private final float m31;
   private final float m32;
   private final float m33;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Matrix4f(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33) {
      this.m00 = m00;
      this.m01 = m01;
      this.m02 = m02;
      this.m03 = m03;
      this.m10 = m10;
      this.m11 = m11;
      this.m12 = m12;
      this.m13 = m13;
      this.m20 = m20;
      this.m21 = m21;
      this.m22 = m22;
      this.m23 = m23;
      this.m30 = m30;
      this.m31 = m31;
      this.m32 = m32;
      this.m33 = m33;
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
               case 3:
                  return this.m03;
            }
         case 1:
            switch (col) {
               case 0:
                  return this.m10;
               case 1:
                  return this.m11;
               case 2:
                  return this.m12;
               case 3:
                  return this.m13;
            }
         case 2:
            switch (col) {
               case 0:
                  return this.m20;
               case 1:
                  return this.m21;
               case 2:
                  return this.m22;
               case 3:
                  return this.m23;
            }
         case 3:
            switch (col) {
               case 0:
                  return this.m30;
               case 1:
                  return this.m31;
               case 2:
                  return this.m32;
               case 3:
                  return this.m33;
            }
      }

      throw new IllegalArgumentException((row >= 0 && row <= 2 ? "" : "row must be greater than zero and smaller than 3. ") + (col >= 0 && col <= 2 ? "" : "col must be greater than zero and smaller than 3."));
   }

   @Nonnull
   public Vector4f getRow(int row) {
      return Vector4f.from(this.get(row, 0), this.get(row, 1), this.get(row, 2), this.get(row, 3));
   }

   @Nonnull
   public Vector4f getColumn(int col) {
      return Vector4f.from(this.get(0, col), this.get(1, col), this.get(2, col), this.get(3, col));
   }

   @Nonnull
   public Matrix4f add(Matrix4f m) {
      return from(this.m00 + m.m00, this.m01 + m.m01, this.m02 + m.m02, this.m03 + m.m03, this.m10 + m.m10, this.m11 + m.m11, this.m12 + m.m12, this.m13 + m.m13, this.m20 + m.m20, this.m21 + m.m21, this.m22 + m.m22, this.m23 + m.m23, this.m30 + m.m30, this.m31 + m.m31, this.m32 + m.m32, this.m33 + m.m33);
   }

   @Nonnull
   public Matrix4f sub(Matrix4f m) {
      return from(this.m00 - m.m00, this.m01 - m.m01, this.m02 - m.m02, this.m03 - m.m03, this.m10 - m.m10, this.m11 - m.m11, this.m12 - m.m12, this.m13 - m.m13, this.m20 - m.m20, this.m21 - m.m21, this.m22 - m.m22, this.m23 - m.m23, this.m30 - m.m30, this.m31 - m.m31, this.m32 - m.m32, this.m33 - m.m33);
   }

   @Nonnull
   public Matrix4f mul(double a) {
      return this.mul((float)a);
   }

   @Nonnull
   public Matrix4f mul(float a) {
      return from(this.m00 * a, this.m01 * a, this.m02 * a, this.m03 * a, this.m10 * a, this.m11 * a, this.m12 * a, this.m13 * a, this.m20 * a, this.m21 * a, this.m22 * a, this.m23 * a, this.m30 * a, this.m31 * a, this.m32 * a, this.m33 * a);
   }

   @Nonnull
   public Matrix4f mul(Matrix4f m) {
      return from(this.m00 * m.m00 + this.m01 * m.m10 + this.m02 * m.m20 + this.m03 * m.m30, this.m00 * m.m01 + this.m01 * m.m11 + this.m02 * m.m21 + this.m03 * m.m31, this.m00 * m.m02 + this.m01 * m.m12 + this.m02 * m.m22 + this.m03 * m.m32, this.m00 * m.m03 + this.m01 * m.m13 + this.m02 * m.m23 + this.m03 * m.m33, this.m10 * m.m00 + this.m11 * m.m10 + this.m12 * m.m20 + this.m13 * m.m30, this.m10 * m.m01 + this.m11 * m.m11 + this.m12 * m.m21 + this.m13 * m.m31, this.m10 * m.m02 + this.m11 * m.m12 + this.m12 * m.m22 + this.m13 * m.m32, this.m10 * m.m03 + this.m11 * m.m13 + this.m12 * m.m23 + this.m13 * m.m33, this.m20 * m.m00 + this.m21 * m.m10 + this.m22 * m.m20 + this.m23 * m.m30, this.m20 * m.m01 + this.m21 * m.m11 + this.m22 * m.m21 + this.m23 * m.m31, this.m20 * m.m02 + this.m21 * m.m12 + this.m22 * m.m22 + this.m23 * m.m32, this.m20 * m.m03 + this.m21 * m.m13 + this.m22 * m.m23 + this.m23 * m.m33, this.m30 * m.m00 + this.m31 * m.m10 + this.m32 * m.m20 + this.m33 * m.m30, this.m30 * m.m01 + this.m31 * m.m11 + this.m32 * m.m21 + this.m33 * m.m31, this.m30 * m.m02 + this.m31 * m.m12 + this.m32 * m.m22 + this.m33 * m.m32, this.m30 * m.m03 + this.m31 * m.m13 + this.m32 * m.m23 + this.m33 * m.m33);
   }

   @Nonnull
   public Matrix4f div(double a) {
      return this.div((float)a);
   }

   @Nonnull
   public Matrix4f div(float a) {
      return from(this.m00 / a, this.m01 / a, this.m02 / a, this.m03 / a, this.m10 / a, this.m11 / a, this.m12 / a, this.m13 / a, this.m20 / a, this.m21 / a, this.m22 / a, this.m23 / a, this.m30 / a, this.m31 / a, this.m32 / a, this.m33 / a);
   }

   @Nonnull
   public Matrix4f div(Matrix4f m) {
      return this.mul(m.invert());
   }

   @Nonnull
   public Matrix4f pow(double pow) {
      return this.pow((float)pow);
   }

   @Nonnull
   public Matrix4f pow(float pow) {
      return from(Math.pow((double)this.m00, (double)pow), Math.pow((double)this.m01, (double)pow), Math.pow((double)this.m02, (double)pow), Math.pow((double)this.m03, (double)pow), Math.pow((double)this.m10, (double)pow), Math.pow((double)this.m11, (double)pow), Math.pow((double)this.m12, (double)pow), Math.pow((double)this.m13, (double)pow), Math.pow((double)this.m20, (double)pow), Math.pow((double)this.m21, (double)pow), Math.pow((double)this.m22, (double)pow), Math.pow((double)this.m23, (double)pow), Math.pow((double)this.m30, (double)pow), Math.pow((double)this.m31, (double)pow), Math.pow((double)this.m32, (double)pow), Math.pow((double)this.m33, (double)pow));
   }

   @Nonnull
   public Matrix4f translate(Vector3f v) {
      return this.translate(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public Matrix4f translate(double x, double y, double z) {
      return this.translate((float)x, (float)y, (float)z);
   }

   @Nonnull
   public Matrix4f translate(float x, float y, float z) {
      return createTranslation(x, y, z).mul(this);
   }

   @Nonnull
   public Matrix4f scale(double scale) {
      return this.scale((float)scale);
   }

   @Nonnull
   public Matrix4f scale(float scale) {
      return this.scale(scale, scale, scale, scale);
   }

   @Nonnull
   public Matrix4f scale(Vector4f v) {
      return this.scale(v.getX(), v.getY(), v.getZ(), v.getW());
   }

   @Nonnull
   public Matrix4f scale(double x, double y, double z, double w) {
      return this.scale((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public Matrix4f scale(float x, float y, float z, float w) {
      return createScaling(x, y, z, w).mul(this);
   }

   @Nonnull
   public Matrix4f rotate(Complexf rot) {
      return createRotation(rot).mul(this);
   }

   @Nonnull
   public Matrix4f rotate(Quaternionf rot) {
      return createRotation(rot).mul(this);
   }

   @Nonnull
   public Vector4f transform(Vector4f v) {
      return this.transform(v.getX(), v.getY(), v.getZ(), v.getW());
   }

   @Nonnull
   public Vector4f transform(double x, double y, double z, double w) {
      return this.transform((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public Vector4f transform(float x, float y, float z, float w) {
      return Vector4f.from(this.m00 * x + this.m01 * y + this.m02 * z + this.m03 * w, this.m10 * x + this.m11 * y + this.m12 * z + this.m13 * w, this.m20 * x + this.m21 * y + this.m22 * z + this.m23 * w, this.m30 * x + this.m31 * y + this.m32 * z + this.m33 * w);
   }

   @Nonnull
   public Matrix4f floor() {
      return from((float)GenericMath.floor(this.m00), (float)GenericMath.floor(this.m01), (float)GenericMath.floor(this.m02), (float)GenericMath.floor(this.m03), (float)GenericMath.floor(this.m10), (float)GenericMath.floor(this.m11), (float)GenericMath.floor(this.m12), (float)GenericMath.floor(this.m13), (float)GenericMath.floor(this.m20), (float)GenericMath.floor(this.m21), (float)GenericMath.floor(this.m22), (float)GenericMath.floor(this.m23), (float)GenericMath.floor(this.m30), (float)GenericMath.floor(this.m31), (float)GenericMath.floor(this.m32), (float)GenericMath.floor(this.m33));
   }

   @Nonnull
   public Matrix4f ceil() {
      return from(Math.ceil((double)this.m00), Math.ceil((double)this.m01), Math.ceil((double)this.m02), Math.ceil((double)this.m03), Math.ceil((double)this.m10), Math.ceil((double)this.m11), Math.ceil((double)this.m12), Math.ceil((double)this.m13), Math.ceil((double)this.m20), Math.ceil((double)this.m21), Math.ceil((double)this.m22), Math.ceil((double)this.m23), Math.ceil((double)this.m30), Math.ceil((double)this.m31), Math.ceil((double)this.m32), Math.ceil((double)this.m33));
   }

   @Nonnull
   public Matrix4f round() {
      return from((float)Math.round(this.m00), (float)Math.round(this.m01), (float)Math.round(this.m02), (float)Math.round(this.m03), (float)Math.round(this.m10), (float)Math.round(this.m11), (float)Math.round(this.m12), (float)Math.round(this.m13), (float)Math.round(this.m20), (float)Math.round(this.m21), (float)Math.round(this.m22), (float)Math.round(this.m23), (float)Math.round(this.m30), (float)Math.round(this.m31), (float)Math.round(this.m32), (float)Math.round(this.m33));
   }

   @Nonnull
   public Matrix4f abs() {
      return from(Math.abs(this.m00), Math.abs(this.m01), Math.abs(this.m02), Math.abs(this.m03), Math.abs(this.m10), Math.abs(this.m11), Math.abs(this.m12), Math.abs(this.m13), Math.abs(this.m20), Math.abs(this.m21), Math.abs(this.m22), Math.abs(this.m23), Math.abs(this.m30), Math.abs(this.m31), Math.abs(this.m32), Math.abs(this.m33));
   }

   @Nonnull
   public Matrix4f negate() {
      return from(-this.m00, -this.m01, -this.m02, -this.m03, -this.m10, -this.m11, -this.m12, -this.m13, -this.m20, -this.m21, -this.m22, -this.m23, -this.m30, -this.m31, -this.m32, -this.m33);
   }

   @Nonnull
   public Matrix4f transpose() {
      return from(this.m00, this.m10, this.m20, this.m30, this.m01, this.m11, this.m21, this.m31, this.m02, this.m12, this.m22, this.m32, this.m03, this.m13, this.m23, this.m33);
   }

   public float trace() {
      return this.m00 + this.m11 + this.m22 + this.m33;
   }

   public float determinant() {
      return this.m00 * (this.m11 * this.m22 * this.m33 + this.m21 * this.m32 * this.m13 + this.m31 * this.m12 * this.m23 - this.m31 * this.m22 * this.m13 - this.m11 * this.m32 * this.m23 - this.m21 * this.m12 * this.m33) - this.m10 * (this.m01 * this.m22 * this.m33 + this.m21 * this.m32 * this.m03 + this.m31 * this.m02 * this.m23 - this.m31 * this.m22 * this.m03 - this.m01 * this.m32 * this.m23 - this.m21 * this.m02 * this.m33) + this.m20 * (this.m01 * this.m12 * this.m33 + this.m11 * this.m32 * this.m03 + this.m31 * this.m02 * this.m13 - this.m31 * this.m12 * this.m03 - this.m01 * this.m32 * this.m13 - this.m11 * this.m02 * this.m33) - this.m30 * (this.m01 * this.m12 * this.m23 + this.m11 * this.m22 * this.m03 + this.m21 * this.m02 * this.m13 - this.m21 * this.m12 * this.m03 - this.m01 * this.m22 * this.m13 - this.m11 * this.m02 * this.m23);
   }

   @Nonnull
   public Matrix4f invert() {
      float det = this.determinant();
      if (Math.abs(det) < GenericMath.FLT_EPSILON) {
         throw new ArithmeticException("Cannot inverse a matrix with a zero determinant");
      } else {
         return from(det3(this.m11, this.m21, this.m31, this.m12, this.m22, this.m32, this.m13, this.m23, this.m33) / det, -det3(this.m01, this.m21, this.m31, this.m02, this.m22, this.m32, this.m03, this.m23, this.m33) / det, det3(this.m01, this.m11, this.m31, this.m02, this.m12, this.m32, this.m03, this.m13, this.m33) / det, -det3(this.m01, this.m11, this.m21, this.m02, this.m12, this.m22, this.m03, this.m13, this.m23) / det, -det3(this.m10, this.m20, this.m30, this.m12, this.m22, this.m32, this.m13, this.m23, this.m33) / det, det3(this.m00, this.m20, this.m30, this.m02, this.m22, this.m32, this.m03, this.m23, this.m33) / det, -det3(this.m00, this.m10, this.m30, this.m02, this.m12, this.m32, this.m03, this.m13, this.m33) / det, det3(this.m00, this.m10, this.m20, this.m02, this.m12, this.m22, this.m03, this.m13, this.m23) / det, det3(this.m10, this.m20, this.m30, this.m11, this.m21, this.m31, this.m13, this.m23, this.m33) / det, -det3(this.m00, this.m20, this.m30, this.m01, this.m21, this.m31, this.m03, this.m23, this.m33) / det, det3(this.m00, this.m10, this.m30, this.m01, this.m11, this.m31, this.m03, this.m13, this.m33) / det, -det3(this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, this.m03, this.m13, this.m23) / det, -det3(this.m10, this.m20, this.m30, this.m11, this.m21, this.m31, this.m12, this.m22, this.m32) / det, det3(this.m00, this.m20, this.m30, this.m01, this.m21, this.m31, this.m02, this.m22, this.m32) / det, -det3(this.m00, this.m10, this.m30, this.m01, this.m11, this.m31, this.m02, this.m12, this.m32) / det, det3(this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, this.m02, this.m12, this.m22) / det);
      }
   }

   @Nonnull
   public Matrix2f toMatrix2() {
      return Matrix2f.from(this);
   }

   @Nonnull
   public Matrix3f toMatrix3() {
      return Matrix3f.from(this);
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
      return columnMajor ? new float[]{this.m00, this.m10, this.m20, this.m30, this.m01, this.m11, this.m21, this.m31, this.m02, this.m12, this.m22, this.m32, this.m03, this.m13, this.m23, this.m33} : new float[]{this.m00, this.m01, this.m02, this.m03, this.m10, this.m11, this.m12, this.m13, this.m20, this.m21, this.m22, this.m23, this.m30, this.m31, this.m32, this.m33};
   }

   @Nonnull
   public Matrix4f toFloat() {
      return from(this.m00, this.m01, this.m02, this.m03, this.m10, this.m11, this.m12, this.m13, this.m20, this.m21, this.m22, this.m23, this.m30, this.m31, this.m32, this.m33);
   }

   @Nonnull
   public Matrix4d toDouble() {
      return Matrix4d.from(this.m00, this.m01, this.m02, this.m03, this.m10, this.m11, this.m12, this.m13, this.m20, this.m21, this.m22, this.m23, this.m30, this.m31, this.m32, this.m33);
   }

   @Nonnull
   public String toString() {
      return this.m00 + " " + this.m01 + " " + this.m02 + " " + this.m03 + "\n" + this.m10 + " " + this.m11 + " " + this.m12 + " " + this.m13 + "\n" + this.m20 + " " + this.m21 + " " + this.m22 + " " + this.m23 + "\n" + this.m30 + " " + this.m31 + " " + this.m32 + " " + this.m33 + "\n";
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Matrix4f)) {
         return false;
      } else {
         Matrix4f matrix4 = (Matrix4f)o;
         if (Float.compare(matrix4.m00, this.m00) != 0) {
            return false;
         } else if (Float.compare(matrix4.m01, this.m01) != 0) {
            return false;
         } else if (Float.compare(matrix4.m02, this.m02) != 0) {
            return false;
         } else if (Float.compare(matrix4.m03, this.m03) != 0) {
            return false;
         } else if (Float.compare(matrix4.m10, this.m10) != 0) {
            return false;
         } else if (Float.compare(matrix4.m11, this.m11) != 0) {
            return false;
         } else if (Float.compare(matrix4.m12, this.m12) != 0) {
            return false;
         } else if (Float.compare(matrix4.m13, this.m13) != 0) {
            return false;
         } else if (Float.compare(matrix4.m20, this.m20) != 0) {
            return false;
         } else if (Float.compare(matrix4.m21, this.m21) != 0) {
            return false;
         } else if (Float.compare(matrix4.m22, this.m22) != 0) {
            return false;
         } else if (Float.compare(matrix4.m23, this.m23) != 0) {
            return false;
         } else if (Float.compare(matrix4.m30, this.m30) != 0) {
            return false;
         } else if (Float.compare(matrix4.m31, this.m31) != 0) {
            return false;
         } else if (Float.compare(matrix4.m32, this.m32) != 0) {
            return false;
         } else {
            return Float.compare(matrix4.m33, this.m33) == 0;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         int result = this.m00 != 0.0F ? Float.hashCode(this.m00) : 0;
         result = 31 * result + (this.m01 != 0.0F ? Float.hashCode(this.m01) : 0);
         result = 31 * result + (this.m02 != 0.0F ? Float.hashCode(this.m02) : 0);
         result = 31 * result + (this.m03 != 0.0F ? Float.hashCode(this.m03) : 0);
         result = 31 * result + (this.m10 != 0.0F ? Float.hashCode(this.m10) : 0);
         result = 31 * result + (this.m11 != 0.0F ? Float.hashCode(this.m11) : 0);
         result = 31 * result + (this.m12 != 0.0F ? Float.hashCode(this.m12) : 0);
         result = 31 * result + (this.m13 != 0.0F ? Float.hashCode(this.m13) : 0);
         result = 31 * result + (this.m20 != 0.0F ? Float.hashCode(this.m20) : 0);
         result = 31 * result + (this.m21 != 0.0F ? Float.hashCode(this.m21) : 0);
         result = 31 * result + (this.m22 != 0.0F ? Float.hashCode(this.m22) : 0);
         result = 31 * result + (this.m23 != 0.0F ? Float.hashCode(this.m23) : 0);
         result = 31 * result + (this.m30 != 0.0F ? Float.hashCode(this.m30) : 0);
         result = 31 * result + (this.m31 != 0.0F ? Float.hashCode(this.m31) : 0);
         result = 31 * result + (this.m32 != 0.0F ? Float.hashCode(this.m32) : 0);
         this.hashCode = 31 * result + (this.m33 != 0.0F ? Float.hashCode(this.m33) : 0);
         this.hashed = true;
      }

      return this.hashCode;
   }

   @Nonnull
   public Matrix4f clone() {
      return from(this);
   }

   @Nonnull
   public static Matrix4f from(float n) {
      return n == 0.0F ? ZERO : new Matrix4f(n, n, n, n, n, n, n, n, n, n, n, n, n, n, n, n);
   }

   @Nonnull
   public static Matrix4f from(Matrix2f m) {
      return from(m.get(0, 0), m.get(0, 1), 0.0F, 0.0F, m.get(1, 0), m.get(1, 1), 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
   }

   @Nonnull
   public static Matrix4f from(Matrix3f m) {
      return from(m.get(0, 0), m.get(0, 1), m.get(0, 2), 0.0F, m.get(1, 0), m.get(1, 1), m.get(1, 2), 0.0F, m.get(2, 0), m.get(2, 1), m.get(2, 2), 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
   }

   @Nonnull
   public static Matrix4f from(Matrix4f m) {
      return from(m.m00, m.m01, m.m02, m.m03, m.m10, m.m11, m.m12, m.m13, m.m20, m.m21, m.m22, m.m23, m.m30, m.m31, m.m32, m.m33);
   }

   @Nonnull
   public static Matrix4f from(MatrixNf m) {
      float m00 = m.get(0, 0);
      float m01 = m.get(0, 1);
      float m10 = m.get(1, 0);
      float m11 = m.get(1, 1);
      float m02;
      float m03;
      float m12;
      float m13;
      float m20;
      float m21;
      float m22;
      float m23;
      float m30;
      float m31;
      float m32;
      float m33;
      if (m.size() > 2) {
         m02 = m.get(0, 2);
         m12 = m.get(1, 2);
         m20 = m.get(2, 0);
         m21 = m.get(2, 1);
         m22 = m.get(2, 2);
         if (m.size() > 3) {
            m03 = m.get(0, 3);
            m13 = m.get(1, 3);
            m23 = m.get(2, 3);
            m30 = m.get(3, 0);
            m31 = m.get(3, 1);
            m32 = m.get(3, 2);
            m33 = m.get(3, 3);
         } else {
            m03 = 0.0F;
            m13 = 0.0F;
            m23 = 0.0F;
            m30 = 0.0F;
            m31 = 0.0F;
            m32 = 0.0F;
            m33 = 0.0F;
         }
      } else {
         m02 = 0.0F;
         m12 = 0.0F;
         m20 = 0.0F;
         m21 = 0.0F;
         m22 = 0.0F;
         m03 = 0.0F;
         m13 = 0.0F;
         m23 = 0.0F;
         m30 = 0.0F;
         m31 = 0.0F;
         m32 = 0.0F;
         m33 = 0.0F;
      }

      return from(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
   }

   @Nonnull
   public static Matrix4f from(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33) {
      return from((float)m00, (float)m01, (float)m02, (float)m03, (float)m10, (float)m11, (float)m12, (float)m13, (float)m20, (float)m21, (float)m22, (float)m23, (float)m30, (float)m31, (float)m32, (float)m33);
   }

   @Nonnull
   public static Matrix4f from(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33) {
      return m00 == 0.0F && m01 == 0.0F && m02 == 0.0F && m03 == 0.0F && m10 == 0.0F && m11 == 0.0F && m12 == 0.0F && m13 == 0.0F && m20 == 0.0F && m21 == 0.0F && m22 == 0.0F && m23 == 0.0F && m30 == 0.0F && m31 == 0.0F && m32 == 0.0F && m33 == 0.0F ? ZERO : new Matrix4f(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
   }

   @Nonnull
   public static Matrix4f fromDiagonal(float m00, float m11, float m22, float m33) {
      return m00 == 0.0F && m11 == 0.0F && m22 == 0.0F && m33 == 0.0F ? ZERO : new Matrix4f(m00, 0.0F, 0.0F, 0.0F, 0.0F, m11, 0.0F, 0.0F, 0.0F, 0.0F, m22, 0.0F, 0.0F, 0.0F, 0.0F, m33);
   }

   @Nonnull
   public static Matrix4f createScaling(double scale) {
      return createScaling((float)scale);
   }

   @Nonnull
   public static Matrix4f createScaling(float scale) {
      return createScaling(scale, scale, scale, scale);
   }

   @Nonnull
   public static Matrix4f createScaling(Vector4f v) {
      return createScaling(v.getX(), v.getY(), v.getZ(), v.getW());
   }

   @Nonnull
   public static Matrix4f createScaling(double x, double y, double z, double w) {
      return createScaling((float)x, (float)y, (float)z, (float)w);
   }

   @Nonnull
   public static Matrix4f createScaling(float x, float y, float z, float w) {
      return from(x, 0.0F, 0.0F, 0.0F, 0.0F, y, 0.0F, 0.0F, 0.0F, 0.0F, z, 0.0F, 0.0F, 0.0F, 0.0F, w);
   }

   @Nonnull
   public static Matrix4f createTranslation(Vector3f v) {
      return createTranslation(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public static Matrix4f createTranslation(double x, double y, double z) {
      return createTranslation((float)x, (float)y, (float)z);
   }

   @Nonnull
   public static Matrix4f createTranslation(float x, float y, float z) {
      return from(1.0F, 0.0F, 0.0F, x, 0.0F, 1.0F, 0.0F, y, 0.0F, 0.0F, 1.0F, z, 0.0F, 0.0F, 0.0F, 1.0F);
   }

   @Nonnull
   public static Matrix4f createRotation(Complexf rot) {
      rot = rot.normalize();
      return from(rot.getX(), -rot.getY(), 0.0F, 0.0F, rot.getY(), rot.getX(), 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
   }

   @Nonnull
   public static Matrix4f createRotation(Quaternionf rot) {
      rot = rot.normalize();
      return from(1.0F - 2.0F * rot.getY() * rot.getY() - 2.0F * rot.getZ() * rot.getZ(), 2.0F * rot.getX() * rot.getY() - 2.0F * rot.getW() * rot.getZ(), 2.0F * rot.getX() * rot.getZ() + 2.0F * rot.getW() * rot.getY(), 0.0F, 2.0F * rot.getX() * rot.getY() + 2.0F * rot.getW() * rot.getZ(), 1.0F - 2.0F * rot.getX() * rot.getX() - 2.0F * rot.getZ() * rot.getZ(), 2.0F * rot.getY() * rot.getZ() - 2.0F * rot.getW() * rot.getX(), 0.0F, 2.0F * rot.getX() * rot.getZ() - 2.0F * rot.getW() * rot.getY(), 2.0F * rot.getY() * rot.getZ() + 2.0F * rot.getX() * rot.getW(), 1.0F - 2.0F * rot.getX() * rot.getX() - 2.0F * rot.getY() * rot.getY(), 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
   }

   @Nonnull
   public static Matrix4f createLookAt(Vector3f eye, Vector3f at, Vector3f up) {
      Vector3f f = at.sub(eye).normalize();
      Vector3f s = f.cross(up).normalize();
      Vector3f u = s.cross(f);
      Matrix4f mat = from(s.getX(), s.getY(), s.getZ(), 0.0F, u.getX(), u.getY(), u.getZ(), 0.0F, -f.getX(), -f.getY(), -f.getZ(), 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
      return mat.translate(eye.negate());
   }

   @Nonnull
   public static Matrix4f createPerspective(double fov, double aspect, double near, double far) {
      return createPerspective((float)fov, (float)aspect, (float)near, (float)far);
   }

   @Nonnull
   public static Matrix4f createPerspective(float fov, float aspect, float near, float far) {
      float scale = 1.0F / TrigMath.tan((double)(fov * 0.008726646F));
      return from(scale / aspect, 0.0F, 0.0F, 0.0F, 0.0F, scale, 0.0F, 0.0F, 0.0F, 0.0F, (far + near) / (near - far), 2.0F * far * near / (near - far), 0.0F, 0.0F, -1.0F, 0.0F);
   }

   @Nonnull
   public static Matrix4f createOrthographic(double right, double left, double top, double bottom, double near, double far) {
      return createOrthographic((float)right, (float)left, (float)top, (float)bottom, (float)near, (float)far);
   }

   @Nonnull
   public static Matrix4f createOrthographic(float right, float left, float top, float bottom, float near, float far) {
      return from(2.0F / (right - left), 0.0F, 0.0F, -(right + left) / (right - left), 0.0F, 2.0F / (top - bottom), 0.0F, -(top + bottom) / (top - bottom), 0.0F, 0.0F, -2.0F / (far - near), -(far + near) / (far - near), 0.0F, 0.0F, 0.0F, 1.0F);
   }

   private static float det3(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
      return m00 * (m11 * m22 - m12 * m21) - m01 * (m10 * m22 - m12 * m20) + m02 * (m10 * m21 - m11 * m20);
   }
}
