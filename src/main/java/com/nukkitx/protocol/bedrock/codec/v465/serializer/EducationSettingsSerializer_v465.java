package com.nukkitx.protocol.bedrock.codec.v465.serializer;

import com.nukkitx.protocol.bedrock.codec.BedrockCodecHelper;
import com.nukkitx.protocol.bedrock.codec.v407.serializer.EducationSettingsSerializer_v407;
import com.nukkitx.protocol.bedrock.packet.EducationSettingsPacket;
import com.nukkitx.protocol.common.util.OptionalBoolean;
import io.netty.buffer.ByteBuf;
import java.util.Optional;

public class EducationSettingsSerializer_v465 extends EducationSettingsSerializer_v407 {
   public static final EducationSettingsSerializer_v465 INSTANCE = new EducationSettingsSerializer_v465();

   public void serialize(ByteBuf buffer, BedrockCodecHelper helper, EducationSettingsPacket packet) {
      helper.writeString(buffer, packet.getCodeBuilderUri());
      helper.writeString(buffer, packet.getCodeBuilderTitle());
      buffer.writeBoolean(packet.isCanResizeCodeBuilder());
      buffer.writeBoolean(packet.isDisableLegacyTitle());
      helper.writeString(buffer, packet.getPostProcessFilter());
      helper.writeString(buffer, packet.getScreenshotBorderPath());
      helper.writeOptional(buffer, OptionalBoolean::isPresent, packet.getEntityCapabilities(), (byteBuf, optional) -> byteBuf.writeBoolean(optional.getAsBoolean()));
      helper.writeOptional(buffer, Optional::isPresent, packet.getOverrideUri(), (byteBuf, optional) -> helper.writeString(byteBuf, (String)optional.get()));
      buffer.writeBoolean(packet.isQuizAttached());
      helper.writeOptional(buffer, OptionalBoolean::isPresent, packet.getExternalLinkSettings(), (byteBuf, optional) -> byteBuf.writeBoolean(optional.getAsBoolean()));
   }

   public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, EducationSettingsPacket packet) {
      packet.setCodeBuilderUri(helper.readString(buffer));
      packet.setCodeBuilderTitle(helper.readString(buffer));
      packet.setCanResizeCodeBuilder(buffer.readBoolean());
      packet.setDisableLegacyTitle(buffer.readBoolean());
      packet.setPostProcessFilter(helper.readString(buffer));
      packet.setScreenshotBorderPath(helper.readString(buffer));
      packet.setEntityCapabilities((OptionalBoolean)helper.readOptional(buffer, OptionalBoolean.empty(), (byteBuf) -> OptionalBoolean.of(buffer.readBoolean())));
      packet.setOverrideUri((Optional)helper.readOptional(buffer, Optional.empty(), (byteBuf) -> Optional.of(helper.readString(byteBuf))));
      packet.setQuizAttached(buffer.readBoolean());
      packet.setExternalLinkSettings((OptionalBoolean)helper.readOptional(buffer, OptionalBoolean.empty(), (byteBuf) -> OptionalBoolean.of(buffer.readBoolean())));
   }

   protected EducationSettingsSerializer_v465() {
   }
}
