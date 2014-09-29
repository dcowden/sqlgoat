/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat.tests;

import com.triage.springconfig.Config;
import com.triage.springconfig.DbConfig;
import com.triage.sqlgoat.database.UserService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 *
 * @author dcowden
 */
@ContextConfiguration(classes=DbConfig.class)
public class BaseTest extends AbstractTestNGSpringContextTests{

    
    public static final String INMEMDB_URL = "jdbc:hsqldb:mem:usermemdb";
    public static final String DB_USER = "SA";
        
    @Autowired
    protected UserService userService;

    public UserService getUserService() {
        return userService;
    }
    
    @org.testng.annotations.BeforeMethod
    public void setUpMethod() throws Exception {

    }
    
}
