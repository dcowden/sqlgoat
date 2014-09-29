/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat.tests;

import com.triage.sqlgoat.database.UserService;
import java.util.List;
import java.util.Map;


import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author dcowden
 */
public class NormalExecutionTest extends BaseTest{
    

    public NormalExecutionTest() {
    }
    
    @Test
    public void testSetup(){
        
    }
    
    @Test
    public void testBasicBad(){
        List<Map<String,Object>> results = this.userService.listUsersUnsafe(UserService.DAVE);
        assertEquals(results.size(),1);

        assertEquals(results.get(0).get("firstname"),"Dave");
    }
    
}
