/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.minesweeper.measurement;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ida
 */
public class PerformanceTest {
    private Performance performanceEasyGame;
    public PerformanceTest() {
    }

    
    @Before
    public void setUp() {
        performanceEasyGame = new Performance(9, 9, 10, 100);
        performanceEasyGame.doCalculations();
    }


    @Test
    public void percentageOfFailedAndSolvedTotalsTo100() {
        for (int i = 0; i < 1000; i++) {
            Double total = performanceEasyGame.getFailed() + performanceEasyGame.getSolved();        
            int totalIntValue = total.intValue();
            assertEquals(100, totalIntValue);
        }        
    }
    
    @Test
    public void timeIsPositiveNumber() {
        assertTrue(performanceEasyGame.getTime() >= 0);        
    }

}
