package com.nukkitx.network.raknet.util;

import com.nukkitx.network.raknet.EncapsulatedPacket;
import com.nukkitx.network.raknet.RakNetUtils;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class FastBinaryMinHeap {
   private HeapElement[] heap;
   public long[] weights;
   private int size;

   public FastBinaryMinHeap() {
      this(8);
   }

   public FastBinaryMinHeap(int initialCapacity) {
      ++initialCapacity;
      this.heap = new HeapElement[initialCapacity];
      this.weights = new long[initialCapacity];
      Arrays.fill(this.weights, Long.MAX_VALUE);
      this.weights[0] = Long.MIN_VALUE;
   }

   private void resize(int capacity) {
      int adjustedSize = this.size + 1;
      int copyLength = Math.min(this.heap.length, adjustedSize);
      HeapElement[] newHeap = new HeapElement[capacity];
      long[] newWeights = new long[capacity];
      System.arraycopy(this.heap, 0, newHeap, 0, copyLength);
      System.arraycopy(this.weights, 0, newWeights, 0, copyLength);
      if (capacity > adjustedSize) {
         Arrays.fill(newWeights, adjustedSize, capacity, Long.MAX_VALUE);
      }

      this.heap = newHeap;
      this.weights = newWeights;
   }

   public void insert(long weight, EncapsulatedPacket element) {
      Objects.requireNonNull(element, "element");
      this.ensureCapacity(this.size + 1);
      this.insert0(weight, new EncapsulatedPacket[]{element});
   }

   private void insert0(long weight, EncapsulatedPacket[] element) {
      int hole = ++this.size;
      int pred = hole >> 1;

      for(long predWeight = this.weights[pred]; predWeight > weight; predWeight = this.weights[pred]) {
         this.weights[hole] = predWeight;
         this.heap[hole] = this.heap[pred];
         hole = pred;
         pred >>= 1;
      }

      this.weights[hole] = weight;
      this.heap[hole] = new HeapElement(element);
   }

   public void insertSeries(long weight, EncapsulatedPacket[] elements) {
      Objects.requireNonNull(elements, "elements");
      if (elements.length != 0) {
         this.ensureCapacity(this.size + elements.length);
         this.insert0(weight, elements);
      }
   }

   private void ensureCapacity(int size) {
      if (size + 1 >= this.heap.length) {
         this.resize(RakNetUtils.powerOfTwoCeiling(size + 1));
      }

   }

   public EncapsulatedPacket peek() {
      return this.heap[1] != null ? this.heap[1].peek() : null;
   }

   public EncapsulatedPacket poll() {
      if (this.size > 0) {
         EncapsulatedPacket e = this.peek();
         this.remove();
         return e;
      } else {
         return null;
      }
   }

   public void remove() {
      if (this.size == 0) {
         throw new NoSuchElementException("Heap is empty");
      } else if (!this.heap[1].remove()) {
         int hole = 1;
         int succ = 2;

         int sz;
         for(sz = this.size; succ < sz; succ <<= 1) {
            long weight1 = this.weights[succ];
            long weight2 = this.weights[succ + 1];
            if (weight1 > weight2) {
               this.weights[hole] = weight2;
               ++succ;
               this.heap[hole] = this.heap[succ];
            } else {
               this.weights[hole] = weight1;
               this.heap[hole] = this.heap[succ];
            }

            hole = succ;
         }

         long bubble = this.weights[sz];

         for(int pred = hole >> 1; this.weights[pred] > bubble; pred >>= 1) {
            this.weights[hole] = this.weights[pred];
            this.heap[hole] = this.heap[pred];
            hole = pred;
         }

         this.weights[hole] = bubble;
         this.heap[hole] = this.heap[sz];
         this.heap[sz] = null;
         this.weights[sz] = Long.MAX_VALUE;
         --this.size;
         if (this.size << 2 < this.heap.length && this.size > 4) {
            this.resize(this.size << 1);
         }

      }
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public int size() {
      return this.size;
   }

   static class HeapElement {
      private final EncapsulatedPacket[] elem;
      private int index;

      public HeapElement(EncapsulatedPacket[] e) {
         this.elem = e;
      }

      public EncapsulatedPacket peek() {
         return this.elem[this.index];
      }

      public boolean remove() {
         ++this.index;
         return this.index < this.elem.length;
      }
   }
}
