/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.minesweeper.logic;


import java.util.ArrayList;
import java.util.Random;
import tira.datastructures.CustomArrayList;


/**
 *
 * @author ida
 */
/**
 * 
 * Minesweeper Board where X increases left to right and y top to bottom
 */
public class Board {
    public Field[] grid;
    int rows;
    int cols;
    int totalMines;
    int flaggedMines;
    int openedFields;
    //ArrayList<Coordinate> totalMines;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Field[rows * cols];
        this.flaggedMines = 0;
        this.openedFields = 0;
        createBoard();
        //this.totalMines = new ArrayList<>();
    }
    
    
    
    private int twoDtoOneD(int x, int y) {
        return x + (y * cols);
    }

    
       
    public void createBoard() { 
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                Coordinate c = new Coordinate(x, y);
                grid[twoDtoOneD(x, y)] = new Field(c);
            }
        }
    }

           
    /**
     * Set totalMines after createBoard() and the first click
     * @param mineCount
     * @param firstClick 
     */
    public void placeMines(int mineCount, Field firstClick) {
        Random r = new Random();

        totalMines = 0;
                
        while (totalMines < mineCount) {
            int y = r.nextInt(rows);
            int x = r.nextInt(cols);
            
            if (getFieldAt(x, y).hasMine == false && !getFieldAt(x, y).equals(firstClick)) {
                //Coordinate c = new Coordinate(x, y);
                getFieldAt(x, y).hasMine = true;
                totalMines++;
                //mines.add(c);
            }
        }        
    }
    
    
    /**
     * 
     * @param coordinate x-y coordinate
     * @return true if coordinate is in bounds
     */
    public boolean inBounds(Coordinate coordinate) {
        return inBounds(coordinate.x, coordinate.y);
    }
    
    
    /**
     * 
     * @param x x coordnate
     * @param y y coodinate
     * @return true if coordinate is in bounds
     */
    public boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < cols && y < rows;
    }
    
    
    /**
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @return field at the coordinate
     */
    public Field getFieldAt(int x, int y) {
        return grid[twoDtoOneD(x, y)];
    }
    
        
    /**
     * 
     * @param f field
     * @return neighbour fields
     */
    public ArrayList<Field> getNeighbours(Field f) {
        ArrayList<Field> neighbours = new ArrayList<>();

        if (inBounds(f.coordinate.x - 1, f.coordinate.y - 1)) {
            neighbours.add(getFieldAt(f.coordinate.x - 1, f.coordinate.y - 1));
        }
        if (inBounds(f.coordinate.x, f.coordinate.y - 1)) {
            neighbours.add(getFieldAt(f.coordinate.x, f.coordinate.y - 1));
        }
        if (inBounds(f.coordinate.x + 1, f.coordinate.y - 1)) {
            neighbours.add(getFieldAt(f.coordinate.x + 1, f.coordinate.y - 1));
        }
        if (inBounds(f.coordinate.x - 1, f.coordinate.y)) {
            neighbours.add(getFieldAt(f.coordinate.x - 1, f.coordinate.y));
        }
        if (inBounds(f.coordinate.x + 1, f.coordinate.y)) {
            neighbours.add(getFieldAt(f.coordinate.x + 1, f.coordinate.y));
        }
        if (inBounds(f.coordinate.x - 1, f.coordinate.y + 1)) {
            neighbours.add(getFieldAt(f.coordinate.x - 1, f.coordinate.y + 1));
        }
        if (inBounds(f.coordinate.x, f.coordinate.y + 1)) {
            neighbours.add(getFieldAt(f.coordinate.x, f.coordinate.y + 1));
        }
        if (inBounds(f.coordinate.x + 1, f.coordinate.y + 1)) {
            neighbours.add(getFieldAt(f.coordinate.x + 1, f.coordinate.y + 1));
        }
        
        return neighbours;

    }

    
    /**
     * counts mines at neighbour fields
     */    
    public void placeNumbers() {
    //to-do:change this method so that numbers are counted only after field is opened
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                ArrayList<Field> neighbours = getNeighbours(getFieldAt(x, y));
                for (int i = 0; i < neighbours.size(); i++) {
                    Field n = (Field) neighbours.get(i);
                    if (n.hasMine) {
                        getFieldAt(x, y).number++;
                    }
                }
            }
        }            
    }
    
     
    /**
     * opens fields recursively
     * @param field 
     */
    public void openField(Field field) {
        if (inBounds(field.coordinate) && !field.isOpened) {
            field.isOpened = true;
            openedFields++;
            //to-do:refactor
            if (field.number == 0 && !isFailed(field.coordinate.x, field.coordinate.y)) {
                openNeighbours(field);        
            }
        }
    }
    
    
    /**
     * help method for openField()
     * @param field 
     */
    public void openNeighbours(Field field) {
        ArrayList<Field> neighbours = getNeighbours(field);
        for (int i = 0; i < neighbours.size(); i++) {
            Field n = (Field) neighbours.get(i);
            if (inBounds(field.coordinate) && !n.isOpened) {
                openField(n);
            }
        }
    }
    
    
    /**
     * 
     * @param field 
     */
    public void openOneField(Field field) {
        if (inBounds(field.coordinate) && !field.isOpened) {
            field.isOpened = true;
            openedFields++;
        }
    }
    
    
    
    
    //to-do: open all neighbours if flaggedNeigboursCount equals to neighboursMineCount
    
    
    /**
     * 
     * @param field 
     */
    public void setMine(Field field) {
        if (field.hasMine == false) {
            totalMines++;
        }
        field.hasMine = true;
    }
    
    
    /**
     * 
     * @param field 
     */
    public void setFlag(Field field) {
        if (field.hasFlag == false) {
            flaggedMines++;
        }
        field.hasFlag = true;
    }
    
    
    /**
     * 
     * @param field 
     */
    public void removeFlag(Field field) {
        if (field.hasFlag == true) {
            flaggedMines--;
        }
        field.hasFlag = false;
    }
    

    /**
     * 
     * @return 
     */
    public int numberUnfoundMines() {
        return totalMines - flaggedMines;
    }
    
    
    /**
     * 
     * @param x
     * @param y
     * @return true if mine is opened
     */
    public boolean isFailed(int x, int y) {
        if (getFieldAt(x, y).hasMine && getFieldAt(x, y).isOpened) {
            return true;
        } else {
            return false;
        }        
    }
    
    
    
    public boolean isFailed() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (getFieldAt(x, y).isOpened() && getFieldAt(x, y).hasMine()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    
    public boolean isSolved() {
        if (totalMines == (rows * cols - openedFields)) {
            return true;
        } else {
            return false;
        }
    } 
    
    
    /**
     * 
     * @param field
     * @return Field state by the highest priority first: mine, number, flag or closed
     */
    //to-do: refactor
    public String getState(Field field) {
        if (field.isOpened && field.hasMine) {
            return State.M.toString();
        } else  if (field.isOpened) {
            return field.getNumber() +"";
        } else if (field.hasFlag) {
            return State.F.toString();
        } else {
            return "";
        }
    }

    
    
    public int getCols() {
        return cols;
    }

    
    
    public int getRows() {
        return rows;
    }

    
    
    public Field[] getGrid() {
        return grid;
    }
    
    
    
}
