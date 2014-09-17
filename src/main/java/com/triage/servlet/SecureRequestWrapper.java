
package com.triage.servlet;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;

/**
 * This wrapper sanitizes any parameter values 
 * @author dcowden
 */
public class SecureRequestWrapper extends HttpServletRequestWrapper{

    public SecureRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String cleaned = super.getParameter(name); 
        cleaned = ESAPI.encoder().canonicalize(cleaned);
        cleaned = ESAPI.encoder().encodeForHTML(cleaned);
        //cleaned = ESAPI.encoder().encodeForJavaScript(cleaned);
        return cleaned;
    }
    
    
}
