/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat.database;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
/**
 *
 * @author dcowden
 */
public class BasicDynamic implements UserSelector{

    
    public Result listUsers(DataSource ds, String id){
        try {
            QueryRunner qr = new QueryRunner(ds);
            return new Result(qr.query("select * from users where id = " + id , new MapListHandler()));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
