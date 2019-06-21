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
public class CustomArrayList<T> {
    
    private T[] list;
    private int actualSize;

    public CustomArrayList() {
        list = (T[]) new Object[10];
    }
    
    
    
    public int size() {
        return actualSize;
    }
    
    
    
    public T get(int index) {
        if (index < actualSize) {
            return (T) list[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    
    
    
    public void add(T obj) {
        if(list.length - actualSize <= 0) {
            increaseListSize();
        }
        list[actualSize++] = obj;
        
    }
    
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
    
    
    
    private void increaseListSize() {
        int newSize = list.length * 2;
        T[] newList = (T[]) new Object[newSize];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list= newList;
    }
    
    public boolean contains(T obj) {
        for (int i = 0; i < actualSize; i++) {
            if (list[i].equals(obj)) {
                return true;
            }
        }
        return false;
    }
    
    
}
