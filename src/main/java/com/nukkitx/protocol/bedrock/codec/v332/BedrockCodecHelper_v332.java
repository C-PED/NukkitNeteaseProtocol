package com.nukkitx.protocol.bedrock.codec.v332;

import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v313.BedrockCodecHelper_v313;
import com.nukkitx.protocol.bedrock.data.definitions.ItemDefinition;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import java.io.IOException;
import java.util.Objects;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

public class BedrockCodecHelper_v332 extends BedrockCodecHelper_v313 {
   public BedrockCodecHelper_v332(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes) {
      super(entityData, gameRulesTypes);
   }

   public ItemData readItem(ByteBuf buffer) {
      int runtimeId = VarInts.readInt(buffer);
      if (runtimeId == 0) {
         return ItemData.AIR;
      } else {
         ItemDefinition definition = this.itemDefinitions.getDefinition(runtimeId);
         int aux = VarInts.readInt(buffer);
         int damage = (short)(aux >> 8);
         if (damage == 32767) {
            damage = -1;
         }

         int count = aux & 255;
         int nbtSize = buffer.readShortLE();
         NbtMap compoundTag = null;
         if (nbtSize > 0) {
            try {
               NBTInputStream reader = NbtUtils.createReaderLE(new ByteBufInputStream(buffer.readSlice(nbtSize)), (long)this.encodingSettings.maxItemNBTSize());

               try {
                  compoundTag = (NbtMap)reader.readTag();
               } catch (Throwable var17) {
                  if (reader != null) {
                     try {
                        reader.close();
                     } catch (Throwable var14) {
                        var17.addSuppressed(var14);
                     }
                  }

                  throw var17;
               }

               if (reader != null) {
                  reader.close();
               }
            } catch (IOException e) {
               throw new IllegalStateException("Unable to load NBT data", e);
            }
         } else if (nbtSize == -1) {
            int tagCount = buffer.readUnsignedByte();
            if (tagCount != 1) {
               throw new IllegalArgumentException("Expected 1 tag but got " + tagCount);
            }

            try {
               NBTInputStream reader = NbtUtils.createNetworkReader(new ByteBufInputStream(buffer));

               try {
                  compoundTag = (NbtMap)reader.readTag();
               } catch (Throwable var15) {
                  if (reader != null) {
                     try {
                        reader.close();
                     } catch (Throwable var13) {
                        var15.addSuppressed(var13);
                     }
                  }

                  throw var15;
               }

               if (reader != null) {
                  reader.close();
               }
            } catch (IOException e) {
               throw new IllegalStateException("Unable to load NBT data", e);
            }
         }

         String[] canPlace = new String[VarInts.readInt(buffer)];

         for(int i = 0; i < canPlace.length; ++i) {
            canPlace[i] = this.readString(buffer);
         }

         String[] canBreak = new String[VarInts.readInt(buffer)];

         for(int i = 0; i < canBreak.length; ++i) {
            canBreak[i] = this.readString(buffer);
         }

         return ItemData.builder().definition(definition).damage(damage).count(count).tag(compoundTag).canPlace(canPlace).canBreak(canBreak).build();
      }
   }

   public void writeItem(ByteBuf buffer, ItemData item) {
      Objects.requireNonNull(item, "item is null!");
      ItemDefinition definition = item.getDefinition();
      if (isAir(definition)) {
         buffer.writeByte(0);
      } else {
         VarInts.writeInt(buffer, definition.getRuntimeId());
         int damage = item.getDamage();
         if (damage == -1) {
            damage = 32767;
         }

         VarInts.writeInt(buffer, damage << 8 | item.getCount() & 255);
         if (item.getTag() != null) {
            buffer.writeShortLE(-1);
            buffer.writeByte(1);
            this.writeTag(buffer, item.getTag());
         } else {
            buffer.writeShortLE(0);
         }

         String[] canPlace = item.getCanPlace();
         VarInts.writeInt(buffer, canPlace.length);

         for(String aCanPlace : canPlace) {
            this.writeString(buffer, aCanPlace);
         }

         String[] canBreak = item.getCanBreak();
         VarInts.writeInt(buffer, canBreak.length);

         for(String aCanBreak : canBreak) {
            this.writeString(buffer, aCanBreak);
         }

      }
   }
}
