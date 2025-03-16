package com.nukkitx.math.matrix;

import com.nukkitx.math.GenericMath;
import com.nukkitx.math.TrigMath;
import com.nukkitx.math.imaginary.Complexd;
import com.nukkitx.math.imaginary.Quaterniond;
import com.nukkitx.math.vector.Vector3d;
import com.nukkitx.math.vector.Vector4d;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Matrix4d implements Matrixd, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Matrix4d ZERO = new Matrix4d((double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F);
   public static final Matrix4d IDENTITY = new Matrix4d((double)1.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F);
   private final double m00;
   private final double m01;
   private final double m02;
   private final double m03;
   private final double m10;
   private final double m11;
   private final double m12;
   private final double m13;
   private final double m20;
   private final double m21;
   private final double m22;
   private final double m23;
   private final double m30;
   private final double m31;
   private final double m32;
   private final double m33;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Matrix4d(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33) {
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

   public double get(int row, int col) {
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
   public Vector4d getRow(int row) {
      return Vector4d.from(this.get(row, 0), this.get(row, 1), this.get(row, 2), this.get(row, 3));
   }

   @Nonnull
   public Vector4d getColumn(int col) {
      return Vector4d.from(this.get(0, col), this.get(1, col), this.get(2, col), this.get(3, col));
   }

   @Nonnull
   public Matrix4d add(Matrix4d m) {
      return from(this.m00 + m.m00, this.m01 + m.m01, this.m02 + m.m02, this.m03 + m.m03, this.m10 + m.m10, this.m11 + m.m11, this.m12 + m.m12, this.m13 + m.m13, this.m20 + m.m20, this.m21 + m.m21, this.m22 + m.m22, this.m23 + m.m23, this.m30 + m.m30, this.m31 + m.m31, this.m32 + m.m32, this.m33 + m.m33);
   }

   @Nonnull
   public Matrix4d sub(Matrix4d m) {
      return from(this.m00 - m.m00, this.m01 - m.m01, this.m02 - m.m02, this.m03 - m.m03, this.m10 - m.m10, this.m11 - m.m11, this.m12 - m.m12, this.m13 - m.m13, this.m20 - m.m20, this.m21 - m.m21, this.m22 - m.m22, this.m23 - m.m23, this.m30 - m.m30, this.m31 - m.m31, this.m32 - m.m32, this.m33 - m.m33);
   }

   @Nonnull
   public Matrix4d mul(float a) {
      return this.mul((double)a);
   }

   @Nonnull
   public Matrix4d mul(double a) {
      return from(this.m00 * a, this.m01 * a, this.m02 * a, this.m03 * a, this.m10 * a, this.m11 * a, this.m12 * a, this.m13 * a, this.m20 * a, this.m21 * a, this.m22 * a, this.m23 * a, this.m30 * a, this.m31 * a, this.m32 * a, this.m33 * a);
   }

   @Nonnull
   public Matrix4d mul(Matrix4d m) {
      return from(this.m00 * m.m00 + this.m01 * m.m10 + this.m02 * m.m20 + this.m03 * m.m30, this.m00 * m.m01 + this.m01 * m.m11 + this.m02 * m.m21 + this.m03 * m.m31, this.m00 * m.m02 + this.m01 * m.m12 + this.m02 * m.m22 + this.m03 * m.m32, this.m00 * m.m03 + this.m01 * m.m13 + this.m02 * m.m23 + this.m03 * m.m33, this.m10 * m.m00 + this.m11 * m.m10 + this.m12 * m.m20 + this.m13 * m.m30, this.m10 * m.m01 + this.m11 * m.m11 + this.m12 * m.m21 + this.m13 * m.m31, this.m10 * m.m02 + this.m11 * m.m12 + this.m12 * m.m22 + this.m13 * m.m32, this.m10 * m.m03 + this.m11 * m.m13 + this.m12 * m.m23 + this.m13 * m.m33, this.m20 * m.m00 + this.m21 * m.m10 + this.m22 * m.m20 + this.m23 * m.m30, this.m20 * m.m01 + this.m21 * m.m11 + this.m22 * m.m21 + this.m23 * m.m31, this.m20 * m.m02 + this.m21 * m.m12 + this.m22 * m.m22 + this.m23 * m.m32, this.m20 * m.m03 + this.m21 * m.m13 + this.m22 * m.m23 + this.m23 * m.m33, this.m30 * m.m00 + this.m31 * m.m10 + this.m32 * m.m20 + this.m33 * m.m30, this.m30 * m.m01 + this.m31 * m.m11 + this.m32 * m.m21 + this.m33 * m.m31, this.m30 * m.m02 + this.m31 * m.m12 + this.m32 * m.m22 + this.m33 * m.m32, this.m30 * m.m03 + this.m31 * m.m13 + this.m32 * m.m23 + this.m33 * m.m33);
   }

   @Nonnull
   public Matrix4d div(float a) {
      return this.div((double)a);
   }

   @Nonnull
   public Matrix4d div(double a) {
      return from(this.m00 / a, this.m01 / a, this.m02 / a, this.m03 / a, this.m10 / a, this.m11 / a, this.m12 / a, this.m13 / a, this.m20 / a, this.m21 / a, this.m22 / a, this.m23 / a, this.m30 / a, this.m31 / a, this.m32 / a, this.m33 / a);
   }

   @Nonnull
   public Matrix4d div(Matrix4d m) {
      return this.mul(m.invert());
   }

   @Nonnull
   public Matrix4d pow(float pow) {
      return this.pow((double)pow);
   }

   @Nonnull
   public Matrix4d pow(double pow) {
      return from(Math.pow(this.m00, pow), Math.pow(this.m01, pow), Math.pow(this.m02, pow), Math.pow(this.m03, pow), Math.pow(this.m10, pow), Math.pow(this.m11, pow), Math.pow(this.m12, pow), Math.pow(this.m13, pow), Math.pow(this.m20, pow), Math.pow(this.m21, pow), Math.pow(this.m22, pow), Math.pow(this.m23, pow), Math.pow(this.m30, pow), Math.pow(this.m31, pow), Math.pow(this.m32, pow), Math.pow(this.m33, pow));
   }

   @Nonnull
   public Matrix4d translate(Vector3d v) {
      return this.translate(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public Matrix4d translate(float x, float y, float z) {
      return this.translate((double)x, (double)y, (double)z);
   }

   @Nonnull
   public Matrix4d translate(double x, double y, double z) {
      return createTranslation(x, y, z).mul(this);
   }

   @Nonnull
   public Matrix4d scale(float scale) {
      return this.scale((double)scale);
   }

   @Nonnull
   public Matrix4d scale(double scale) {
      return this.scale(scale, scale, scale, scale);
   }

   @Nonnull
   public Matrix4d scale(Vector4d v) {
      return this.scale(v.getX(), v.getY(), v.getZ(), v.getW());
   }

   @Nonnull
   public Matrix4d scale(float x, float y, float z, float w) {
      return this.scale((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public Matrix4d scale(double x, double y, double z, double w) {
      return createScaling(x, y, z, w).mul(this);
   }

   @Nonnull
   public Matrix4d rotate(Complexd rot) {
      return createRotation(rot).mul(this);
   }

   @Nonnull
   public Matrix4d rotate(Quaterniond rot) {
      return createRotation(rot).mul(this);
   }

   @Nonnull
   public Vector4d transform(Vector4d v) {
      return this.transform(v.getX(), v.getY(), v.getZ(), v.getW());
   }

   @Nonnull
   public Vector4d transform(float x, float y, float z, float w) {
      return this.transform((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public Vector4d transform(double x, double y, double z, double w) {
      return Vector4d.from(this.m00 * x + this.m01 * y + this.m02 * z + this.m03 * w, this.m10 * x + this.m11 * y + this.m12 * z + this.m13 * w, this.m20 * x + this.m21 * y + this.m22 * z + this.m23 * w, this.m30 * x + this.m31 * y + this.m32 * z + this.m33 * w);
   }

   @Nonnull
   public Matrix4d floor() {
      return from((float)GenericMath.floor(this.m00), (float)GenericMath.floor(this.m01), (float)GenericMath.floor(this.m02), (float)GenericMath.floor(this.m03), (float)GenericMath.floor(this.m10), (float)GenericMath.floor(this.m11), (float)GenericMath.floor(this.m12), (float)GenericMath.floor(this.m13), (float)GenericMath.floor(this.m20), (float)GenericMath.floor(this.m21), (float)GenericMath.floor(this.m22), (float)GenericMath.floor(this.m23), (float)GenericMath.floor(this.m30), (float)GenericMath.floor(this.m31), (float)GenericMath.floor(this.m32), (float)GenericMath.floor(this.m33));
   }

   @Nonnull
   public Matrix4d ceil() {
      return from(Math.ceil(this.m00), Math.ceil(this.m01), Math.ceil(this.m02), Math.ceil(this.m03), Math.ceil(this.m10), Math.ceil(this.m11), Math.ceil(this.m12), Math.ceil(this.m13), Math.ceil(this.m20), Math.ceil(this.m21), Math.ceil(this.m22), Math.ceil(this.m23), Math.ceil(this.m30), Math.ceil(this.m31), Math.ceil(this.m32), Math.ceil(this.m33));
   }

   @Nonnull
   public Matrix4d round() {
      return from((float)Math.round(this.m00), (float)Math.round(this.m01), (float)Math.round(this.m02), (float)Math.round(this.m03), (float)Math.round(this.m10), (float)Math.round(this.m11), (float)Math.round(this.m12), (float)Math.round(this.m13), (float)Math.round(this.m20), (float)Math.round(this.m21), (float)Math.round(this.m22), (float)Math.round(this.m23), (float)Math.round(this.m30), (float)Math.round(this.m31), (float)Math.round(this.m32), (float)Math.round(this.m33));
   }

   @Nonnull
   public Matrix4d abs() {
      return from(Math.abs(this.m00), Math.abs(this.m01), Math.abs(this.m02), Math.abs(this.m03), Math.abs(this.m10), Math.abs(this.m11), Math.abs(this.m12), Math.abs(this.m13), Math.abs(this.m20), Math.abs(this.m21), Math.abs(this.m22), Math.abs(this.m23), Math.abs(this.m30), Math.abs(this.m31), Math.abs(this.m32), Math.abs(this.m33));
   }

   @Nonnull
   public Matrix4d negate() {
      return from(-this.m00, -this.m01, -this.m02, -this.m03, -this.m10, -this.m11, -this.m12, -this.m13, -this.m20, -this.m21, -this.m22, -this.m23, -this.m30, -this.m31, -this.m32, -this.m33);
   }

   @Nonnull
   public Matrix4d transpose() {
      return from(this.m00, this.m10, this.m20, this.m30, this.m01, this.m11, this.m21, this.m31, this.m02, this.m12, this.m22, this.m32, this.m03, this.m13, this.m23, this.m33);
   }

   public double trace() {
      return this.m00 + this.m11 + this.m22 + this.m33;
   }

   public double determinant() {
      return this.m00 * (this.m11 * this.m22 * this.m33 + this.m21 * this.m32 * this.m13 + this.m31 * this.m12 * this.m23 - this.m31 * this.m22 * this.m13 - this.m11 * this.m32 * this.m23 - this.m21 * this.m12 * this.m33) - this.m10 * (this.m01 * this.m22 * this.m33 + this.m21 * this.m32 * this.m03 + this.m31 * this.m02 * this.m23 - this.m31 * this.m22 * this.m03 - this.m01 * this.m32 * this.m23 - this.m21 * this.m02 * this.m33) + this.m20 * (this.m01 * this.m12 * this.m33 + this.m11 * this.m32 * this.m03 + this.m31 * this.m02 * this.m13 - this.m31 * this.m12 * this.m03 - this.m01 * this.m32 * this.m13 - this.m11 * this.m02 * this.m33) - this.m30 * (this.m01 * this.m12 * this.m23 + this.m11 * this.m22 * this.m03 + this.m21 * this.m02 * this.m13 - this.m21 * this.m12 * this.m03 - this.m01 * this.m22 * this.m13 - this.m11 * this.m02 * this.m23);
   }

   @Nonnull
   public Matrix4d invert() {
      double det = this.determinant();
      if (Math.abs(det) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot inverse a matrix with a zero determinant");
      } else {
         return from(det3(this.m11, this.m21, this.m31, this.m12, this.m22, this.m32, this.m13, this.m23, this.m33) / det, -det3(this.m01, this.m21, this.m31, this.m02, this.m22, this.m32, this.m03, this.m23, this.m33) / det, det3(this.m01, this.m11, this.m31, this.m02, this.m12, this.m32, this.m03, this.m13, this.m33) / det, -det3(this.m01, this.m11, this.m21, this.m02, this.m12, this.m22, this.m03, this.m13, this.m23) / det, -det3(this.m10, this.m20, this.m30, this.m12, this.m22, this.m32, this.m13, this.m23, this.m33) / det, det3(this.m00, this.m20, this.m30, this.m02, this.m22, this.m32, this.m03, this.m23, this.m33) / det, -det3(this.m00, this.m10, this.m30, this.m02, this.m12, this.m32, this.m03, this.m13, this.m33) / det, det3(this.m00, this.m10, this.m20, this.m02, this.m12, this.m22, this.m03, this.m13, this.m23) / det, det3(this.m10, this.m20, this.m30, this.m11, this.m21, this.m31, this.m13, this.m23, this.m33) / det, -det3(this.m00, this.m20, this.m30, this.m01, this.m21, this.m31, this.m03, this.m23, this.m33) / det, det3(this.m00, this.m10, this.m30, this.m01, this.m11, this.m31, this.m03, this.m13, this.m33) / det, -det3(this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, this.m03, this.m13, this.m23) / det, -det3(this.m10, this.m20, this.m30, this.m11, this.m21, this.m31, this.m12, this.m22, this.m32) / det, det3(this.m00, this.m20, this.m30, this.m01, this.m21, this.m31, this.m02, this.m22, this.m32) / det, -det3(this.m00, this.m10, this.m30, this.m01, this.m11, this.m31, this.m02, this.m12, this.m32) / det, det3(this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, this.m02, this.m12, this.m22) / det);
      }
   }

   @Nonnull
   public Matrix2d toMatrix2() {
      return Matrix2d.from(this);
   }

   @Nonnull
   public Matrix3d toMatrix3() {
      return Matrix3d.from(this);
   }

   @Nonnull
   public MatrixNd toMatrixN() {
      return MatrixNd.from(this);
   }

   @Nonnull
   public double[] toArray() {
      return this.toArray(false);
   }

   @Nonnull
   public double[] toArray(boolean columnMajor) {
      return columnMajor ? new double[]{this.m00, this.m10, this.m20, this.m30, this.m01, this.m11, this.m21, this.m31, this.m02, this.m12, this.m22, this.m32, this.m03, this.m13, this.m23, this.m33} : new double[]{this.m00, this.m01, this.m02, this.m03, this.m10, this.m11, this.m12, this.m13, this.m20, this.m21, this.m22, this.m23, this.m30, this.m31, this.m32, this.m33};
   }

   @Nonnull
   public Matrix4f toFloat() {
      return Matrix4f.from(this.m00, this.m01, this.m02, this.m03, this.m10, this.m11, this.m12, this.m13, this.m20, this.m21, this.m22, this.m23, this.m30, this.m31, this.m32, this.m33);
   }

   @Nonnull
   public Matrix4d toDouble() {
      return from(this.m00, this.m01, this.m02, this.m03, this.m10, this.m11, this.m12, this.m13, this.m20, this.m21, this.m22, this.m23, this.m30, this.m31, this.m32, this.m33);
   }

   @Nonnull
   public String toString() {
      return this.m00 + " " + this.m01 + " " + this.m02 + " " + this.m03 + "\n" + this.m10 + " " + this.m11 + " " + this.m12 + " " + this.m13 + "\n" + this.m20 + " " + this.m21 + " " + this.m22 + " " + this.m23 + "\n" + this.m30 + " " + this.m31 + " " + this.m32 + " " + this.m33 + "\n";
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Matrix4d)) {
         return false;
      } else {
         Matrix4d matrix4 = (Matrix4d)o;
         if (Double.compare(matrix4.m00, this.m00) != 0) {
            return false;
         } else if (Double.compare(matrix4.m01, this.m01) != 0) {
            return false;
         } else if (Double.compare(matrix4.m02, this.m02) != 0) {
            return false;
         } else if (Double.compare(matrix4.m03, this.m03) != 0) {
            return false;
         } else if (Double.compare(matrix4.m10, this.m10) != 0) {
            return false;
         } else if (Double.compare(matrix4.m11, this.m11) != 0) {
            return false;
         } else if (Double.compare(matrix4.m12, this.m12) != 0) {
            return false;
         } else if (Double.compare(matrix4.m13, this.m13) != 0) {
            return false;
         } else if (Double.compare(matrix4.m20, this.m20) != 0) {
            return false;
         } else if (Double.compare(matrix4.m21, this.m21) != 0) {
            return false;
         } else if (Double.compare(matrix4.m22, this.m22) != 0) {
            return false;
         } else if (Double.compare(matrix4.m23, this.m23) != 0) {
            return false;
         } else if (Double.compare(matrix4.m30, this.m30) != 0) {
            return false;
         } else if (Double.compare(matrix4.m31, this.m31) != 0) {
            return false;
         } else if (Double.compare(matrix4.m32, this.m32) != 0) {
            return false;
         } else {
            return Double.compare(matrix4.m33, this.m33) == 0;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         int result = this.m00 != (double)0.0F ? Double.hashCode(this.m00) : 0;
         result = 31 * result + (this.m01 != (double)0.0F ? Double.hashCode(this.m01) : 0);
         result = 31 * result + (this.m02 != (double)0.0F ? Double.hashCode(this.m02) : 0);
         result = 31 * result + (this.m03 != (double)0.0F ? Double.hashCode(this.m03) : 0);
         result = 31 * result + (this.m10 != (double)0.0F ? Double.hashCode(this.m10) : 0);
         result = 31 * result + (this.m11 != (double)0.0F ? Double.hashCode(this.m11) : 0);
         result = 31 * result + (this.m12 != (double)0.0F ? Double.hashCode(this.m12) : 0);
         result = 31 * result + (this.m13 != (double)0.0F ? Double.hashCode(this.m13) : 0);
         result = 31 * result + (this.m20 != (double)0.0F ? Double.hashCode(this.m20) : 0);
         result = 31 * result + (this.m21 != (double)0.0F ? Double.hashCode(this.m21) : 0);
         result = 31 * result + (this.m22 != (double)0.0F ? Double.hashCode(this.m22) : 0);
         result = 31 * result + (this.m23 != (double)0.0F ? Double.hashCode(this.m23) : 0);
         result = 31 * result + (this.m30 != (double)0.0F ? Double.hashCode(this.m30) : 0);
         result = 31 * result + (this.m31 != (double)0.0F ? Double.hashCode(this.m31) : 0);
         result = 31 * result + (this.m32 != (double)0.0F ? Double.hashCode(this.m32) : 0);
         this.hashCode = 31 * result + (this.m33 != (double)0.0F ? Double.hashCode(this.m33) : 0);
         this.hashed = true;
      }

      return this.hashCode;
   }

   @Nonnull
   public Matrix4d clone() {
      return from(this);
   }

   @Nonnull
   public static Matrix4d from(double n) {
      return n == (double)0.0F ? ZERO : new Matrix4d(n, n, n, n, n, n, n, n, n, n, n, n, n, n, n, n);
   }

   @Nonnull
   public static Matrix4d from(Matrix2d m) {
      return from(m.get(0, 0), m.get(0, 1), (double)0.0F, (double)0.0F, m.get(1, 0), m.get(1, 1), (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F);
   }

   @Nonnull
   public static Matrix4d from(Matrix3d m) {
      return from(m.get(0, 0), m.get(0, 1), m.get(0, 2), (double)0.0F, m.get(1, 0), m.get(1, 1), m.get(1, 2), (double)0.0F, m.get(2, 0), m.get(2, 1), m.get(2, 2), (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F);
   }

   @Nonnull
   public static Matrix4d from(Matrix4d m) {
      return from(m.m00, m.m01, m.m02, m.m03, m.m10, m.m11, m.m12, m.m13, m.m20, m.m21, m.m22, m.m23, m.m30, m.m31, m.m32, m.m33);
   }

   @Nonnull
   public static Matrix4d from(MatrixNd m) {
      double m00 = m.get(0, 0);
      double m01 = m.get(0, 1);
      double m10 = m.get(1, 0);
      double m11 = m.get(1, 1);
      double m02;
      double m03;
      double m12;
      double m13;
      double m20;
      double m21;
      double m22;
      double m23;
      double m30;
      double m31;
      double m32;
      double m33;
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
            m03 = (double)0.0F;
            m13 = (double)0.0F;
            m23 = (double)0.0F;
            m30 = (double)0.0F;
            m31 = (double)0.0F;
            m32 = (double)0.0F;
            m33 = (double)0.0F;
         }
      } else {
         m02 = (double)0.0F;
         m12 = (double)0.0F;
         m20 = (double)0.0F;
         m21 = (double)0.0F;
         m22 = (double)0.0F;
         m03 = (double)0.0F;
         m13 = (double)0.0F;
         m23 = (double)0.0F;
         m30 = (double)0.0F;
         m31 = (double)0.0F;
         m32 = (double)0.0F;
         m33 = (double)0.0F;
      }

      return from(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
   }

   @Nonnull
   public static Matrix4d from(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33) {
      return from((double)m00, (double)m01, (double)m02, (double)m03, (double)m10, (double)m11, (double)m12, (double)m13, (double)m20, (double)m21, (double)m22, (double)m23, (double)m30, (double)m31, (double)m32, (double)m33);
   }

   @Nonnull
   public static Matrix4d from(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33) {
      return m00 == (double)0.0F && m01 == (double)0.0F && m02 == (double)0.0F && m03 == (double)0.0F && m10 == (double)0.0F && m11 == (double)0.0F && m12 == (double)0.0F && m13 == (double)0.0F && m20 == (double)0.0F && m21 == (double)0.0F && m22 == (double)0.0F && m23 == (double)0.0F && m30 == (double)0.0F && m31 == (double)0.0F && m32 == (double)0.0F && m33 == (double)0.0F ? ZERO : new Matrix4d(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
   }

   @Nonnull
   public static Matrix4d fromDiagonal(double m00, double m11, double m22, double m33) {
      return m00 == (double)0.0F && m11 == (double)0.0F && m22 == (double)0.0F && m33 == (double)0.0F ? ZERO : new Matrix4d(m00, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, m11, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, m22, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, m33);
   }

   @Nonnull
   public static Matrix4d createScaling(float scale) {
      return createScaling((double)scale);
   }

   @Nonnull
   public static Matrix4d createScaling(double scale) {
      return createScaling(scale, scale, scale, scale);
   }

   @Nonnull
   public static Matrix4d createScaling(Vector4d v) {
      return createScaling(v.getX(), v.getY(), v.getZ(), v.getW());
   }

   @Nonnull
   public static Matrix4d createScaling(float x, float y, float z, float w) {
      return createScaling((double)x, (double)y, (double)z, (double)w);
   }

   @Nonnull
   public static Matrix4d createScaling(double x, double y, double z, double w) {
      return from(x, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, y, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, z, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, w);
   }

   @Nonnull
   public static Matrix4d createTranslation(Vector3d v) {
      return createTranslation(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public static Matrix4d createTranslation(float x, float y, float z) {
      return createTranslation((double)x, (double)y, (double)z);
   }

   @Nonnull
   public static Matrix4d createTranslation(double x, double y, double z) {
      return from((double)1.0F, (double)0.0F, (double)0.0F, x, (double)0.0F, (double)1.0F, (double)0.0F, y, (double)0.0F, (double)0.0F, (double)1.0F, z, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F);
   }

   @Nonnull
   public static Matrix4d createRotation(Complexd rot) {
      rot = rot.normalize();
      return from(rot.getX(), -rot.getY(), (double)0.0F, (double)0.0F, rot.getY(), rot.getX(), (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F);
   }

   @Nonnull
   public static Matrix4d createRotation(Quaterniond rot) {
      rot = rot.normalize();
      return from((double)1.0F - (double)2.0F * rot.getY() * rot.getY() - (double)2.0F * rot.getZ() * rot.getZ(), (double)2.0F * rot.getX() * rot.getY() - (double)2.0F * rot.getW() * rot.getZ(), (double)2.0F * rot.getX() * rot.getZ() + (double)2.0F * rot.getW() * rot.getY(), (double)0.0F, (double)2.0F * rot.getX() * rot.getY() + (double)2.0F * rot.getW() * rot.getZ(), (double)1.0F - (double)2.0F * rot.getX() * rot.getX() - (double)2.0F * rot.getZ() * rot.getZ(), (double)2.0F * rot.getY() * rot.getZ() - (double)2.0F * rot.getW() * rot.getX(), (double)0.0F, (double)2.0F * rot.getX() * rot.getZ() - (double)2.0F * rot.getW() * rot.getY(), (double)2.0F * rot.getY() * rot.getZ() + (double)2.0F * rot.getX() * rot.getW(), (double)1.0F - (double)2.0F * rot.getX() * rot.getX() - (double)2.0F * rot.getY() * rot.getY(), (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F);
   }

   @Nonnull
   public static Matrix4d createLookAt(Vector3d eye, Vector3d at, Vector3d up) {
      Vector3d f = at.sub(eye).normalize();
      Vector3d s = f.cross(up).normalize();
      Vector3d u = s.cross(f);
      Matrix4d mat = from(s.getX(), s.getY(), s.getZ(), (double)0.0F, u.getX(), u.getY(), u.getZ(), (double)0.0F, -f.getX(), -f.getY(), -f.getZ(), (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F);
      return mat.translate(eye.negate());
   }

   @Nonnull
   public static Matrix4d createPerspective(float fov, float aspect, float near, float far) {
      return createPerspective((double)fov, (double)aspect, (double)near, (double)far);
   }

   @Nonnull
   public static Matrix4d createPerspective(double fov, double aspect, double near, double far) {
      double scale = (double)(1.0F / TrigMath.tan(fov * 0.008726646259971648));
      return from(scale / aspect, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, scale, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (far + near) / (near - far), (double)2.0F * far * near / (near - far), (double)0.0F, (double)0.0F, (double)-1.0F, (double)0.0F);
   }

   @Nonnull
   public static Matrix4d createOrthographic(float right, float left, float top, float bottom, float near, float far) {
      return createOrthographic((double)right, (double)left, (double)top, (double)bottom, (double)near, (double)far);
   }

   @Nonnull
   public static Matrix4d createOrthographic(double right, double left, double top, double bottom, double near, double far) {
      return from((double)2.0F / (right - left), (double)0.0F, (double)0.0F, -(right + left) / (right - left), (double)0.0F, (double)2.0F / (top - bottom), (double)0.0F, -(top + bottom) / (top - bottom), (double)0.0F, (double)0.0F, (double)-2.0F / (far - near), -(far + near) / (far - near), (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F);
   }

   private static double det3(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
      return m00 * (m11 * m22 - m12 * m21) - m01 * (m10 * m22 - m12 * m20) + m02 * (m10 * m21 - m11 * m20);
   }
}
