/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this content content, choose Tools | Templates
 * and open the content in the editor.
 */
package tira.minesweeper;

import tira.minesweeper.measurement.Performance;




/**
 *
 * @author ida
 */
public class Main {
    
    public static void main(String[] args) {
        //to-do: rows,cols and mines are hardcoded, ask rather from user.
        int rows = 16;
        int cols = 16;
        int mines = 40;
        
        Performance performance = new Performance(rows, cols, mines);
        performance.doCalculations();
        double failed = performance.getFailed();
        double solved = performance.getSolved();
        int repetitions = performance.getRepetition();
        long execTime = performance.getTime();
        
        //there is a bug 
        System.out.println(failed + "% are failed");
        System.out.println(solved + "% are solved");
        System.out.println(execTime + " ms for " + repetitions + " games");
                
    }    
}
