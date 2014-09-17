/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.rulehelpers;

import com.triage.servlet.SecureRequestWrapper;
import com.triage.servlet.SecureResponseWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.byteman.rule.Rule;
import org.jboss.byteman.rule.helper.Helper;

/**
 *
 * @author dcowden
 */
public class ServletWrapperHelper extends Helper{
    public ServletWrapperHelper(Rule rule){
        super(rule);
    }   
    
    public HttpServletRequest wrapRequest(HttpServletRequest request){
        return new SecureRequestWrapper(request);
    }
    public HttpServletResponse wrapResponse(HttpServletResponse response){
        return new SecureResponseWrapper(response);
    }    
}
