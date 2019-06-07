/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.minesweeper.solver;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import tira.datastructures.CustomArrayList;
import tira.minesweeper.logic.Board;
import tira.minesweeper.logic.Field;

/**
 *
 * @author ida
 */
public class Solver {
    public Board board;
    Stack<Field> safeFields;
    Stack<Field> questionFields;
    Field x;
    int xCoord, yCoord, rows, cols, mines, checked;
    Random random;

   
    public Solver(int rows, int cols, int mines) {
        this.safeFields = new Stack();
        this.questionFields = new Stack();
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        this.board = new Board(rows, cols);
        this.random = new Random();
        this.checked = 0;
        
    }
    
    public void init() {
        //init the board only after the first (random) click
        board.createBoard();
        int yCoord = random.nextInt(rows);
        int xCoord = random.nextInt(cols);
        x = board.getFieldAt(xCoord, yCoord);
        board.placeMines(mines, x);
        board.placeNumbers();
        
    }
    
    
    /**
     * Double Set Single Point algorithm. 
     * Algorithm starts with a random field. This field is added to safeFields. 
     * If randomly choosen field is a mine, the solver fails. Next the algortihm 
     * checks whether all neighbours are known to be free of mines. These are 
     * added to safeFields, otherwise these fields are added to questionFields. 
     * Now this SafeFields is iterated until it is empty. Then the algorithm moves to handle questionFields. 
     * For these fields, the algorithm first checks whether all the neighbours are 
     * known to be mines and sets flags if they are. This adds new information so
     * questionFields are iterated again to check whether all the neigbour mines are free 
     * of mines. After all this, the algorithm repeats the same from the beginning and 
     * at the beginning of the loop it selects a random field to safeField.
     * Here it may fail or continue until the game is solved.
     */
    public void DSSP() {
        //to-do: uses stack and arrayList at the moment. Change to hashSet and get rid of helper lists.
        
        safeFields.clear();
        questionFields.clear();
        
        //use a global variable x for the field that is under handling
        safeFields.add(x);
        
        while(!board.isSolved()) {
            
            //add a random field to safeFields
            if (safeFields.isEmpty()) {
                x = getRandomField(getUnknowns());
                safeFields.add(x);
            }
            //open safeFields and stop if randomly selected field is mine
            while (!safeFields.isEmpty()) {
                x  = safeFields.pop();
                board.openOneField(x);
                checked++;
                if (x.hasMine()) {
                    return;
                }
            
            //check whether new safeFields are found
            xCoord = x.getCoordinate().x;
            yCoord = x.getCoordinate().y;
            if (isAFN(xCoord, yCoord)) {
                ArrayList<Field> unFlagged = getUnFlaggeddNeighbours(xCoord, yCoord);
                for (int i = 0; i < unFlagged.size(); i++) {
                    if (!safeFields.contains(unFlagged.get(i))) {
                        safeFields.add(unFlagged.get(i));
                    }                          
                }
            } else {
                if (!questionFields.contains(x)) {
                    questionFields.add(x);
                }                        
            }
               

            }

            //helper list
            ArrayList<Field> toBeRemoved = new ArrayList<>();
            //check questionFields if all the neigbours can be flagged
            for (int i = 0; i < questionFields.size(); i++) {
                x = questionFields.get(i);
                xCoord = x.getCoordinate().x;
                yCoord = x.getCoordinate().y;
                if (isAMN(xCoord, yCoord)) {
                    ArrayList<Field> unFlagged = getUnFlaggeddNeighbours(xCoord, yCoord);
                    for (int j = 0; j < unFlagged.size(); j++) {
                        board.setFlag(unFlagged.get(j));
                        checked++;
                    }
                    toBeRemoved.add(x);
                }
            }
            //use heldeper list
            for (int i = 0; i < toBeRemoved.size(); i++) {
                questionFields.remove(toBeRemoved.get(i));
            }
            //empty helper list
            toBeRemoved.clear();

            //check questionFields if some of them are now safe to open
            for (int i = 0; i < questionFields.size(); i++) {
                x = questionFields.get(i);
                xCoord = x.getCoordinate().x;
                yCoord = x.getCoordinate().y;
                if (isAFN(xCoord, yCoord)) {
                    ArrayList<Field> unFlagged = getUnFlaggeddNeighbours(xCoord, yCoord);
                    for (int j = 0; j < unFlagged.size(); j++) {
                        safeFields.add(unFlagged.get(j));
                    }
                    toBeRemoved.add(x);
                }
            }
            //use helper list
            for (int i = 0; i < toBeRemoved.size(); i++) {
                questionFields.remove(toBeRemoved.get(i));
            }
            //empty helper list
            toBeRemoved.clear();
            
            
        } 
       
    }
       

    /**
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @return true if all the neighbours are know to be free of mines
     */
    public boolean isAFN(int x, int y) {
        if (effectiveNumber(x, y) == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    
    /**
     * 
     * @param x x coordiante
     * @param y y coordinate
     * @return true if all the neigbours are known to be mines
     */
    public boolean isAMN(int x, int y) {
        if (effectiveNumber(x, y) == getUnFlaggeddNeighbours(x, y).size()) {
            return true;
        } else {
            return false;
        }
    }
    
    
    /**
     * 
     * @param x x coordiante
     * @param y y coordiante
     * @return list of closed neighbours that are unflagged
     */
    public ArrayList<Field> getUnFlaggeddNeighbours(int x, int y) {
        ArrayList<Field> neigbours = board.getNeighbours(board.getFieldAt(x, y));
        ArrayList<Field> unFlaggedNeigbours = new ArrayList<>();
        for (int i = 0; i < neigbours.size(); i++) {
            Field n = neigbours.get(i);
            if (!n.isOpened() && !n.hasFlag()) {
                unFlaggedNeigbours.add(n);
            }
        }
        return unFlaggedNeigbours;
    }
   
    
    /**
     * 
     * @param x x-coordinate
     * @param y y-coordiante
     * @return how many neighbour fields should be flagged still
     */
    public int effectiveNumber(int x, int y) {
        return  board.getFieldAt(x, y).getNumber() - getFlaggedCount(x, y);
    }
    
    
    /**
     * 
     * @param x x coordiante
     * @param y y coordinate
     * @return count of neighbour fields that are flagged
     */
    public int getFlaggedCount(int x, int y) {
        int flagged = 0;
        ArrayList<Field> neigbours = board.getNeighbours(board.getFieldAt(x, y));
        for (int i = 0; i < neigbours.size(); i++) {
            Field n = neigbours.get(i);
            if (!n.isOpened() && n.hasFlag()) {
                flagged++;
            }
        }
        return flagged;
    }
    
       
    /**
     * Iterates the whole board and lists all the fields that are still 
     * closed and unflagged
     * @return 
     */
    public ArrayList<Field> getUnknowns() {
        ArrayList<Field> unknowns = new ArrayList<>();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                Field f = board.getFieldAt(x, y);
                if (!f.isOpened() && !f.hasFlag()) {
                    unknowns.add(f);
                }
            }
        }
        return unknowns;
    }
    
    
    /**
     * 
     * @param unknowns
     * @return random field amongst unkown fields
     */
    public Field getRandomField(ArrayList<Field> unknowns) {
        Random r = new Random();
        int idx = r.nextInt(unknowns.size());
        return unknowns.get(idx);
    }
    
    
}
