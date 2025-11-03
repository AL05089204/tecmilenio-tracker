package com.tecmilenio.imctracker.model;
import java.time.LocalDateTime;
public class Usuario {
  private Long id; private String nombreCompleto; private String nombreUsuario;
  private Integer edad; private Sexo sexo; private Double estaturaM;
  private LocalDateTime creadoEn; private LocalDateTime actualizadoEn;
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public String getNombreCompleto(){return nombreCompleto;} public void setNombreCompleto(String v){this.nombreCompleto=v;}
  public String getNombreUsuario(){return nombreUsuario;} public void setNombreUsuario(String v){this.nombreUsuario=v;}
  public Integer getEdad(){return edad;} public void setEdad(Integer v){this.edad=v;}
  public Sexo getSexo(){return sexo;} public void setSexo(Sexo v){this.sexo=v;}
  public Double getEstaturaM(){return estaturaM;} public void setEstaturaM(Double v){this.estaturaM=v;}
  public LocalDateTime getCreadoEn(){return creadoEn;} public void setCreadoEn(LocalDateTime v){this.creadoEn=v;}
  public LocalDateTime getActualizadoEn(){return actualizadoEn;} public void setActualizadoEn(LocalDateTime v){this.actualizadoEn=v;}
}
