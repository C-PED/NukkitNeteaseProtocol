package com.nukkitx.network.raknet.util;

import java.util.Collection;
import java.util.Iterator;

public class RoundRobinIterator<E> implements Iterator<E> {
   private final Collection<E> collection;
   private Iterator<E> iterator;

   public RoundRobinIterator(Collection<E> collection) {
      this.collection = collection;
      this.iterator = this.collection.iterator();
   }

   public synchronized boolean hasNext() {
      return !this.collection.isEmpty();
   }

   public synchronized E next() {
      if (!this.iterator.hasNext()) {
         this.iterator = this.collection.iterator();
      }

      return (E)this.iterator.next();
   }
}
