/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat;

import com.triage.sqlgoat.userselectors.BasicDynamic;
import com.triage.sqlgoat.userselectors.BasicPrepared;
import com.triage.sqlgoat.userselectors.Result;
import com.triage.sqlgoat.userselectors.UserSelector;
import java.sql.SQLException;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

/**
 * Tests that our basic mock selectors behave as expected
 * @author dcowden
 */
public class TestBasicSelectors extends BaseTest{
    
    public TestBasicSelectors() {
    }

    protected int verifyUserCount( UserSelector selector, String id){
        Result results = selector.listUsers(this.getDataSource(), id);
        return results.getSize();
    }
 

    @Test
    public void testBasicPreparedWithOutInjection(){
        assertEquals(1,verifyUserCount(new BasicPrepared(),"1"));
    }
    
    @Test(expectedExceptions=RuntimeException.class)
    public void testBasicPreparedWithInjection(){
        assertEquals(1,verifyUserCount(new BasicPrepared(),"1 OR 3=3"));
    }
    
    @Test
    public void testBasicDynamicWithOutInjection(){
        assertEquals(1,verifyUserCount(new BasicDynamic(),"1"));
    }
    
    @Test
    public void testBasicDynamicWithInjection(){
        assertEquals(3,verifyUserCount(new BasicDynamic(),"1 OR 3=3"));
    }    
 
}
