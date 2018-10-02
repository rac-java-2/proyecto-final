<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Green - Cursos</title>

        <%@ include file="/WEB-INF/jspf/head.jspf" %> 
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/menu.jspf" %> 

        <div class="container py-2">
            <form method="post" action="${pageContext.request.contextPath}/app/Curso/store">
                <div class="form-group">
                    <label for="description">Descripción del curso</label>
                    <input type="text" class="form-control" id="description" name="description" minlength="4" maxlength="70" autofocus required>
                </div>
                <button type="submit" class="btn btn-primary">Guardar</button>
            </form>
        </div>

        <%@ include file="/WEB-INF/jspf/scripts.jspf" %> 
    </body>
</html>
