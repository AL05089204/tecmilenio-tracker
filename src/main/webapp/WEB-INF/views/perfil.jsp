<%@ include file="/WEB-INF/views/_layout/header.jspf" %>

<div class="row justify-content-center"><div class="col-12 col-lg-8">
  <div class="card shadow-sm rounded-4"><div class="card-body p-4">
    <h1 class="h5 mb-4">Mi perfil</h1>
    <c:if test="${not empty ok}"><div class="alert alert-success">${ok}</div></c:if>
    <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
    <form action="<c:url value='/perfil'/>" method="post" novalidate>
      <div class="row g-3">
        <div class="col-12 col-md-6"><div class="form-floating">
          <input class="form-control" id="nombreCompleto" name="nombreCompleto"
                 value="${usuario.nombreCompleto}" placeholder="Nombre completo" required>
          <label for="nombreCompleto">Nombre completo</label>
        </div></div>
        <div class="col-12 col-md-6"><div class="form-floating">
          <input class="form-control" id="nombreUsuario" name="nombreUsuario"
                 value="${usuario.nombreUsuario}" placeholder="Usuario" readonly>
          <label for="nombreUsuario">Usuario</label>
        </div></div>
        <div class="col-6 col-md-3"><div class="form-floating">
          <input type="number" class="form-control" id="edad" name="edad" min="15" max="120"
                 value="${usuario.edad}" placeholder="Edad" required>
          <label for="edad">Edad</label>
        </div></div>
        <div class="col-6 col-md-3"><div class="form-floating">
          <select class="form-select" id="sexo" name="sexo" required>
            <option value="MASCULINO" ${usuario.sexo == 'MASCULINO' ? 'selected' : ''}>Masculino</option>
            <option value="FEMENINO"  ${usuario.sexo == 'FEMENINO'  ? 'selected' : ''}>Femenino</option>
            <option value="NO_BINARIO" ${usuario.sexo == 'NO_BINARIO' ? 'selected' : ''}>No binario</option>
          </select>
          <label for="sexo">Sexo</label>
        </div></div>
        <div class="col-12 col-md-6"><div class="form-floating">
          <input type="number" step="0.01" min="1.00" max="2.50" class="form-control" id="estatura" name="estatura"
                 value="${usuario.estaturaM}" placeholder="Estatura (m)" required>
          <label for="estatura">Estatura (m)</label>
        </div></div>
      </div>
      <div class="d-grid d-md-flex gap-2 mt-3">
        <button class="btn btn-primary">Guardar cambios</button>
        <a class="btn btn-outline-secondary" href="<c:url value='/dashboard'/>">Cancelar</a>
      </div>
    </form>
  </div></div>
</div></div>
<%@ include file="/WEB-INF/views/_layout/footer.jspf" %>
