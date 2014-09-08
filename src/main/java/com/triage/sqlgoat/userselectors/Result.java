/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat.userselectors;

import java.util.List;
import java.util.Map;

/**
 *
 * @author dcowden
 */
public class Result {
   
    protected List<Map<String,Object>> results;
    
    public Result(List<Map<String,Object>> results){
        this.results = results;
    }

    public Object getValue(int row, String name){
        return this.results.get(row).get(name);    
    }
    
    public int getSize(){
        return results.size();
    }
}
