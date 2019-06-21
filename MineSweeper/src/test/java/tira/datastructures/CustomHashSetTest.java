/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.datastructures;

import org.junit.Before;
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
    public void returnsTrueWhenSetIsEmpty() {
        customHashSet.remove(new Field(new Coordinate(2,3)));
        customHashSet.remove(new Field(new Coordinate(4,3)));
        assertTrue(customHashSet.isEmpty());        
    }
    
    @Test
    public void returnsFalseWhenSetIsNotEmpty() {
        customHashSet.remove(new Field(new Coordinate(2,3)));
        assertFalse(customHashSet.isEmpty());        
    }
    
    @Test
    public void addItemWhenNotYetAddedAndHashCodeDoesNotExists() {
        assertTrue(customHashSet.add(new Field(new Coordinate(0,2))));
    }
    
    @Test
    public void addItemWhenNotYetAddedAndHashCodeExists() {
        assertTrue(customHashSet.add(new Field(new Coordinate(2,2))));
    }
    
    @Test
    public void DoesNotAddItemWhenAlreadyAdded() {
        assertFalse(customHashSet.add(new Field(new Coordinate(2,3))));
    }
    
    @Test
    public void DoesNotAddItemWhenAlreadyAddedAndManyItemsHaveTheSameHashCode() {
        customHashSet.add(new Field(new Coordinate(2,4)));
        assertFalse(customHashSet.add(new Field(new Coordinate(2,4))));
    }
               
    @Test
    public void itemIsRemovedWhenManyItemsHaveTheSameHashCode() {
        customHashSet.add(new Field(new Coordinate(2,4)));
        assertTrue(new Field(new Coordinate(2,4)).equals(customHashSet.remove(new Field(new Coordinate(2,4)))));        
    }
    
    @Test
    public void nullWhenRemovingObjectThatIsNotInSetAndHashCodeIs() {
        assertEquals(null, customHashSet.remove(new Field(new Coordinate(2,4))));
    }
    
    @Test
    public void nullWhenRemovingObjectThatIsNotInSetAndHashCodeIsNotInSet() {
        customHashSet.remove(new Field(new Coordinate(2,3)));
        assertEquals(null, customHashSet.remove(new Field(new Coordinate(2,3))));
    }
    
    @Test (expected = NullPointerException.class)
    public void NullPointerExceptionIsThrownWhenRemovingObjectFromNewEmptySet() {
        CustomHashSet hs = new CustomHashSet();
        hs.remove(new Field(new Coordinate(6,6)));
    }
    
    
    @Test
    public void hasNextWorksWhenSetDoesHaveNext() {
        CustomIterator iterator = customHashSet.iterator();
        iterator.next();
        assertTrue(iterator.hasNext());
    }
   

    @Test
    public void hasNextWorksWhenSetIsEmpty() {
        customHashSet.remove(new Field(new Coordinate(2,3)));
        customHashSet.remove(new Field(new Coordinate(4,3)));
        CustomIterator iterator = customHashSet.iterator();
        assertFalse(iterator.hasNext());
    }
    
    
    @Test (expected = NullPointerException.class)
    public void NullPointerExceptionIsThrownWhenIteratingEmptySet1() {
        customHashSet.remove(new Field(new Coordinate(2,3)));
        customHashSet.remove(new Field(new Coordinate(4,3)));
        CustomIterator iterator = customHashSet.iterator();
        iterator.next();
    }    
   


    @Test (expected = NullPointerException.class)
    public void NullPointerExceptionIsThrownWhenIteratingEmptySet2() {
        customHashSet = new CustomHashSet();
        CustomIterator iterator = customHashSet.iterator();
        iterator.next();
    }

    @Test
    public void trueWhenNextInSameHashCode() {
        customHashSet.add(new Field(new Coordinate(2,4)));
        CustomIterator iterator = customHashSet.iterator();
        iterator.next();
        iterator.next();
        assertEquals(iterator.next(), new Field(new Coordinate(2,4)));
    }
    
    @Test
    public void trueWhenHasNextIsFirstOneInTheHashCode() {
        CustomIterator iterator = customHashSet.iterator();
        assertTrue(iterator.hasNext());
    }
    
    @Test
    public void getSize() {
        assertEquals(2, customHashSet.getSize());
    }
   
}
