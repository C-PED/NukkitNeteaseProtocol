package com.nukkitx.math.matrix;

import com.nukkitx.math.GenericMath;
import com.nukkitx.math.imaginary.Complexd;
import com.nukkitx.math.imaginary.Quaterniond;
import com.nukkitx.math.vector.Vector2d;
import com.nukkitx.math.vector.Vector3d;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Matrix3d implements Matrixd, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Matrix3d ZERO = new Matrix3d((double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F);
   public static final Matrix3d IDENTITY = new Matrix3d((double)1.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F, (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F);
   private final double m00;
   private final double m01;
   private final double m02;
   private final double m10;
   private final double m11;
   private final double m12;
   private final double m20;
   private final double m21;
   private final double m22;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Matrix3d(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
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
   public Vector3d getRow(int row) {
      return Vector3d.from(this.get(row, 0), this.get(row, 1), this.get(row, 2));
   }

   @Nonnull
   public Vector3d getColumn(int col) {
      return Vector3d.from(this.get(0, col), this.get(1, col), this.get(2, col));
   }

   @Nonnull
   public Matrix3d add(Matrix3d m) {
      return from(this.m00 + m.m00, this.m01 + m.m01, this.m02 + m.m02, this.m10 + m.m10, this.m11 + m.m11, this.m12 + m.m12, this.m20 + m.m20, this.m21 + m.m21, this.m22 + m.m22);
   }

   @Nonnull
   public Matrix3d sub(Matrix3d m) {
      return from(this.m00 - m.m00, this.m01 - m.m01, this.m02 - m.m02, this.m10 - m.m10, this.m11 - m.m11, this.m12 - m.m12, this.m20 - m.m20, this.m21 - m.m21, this.m22 - m.m22);
   }

   @Nonnull
   public Matrix3d mul(float a) {
      return this.mul((double)a);
   }

   @Nonnull
   public Matrix3d mul(double a) {
      return from(this.m00 * a, this.m01 * a, this.m02 * a, this.m10 * a, this.m11 * a, this.m12 * a, this.m20 * a, this.m21 * a, this.m22 * a);
   }

   @Nonnull
   public Matrix3d mul(Matrix3d m) {
      return from(this.m00 * m.m00 + this.m01 * m.m10 + this.m02 * m.m20, this.m00 * m.m01 + this.m01 * m.m11 + this.m02 * m.m21, this.m00 * m.m02 + this.m01 * m.m12 + this.m02 * m.m22, this.m10 * m.m00 + this.m11 * m.m10 + this.m12 * m.m20, this.m10 * m.m01 + this.m11 * m.m11 + this.m12 * m.m21, this.m10 * m.m02 + this.m11 * m.m12 + this.m12 * m.m22, this.m20 * m.m00 + this.m21 * m.m10 + this.m22 * m.m20, this.m20 * m.m01 + this.m21 * m.m11 + this.m22 * m.m21, this.m20 * m.m02 + this.m21 * m.m12 + this.m22 * m.m22);
   }

   @Nonnull
   public Matrix3d div(float a) {
      return this.div((double)a);
   }

   @Nonnull
   public Matrix3d div(double a) {
      return from(this.m00 / a, this.m01 / a, this.m02 / a, this.m10 / a, this.m11 / a, this.m12 / a, this.m20 / a, this.m21 / a, this.m22 / a);
   }

   @Nonnull
   public Matrix3d div(Matrix3d m) {
      return this.mul(m.invert());
   }

   @Nonnull
   public Matrix3d pow(float pow) {
      return this.pow((double)pow);
   }

   @Nonnull
   public Matrix3d pow(double pow) {
      return from(Math.pow(this.m00, pow), Math.pow(this.m01, pow), Math.pow(this.m02, pow), Math.pow(this.m10, pow), Math.pow(this.m11, pow), Math.pow(this.m12, pow), Math.pow(this.m20, pow), Math.pow(this.m21, pow), Math.pow(this.m22, pow));
   }

   @Nonnull
   public Matrix3d translate(Vector2d v) {
      return this.translate(v.getX(), v.getY());
   }

   @Nonnull
   public Matrix3d translate(float x, float y) {
      return this.translate((double)x, (double)y);
   }

   @Nonnull
   public Matrix3d translate(double x, double y) {
      return createTranslation(x, y).mul(this);
   }

   @Nonnull
   public Matrix3d scale(float scale) {
      return this.scale((double)scale);
   }

   @Nonnull
   public Matrix3d scale(double scale) {
      return this.scale(scale, scale, scale);
   }

   @Nonnull
   public Matrix3d scale(Vector3d v) {
      return this.scale(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public Matrix3d scale(float x, float y, float z) {
      return this.scale((double)x, (double)y, (double)z);
   }

   @Nonnull
   public Matrix3d scale(double x, double y, double z) {
      return createScaling(x, y, z).mul(this);
   }

   @Nonnull
   public Matrix3d rotate(Complexd rot) {
      return createRotation(rot).mul(this);
   }

   @Nonnull
   public Matrix3d rotate(Quaterniond rot) {
      return createRotation(rot).mul(this);
   }

   @Nonnull
   public Vector3d transform(Vector3d v) {
      return this.transform(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public Vector3d transform(float x, float y, float z) {
      return this.transform((double)x, (double)y, (double)z);
   }

   @Nonnull
   public Vector3d transform(double x, double y, double z) {
      return Vector3d.from(this.m00 * x + this.m01 * y + this.m02 * z, this.m10 * x + this.m11 * y + this.m12 * z, this.m20 * x + this.m21 * y + this.m22 * z);
   }

   @Nonnull
   public Matrix3d floor() {
      return from((float)GenericMath.floor(this.m00), (float)GenericMath.floor(this.m01), (float)GenericMath.floor(this.m02), (float)GenericMath.floor(this.m10), (float)GenericMath.floor(this.m11), (float)GenericMath.floor(this.m12), (float)GenericMath.floor(this.m20), (float)GenericMath.floor(this.m21), (float)GenericMath.floor(this.m22));
   }

   @Nonnull
   public Matrix3d ceil() {
      return from(Math.ceil(this.m00), Math.ceil(this.m01), Math.ceil(this.m02), Math.ceil(this.m10), Math.ceil(this.m11), Math.ceil(this.m12), Math.ceil(this.m20), Math.ceil(this.m21), Math.ceil(this.m22));
   }

   @Nonnull
   public Matrix3d round() {
      return from((float)Math.round(this.m00), (float)Math.round(this.m01), (float)Math.round(this.m02), (float)Math.round(this.m10), (float)Math.round(this.m11), (float)Math.round(this.m12), (float)Math.round(this.m20), (float)Math.round(this.m21), (float)Math.round(this.m22));
   }

   @Nonnull
   public Matrix3d abs() {
      return from(Math.abs(this.m00), Math.abs(this.m01), Math.abs(this.m02), Math.abs(this.m10), Math.abs(this.m11), Math.abs(this.m12), Math.abs(this.m20), Math.abs(this.m21), Math.abs(this.m22));
   }

   @Nonnull
   public Matrix3d negate() {
      return from(-this.m00, -this.m01, -this.m02, -this.m10, -this.m11, -this.m12, -this.m20, -this.m21, -this.m22);
   }

   @Nonnull
   public Matrix3d transpose() {
      return from(this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, this.m02, this.m12, this.m22);
   }

   public double trace() {
      return this.m00 + this.m11 + this.m22;
   }

   public double determinant() {
      return this.m00 * (this.m11 * this.m22 - this.m12 * this.m21) - this.m01 * (this.m10 * this.m22 - this.m12 * this.m20) + this.m02 * (this.m10 * this.m21 - this.m11 * this.m20);
   }

   @Nonnull
   public Matrix3d invert() {
      double det = this.determinant();
      if (Math.abs(det) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot inverse a matrix with a zero determinant");
      } else {
         return from((this.m11 * this.m22 - this.m21 * this.m12) / det, -(this.m01 * this.m22 - this.m21 * this.m02) / det, (this.m01 * this.m12 - this.m02 * this.m11) / det, -(this.m10 * this.m22 - this.m20 * this.m12) / det, (this.m00 * this.m22 - this.m20 * this.m02) / det, -(this.m00 * this.m12 - this.m10 * this.m02) / det, (this.m10 * this.m21 - this.m20 * this.m11) / det, -(this.m00 * this.m21 - this.m20 * this.m01) / det, (this.m00 * this.m11 - this.m01 * this.m10) / det);
      }
   }

   @Nonnull
   public Matrix2d toMatrix2() {
      return Matrix2d.from(this);
   }

   @Nonnull
   public Matrix4d toMatrix4() {
      return Matrix4d.from(this);
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
      return columnMajor ? new double[]{this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, this.m02, this.m12, this.m22} : new double[]{this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22};
   }

   @Nonnull
   public Matrix3f toFloat() {
      return Matrix3f.from(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22);
   }

   @Nonnull
   public Matrix3d toDouble() {
      return from(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22);
   }

   @Nonnull
   public String toString() {
      return this.m00 + " " + this.m01 + " " + this.m02 + "\n" + this.m10 + " " + this.m11 + " " + this.m12 + "\n" + this.m20 + " " + this.m21 + " " + this.m22 + "\n";
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Matrix3d)) {
         return false;
      } else {
         Matrix3d matrix3 = (Matrix3d)o;
         if (Double.compare(matrix3.m00, this.m00) != 0) {
            return false;
         } else if (Double.compare(matrix3.m01, this.m01) != 0) {
            return false;
         } else if (Double.compare(matrix3.m02, this.m02) != 0) {
            return false;
         } else if (Double.compare(matrix3.m10, this.m10) != 0) {
            return false;
         } else if (Double.compare(matrix3.m11, this.m11) != 0) {
            return false;
         } else if (Double.compare(matrix3.m12, this.m12) != 0) {
            return false;
         } else if (Double.compare(matrix3.m20, this.m20) != 0) {
            return false;
         } else if (Double.compare(matrix3.m21, this.m21) != 0) {
            return false;
         } else {
            return Double.compare(matrix3.m22, this.m22) == 0;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         int result = this.m00 != (double)0.0F ? Double.hashCode(this.m00) : 0;
         result = 31 * result + (this.m01 != (double)0.0F ? Double.hashCode(this.m01) : 0);
         result = 31 * result + (this.m02 != (double)0.0F ? Double.hashCode(this.m02) : 0);
         result = 31 * result + (this.m10 != (double)0.0F ? Double.hashCode(this.m10) : 0);
         result = 31 * result + (this.m11 != (double)0.0F ? Double.hashCode(this.m11) : 0);
         result = 31 * result + (this.m12 != (double)0.0F ? Double.hashCode(this.m12) : 0);
         result = 31 * result + (this.m20 != (double)0.0F ? Double.hashCode(this.m20) : 0);
         result = 31 * result + (this.m21 != (double)0.0F ? Double.hashCode(this.m21) : 0);
         this.hashCode = 31 * result + (this.m22 != (double)0.0F ? Double.hashCode(this.m22) : 0);
         this.hashed = true;
      }

      return this.hashCode;
   }

   @Nonnull
   public Matrix3d clone() {
      return from(this);
   }

   @Nonnull
   public static Matrix3d from(double n) {
      return n == (double)0.0F ? ZERO : new Matrix3d(n, n, n, n, n, n, n, n, n);
   }

   @Nonnull
   public static Matrix3d from(Matrix2d m) {
      return from(m.get(0, 0), m.get(0, 1), (double)0.0F, m.get(1, 0), m.get(1, 1), (double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F);
   }

   @Nonnull
   public static Matrix3d from(Matrix3d m) {
      return from(m.m00, m.m01, m.m02, m.m10, m.m11, m.m12, m.m20, m.m21, m.m22);
   }

   @Nonnull
   public static Matrix3d from(Matrix4d m) {
      return from(m.get(0, 0), m.get(0, 1), m.get(0, 2), m.get(1, 0), m.get(1, 1), m.get(1, 2), m.get(2, 0), m.get(2, 1), m.get(2, 2));
   }

   @Nonnull
   public static Matrix3d from(MatrixNd m) {
      double m00 = m.get(0, 0);
      double m01 = m.get(0, 1);
      double m10 = m.get(1, 0);
      double m11 = m.get(1, 1);
      double m02;
      double m12;
      double m20;
      double m21;
      double m22;
      if (m.size() > 2) {
         m02 = m.get(0, 2);
         m12 = m.get(1, 2);
         m20 = m.get(2, 0);
         m21 = m.get(2, 1);
         m22 = m.get(2, 2);
      } else {
         m02 = (double)0.0F;
         m12 = (double)0.0F;
         m20 = (double)0.0F;
         m21 = (double)0.0F;
         m22 = (double)0.0F;
      }

      return from(m00, m01, m02, m10, m11, m12, m20, m21, m22);
   }

   @Nonnull
   public static Matrix3d from(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
      return from((double)m00, (double)m01, (double)m02, (double)m10, (double)m11, (double)m12, (double)m20, (double)m21, (double)m22);
   }

   @Nonnull
   public static Matrix3d from(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
      return m00 == (double)0.0F && m01 == (double)0.0F && m02 == (double)0.0F && m10 == (double)0.0F && m11 == (double)0.0F && m12 == (double)0.0F && m20 == (double)0.0F && m21 == (double)0.0F && m22 == (double)0.0F ? ZERO : new Matrix3d(m00, m01, m02, m10, m11, m12, m20, m21, m22);
   }

   @Nonnull
   public static Matrix3d fromDiagonal(double m00, double m11, double m22) {
      return m00 == (double)0.0F && m11 == (double)0.0F && m22 == (double)0.0F ? ZERO : new Matrix3d(m00, (double)0.0F, (double)0.0F, (double)0.0F, m11, (double)0.0F, (double)0.0F, (double)0.0F, m22);
   }

   @Nonnull
   public static Matrix3d createScaling(float scale) {
      return createScaling((double)scale);
   }

   @Nonnull
   public static Matrix3d createScaling(double scale) {
      return createScaling(scale, scale, scale);
   }

   @Nonnull
   public static Matrix3d createScaling(Vector3d v) {
      return createScaling(v.getX(), v.getY(), v.getZ());
   }

   @Nonnull
   public static Matrix3d createScaling(float x, float y, float z) {
      return createScaling((double)x, (double)y, (double)z);
   }

   @Nonnull
   public static Matrix3d createScaling(double x, double y, double z) {
      return from(x, (double)0.0F, (double)0.0F, (double)0.0F, y, (double)0.0F, (double)0.0F, (double)0.0F, z);
   }

   @Nonnull
   public static Matrix3d createTranslation(Vector2d v) {
      return createTranslation(v.getX(), v.getY());
   }

   @Nonnull
   public static Matrix3d createTranslation(float x, float y) {
      return createTranslation((double)x, (double)y);
   }

   @Nonnull
   public static Matrix3d createTranslation(double x, double y) {
      return from((double)1.0F, (double)0.0F, x, (double)0.0F, (double)1.0F, y, (double)0.0F, (double)0.0F, (double)1.0F);
   }

   @Nonnull
   public static Matrix3d createRotation(Complexd rot) {
      rot = rot.normalize();
      return from(rot.getX(), -rot.getY(), (double)0.0F, rot.getY(), rot.getX(), (double)0.0F, (double)0.0F, (double)0.0F, (double)1.0F);
   }

   @Nonnull
   public static Matrix3d createRotation(Quaterniond rot) {
      rot = rot.normalize();
      return from((double)1.0F - (double)2.0F * rot.getY() * rot.getY() - (double)2.0F * rot.getZ() * rot.getZ(), (double)2.0F * rot.getX() * rot.getY() - (double)2.0F * rot.getW() * rot.getZ(), (double)2.0F * rot.getX() * rot.getZ() + (double)2.0F * rot.getW() * rot.getY(), (double)2.0F * rot.getX() * rot.getY() + (double)2.0F * rot.getW() * rot.getZ(), (double)1.0F - (double)2.0F * rot.getX() * rot.getX() - (double)2.0F * rot.getZ() * rot.getZ(), (double)2.0F * rot.getY() * rot.getZ() - (double)2.0F * rot.getW() * rot.getX(), (double)2.0F * rot.getX() * rot.getZ() - (double)2.0F * rot.getW() * rot.getY(), (double)2.0F * rot.getY() * rot.getZ() + (double)2.0F * rot.getX() * rot.getW(), (double)1.0F - (double)2.0F * rot.getX() * rot.getX() - (double)2.0F * rot.getY() * rot.getY());
   }
}
