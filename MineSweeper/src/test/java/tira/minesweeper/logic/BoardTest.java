/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.minesweeper.logic;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author ida
 */
public class BoardTest {
    
    Board board;
    
    
    @Before
    public void setUp() {
        // Board setup
        // [1 1 1 0]
        // [2 2 1 0]
        // [0 0 0 0]
        
        board = new Board(3, 4);
        
        //set two mines in x-y coordinates (0,0) and (1,0)
        board.getFieldAt(0, 0).setMine();
        board.getFieldAt(1, 0).setMine();
        
        board.placeNumbers();
        
        //open square (2,2)
        board.openField(board.getFieldAt(2, 2));
    }
    
    @Test 
    public void boundCheckWorks1() {
        assertTrue(board.inBounds(3, 2));
    }
    
    @Test 
    public void boundCheckWorks2() {
        assertFalse(board.inBounds(3, 3));
    }

 
    @Test
    public void fieldsOpenedCorrectly1() {               
        assertFalse(board.getFieldAt(0, 0).isOpened());   
    }
    @Test
    public void fieldsOpenedCorrectly2() {
        assertFalse(board.getFieldAt(1, 0).isOpened());   
    }
    @Test
    public void fieldsOpenedCorrectly3() {
        assertTrue(board.getFieldAt(2, 0).isOpened());  
    }
    @Test
    public void fieldsOpenedCorrectly4() {
        assertTrue(board.getFieldAt(3, 0).isOpened()); 
    }
    @Test
    public void fieldsOpenedCorrectly5() {
        assertTrue(board.getFieldAt(0, 1).isOpened());   
    }
    @Test
    public void fieldsOpenedCorrectly6() {
        assertTrue(board.getFieldAt(1, 1).isOpened());  
    }
    @Test
    public void fieldsOpenedCorrectly7() {
        assertTrue(board.getFieldAt(2, 1).isOpened()); 
    }
    @Test
    public void fieldsOpenedCorrectly8() {
        assertTrue(board.getFieldAt(3, 1).isOpened());   
    }
    @Test
    public void fieldsOpenedCorrectly9() {
        assertTrue(board.getFieldAt(0, 2).isOpened());    
    }
    @Test
    public void fieldsOpenedCorrectly10() {
        assertTrue(board.getFieldAt(1, 2).isOpened());    
    }
    @Test
    public void fieldsOpenedCorrectly11() {
        assertTrue(board.getFieldAt(2, 2).isOpened());    
    }
    @Test
    public void fieldsOpenedCorrectly12() {
        assertTrue(board.getFieldAt(3, 2).isOpened());    
    }
    
   
    @Test
    public void calculatesNeigbourMinesCorrectly1() {
        assertEquals(1, board.getFieldAt(0, 0).getNumber());    
    }
    @Test
    public void calculatesNeigbourMinesCorrectly2() {
        assertEquals(1, board.getFieldAt(1, 0).getNumber());    
    }
    @Test
    public void calculatesNeigbourMinesCorrectly3() {
        assertEquals(1, board.getFieldAt(2, 0).getNumber());    
    }
    @Test
    public void calculatesNeigbourMinesCorrectly4() {
        assertEquals(0, board.getFieldAt(3, 0).getNumber());    
    }
    @Test
    public void calculatesNeigbourMinesCorrectly5() {
        assertEquals(2, board.getFieldAt(0, 1).getNumber());    
    }
    @Test
    public void calculatesNeigbourMinesCorrectly6() {
        assertEquals(2, board.getFieldAt(1, 1).getNumber());    
    }
    @Test
    public void calculatesNeigbourMinesCorrectly7() {
        assertEquals(1, board.getFieldAt(2, 1).getNumber());    
    }
    @Test
    public void calculatesNeigbourMinesCorrectly8() {
        assertEquals(0, board.getFieldAt(3, 1).getNumber());    
    }
    @Test
    public void calculatesNeigbourMinesCorrectly9() {
        assertEquals(0, board.getFieldAt(0, 2).getNumber());    
    }
    @Test
    public void calculatesNeigbourMinesCorrectly10() {
        assertEquals(0, board.getFieldAt(1, 2).getNumber());    
    }
    @Test
    public void calculatesNeigbourMinesCorrectly11() {
        assertEquals(0, board.getFieldAt(2, 2).getNumber());    
    }
    @Test
    public void calculatesNeigbourMinesCorrectly12() {
        assertEquals(0, board.getFieldAt(3, 2).getNumber());    
    }
    
}
