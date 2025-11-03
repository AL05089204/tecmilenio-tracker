package com.tecmilenio.imctracker.web;

import com.tecmilenio.imctracker.dao.UsuarioDAO;
import com.tecmilenio.imctracker.model.Sexo;
import com.tecmilenio.imctracker.model.Usuario;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/perfil")
public class PerfilServlet extends HttpServlet {

  @Inject
  private UsuarioDAO usuarioDAO;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Long uid = (Long) req.getSession().getAttribute("authedUserId");
    if (uid == null) { resp.sendRedirect(req.getContextPath() + "/login"); return; }

    Usuario u = usuarioDAO.findById(uid);
    if (u == null) { // sesión huérfana (usuario borrado)
      req.getSession().invalidate();
      resp.sendRedirect(req.getContextPath() + "/login");
      return;
    }
    req.setAttribute("usuario", u);
    req.getRequestDispatcher("/WEB-INF/views/perfil.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Long uid = (Long) req.getSession().getAttribute("authedUserId");
    if (uid == null) { resp.sendRedirect(req.getContextPath() + "/login"); return; }

    String nombreCompleto = req.getParameter("nombreCompleto");
    String edadStr = req.getParameter("edad");
    String sexoStr = req.getParameter("sexo");
    String estaturaStr = req.getParameter("estatura");

    try {
      int edad = Integer.parseInt(edadStr);
      double estatura = Double.parseDouble(estaturaStr);
      if (edad < 15) throw new IllegalArgumentException("Edad >= 15");
      if (estatura < 1.00 || estatura > 2.50) throw new IllegalArgumentException("Estatura 1.00–2.50 m");

      Usuario u = usuarioDAO.findById(uid);
      if (u == null) throw new IllegalStateException("Usuario no encontrado");

      u.setNombreCompleto(nombreCompleto);
      u.setEdad(edad);
      u.setSexo(Sexo.valueOf(sexoStr));
      u.setEstaturaM(estatura);

      usuarioDAO.update(u);
      req.setAttribute("ok", "Perfil actualizado correctamente");
      req.setAttribute("usuario", u);
    } catch (Exception ex) {
      req.setAttribute("error", "No se pudo actualizar: " + ex.getMessage());
      // Mantén lo que el usuario escribió
      Usuario u = new Usuario();
      u.setId(uid);
      u.setNombreCompleto(nombreCompleto);
      try { u.setEdad(Integer.parseInt(edadStr)); } catch (Exception ignore) {}
      try { u.setSexo(Sexo.valueOf(sexoStr)); } catch (Exception ignore) {}
      try { u.setEstaturaM(Double.parseDouble(estaturaStr)); } catch (Exception ignore) {}
      req.setAttribute("usuario", u);
    }
    req.getRequestDispatcher("/WEB-INF/views/perfil.jsp").forward(req, resp);
  }
}
