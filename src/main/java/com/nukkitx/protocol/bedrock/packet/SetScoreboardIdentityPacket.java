package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.UUID;

public class SetScoreboardIdentityPacket implements BedrockPacket {
   private final List<Entry> entries = new ObjectArrayList();
   private Action action;

   public final PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.SET_SCOREBOARD_IDENTITY;
   }

   public List<Entry> getEntries() {
      return this.entries;
   }

   public Action getAction() {
      return this.action;
   }

   public void setAction(Action action) {
      this.action = action;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SetScoreboardIdentityPacket)) {
         return false;
      } else {
         SetScoreboardIdentityPacket other = (SetScoreboardIdentityPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$entries = this.entries;
            Object other$entries = other.entries;
            if (this$entries == null) {
               if (other$entries != null) {
                  return false;
               }
            } else if (!this$entries.equals(other$entries)) {
               return false;
            }

            Object this$action = this.action;
            Object other$action = other.action;
            if (this$action == null) {
               if (other$action != null) {
                  return false;
               }
            } else if (!this$action.equals(other$action)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SetScoreboardIdentityPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $entries = this.entries;
      result = result * 59 + ($entries == null ? 43 : $entries.hashCode());
      Object $action = this.action;
      result = result * 59 + ($action == null ? 43 : $action.hashCode());
      return result;
   }

   public String toString() {
      return "SetScoreboardIdentityPacket(entries=" + this.entries + ", action=" + this.action + ")";
   }

   public static enum Action {
      ADD,
      REMOVE;

      // $FF: synthetic method
      private static Action[] $values() {
         return new Action[]{ADD, REMOVE};
      }
   }

   public static final class Entry {
      private final long scoreboardId;
      private final UUID uuid;

      public Entry(long scoreboardId, UUID uuid) {
         this.scoreboardId = scoreboardId;
         this.uuid = uuid;
      }

      public long getScoreboardId() {
         return this.scoreboardId;
      }

      public UUID getUuid() {
         return this.uuid;
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof Entry)) {
            return false;
         } else {
            Entry other = (Entry)o;
            if (this.getScoreboardId() != other.getScoreboardId()) {
               return false;
            } else {
               Object this$uuid = this.getUuid();
               Object other$uuid = other.getUuid();
               if (this$uuid == null) {
                  if (other$uuid != null) {
                     return false;
                  }
               } else if (!this$uuid.equals(other$uuid)) {
                  return false;
               }

               return true;
            }
         }
      }

      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         long $scoreboardId = this.getScoreboardId();
         result = result * 59 + (int)($scoreboardId >>> 32 ^ $scoreboardId);
         Object $uuid = this.getUuid();
         result = result * 59 + ($uuid == null ? 43 : $uuid.hashCode());
         return result;
      }

      public String toString() {
         return "SetScoreboardIdentityPacket.Entry(scoreboardId=" + this.getScoreboardId() + ", uuid=" + this.getUuid() + ")";
      }
   }
}
