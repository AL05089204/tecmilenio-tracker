<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8"><meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Iniciar sesión TecMilenio</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light"><div class="container py-5"><div class="row justify-content-center">
        <div class="col-12 col-md-6 col-lg-4">
            <div class="card shadow-lg rounded-4">
                <div class="card-body p-4">
                    <h1 class="h4 mb-3 text-center">TecMilenio IMC</h1>
                       <c:if test="${not empty error}">
                           <div class="alert alert-danger">${error}</div>
                       </c:if>
                    <form action="<c:url value='/login'/>" method="post">
                        <div class="form-floating mb-3">
                            <input class="form-control" id="usuario" name="usuario" placeholder="Usuario" required/>
                            <label for="usuario">Usuario</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="password" name="password" placeholder="Contraseña" required/>
                            <label for="password">Contraseña</label>
                        </div>
                        <button class="btn btn-primary w-100" type="submit">Ingresar</button>
                    </form>
                    <hr/>
                    <p class="text-center mb-0">¿No tienes cuenta? <a href="<c:url value='/registro'/>">Regístrate</a></p>
                </div>
            </div>
        </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
