package com.neteasemc.protocol.custom.packet.skinConfirmPacket;

import com.neteasemc.protocol.custom.GeyserPacketSerializer;
import com.nukkitx.network.VarInts;
import com.nukkitx.network.util.Preconditions;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class SkinConfirmPacketSerializer implements GeyserPacketSerializer<SkinConfirmPacket> {
   public static final SkinConfirmPacketSerializer INSTANCE = new SkinConfirmPacketSerializer();

   private void writeString(ByteBuf buffer, String string) {
      Preconditions.checkNotNull(buffer, "buffer");
      Preconditions.checkNotNull(string, "string");
      this.writeByteArray(buffer, string.getBytes(StandardCharsets.UTF_8));
   }

   public void writeByteArray(ByteBuf buffer, byte[] bytes) {
      Preconditions.checkNotNull(buffer, "buffer");
      Preconditions.checkNotNull(bytes, "bytes");
      VarInts.writeUnsignedInt(buffer, bytes.length);
      buffer.writeBytes(bytes);
   }

   public String readString(ByteBuf buffer) {
      Preconditions.checkNotNull(buffer, "buffer");
      return new String(this.readByteArray(buffer), StandardCharsets.UTF_8);
   }

   public byte[] readByteArray(ByteBuf buffer) {
      Preconditions.checkNotNull(buffer, "buffer");
      int length = VarInts.readUnsignedInt(buffer);
      Preconditions.checkArgument(buffer.isReadable(length), "Tried to read %s bytes but only has %s readable", length, buffer.readableBytes());
      byte[] bytes = new byte[length];
      buffer.readBytes(bytes);
      return bytes;
   }

   public void writeUuid(ByteBuf buffer, UUID uuid) {
      Preconditions.checkNotNull(buffer, "buffer");
      Preconditions.checkNotNull(uuid, "uuid");
      buffer.writeLongLE(uuid.getMostSignificantBits());
      buffer.writeLongLE(uuid.getLeastSignificantBits());
   }

   public UUID readUuid(ByteBuf buffer) {
      Preconditions.checkNotNull(buffer, "buffer");
      return new UUID(buffer.readLongLE(), buffer.readLongLE());
   }

   public void serialize(ByteBuf buffer, SkinConfirmPacket packet) {
      this.writeUuid(buffer, packet.getUuid());
      this.writeUuid(buffer, packet.getJavaUuid());
      this.writeString(buffer, packet.getUserName());
      this.writeString(buffer, packet.getGeoStr());
      this.writeString(buffer, packet.getUidStr());
      this.writeString(buffer, packet.getPlayFabIdStr());
      this.writeString(buffer, packet.getSkinResourcePatch());
      this.writeString(buffer, packet.getFulllSkinId());
      this.writeString(buffer, packet.getXuid());
      this.writeString(buffer, packet.getCapeIdStr());
      this.writeString(buffer, packet.getCapeData());
      buffer.writeInt(packet.getCapeWidth());
      buffer.writeInt(packet.getCapeHeight());
      this.writeString(buffer, packet.getSkinData());
      buffer.writeInt(packet.getSkinWidth());
      buffer.writeInt(packet.getSkinHeight());
      this.writeString(buffer, packet.getNeteaseUid());
      buffer.writeBoolean(packet.isOther());
   }

   public void deserialize(ByteBuf buffer, SkinConfirmPacket packet) {
      UUID uuid = this.readUuid(buffer);
      UUID javaUUid = this.readUuid(buffer);
      String userName = this.readString(buffer);
      String geoStr = this.readString(buffer);
      String uidStr = this.readString(buffer);
      String playFabId = this.readString(buffer);
      String skinResPatch = this.readString(buffer);
      String fullSkinId = this.readString(buffer);
      String xuid = this.readString(buffer);
      String capeIdStr = this.readString(buffer);
      String capeData = this.readString(buffer);
      int capeWidth = buffer.readInt();
      int capeHeight = buffer.readInt();
      String skinData = this.readString(buffer);
      int skinWidth = buffer.readInt();
      int skinHeight = buffer.readInt();
      String neteaseUid = this.readString(buffer);
      boolean isOther = buffer.readBoolean();
      packet.setUuid(uuid);
      packet.setJavaUuid(javaUUid);
      packet.setUserName(userName);
      packet.setGeoStr(geoStr);
      packet.setUidStr(uidStr);
      packet.setPlayFabIdStr(playFabId);
      packet.setSkinResourcePatch(skinResPatch);
      packet.setFulllSkinId(fullSkinId);
      packet.setXuid(xuid);
      packet.setCapeIdStr(capeIdStr);
      packet.setCapeData(capeData);
      packet.setCapeWidth(capeWidth);
      packet.setCapeHeight(capeHeight);
      packet.setSkinData(skinData);
      packet.setSkinWidth(skinWidth);
      packet.setSkinHeight(skinHeight);
      packet.setNeteaseUid(neteaseUid);
      packet.setOther(isOther);
   }
}
