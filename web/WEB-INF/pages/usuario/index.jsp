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

            <a href="${pageContext.request.contextPath}/app/Usuario/create">Agregar nuevo usuario</a>


            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nickname</th>
                        <th>Role</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="usuario" items="${usuarios}" varStatus="row">
                        <tr>
                            <td>${usuario.id}</td>
                            <td>${usuario.nickname}</td>
                            <td>

                                <c:choose>
                                    <c:when test="${usuario.role == 'ADMINISTRATOR'}">
                                        <span class="badge badge-dark">${usuario.role}</span>
                                    </c:when>
                                    <c:when test="${usuario.role == 'NORMAL'}">
                                        <span class="badge badge-info">${usuario.role}</span>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <a class="btn btn-warning" href="#">Editar</a>
                                <a href="${pageContext.request.contextPath}/app/Usuario/destroy/${usuario.id}" class="btn btn-danger">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>

            </table>
        </div>

        <%@ include file="/WEB-INF/jspf/scripts.jspf" %> 

        <script>
            $('#formNuevoUsuario').submit(function (e) {

                e.preventDefault();

                // var formData = document.forms['nuevoUsuario'];
                var formData = $('#formNuevoUsuario').serialize();

                axios.post('Usuario/store', formData)
                        .then(function (response) {
                            console.log(response);
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
            });

            function eliminar(c) {
                console.log(c);

                axios.delete('Usuario/' + c)
                        .then(function (response) {
                            console.log(response);
                            location.reload();
                        })
                        .catch(function (error) {
                            console.log(error);
                        });

            }
        </script>
    </body>
</html>
