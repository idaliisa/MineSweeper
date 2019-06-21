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
public class Random {
    
    private long seed;

    public Random() {
        this(System.currentTimeMillis());
    }

    public Random(long seed) {
        setSeed(seed);
    }
    
    
    
    private void setSeed(long seed) {
        this.seed = (seed ^ 0X5DEECE66DL) & ((1L << 48) - 1);
    }
    
    
    
    private int next(int bits) {
        seed = (seed * 0X5DEECE66DL + 0XBL) & ((1L << 48) - 1);
        return (int) (seed >>> (48 - bits));
    }
    
    

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
