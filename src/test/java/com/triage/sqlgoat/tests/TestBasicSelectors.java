/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat.tests;

import java.sql.SQLException;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

/**
 * Tests that our basic mock selectors behave as expected
 * @author dcowden
 */
public class TestBasicSelectors extends BaseTest{
    
    public TestBasicSelectors() {
    }
    
    @Test
    public void testBasicPreparedWithOutInjection(){
        assertEquals(1,this.userService.listUsersSafe("1").size());
    }
    
    @Test(expectedExceptions=RuntimeException.class)
    public void testBasicPreparedWithInjection(){
        assertEquals(1,this.userService.listUsersSafe("1 OR 3=3").size());
    }
    
    @Test
    public void testBasicDynamicWithOutInjection(){
        assertEquals(1,this.userService.listUsersUnsafe("1").size());
    }
    
    @Test
    public void testBasicDynamicWithInjection(){
        assertEquals(3,this.userService.listUsersUnsafe("1 OR 3=3").size());
    }    
 
}
