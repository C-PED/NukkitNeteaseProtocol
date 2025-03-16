package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.ResourcePackStackPacket;
import io.netty.buffer.ByteBuf;
import java.util.Objects;

public class ResourcePackStackSerializer_v291 implements BedrockPacketSerializer<ResourcePackStackPacket> {
   public static final ResourcePackStackSerializer_v291 INSTANCE = new ResourcePackStackSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
      buffer.writeBoolean(packet.isForcedToAccept());
      helper.writeArray(buffer, packet.getBehaviorPacks(), this::writeEntry);
      helper.writeArray(buffer, packet.getResourcePacks(), this::writeEntry);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket packet) {
      packet.setForcedToAccept(buffer.readBoolean());
      helper.readArray(buffer, packet.getBehaviorPacks(), this::readEntry);
      helper.readArray(buffer, packet.getResourcePacks(), this::readEntry);
   }

   public ResourcePackStackPacket.Entry readEntry(ByteBuf buffer, BedrockCodecHelper helper) {
      String packId = helper.readString(buffer);
      String packVersion = helper.readString(buffer);
      String subPackName = helper.readString(buffer);
      return new ResourcePackStackPacket.Entry(packId, packVersion, subPackName);
   }

   public void writeEntry(ByteBuf buffer, BedrockCodecHelper helper, ResourcePackStackPacket.Entry entry) {
      Objects.requireNonNull(entry, "ResourcePackStackPacket entry is null");
      helper.writeString(buffer, entry.getPackId());
      helper.writeString(buffer, entry.getPackVersion());
      helper.writeString(buffer, entry.getSubPackName());
   }

   protected ResourcePackStackSerializer_v291() {
   }
}
