/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat;

import com.triage.sqlgoat.userselectors.BasicDynamic;
import com.triage.sqlgoat.userselectors.Result;
import com.triage.sqlgoat.userselectors.UserSelector;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.hsqldb.jdbc.JDBCDataSource;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author dcowden
 */
public class NormalExecutionTest extends BaseTest{
    

    public NormalExecutionTest() {
    }
    
    @Test
    public void testSetup(){
        
    }
    
    @Test
    public void testBasicBad(){
        BasicDynamic b = new BasicDynamic();
        Result results = b.listUsers(this.getDataSource(), UserSelector.DAVE);
        assertEquals(results.getSize(),1);

        assertEquals(results.getValue(0,"firstname"),"Dave");
    }
    
}
