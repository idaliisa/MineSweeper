/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.minesweeper.logic;


import tira.datastructures.CustomArrayList;
import tira.util.Random;


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

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Field[rows * cols];
        this.flaggedMines = 0;
        this.openedFields = 0;
        createBoard();
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
        //use arraylist to store all possible fields that random can access
        Random r = new Random();
        CustomArrayList fields = new CustomArrayList();
        for (int i = 0; i < (grid.length); i++) {
            fields.add(grid[i]);
        }
        totalMines = 0;
                
        while (totalMines < mineCount) {
            int index = r.nextInt(fields.size());
            Field f = (Field) fields.get(index);
            
            if (f.hasMine == false && !f.equals(firstClick) && !getNeighbours(firstClick).contains(f)) {
                f.hasMine = true;
                totalMines++;
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
    public CustomArrayList<Field> getNeighbours(Field f) {
        CustomArrayList<Field> neighbours = new CustomArrayList<>();

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
                CustomArrayList<Field> neighbours = getNeighbours(getFieldAt(x, y));
                for (int i = 0; i < neighbours.size(); i++) {
                    Field n = neighbours.get(i);
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
        CustomArrayList<Field> neighbours = getNeighbours(field);
        for (int i = 0; i < neighbours.size(); i++) {
            Field n = neighbours.get(i);
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
        int mineFree = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if ((getFieldAt(x, y).isOpened()) && !getFieldAt(x, y).hasMine()) {
                    mineFree++;
                }
            }
        }
        if ((size() - mineFree) == totalMines) {
            return true;
        }
        return false;
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

    
    
    
    public int size() {
        return rows * cols;
    }

    
    
}
