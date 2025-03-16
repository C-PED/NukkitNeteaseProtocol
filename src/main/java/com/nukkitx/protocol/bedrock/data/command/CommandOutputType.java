package com.nukkitx.protocol.bedrock.data.command;

public enum CommandOutputType {
   NONE,
   LAST_OUTPUT,
   SILENT,
   ALL_OUTPUT,
   DATA_SET;

   // $FF: synthetic method
   private static CommandOutputType[] $values() {
      return new CommandOutputType[]{NONE, LAST_OUTPUT, SILENT, ALL_OUTPUT, DATA_SET};
   }
}
