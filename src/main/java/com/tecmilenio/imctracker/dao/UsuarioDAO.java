package com.tecmilenio.imctracker.dao;
import com.tecmilenio.imctracker.model.Usuario;
public interface UsuarioDAO {
  Long create(Usuario u);
  Usuario findByNombreUsuario(String username);
  Usuario findById(Long id);
  void update(Usuario u);
}
