/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.minesweeper.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ida
 */
public class FieldTest {
    
    Field field;
   
    @Before
    public void setUp() {
        field = new Field(new Coordinate(0, 0));
    }

    @Test
    public void equalsFieldsWorks1() {
        assertTrue(field.equals(new Field(new Coordinate(0, 0))));
    }
    @Test
    public void equalsFieldsWorks2() {
        assertFalse(field.equals(new Field(new Coordinate(1, 0))));
    }
    
    @Test
    public void notEqualWhenClassesMismatch() {
        assertFalse(field.equals(new Field(new Coordinate(1, 0)).toString()));
    }
    
    @Test
    public void notEqualWhenParameterIsNull() {
        Field f = null;
        assertFalse(field.equals(f));
    }
}
