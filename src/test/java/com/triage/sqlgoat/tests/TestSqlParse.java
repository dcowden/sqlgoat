/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.sqlgoat.tests;

import com.triage.sqlparse.SqlParse;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * See https://github.com/Kanatoko/libinjection-Java for implementation
 * See https://libinjection.client9.com/ for tests
 * @author dcowden
 */
public class TestSqlParse {
    
    public TestSqlParse() {
    }

    protected void assertIsSqlI(String input){
        assertTrue(SqlParse.isSQLi(input));
    }
    protected void assertNotSqlI(String input){
        assertFalse(SqlParse.isSQLi(input));
    }
    
    @Test
    //from WebGoat SQL Injection Lessons
    public void testWebGoatSqlInjection(){
        assertIsSqlI("101 OR 1=1");
        assertIsSqlI("101 OR 372=372");        
        assertIsSqlI("Erwin' OR '1'='1");
        assertIsSqlI("101 OR 1=1 ORDER BY salary desc ");
        assertIsSqlI("whatever'; UPDATE salaries SET salary=999999 WHERE userid='jsmith");
        assertIsSqlI("101 AND ((SELECT pin FROM pins WHERE cc_number='1111222233334444') > 10000 );");
        assertIsSqlI("101 AND (SUBSTRING((SELECT name FROM pins WHERE cc_number='4321432143214321'), 1, 1) < 'H' );");
    }
    
    @Test
    //from http://www.w3schools.com/sql/sql_injection.asp
    public void testCommonSqlIAttacks(){
        assertIsSqlI("675; DROP TABLE whatever");
        assertIsSqlI("\" or \"\"=\"");
        assertIsSqlI("anything' OR 'x'='x");
        assertIsSqlI("x' AND email IS NULL; --");
        assertIsSqlI("bob@example.com' AND passwd = 'hello123");
    }
    
    @Test
    //from http://ferruh.mavituna.com/sql-injection-cheatsheet-oku/
    public void testAdvancedAttacks(){
        assertIsSqlI("' UNION SELECT 1, 'anotheruser', 'doesnt matter', 1--");
        assertIsSqlI("10; DROP TABLE members /*");
        assertIsSqlI("CONCAT('0x',HEX('c:\\\\boot.ini'))");
        assertIsSqlI("LOAD_FILE(0x633A5C626F6F742E696E69)");
        assertIsSqlI("admin' --");
        assertIsSqlI("admin' /*");
        assertIsSqlI("' or 1=1--");
        assertIsSqlI("') or '1'='1--");
        assertIsSqlI("') or ('1'='1--");
        assertIsSqlI("1234 ' AND 1=0 UNION ALL SELECT 'admin', '81dc9bdb52d04dc20036dbd8313ed055");
        assertIsSqlI("' union select sum(columntofind) from users--");
        assertIsSqlI("11223344) UNION SELECT NULL,NULL,NULL,NULL WHERE 1=2 –-");
        assertIsSqlI("asdsd'; EXEC master.dbo.xp_cmdshell 'cmd.exe dir c:'");
    }
    
    @Test
    public void testValidThings(){
        assertNotSqlI("101");
        assertNotSqlI("update my profile");
        assertNotSqlI("'this is a test'");
        assertNotSqlI("'this is a test");
        assertNotSqlI("select * from sometable");
    }
    
}
