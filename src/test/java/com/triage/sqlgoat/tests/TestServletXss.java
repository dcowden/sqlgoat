package com.triage.sqlgoat.tests;

import com.triage.sqlgoat.servlet.EchoServlet;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.jboss.byteman.contrib.bmunit.BMNGListener;
import org.jboss.byteman.contrib.bmunit.BMScript;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
/**
 *
 * @author dcowden
 */
@Listeners(BMNGListener.class)
public class TestServletXss {
    
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
    public void testNoInjection() throws Exception{        
        HttpTester response = makeServletRequest(TEST_TEXT);
      
        assertEquals(200,response.getStatus());
        assertEquals(TEST_TEXT,response.getContent());
    } 
    
    @Test
    public void testBasicInjection() throws Exception{
        
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
        String XSS_IMAGE = "<img src=\"a\" onError=\"alert('Xss');\">";
        HttpTester response = makeServletRequest(XSS_IMAGE);
        
        assertEquals(200,response.getStatus());
        assertEquals("&lt;img src&#x3d;&quot;a&quot; onError&#x3d;&quot;alert&#x28;&#x27;Xss&#x27;&#x29;&#x3b;&quot;&gt;",response.getContent());
        
    }
}
