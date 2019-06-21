/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.minesweeper.logic;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tira.datastructures.CustomArrayList;


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
        board.setMine(board.getFieldAt(0, 0));
        board.setMine(board.getFieldAt(1, 0));
        
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
    
    @Test
    public void isFailedAtWorks1() {
        board.openField(board.getFieldAt(0, 0));
        assertTrue(board.isFailed(0, 0));
    }   
    @Test
    public void isFailedAtWorks2() {
        board.openField(board.getFieldAt(2, 0));
        assertFalse(board.isFailed(2, 0));
    }
    @Test
    public void isFailedWorks1() {
        board.openField(board.getFieldAt(0, 0));
        assertTrue(board.isFailed());
    }   
    @Test
    public void isFailedWorks2() {
        board.openField(board.getFieldAt(2, 0));
        assertFalse(board.isFailed());
    }

    @Test
    public void calcultesUnfoundMinesCorrectly1() {
        assertEquals(2, board.numberUnfoundMines());
    }
    @Test
    public void calcultesUnfoundMinesCorrectly2() {
        board.setFlag(board.getFieldAt(0, 0));
        assertEquals(1, board.numberUnfoundMines());
    }
    @Test
    public void calcultesUnfoundMinesCorrectly3() {
        board.setFlag(board.getFieldAt(0, 0));
        board.setFlag(board.getFieldAt(1, 0));
        board.removeFlag(board.getFieldAt(0, 0));
        assertEquals(1, board.numberUnfoundMines());
    }
    
    @Test
    public void TrueWhenSolved() {
        assertTrue(board.isSolved());
    }
    
    @Test
    public void calculatesOpenedFieldsCorrectly() {
        assertEquals(10, board.openedFields);
    }
    
    @Test
    public void getStateCorrectly1() {
        assertEquals("2", board.getState(board.getFieldAt(0, 1)));
    }   
    @Test
    public void getStateCorrectly2() {
        assertEquals("", board.getState(board.getFieldAt(0, 0)));
    }
    @Test
    public void getStateCorrectly3() {
        board.setFlag(board.getFieldAt(0, 0));
        assertEquals("F", board.getState(board.getFieldAt(0, 0)));
    }
    @Test
    public void getStateCorrectly4() {
        board.setFlag(board.getFieldAt(0, 0));
        board.openField(board.getFieldAt(0, 0));
        assertEquals("M", board.getState(board.getFieldAt(0, 0)));
    }
    
    @Test
    public void placeMines() {
        board = new Board(3, 4);
        Field firstClick = new Field(new Coordinate(3,4));
        board.openField(firstClick);
        board.placeMines(2, firstClick);
        CustomArrayList neighboursFirstClick = board.getNeighbours(firstClick);
        for (int i = 0; i < neighboursFirstClick.size(); i++) {
            Field field = (Field) neighboursFirstClick.get(i);
            assertFalse(field.hasMine());
        }
        
    }
    
    @Test
    public void openOneField() {
        board = new Board(3, 4);       
        //set two mines in x-y coordinates (0,0) and (1,0)
        board.setMine(board.getFieldAt(0, 0));
        board.setMine(board.getFieldAt(1, 0));
        
        board.openOneField(board.getFieldAt(3, 0));
        assertTrue(board.getFieldAt(3, 0).isOpened());
        assertFalse(board.getFieldAt(3, 1).isOpened());
    }
    
}
