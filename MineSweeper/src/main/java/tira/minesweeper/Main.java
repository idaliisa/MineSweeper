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
        
        
        //easy level
        System.out.println("Easy Level:");
        Performance performance10000e = new Performance(9, 9, 10, 10000);
        performance10000e.doCalculations(); 
        System.out.println(performance10000e.getFailed() + "% are failed");
        System.out.println(performance10000e.getSolved() + "% are solved");
        System.out.println(performance10000e.getTime() + " ms for " + performance10000e.getRepetition() + " games \n");
        
        
        //intermediate level
        System.out.println("Intermediate Level:");
        Performance performance10000i = new Performance(16, 16, 40, 10000);
        performance10000i.doCalculations(); 
        System.out.println(performance10000i.getFailed() + "% are failed");
        System.out.println(performance10000i.getSolved() + "% are solved");
        System.out.println(performance10000i.getTime() + " ms for " + performance10000i.getRepetition() + " games \n");
             
        
        //expert level         
        System.out.println("Expert Level:");
        Performance performance10000m = new Performance(16, 30, 99, 10000);
        performance10000m.doCalculations(); 
        System.out.println(performance10000m.getFailed() + "% are failed");
        System.out.println(performance10000m.getSolved() + "% are solved");
        System.out.println(performance10000m.getTime() + " ms for " + performance10000m.getRepetition() + " games \n");
        
        

    }    
}
