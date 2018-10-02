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

            <a href="${pageContext.request.contextPath}/app/Curso/create">Agregar nuevo curso</a>


            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Course</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="curso" items="${cursos}" varStatus="row">
                        <tr>
                            <td>${curso.id}</td>
                            <td>${curso.description}</td>
                            <td>
                                <a class="btn btn-warning" href="#">Editar</a>
                                <a href="${pageContext.request.contextPath}/app/Curso/destroy/${curso.id}" class="btn btn-danger">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>

            </table>
        </div>

        <%@ include file="/WEB-INF/jspf/scripts.jspf" %> 
    </body>
</html>
