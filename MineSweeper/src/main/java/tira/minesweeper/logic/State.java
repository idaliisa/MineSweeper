/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.minesweeper.logic;

/**
 *
 * @author ida
 */
/**
 * 
 * Field state where M=mine, F=flagged, NUMBER=neighbour mine count, CLOSED = unopened 
 */
public enum State {
    //to-do: refactor
    M,
    F,
    NUMBER,
    CLOSED;
    
}
