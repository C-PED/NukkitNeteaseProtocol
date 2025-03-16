package com.nukkitx.network.raknet;

public enum RakNetReliability {
   UNRELIABLE(false, false, false, false),
   UNRELIABLE_SEQUENCED(false, false, true, false),
   RELIABLE(true, false, false, false),
   RELIABLE_ORDERED(true, true, false, false),
   RELIABLE_SEQUENCED(true, false, true, false),
   UNRELIABLE_WITH_ACK_RECEIPT(false, false, false, true),
   RELIABLE_WITH_ACK_RECEIPT(true, false, false, true),
   RELIABLE_ORDERED_WITH_ACK_RECEIPT(true, true, false, true);

   private static final RakNetReliability[] VALUES = values();
   final boolean reliable;
   final boolean ordered;
   final boolean sequenced;
   final boolean withAckReceipt;
   final int size;

   private RakNetReliability(boolean reliable, boolean ordered, boolean sequenced, boolean withAckReceipt) {
      this.reliable = reliable;
      this.ordered = ordered;
      this.sequenced = sequenced;
      this.withAckReceipt = withAckReceipt;
      int size = 0;
      if (this.reliable) {
         size += 3;
      }

      if (this.sequenced) {
         size += 3;
      }

      if (this.ordered) {
         size += 4;
      }

      this.size = size;
   }

   public static RakNetReliability fromId(int id) {
      return id >= 0 && id <= 7 ? VALUES[id] : null;
   }

   public boolean isReliable() {
      return this.reliable;
   }

   public boolean isOrdered() {
      return this.ordered;
   }

   public boolean isSequenced() {
      return this.sequenced;
   }

   public boolean isWithAckReceipt() {
      return this.withAckReceipt;
   }

   public int getSize() {
      return this.size;
   }

   // $FF: synthetic method
   private static RakNetReliability[] $values() {
      return new RakNetReliability[]{UNRELIABLE, UNRELIABLE_SEQUENCED, RELIABLE, RELIABLE_ORDERED, RELIABLE_SEQUENCED, UNRELIABLE_WITH_ACK_RECEIPT, RELIABLE_WITH_ACK_RECEIPT, RELIABLE_ORDERED_WITH_ACK_RECEIPT};
   }
}
