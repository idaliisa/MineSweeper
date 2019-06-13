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

        public T getData() {
            return obj;
        }
    }

    
    
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
    
    
    
    public T remove(T obj) {
        int index = obj.hashCode() % buckets.length;
        Entry current = buckets[index];

        if (current == null) {
            new Exception("Object not found");
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
    
    
    
    
    private int bucketLength() {
        return buckets.length;
    }

    
            
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    
    
    
    
    public CustomIterator iterator() {
        return new CustomIterator<>();
    }
    


    public class CustomIterator<T> implements Iterator {
    
        private Entry current;
        private int index;

        public CustomIterator() {
            this.current = null;
            this.index = -1;
        }
        
        
        
        public T next() {
            if (current != null && current.next != null) {
                current = current.next;
            } else {
                index++;
                if (index == buckets.length) {
                   new Exception("Next object does not exist");
                }
                current = buckets[index];
             }
            return (T) current.obj;
        }
          
          
        
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
