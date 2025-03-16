package com.nukkitx.protocol.bedrock.data.inventory;

import com.nukkitx.protocol.bedrock.data.definitions.BlockDefinition;
import com.nukkitx.protocol.bedrock.data.definitions.ItemDefinition;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.cloudburstmc.nbt.NbtMap;

public interface ItemData {
   ItemData AIR = new BaseItemData(ItemDefinition.AIR, 0, 0, (NbtMap)null, BaseItemData.EMPTY_ARRAY, BaseItemData.EMPTY_ARRAY, 0L, (BlockDefinition)null, false, 0);

   @NonNull ItemDefinition getDefinition();

   int getDamage();

   int getCount();

   @Nullable NbtMap getTag();

   String[] getCanPlace();

   String[] getCanBreak();

   long getBlockingTicks();

   @Nullable BlockDefinition getBlockDefinition();

   boolean isUsingNetId();

   int getNetId();

   void setNetId(int var1);

   boolean isValid();

   boolean isNull();

   boolean equals(ItemData var1, boolean var2, boolean var3, boolean var4);

   default Builder toBuilder() {
      return new Builder(this);
   }

   static Builder builder() {
      return new Builder();
   }

   public static class Builder {
      private ItemDefinition definition;
      private int damage;
      private int count;
      private NbtMap tag;
      private String[] canPlace;
      private String[] canBreak;
      private long blockingTicks;
      private BlockDefinition blockDefinition;
      private boolean usingNetId;
      private int netId;

      private Builder() {
      }

      private Builder(ItemData data) {
         this.definition = data.getDefinition();
         this.damage = data.getDamage();
         this.count = data.getCount();
         this.tag = data.getTag();
         this.canPlace = data.getCanPlace();
         this.canBreak = data.getCanBreak();
         this.blockingTicks = data.getBlockingTicks();
         this.blockDefinition = data.getBlockDefinition();
         this.usingNetId = data.isUsingNetId();
         this.netId = data.getNetId();
      }

      public Builder definition(@NonNull ItemDefinition definition) {
         this.definition = definition;
         return this;
      }

      public Builder damage(int damage) {
         this.damage = damage;
         return this;
      }

      public Builder count(int count) {
         this.count = count;
         return this;
      }

      public Builder tag(NbtMap tag) {
         this.tag = tag;
         return this;
      }

      public Builder canPlace(String... canPlace) {
         this.canPlace = canPlace;
         return this;
      }

      public Builder canBreak(String... canBreak) {
         this.canBreak = canBreak;
         return this;
      }

      public Builder blockingTicks(long blockingTicks) {
         this.blockingTicks = blockingTicks;
         return this;
      }

      public Builder blockDefinition(@Nullable BlockDefinition blockDefinition) {
         this.blockDefinition = blockDefinition;
         return this;
      }

      public Builder usingNetId(boolean usingNetId) {
         this.usingNetId = usingNetId;
         return this;
      }

      public Builder netId(int netId) {
         this.netId = netId;
         return this;
      }

      public ItemData build() {
         return new BaseItemData(this.definition, this.damage, this.count, this.tag, this.canPlace, this.canBreak, this.blockingTicks, this.blockDefinition, this.usingNetId, this.netId);
      }
   }
}
