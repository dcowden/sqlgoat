/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat.tests;

import static com.triage.sqlgoat.tests.NormalExecutionTest.INMEMDB_URL;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.hsqldb.jdbc.JDBCDataSource;
import org.jboss.byteman.contrib.bmunit.BMNGRunner;

/**
 *
 * @author dcowden
 */
public class BaseTest {

    
    public static final String INMEMDB_URL = "jdbc:hsqldb:mem:usermemdb";
    public static final String DB_USER = "SA";
    
    protected DataSource datasource;
    
    public DataSource getDataSource(){
        return datasource;
    }
    
    protected void setupDataSource(){
        JDBCDataSource jds = new JDBCDataSource();
        jds.setUrl(INMEMDB_URL);
        jds.setUser("SA");
        jds.setPassword("");
        this.datasource = jds;
    }    
    
    @org.testng.annotations.BeforeMethod
    public void setUpMethod() throws Exception {
        setupDataSource();
        Flyway f = new Flyway();
        f.setDataSource(datasource);
        f.migrate();
    }
    
}
