<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Green - Inscripciones</title>

        <%@ include file="/WEB-INF/jspf/head.jspf" %> 
    </head>
    <body>
        <%@ include file="/WEB-INF/jspf/menu.jspf" %> 

        <div class="container py-2">
            <form method="post" action="${pageContext.request.contextPath}/app/Inscripcion/store">
                <div class="form-group">
                    <label for="first_name">Nombres</label>
                    <input type="text" class="form-control" id="first_name" name="first_name" minlength="4" maxlength="30" autofocus required>
                </div>
                <div class="form-group">
                    <label for="last_name">Apellidos</label>
                    <input type="text" class="form-control" id="last_name" name="last_name" minlength="4" maxlength="50" required>
                </div>
                <div class="form-group">
                    <label for="cellphone">Numero celular</label>
                    <input type="text" class="form-control" id="cellphone" name="cellphone" minlength="4" maxlength="9">
                </div>
                <div class="form-group">
                    <label for="courses_id">Seleccione curso</label>
                    <select class="form-control" id="courses_id" name="courses_id" required>
                        <c:forEach var="curso" items="${cursos}" varStatus="row">
                            <option value="${curso.id}">${curso.description}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="price">Precio</label>
                    <input type="number" class="form-control" id="price" name="price" min="0" required>
                </div>
                <div class="form-group">
                    <label for="registration_date">Fecha de registro</label>
                    <input type="date" class="form-control" id="registration_date" name="registration_date" required>
                </div>
                <button type="submit" class="btn btn-primary">Guardar</button>
            </form>
        </div>

        <%@ include file="/WEB-INF/jspf/scripts.jspf" %> 
    </body>
</html>
