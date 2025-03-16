package com.nukkitx.protocol.bedrock.data;

public final class EduSharedUriResource {
   public static final EduSharedUriResource EMPTY = new EduSharedUriResource("", "");
   private final String buttonName;
   private final String linkUri;

   public EduSharedUriResource(String buttonName, String linkUri) {
      this.buttonName = buttonName;
      this.linkUri = linkUri;
   }

   public String getButtonName() {
      return this.buttonName;
   }

   public String getLinkUri() {
      return this.linkUri;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EduSharedUriResource)) {
         return false;
      } else {
         EduSharedUriResource other = (EduSharedUriResource)o;
         Object this$buttonName = this.getButtonName();
         Object other$buttonName = other.getButtonName();
         if (this$buttonName == null) {
            if (other$buttonName != null) {
               return false;
            }
         } else if (!this$buttonName.equals(other$buttonName)) {
            return false;
         }

         Object this$linkUri = this.getLinkUri();
         Object other$linkUri = other.getLinkUri();
         if (this$linkUri == null) {
            if (other$linkUri != null) {
               return false;
            }
         } else if (!this$linkUri.equals(other$linkUri)) {
            return false;
         }

         return true;
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $buttonName = this.getButtonName();
      result = result * 59 + ($buttonName == null ? 43 : $buttonName.hashCode());
      Object $linkUri = this.getLinkUri();
      result = result * 59 + ($linkUri == null ? 43 : $linkUri.hashCode());
      return result;
   }

   public String toString() {
      return "EduSharedUriResource(buttonName=" + this.getButtonName() + ", linkUri=" + this.getLinkUri() + ")";
   }
}
