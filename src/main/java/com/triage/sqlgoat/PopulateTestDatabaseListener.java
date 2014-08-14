package com.triage.sqlgoat;


import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Web application lifecycle listener.
 *
 * @author dcowden
 */
public class PopulateTestDatabaseListener implements ServletContextListener {

    private DataSource ds;

    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        lookupDataSource();
        Flyway flyway = new Flyway();
        flyway.setDataSource(this.ds);
        flyway.migrate();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){
    }
 
    public void lookupDataSource(){
        Context ctx = null;
        try{
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/InMemUsers");
                          
        }catch(NamingException e){
            e.printStackTrace();
        }   
    }
}
