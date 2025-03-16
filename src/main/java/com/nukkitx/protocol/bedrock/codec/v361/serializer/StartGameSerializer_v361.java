package com.nukkitx.protocol.bedrock.codec.v361.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.StartGameSerializer_v332;
import com.nukkitx.protocol.bedrock.data.GameType;
import com.nukkitx.protocol.bedrock.data.definitions.SimpleItemDefinition;
import com.nukkitx.protocol.bedrock.packet.StartGamePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import org.cloudburstmc.nbt.NbtList;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

public class StartGameSerializer_v361 extends StartGameSerializer_v332 {
   public static final StartGameSerializer_v361 INSTANCE = new StartGameSerializer_v361();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      VarInts.writeLong(buffer, packet.getUniqueEntityId());
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      VarInts.writeInt(buffer, packet.getPlayerGameType().ordinal());
      helper.writeVector3f(buffer, packet.getPlayerPosition());
      helper.writeVector2f(buffer, packet.getRotation());
      this.writeLevelSettings(buffer, helper, packet);
      helper.writeString(buffer, packet.getLevelId());
      helper.writeString(buffer, packet.getLevelName());
      helper.writeString(buffer, packet.getPremiumWorldTemplateId());
      buffer.writeBoolean(packet.isTrial());
      buffer.writeLongLE(packet.getCurrentTick());
      VarInts.writeInt(buffer, packet.getEnchantmentSeed());
      List<NbtMap> palette = packet.getBlockPalette();
      VarInts.writeUnsignedInt(buffer, palette.size());

      for(NbtMap entry : palette) {
         NbtMap blockTag = entry.getCompound("block");
         helper.writeString(buffer, blockTag.getString("name"));
         buffer.writeShortLE(entry.getShort("meta"));
         buffer.writeShortLE(entry.getShort("id"));
      }

      helper.writeArray(buffer, packet.getItemDefinitions(), (buf, entry) -> {
         helper.writeString(buf, entry.getIdentifier());
         buf.writeShortLE(entry.getRuntimeId());
      });
      helper.writeString(buffer, packet.getMultiplayerCorrelationId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      packet.setUniqueEntityId(VarInts.readLong(buffer));
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setPlayerGameType(GameType.from(VarInts.readInt(buffer)));
      packet.setPlayerPosition(helper.readVector3f(buffer));
      packet.setRotation(helper.readVector2f(buffer));
      this.readLevelSettings(buffer, helper, packet);
      packet.setLevelId(helper.readString(buffer));
      packet.setLevelName(helper.readString(buffer));
      packet.setPremiumWorldTemplateId(helper.readString(buffer));
      packet.setTrial(buffer.readBoolean());
      packet.setCurrentTick(buffer.readLongLE());
      packet.setEnchantmentSeed(VarInts.readInt(buffer));
      int paletteLength = VarInts.readUnsignedInt(buffer);
      List<NbtMap> palette = new ObjectArrayList(paletteLength);

      for(int i = 0; i < paletteLength; ++i) {
         palette.add(NbtMap.builder().putCompound("block", NbtMap.builder().putString("name", helper.readString(buffer)).build()).putShort("meta", buffer.readShortLE()).putShort("id", buffer.readShortLE()).build());
      }

      packet.setBlockPalette(new NbtList(NbtType.COMPOUND, palette));
      helper.readArray(buffer, packet.getItemDefinitions(), (buf, packetHelper) -> {
         String identifier = packetHelper.readString(buf);
         short id = buf.readShortLE();
         return new SimpleItemDefinition(identifier, id, false);
      });
      packet.setMultiplayerCorrelationId(helper.readString(buffer));
   }

   protected void readLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      super.readLevelSettings(buffer, helper, packet);
      packet.setOnlySpawningV1Villagers(buffer.readBoolean());
   }

   protected void writeLevelSettings(ByteBuf buffer, BedrockCodecHelper helper, StartGamePacket packet) {
      super.writeLevelSettings(buffer, helper, packet);
      buffer.writeBoolean(packet.isOnlySpawningV1Villagers());
   }

   protected StartGameSerializer_v361() {
   }
}
