/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat.tests;


import com.triage.rulehelpers.DynamicSqlException;
import com.triage.rulehelpers.ProbableSqlInjectionException;
import org.jboss.byteman.contrib.bmunit.BMNGListener;
import org.jboss.byteman.contrib.bmunit.BMScript;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author dcowden
 */
@Listeners(BMNGListener.class)
public class SimpleSqlInjectionTest extends BaseTest{
    
    //@Test(expectedExceptions=DynamicSqlException.class)
    //@BMScript(value="dynamicSqlDetector",dir="target/test-classes/btmrules")
    public void testBadDynamicSql(){
        //we need two invocations to detect a problem, so we'll run it twice
        //this should produce identical results both times, and thus should pass
        //the assumption here is that the application will run correctly at least once
        //without injection.
        this.userService.listUsersUnsafe("1");
        this.userService.listUsersUnsafe("2 OR 3=3");
        
    }    
    
    //@Test(expectedExceptions=DynamicSqlException.class)
    //@BMScript(value="dynamicSqlDetector",dir="target/test-classes/btmrules")
    public void testNormalDynamicSqlValid(){
        this.userService.listUsersUnsafe("1");
        this.userService.listUsersUnsafe("2 OR 3=3");
    }   
    
    //@Test(expectedExceptions=ProbableSqlInjectionException.class)
    //@BMScript(value="injectionDetector",dir="target/test-classes/btmrules")
    public void testNormalDynamicSqlInvalid(){
        this.userService.listUsersUnsafe("1");
        this.userService.listUsersUnsafe("2 OR 3=3");
    }   
    
    //@Test
    //@BMScript(value="injectionDetector",dir="target/test-classes/btmrules")
    public void testNormalPreparedStatements(){
        this.userService.listUsersSafe("1");
        this.userService.listUsersSafe("3");
    }  
    
    //@Test(expectedExceptions=RuntimeException.class)
    //@BMScript(value="injectionDetector",dir="target/test-classes/btmrules")
    public void testBadPreparedStatements(){
        this.userService.listUsersUnsafe("1");
        this.userService.listUsersUnsafe("2 OR 3=3");
    }      
}
