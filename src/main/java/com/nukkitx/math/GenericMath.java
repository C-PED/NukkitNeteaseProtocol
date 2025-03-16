package com.nukkitx.math;

import com.nukkitx.math.imaginary.Quaterniond;
import com.nukkitx.math.imaginary.Quaternionf;
import com.nukkitx.math.vector.Vector2d;
import com.nukkitx.math.vector.Vector2f;
import com.nukkitx.math.vector.Vector3d;
import com.nukkitx.math.vector.Vector3f;
import com.nukkitx.math.vector.Vector4d;
import com.nukkitx.math.vector.Vector4f;
import com.nukkitx.math.vector.VectorNd;
import com.nukkitx.math.vector.VectorNf;
import java.awt.Color;

public class GenericMath {
   public static final double DBL_EPSILON = Double.longBitsToDouble(4372995238176751616L);
   public static final float FLT_EPSILON = Float.intBitsToFloat(872415232);

   private GenericMath() {
   }

   public static float getDegreeDifference(float angle1, float angle2) {
      return Math.abs(wrapAngleDeg(angle1 - angle2));
   }

   public static double getRadianDifference(double radian1, double radian2) {
      return Math.abs(wrapAngleRad(radian1 - radian2));
   }

   public static float wrapAngleDeg(float angle) {
      angle %= 360.0F;
      if (angle <= -180.0F) {
         return angle + 360.0F;
      } else {
         return angle > 180.0F ? angle - 360.0F : angle;
      }
   }

   public static double wrapAngleRad(double angle) {
      angle %= (Math.PI * 2D);
      if (angle <= -Math.PI) {
         return angle + (Math.PI * 2D);
      } else {
         return angle > Math.PI ? angle - (Math.PI * 2D) : angle;
      }
   }

   public static float wrapAnglePitchDeg(float angle) {
      angle = wrapAngleDeg(angle);
      if (angle < -90.0F) {
         return -90.0F;
      } else {
         return angle > 90.0F ? 90.0F : angle;
      }
   }

   public static byte wrapByte(int value) {
      value %= 256;
      if (value < 0) {
         value += 256;
      }

      return (byte)value;
   }

   public static double round(double input, int decimals) {
      double p = Math.pow((double)10.0F, (double)decimals);
      return (double)Math.round(input * p) / p;
   }

   public static double lerp(double a, double b, double percent) {
      return ((double)1.0F - percent) * a + percent * b;
   }

   public static float lerp(float a, float b, float percent) {
      return (1.0F - percent) * a + percent * b;
   }

   public static int lerp(int a, int b, int percent) {
      return (1 - percent) * a + percent * b;
   }

   public static Vector3f lerp(Vector3f a, Vector3f b, float percent) {
      return a.mul(1.0F - percent).add(b.mul(percent));
   }

   public static Vector3d lerp(Vector3d a, Vector3d b, double percent) {
      return a.mul((double)1.0F - percent).add(b.mul(percent));
   }

   public static Vector2f lerp(Vector2f a, Vector2f b, float percent) {
      return a.mul(1.0F - percent).add(b.mul(percent));
   }

   public static Vector2d lerp(Vector2d a, Vector2d b, double percent) {
      return a.mul((double)1.0F - percent).add(b.mul(percent));
   }

   public static double lerp(double x, double x1, double x2, double q0, double q1) {
      return (x2 - x) / (x2 - x1) * q0 + (x - x1) / (x2 - x1) * q1;
   }

   public static Color lerp(Color a, Color b, float percent) {
      int red = (int)lerp((float)a.getRed(), (float)b.getRed(), percent);
      int blue = (int)lerp((float)a.getBlue(), (float)b.getBlue(), percent);
      int green = (int)lerp((float)a.getGreen(), (float)b.getGreen(), percent);
      int alpha = (int)lerp((float)a.getAlpha(), (float)b.getAlpha(), percent);
      return new Color(red, green, blue, alpha);
   }

   public static Quaternionf slerp(Quaternionf a, Quaternionf b, float percent) {
      float cosineTheta = a.dot(b);
      float inverted;
      if (cosineTheta < 0.0F) {
         cosineTheta = -cosineTheta;
         inverted = -1.0F;
      } else {
         inverted = 1.0F;
      }

      if (1.0F - cosineTheta < FLT_EPSILON) {
         return a.mul(1.0F - percent).add(b.mul(percent * inverted));
      } else {
         float theta = (float)TrigMath.acos((double)cosineTheta);
         float sineTheta = TrigMath.sin((double)theta);
         float coefficient1 = TrigMath.sin((double)((1.0F - percent) * theta)) / sineTheta;
         float coefficient2 = TrigMath.sin((double)(percent * theta)) / sineTheta * inverted;
         return a.mul(coefficient1).add(b.mul(coefficient2));
      }
   }

