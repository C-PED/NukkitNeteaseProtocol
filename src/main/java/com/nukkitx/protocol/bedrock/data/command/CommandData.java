package com.nukkitx.protocol.bedrock.data.command;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class CommandData {
   private final String name;
   private final String description;
   private final Set<Flag> flags;
   private final CommandPermission permission;
   private final CommandEnumData aliases;
   private final List<ChainedSubCommandData> subcommands;
   private final CommandOverloadData[] overloads;

   public String toString() {
      StringBuilder overloads = new StringBuilder("[\r\n");

      for(CommandOverloadData overload : this.overloads) {
         overloads.append("    [\r\n");
         overloads.append("       chaining=").append(overload.isChaining()).append("\r\n");

         for(CommandParamData parameter : overload.getOverloads()) {
            overloads.append("       ").append(parameter).append("\r\n");
         }

         overloads.append("    ]\r\n");
      }

      overloads.append("]\r\n");
      StringBuilder builder = new StringBuilder("CommandData(\r\n");

      for(Object object : Arrays.asList("name=" + this.name, "description=" + this.description, "flags=" + Arrays.toString(this.flags.toArray()), "permission=" + this.permission, "aliases=" + this.aliases, "subcommands=" + Arrays.toString(this.subcommands.toArray()), "overloads=" + overloads)) {
         builder.append("    ").append(Objects.toString(object).replaceAll("\r\n", "\r\n    ")).append("\r\n");
      }

      return builder.append(")").toString();
   }

   public CommandData(String name, String description, Set<Flag> flags, CommandPermission permission, CommandEnumData aliases, List<ChainedSubCommandData> subcommands, CommandOverloadData[] overloads) {
      this.name = name;
      this.description = description;
      this.flags = flags;
      this.permission = permission;
      this.aliases = aliases;
      this.subcommands = subcommands;
      this.overloads = overloads;
   }

   public String getName() {
      return this.name;
   }

   public String getDescription() {
      return this.description;
   }

   public Set<Flag> getFlags() {
      return this.flags;
   }

   public CommandPermission getPermission() {
      return this.permission;
   }

   public CommandEnumData getAliases() {
      return this.aliases;
   }

   public List<ChainedSubCommandData> getSubcommands() {
      return this.subcommands;
   }

   public CommandOverloadData[] getOverloads() {
      return this.overloads;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CommandData)) {
         return false;
      } else {
         CommandData other = (CommandData)o;
         Object this$name = this.getName();
         Object other$name = other.getName();
         if (this$name == null) {
            if (other$name != null) {
               return false;
            }
         } else if (!this$name.equals(other$name)) {
            return false;
         }

         Object this$description = this.getDescription();
         Object other$description = other.getDescription();
         if (this$description == null) {
            if (other$description != null) {
               return false;
            }
         } else if (!this$description.equals(other$description)) {
            return false;
         }

         Object this$flags = this.getFlags();
         Object other$flags = other.getFlags();
         if (this$flags == null) {
            if (other$flags != null) {
               return false;
            }
         } else if (!this$flags.equals(other$flags)) {
            return false;
         }

         Object this$permission = this.getPermission();
         Object other$permission = other.getPermission();
         if (this$permission == null) {
            if (other$permission != null) {
               return false;
            }
         } else if (!this$permission.equals(other$permission)) {
            return false;
         }

         Object this$aliases = this.getAliases();
         Object other$aliases = other.getAliases();
         if (this$aliases == null) {
            if (other$aliases != null) {
               return false;
            }
         } else if (!this$aliases.equals(other$aliases)) {
            return false;
         }

         Object this$subcommands = this.getSubcommands();
         Object other$subcommands = other.getSubcommands();
         if (this$subcommands == null) {
            if (other$subcommands != null) {
               return false;
            }
         } else if (!this$subcommands.equals(other$subcommands)) {
            return false;
         }

         if (!Arrays.deepEquals(this.getOverloads(), other.getOverloads())) {
            return false;
         } else {
            return true;
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $name = this.getName();
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $description = this.getDescription();
      result = result * 59 + ($description == null ? 43 : $description.hashCode());
      Object $flags = this.getFlags();
      result = result * 59 + ($flags == null ? 43 : $flags.hashCode());
      Object $permission = this.getPermission();
      result = result * 59 + ($permission == null ? 43 : $permission.hashCode());
      Object $aliases = this.getAliases();
      result = result * 59 + ($aliases == null ? 43 : $aliases.hashCode());
      Object $subcommands = this.getSubcommands();
      result = result * 59 + ($subcommands == null ? 43 : $subcommands.hashCode());
      result = result * 59 + Arrays.deepHashCode(this.getOverloads());
      return result;
   }

   public static enum Flag {
      TEST_USAGE,
      HIDDEN_FROM_COMMAND_BLOCK,
      HIDDEN_FROM_PLAYER,
      HIDDEN_FROM_AUTOMATION,
      LOCAL_SYNC,
      EXECUTE_DISALLOWED,
      MESSAGE_TYPE,
      NOT_CHEAT,
      ASYNC;

      // $FF: synthetic method
      private static Flag[] $values() {
         return new Flag[]{TEST_USAGE, HIDDEN_FROM_COMMAND_BLOCK, HIDDEN_FROM_PLAYER, HIDDEN_FROM_AUTOMATION, LOCAL_SYNC, EXECUTE_DISALLOWED, MESSAGE_TYPE, NOT_CHEAT, ASYNC};
      }
   }
}
