package com.nukkitx.protocol.common;

public interface DefinitionRegistry<D extends Definition> {
   D getDefinition(int var1);

   boolean isRegistered(D var1);
}
