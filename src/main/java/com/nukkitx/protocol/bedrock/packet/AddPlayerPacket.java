package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.AbilityLayer;
import com.nukkitx.protocol.bedrock.data.GameType;
import com.nukkitx.protocol.bedrock.data.PlayerAbilityHolder;
import com.nukkitx.protocol.bedrock.data.PlayerPermission;
import com.nukkitx.protocol.bedrock.data.command.CommandPermission;
import com.nukkitx.protocol.bedrock.data.entity.EntityDataMap;
import com.nukkitx.protocol.bedrock.data.entity.EntityLinkData;
import com.nukkitx.protocol.bedrock.data.entity.EntityProperties;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.UUID;
import org.cloudburstmc.math.vector.Vector3f;

public class AddPlayerPacket implements BedrockPacket, PlayerAbilityHolder {
   private final EntityDataMap metadata = new EntityDataMap();
   private final List<EntityLinkData> entityLinks = new ObjectArrayList();
   private UUID uuid;
   private String username;
   private long uniqueEntityId;
   private long runtimeEntityId;
   private String platformChatId;
   private Vector3f position;
   private Vector3f motion;
   private Vector3f rotation;
   private ItemData hand;
   private final AdventureSettingsPacket adventureSettings = new AdventureSettingsPacket();
   private String deviceId;
   private int buildPlatform;
   private GameType gameType;
   private List<AbilityLayer> abilityLayers = new ObjectArrayList();
   private final EntityProperties properties = new EntityProperties();

   public void setUniqueEntityId(long uniqueEntityId) {
      this.uniqueEntityId = uniqueEntityId;
      this.adventureSettings.setUniqueEntityId(uniqueEntityId);
   }

   public PlayerPermission getPlayerPermission() {
      return this.adventureSettings.getPlayerPermission();
   }

   public void setPlayerPermission(PlayerPermission playerPermission) {
      this.adventureSettings.setPlayerPermission(playerPermission);
   }

   public CommandPermission getCommandPermission() {
      return this.adventureSettings.getCommandPermission();
   }

