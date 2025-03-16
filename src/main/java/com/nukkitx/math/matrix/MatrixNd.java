package com.nukkitx.math.matrix;

import com.nukkitx.math.GenericMath;
import com.nukkitx.math.TrigMath;
import com.nukkitx.math.imaginary.Complexd;
import com.nukkitx.math.imaginary.Quaterniond;
import com.nukkitx.math.vector.Vector3d;
import com.nukkitx.math.vector.VectorNd;
import java.io.Serializable;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class MatrixNd implements Matrixd, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final MatrixNd IDENTITY_2 = new ImmutableIdentityMatrixN(2);
   public static final MatrixNd IDENTITY_3 = new ImmutableIdentityMatrixN(3);
   public static final MatrixNd IDENTITY_4 = new ImmutableIdentityMatrixN(4);
   private final double[][] mat;

   private MatrixNd(double[][] mat) {
      this.mat = mat;
   }

   public int size() {
      return this.mat.length;
   }

   public double get(int row, int col) {
      return this.mat[row][col];
   }

   @Nonnull
   public VectorNd getRow(int row) {
      int size = this.size();
      VectorNd d = VectorNd.from(size);

      for(int col = 0; col < size; ++col) {
         d.set(col, this.get(row, col));
      }

      return d;
   }

   @Nonnull
   public VectorNd getColumn(int col) {
      int size = this.size();
      VectorNd d = VectorNd.from(size);

      for(int row = 0; row < size; ++row) {
         d.set(row, this.get(row, col));
      }

      return d;
   }

   public void set(int row, int col, float val) {
      this.set(row, col, (double)val);
   }

   public void set(int row, int col, double val) {
      this.mat[row][col] = val;
   }

   public final void setIdentity() {
      int size = this.size();

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            if (row == col) {
               this.mat[row][col] = (double)1.0F;
            } else {
               this.mat[row][col] = (double)0.0F;
            }
         }
      }

   }

   public void setZero() {
      int size = this.size();

      for(int row = 0; row < size; ++row) {
         Arrays.fill(this.mat[row], (double)0.0F);
      }

   }

   @Nonnull
   public MatrixNd resize(int size) {
      MatrixNd d = from(size);

      for(int rowCol = this.size(); rowCol < size; ++rowCol) {
         d.set(rowCol, rowCol, 0.0F);
      }

      size = Math.min(size, this.size());

      for(int row = 0; row < size; ++row) {
         System.arraycopy(this.mat[row], 0, d.mat[row], 0, size);
      }

      return d;
   }

   @Nonnull
   public MatrixNd add(MatrixNd m) {
      int size = this.size();
      if (size != m.size()) {
         throw new IllegalArgumentException("Matrix sizes must be the same");
      } else {
         MatrixNd d = from(size);

         for(int row = 0; row < size; ++row) {
            for(int col = 0; col < size; ++col) {
               d.mat[row][col] = this.mat[row][col] + m.mat[row][col];
            }
         }

         return d;
      }
   }

   @Nonnull
   public MatrixNd sub(MatrixNd m) {
      int size = this.size();
      if (size != m.size()) {
         throw new IllegalArgumentException("Matrix sizes must be the same");
      } else {
         MatrixNd d = from(size);

         for(int row = 0; row < size; ++row) {
            for(int col = 0; col < size; ++col) {
               d.mat[row][col] = this.mat[row][col] - m.mat[row][col];
            }
         }

         return d;
      }
   }

   @Nonnull
   public MatrixNd mul(float a) {
      return this.mul((double)a);
   }

   @Nonnull
   public MatrixNd mul(double a) {
      int size = this.size();
      MatrixNd d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = this.mat[row][col] * a;
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNd mul(MatrixNd m) {
      int size = this.size();
      if (size != m.size()) {
         throw new IllegalArgumentException("Matrix sizes must be the same");
      } else {
         MatrixNd d = from(size);

         for(int row = 0; row < size; ++row) {
            for(int col = 0; col < size; ++col) {
               double dot = (double)0.0F;

               for(int i = 0; i < size; ++i) {
                  dot += this.mat[row][i] * m.mat[i][col];
               }

               d.mat[row][col] = dot;
            }
         }

         return d;
      }
   }

   @Nonnull
   public MatrixNd div(float a) {
      return this.div((double)a);
   }

   @Nonnull
   public MatrixNd div(double a) {
      int size = this.size();
      MatrixNd d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = this.mat[row][col] / a;
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNd div(MatrixNd m) {
      return this.mul(m.invert());
   }

   @Nonnull
   public MatrixNd pow(float pow) {
      return this.pow((double)pow);
   }

   @Nonnull
   public MatrixNd pow(double pow) {
      int size = this.size();
      MatrixNd d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = Math.pow(this.mat[row][col], pow);
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNd translate(VectorNd v) {
      return this.translate(v.toArray());
   }

   @Nonnull
   public MatrixNd translate(double... v) {
      return createTranslation(v).mul(this);
   }

   @Nonnull
   public MatrixNd scale(VectorNd v) {
      return this.scale(v.toArray());
   }

   @Nonnull
   public MatrixNd scale(double... v) {
      return createScaling(v).mul(this);
   }

   @Nonnull
   public MatrixNd rotate(Complexd rot) {
      return createRotation(this.size(), rot).mul(this);
   }

   @Nonnull
   public MatrixNd rotate(Quaterniond rot) {
      return createRotation(this.size(), rot).mul(this);
   }

   @Nonnull
   public VectorNd transform(VectorNd v) {
      return this.transform(v.toArray());
   }

   @Nonnull
   public VectorNd transform(double... vec) {
      int size = this.size();
      if (size != vec.length) {
         throw new IllegalArgumentException("Matrix and vector sizes must be the same");
      } else {
         VectorNd d = VectorNd.from(size);

         for(int row = 0; row < size; ++row) {
            double dot = (double)0.0F;

            for(int col = 0; col < size; ++col) {
               dot += this.mat[row][col] * vec[col];
            }

            d.set(row, dot);
         }

         return d;
      }
   }

   @Nonnull
   public MatrixNd floor() {
      int size = this.size();
      MatrixNd d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = (double)GenericMath.floor(this.mat[row][col]);
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNd ceil() {
      int size = this.size();
      MatrixNd d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = Math.ceil(this.mat[row][col]);
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNd round() {
      int size = this.size();
      MatrixNd d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = (double)Math.round(this.mat[row][col]);
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNd abs() {
      int size = this.size();
      MatrixNd d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = Math.abs(this.mat[row][col]);
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNd negate() {
      int size = this.size();
      MatrixNd d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = -this.mat[row][col];
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNd transpose() {
      int size = this.size();
      MatrixNd d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = this.mat[col][row];
         }
      }

      return d;
   }

   public double trace() {
      int size = this.size();
      double trace = (double)0.0F;

      for(int rowCol = 0; rowCol < size; ++rowCol) {
         trace += this.mat[rowCol][rowCol];
      }

      return trace;
   }

   public double determinant() {
      int size = this.size();
      double[][] m = deepClone(this.mat);

      for(int i = 0; i < size - 1; ++i) {
         for(int col = i + 1; col < size; ++col) {
            double det = m[i][i] < GenericMath.DBL_EPSILON ? (double)0.0F : m[i][col] / m[i][i];

            for(int row = i; row < size; ++row) {
               m[row][col] -= det * m[row][i];
            }
         }
      }

      double det = (double)1.0F;

      for(int i = 0; i < size; ++i) {
         det *= m[i][i];
      }

      return det;
   }

   @Nonnull
   public MatrixNd invert() {
      if (Math.abs(this.determinant()) < GenericMath.DBL_EPSILON) {
         throw new ArithmeticException("Cannot inverse a matrix with a zero determinant");
      } else {
         int size = this.size();
         AugmentedMatrixN augMat = new AugmentedMatrixN(this);
         int augmentedSize = augMat.getAugmentedSize();

         for(int i = 0; i < size; ++i) {
            for(int row = 0; row < size; ++row) {
               if (i != row) {
                  double ratio = augMat.get(row, i) / augMat.get(i, i);

                  for(int col = 0; col < augmentedSize; ++col) {
                     augMat.set(row, col, augMat.get(row, col) - ratio * augMat.get(i, col));
                  }
               }
            }
         }

         for(int row = 0; row < size; ++row) {
            double div = augMat.get(row, row);

            for(int col = 0; col < augmentedSize; ++col) {
               augMat.set(row, col, augMat.get(row, col) / div);
            }
         }

         return augMat.getAugmentation();
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
   public Matrix4d toMatrix4() {
      return Matrix4d.from(this);
   }

   @Nonnull
   public double[] toArray() {
      return this.toArray(false);
   }

   @Nonnull
   public MatrixNf toFloat() {
      int size = this.size();
      float[] m = new float[size * size];

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            m[col + row * size] = (float)this.get(row, col);
         }
      }

      return MatrixNf.from(m);
   }

   @Nonnull
   public MatrixNd toDouble() {
      int size = this.size();
      double[] m = new double[size * size];

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            m[col + row * size] = this.get(row, col);
         }
      }

      return from(m);
   }

   @Nonnull
   public double[] toArray(boolean columnMajor) {
      int size = this.size();
      double[] array = new double[size * size];
      if (columnMajor) {
         for(int col = 0; col < size; ++col) {
            for(int row = 0; row < size; ++row) {
               array[row + col * size] = this.mat[row][col];
            }
         }
      } else {
         for(int row = 0; row < size; ++row) {
            System.arraycopy(this.mat[row], 0, array, row * size, size);
         }
      }

      return array;
   }

   @Nonnull
   public String toString() {
      int size = this.size();
      StringBuilder builder = new StringBuilder();

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            builder.append(this.mat[row][col]);
            if (col < size - 1) {
               builder.append(' ');
            }
         }

         if (row < size - 1) {
            builder.append('\n');
         }
      }

      return builder.toString();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else {
         return !(obj instanceof MatrixNd) ? false : Arrays.deepEquals(this.mat, ((MatrixNd)obj).mat);
      }
   }

   public int hashCode() {
      return 395 + Arrays.deepHashCode(this.mat);
   }

   @Nonnull
   public MatrixNd clone() {
      return from(this);
   }

   @Nonnull
   public static MatrixNd createScaling(VectorNd v) {
      return createScaling(v.toArray());
   }

   @Nonnull
   public static MatrixNd createScaling(double... vec) {
      int size = vec.length;
      MatrixNd m = from(size);

      for(int rowCol = 0; rowCol < size; ++rowCol) {
         m.set(rowCol, rowCol, vec[rowCol]);
      }

      return m;
   }

   @Nonnull
   public static MatrixNd createTranslation(VectorNd v) {
      return createTranslation(v.toArray());
   }

   @Nonnull
   public static MatrixNd createTranslation(double... vec) {
      int size = vec.length;
      MatrixNd m = from(size + 1);

      for(int row = 0; row < size; ++row) {
         m.set(row, size, vec[row]);
      }

      return m;
   }

   @Nonnull
   public static MatrixNd createRotation(int size, Complexd rot) {
      if (size < 2) {
         throw new IllegalArgumentException("Minimum matrix size is 2");
      } else {
         MatrixNd m = from(size);
         rot = rot.normalize();
         m.set(0, 0, rot.getX());
         m.set(0, 1, -rot.getY());
         m.set(1, 0, rot.getY());
         m.set(1, 1, rot.getX());
         return m;
      }
   }

   @Nonnull
   public static MatrixNd createRotation(int size, Quaterniond rot) {
      if (size < 3) {
         throw new IllegalArgumentException("Minimum matrix size is 3");
      } else {
         MatrixNd m = from(size);
         rot = rot.normalize();
         m.set(0, 0, (double)1.0F - (double)2.0F * rot.getY() * rot.getY() - (double)2.0F * rot.getZ() * rot.getZ());
         m.set(0, 1, (double)2.0F * rot.getX() * rot.getY() - (double)2.0F * rot.getW() * rot.getZ());
         m.set(0, 2, (double)2.0F * rot.getX() * rot.getZ() + (double)2.0F * rot.getW() * rot.getY());
         m.set(1, 0, (double)2.0F * rot.getX() * rot.getY() + (double)2.0F * rot.getW() * rot.getZ());
         m.set(1, 1, (double)1.0F - (double)2.0F * rot.getX() * rot.getX() - (double)2.0F * rot.getZ() * rot.getZ());
         m.set(1, 2, (double)2.0F * rot.getY() * rot.getZ() - (double)2.0F * rot.getW() * rot.getX());
         m.set(2, 0, (double)2.0F * rot.getX() * rot.getZ() - (double)2.0F * rot.getW() * rot.getY());
         m.set(2, 1, (double)2.0F * rot.getY() * rot.getZ() + (double)2.0F * rot.getX() * rot.getW());
         m.set(2, 2, (double)1.0F - (double)2.0F * rot.getX() * rot.getX() - (double)2.0F * rot.getY() * rot.getY());
         return m;
      }
   }

   @Nonnull
   public static MatrixNd createLookAt(int size, Vector3d eye, Vector3d at, Vector3d up) {
      if (size < 4) {
         throw new IllegalArgumentException("Minimum matrix size is 4");
      } else {
         Vector3d f = at.sub(eye).normalize();
         up = up.normalize();
         Vector3d s = f.cross(up).normalize();
         Vector3d u = s.cross(f).normalize();
         MatrixNd mat = from(size);
         mat.set(0, 0, s.getX());
         mat.set(0, 1, s.getY());
         mat.set(0, 2, s.getZ());
         mat.set(1, 0, u.getX());
         mat.set(1, 1, u.getY());
         mat.set(1, 2, u.getZ());
         mat.set(2, 0, -f.getX());
         mat.set(2, 1, -f.getY());
         mat.set(2, 2, -f.getZ());
         return mat.translate(eye.mul(-1.0F).toVectorN());
      }
   }

   @Nonnull
   public static MatrixNd createPerspective(int size, float fov, float aspect, float near, float far) {
      return createPerspective(size, (double)fov, (double)aspect, (double)near, (double)far);
   }

   @Nonnull
   public static MatrixNd createPerspective(int size, double fov, double aspect, double near, double far) {
      if (size < 4) {
         throw new IllegalArgumentException("Minimum matrix size is 4");
      } else {
         MatrixNd perspective = from(size);
         double scale = (double)(1.0F / TrigMath.tan(fov * 0.008726646259971648));
         perspective.set(0, 0, scale / aspect);
         perspective.set(1, 1, scale);
         perspective.set(2, 2, (far + near) / (near - far));
         perspective.set(2, 3, (double)2.0F * far * near / (near - far));
         perspective.set(3, 2, -1.0F);
         perspective.set(3, 3, 0.0F);
         return perspective;
      }
   }

   @Nonnull
   public static MatrixNd createOrthographic(int size, float right, float left, float top, float bottom, float near, float far) {
      return createOrthographic(size, (double)right, (double)left, (double)top, (double)bottom, (double)near, (double)far);
   }

   @Nonnull
   public static MatrixNd createOrthographic(int size, double right, double left, double top, double bottom, double near, double far) {
      if (size < 4) {
         throw new IllegalArgumentException("Minimum matrix size is 4");
      } else {
         MatrixNd orthographic = from(size);
         orthographic.set(0, 0, (double)2.0F / (right - left));
         orthographic.set(1, 1, (double)2.0F / (top - bottom));
         orthographic.set(2, 2, (double)-2.0F / (far - near));
         orthographic.set(0, 3, -(right + left) / (right - left));
         orthographic.set(1, 3, -(top + bottom) / (top - bottom));
         orthographic.set(2, 3, -(far + near) / (far - near));
         return orthographic;
      }
   }

   @Nonnull
   private static double[][] deepClone(double[][] array) {
      int size = array.length;
      double[][] clone = ((array).clone());

      for(int i = 0; i < size; ++i) {
         clone[i] = (double[])array[i].clone();
      }

      return clone;
   }

   @Nonnull
   public static MatrixNd from(int size) {
      if (size < 2) {
         throw new IllegalArgumentException("Minimum matrix size is 2");
      } else {
         MatrixNd mat = new MatrixNd(new double[size][size]);
         mat.setIdentity();
         return mat;
      }
   }

   @Nonnull
   public static MatrixNd from(Matrix2d m) {
      return new MatrixNd(new double[][]{{m.get(0, 0), m.get(0, 1)}, {m.get(1, 0), m.get(1, 1)}});
   }

   @Nonnull
   public static MatrixNd from(Matrix3d m) {
      return new MatrixNd(new double[][]{{m.get(0, 0), m.get(0, 1), m.get(0, 2)}, {m.get(1, 0), m.get(1, 1), m.get(1, 2)}, {m.get(2, 0), m.get(2, 1), m.get(2, 2)}});
   }

   @Nonnull
   public static MatrixNd from(Matrix4d m) {
      return new MatrixNd(new double[][]{{m.get(0, 0), m.get(0, 1), m.get(0, 2), m.get(0, 3)}, {m.get(1, 0), m.get(1, 1), m.get(1, 2), m.get(1, 3)}, {m.get(2, 0), m.get(2, 1), m.get(2, 2), m.get(2, 3)}, {m.get(3, 0), m.get(3, 1), m.get(3, 2), m.get(3, 3)}});
   }

   @Nonnull
   public static MatrixNd from(double... m) {
      if (m.length < 4) {
         throw new IllegalArgumentException("Minimum matrix size is 2");
      } else {
         int size = (int)Math.ceil(Math.sqrt((double)m.length));
         double[][] mat = new double[size][size];

         for(int row = 0; row < size; ++row) {
            for(int col = 0; col < size; ++col) {
               int index = col + row * size;
               if (index < m.length) {
                  mat[row][col] = m[index];
               } else {
                  mat[row][col] = (double)0.0F;
               }
            }
         }

         return new MatrixNd(mat);
      }
   }

   @Nonnull
   public static MatrixNd from(MatrixNd m) {
      return new MatrixNd(deepClone(m.mat));
   }

   private static class ImmutableIdentityMatrixN extends MatrixNd {
      public ImmutableIdentityMatrixN(int size) {
         super(new double[size][size]);
         this.setIdentity();
      }

      public void set(int row, int col, double val) {
         throw new UnsupportedOperationException("You may not alter this matrix");
      }

      public void setZero() {
         throw new UnsupportedOperationException("You may not alter this matrix");
      }
   }

   private static class AugmentedMatrixN {
      private final MatrixNd mat;
      private final MatrixNd aug;
      private final int size;

      private AugmentedMatrixN(@Nonnull MatrixNd mat) {
         this.mat = mat.clone();
         this.size = mat.size();
         this.aug = MatrixNd.from(this.size);
      }

      @Nonnull
      private MatrixNd getAugmentation() {
         return this.aug;
      }

      private int getAugmentedSize() {
         return this.size * 2;
      }

      private double get(int row, int col) {
         return col < this.size ? this.mat.get(row, col) : this.aug.get(row, col - this.size);
      }

      private void set(int row, int col, double val) {
         if (col < this.size) {
            this.mat.set(row, col, val);
         } else {
            this.aug.set(row, col - this.size, val);
         }

      }
   }
}
