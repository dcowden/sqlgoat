/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat.tests;

import com.triage.config.Configuration;
import com.triage.rulehelpers.DynamicSqlException;
import com.triage.rulehelpers.ProbableSqlInjectionException;
import com.triage.sqlgoat.database.BasicDynamic;
import com.triage.sqlgoat.database.BasicPrepared;
import com.triage.sqlgoat.database.Result;
import com.triage.sqlgoat.database.UserSelector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.jboss.byteman.contrib.bmunit.BMNGListener;
import org.jboss.byteman.contrib.bmunit.BMRule;
import org.jboss.byteman.contrib.bmunit.BMScript;
import static org.testng.Assert.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author dcowden
 */
@Listeners(BMNGListener.class)
public class SimpleSqlInjectionTest extends BaseTest{
    
    public SimpleSqlInjectionTest() {
    }
    
    protected void listUsersForParameters(UserSelector selector,List<String> userList){
        Result results = null;
        for (String user: userList){
            results= selector.listUsers(this.getDataSource(), user );
        }        
    }
    
    @Test(expectedExceptions=DynamicSqlException.class)
    @BMScript(value="injectionDetector",dir="target/test-classes/btmrules")
    public void testBadDynamicSql(){
        //we need two invocations to detect a problem, so we'll run it twice
        //this should produce identical results both times, and thus should pass
        //the assumption here is that the application will run correctly at least once
        //without injection.
        Configuration.active.setAllowDynamicSql(false);
        listUsersForParameters(new BasicDynamic(), Arrays.asList(new String[]{ "1","1 OR 3=3"}));
        
    }    
    
    @Test(expectedExceptions=DynamicSqlException.class)
    @BMScript(value="injectionDetector",dir="target/test-classes/btmrules")
    public void testNormalDynamicSqlValid(){
        Configuration.active.setAllowDynamicSql(false);
        listUsersForParameters(new BasicDynamic(), Arrays.asList(new String[]{ "1","3"}));
    }   
    
    @Test(expectedExceptions=ProbableSqlInjectionException.class)
    @BMScript(value="injectionDetector",dir="target/test-classes/btmrules")
    public void testNormalDynamicSqlInvalid(){
        Configuration.active.setAllowDynamicSql(true);
        listUsersForParameters(new BasicDynamic(), Arrays.asList(new String[]{ "1","1 OR 3=3"}));
    }   
    
    @Test
    @BMScript(value="injectionDetector",dir="target/test-classes/btmrules")
    public void testNormalPreparedStatements(){
        Configuration.active.setAllowDynamicSql(false);
        listUsersForParameters(new BasicPrepared(), Arrays.asList(new String[]{ "1","3"}));
    }  
    
    @Test(expectedExceptions=RuntimeException.class)
    @BMScript(value="injectionDetector",dir="target/test-classes/btmrules")
    public void testBadPreparedStatements(){
        Configuration.active.setAllowDynamicSql(true);
        listUsersForParameters(new BasicPrepared(), Arrays.asList(new String[]{ "1","1 OR 3=3"}));
    }      
}