   public void setCommandPermission(CommandPermission commandPermission) {
      this.adventureSettings.setCommandPermission(commandPermission);
   }

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.ADD_PLAYER;
   }

   public EntityDataMap getMetadata() {
      return this.metadata;
   }

   public List<EntityLinkData> getEntityLinks() {
      return this.entityLinks;
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public String getUsername() {
      return this.username;
   }

   public long getUniqueEntityId() {
      return this.uniqueEntityId;
   }

   public long getRuntimeEntityId() {
      return this.runtimeEntityId;
   }

   public String getPlatformChatId() {
      return this.platformChatId;
   }

   public Vector3f getPosition() {
      return this.position;
   }

   public Vector3f getMotion() {
      return this.motion;
   }

   public Vector3f getRotation() {
      return this.rotation;
   }

   public ItemData getHand() {
      return this.hand;
   }

   public AdventureSettingsPacket getAdventureSettings() {
      return this.adventureSettings;
   }

   public String getDeviceId() {
      return this.deviceId;
   }

   public int getBuildPlatform() {
      return this.buildPlatform;
   }

   public GameType getGameType() {
      return this.gameType;
   }

   public List<AbilityLayer> getAbilityLayers() {
      return this.abilityLayers;
   }

   public EntityProperties getProperties() {
      return this.properties;
   }

   public void setUuid(UUID uuid) {
      this.uuid = uuid;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setRuntimeEntityId(long runtimeEntityId) {
      this.runtimeEntityId = runtimeEntityId;
   }

   public void setPlatformChatId(String platformChatId) {
      this.platformChatId = platformChatId;
   }

   public void setPosition(Vector3f position) {
      this.position = position;
   }

   public void setMotion(Vector3f motion) {
      this.motion = motion;
   }

   public void setRotation(Vector3f rotation) {
      this.rotation = rotation;
   }

   public void setHand(ItemData hand) {
      this.hand = hand;
   }

   public void setDeviceId(String deviceId) {
      this.deviceId = deviceId;
   }

   public void setBuildPlatform(int buildPlatform) {
      this.buildPlatform = buildPlatform;
   }

   public void setGameType(GameType gameType) {
      this.gameType = gameType;
   }

   public void setAbilityLayers(List<AbilityLayer> abilityLayers) {
      this.abilityLayers = abilityLayers;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof AddPlayerPacket)) {
         return false;
      } else {
         AddPlayerPacket other = (AddPlayerPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.uniqueEntityId != other.uniqueEntityId) {
            return false;
         } else if (this.runtimeEntityId != other.runtimeEntityId) {
            return false;
         } else if (this.buildPlatform != other.buildPlatform) {
            return false;
         } else {
            Object this$metadata = this.metadata;
            Object other$metadata = other.metadata;
            if (this$metadata == null) {
               if (other$metadata != null) {
                  return false;
               }
            } else if (!this$metadata.equals(other$metadata)) {
               return false;
            }

            Object this$entityLinks = this.entityLinks;
            Object other$entityLinks = other.entityLinks;
            if (this$entityLinks == null) {
               if (other$entityLinks != null) {
                  return false;
               }
            } else if (!this$entityLinks.equals(other$entityLinks)) {
               return false;
            }

            Object this$uuid = this.uuid;
            Object other$uuid = other.uuid;
            if (this$uuid == null) {
               if (other$uuid != null) {
                  return false;
               }
            } else if (!this$uuid.equals(other$uuid)) {
               return false;
            }

            Object this$username = this.username;
            Object other$username = other.username;
            if (this$username == null) {
               if (other$username != null) {
                  return false;
               }
            } else if (!this$username.equals(other$username)) {
               return false;
            }

            Object this$platformChatId = this.platformChatId;
            Object other$platformChatId = other.platformChatId;
            if (this$platformChatId == null) {
               if (other$platformChatId != null) {
                  return false;
               }
            } else if (!this$platformChatId.equals(other$platformChatId)) {
               return false;
            }

            Object this$position = this.position;
            Object other$position = other.position;
            if (this$position == null) {
               if (other$position != null) {
                  return false;
               }
            } else if (!this$position.equals(other$position)) {
               return false;
            }

            Object this$motion = this.motion;
            Object other$motion = other.motion;
            if (this$motion == null) {
               if (other$motion != null) {
                  return false;
               }
            } else if (!this$motion.equals(other$motion)) {
               return false;
            }

            Object this$rotation = this.rotation;
            Object other$rotation = other.rotation;
            if (this$rotation == null) {
               if (other$rotation != null) {
                  return false;
               }
            } else if (!this$rotation.equals(other$rotation)) {
               return false;
            }

            Object this$hand = this.hand;
            Object other$hand = other.hand;
            if (this$hand == null) {
               if (other$hand != null) {
                  return false;
               }
            } else if (!this$hand.equals(other$hand)) {
               return false;
            }

            Object this$adventureSettings = this.adventureSettings;
            Object other$adventureSettings = other.adventureSettings;
            if (this$adventureSettings == null) {
               if (other$adventureSettings != null) {
                  return false;
               }
            } else if (!this$adventureSettings.equals(other$adventureSettings)) {
               return false;
            }

            Object this$deviceId = this.deviceId;
            Object other$deviceId = other.deviceId;
            if (this$deviceId == null) {
               if (other$deviceId != null) {
                  return false;
               }
            } else if (!this$deviceId.equals(other$deviceId)) {
               return false;
            }

            Object this$gameType = this.gameType;
            Object other$gameType = other.gameType;
            if (this$gameType == null) {
               if (other$gameType != null) {
                  return false;
               }
            } else if (!this$gameType.equals(other$gameType)) {
               return false;
            }

            Object this$abilityLayers = this.abilityLayers;
            Object other$abilityLayers = other.abilityLayers;
            if (this$abilityLayers == null) {
               if (other$abilityLayers != null) {
                  return false;
               }
            } else if (!this$abilityLayers.equals(other$abilityLayers)) {
               return false;
            }

            Object this$properties = this.properties;
            Object other$properties = other.properties;
            if (this$properties == null) {
               if (other$properties != null) {
                  return false;
               }
            } else if (!this$properties.equals(other$properties)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof AddPlayerPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      long $uniqueEntityId = this.uniqueEntityId;
      result = result * 59 + (int)($uniqueEntityId >>> 32 ^ $uniqueEntityId);
      long $runtimeEntityId = this.runtimeEntityId;
      result = result * 59 + (int)($runtimeEntityId >>> 32 ^ $runtimeEntityId);
      result = result * 59 + this.buildPlatform;
      Object $metadata = this.metadata;
      result = result * 59 + ($metadata == null ? 43 : $metadata.hashCode());
      Object $entityLinks = this.entityLinks;
      result = result * 59 + ($entityLinks == null ? 43 : $entityLinks.hashCode());
      Object $uuid = this.uuid;
      result = result * 59 + ($uuid == null ? 43 : $uuid.hashCode());
      Object $username = this.username;
      result = result * 59 + ($username == null ? 43 : $username.hashCode());
      Object $platformChatId = this.platformChatId;
      result = result * 59 + ($platformChatId == null ? 43 : $platformChatId.hashCode());
      Object $position = this.position;
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      Object $motion = this.motion;
      result = result * 59 + ($motion == null ? 43 : $motion.hashCode());
      Object $rotation = this.rotation;
      result = result * 59 + ($rotation == null ? 43 : $rotation.hashCode());
      Object $hand = this.hand;
      result = result * 59 + ($hand == null ? 43 : $hand.hashCode());
      Object $adventureSettings = this.adventureSettings;
      result = result * 59 + ($adventureSettings == null ? 43 : $adventureSettings.hashCode());
      Object $deviceId = this.deviceId;
      result = result * 59 + ($deviceId == null ? 43 : $deviceId.hashCode());
      Object $gameType = this.gameType;
      result = result * 59 + ($gameType == null ? 43 : $gameType.hashCode());
      Object $abilityLayers = this.abilityLayers;
      result = result * 59 + ($abilityLayers == null ? 43 : $abilityLayers.hashCode());
      Object $properties = this.properties;
      result = result * 59 + ($properties == null ? 43 : $properties.hashCode());
      return result;
   }

   public String toString() {
      return "AddPlayerPacket(metadata=" + this.metadata + ", entityLinks=" + this.entityLinks + ", uuid=" + this.uuid + ", username=" + this.username + ", uniqueEntityId=" + this.uniqueEntityId + ", runtimeEntityId=" + this.runtimeEntityId + ", platformChatId=" + this.platformChatId + ", position=" + this.position + ", motion=" + this.motion + ", rotation=" + this.rotation + ", hand=" + this.hand + ", adventureSettings=" + this.adventureSettings + ", deviceId=" + this.deviceId + ", buildPlatform=" + this.buildPlatform + ", gameType=" + this.gameType + ", abilityLayers=" + this.abilityLayers + ", properties=" + this.properties + ")";
   }
}
