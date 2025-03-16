package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.PlayerPermission;
import com.nukkitx.protocol.common.PacketSignal;

public class RequestPermissionsPacket implements BedrockPacket {
   private long uniqueEntityId;
   private PlayerPermission permissions;
   private int customPermissions;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.REQUEST_PERMISSIONS;
   }

   public long getUniqueEntityId() {
      return this.uniqueEntityId;
   }

   public PlayerPermission getPermissions() {
      return this.permissions;
   }

   public int getCustomPermissions() {
      return this.customPermissions;
   }

   public void setUniqueEntityId(long uniqueEntityId) {
      this.uniqueEntityId = uniqueEntityId;
   }

   public void setPermissions(PlayerPermission permissions) {
      this.permissions = permissions;
   }

   public void setCustomPermissions(int customPermissions) {
      this.customPermissions = customPermissions;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RequestPermissionsPacket)) {
         return false;
      } else {
         RequestPermissionsPacket other = (RequestPermissionsPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.uniqueEntityId != other.uniqueEntityId) {
            return false;
         } else if (this.customPermissions != other.customPermissions) {
            return false;
         } else {
            Object this$permissions = this.permissions;
            Object other$permissions = other.permissions;
            if (this$permissions == null) {
               if (other$permissions != null) {
                  return false;
               }
            } else if (!this$permissions.equals(other$permissions)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RequestPermissionsPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $uniqueEntityId = this.uniqueEntityId;
      result = result * 59 + (int)($uniqueEntityId >>> 32 ^ $uniqueEntityId);
      result = result * 59 + this.customPermissions;
      Object $permissions = this.permissions;
      result = result * 59 + ($permissions == null ? 43 : $permissions.hashCode());
      return result;
   }

   public String toString() {
      return "RequestPermissionsPacket(uniqueEntityId=" + this.uniqueEntityId + ", permissions=" + this.permissions + ", customPermissions=" + this.customPermissions + ")";
   }
}
