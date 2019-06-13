/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.minesweeper.solver;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tira.minesweeper.logic.Board;

/**
 *
 * @author ida
 */
public class SolverTest {
    
    Solver solver;
    
    @Before
    public void setUp() {
        // Board setup
        // [M M 1 0]
        // [2 2 1 0]
        // [0 0 1 1]
        // [0 1 2 M]
        // [0 1 M 2]
        solver = new Solver(5, 4, 0);
        solver.board = new Board(5, 4);
        
        //set four mines in x-y coordinates (0,0), (1,0), (3,4) and (2,4)
        solver.board.setMine(solver.board.getFieldAt(0, 0));
        solver.board.setMine(solver.board.getFieldAt(1, 0));
        solver.board.setMine(solver.board.getFieldAt(3, 3));
        solver.board.setMine(solver.board.getFieldAt(2, 4));
        
        solver.board.placeNumbers();
        
                
        
        
        //open square (1,2)
        solver.board.openField(solver.board.getFieldAt(1, 2));

    }
    
    
    @Test
    public void countsEffectiveNumberCorrectly1() {
        assertEquals(2, solver.effectiveNumber(2, 3));
    }
    
    @Test
    public void countsEffectiveNumberCorrectly2() {
        solver.board.setFlag(solver.board.getFieldAt(2, 4));
        assertEquals(1, solver.effectiveNumber(2, 3));
    }
    
    @Test
    public void isAFNworks1() {
        assertFalse(solver.isAFN(2, 3));
    }
    @Test
    public void isAFNworks2() {
        solver.board.setFlag(solver.board.getFieldAt(2, 4));
        solver.board.setFlag(solver.board.getFieldAt(3, 3));
        assertTrue(solver.isAFN(2, 3));
    }
    @Test
    public void isAMNworks1() {
        solver.board.setFlag(solver.board.getFieldAt(2, 4));
        solver.board.setFlag(solver.board.getFieldAt(3, 3));
        assertFalse(solver.isAMN(2, 3));
    }
    @Test
    public void isAMNworks2() {
        solver.board.openField(solver.board.getFieldAt(3, 2));
        solver.board.openField(solver.board.getFieldAt(3, 4));
        assertTrue(solver.isAMN(2, 3));
    }
    
}
