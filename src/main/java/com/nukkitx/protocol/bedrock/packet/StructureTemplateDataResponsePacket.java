package com.nukkitx.protocol.bedrock.packet;

import com.nukkitx.protocol.bedrock.data.structure.StructureTemplateResponseType;
import com.nukkitx.protocol.common.PacketSignal;
import org.cloudburstmc.nbt.NbtMap;

public class StructureTemplateDataResponsePacket implements BedrockPacket {
   private String name;
   private boolean save;
   private NbtMap tag;
   private StructureTemplateResponseType type;

   public PacketSignal handle(BedrockPacketHandler handler) {
      return handler.handle(this);
   }

   public BedrockPacketType getPacketType() {
      return BedrockPacketType.STRUCTURE_TEMPLATE_DATA_EXPORT_RESPONSE;
   }

   public String getName() {
      return this.name;
   }

   public boolean isSave() {
      return this.save;
   }

   public NbtMap getTag() {
      return this.tag;
   }

   public StructureTemplateResponseType getType() {
      return this.type;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setSave(boolean save) {
      this.save = save;
   }

   public void setTag(NbtMap tag) {
      this.tag = tag;
   }

   public void setType(StructureTemplateResponseType type) {
      this.type = type;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof StructureTemplateDataResponsePacket)) {
         return false;
      } else {
         StructureTemplateDataResponsePacket other = (StructureTemplateDataResponsePacket)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.save != other.save) {
            return false;
         } else {
            Object this$name = this.name;
            Object other$name = other.name;
            if (this$name == null) {
               if (other$name != null) {
                  return false;
               }
            } else if (!this$name.equals(other$name)) {
               return false;
            }

            Object this$tag = this.tag;
            Object other$tag = other.tag;
            if (this$tag == null) {
               if (other$tag != null) {
                  return false;
               }
            } else if (!this$tag.equals(other$tag)) {
               return false;
            }

            Object this$type = this.type;
            Object other$type = other.type;
            if (this$type == null) {
               if (other$type != null) {
                  return false;
               }
            } else if (!this$type.equals(other$type)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof StructureTemplateDataResponsePacket;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.save ? 79 : 97);
      Object $name = this.name;
      result = result * 59 + ($name == null ? 43 : $name.hashCode());
      Object $tag = this.tag;
      result = result * 59 + ($tag == null ? 43 : $tag.hashCode());
      Object $type = this.type;
      result = result * 59 + ($type == null ? 43 : $type.hashCode());
      return result;
   }

   public String toString() {
      return "StructureTemplateDataResponsePacket(name=" + this.name + ", save=" + this.save + ", tag=" + this.tag + ", type=" + this.type + ")";
   }
}