   public static Quaterniond slerp(Quaterniond a, Quaterniond b, double percent) {
      double cosineTheta = a.dot(b);
      double inverted;
      if (cosineTheta < (double)0.0F) {
         cosineTheta = -cosineTheta;
         inverted = (double)-1.0F;
      } else {
         inverted = (double)1.0F;
      }

      if ((double)1.0F - cosineTheta < DBL_EPSILON) {
         return a.mul((double)1.0F - percent).add(b.mul(percent * inverted));
      } else {
         double theta = TrigMath.acos(cosineTheta);
         double sineTheta = (double)TrigMath.sin(theta);
         double coefficient1 = (double)TrigMath.sin(((double)1.0F - percent) * theta) / sineTheta;
         double coefficient2 = (double)TrigMath.sin(percent * theta) / sineTheta * inverted;
         return a.mul(coefficient1).add(b.mul(coefficient2));
      }
   }

   public static Quaternionf lerp(Quaternionf a, Quaternionf b, float percent) {
      return a.mul(1.0F - percent).add(b.mul(percent));
   }

   public static Quaterniond lerp(Quaterniond a, Quaterniond b, double percent) {
      return a.mul((double)1.0F - percent).add(b.mul(percent));
   }

   public static double biLerp(double x, double y, double q00, double q01, double q10, double q11, double x1, double x2, double y1, double y2) {
      double q0 = lerp(x, x1, x2, q00, q10);
      double q1 = lerp(x, x1, x2, q01, q11);
      return lerp(y, y1, y2, q0, q1);
   }

   public static double triLerp(double x, double y, double z, double q000, double q001, double q010, double q011, double q100, double q101, double q110, double q111, double x1, double x2, double y1, double y2, double z1, double z2) {
      double q00 = lerp(x, x1, x2, q000, q100);
      double q01 = lerp(x, x1, x2, q010, q110);
      double q10 = lerp(x, x1, x2, q001, q101);
      double q11 = lerp(x, x1, x2, q011, q111);
      double q0 = lerp(y, y1, y2, q00, q10);
      double q1 = lerp(y, y1, y2, q01, q11);
      return lerp(z, z1, z2, q0, q1);
   }

   public static Color blend(Color a, Color b) {
      return lerp(a, b, (float)a.getAlpha() / 255.0F);
   }

   public static double clamp(double value, double low, double high) {
      if (value < low) {
         return low;
      } else {
         return value > high ? high : value;
      }
   }

   public static int clamp(int value, int low, int high) {
      if (value < low) {
         return low;
      } else {
         return value > high ? high : value;
      }
   }

   public static double inverseSqrt(double a) {
      double halfA = (double)0.5F * a;
      a = Double.longBitsToDouble(6910469410427058090L - (Double.doubleToRawLongBits(a) >> 1));
      return a * ((double)1.5F - halfA * a * a);
   }

   public static double sqrt(double a) {
      return a * inverseSqrt(a);
   }

   public static int ceil(double a) {
      int possible_result = (int)a;
      if (a - (double)possible_result > (double)0.0F) {
         ++possible_result;
      }

      return possible_result;
   }

   public static int ceil(float a) {
      int possible_result = (int)a;
      if (a - (float)possible_result > 0.0F) {
         ++possible_result;
      }

      return possible_result;
   }

   public static long ceil64(double a) {
      long possible_result = (long)a;
      if (a - (double)possible_result > (double)0.0F) {
         ++possible_result;
      }

      return possible_result;
   }

   public static long ceil64(float a) {
      long possible_result = (long)a;
      if (a - (float)possible_result > 0.0F) {
         ++possible_result;
      }

      return possible_result;
   }

   public static int floor(double a) {
      int y = (int)a;
      return a < (double)y ? y - 1 : y;
   }

   public static int floor(float a) {
      int y = (int)a;
      return a < (float)y ? y - 1 : y;
   }

   public static long floor64(double a) {
      long y = (long)a;
      return a < (double)y ? y - 1L : y;
   }

   public static long floor64(float a) {
      long y = (long)a;
      return a < (float)y ? y - 1L : y;
   }

   public static byte max(byte value1, byte value2) {
      return value1 > value2 ? value1 : value2;
   }

   public static int roundUpPow2(int a) {
      if (a <= 0) {
         return 1;
      } else if (a > 1073741824) {
         throw new IllegalArgumentException("Rounding " + a + " to the next highest power of two would exceed the int range");
      } else {
         --a;
         a |= a >> 1;
         a |= a >> 2;
         a |= a >> 4;
         a |= a >> 8;
         a |= a >> 16;
         ++a;
         return a;
      }
   }

