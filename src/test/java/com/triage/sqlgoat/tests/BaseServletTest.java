/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat.tests;

import com.triage.sqlgoat.servlet.EchoServlet;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author dcowden
 */
public class BaseServletTest {

    protected ServletTester servletTester;
    public static final String TEST_TEXT = "foo";
    
    @BeforeMethod
    public void setupServlet() throws Exception{
        servletTester = new ServletTester();
        servletTester.addServlet(EchoServlet.class, "/echo");
        servletTester.setContextPath("/");
        servletTester.start();        
    }
    
    @AfterMethod
    public void tearDown() throws Exception{
        servletTester.stop();
    }
    
    protected String makeUrl(String paramValue) throws UnsupportedEncodingException{
        return "/echo?" + EchoServlet.TEST_PARAM + "=" + URLEncoder.encode(paramValue, "UTF-8");
    }
    
    protected HttpTester makeServletRequest(String paramValue) throws Exception{
        HttpTester request = makeTestRequest(paramValue);
        HttpTester response = new HttpTester();
        response.parse(servletTester.getResponses(request.generate()));        
        return response;
    }
    
    protected HttpTester makeTestRequest(String paramValue) throws UnsupportedEncodingException{
        HttpTester request = new HttpTester();
        request.setMethod("GET");
        request.setVersion("HTTP/1.1");
        request.setURI(makeUrl(paramValue));
        request.setHeader("host", "tester");
        return request;
    }    

    @Test
    public void testThatServletEchos() throws Exception{        
        HttpTester response = makeServletRequest(TEST_TEXT);      
        assertEquals(200,response.getStatus());
        assertEquals(TEST_TEXT,response.getContent());
    }     
    
}
