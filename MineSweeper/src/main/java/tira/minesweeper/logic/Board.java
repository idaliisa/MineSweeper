/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.minesweeper.logic;


import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author ida
 */
/**
 * 
 * Minesweeper Board where X increases left to right and y top to bottom
 */
public class Board {
    public Field[][] grid;
    int rows;
    int cols;
    int totalMines;
    int flaggedMines;
    int openedFields;
    //ArrayList<Coordinate> totalMines;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Field[rows][cols];
        this.flaggedMines = 0;
        this.openedFields = 0;
        createBoard();
        //this.totalMines = new ArrayList<>();
    }
    

    public void createBoard() { 
        //to-do: select the first square before creating the board
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                Coordinate c = new Coordinate(x, y);
                grid[y][x] = new Field(c);
            }
        }
    }

    /**
     * Set totalMines after createBoard()
     * @param mineCount 
     */
    public void placeMines(int mineCount) {
        Random r = new Random();

        totalMines = 0;
                
        while (totalMines < mineCount) {
            int y = r.nextInt(rows);
            int x = r.nextInt(cols);
            
            if (getFieldAt(x, y).hasMine == false) {
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
        return grid[y][x];
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
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                ArrayList<Field> neighbours = getNeighbours(grid[y][x]);
                for (Field n : neighbours) {
                    if (n.hasMine) {
                        grid[y][x].number++;
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
        for (Field n : neighbours) {
            if (inBounds(field.coordinate) && !n.isOpened) {
                openField(n);
            }
        }
    }
    
    
    
    
    //to-do: open all neighbours if flaggedNeigboursCount equals to neighboursMineCount
    
    
    
    public void setMine(Field field) {
        if (field.hasMine == false) {
            totalMines++;
        }
        field.hasMine = true;
    }
    
    
    
    public void setFlag(Field field) {
        if (field.hasFlag == false) {
            flaggedMines++;
        }
        field.hasFlag = true;
    }
    
    
    
    public void removeFlag(Field field) {
        if (field.hasFlag == true) {
            flaggedMines--;
        }
        field.hasFlag = false;
    }
    

    
    public int numberUnfoundMines() {
        return totalMines - flaggedMines;
    }
    
    
    
    public boolean isFailed(int x, int y) {
        if (getFieldAt(x, y).hasMine && getFieldAt(x, y).isOpened) {
            return true;
        } else {
            return false;
        }        
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
            return State.MINE.toString();
        } else  if (field.isOpened) {
            return field.getNumber() +"";
        } else if (field.hasFlag) {
            return State.FLAG.toString();
        } else {
            return "";
        }
    }
    
    
}
