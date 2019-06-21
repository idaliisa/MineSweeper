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
 * Minesweeper Board where X increases left to right and Y top to bottom. Implements
 * createBoard, placeMines, inBounds, getFieldAt, getNeighbours, placeNumbers, 
 * openField, openOneField, setMine, setFlag, removeFlag, numberUnfoundMines, isFailed,
 * isFailedAt,isSolved, getState, size.
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
    
    
    /**
     * Converts 2D-grid to 1D
     * @param x x-coordinate
     * @param y y-coordinate
     * @return 
     */
    private int twoDtoOneD(int x, int y) {
        return x + (y * cols);
    }

    
    /**
     * Adds fields to the game board.
     */   
    public void createBoard() { 
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                Coordinate c = new Coordinate(x, y);
                grid[twoDtoOneD(x, y)] = new Field(c);
            }
        }
    }

           
    /**
     * Place mines randomly after createBoard() and the first click. First click 
     * is not mine.
     * @param mineCount total number of mines
     * @param firstClick the first click
     */
    public void placeMines(int mineCount, Field firstClick) {
        //restore fields to arrayList and select random field from the list later      
        CustomArrayList fields = new CustomArrayList();
        for (int i = 0; i < (grid.length); i++) {
            fields.add(grid[i]);
        }
        Random r = new Random(); 
        totalMines = 0;
        //place mines to random positions
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
    private boolean inBounds(Coordinate coordinate) {
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
     * Lists all neighbour fields.
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
     * Counts mines at neighbour fields.
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
     * Opens field and if number at specified field is zero, opens neighbours
     * recursively.
     * @param field field at specified position
     */
    public void openField(Field field) {
        if (inBounds(field.coordinate) && !field.isOpened) {
            field.isOpened = true;
            openedFields++;
            //to-do:refactor
            if (field.number == 0 && !isFailedAt(field.coordinate.x, field.coordinate.y)) {
                openNeighbours(field);        
            }
        }
    }
    
    
    /**
     * Help method for openField.
     * @param field 
     */
    private void openNeighbours(Field field) {
        CustomArrayList<Field> neighbours = getNeighbours(field);
        for (int i = 0; i < neighbours.size(); i++) {
            Field n = neighbours.get(i);
            if (inBounds(field.coordinate) && !n.isOpened) {
                openField(n);
            }
        }
    }
    
    
    /**
     * Opens one field only.
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
     * Sets mine. Increases number of total mines accordingly.
     * @param field 
     */
    public void setMine(Field field) {
        if (field.hasMine == false) {
            totalMines++;
        }
        field.hasMine = true;
    }
    
    
    /**
     * Sets flag. Increases number of flagged mines accordingly.
     * @param field 
     */
    public void setFlag(Field field) {
        if (field.hasFlag == false) {
            flaggedMines++;
        }
        field.hasFlag = true;
    }
    
    
    /**
     * Removes flag. Decreased number of flagged mines accordingly.
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
     * @return number of mines that are not flagged
     */
    public int numberUnfoundMines() {
        return totalMines - flaggedMines;
    }
    
    
    /**
     * Game is failed if the specified field is opened and has mine.
     * @param x x-coordiante
     * @param y y-coordinate
     * @return true if mine is opened i.e if game fails
     */
    public boolean isFailedAt(int x, int y) {
        if (getFieldAt(x, y).hasMine && getFieldAt(x, y).isOpened) {
            return true;
        } else {
            return false;
        }        
    }
    
    
    /**
     * Iterates the game board and Game is failed if mine is opened.
     * @return true if game fails
     */
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
    
    
    /**
     * Iterates the game board and game is solved if all fields are opened that have not mine.
     * @return true if game is solved
     */
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
     * @param field specified field
     * @return field state by the highest priority first: mine, number, flag or closed
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

    
    
    /**
     * 
     * @return number of fields in the game board
     */
    public int size() {
        return rows * cols;
    }

    
    
}
