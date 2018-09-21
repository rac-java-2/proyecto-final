<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="assets/signin.css" media="screen">
    </head>
    <body>
        <form class="form-signin" method="post" action="${pageContext.request.contextPath}/Login">
            <img class="mb-4 mx-auto d-block" src="assets/images/unap.png" alt="" width="90">
            <input type="hidden" name="accion" value="validarSesion">
            <h3 class="mb-3 text-center">Inicio de Sesion</h3>
            <div class="form-group">
                <label>Correo electronico</label>
                <input type="email" name="email" class="form-control" placeholder="Correo electronico" required>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Clave</label>
                <input type="password" name="password" class="form-control" placeholder="Clave" required>
            </div>


            <c:if test="${not empty requestScope.estado}">
                <div class="alert alert-danger" role="alert">
                    ${requestScope.estado}
                </div>
            </c:if>


            <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
            <p class="mt-5 mb-3 text-muted text-center">&copy; 2018-2019</p>
        </form>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
