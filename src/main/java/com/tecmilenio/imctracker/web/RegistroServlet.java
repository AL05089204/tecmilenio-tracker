package com.tecmilenio.imctracker.web;
import com.tecmilenio.imctracker.dao.CredencialesDAO;
import com.tecmilenio.imctracker.dao.UsuarioDAO;
import com.tecmilenio.imctracker.model.Credenciales;
import com.tecmilenio.imctracker.model.Rol;
import com.tecmilenio.imctracker.model.Sexo;
import com.tecmilenio.imctracker.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/registro")
public class RegistroServlet extends HttpServlet {
  @Inject private UsuarioDAO usuarioDAO;
  @Inject private CredencialesDAO credDAO;
  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(req, resp);
  }
  @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String nombre = req.getParameter("nombreCompleto");
    String usuario = req.getParameter("nombreUsuario");
    String edadStr = req.getParameter("edad");
    String sexoStr = req.getParameter("sexo");
    String estaturaStr = req.getParameter("estatura");
    String pass = req.getParameter("password");
    try {
      int edad = Integer.parseInt(edadStr);
      double estatura = Double.parseDouble(estaturaStr);
      if (edad < 15) throw new IllegalArgumentException("Edad >= 15");
      if (estatura < 1.00 || estatura > 2.50) throw new IllegalArgumentException("Estatura 1.00-2.50m");
      Usuario u = new Usuario();
      u.setNombreCompleto(nombre); u.setNombreUsuario(usuario); u.setEdad(edad);
      u.setSexo(Sexo.valueOf(sexoStr)); u.setEstaturaM(estatura);
      Long uid = usuarioDAO.create(u);
      Credenciales c = new Credenciales();
      c.setUsuarioId(uid); c.setHashPassword(BCrypt.hashpw(pass, BCrypt.gensalt()));
      c.setRol(Rol.USER); c.setActivo(true);
      credDAO.create(c);
      resp.sendRedirect(req.getContextPath() + "/login");
    } catch (Exception ex){
      req.setAttribute("error", "Datos inválidos: " + ex.getMessage());
      req.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(req, resp);
    }
  }
}
