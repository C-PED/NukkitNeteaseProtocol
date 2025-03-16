package com.nukkitx.protocol.bedrock.codec.v291.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.TextPacket;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.Objects;

public class TextSerializer_v291 implements BedrockPacketSerializer<TextPacket> {
   public static final TextSerializer_v291 INSTANCE = new TextSerializer_v291();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
      TextPacket.Type type = packet.getType();
      buffer.writeByte(type.ordinal());
      buffer.writeBoolean(packet.isNeedsTranslation());
      switch (type) {
         case CHAT:
         case WHISPER:
         case ANNOUNCEMENT:
            helper.writeString(buffer, packet.getSourceName());
         case RAW:
         case TIP:
         case SYSTEM:
            helper.writeString(buffer, packet.getMessage());
            break;
         case TRANSLATION:
         case POPUP:
         case JUKEBOX_POPUP:
            helper.writeString(buffer, packet.getMessage());
            List var10002 = packet.getParameters();
            Objects.requireNonNull(helper);
            helper.writeArray(buffer, var10002, helper::writeString);
            break;
         default:
            throw new UnsupportedOperationException("Unsupported TextType " + type);
      }

      helper.writeString(buffer, packet.getXuid());
      helper.writeString(buffer, packet.getPlatformChatId());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
      TextPacket.Type type = TextPacket.Type.values()[buffer.readUnsignedByte()];
      packet.setType(type);
      packet.setNeedsTranslation(buffer.readBoolean());
      switch (type) {
         case CHAT:
         case WHISPER:
         case ANNOUNCEMENT:
            packet.setSourceName(helper.readString(buffer));
         case RAW:
         case TIP:
         case SYSTEM:
            packet.setMessage(helper.readString(buffer));
            break;
         case TRANSLATION:
         case POPUP:
         case JUKEBOX_POPUP:
            packet.setMessage(helper.readString(buffer));
            List var10002 = packet.getParameters();
            Objects.requireNonNull(helper);
            helper.readArray(buffer, var10002, helper::readString);
            break;
         default:
            throw new UnsupportedOperationException("Unsupported TextType " + type);
      }

      packet.setXuid(helper.readString(buffer));
      packet.setPlatformChatId(helper.readString(buffer));
   }

   protected TextSerializer_v291() {
   }
}
