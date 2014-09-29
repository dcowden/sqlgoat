/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.rulehelpers;

/**
 *
 * @author dcowden
 */
public class ProbableSqlInjectionException extends BadSqlException{

    public ProbableSqlInjectionException(String location,String sql) {
        super(location,sql);
    }
    
    public ProbableSqlInjectionException( String sql){
        super(sql);
    }
    
}    

