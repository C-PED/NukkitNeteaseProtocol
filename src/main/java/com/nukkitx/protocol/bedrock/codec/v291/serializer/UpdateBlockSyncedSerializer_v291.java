package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.BlockSyncType;
import com.nukkitx.protocol.bedrock.data.definitions.BlockDefinition;
import com.nukkitx.protocol.bedrock.packet.UpdateBlockPacket;
import com.nukkitx.protocol.bedrock.packet.UpdateBlockSyncedPacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.Set;

public class UpdateBlockSyncedSerializer_v291 implements BedrockPacketSerializer<UpdateBlockSyncedPacket> {
   public static final UpdateBlockSyncedSerializer_v291 INSTANCE = new UpdateBlockSyncedSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateBlockSyncedPacket packet) {
      helper.writeBlockPosition(buffer, packet.getBlockPosition());
      VarInts.writeUnsignedInt(buffer, packet.getDefinition().getRuntimeId());
      int flagValue = 0;

      for(UpdateBlockPacket.Flag flag : packet.getFlags()) {
         flagValue |= 1 << flag.ordinal();
      }

      VarInts.writeUnsignedInt(buffer, flagValue);
      VarInts.writeUnsignedInt(buffer, packet.getDataLayer());
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      VarInts.writeUnsignedLong(buffer, (long)packet.getEntityBlockSyncType().ordinal());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, UpdateBlockSyncedPacket packet) {
      packet.setBlockPosition(helper.readBlockPosition(buffer));
      packet.setDefinition((BlockDefinition)helper.getBlockDefinitions().getDefinition(VarInts.readUnsignedInt(buffer)));
      int flagValue = VarInts.readUnsignedInt(buffer);
      Set<UpdateBlockPacket.Flag> flags = packet.getFlags();

      for(UpdateBlockPacket.Flag flag : UpdateBlockPacket.Flag.values()) {
         if ((flagValue & 1 << flag.ordinal()) != 0) {
            flags.add(flag);
         }
      }

      packet.setDataLayer(VarInts.readUnsignedInt(buffer));
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setEntityBlockSyncType(BlockSyncType.values()[(int)VarInts.readUnsignedLong(buffer)]);
   }

   protected UpdateBlockSyncedSerializer_v291() {
   }
}
