<%-- 
    Document   : show_results
    Created on : Aug 10, 2014, 7:44:44 PM
    Author     : dcowden
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="resultData" type="javax.servlet.jsp.jstl.sql.Result"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- any content can be specified here e.g.: --%>

<h2>Results</h2>
<table border="1">
<tr>
    <c:forEach var="columnName" items="${resultData.columnNames}">
        <th><c:out value="${columnName}"/></th>
    </c:forEach>
</tr>                
<c:forEach var="row" items="${resultData.rowsByIndex}">
    <tr>
        <%-- Output each column of data --%>
        <c:forEach var="col" items="${row}">
            <td><c:out value="${col}"/></td>
        </c:forEach>
    </tr>
</c:forEach>            
</table>    