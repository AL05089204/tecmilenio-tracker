package com.tecmilenio.imctracker.web;
import com.tecmilenio.imctracker.dao.MedicionDAO;
import com.tecmilenio.imctracker.dao.UsuarioDAO;
import com.tecmilenio.imctracker.model.MedicionIMC;
import com.tecmilenio.imctracker.model.Usuario;
import com.tecmilenio.imctracker.util.IMCCalculadora;
import jakarta.inject.Inject;
import jakarta.json.*; 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet; 
import jakarta.servlet.http.*;
import java.io.IOException; 
import java.util.List;

@WebServlet(urlPatterns = "/api/mediciones")
public class IMCApiServlet extends HttpServlet {
  @Inject private MedicionDAO medicionDAO;
  @Inject private UsuarioDAO usuarioDAO;
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Long uid = (Long) req.getSession().getAttribute("authedUserId");
    if (uid == null){ resp.sendError(HttpServletResponse.SC_UNAUTHORIZED); return; }
    List<MedicionIMC> lista = medicionDAO.listByUsuario(uid, 180, 0);
    Usuario u = usuarioDAO.findById(uid);
    JsonArrayBuilder items = Json.createArrayBuilder();
    for (MedicionIMC m: lista){
      items.add(Json.createObjectBuilder()
        .add("fecha", m.getFechaMedicion().toString())
        .add("pesoKg", m.getPesoKg())
        .add("imc", m.getImc())
        .add("nota", m.getNota()==null? "": m.getNota()));
    }
    double imcActual = lista.isEmpty()? 0 : lista.get(0).getImc();
    String clasificacion = lista.isEmpty()? "—" : IMCCalculadora.clasificar(imcActual);
    String ultimaFecha = lista.isEmpty()? "—" : lista.get(0).getFechaMedicion().toString();
    JsonObject out = Json.createObjectBuilder()
      .add("usuario", u.getNombreUsuario())
      .add("estaturaM", u.getEstaturaM())
      .add("imcActual", imcActual)
      .add("clasificacion", clasificacion)
      .add("ultimaFecha", ultimaFecha)
      .add("items", items).build();
    resp.setContentType("application/json;charset=UTF-8");
    resp.getWriter().write(out.toString());
  }
}