   public static long roundUpPow2(long a) {
      if (a <= 0L) {
         return 1L;
      } else if (a > 4611686018427387904L) {
         throw new IllegalArgumentException("Rounding " + a + " to the next highest power of two would exceed the int range");
      } else {
         --a;
         a |= a >> 1;
         a |= a >> 2;
         a |= a >> 4;
         a |= a >> 8;
         a |= a >> 16;
         a |= a >> 32;
         ++a;
         return a;
      }
   }

   public static Float castFloat(Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof Number) {
         return ((Number)o).floatValue();
      } else {
         try {
            return Float.valueOf(o.toString());
         } catch (NumberFormatException var2) {
            return null;
         }
      }
   }

   public static Byte castByte(Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof Number) {
         return ((Number)o).byteValue();
      } else {
         try {
            return Byte.valueOf(o.toString());
         } catch (NumberFormatException var2) {
            return null;
         }
      }
   }

   public static Short castShort(Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof Number) {
         return ((Number)o).shortValue();
      } else {
         try {
            return Short.valueOf(o.toString());
         } catch (NumberFormatException var2) {
            return null;
         }
      }
   }

   public static Integer castInt(Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof Number) {
         return ((Number)o).intValue();
      } else {
         try {
            return Integer.valueOf(o.toString());
         } catch (NumberFormatException var2) {
            return null;
         }
      }
   }

   public static Double castDouble(Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof Number) {
         return ((Number)o).doubleValue();
      } else {
         try {
            return Double.valueOf(o.toString());
         } catch (NumberFormatException var2) {
            return null;
         }
      }
   }

   public static Long castLong(Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof Number) {
         return ((Number)o).longValue();
      } else {
         try {
            return Long.valueOf(o.toString());
         } catch (NumberFormatException var2) {
            return null;
         }
      }
   }

   public static Boolean castBoolean(Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof Boolean) {
         return (Boolean)o;
      } else if (o instanceof String) {
         try {
            return Boolean.parseBoolean((String)o);
         } catch (IllegalArgumentException var2) {
            return null;
         }
      } else {
         return null;
      }
   }

   public static int mean(int... values) {
      int sum = 0;

      for(int v : values) {
         sum += v;
      }

      return sum / values.length;
   }

   public static double mean(double... values) {
      double sum = (double)0.0F;

      for(double v : values) {
         sum += v;
      }

      return sum / (double)values.length;
   }

   public static String decToHex(int dec, int minDigits) {
      StringBuilder ret = new StringBuilder(Integer.toHexString(dec));

      while(ret.length() < minDigits) {
         ret.insert(0, '0');
      }

      return ret.toString();
   }

   public static int mod(int a, int div) {
      int remainder = a % div;
      return remainder < 0 ? remainder + div : remainder;
   }

   public static float mod(float a, float div) {
      float remainder = a % div;
      return remainder < 0.0F ? remainder + div : remainder;
   }

   public static double mod(double a, double div) {
      double remainder = a % div;
      return remainder < (double)0.0F ? remainder + div : remainder;
   }

   public static boolean isPowerOfTwo(int num) {
      return num > 0 && (num & num - 1) == 0;
   }

   public static int multiplyToShift(int a) {
      if (a < 1) {
         throw new IllegalArgumentException("Multiplicand must be at least 1");
      } else {
         int shift = 31 - Integer.numberOfLeadingZeros(a);
         if (1 << shift != a) {
            throw new IllegalArgumentException("Multiplicand must be a power of 2");
         } else {
            return shift;
         }
      }
   }

   public static Vector2f normalizeSafe(Vector2f v) {
      try {
         return v.normalize();
      } catch (ArithmeticException var2) {
         return Vector2f.ZERO;
      }
   }

   public static Vector2d normalizeSafe(Vector2d v) {
      try {
         return v.normalize();
      } catch (ArithmeticException var2) {
         return Vector2d.ZERO;
      }
   }

   public static Vector3f normalizeSafe(Vector3f v) {
      try {
         return v.normalize();
      } catch (ArithmeticException var2) {
         return Vector3f.ZERO;
      }
   }

   public static Vector3d normalizeSafe(Vector3d v) {
      try {
         return v.normalize();
      } catch (ArithmeticException var2) {
         return Vector3d.ZERO;
      }
   }

   public static Vector4f normalizeSafe(Vector4f v) {
      try {
         return v.normalize();
      } catch (ArithmeticException var2) {
         return Vector4f.ZERO;
      }
   }

   public static Vector4d normalizeSafe(Vector4d v) {
      try {
         return v.normalize();
      } catch (ArithmeticException var2) {
         return Vector4d.ZERO;
      }
   }

   public static VectorNf normalizeSafe(VectorNf v) {
      try {
         return v.normalize();
      } catch (ArithmeticException var2) {
         return VectorNf.from(v.size());
      }
   }

   public static VectorNd normalizeSafe(VectorNd v) {
      try {
         return v.normalize();
      } catch (ArithmeticException var2) {
         return VectorNd.from(v.size());
      }
   }
}
