package com.tecmilenio.imctracker.service;
import com.tecmilenio.imctracker.dao.CredencialesDAO;
import com.tecmilenio.imctracker.dao.UsuarioDAO;
import com.tecmilenio.imctracker.model.Credenciales;
import com.tecmilenio.imctracker.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class AuthService {
  @Inject private UsuarioDAO usuarioDAO;
  @Inject private CredencialesDAO credDAO;
  public Usuario login(String username, String passPlano){
    Credenciales cred = credDAO.findByUsername(username);
    if (cred == null || !cred.getActivo()) return null;
    if (!BCrypt.checkpw(passPlano, cred.getHashPassword())) return null;
    credDAO.updateUltimoAcceso(cred.getUsuarioId());
    return usuarioDAO.findById(cred.getUsuarioId());
  }
}
