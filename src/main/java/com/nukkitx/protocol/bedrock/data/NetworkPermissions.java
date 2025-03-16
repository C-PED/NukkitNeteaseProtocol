package com.nukkitx.protocol.bedrock.data;

public final class NetworkPermissions {
   public static final NetworkPermissions DEFAULT = new NetworkPermissions(false);
   private final boolean serverAuthSounds;

   public NetworkPermissions(boolean serverAuthSounds) {
      this.serverAuthSounds = serverAuthSounds;
   }

   public boolean isServerAuthSounds() {
      return this.serverAuthSounds;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NetworkPermissions)) {
         return false;
      } else {
         NetworkPermissions other = (NetworkPermissions)o;
         return this.isServerAuthSounds() == other.isServerAuthSounds();
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.isServerAuthSounds() ? 79 : 97);
      return result;
   }

   public String toString() {
      return "NetworkPermissions(serverAuthSounds=" + this.isServerAuthSounds() + ")";
   }
}
