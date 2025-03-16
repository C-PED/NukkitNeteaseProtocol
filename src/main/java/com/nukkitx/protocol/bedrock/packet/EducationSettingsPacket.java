package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.common.PacketSignal;
import com.nukkitx.protocol.common.util.OptionalBoolean;
import java.util.Optional;

public class EducationSettingsPacket implements BedrockPacket {
   private String codeBuilderUri;
   private String codeBuilderTitle;
   private boolean canResizeCodeBuilder;
   private boolean disableLegacyTitle;
   private String postProcessFilter;
   private String screenshotBorderPath;
   private OptionalBoolean entityCapabilities;
   private Optional<String> overrideUri;
   private boolean quizAttached;
   private OptionalBoolean externalLinkSettings;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.EDUCATION_SETTINGS;
   }

   public String getCodeBuilderUri() {
      return this.codeBuilderUri;
   }

   public String getCodeBuilderTitle() {
      return this.codeBuilderTitle;
   }

   public boolean isCanResizeCodeBuilder() {
      return this.canResizeCodeBuilder;
   }

   public boolean isDisableLegacyTitle() {
      return this.disableLegacyTitle;
   }

   public String getPostProcessFilter() {
      return this.postProcessFilter;
   }

   public String getScreenshotBorderPath() {
      return this.screenshotBorderPath;
   }

   public OptionalBoolean getEntityCapabilities() {
      return this.entityCapabilities;
   }

   public Optional<String> getOverrideUri() {
      return this.overrideUri;
   }

   public boolean isQuizAttached() {
      return this.quizAttached;
   }

   public OptionalBoolean getExternalLinkSettings() {
      return this.externalLinkSettings;
   }

   public void setCodeBuilderUri(String codeBuilderUri) {
      this.codeBuilderUri = codeBuilderUri;
   }

   public void setCodeBuilderTitle(String codeBuilderTitle) {
      this.codeBuilderTitle = codeBuilderTitle;
   }

   public void setCanResizeCodeBuilder(boolean canResizeCodeBuilder) {
      this.canResizeCodeBuilder = canResizeCodeBuilder;
   }

   public void setDisableLegacyTitle(boolean disableLegacyTitle) {
      this.disableLegacyTitle = disableLegacyTitle;
   }

   public void setPostProcessFilter(String postProcessFilter) {
      this.postProcessFilter = postProcessFilter;
   }

   public void setScreenshotBorderPath(String screenshotBorderPath) {
      this.screenshotBorderPath = screenshotBorderPath;
   }

   public void setEntityCapabilities(OptionalBoolean entityCapabilities) {
      this.entityCapabilities = entityCapabilities;
   }

   public void setOverrideUri(Optional<String> overrideUri) {
      this.overrideUri = overrideUri;
   }

   public void setQuizAttached(boolean quizAttached) {
      this.quizAttached = quizAttached;
   }

   public void setExternalLinkSettings(OptionalBoolean externalLinkSettings) {
      this.externalLinkSettings = externalLinkSettings;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EducationSettingsPacket)) {
         return false;
      } else {
         EducationSettingsPacket other = (EducationSettingsPacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.canResizeCodeBuilder != other.canResizeCodeBuilder) {
            return false;
         } else if (this.disableLegacyTitle != other.disableLegacyTitle) {
            return false;
         } else if (this.quizAttached != other.quizAttached) {
            return false;
         } else {
            Object this$codeBuilderUri = this.codeBuilderUri;
            Object other$codeBuilderUri = other.codeBuilderUri;
            if (this$codeBuilderUri == null) {
               if (other$codeBuilderUri != null) {
                  return false;
               }
            } else if (!this$codeBuilderUri.equals(other$codeBuilderUri)) {
               return false;
            }

            Object this$codeBuilderTitle = this.codeBuilderTitle;
            Object other$codeBuilderTitle = other.codeBuilderTitle;
            if (this$codeBuilderTitle == null) {
               if (other$codeBuilderTitle != null) {
                  return false;
               }
            } else if (!this$codeBuilderTitle.equals(other$codeBuilderTitle)) {
               return false;
            }

            Object this$postProcessFilter = this.postProcessFilter;
            Object other$postProcessFilter = other.postProcessFilter;
            if (this$postProcessFilter == null) {
               if (other$postProcessFilter != null) {
                  return false;
               }
            } else if (!this$postProcessFilter.equals(other$postProcessFilter)) {
               return false;
            }

            Object this$screenshotBorderPath = this.screenshotBorderPath;
            Object other$screenshotBorderPath = other.screenshotBorderPath;
            if (this$screenshotBorderPath == null) {
               if (other$screenshotBorderPath != null) {
                  return false;
               }
            } else if (!this$screenshotBorderPath.equals(other$screenshotBorderPath)) {
               return false;
            }

            Object this$entityCapabilities = this.entityCapabilities;
            Object other$entityCapabilities = other.entityCapabilities;
            if (this$entityCapabilities == null) {
               if (other$entityCapabilities != null) {
                  return false;
               }
            } else if (!this$entityCapabilities.equals(other$entityCapabilities)) {
               return false;
            }

            Object this$overrideUri = this.overrideUri;
            Object other$overrideUri = other.overrideUri;
            if (this$overrideUri == null) {
               if (other$overrideUri != null) {
                  return false;
               }
            } else if (!this$overrideUri.equals(other$overrideUri)) {
               return false;
            }

            Object this$externalLinkSettings = this.externalLinkSettings;
            Object other$externalLinkSettings = other.externalLinkSettings;
            if (this$externalLinkSettings == null) {
               if (other$externalLinkSettings != null) {
                  return false;
               }
            } else if (!this$externalLinkSettings.equals(other$externalLinkSettings)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EducationSettingsPacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.canResizeCodeBuilder ? 79 : 97);
      result = result * 59 + (this.disableLegacyTitle ? 79 : 97);
      result = result * 59 + (this.quizAttached ? 79 : 97);
      Object $codeBuilderUri = this.codeBuilderUri;
      result = result * 59 + ($codeBuilderUri == null ? 43 : $codeBuilderUri.hashCode());
      Object $codeBuilderTitle = this.codeBuilderTitle;
      result = result * 59 + ($codeBuilderTitle == null ? 43 : $codeBuilderTitle.hashCode());
      Object $postProcessFilter = this.postProcessFilter;
      result = result * 59 + ($postProcessFilter == null ? 43 : $postProcessFilter.hashCode());
      Object $screenshotBorderPath = this.screenshotBorderPath;
      result = result * 59 + ($screenshotBorderPath == null ? 43 : $screenshotBorderPath.hashCode());
      Object $entityCapabilities = this.entityCapabilities;
      result = result * 59 + ($entityCapabilities == null ? 43 : $entityCapabilities.hashCode());
      Object $overrideUri = this.overrideUri;
      result = result * 59 + ($overrideUri == null ? 43 : $overrideUri.hashCode());
      Object $externalLinkSettings = this.externalLinkSettings;
      result = result * 59 + ($externalLinkSettings == null ? 43 : $externalLinkSettings.hashCode());
      return result;
   }

   public String toString() {
      return "EducationSettingsPacket(codeBuilderUri=" + this.codeBuilderUri + ", codeBuilderTitle=" + this.codeBuilderTitle + ", canResizeCodeBuilder=" + this.canResizeCodeBuilder + ", disableLegacyTitle=" + this.disableLegacyTitle + ", postProcessFilter=" + this.postProcessFilter + ", screenshotBorderPath=" + this.screenshotBorderPath + ", entityCapabilities=" + this.entityCapabilities + ", overrideUri=" + this.overrideUri + ", quizAttached=" + this.quizAttached + ", externalLinkSettings=" + this.externalLinkSettings + ")";
   }
}
