package com.nukkitx.protocol.bedrock.codec.v422.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v332.serializer.ResourcePacksInfoSerializer_v332;
import com.nukkitx.protocol.bedrock.packet.ResourcePacksInfoPacket;
import io.netty.buffer.ByteBuf;

public class ResourcePacksInfoSerializer_v422 extends ResourcePacksInfoSerializer_v332 {
   public static final ResourcePacksInfoSerializer_v422 INSTANCE = new ResourcePacksInfoSerializer_v422();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
      buffer.writeBoolean(packet.isForcedToAccept());
      buffer.writeBoolean(packet.isScriptingEnabled());
      this.writePacks(buffer, packet.getBehaviorPackInfos(), helper, false);
      this.writePacks(buffer, packet.getResourcePackInfos(), helper, true);
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket packet) {
      packet.setForcedToAccept(buffer.readBoolean());
      packet.setScriptingEnabled(buffer.readBoolean());
      this.readPacks(buffer, packet.getBehaviorPackInfos(), helper, false);
      this.readPacks(buffer, packet.getResourcePackInfos(), helper, true);
   }

   public void writeEntry(ByteBuf buffer, BedrockCodecHelper helper, ResourcePacksInfoPacket.Entry entry, boolean resource) {
      super.writeEntry(buffer, helper, entry, resource);
      if (resource) {
         buffer.writeBoolean(entry.isRaytracingCapable());
      }

   }

   public ResourcePacksInfoPacket.Entry readEntry(ByteBuf buffer, BedrockCodecHelper helper, boolean resource) {
      String packId = helper.readString(buffer);
      String packVersion = helper.readString(buffer);
      long packSize = buffer.readLongLE();
      String contentKey = helper.readString(buffer);
      String subPackName = helper.readString(buffer);
      String contentId = helper.readString(buffer);
      boolean isScripting = buffer.readBoolean();
      boolean raytracingCapable = resource && buffer.readBoolean();
      return new ResourcePacksInfoPacket.Entry(packId, packVersion, packSize, contentKey, subPackName, contentId, isScripting, raytracingCapable);
   }

   protected ResourcePacksInfoSerializer_v422() {
   }
}
