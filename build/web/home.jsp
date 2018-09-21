<%-- 
    Document   : home
    Created on : 21-sep-2018, 4:45:31
    Author     : Rene
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <p><%= request.getAttribute("estado") %></p>
    </body>
</html>
