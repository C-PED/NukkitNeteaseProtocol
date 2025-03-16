package com.nukkitx.natives.util;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Locale;

public class PlatformUtils {
   public static final String ARCHITECTURE = normalizeArch(getProperty("os.arch", ""));
   public static final String OPERATING_SYSTEM = normalizeOs(getProperty("os.name", ""));
   private static final boolean IS_ANDROID = isAndroid0();
   private static final int JAVA_VERSION = javaVersion0();

   private static String normalize(String value) {
      return value.toLowerCase(Locale.US).replaceAll("[^a-z0-9]+", "");
   }

   private static String normalizeArch(String value) {
      value = normalize(value);
      if (value.matches("^(x8664|amd64|ia32e|em64t|x64)$")) {
         return "x86_64";
      } else if (value.matches("^(x8632|x86|i[3-6]86|ia32|x32)$")) {
         return "x86_32";
      } else if (value.matches("^(ia64|itanium64)$")) {
         return "itanium_64";
      } else if (value.matches("^(sparc|sparc32)$")) {
         return "sparc_32";
      } else if (value.matches("^(sparcv9|sparc64)$")) {
         return "sparc_64";
      } else if (value.matches("^(arm|arm32)$")) {
         return "arm_32";
      } else if ("aarch64".equals(value)) {
         return "aarch_64";
      } else if (value.matches("^(ppc|ppc32)$")) {
         return "ppc_32";
      } else if ("ppc64".equals(value)) {
         return "ppc_64";
      } else if ("ppc64le".equals(value)) {
         return "ppcle_64";
      } else if ("s390".equals(value)) {
         return "s390_32";
      } else {
         return "s390x".equals(value) ? "s390_64" : "unknown";
      }
   }

   private static String normalizeOs(String value) {
      value = normalize(value);
      if (value.startsWith("aix")) {
         return "aix";
      } else if (value.startsWith("hpux")) {
         return "hpux";
      } else if (!value.startsWith("os400") || value.length() > 5 && Character.isDigit(value.charAt(5))) {
         if (value.startsWith("linux")) {
            return "linux";
         } else if (!value.startsWith("macosx") && !value.startsWith("osx")) {
            if (value.startsWith("freebsd")) {
               return "freebsd";
            } else if (value.startsWith("openbsd")) {
               return "openbsd";
            } else if (value.startsWith("netbsd")) {
               return "netbsd";
            } else if (!value.startsWith("solaris") && !value.startsWith("sunos")) {
               return value.startsWith("windows") ? "windows" : "unknown";
            } else {
               return "sunos";
            }
         } else {
            return "osx";
         }
      } else {
         return "os400";
      }
   }

   public static String getProperty(final String key, String def) {
      if (key == null) {
         throw new NullPointerException("key");
      } else if (key.isEmpty()) {
         throw new IllegalArgumentException("key must not be empty.");
      } else {
         String value = null;

         try {
            if (System.getSecurityManager() == null) {
               value = System.getProperty(key);
            } else {
               value = (String)AccessController.doPrivileged(new PrivilegedAction<String>() {
                  public String run() {
                     return System.getProperty(key);
                  }
               });
            }
         } catch (SecurityException var4) {
         }

         return value == null ? def : value;
      }
   }

   static boolean isAndroid() {
      return IS_ANDROID;
   }

   private static boolean isAndroid0() {
      String vmName = System.getProperty("java.vm.name");
      return "Dalvik".equals(vmName);
   }

   static int javaVersion() {
      return JAVA_VERSION;
   }

   private static int javaVersion0() {
      int majorVersion;
      if (isAndroid0()) {
         majorVersion = 6;
      } else {
         majorVersion = majorVersionFromJavaSpecificationVersion();
      }

      return majorVersion;
   }

   private static int majorVersionFromJavaSpecificationVersion() {
      return majorVersion(System.getProperty("java.specification.version", "1.6"));
   }

   private static int majorVersion(String javaSpecVersion) {
      String[] components = javaSpecVersion.split("\\.");
      int[] version = new int[components.length];

      for(int i = 0; i < components.length; ++i) {
         version[i] = Integer.parseInt(components[i]);
      }

      if (version[0] == 1) {
         assert version[1] >= 6;

         return version[1];
      } else {
         return version[0];
      }
   }
}
