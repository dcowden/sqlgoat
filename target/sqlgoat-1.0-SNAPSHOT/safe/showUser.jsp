<%-- 
    Document   : showUser.jsp
    Created on : Aug 10, 2014, 7:13:47 PM
    Author     : dcowden
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="result" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show User Details-- Vulnerable To Injection! </title>
    </head>
    <body>

   <sql:query var="resultData" dataSource="jdbc/InMemUsers">
        SELECT * FROM users where id = ?
        <sql:param value="${param.id}"/>
    </sql:query>

    <result:show_results resultData="${resultData}"/>          
        </body>        
        
    </body>
</html>
