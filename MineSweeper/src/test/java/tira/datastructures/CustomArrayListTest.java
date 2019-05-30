/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira.datastructures;

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
public class CustomArrayListTest {
    
    CustomArrayList customArrayList;
    
    @Before
    public void setUp() {
        customArrayList = new CustomArrayList();
        customArrayList.add("0");
        customArrayList.add("1");
        customArrayList.add("2");
    }
    
    
    @Test
    public void addsCorrectlytoCustomArrayList1() {
        assertEquals("1", customArrayList.get(1).toString());
    }    
    @Test
    public void addsCorrectlytoCustomArrayList2() {
        customArrayList.add("3");
        customArrayList.add("4");
        customArrayList.add("5");
        customArrayList.add("6");
        customArrayList.add("7");
        customArrayList.add("8");
        customArrayList.add("9");
        customArrayList.add("10");
        customArrayList.add("11");
        assertEquals("5", customArrayList.get(5).toString());
    }
    @Test
    public void addsCorrectlytoCustomArrayList3() {
        customArrayList.add("3");
        customArrayList.add("4");
        customArrayList.add("5");
        customArrayList.add("6");
        customArrayList.add("7");
        customArrayList.add("8");
        customArrayList.add("9");
        customArrayList.add("10");
        customArrayList.add("11");
        assertEquals("11", customArrayList.get(11).toString());
    }

}
