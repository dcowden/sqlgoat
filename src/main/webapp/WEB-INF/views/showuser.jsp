<%-- 
    Document   : showuser
    Created on : Sep 27, 2014, 7:36:46 PM
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
        <h1>Show User Details -- Vulnerable to Injection! </h1>
        <table border="1">
            <tr>
            <c:forEach var="colname" items="${dataCols}">
                <th>${colname}</th>
            </c:forEach>
            </tr>
            <c:forEach var="row" items="${userData}">
                <tr>
                    <%-- Output each column of data --%>
                    <c:forEach var="col" items="${row}">
                        <td>${col.value}</td>
                    </c:forEach>
                </tr>
            </c:forEach>           
        </table>
    </body>
</html>
