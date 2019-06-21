/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.datastructures;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ida
 */
public class CustomArrayListTest {
    
    CustomArrayList customArrayList;
    
    @Before
    public void setUp() {
        customArrayList = new CustomArrayList();
        customArrayList.add("0");
        customArrayList.add("1");
        customArrayList.add("2");
    }
    
    
    @Test
    public void addsCorrectlytoCustomArrayList() {
        assertEquals("1", customArrayList.get(1).toString());
    }
    
    @Test
    public void addsCorrectlytoCustomArrayListWhenArrayLengthIsIncreased1() {
        customArrayList.add("3");
        customArrayList.add("4");
        customArrayList.add("5");
        customArrayList.add("6");
        customArrayList.add("7");
        customArrayList.add("8");
        customArrayList.add("9");
        customArrayList.add("10");
        customArrayList.add("11");
        assertEquals("5", customArrayList.get(5).toString());
    }
    
    @Test
    public void addsCorrectlytoCustomArrayListWhenArrayLengthIsIncreased2() {
        customArrayList.add("3");
        customArrayList.add("4");
        customArrayList.add("5");
        customArrayList.add("6");
        customArrayList.add("7");
        customArrayList.add("8");
        customArrayList.add("9");
        customArrayList.add("10");
        customArrayList.add("11");
        assertEquals("11", customArrayList.get(11).toString());
    }
    
    @Test
    public void returnsTrueWhenArrayListContainsParamObject() {
        assertTrue(customArrayList.contains("2"));
    }

    @Test
    public void returnsFalseWhenArrayListDoesNotContainParamObject() {
        assertFalse(customArrayList.contains("3"));
    }
    
    @Test (expected = IndexOutOfBoundsException.class)
    public void indexOutOfBoundsExceptionForGet() {
        customArrayList.get(3);
    }
    
    @Test
    public void removeElementThatIsInList() {
        customArrayList.remove(0);
        assertEquals("1", customArrayList.get(0));
        assertEquals("2", customArrayList.get(1));
        assertEquals(2, customArrayList.size());
    }
    
    @Test (expected = IndexOutOfBoundsException.class)
    public void indexOutOfBoundsExceptionForRemove() {
        customArrayList.remove(3);
    }
    

}
