<%-- 
    Document   : listUser
    Created on : Aug 10, 2014, 4:34:05 PM
    Author     : dcowden
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="result" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List All Users: Vulnerable </title>
    </head>

    
   <sql:query var="resultData" dataSource="jdbc/InMemUsers">
        SELECT * FROM users
    </sql:query>

    <body>
        <h1>Select:</h1>
        <pre>SELECT * FROM users</pre>        
      
                                       
    <result:show_results resultData="${resultData}"/>
    </body>
    

    
</html>
