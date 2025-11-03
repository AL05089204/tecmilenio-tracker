package com.tecmilenio.imctracker.dao;
import com.tecmilenio.imctracker.model.Credenciales;

public interface CredencialesDAO {
  void create(Credenciales c);
  Credenciales findByUsuarioId(Long uid);
  Credenciales findByUsername(String username);
  void updateUltimoAcceso(Long uid);
}
