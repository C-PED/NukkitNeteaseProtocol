package com.nukkitx.protocol.bedrock.data.event;

public final class PatternRemovedEventData implements EventData {
   private final int itemId;
   private final int auxValue;
   private final int patternsSize;
   private final int patternIndex;
   private final int patternColor;

   public EventDataType getType() {
      return EventDataType.PATTERN_REMOVED;
   }

   public PatternRemovedEventData(int itemId, int auxValue, int patternsSize, int patternIndex, int patternColor) {
      this.itemId = itemId;
      this.auxValue = auxValue;
      this.patternsSize = patternsSize;
      this.patternIndex = patternIndex;
      this.patternColor = patternColor;
   }

   public int getItemId() {
      return this.itemId;
   }

   public int getAuxValue() {
      return this.auxValue;
   }

   public int getPatternsSize() {
      return this.patternsSize;
   }

   public int getPatternIndex() {
      return this.patternIndex;
   }

   public int getPatternColor() {
      return this.patternColor;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PatternRemovedEventData)) {
         return false;
      } else {
         PatternRemovedEventData other = (PatternRemovedEventData)o;
         if (this.getItemId() != other.getItemId()) {
            return false;
         } else if (this.getAuxValue() != other.getAuxValue()) {
            return false;
         } else if (this.getPatternsSize() != other.getPatternsSize()) {
            return false;
         } else if (this.getPatternIndex() != other.getPatternIndex()) {
            return false;
         } else {
            return this.getPatternColor() == other.getPatternColor();
         }
      }
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + this.getItemId();
      result = result * 59 + this.getAuxValue();
      result = result * 59 + this.getPatternsSize();
      result = result * 59 + this.getPatternIndex();
      result = result * 59 + this.getPatternColor();
      return result;
   }

   public String toString() {
      return "PatternRemovedEventData(itemId=" + this.getItemId() + ", auxValue=" + this.getAuxValue() + ", patternsSize=" + this.getPatternsSize() + ", patternIndex=" + this.getPatternIndex() + ", patternColor=" + this.getPatternColor() + ")";
   }
}
