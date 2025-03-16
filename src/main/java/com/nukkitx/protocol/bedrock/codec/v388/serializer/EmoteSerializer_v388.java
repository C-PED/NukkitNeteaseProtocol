package com.nukkitx.protocol.bedrock.codec.v388.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.data.EmoteFlag;
import com.nukkitx.protocol.bedrock.packet.EmotePacket;
import com.nukkitx.protocol.common.util.VarInts;
import io.netty.buffer.ByteBuf;
import java.util.Set;

public class EmoteSerializer_v388 implements BedrockPacketSerializer<EmotePacket> {
   public static final EmoteSerializer_v388 INSTANCE = new EmoteSerializer_v388();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EmotePacket packet) {
      VarInts.writeUnsignedLong(buffer, packet.getRuntimeEntityId());
      helper.writeString(buffer, packet.getEmoteId());
      this.writeFlags(buffer, helper, packet.getFlags());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EmotePacket packet) {
      packet.setRuntimeEntityId(VarInts.readUnsignedLong(buffer));
      packet.setEmoteId(helper.readString(buffer));
      this.readFlags(buffer, helper, packet.getFlags());
   }

   protected void writeFlags(ByteBuf buffer, BedrockCodecHelper helper, Set<EmoteFlag> flags) {
      int flagsData = 0;

      for(EmoteFlag flag : flags) {
         flagsData |= 1 << flag.ordinal();
      }

      buffer.writeByte(flagsData);
   }

   protected void readFlags(ByteBuf buffer, BedrockCodecHelper helper, Set<EmoteFlag> flags) {
      int flagsData = buffer.readUnsignedByte();

      for(EmoteFlag flag : EmoteFlag.values()) {
         if (((long)flagsData & 1L << flag.ordinal()) != 0L) {
            flags.add(flag);
         }
      }

   }

   protected EmoteSerializer_v388() {
   }
}
