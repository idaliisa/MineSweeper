/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.datastructures;

import java.util.Iterator;

/**
 *
 * @author ida
 */
/**
 * Custom implementation to hashSet. Implements add, remove, isEmtpy and size operations.
 * Uses Entry and CustomIterator classes and is iterable.
 * @author ida
 * @param <T> Type of elements
 */
public class CustomHashSet<T> {
        
    private Entry[] buckets;
    private int size;

        
    public CustomHashSet() {
        this.buckets = new Entry[1];
        this.size = 0;
    }

   
  
    public class Entry<T> {
        T obj;
        Entry next;

        public Entry(T obj) {
            this.obj = obj;
            this.next = null;
        }      
        
    }

    
    /**
     * Adds the specified element to the set if not yet added.
     * @param obj specified element
     * @return true if added
     */
    public boolean add(T obj) {
        int index = obj.hashCode() % buckets.length;
        Entry current = buckets[index];
        Entry<T> newEntry = new Entry<>(obj);

        if (current == null) {
            buckets[index] = newEntry;
            size++;
            return true;
        }
        
        while (current.next != null) {
            if (current.obj.equals(obj)) {
                return false;
            }
            current = current.next;
        }

        if (!current.obj.equals(obj)) {
            current.next = newEntry;
            size++;
            return true;
        }
        return false;
    }
    
    
    /**
     * Removes the specified element if it is present in the set.
     * @param obj specified element
     * @return removed element
     */
    public T remove(T obj) {
        int index = obj.hashCode() % buckets.length;
        Entry current = buckets[index];

        if (current == null) {
            throw new NullPointerException("Object not found");
        }

        if (current.obj.equals(obj)) {
            buckets[index] = current.next;
            size--;
            return obj;
        }

        Entry previous = current;
        while (current != null) {
            if (current.obj.equals(obj)) {
                previous.next = current.next;
                size--;
                return obj;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }
       
    
    /**
     * 
     * @return True if set does not contain eny elements
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    
    /**
     * 
     * @return number of elements in the set
     */
    public int getSize() {
        return size;
    }
    
    
    /**
     * Uses CustomIterator class. Implements next and hasNext operations.
     * @return iterator over the elements in set
     */
    public CustomIterator iterator() {
        return new CustomIterator<>();
    }
    

    /**
     * 
     * @param <T> Type of element
     */
    public class CustomIterator<T> implements Iterator {
    
        private Entry current;
        private int index;

        public CustomIterator() {
            this.current = null;
            this.index = -1;
        }
        
        
        /**
         * 
         * @return next element
         */
        public T next() {
            if (current != null && current.next != null) {
                current = current.next;
            } else {
                index++;
                current = buckets[index];
             }
            return (T) current.obj;
        }
          
          
        /**
         * 
         * @return true if next element is present
         */
        public boolean hasNext() {
            if (current != null && current.next != null) { 
                return true;
            }
            for (int i = index + 1; i < buckets.length; i++) {
               if (buckets[i] != null) { 
                   return true;
               }
            }
            return false;
        }   
        
    
    }
}
