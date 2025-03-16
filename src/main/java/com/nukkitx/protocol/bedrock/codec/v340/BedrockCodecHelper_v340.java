package com.nukkitx.protocol.bedrock.codec.v340;

import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v332.BedrockCodecHelper_v332;
import com.nukkitx.protocol.bedrock.data.definitions.ItemDefinition;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.packet.InventoryTransactionPacket;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import java.io.IOException;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

public class BedrockCodecHelper_v340 extends BedrockCodecHelper_v332 {
   protected static final String BLOCKING_ID = "minecraft:shield";

   public BedrockCodecHelper_v340(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes) {
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
               NBTInputStream reader = NbtUtils.createReaderLE(new ByteBufInputStream(buffer.readSlice(nbtSize), this.encodingSettings.maxItemNBTSize()));

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

         long blockingTicks = 0L;
         if (definition != null && "minecraft:shield".equals(definition.getIdentifier())) {
            blockingTicks = VarInts.readLong(buffer);
         }

         return ItemData.builder().definition(definition).damage(damage).count(count).tag(compoundTag).canPlace(canPlace).canBreak(canBreak).blockingTicks(blockingTicks).build();
      }
   }

   public void writeItem(ByteBuf buffer, ItemData item) {
      super.writeItem(buffer, item);
      ItemDefinition definition = item.getDefinition();
      if (definition != null && "minecraft:shield".equals(definition.getIdentifier())) {
         VarInts.writeLong(buffer, item.getBlockingTicks());
      }

   }

   public void readItemUse(ByteBuf buffer, InventoryTransactionPacket packet) {
      super.readItemUse(buffer, packet);
      packet.setBlockDefinition(this.blockDefinitions.getDefinition(VarInts.readUnsignedInt(buffer)));
   }

   public void writeItemUse(ByteBuf buffer, InventoryTransactionPacket packet) {
      super.writeItemUse(buffer, packet);
      VarInts.writeUnsignedInt(buffer, packet.getBlockDefinition().getRuntimeId());
   }
}
