
package com.triage.servlet;

import com.triage.rulehelpers.ProbableSqlInjectionException;
import com.triage.sqlparse.SqlParse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.owasp.esapi.ESAPI;
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
        return  this.cleanParameterValue(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        return cleanParameterValues(super.getParameterValues(name)); 
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        // a faster way would be to return a subclass that will do this cleaning 
        //lazily, but i think this is safer
        Map<String,String[]> allParameters = super.getParameterMap();
        Map<String,String[]> cleanedParameters = new HashMap<String,String[]>();
        for(Map.Entry<String,String[]> entry: allParameters.entrySet() ){
            cleanedParameters.put(entry.getKey(),cleanParameterValues(entry.getValue()));
        }
        return cleanedParameters;
    }
    
    protected String[] cleanParameterValues(String[] parameterValues){
        List<String> cleaned = new ArrayList<String>();
        for ( String pV: parameterValues){
            cleaned.add(cleanParameterValue(pV));
        }
        return cleaned.toArray(new String[cleaned.size() ]);
    }
    
    protected String cleanParameterValue(String paramValue){
        String cleaned = ESAPI.encoder().canonicalize(paramValue);        
        cleaned = cleaned.replaceAll("\0","");
        
        if ( SqlParse.isSQLi(cleaned)) {            
            throw new ProbableSqlInjectionException(cleaned);
        }
        
        return Jsoup.clean(cleaned, Whitelist.none()); //simpleText() might be even better.        
    }
    
}
