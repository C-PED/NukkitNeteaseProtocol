package com.nukkitx.protocol.common.util;

import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SequencedHashSet<E> implements List<E> {
   private final Object2IntMap<E> map = new Object2IntLinkedOpenHashMap();
   private final Int2ObjectMap<E> inverse = new Int2ObjectLinkedOpenHashMap();
   private int index = 0;

   public int indexOf(Object o) {
      return this.map.getInt(o);
   }

   public int lastIndexOf(Object o) {
      return this.map.getInt(o);
   }

   public ListIterator<E> listIterator() {
      throw new UnsupportedOperationException();
   }

   public ListIterator<E> listIterator(int index) {
      throw new UnsupportedOperationException();
   }

   public List<E> subList(int fromIndex, int toIndex) {
      throw new UnsupportedOperationException();
   }

   public int size() {
      return this.map.size();
   }

   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   public boolean contains(Object o) {
      return this.map.containsKey(o);
   }

   public Iterator<E> iterator() {
      return this.map.keySet().iterator();
   }

   public Object[] toArray() {
      return this.map.keySet().toArray();
   }

   public <T> T[] toArray(T[] a) {
      return (T[])this.map.keySet().toArray(a);
   }

   public boolean add(E e) {
      if (!this.map.containsKey(e)) {
         int index = this.index++;
         this.map.put(e, index);
         this.inverse.put(index, e);
         return true;
      } else {
         return false;
      }
   }

   public boolean remove(Object o) {
      throw new UnsupportedOperationException();
   }

   public boolean containsAll(Collection<?> c) {
      return this.map.keySet().containsAll(c);
   }

   public boolean addAll(Collection<? extends E> c) {
      for(E e : c) {
         this.add(e);
      }

      return true;
   }

   public boolean addAll(int index, Collection<? extends E> c) {
      throw new UnsupportedOperationException();
   }

   public boolean retainAll(Collection<?> c) {
      throw new UnsupportedOperationException();
   }

   public boolean removeAll(Collection<?> c) {
      throw new UnsupportedOperationException();
   }

   public void clear() {
      throw new UnsupportedOperationException();
   }

   public E get(int index) {
      return (E)this.inverse.get(index);
   }

   public E set(int index, E element) {
      throw new UnsupportedOperationException();
   }

   public void add(int index, E element) {
      throw new UnsupportedOperationException();
   }

   public E remove(int index) {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      return this.map.keySet().toString();
   }
}
