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

            <a href="${pageContext.request.contextPath}/app/Inscripcion/create">Agregar nueva inscripcion</a>


            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Fullname</th>
                        <th>Cellphone</th>
                        <th>Price</th>
                        <th>Registration date</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="inscripcion" items="${inscripciones}" varStatus="row">
                        <tr>
                            <td>${inscripcion.id}</td>
                            <td>
                                ${inscripcion.fullname}
                                <p class="text-info">${inscripcion.curso.description}</p>
                            </td>
                            <td>${inscripcion.cellphone}</td>
                            <td>${inscripcion.price}</td>
                            <td>${inscripcion.registrationDate}</td>
                            <td>
                                <a class="btn btn-warning" href="#">Editar</a>
                                <a href="${pageContext.request.contextPath}/app/Inscripcion/destroy/${inscripcion.id}" class="btn btn-danger">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>

            </table>
        </div>

        <%@ include file="/WEB-INF/jspf/scripts.jspf" %> 
    </body>
</html>
