<%@ include file="/WEB-INF/views/_layout/header.jspf" %>
<div class="d-flex justify-content-between align-items-center mb-3">
  <h1 class="h5 mb-0">Mediciones</h1>
  <a href="<c:url value='/medicion/nueva'/>" class="btn btn-success">Nueva medición</a>
</div>
<div class="card"><div class="card-body">
  <div class="table-responsive">
    <table class="table table-hover align-middle">
      <thead class="table-light"><tr><th>Fecha</th><th>Peso (kg)</th><th>IMC</th><th>Nota</th></tr></thead>
      <tbody></tbody>
    </table>
  </div>
</div></div>
<script>
const apiUrl = '<c:url value="/api/mediciones"/>';
fetch(apiUrl)
  .then(function(r){ return r.json(); })
  .then(function(d){
    const tbody = document.querySelector('tbody');
    tbody.innerHTML = '';
    d.items.forEach(function(it){
      const tr = document.createElement('tr');
      tr.innerHTML =
        '<td>' + it.fecha + '</td>' +
        '<td>' + it.pesoKg + '</td>' +
        '<td>' + Number(it.imc).toFixed(2) + '</td>' +
        '<td>' + (it.nota ? it.nota : '') + '</td>';
      tbody.appendChild(tr);
    });
  })
  .catch(function(err){
    console.error('Error cargando mediciones:', err);
  });
</script>

<%@ include file="/WEB-INF/views/_layout/footer.jspf" %>
