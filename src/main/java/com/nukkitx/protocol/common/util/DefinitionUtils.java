package com.nukkitx.protocol.common.util;

import com.nukkitx.protocol.common.Definition;
import com.nukkitx.protocol.common.DefinitionRegistry;

public class DefinitionUtils {
   public static <D extends Definition> D checkDefinition(DefinitionRegistry<D> registry, D definition) {
      if (!registry.isRegistered(definition)) {
         throw new IllegalArgumentException("Definition is not registered: " + definition);
      } else {
         return definition;
      }
   }
}
