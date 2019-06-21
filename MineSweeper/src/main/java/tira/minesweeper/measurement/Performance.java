/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.minesweeper.measurement;

import tira.minesweeper.solver.Solver;

/**
 *
 * @author ida
 */
public class Performance {
    private int rows,cols, mines;
    private int failed, solved;
    private int repetition;
    private long time;
    private Solver solver;

    public Performance(int rows, int cols, int mines, int repetitions) {
        
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        this.repetition = repetitions;
        this.failed = 0;
        this.solved = 0;
        this.time = 0;
    
    }
    
    public void doCalculations() {
        long startTime = System.currentTimeMillis();
        
        int i = 0;
        
        while (i < repetition) {
            solver = new Solver(rows, cols, mines);
            solver.init();
        
            while (!solver.board.isFailed() && !solver.board.isSolved()) {
                solver.DSSP();
            }

            if (solver.board.isFailed()) {
                failed++;
            }
            if (solver.board.isSolved()) {
                solved++;
            }
            
            i++;
        }

        long endTime = System.currentTimeMillis();
        
        time = endTime - startTime;
    }

    
    
    public double getFailed() {
        return  failed * 100.0 / getRepetition();
    }

    
    
    public double getSolved() {
        return solved * 100.0 / getRepetition();
    }

    
    
    public long getTime() {
        return time;
    }

    
    
    public int getRepetition() {
        return repetition;
    }
     
         
        
}
