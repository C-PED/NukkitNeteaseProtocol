package com.nukkitx.math;

public class TrigMath {
   public static final double PI = Math.PI;
   public static final double SQUARED_PI = 9.869604401089358;
   public static final double HALF_PI = (Math.PI / 2D);
   public static final double QUARTER_PI = (Math.PI / 4D);
   public static final double TWO_PI = (Math.PI * 2D);
   public static final double THREE_PI_HALVES = (Math.PI * 1.5D);
   public static final double DEG_TO_RAD = (Math.PI / 180D);
   public static final double HALF_DEG_TO_RAD = 0.008726646259971648;
   public static final double RAD_TO_DEG = (180D / Math.PI);
   public static final double SQRT_OF_TWO = Math.sqrt((double)2.0F);
   public static final double HALF_SQRT_OF_TWO;
   private static final int SIN_BITS = 22;
   private static final int SIN_SIZE = 4194304;
   private static final int SIN_MASK = 4194303;
   private static final float[] SIN_TABLE;
   private static final double SIN_CONVERSION_FACTOR = 667544.214430109;
   private static final int COS_OFFSET = 1048576;
   private static final double sq2p1 = 2.414213562373095;
   private static final double sq2m1 = 0.41421356237309503;
   private static final double p4 = 16.15364129822302;
   private static final double p3 = 268.42548195503974;
   private static final double p2 = 1153.029351540485;
   private static final double p1 = 1780.406316433197;
   private static final double p0 = 896.7859740366387;
   private static final double q4 = 58.95697050844462;
   private static final double q3 = 536.2653740312153;
   private static final double q2 = 1666.7838148816338;
   private static final double q1 = 2079.33497444541;
   private static final double q0 = 896.7859740366387;

   private TrigMath() {
   }

   public static float sin(double angle) {
      return sinRaw(GenericMath.floor(angle * 667544.214430109));
   }

   public static float cos(double angle) {
      return cosRaw(GenericMath.floor(angle * 667544.214430109));
   }

   public static float tan(double angle) {
      int idx = GenericMath.floor(angle * 667544.214430109);
      return sinRaw(idx) / cosRaw(idx);
   }

   public static float csc(double angle) {
      return 1.0F / sin(angle);
   }

   public static float sec(double angle) {
      return 1.0F / cos(angle);
   }

   public static float cot(double angle) {
      int idx = GenericMath.floor(angle * 667544.214430109);
      return cosRaw(idx) / sinRaw(idx);
   }

   public static double asin(double value) {
      if (value > (double)1.0F) {
         return Double.NaN;
      } else if (value < (double)0.0F) {
         return -asin(-value);
      } else {
         double temp = Math.sqrt((double)1.0F - value * value);
         return value > 0.7 ? (Math.PI / 2D) - msatan(temp / value) : msatan(value / temp);
      }
   }

   public static double acos(double value) {
      return !(value > (double)1.0F) && !(value < (double)-1.0F) ? (Math.PI / 2D) - asin(value) : Double.NaN;
   }

   public static double atan(double value) {
      return value > (double)0.0F ? msatan(value) : -msatan(-value);
   }

   public static double atan2(double y, double x) {
      if (y + x == y) {
         return y >= (double)0.0F ? (Math.PI / 2D) : (-Math.PI / 2D);
      } else {
         y = atan(y / x);
         if (x < (double)0.0F) {
            return y <= (double)0.0F ? y + Math.PI : y - Math.PI;
         } else {
            return y;
         }
      }
   }

   public static double acsc(double value) {
      return value == (double)0.0F ? Double.NaN : asin((double)1.0F / value);
   }

   public static double asec(double value) {
      return value == (double)0.0F ? Double.NaN : acos((double)1.0F / value);
   }

   public static double acot(double value) {
      if (value == (double)0.0F) {
         return Double.NaN;
      } else {
         return value > (double)0.0F ? atan((double)1.0F / value) : atan((double)1.0F / value) + Math.PI;
      }
   }

   private static float sinRaw(int idx) {
      return SIN_TABLE[idx & 4194303];
   }

   private static float cosRaw(int idx) {
      return SIN_TABLE[idx + 1048576 & 4194303];
   }

   private static double mxatan(double arg) {
      double argsq = arg * arg;
      double value = (((16.15364129822302 * argsq + 268.42548195503974) * argsq + 1153.029351540485) * argsq + 1780.406316433197) * argsq + 896.7859740366387;
      value /= ((((argsq + 58.95697050844462) * argsq + 536.2653740312153) * argsq + 1666.7838148816338) * argsq + 2079.33497444541) * argsq + 896.7859740366387;
      return value * arg;
   }

   private static double msatan(double arg) {
      if (arg < 0.41421356237309503) {
         return mxatan(arg);
      } else {
         return arg > 2.414213562373095 ? (Math.PI / 2D) - mxatan((double)1.0F / arg) : (Math.PI / 4D) + mxatan((arg - (double)1.0F) / (arg + (double)1.0F));
      }
   }

   static {
      HALF_SQRT_OF_TWO = SQRT_OF_TWO / (double)2.0F;
      SIN_TABLE = new float[4194304];

      for(int i = 0; i < 4194304; ++i) {
         SIN_TABLE[i] = (float)Math.sin((double)i * (Math.PI * 2D) / (double)4194304.0F);
      }

   }
}
