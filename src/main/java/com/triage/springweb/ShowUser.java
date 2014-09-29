/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.springweb;


import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author dcowden
 */
@Controller
public class ShowUser {
 
    protected DataSource dataSource;
    private JdbcTemplate template;
    
    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new JdbcTemplate(dataSource);
    }
    
     @RequestMapping("/showUser")
     public String showUsers(@RequestParam(value="id", required=false, defaultValue="0") String id, Model model) {        
        
        List<Map<String,Object>> userList = listUsers(id);
        Set<String> columns = userList.get(0).keySet();
        
        model.addAttribute("userData", listUsers(id));
        model.addAttribute("dataCols", columns);
        
        return "showuser";
     } 
     
     public List<Map<String, Object>> listUsers(String userId){
         List<Map<String, Object>> data = this.template.query("select * from users where id = " + userId, new ColumnMapRowMapper() );
         return data;
     }
}
