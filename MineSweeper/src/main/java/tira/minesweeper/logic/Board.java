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
    //ArrayList<Coordinate> mines;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Field[rows][cols];
        createBoard();
        //this.mines = new ArrayList<>();
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
     * Set mines after createBoard()
     * @param mineCount 
     */
    public void placeMines(int mineCount) {
        Random r = new Random();

        int mineCounter = 0;
                
        while (mineCounter < mineCount) {
            int y = r.nextInt(rows);
            int x = r.nextInt(cols);
            
            if (getFieldAt(x, y).hasMine == false) {
                Coordinate c = new Coordinate(x, y);
                getFieldAt(x, y).hasMine = true;
                mineCounter++;
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
            if (field.number == 0) {
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
     
    
    
}
