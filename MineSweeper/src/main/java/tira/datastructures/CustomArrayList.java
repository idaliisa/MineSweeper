/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.datastructures;

import java.util.Arrays;

/**
 *
 * @author ida
 */
public class CustomArrayList<Class> {
    
    private Class[] list;
    private int actualSize;

    public CustomArrayList() {
        list = (Class[]) new Object[10];
    }
    
    
    
    public int size() {
        return actualSize;
    }
    
    
    
    public Object get(int index) {
        if (index < actualSize) {
            return list[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    
    public void add(Class obj) {
        if(list.length - actualSize <= 0) {
            increaseListSize();
        }
        list[actualSize++] = obj;
        
    }
    
    
    
    private void increaseListSize() {
        int newSize = list.length * 2;
        list = Arrays.copyOf(list, newSize);
    }
    
    
}
