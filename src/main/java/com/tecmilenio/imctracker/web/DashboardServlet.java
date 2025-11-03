package com.tecmilenio.imctracker.web;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*; 
import java.io.IOException;

@WebServlet(urlPatterns = {"/dashboard","/medicion/lista"})
public class DashboardServlet extends HttpServlet {
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String path = req.getServletPath();
    String view = "/WEB-INF/views/dashboard.jsp";
    if ("/medicion/lista".equals(path)) view = "/WEB-INF/views/medicion_list.jsp";
    else if ("/perfil".equals(path)) view = "/WEB-INF/views/perfil.jsp";
    req.getRequestDispatcher(view).forward(req, resp);
  }
}
