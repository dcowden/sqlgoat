package com.triage.sqlgoat.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is a deliberately vulnerable servlet for XSS and other testing.  This servlet is subject to these attacks:
 *   XSS
 *   HTTP Splitting
 *   CSRF
 *   
 * @author dcowden
 */
public class EchoServlet extends HttpServlet{
    public static final String TEST_PARAM = "testParam";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().print(req.getParameter(TEST_PARAM));        
    }
    
}
