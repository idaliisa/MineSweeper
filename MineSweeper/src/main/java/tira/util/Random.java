/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.util;

/**
 *
 * @author ida
 */
/***
 * PseudoRandom number generator. Implements nextInt operation.
 * 
 */
public class Random {
    
    private long seed;
   
    /***
     * Creates a new pseudo random number generator.
     */
    public Random() {
        this(System.currentTimeMillis());
    }

    
    /***
     * Creates a new pseudo random generator.
     * @param seed 
     */
    public Random(long seed) {
        setSeed(seed);
    }
    
    
    /**
     * Sets the seed of the random number generator.
     * @param seed 
     */
    private void setSeed(long seed) {
        this.seed = (seed ^ 0X5DEECE66DL) & ((1L << 48) - 1);
    }
    
    
    /**
     * Generates pseudo random number.
     * @param bits bit value
     * @return int value
     */
    private int next(int bits) {
        seed = (seed * 0X5DEECE66DL + 0XBL) & ((1L << 48) - 1);
        return (int) (seed >>> (48 - bits));
    }
    
    
    /**
     * Generates pseudo random int between 0 (inclusive) and specified value (exclusive).
     * @param n specified value
     * @return random int
     */
    public int nextInt(int n) {
        if ((n & -n) == n) {
            return (int) ((n*(long) next(31)) >> 31);
        }
        int bits, val;
        
        bits = next(31);
        val = bits % n;
        while (bits - val + (n - 1) < 0);
        return val;
    }
    
}
