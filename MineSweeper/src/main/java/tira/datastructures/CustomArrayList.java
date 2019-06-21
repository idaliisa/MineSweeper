/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.datastructures;


/**
 *
 * @author ida
 */
/**
 * Custom implementation to ArrayList. Resizable-array starting with size of 10.
 * Implements size, get, add and remove operations.
 * @author ida
 * @param <T> Type of elements
 */
public class CustomArrayList<T> {
    
    private T[] list;
    private int actualSize;

    public CustomArrayList() {
        list = (T[]) new Object[10];
    }
    
    
    /**
     * 
     * @return number of elements in the list
     */
    public int size() {
        return actualSize;
    }
    
    
    /**
     * 
     * @param index position in the list
     * @return element at the specified position
     */
    public T get(int index) {
        if (index < actualSize) {
            return (T) list[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    
    
    /**
     * Adds the specified element to the end of the list.
     * @param obj element to be added
     */
    public void add(T obj) {
        if(list.length - actualSize <= 0) {
            increaseListSize();
        }
        list[actualSize++] = obj;
        
    }
    
    
    /**
     * Removes the element at the specified position.
     * @param index position in the list
     * @return removed elemet
     */
    public T remove(int index) {
        if (index < actualSize && index >= 0) {
            int i = index;
            T removed = list[index];

            for (int j = 0; j < actualSize - 1; j++) {
                list[j] = list[j + 1];
            }
            actualSize--;
            return removed;
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }

    }
    
    
    /**
     * Copy elemnts to the new double-sized list.
     */
    private void increaseListSize() {
        int newSize = list.length * 2;
        T[] newList = (T[]) new Object[newSize];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list= newList;
    }
    
    
    /**
     * Iterates the list and checks whether at least one specified element exists.
     * @param obj specified element
     * @return True if the list contains the specified element.
     */
    public boolean contains(T obj) {
        for (int i = 0; i < actualSize; i++) {
            if (list[i].equals(obj)) {
                return true;
            }
        }
        return false;
    }
    
    
}
