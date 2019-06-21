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
    
    
    
    public boolean isOpened() {
        return isOpened;
    }

    
    
    public int getNumber() {
        return number;
    }

    
    
    public boolean hasFlag() {
        return hasFlag;
    }

    
    
    public boolean hasMine() {
        return hasMine;
    }

    
        
    public Coordinate getCoordinate() {
        return coordinate;
    }
    
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Field o = (Field) obj;
        
        if (o.getCoordinate().getX() == this.getCoordinate().getX() && o.getCoordinate().getY() == this.getCoordinate().getY()) {
            return true;
        } else {
            return false;
        }
    }
    
    
    
    @Override
    public int hashCode() {
        return coordinate.getX();
    }
        
}
