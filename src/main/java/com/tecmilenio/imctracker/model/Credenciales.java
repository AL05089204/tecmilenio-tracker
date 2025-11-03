package com.tecmilenio.imctracker.model;

import java.time.LocalDateTime;

public class Credenciales {
  private Long id; private Long usuarioId; private String hashPassword;
  private Rol rol = Rol.USER; private Boolean activo = true; private LocalDateTime ultimoAcceso;
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public Long getUsuarioId(){return usuarioId;} public void setUsuarioId(Long v){this.usuarioId=v;}
  public String getHashPassword(){return hashPassword;} public void setHashPassword(String v){this.hashPassword=v;}
  public Rol getRol(){return rol;} public void setRol(Rol v){this.rol=v;}
  public Boolean getActivo(){return activo;} public void setActivo(Boolean v){this.activo=v;}
  public LocalDateTime getUltimoAcceso(){return ultimoAcceso;} public void setUltimoAcceso(LocalDateTime v){this.ultimoAcceso=v;}
}
