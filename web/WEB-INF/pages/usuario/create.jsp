<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Green - Usuarios</title>

        <%@ include file="/WEB-INF/jspf/head.jspf" %> 
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/menu.jspf" %> 

        <div class="container py-2">
            <form method="post" action="${pageContext.request.contextPath}/app/Usuario/store">
                <div class="form-group">
                    <label for="nickname">Nombre del usuario</label>
                    <input type="text" class="form-control" id="nickname" name="nickname" minlength="4" maxlength="30" autofocus required>
                    <small class="form-text text-muted">Se recomienda utilizar letras y numeros.</small>
                </div>
                <div class="form-group">
                    <label for="password">Clave</label>
                    <input type="password" class="form-control" id="password" name="password" minlength="3" maxlength="10" required>
                </div>
                <div class="form-group">
                    <label for="role">Seleccione rol</label>
                    <select class="form-control" id="role" name="role" required>
                        <option>NORMAL</option>
                        <option>ADMINISTRATOR</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Guardar</button>
            </form>
        </div>

        <%@ include file="/WEB-INF/jspf/scripts.jspf" %> 
    </body>
</html>
