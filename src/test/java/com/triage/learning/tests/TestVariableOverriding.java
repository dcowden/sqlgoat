/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.learning.tests;

import com.triage.learning.Foo;
import com.triage.learning.FooImpl;
import com.triage.learning.FooProcessor;
import com.triage.learning.FooWrapper;
import org.jboss.byteman.contrib.bmunit.BMNGListener;
import org.jboss.byteman.contrib.bmunit.BMScript;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Tests Injecting wrapped instances into a method-- IE, like transforming
 * request and resonse inside of the servlet service method
 * @author dcowden
 */
@Listeners(BMNGListener.class)
public class TestVariableOverriding {


    @Test
    public void testNoOverride(){
        FooProcessor p = new FooProcessor();
        assertEquals(Foo.RESPONSE, p.proccess(new FooImpl() ));
    }
    
    @Test
    @BMScript(value="fooproccessor",dir="target/test-classes/btmrules")
    public void testOverride(){
        FooProcessor p = new FooProcessor();
        assertEquals(Foo.WRAPPED_RESPONSE, p.proccess(new FooImpl() ));
    }    
    
}
