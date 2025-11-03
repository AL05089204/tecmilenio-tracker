<%@ include file="/WEB-INF/views/_layout/header.jspf" %>
<div class="row justify-content-center">
    <div class="col-12 col-lg-8">
        <div class="card shadow-sm rounded-4">
            <div class="card-body p-4">
                <h1 class="h4 mb-4">Crea una cuenta</h1>
                <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
                <form action="<c:url value='/registro'/>" method="post" novalidate>
                    <div class="row g-3">
                        <div class="col-12 col-md-6"><div class="form-floating">
                            <input class="form-control" id="nombreCompleto" name="nombreCompleto" placeholder="Nombre completo" required>
                            <label for="nombreCompleto">Nombre completo</label>
                        </div>
                    </div>
                    <div class="col-12 col-md-6">
                        <div class="form-floating">
                            <input class="form-control" id="nombreUsuario" name="nombreUsuario" placeholder="Usuario" required/>
                            <label for="nombreUsuario">Nombre de usuario</label>
                        </div>
                    </div>
                    <div class="col-6 col-md-3">
                        <div class="form-floating">
                            <input type="number" class="form-control" id="edad" name="edad" min="15" max="120" placeholder="Edad" required>
                            <label for="edad">Edad</label>
                        </div>
                    </div>
                    <div class="col-6 col-md-3">
                        <div class="form-floating">
                            <select class="form-select" id="sexo" name="sexo" required>
                                <option value="" selected disabled>Selecciona</option>
                                <option value="MASCULINO">Masculino</option>
                                <option value="FEMENINO">Femenino</option>
                                <option value="NO_BINARIO">No binario</option>
                            </select>
                            <label for="sexo">Sexo</label>
                        </div>
                    </div>
                    <div class="col-12 col-md-6">
                        <div class="form-floating">
                            <input type="number" step="0.01" min="1.00" max="2.50" class="form-control" id="estatura" name="estatura" placeholder="Estatura en metros" required>
                            <label for="estatura">Estatura (m)</label>
                        </div>
                    </div>
                    <div class="col-12 col-md-6">
                        <div class="form-floating">
                            <input type="password" class="form-control" id="password" name="password" placeholder="Contraseña" required>
                            <label for="password">Contraseña</label>
                        </div>
                    </div>
                    </div>
                    <div class="d-grid d-md-flex gap-2 mt-3">
                        <button class="btn btn-success">Crear cuenta</button>
                        <a class="btn btn-outline-secondary" href="<c:url value='/login'/>">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/_layout/footer.jspf" %>

