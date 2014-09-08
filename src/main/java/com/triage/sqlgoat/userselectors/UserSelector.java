/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat.userselectors;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author dcowden
 */
public interface UserSelector {
    public Result listUsers(DataSource ds, String id);
    
    public String DAVE = "1";
    public String RICK = "2";
}
