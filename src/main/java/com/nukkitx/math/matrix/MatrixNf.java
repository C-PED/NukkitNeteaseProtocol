package com.nukkitx.math.matrix;

import com.nukkitx.math.GenericMath;
import com.nukkitx.math.TrigMath;
import com.nukkitx.math.imaginary.Complexf;
import com.nukkitx.math.imaginary.Quaternionf;
import com.nukkitx.math.vector.Vector3f;
import com.nukkitx.math.vector.VectorNf;
import java.io.Serializable;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class MatrixNf implements Matrixf, Serializable, Cloneable {
   private static final long serialVersionUID = 1L;
   public static final MatrixNf IDENTITY_2 = new ImmutableIdentityMatrixN(2);
   public static final MatrixNf IDENTITY_3 = new ImmutableIdentityMatrixN(3);
   public static final MatrixNf IDENTITY_4 = new ImmutableIdentityMatrixN(4);
   private final float[][] mat;

   private MatrixNf(float[][] mat) {
      this.mat = mat;
   }

   public int size() {
      return this.mat.length;
   }

   public float get(int row, int col) {
      return this.mat[row][col];
   }

   @Nonnull
   public VectorNf getRow(int row) {
      int size = this.size();
      VectorNf d = VectorNf.from(size);

      for(int col = 0; col < size; ++col) {
         d.set(col, this.get(row, col));
      }

      return d;
   }

   @Nonnull
   public VectorNf getColumn(int col) {
      int size = this.size();
      VectorNf d = VectorNf.from(size);

      for(int row = 0; row < size; ++row) {
         d.set(row, this.get(row, col));
      }

      return d;
   }

   public void set(int row, int col, double val) {
      this.set(row, col, (float)val);
   }

   public void set(int row, int col, float val) {
      this.mat[row][col] = val;
   }

   public final void setIdentity() {
      int size = this.size();

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            if (row == col) {
               this.mat[row][col] = 1.0F;
            } else {
               this.mat[row][col] = 0.0F;
            }
         }
      }

   }

   public void setZero() {
      int size = this.size();

      for(int row = 0; row < size; ++row) {
         Arrays.fill(this.mat[row], 0.0F);
      }

   }

   @Nonnull
   public MatrixNf resize(int size) {
      MatrixNf d = from(size);

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
   public MatrixNf add(MatrixNf m) {
      int size = this.size();
      if (size != m.size()) {
         throw new IllegalArgumentException("Matrix sizes must be the same");
      } else {
         MatrixNf d = from(size);

         for(int row = 0; row < size; ++row) {
            for(int col = 0; col < size; ++col) {
               d.mat[row][col] = this.mat[row][col] + m.mat[row][col];
            }
         }

         return d;
      }
   }

   @Nonnull
   public MatrixNf sub(MatrixNf m) {
      int size = this.size();
      if (size != m.size()) {
         throw new IllegalArgumentException("Matrix sizes must be the same");
      } else {
         MatrixNf d = from(size);

         for(int row = 0; row < size; ++row) {
            for(int col = 0; col < size; ++col) {
               d.mat[row][col] = this.mat[row][col] - m.mat[row][col];
            }
         }

         return d;
      }
   }

   @Nonnull
   public MatrixNf mul(double a) {
      return this.mul((float)a);
   }

   @Nonnull
   public MatrixNf mul(float a) {
      int size = this.size();
      MatrixNf d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = this.mat[row][col] * a;
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNf mul(MatrixNf m) {
      int size = this.size();
      if (size != m.size()) {
         throw new IllegalArgumentException("Matrix sizes must be the same");
      } else {
         MatrixNf d = from(size);

         for(int row = 0; row < size; ++row) {
            for(int col = 0; col < size; ++col) {
               float dot = 0.0F;

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
   public MatrixNf div(double a) {
      return this.div((float)a);
   }

   @Nonnull
   public MatrixNf div(float a) {
      int size = this.size();
      MatrixNf d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = this.mat[row][col] / a;
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNf div(MatrixNf m) {
      return this.mul(m.invert());
   }

   @Nonnull
   public MatrixNf pow(double pow) {
      return this.pow((float)pow);
   }

   @Nonnull
   public MatrixNf pow(float pow) {
      int size = this.size();
      MatrixNf d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = (float)Math.pow((double)this.mat[row][col], (double)pow);
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNf translate(VectorNf v) {
      return this.translate(v.toArray());
   }

   @Nonnull
   public MatrixNf translate(float... v) {
      return createTranslation(v).mul(this);
   }

   @Nonnull
   public MatrixNf scale(VectorNf v) {
      return this.scale(v.toArray());
   }

   @Nonnull
   public MatrixNf scale(float... v) {
      return createScaling(v).mul(this);
   }

   @Nonnull
   public MatrixNf rotate(Complexf rot) {
      return createRotation(this.size(), rot).mul(this);
   }

   @Nonnull
   public MatrixNf rotate(Quaternionf rot) {
      return createRotation(this.size(), rot).mul(this);
   }

   @Nonnull
   public VectorNf transform(VectorNf v) {
      return this.transform(v.toArray());
   }

   @Nonnull
   public VectorNf transform(float... vec) {
      int size = this.size();
      if (size != vec.length) {
         throw new IllegalArgumentException("Matrix and vector sizes must be the same");
      } else {
         VectorNf d = VectorNf.from(size);

         for(int row = 0; row < size; ++row) {
            float dot = 0.0F;

            for(int col = 0; col < size; ++col) {
               dot += this.mat[row][col] * vec[col];
            }

            d.set(row, dot);
         }

         return d;
      }
   }

   @Nonnull
   public MatrixNf floor() {
      int size = this.size();
      MatrixNf d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = (float)GenericMath.floor(this.mat[row][col]);
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNf ceil() {
      int size = this.size();
      MatrixNf d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = (float)Math.ceil((double)this.mat[row][col]);
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNf round() {
      int size = this.size();
      MatrixNf d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = (float)Math.round(this.mat[row][col]);
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNf abs() {
      int size = this.size();
      MatrixNf d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = Math.abs(this.mat[row][col]);
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNf negate() {
      int size = this.size();
      MatrixNf d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = -this.mat[row][col];
         }
      }

      return d;
   }

   @Nonnull
   public MatrixNf transpose() {
      int size = this.size();
      MatrixNf d = from(size);

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            d.mat[row][col] = this.mat[col][row];
         }
      }

      return d;
   }

   public float trace() {
      int size = this.size();
      float trace = 0.0F;

      for(int rowCol = 0; rowCol < size; ++rowCol) {
         trace += this.mat[rowCol][rowCol];
      }

      return trace;
   }

   public float determinant() {
      int size = this.size();
      float[][] m = deepClone(this.mat);

      for(int i = 0; i < size - 1; ++i) {
         for(int col = i + 1; col < size; ++col) {
            float det = m[i][i] < GenericMath.FLT_EPSILON ? 0.0F : m[i][col] / m[i][i];

            for(int row = i; row < size; ++row) {
               m[row][col] -= det * m[row][i];
            }
         }
      }

      float det = 1.0F;

      for(int i = 0; i < size; ++i) {
         det *= m[i][i];
      }

      return det;
   }

   @Nonnull
   public MatrixNf invert() {
      if (Math.abs(this.determinant()) < GenericMath.FLT_EPSILON) {
         throw new ArithmeticException("Cannot inverse a matrix with a zero determinant");
      } else {
         int size = this.size();
         AugmentedMatrixN augMat = new AugmentedMatrixN(this);
         int augmentedSize = augMat.getAugmentedSize();

         for(int i = 0; i < size; ++i) {
            for(int row = 0; row < size; ++row) {
               if (i != row) {
                  float ratio = augMat.get(row, i) / augMat.get(i, i);

                  for(int col = 0; col < augmentedSize; ++col) {
                     augMat.set(row, col, augMat.get(row, col) - ratio * augMat.get(i, col));
                  }
               }
            }
         }

         for(int row = 0; row < size; ++row) {
            float div = augMat.get(row, row);

            for(int col = 0; col < augmentedSize; ++col) {
               augMat.set(row, col, augMat.get(row, col) / div);
            }
         }

         return augMat.getAugmentation();
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
   public Matrix4f toMatrix4() {
      return Matrix4f.from(this);
   }

   @Nonnull
   public float[] toArray() {
      return this.toArray(false);
   }

   @Nonnull
   public MatrixNf toFloat() {
      int size = this.size();
      float[] m = new float[size * size];

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            m[col + row * size] = this.get(row, col);
         }
      }

      return from(m);
   }

   @Nonnull
   public MatrixNd toDouble() {
      int size = this.size();
      double[] m = new double[size * size];

      for(int row = 0; row < size; ++row) {
         for(int col = 0; col < size; ++col) {
            m[col + row * size] = (double)this.get(row, col);
         }
      }

      return MatrixNd.from(m);
   }

   @Nonnull
   public float[] toArray(boolean columnMajor) {
      int size = this.size();
      float[] array = new float[size * size];
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
         return !(obj instanceof MatrixNf) ? false : Arrays.deepEquals(this.mat, ((MatrixNf)obj).mat);
      }
   }

   public int hashCode() {
      return 395 + Arrays.deepHashCode(this.mat);
   }

   @Nonnull
   public MatrixNf clone() {
      return from(this);
   }

   @Nonnull
   public static MatrixNf createScaling(VectorNf v) {
      return createScaling(v.toArray());
   }

   @Nonnull
   public static MatrixNf createScaling(float... vec) {
      int size = vec.length;
      MatrixNf m = from(size);

      for(int rowCol = 0; rowCol < size; ++rowCol) {
         m.set(rowCol, rowCol, vec[rowCol]);
      }

      return m;
   }

   @Nonnull
   public static MatrixNf createTranslation(VectorNf v) {
      return createTranslation(v.toArray());
   }

   @Nonnull
   public static MatrixNf createTranslation(float... vec) {
      int size = vec.length;
      MatrixNf m = from(size + 1);

      for(int row = 0; row < size; ++row) {
         m.set(row, size, vec[row]);
      }

      return m;
   }

   @Nonnull
   public static MatrixNf createRotation(int size, Complexf rot) {
      if (size < 2) {
         throw new IllegalArgumentException("Minimum matrix size is 2");
      } else {
         MatrixNf m = from(size);
         rot = rot.normalize();
         m.set(0, 0, rot.getX());
         m.set(0, 1, -rot.getY());
         m.set(1, 0, rot.getY());
         m.set(1, 1, rot.getX());
         return m;
      }
   }

   @Nonnull
   public static MatrixNf createRotation(int size, Quaternionf rot) {
      if (size < 3) {
         throw new IllegalArgumentException("Minimum matrix size is 3");
      } else {
         MatrixNf m = from(size);
         rot = rot.normalize();
         m.set(0, 0, 1.0F - 2.0F * rot.getY() * rot.getY() - 2.0F * rot.getZ() * rot.getZ());
         m.set(0, 1, 2.0F * rot.getX() * rot.getY() - 2.0F * rot.getW() * rot.getZ());
         m.set(0, 2, 2.0F * rot.getX() * rot.getZ() + 2.0F * rot.getW() * rot.getY());
         m.set(1, 0, 2.0F * rot.getX() * rot.getY() + 2.0F * rot.getW() * rot.getZ());
         m.set(1, 1, 1.0F - 2.0F * rot.getX() * rot.getX() - 2.0F * rot.getZ() * rot.getZ());
         m.set(1, 2, 2.0F * rot.getY() * rot.getZ() - 2.0F * rot.getW() * rot.getX());
         m.set(2, 0, 2.0F * rot.getX() * rot.getZ() - 2.0F * rot.getW() * rot.getY());
         m.set(2, 1, 2.0F * rot.getY() * rot.getZ() + 2.0F * rot.getX() * rot.getW());
         m.set(2, 2, 1.0F - 2.0F * rot.getX() * rot.getX() - 2.0F * rot.getY() * rot.getY());
         return m;
      }
   }

   @Nonnull
   public static MatrixNf createLookAt(int size, Vector3f eye, Vector3f at, Vector3f up) {
      if (size < 4) {
         throw new IllegalArgumentException("Minimum matrix size is 4");
      } else {
         Vector3f f = at.sub(eye).normalize();
         up = up.normalize();
         Vector3f s = f.cross(up).normalize();
         Vector3f u = s.cross(f).normalize();
         MatrixNf mat = from(size);
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
   public static MatrixNf createPerspective(int size, double fov, double aspect, double near, double far) {
      return createPerspective(size, (float)fov, (float)aspect, (float)near, (float)far);
   }

   @Nonnull
   public static MatrixNf createPerspective(int size, float fov, float aspect, float near, float far) {
      if (size < 4) {
         throw new IllegalArgumentException("Minimum matrix size is 4");
      } else {
         MatrixNf perspective = from(size);
         float scale = 1.0F / TrigMath.tan((double)(fov * 0.008726646F));
         perspective.set(0, 0, scale / aspect);
         perspective.set(1, 1, scale);
         perspective.set(2, 2, (far + near) / (near - far));
         perspective.set(2, 3, 2.0F * far * near / (near - far));
         perspective.set(3, 2, -1.0F);
         perspective.set(3, 3, 0.0F);
         return perspective;
      }
   }

   @Nonnull
   public static MatrixNf createOrthographic(int size, double right, double left, double top, double bottom, double near, double far) {
      return createOrthographic(size, (float)right, (float)left, (float)top, (float)bottom, (float)near, (float)far);
   }

   @Nonnull
   public static MatrixNf createOrthographic(int size, float right, float left, float top, float bottom, float near, float far) {
      if (size < 4) {
         throw new IllegalArgumentException("Minimum matrix size is 4");
      } else {
         MatrixNf orthographic = from(size);
         orthographic.set(0, 0, 2.0F / (right - left));
         orthographic.set(1, 1, 2.0F / (top - bottom));
         orthographic.set(2, 2, -2.0F / (far - near));
         orthographic.set(0, 3, -(right + left) / (right - left));
         orthographic.set(1, 3, -(top + bottom) / (top - bottom));
         orthographic.set(2, 3, -(far + near) / (far - near));
         return orthographic;
      }
   }

   @Nonnull
   private static float[][] deepClone(float[][] array) {
      int size = array.length;
      float[][] clone = ((array).clone());

      for(int i = 0; i < size; ++i) {
         clone[i] = array[i].clone();
      }

      return clone;
   }

   @Nonnull
   public static MatrixNf from(int size) {
      if (size < 2) {
         throw new IllegalArgumentException("Minimum matrix size is 2");
      } else {
         MatrixNf mat = new MatrixNf(new float[size][size]);
         mat.setIdentity();
         return mat;
      }
   }

   @Nonnull
   public static MatrixNf from(Matrix2f m) {
      return new MatrixNf(new float[][]{{m.get(0, 0), m.get(0, 1)}, {m.get(1, 0), m.get(1, 1)}});
   }

   @Nonnull
   public static MatrixNf from(Matrix3f m) {
      return new MatrixNf(new float[][]{{m.get(0, 0), m.get(0, 1), m.get(0, 2)}, {m.get(1, 0), m.get(1, 1), m.get(1, 2)}, {m.get(2, 0), m.get(2, 1), m.get(2, 2)}});
   }

   @Nonnull
   public static MatrixNf from(Matrix4f m) {
      return new MatrixNf(new float[][]{{m.get(0, 0), m.get(0, 1), m.get(0, 2), m.get(0, 3)}, {m.get(1, 0), m.get(1, 1), m.get(1, 2), m.get(1, 3)}, {m.get(2, 0), m.get(2, 1), m.get(2, 2), m.get(2, 3)}, {m.get(3, 0), m.get(3, 1), m.get(3, 2), m.get(3, 3)}});
   }

   @Nonnull
   public static MatrixNf from(float... m) {
      if (m.length < 4) {
         throw new IllegalArgumentException("Minimum matrix size is 2");
      } else {
         int size = (int)Math.ceil(Math.sqrt((double)m.length));
         float[][] mat = new float[size][size];

         for(int row = 0; row < size; ++row) {
            for(int col = 0; col < size; ++col) {
               int index = col + row * size;
               if (index < m.length) {
                  mat[row][col] = m[index];
               } else {
                  mat[row][col] = 0.0F;
               }
            }
         }

         return new MatrixNf(mat);
      }
   }

   @Nonnull
   public static MatrixNf from(MatrixNf m) {
      return new MatrixNf(deepClone(m.mat));
   }

   private static class ImmutableIdentityMatrixN extends MatrixNf {
      public ImmutableIdentityMatrixN(int size) {
         super(new float[size][size]);
         this.setIdentity();
      }

      public void set(int row, int col, float val) {
         throw new UnsupportedOperationException("You may not alter this matrix");
      }

      public void setZero() {
         throw new UnsupportedOperationException("You may not alter this matrix");
      }
   }

   private static class AugmentedMatrixN {
      private final MatrixNf mat;
      private final MatrixNf aug;
      private final int size;

      private AugmentedMatrixN(@Nonnull MatrixNf mat) {
         this.mat = mat.clone();
         this.size = mat.size();
         this.aug = MatrixNf.from(this.size);
      }

      @Nonnull
      private MatrixNf getAugmentation() {
         return this.aug;
      }

      private int getAugmentedSize() {
         return this.size * 2;
      }

      private float get(int row, int col) {
         return col < this.size ? this.mat.get(row, col) : this.aug.get(row, col - this.size);
      }

      private void set(int row, int col, float val) {
         if (col < this.size) {
            this.mat.set(row, col, val);
         } else {
            this.aug.set(row, col - this.size, val);
         }

      }
   }
}
