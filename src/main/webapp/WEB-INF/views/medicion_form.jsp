<%@ include file="/WEB-INF/views/_layout/header.jspf" %>
<h1 class="h5 mb-3">Nueva medición</h1>
<c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>
<form class="needs-validation" action="<c:url value='/medicion/nueva'/>" method="post" novalidate>
  <div class="row g-3">
    <div class="col-6"><div class="form-floating">
      <input type="number" step="0.1" min="0.1" max="400" class="form-control" id="peso" name="peso" placeholder="Peso (kg)" required>
      <label for="peso">Peso (kg)</label>
    </div></div>
    <div class="col-6"><div class="form-floating">
      <input type="date" class="form-control" id="fecha" name="fecha" required>
      <label for="fecha">Fecha</label>
    </div></div>
    <div class="col-12"><div class="form-floating">
      <input type="text" maxlength="180" class="form-control" id="nota" name="nota" placeholder="Nota (opcional)">
      <label for="nota">Nota (opcional)</label>
    </div></div>
  </div>
  <div class="d-grid d-md-flex gap-2 mt-3">
    <button class="btn btn-success">Guardar medición</button>
    <a class="btn btn-outline-secondary" href="<c:url value='/dashboard'/>">Cancelar</a>
  </div>
</form>
<%@ include file="/WEB-INF/views/_layout/footer.jspf" %>
