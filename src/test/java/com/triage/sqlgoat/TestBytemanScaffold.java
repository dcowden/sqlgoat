/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat;

import com.triage.sqlgoat.userselectors.Result;
import org.jboss.byteman.contrib.bmunit.BMNGListener;
import org.jboss.byteman.contrib.bmunit.BMRule;
import org.jboss.byteman.contrib.bmunit.BMScript;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.TimeZone;
import com.triage.rulehelpers.TestHelper;
/**
 *
 * @author dcowden
 */
@Listeners(BMNGListener.class)
public class TestBytemanScaffold {

    @Test
    public void testNoMods(){        
        String name = TimeZone.getDefault().getDisplayName();
    }
    
    @Test(expectedExceptions=RuntimeException.class)
    @BMRule(name="throw exception for PreparedStatement",
            targetClass="java.util.TimeZone",
            targetMethod="getDisplayName",
            action="throw new RuntimeException(\"ha ha\")")
    public void testInlineBytemanRule(){
        String name = TimeZone.getDefault().getDisplayName();
    }
    
    @Test(expectedExceptions=RuntimeException.class)
    @BMScript(value="scaffoldbasictest",dir="target/test-classes/btmrules")
    public void testBytemanRuleFromFile(){
        String name = TimeZone.getDefault().getDisplayName();
    }    
    
    @Test
    @BMScript(value="scaffoldhelpertest",dir="target/test-classes/btmrules")
    public void testBytemanRuleFromFileWithHelper(){
        String name = TimeZone.getDefault().getDisplayName();
        assertEquals(TestHelper.TEST_TEXT,name);
    }         
}
