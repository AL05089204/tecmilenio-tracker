package com.tecmilenio.imctracker.web;
import com.tecmilenio.imctracker.service.MedicionService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException; 
import java.time.LocalDate;

@WebServlet(urlPatterns = "/medicion/nueva")
public class MedicionCreateServlet extends HttpServlet {
  @Inject private MedicionService medicionService;
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/views/medicion_form.jsp").forward(req, resp);
  }
  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Long uid = (Long) req.getSession().getAttribute("authedUserId");
    if (uid == null){ resp.sendRedirect(req.getContextPath()+"/login"); return; }
    try {
      double peso = Double.parseDouble(req.getParameter("peso"));
      LocalDate fecha = LocalDate.parse(req.getParameter("fecha"));
      String nota = req.getParameter("nota");
      medicionService.registrarMedicion(uid, peso, fecha, nota);
      resp.sendRedirect(req.getContextPath()+"/dashboard");
    } catch (Exception e){
      req.setAttribute("error", "No se pudo guardar: "+e.getMessage());
      doGet(req, resp);
    }
  }
}
