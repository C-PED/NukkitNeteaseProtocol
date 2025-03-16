package com.nukkitx.protocol.bedrock.codec.v422.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.BedrockPacketSerializer;
import com.nukkitx.protocol.bedrock.packet.FilterTextPacket;
import io.netty.buffer.ByteBuf;

public class FilterTextSerializer_v422 implements BedrockPacketSerializer<FilterTextPacket> {
   public static final FilterTextSerializer_v422 INSTANCE = new FilterTextSerializer_v422();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, FilterTextPacket packet) {
      helper.writeString(buffer, packet.getText());
      buffer.writeBoolean(packet.isFromServer());
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, FilterTextPacket packet) {
      packet.setText(helper.readString(buffer));
      packet.setFromServer(buffer.readBoolean());
   }

   protected FilterTextSerializer_v422() {
   }
}
