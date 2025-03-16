package com.nukkitx.natives.util;

import com.nukkitx.natives.NativeCode;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BooleanSupplier;

public class LibraryLoader implements BooleanSupplier {
   private static final Set<Path> toDelete = new HashSet();
   private final String name;

   public LibraryLoader(String name) {
      this.name = name;
   }

   private static String getExtension(String name) {
      String extension = "";
      int i = name.lastIndexOf(46);
      int p = Math.max(name.lastIndexOf(47), name.lastIndexOf(92));
      if (i > p) {
         extension = name.substring(i + 1);
      }

      return extension;
   }

   public boolean getAsBoolean() {
      String fullName = this.name + '-' + PlatformUtils.OPERATING_SYSTEM + '-' + PlatformUtils.ARCHITECTURE;
      String tmpName = "native-" + fullName;
      String libraryName = System.mapLibraryName(fullName);
      String libraryExtension = getExtension(libraryName);

      try {
         System.loadLibrary(tmpName);
         return true;
      } catch (Throwable var11) {
         try {
            InputStream resourceStream = NativeCode.class.getClassLoader().getResourceAsStream(libraryName);

            boolean var12;
            label56: {
               boolean var7;
               try {
                  if (resourceStream == null) {
                     var12 = false;
                     break label56;
                  }

                  Path tempPath = Files.createTempFile(tmpName, libraryExtension);
                  Files.copy(resourceStream, tempPath, new CopyOption[0]);
                  toDelete.add(tempPath);
                  System.loadLibrary(this.name);
                  var7 = true;
               } catch (Throwable var9) {
                  if (resourceStream != null) {
                     try {
                        resourceStream.close();
                     } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                     }
                  }

                  throw var9;
               }

               if (resourceStream != null) {
                  resourceStream.close();
               }

               return var7;
            }

            if (resourceStream != null) {
               resourceStream.close();
            }

            return var12;
         } catch (Throwable e) {
            System.out.println("Could not load native library: " + e.getMessage());
            return false;
         }
      }
   }

   static {
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
         for(Path path : toDelete) {
            try {
               Files.deleteIfExists(path);
            } catch (IOException var3) {
            }
         }

      }));
   }
}
