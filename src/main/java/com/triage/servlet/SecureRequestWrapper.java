
package com.triage.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.owasp.esapi.ESAPI;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
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
        cleaned = cleaned.replaceAll("\0","");
        //cleaned = ESAPI.encoder().encodeForHTML(cleaned);
        //cleaned = ESAPI.encoder().encodeForJavaScript(cleaned);
        return Jsoup.clean(cleaned, Whitelist.none()); //simpleText() might be even better.
    }
    
    
}
