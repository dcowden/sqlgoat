
package com.triage.servlet;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author dcowden
 */
public class SecureResponseWrapper extends HttpServletResponseWrapper{

    public SecureResponseWrapper(HttpServletResponse response) {
        super(response);
    }
    
}
