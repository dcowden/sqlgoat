package com.triage.sqlgoat.tests;

import org.eclipse.jetty.testing.HttpTester;
import org.jboss.byteman.contrib.bmunit.BMNGListener;
import org.jboss.byteman.contrib.bmunit.BMScript;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
/**
 *
 * @author dcowden
 */
@Listeners(BMNGListener.class)
public class TestServletXss extends BaseServletTest{
  
    @Test
    public void testBasicInjectionFlawIsThere() throws Exception{
        
        //this response allows unchecked XSS input
        String XSS_IMAGE = "<img src=\"a\" onError=\"alert('Xss');\">";
        HttpTester response = makeServletRequest(XSS_IMAGE);
        
        assertEquals(200,response.getStatus());
        assertEquals(XSS_IMAGE,response.getContent());        
    }
    
    @Test
    @BMScript(value="xssFilter",dir="target/test-classes/btmrules")
    public void testXssProtection() throws Exception{
        //this response allows unchecked XSS input
        String XSS_IMAGE = "TESTVALUE<img src=\"a\" onError=\"alert('Xss');\">";
        HttpTester response = makeServletRequest(XSS_IMAGE);
        
        assertEquals(200,response.getStatus());
        assertEquals("TESTVALUE",response.getContent());
        
    }
}
