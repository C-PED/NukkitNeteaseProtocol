package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.BookEditPacket;
import com.nukkitx.protocol.common.util.Int2ObjectBiMap;
import io.netty.buffer.ByteBuf;

public class BookEditSerializer_v291 implements BedrockPacketSerializer<BookEditPacket> {
   public static final BookEditSerializer_v291 INSTANCE = new BookEditSerializer_v291();
   private static final Int2ObjectBiMap<BookEditPacket.Action> types = new Int2ObjectBiMap<BookEditPacket.Action>();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
      buffer.writeByte(packet.getAction().ordinal());
      buffer.writeByte(packet.getInventorySlot());
      switch (packet.getAction()) {
         case REPLACE_PAGE:
         case ADD_PAGE:
            buffer.writeByte(packet.getPageNumber());
            helper.writeString(buffer, packet.getText());
            helper.writeString(buffer, packet.getPhotoName());
            break;
         case DELETE_PAGE:
            buffer.writeByte(packet.getPageNumber());
            break;
         case SWAP_PAGES:
            buffer.writeByte(packet.getPageNumber());
            buffer.writeByte(packet.getSecondaryPageNumber());
            break;
         case SIGN_BOOK:
            helper.writeString(buffer, packet.getTitle());
            helper.writeString(buffer, packet.getAuthor());
            helper.writeString(buffer, packet.getXuid());
      }

   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, BookEditPacket packet) {
      packet.setAction(types.get(buffer.readUnsignedByte()));
      packet.setInventorySlot(buffer.readUnsignedByte());
      switch (packet.getAction()) {
         case REPLACE_PAGE:
         case ADD_PAGE:
            packet.setPageNumber(buffer.readUnsignedByte());
            packet.setText(helper.readString(buffer));
            packet.setPhotoName(helper.readString(buffer));
            break;
         case DELETE_PAGE:
            packet.setPageNumber(buffer.readUnsignedByte());
            break;
         case SWAP_PAGES:
            packet.setPageNumber(buffer.readUnsignedByte());
            packet.setSecondaryPageNumber(buffer.readUnsignedByte());
            break;
         case SIGN_BOOK:
            packet.setTitle(helper.readString(buffer));
            packet.setAuthor(helper.readString(buffer));
            packet.setXuid(helper.readString(buffer));
      }

   }

   protected BookEditSerializer_v291() {
   }

   static {
      types.put(0, BookEditPacket.Action.REPLACE_PAGE);
      types.put(1, BookEditPacket.Action.ADD_PAGE);
      types.put(2, BookEditPacket.Action.DELETE_PAGE);
      types.put(3, BookEditPacket.Action.SWAP_PAGES);
      types.put(4, BookEditPacket.Action.SIGN_BOOK);
   }
}
