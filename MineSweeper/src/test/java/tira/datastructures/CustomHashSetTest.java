/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.datastructures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tira.minesweeper.logic.Coordinate;
import tira.minesweeper.logic.Field;
import tira.datastructures.CustomHashSet.CustomIterator;

/**
 *
 * @author ida
 */
public class CustomHashSetTest {
    
    CustomHashSet customHashSet;
    
    
    @Before
    public void setUp() {
        customHashSet = new CustomHashSet();
        customHashSet.add(new Field(new Coordinate(2,3)));
        customHashSet.add(new Field(new Coordinate(4,3)));
    }
    
    @Test
    public void isEmptyWorksWhenSetIsEmpty() {
        customHashSet.remove(new Field(new Coordinate(2,3)));
        customHashSet.remove(new Field(new Coordinate(4,3)));
        assertTrue(customHashSet.isEmpty());        
    }
    
    @Test
    public void isEmptyWorksWhenSetIsNotEmpty() {
        customHashSet.remove(new Field(new Coordinate(2,3)));
        assertFalse(customHashSet.isEmpty());        
    }
    
    @Test
    public void correctItemIsRemovedWhenOtherItemsArehavingTheSameHashCode() {
        customHashSet.add(new Field(new Coordinate(2,4)));
        assertEquals(new Field(new Coordinate(2,3)), customHashSet.remove(new Field(new Coordinate(2,3))));        
    }
    
    
    @Test
    public void itemIsRemovedCorrectlyWhenOtherItemsArehavingTheSameHashCode() {
        customHashSet.add(new Field(new Coordinate(2,4)));
        assertTrue(new Field(new Coordinate(2,4)).equals(customHashSet.remove(new Field(new Coordinate(2,4)))));        
    }
    
    
    @Test
    public void hasNextWorksWhenSetDoesHaveNext() {
        CustomIterator iterator = customHashSet.iterator();
        iterator.next();
        assertTrue(iterator.hasNext());
    }
   
    
    @Test
    public void hasNextWorksWhenSetDoesNotHaveNext() {
        CustomIterator iterator = customHashSet.iterator();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }
    
    
    @Test
    public void hasNextWorksWhenSetIsEmpty() {
        customHashSet.remove(new Field(new Coordinate(2,3)));
        customHashSet.remove(new Field(new Coordinate(4,3)));
        CustomIterator iterator = customHashSet.iterator();
        assertFalse(iterator.hasNext());
    }
    
}
