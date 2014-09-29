package com.triage.sqlgoat.database;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author dcowden
 */
public class UserService {
    
    protected DataSource dataSource;
    private JdbcTemplate template;

    public static String DAVE = "1";
    public static String RICK = "2";
    public UserService(DataSource dataSource){
        this.dataSource = dataSource;
        this.template = new JdbcTemplate(dataSource);
    }
    
     public List<Map<String, Object>> listUsersUnsafe(String userId){
         List<Map<String, Object>> data = this.template.query("select * from users where id = " + userId, new ColumnMapRowMapper() );
         return data;
     }   
     
     public List<Map<String, Object>> listUsersSafe(String userId){
         List<Map<String, Object>> data = this.template.query("select * from users where id = ?" , new ColumnMapRowMapper(),userId );
         return data;
     }      
}
