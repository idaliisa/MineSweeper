/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.util;

import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ida
 */
public class RandomTest {
    private Random customRandom;
    
    public RandomTest() {
    }
    

    
    @Before
    public void setUp() {
        customRandom = new Random();
    }
    
    @Test
    public void nextInt1() {
        for (int i = 0; i < 1000; i++) {
            int r = customRandom.nextInt(1);
            assertTrue(r < 1);
            assertTrue(r >= 0);
        }
    }
    
    @Test
    public void nextInt2() {
        for (int i = 0; i < 10000; i++) {
            int r = customRandom.nextInt(100);
            assertTrue(r < 100);
            assertTrue(r >= 0);
        }
    }
}
