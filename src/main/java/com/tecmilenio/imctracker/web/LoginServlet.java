package com.tecmilenio.imctracker.web;
import com.tecmilenio.imctracker.model.Usuario;
import com.tecmilenio.imctracker.service.AuthService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login","/logout"})
public class LoginServlet extends HttpServlet {
  @Inject private AuthService auth;
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if ("/logout".equals(req.getServletPath())) { req.getSession().invalidate(); resp.sendRedirect(req.getContextPath()+"/login"); return; }
    req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
  }
  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String user = req.getParameter("usuario");
    String pass = req.getParameter("password");
    Usuario u = auth.login(user, pass);
    if (u == null){ req.setAttribute("error","Credenciales inválidas"); doGet(req, resp); return; }
    req.getSession().setAttribute("authedUserId", u.getId());
    resp.sendRedirect(req.getContextPath()+"/dashboard");
  }
}
