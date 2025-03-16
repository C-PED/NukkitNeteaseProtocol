package com.nukkitx.math.matrix;

import com.nukkitx.math.GenericMath;
import com.nukkitx.math.imaginary.Complexd;
import com.nukkitx.math.vector.Vector2d;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Matrix2d implements Matrixd, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final Matrix2d ZERO = new Matrix2d((double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F);
   public static final Matrix2d IDENTITY = new Matrix2d((double)1.0F, (double)0.0F, (double)0.0F, (double)1.0F);
   private final double m00;
   private final double m01;
   private final double m10;
   private final double m11;
   private transient volatile boolean hashed = false;
   private transient volatile int hashCode = 0;

   private Matrix2d(double m00, double m01, double m10, double m11) {
      this.m00 = m00;
      this.m01 = m01;
      this.m10 = m10;
      this.m11 = m11;
   }

   public double get(int row, int col) {
      switch (row) {
         case 0:
            switch (col) {
               case 0:
                  return this.m00;
               case 1:
                  return this.m01;
            }
         case 1:
            switch (col) {
               case 0:
                  return this.m10;
               case 1:
                  return this.m11;
            }
      }

      throw new IllegalArgumentException((row >= 0 && row <= 1 ? "" : "row must be greater than zero and smaller than 2. ") + (col >= 0 && col <= 1 ? "" : "col must be greater than zero and smaller than 2."));
   }

   @Nonnull
   public Vector2d getRow(int row) {
      return Vector2d.from(this.get(row, 0), this.get(row, 1));
   }

   @Nonnull
   public Vector2d getColumn(int col) {
      return Vector2d.from(this.get(0, col), this.get(1, col));
   }

   @Nonnull
   public Matrix2d add(Matrix2d m) {
      return from(this.m00 + m.m00, this.m01 + m.m01, this.m10 + m.m10, this.m11 + m.m11);
   }

   @Nonnull
   public Matrix2d sub(Matrix2d m) {
      return from(this.m00 - m.m00, this.m01 - m.m01, this.m10 - m.m10, this.m11 - m.m11);
   }

   @Nonnull
   public Matrix2d mul(float a) {
      return this.mul((double)a);
   }

   @Nonnull
   public Matrix2d mul(double a) {
      return from(this.m00 * a, this.m01 * a, this.m10 * a, this.m11 * a);
   }

   @Nonnull
   public Matrix2d mul(Matrix2d m) {
      return from(this.m00 * m.m00 + this.m01 * m.m10, this.m00 * m.m01 + this.m01 * m.m11, this.m10 * m.m00 + this.m11 * m.m10, this.m10 * m.m01 + this.m11 * m.m11);
   }

   @Nonnull
   public Matrix2d div(float a) {
      return this.div((double)a);
   }

   @Nonnull
   public Matrix2d div(double a) {
      return from(this.m00 / a, this.m01 / a, this.m10 / a, this.m11 / a);
   }

   @Nonnull
   public Matrix2d div(Matrix2d m) {
      return this.mul(m.invert());
   }

   @Nonnull
   public Matrix2d pow(float pow) {
      return this.pow((double)pow);
   }

   @Nonnull
   public Matrix2d pow(double pow) {
      return from(Math.pow(this.m00, pow), Math.pow(this.m01, pow), Math.pow(this.m10, pow), Math.pow(this.m11, pow));
   }

   @Nonnull
   public Matrix2d translate(float x) {
      return this.translate((double)x);
   }

   @Nonnull
   public Matrix2d translate(double x) {
      return createTranslation(x).mul(this);
   }

   @Nonnull
   public Matrix2d scale(float scale) {
      return this.scale((double)scale);
   }

   @Nonnull
   public Matrix2d scale(double scale) {
      return this.scale(scale, scale);
   }

   @Nonnull
   public Matrix2d scale(Vector2d v) {
      return this.scale(v.getX(), v.getY());
   }

   @Nonnull
   public Matrix2d scale(float x, float y) {
      return this.scale((double)x, (double)y);
   }

   @Nonnull
   public Matrix2d scale(double x, double y) {
      return createScaling(x, y).mul(this);
   }

   @Nonnull
   public Matrix2d rotate(Complexd rot) {
      return createRotation(rot).mul(this);
   }

   @Nonnull
   public Vector2d transform(Vector2d v) {
      return this.transform(v.getX(), v.getY());
   }

   @Nonnull
   public Vector2d transform(float x, float y) {
      return this.transform((double)x, (double)y);
   }

   @Nonnull
   public Vector2d transform(double x, double y) {
      return Vector2d.from(this.m00 * x + this.m01 * y, this.m10 * x + this.m11 * y);
   }

   @Nonnull
   public Matrix2d floor() {
      return from((float)GenericMath.floor(this.m00), (float)GenericMath.floor(this.m01), (float)GenericMath.floor(this.m10), (float)GenericMath.floor(this.m11));
   }

   @Nonnull
   public Matrix2d ceil() {
      return from(Math.ceil(this.m00), Math.ceil(this.m01), Math.ceil(this.m10), Math.ceil(this.m11));
   }

   @Nonnull
   public Matrix2d round() {
      return from((float)Math.round(this.m00), (float)Math.round(this.m01), (float)Math.round(this.m10), (float)Math.round(this.m11));
   }

   @Nonnull
   public Matrix2d abs() {
      return from(Math.abs(this.m00), Math.abs(this.m01), Math.abs(this.m10), Math.abs(this.m11));
   }

   @Nonnull
   public Matrix2d negate() {
      return from(-this.m00, -this.m01, -this.m10, -this.m11);
   }

   @Nonnull
   public Matrix2d transpose() {
      return from(this.m00, this.m10, this.m01, this.m11);
   }

   public double trace() {
      return this.m00 + this.m11;
   }

   public double determinant() {
      return this.m00 * this.m11 - this.m01 * this.m10;
   }

   @Nonnull
   public Matrix2d invert() {
      double det = this.determinant();
      if (Math.abs(det) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot inverse a matrix with a zero determinant");
      } else {
         return from(this.m11 / det, -this.m01 / det, -this.m10 / det, this.m00 / det);
      }
   }

   @Nonnull
   public Matrix3d toMatrix3() {
      return Matrix3d.from(this);
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
      return columnMajor ? new double[]{this.m00, this.m10, this.m01, this.m11} : new double[]{this.m00, this.m01, this.m10, this.m11};
   }

   @Nonnull
   public Matrix2f toFloat() {
      return Matrix2f.from(this.m00, this.m01, this.m10, this.m11);
   }

   @Nonnull
   public Matrix2d toDouble() {
      return from(this.m00, this.m01, this.m10, this.m11);
   }

   @Nonnull
   public String toString() {
      return this.m00 + " " + this.m01 + "\n" + this.m10 + " " + this.m11;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Matrix2d)) {
         return false;
      } else {
         Matrix2d matrix2 = (Matrix2d)o;
         if (Double.compare(matrix2.m00, this.m00) != 0) {
            return false;
         } else if (Double.compare(matrix2.m01, this.m01) != 0) {
            return false;
         } else if (Double.compare(matrix2.m10, this.m10) != 0) {
            return false;
         } else {
            return Double.compare(matrix2.m11, this.m11) == 0;
         }
      }
   }

   public int hashCode() {
      if (!this.hashed) {
         int result = this.m00 != (double)0.0F ? Double.hashCode(this.m00) : 0;
         result = 31 * result + (this.m01 != (double)0.0F ? Double.hashCode(this.m01) : 0);
         result = 31 * result + (this.m10 != (double)0.0F ? Double.hashCode(this.m10) : 0);
         this.hashCode = 31 * result + (this.m11 != (double)0.0F ? Double.hashCode(this.m11) : 0);
         this.hashed = true;
      }

      return this.hashCode;
   }

   @Nonnull
   public Matrix2d clone() {
      return from(this);
   }

   @Nonnull
   public static Matrix2d from(double n) {
      return n == (double)0.0F ? ZERO : new Matrix2d(n, n, n, n);
   }

   @Nonnull
   public static Matrix2d from(Matrix2d m) {
      return from(m.m00, m.m01, m.m10, m.m11);
   }

   @Nonnull
   public static Matrix2d from(Matrix3d m) {
      return from(m.get(0, 0), m.get(0, 1), m.get(1, 0), m.get(1, 1));
   }

   @Nonnull
   public static Matrix2d from(Matrix4d m) {
      return from(m.get(0, 0), m.get(0, 1), m.get(1, 0), m.get(1, 1));
   }

   @Nonnull
   public static Matrix2d from(MatrixNd m) {
      return from(m.get(0, 0), m.get(0, 1), m.get(1, 0), m.get(1, 1));
   }

   @Nonnull
   public static Matrix2d from(float m00, float m01, float m10, float m11) {
      return from((double)m00, (double)m01, (double)m10, (double)m11);
   }

   @Nonnull
   public static Matrix2d from(double m00, double m01, double m10, double m11) {
      return m00 == (double)0.0F && m01 == (double)0.0F && m10 == (double)0.0F && m11 == (double)0.0F ? ZERO : new Matrix2d(m00, m01, m10, m11);
   }

   @Nonnull
   public static Matrix2d fromDiagonal(double m00, double m11) {
      return m00 == (double)0.0F && m11 == (double)0.0F ? ZERO : new Matrix2d(m00, (double)0.0F, (double)0.0F, m11);
   }

   @Nonnull
   public static Matrix2d createScaling(float scale) {
      return createScaling((double)scale);
   }

   @Nonnull
   public static Matrix2d createScaling(double scale) {
      return createScaling(scale, scale);
   }

   @Nonnull
   public static Matrix2d createScaling(Vector2d v) {
      return createScaling(v.getX(), v.getY());
   }

   @Nonnull
   public static Matrix2d createScaling(float x, float y) {
      return createScaling((double)x, (double)y);
   }

   @Nonnull
   public static Matrix2d createScaling(double x, double y) {
      return from(x, (double)0.0F, (double)0.0F, y);
   }

   @Nonnull
   public static Matrix2d createTranslation(float x) {
      return createTranslation((double)x);
   }

   @Nonnull
   public static Matrix2d createTranslation(double x) {
      return from((double)1.0F, x, (double)0.0F, (double)1.0F);
   }

   @Nonnull
   public static Matrix2d createRotation(Complexd rot) {
      rot = rot.normalize();
      return from(rot.getX(), -rot.getY(), rot.getY(), rot.getX());
   }
}
