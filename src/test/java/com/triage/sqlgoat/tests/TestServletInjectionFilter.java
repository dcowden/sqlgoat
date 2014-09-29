/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat.tests;

import static com.triage.sqlgoat.tests.BaseServletTest.TEST_TEXT;
import org.eclipse.jetty.testing.HttpTester;
import org.jboss.byteman.contrib.bmunit.BMNGListener;
import org.jboss.byteman.contrib.bmunit.BMScript;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author dcowden
 */
@Listeners(BMNGListener.class)
public class TestServletInjectionFilter extends BaseServletTest{
    
    
    @Test
    public void testSqlIFlaw() throws Exception{        
        String badSQL = "675; DROP TABLE whatever";
        HttpTester response = makeServletRequest(badSQL);
      
        assertEquals(200,response.getStatus());
        assertEquals(badSQL,response.getContent());
    } 
    
    
    @Test
    @BMScript(value="sqlInjectionFilter",dir="target/test-classes/btmrules")
    public void testXssProtection() throws Exception{
        String badSQL = "675; DROP TABLE whatever";        
        HttpTester response = makeServletRequest(badSQL);
        
        assertEquals(500,response.getStatus());
        
    }    
}
