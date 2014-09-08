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
public abstract class BadSqlException extends RuntimeException{
    protected String location = "";
    protected String sql= "";
    
    public BadSqlException(String location, String sql){
        super("Suspected Dynamic Sql: site='" + location + "',sql='" + sql + "'");
        this.location= location;
        this.sql = sql;
        
    }
}
