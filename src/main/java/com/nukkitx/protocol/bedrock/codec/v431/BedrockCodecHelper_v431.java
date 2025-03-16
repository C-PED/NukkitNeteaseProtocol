package com.nukkitx.protocol.bedrock.codec.v431;

import com.nukkitx.protocol.bedrock.codec.EntityDataTypeMap;
import com.nukkitx.protocol.bedrock.codec.v428.BedrockCodecHelper_v428;
import com.nukkitx.protocol.bedrock.data.definitions.ItemDefinition;
import com.nukkitx.protocol.bedrock.data.inventory.ContainerSlotType;
import com.nukkitx.protocol.bedrock.data.inventory.ItemData;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.CraftResultsDeprecatedAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestAction;
import com.nukkitx.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import com.nukkitx.protocol.bedrock.data.inventory.transaction.InventoryActionData;
import com.nukkitx.protocol.bedrock.data.inventory.transaction.InventorySource;
import com.nukkitx.protocol.common.util.TypeMap;
import com.nukkitx.protocol.common.util.VarInts;
import com.nukkitx.protocol.common.util.stream.LittleEndianByteBufInputStream;
import com.nukkitx.protocol.common.util.stream.LittleEndianByteBufOutputStream;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;

public class BedrockCodecHelper_v431 extends BedrockCodecHelper_v428 {
   public BedrockCodecHelper_v431(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerSlotType> containerSlotTypes) {
      super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes);
   }

   public ItemData readItemInstance(ByteBuf buffer) {
      int runtimeId = VarInts.readInt(buffer);
      if (runtimeId == 0) {
         return ItemData.AIR;
      } else {
         ItemDefinition definition = this.itemDefinitions.getDefinition(runtimeId);
         int count = buffer.readUnsignedShortLE();
         int damage = VarInts.readUnsignedInt(buffer);
         int blockRuntimeId = VarInts.readInt(buffer);
         NbtMap compoundTag = null;
         long blockingTicks = 0L;
         ByteBuf buf = buffer.readSlice(VarInts.readUnsignedInt(buffer));

         String[] canPlace;
         String[] canBreak;
         try {
            LittleEndianByteBufInputStream stream = new LittleEndianByteBufInputStream(buf);

            try {
               NBTInputStream nbtStream = new NBTInputStream(stream, (long)this.encodingSettings.maxItemNBTSize());

               try {
                  int nbtSize = stream.readShort();
                  if (nbtSize > 0) {
                     compoundTag = (NbtMap)nbtStream.readTag();
                  } else if (nbtSize == -1) {
                     int tagCount = stream.readUnsignedByte();
                     if (tagCount != 1) {
                        throw new IllegalArgumentException("Expected 1 tag but got " + tagCount);
                     }

                     compoundTag = (NbtMap)nbtStream.readTag();
                  }

                  canPlace = new String[stream.readInt()];

                  for(int i = 0; i < canPlace.length; ++i) {
                     canPlace[i] = stream.readUTF();
                  }

                  canBreak = new String[stream.readInt()];

                  for(int i = 0; i < canBreak.length; ++i) {
                     canBreak[i] = stream.readUTF();
                  }

                  if (definition != null && "minecraft:shield".equals(definition.getIdentifier())) {
                     blockingTicks = stream.readLong();
                  }
               } catch (Throwable var19) {
                  try {
                     nbtStream.close();
                  } catch (Throwable var18) {
                     var19.addSuppressed(var18);
                  }

                  throw var19;
               }

               nbtStream.close();
            } catch (Throwable var20) {
               try {
                  stream.close();
               } catch (Throwable var17) {
                  var20.addSuppressed(var17);
               }

               throw var20;
            }

            stream.close();
         } catch (IOException e) {
            throw new IllegalStateException("Unable to read item user data", e);
         }

         if (buf.isReadable()) {
            log.info("Item user data has {} readable bytes left", buf.readableBytes());
            if (log.isDebugEnabled()) {
               log.debug("Item data:\n{}", ByteBufUtil.prettyHexDump(buf.readerIndex(0)));
            }
         }

         return ItemData.builder().definition(definition).damage(damage).count(count).tag(compoundTag).canPlace(canPlace).canBreak(canBreak).blockingTicks(blockingTicks).blockDefinition(this.blockDefinitions.getDefinition(blockRuntimeId)).build();
      }
   }

   public ItemData readItem(ByteBuf buffer) {
      int runtimeId = VarInts.readInt(buffer);
      if (runtimeId == 0) {
         return ItemData.AIR;
      } else {
         ItemDefinition definition = this.itemDefinitions.getDefinition(runtimeId);
         int count = buffer.readUnsignedShortLE();
         int damage = VarInts.readUnsignedInt(buffer);
         boolean hasNetId = buffer.readBoolean();
         int netId = 0;
         if (hasNetId) {
            netId = VarInts.readInt(buffer);
         }

         int blockRuntimeId = VarInts.readInt(buffer);
         NbtMap compoundTag = null;
         long blockingTicks = 0L;
         ByteBuf buf = buffer.readSlice(VarInts.readUnsignedInt(buffer));

         String[] canPlace;
         String[] canBreak;
         try {
            LittleEndianByteBufInputStream stream = new LittleEndianByteBufInputStream(buf);

            try {
               NBTInputStream nbtStream = new NBTInputStream(stream, (long)this.encodingSettings.maxItemNBTSize());

               try {
                  int nbtSize = stream.readShort();
                  if (nbtSize > 0) {
                     compoundTag = (NbtMap)nbtStream.readTag();
                  } else if (nbtSize == -1) {
                     int tagCount = stream.readUnsignedByte();
                     if (tagCount != 1) {
                        throw new IllegalArgumentException("Expected 1 tag but got " + tagCount);
                     }

                     compoundTag = (NbtMap)nbtStream.readTag();
                  }

                  canPlace = new String[stream.readInt()];

                  for(int i = 0; i < canPlace.length; ++i) {
                     canPlace[i] = stream.readUTF();
                  }

                  canBreak = new String[stream.readInt()];

                  for(int i = 0; i < canBreak.length; ++i) {
                     canBreak[i] = stream.readUTF();
                  }

                  if (definition != null && "minecraft:shield".equals(definition.getIdentifier())) {
                     blockingTicks = stream.readLong();
                  }
               } catch (Throwable var21) {
                  try {
                     nbtStream.close();
                  } catch (Throwable var20) {
                     var21.addSuppressed(var20);
                  }

                  throw var21;
               }

               nbtStream.close();
            } catch (Throwable var22) {
               try {
                  stream.close();
               } catch (Throwable var19) {
                  var22.addSuppressed(var19);
               }

               throw var22;
            }

            stream.close();
         } catch (IOException e) {
            throw new IllegalStateException("Unable to read item user data", e);
         }

         if (buf.isReadable()) {
            log.info("Item user data has {} readable bytes left", buf.readableBytes());
            if (log.isDebugEnabled()) {
               log.debug("Item data:\n{}", ByteBufUtil.prettyHexDump(buf.readerIndex(0)));
            }
         }

         return ItemData.builder().definition(definition).damage(damage).count(count).tag(compoundTag).canPlace(canPlace).canBreak(canBreak).blockingTicks(blockingTicks).blockDefinition(this.blockDefinitions.getDefinition(blockRuntimeId)).usingNetId(hasNetId).netId(netId).build();
      }
   }

   public ItemData readNetItem(ByteBuf buffer) {
      return this.readItem(buffer);
   }

   public void writeItemInstance(ByteBuf buffer, ItemData item) {
      Objects.requireNonNull(item, "item is null!");
      ItemDefinition definition = item.getDefinition();
      if (isAir(definition)) {
         buffer.writeByte(0);
      } else {
         VarInts.writeInt(buffer, definition.getRuntimeId());
         buffer.writeShortLE(item.getCount());
         VarInts.writeUnsignedInt(buffer, item.getDamage());
         VarInts.writeInt(buffer, item.getBlockDefinition() == null ? 0 : item.getBlockDefinition().getRuntimeId());
         ByteBuf userDataBuf = ByteBufAllocator.DEFAULT.ioBuffer();

         try {
            LittleEndianByteBufOutputStream stream = new LittleEndianByteBufOutputStream(userDataBuf);

            try {
               NBTOutputStream nbtStream = new NBTOutputStream(stream);

               try {
                  if (item.getTag() != null) {
                     stream.writeShort(-1);
                     stream.writeByte(1);
                     nbtStream.writeTag(item.getTag());
                  } else {
                     userDataBuf.writeShortLE(0);
                  }

                  String[] canPlace = item.getCanPlace();
                  stream.writeInt(canPlace.length);

                  for(String aCanPlace : canPlace) {
                     stream.writeUTF(aCanPlace);
                  }

                  String[] canBreak = item.getCanBreak();
                  stream.writeInt(canBreak.length);

                  for(String aCanBreak : canBreak) {
                     stream.writeUTF(aCanBreak);
                  }

                  if ("minecraft:shield".equals(definition.getIdentifier())) {
                     stream.writeLong(item.getBlockingTicks());
                  }

                  VarInts.writeUnsignedInt(buffer, userDataBuf.readableBytes());
                  buffer.writeBytes(userDataBuf);
               } catch (Throwable var22) {
                  try {
                     nbtStream.close();
                  } catch (Throwable var21) {
                     var22.addSuppressed(var21);
                  }

                  throw var22;
               }

               nbtStream.close();
            } catch (Throwable var23) {
               try {
                  stream.close();
               } catch (Throwable var20) {
                  var23.addSuppressed(var20);
               }

               throw var23;
            }

            stream.close();
         } catch (IOException e) {
            throw new IllegalStateException("Unable to write item user data", e);
         } finally {
            userDataBuf.release();
         }

      }
   }

   public void writeItem(ByteBuf buffer, ItemData item) {
      Objects.requireNonNull(item, "item is null!");
      ItemDefinition definition = item.getDefinition();
      if (isAir(definition)) {
         buffer.writeByte(0);
      } else {
         VarInts.writeInt(buffer, definition.getRuntimeId());
         buffer.writeShortLE(item.getCount());
         VarInts.writeUnsignedInt(buffer, item.getDamage());
         buffer.writeBoolean(item.isUsingNetId());
         if (item.isUsingNetId()) {
            VarInts.writeInt(buffer, item.getNetId());
         }

         VarInts.writeInt(buffer, item.getBlockDefinition() == null ? 0 : item.getBlockDefinition().getRuntimeId());
         ByteBuf userDataBuf = ByteBufAllocator.DEFAULT.ioBuffer();

         try {
            LittleEndianByteBufOutputStream stream = new LittleEndianByteBufOutputStream(userDataBuf);

            try {
               NBTOutputStream nbtStream = new NBTOutputStream(stream);

               try {
                  if (item.getTag() != null) {
                     stream.writeShort(-1);
                     stream.writeByte(1);
                     nbtStream.writeTag(item.getTag());
                  } else {
                     userDataBuf.writeShortLE(0);
                  }

                  String[] canPlace = item.getCanPlace();
                  stream.writeInt(canPlace.length);

                  for(String aCanPlace : canPlace) {
                     stream.writeUTF(aCanPlace);
                  }

                  String[] canBreak = item.getCanBreak();
                  stream.writeInt(canBreak.length);

                  for(String aCanBreak : canBreak) {
                     stream.writeUTF(aCanBreak);
                  }

                  if ("minecraft:shield".equals(definition.getIdentifier())) {
                     stream.writeLong(item.getBlockingTicks());
                  }

                  VarInts.writeUnsignedInt(buffer, userDataBuf.readableBytes());
                  buffer.writeBytes(userDataBuf);
               } catch (Throwable var22) {
                  try {
                     nbtStream.close();
                  } catch (Throwable var21) {
                     var22.addSuppressed(var21);
                  }

                  throw var22;
               }

               nbtStream.close();
            } catch (Throwable var23) {
               try {
                  stream.close();
               } catch (Throwable var20) {
                  var23.addSuppressed(var20);
               }

               throw var23;
            }

            stream.close();
         } catch (IOException e) {
            throw new IllegalStateException("Unable to write item user data", e);
         } finally {
            userDataBuf.release();
         }

      }
   }

   public void writeNetItem(ByteBuf buffer, ItemData item) {
      this.writeItem(buffer, item);
   }

   public boolean readInventoryActions(ByteBuf buffer, List<InventoryActionData> actions) {
      this.readArray(buffer, actions, (buf, helper) -> {
         InventorySource source = this.readSource(buf);
         int slot = VarInts.readUnsignedInt(buf);
         ItemData fromItem = helper.readItem(buf);
         ItemData toItem = helper.readItem(buf);
         return new InventoryActionData(source, slot, fromItem, toItem);
      }, 64);
      return false;
   }

   public void writeInventoryActions(ByteBuf buffer, List<InventoryActionData> actions, boolean hasNetworkIds) {
      this.writeArray(buffer, actions, (buf, helper, action) -> {
         this.writeSource(buf, action.getSource());
         VarInts.writeUnsignedInt(buf, action.getSlot());
         helper.writeItem(buf, action.getFromItem());
         helper.writeItem(buf, action.getToItem());
      });
   }

   protected ItemStackRequestAction readRequestActionData(ByteBuf byteBuf, ItemStackRequestActionType type) {
      return (ItemStackRequestAction)(type == ItemStackRequestActionType.CRAFT_RESULTS_DEPRECATED ? new CraftResultsDeprecatedAction((ItemData[])this.readArray(byteBuf, new ItemData[0], this::readItemInstance), byteBuf.readUnsignedByte()) : super.readRequestActionData(byteBuf, type));
   }

   protected void writeRequestActionData(ByteBuf byteBuf, ItemStackRequestAction action) {
      if (action.getType() == ItemStackRequestActionType.CRAFT_RESULTS_DEPRECATED) {
         this.writeArray(byteBuf, ((CraftResultsDeprecatedAction)action).getResultItems(), this::writeItemInstance);
         byteBuf.writeByte(((CraftResultsDeprecatedAction)action).getTimesCrafted());
      } else {
         super.writeRequestActionData(byteBuf, action);
      }

   }
}
