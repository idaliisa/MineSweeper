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
/**
 * 
 * Square in the minesweeper board. Holds information about square status.
 */
public class Field {
    boolean hasMine;
    boolean hasFlag;
    boolean isOpened;
    int number;
    Coordinate coordinate;

    public Field(Coordinate c) {
        this.hasMine = false;
        this.hasFlag = false;
        this.isOpened = false;
        this.number = 0;
        this.coordinate = c;
    }

    public void setMine() {
        this.hasMine = true;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public int getNumber() {
        return number;
    }
    
    
    
    
    
}
