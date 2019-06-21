/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.minesweeper.logic;

/**
 *
 * @author ida
 */
/***
 * XY-coordinate where X corresponds to gameboard colums and Y to rows.
 * 
 */
public class Coordinate {
    public final int x;
    public final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    
    /**
     * 
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    
    /**
     * 
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }
    
        
}
