/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.minesweeper.solver;

import tira.datastructures.CustomArrayList;
import tira.minesweeper.logic.Coordinate;
import tira.minesweeper.logic.Field;

/**
 *
 * @author ida
 */
public class Solver {
    
    /***
     * Finds block of unopened fields whose mine status is independent of unopened fields outside the block
     * @return Fields within the block
     */
    public CustomArrayList<Field> commonBlock() {
        CustomArrayList<Field> block = new CustomArrayList<>();
        //to-do
        return block;
    }    
}
