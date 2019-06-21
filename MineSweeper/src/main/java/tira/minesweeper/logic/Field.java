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
 * Implements isOpened,getNumber, hasFlag, hasMine, getCoordinate, equals and hashcode
 * operations.
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
    
    
    /**
     * 
     * @return true if the field is opened
     */
    public boolean isOpened() {
        return isOpened;
    }

    
    /**
     * 
     * @return count of mines at neighbours
     */
    public int getNumber() {
        return number;
    }

    
    /**
     * 
     * @return true if the field is flagged
     */
    public boolean hasFlag() {
        return hasFlag;
    }

    
    /**
     * 
     * @return true if the field has mine
     */
    public boolean hasMine() {
        return hasMine;
    }

    
    /**
     * 
     * @return coordinate of teh field
     */    
    public Coordinate getCoordinate() {
        return coordinate;
    }
    
    
    /**
     * 
     * @param obj specified element
     * @return true if both are type of Field and x-y-coordinates do match
     */
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
    
    
    /**
     * 
     * @return hashCode that is x-coordinate
     */
    @Override
    public int hashCode() {
        return coordinate.getX();
    }
        
}
