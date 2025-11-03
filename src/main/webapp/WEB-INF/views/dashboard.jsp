<%@ include file="/WEB-INF/views/_layout/header.jspf" %>

<div class="row g-3">
  <div class="col-12 col-lg-4">
    <div class="card h-100"><div class="card-body">
      <h2 class="h6 text-muted">IMC actual</h2>
      <div class="display-5 fw-bold" id="imcActual">?</div>
      <span class="badge bg-info-subtle text-info-emphasis" id="clasificacion">?</span>
      <p class="small text-muted mt-2">Última medición: <span id="ultimaFecha">?</span></p>
      <a href="<c:url value='/medicion/nueva'/>" class="btn btn-success w-100 mt-2">Nueva medición</a>
    </div></div>
  </div>
  <div class="col-12 col-lg-8">
    <div class="card h-100"><div class="card-body">
      <h2 class="h6 text-muted mb-3">Evolución</h2>
      <canvas id="imcChart" height="120"></canvas>
    </div></div>
  </div>
</div>

<div class="row g-3 mt-1"><div class="col-12"><div class="card"><div class="card-body">
  <h2 class="h6 text-muted mb-3">Últimas mediciones</h2>
  <div class="table-responsive">
    <table class="table table-sm align-middle">
      <thead><tr><th>Fecha</th><th>Peso (kg)</th><th>IMC</th><th>Nota</th></tr></thead>
      <tbody></tbody>
    </table>
  </div>
  <a href="<c:url value='/medicion/lista'/>" class="btn btn-outline-primary btn-sm">Ver todas</a>
</div></div></div></div>

<script>
const apiUrl = '<c:url value="/api/mediciones"/>';
fetch(apiUrl)
  .then(function(r){ if(!r.ok) throw new Error('No autorizado'); return r.json(); })
  .then(function(d){
    document.getElementById('imcActual').textContent = Number(d.imcActual || 0).toFixed(2);
    document.getElementById('clasificacion').textContent = d.clasificacion || '?';
    document.getElementById('ultimaFecha').textContent = d.ultimaFecha || '?';

    const tbody = document.querySelector('tbody'); 
    tbody.innerHTML = '';
    d.items.slice(0,5).forEach(function(it){
      const tr = document.createElement('tr');
      tr.innerHTML =
          '<td>' + it.fecha + '</td>'
        + '<td>' + it.pesoKg + '</td>'
        + '<td>' + Number(it.imc).toFixed(2) + '</td>'
        + '<td>' + (it.nota ? it.nota : '') + '</td>';
      tbody.appendChild(tr);
    });

    const labels = d.items.map(function(it){ return it.fecha; }).reverse();
    const data   = d.items.map(function(it){ return it.imc;   }).reverse();
    new Chart(document.getElementById('imcChart'), {
      type: 'line',
      data: { labels: labels, datasets: [{ label: 'IMC', data: data }] }
    });
  })
  .catch(function(){
    window.location = '<c:url value="/login"/>';
  });
</script>


<%@ include file="/WEB-INF/views/_layout/footer.jspf" %>
