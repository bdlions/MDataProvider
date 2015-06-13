/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alamgir
 */
public class LogWriterTest {
    
    public LogWriterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void main(){
        LogWriter.getDebugLog().debug("debug message");
        LogWriter.getErrorLog().error("debug message");
        System.out.println("dfsdf");
    }
}