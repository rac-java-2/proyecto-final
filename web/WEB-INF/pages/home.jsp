<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <%@ include file="/WEB-INF/jspf/head.jspf" %> 
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/menu.jspf" %> 
        
        <div class="container">
            <h1>frd</h1>
            Rol: <c:out value='${sessionScope.role}'/>
        </div>
        
        <%@ include file="/WEB-INF/jspf/scripts.jspf" %> 
    </body>
</html>
