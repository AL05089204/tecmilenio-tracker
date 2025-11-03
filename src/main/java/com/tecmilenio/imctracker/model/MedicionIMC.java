package com.tecmilenio.imctracker.model;
import java.time.LocalDate;
public class MedicionIMC {
  private Long id; private Long usuarioId; private Double pesoKg;
  private Double imc; private LocalDate fechaMedicion; private String nota;
  public Long getId(){return id;} public void setId(Long id){this.id=id;}
  public Long getUsuarioId(){return usuarioId;} public void setUsuarioId(Long v){this.usuarioId=v;}
  public Double getPesoKg(){return pesoKg;} public void setPesoKg(Double v){this.pesoKg=v;}
  public Double getImc(){return imc;} public void setImc(Double v){this.imc=v;}
  public LocalDate getFechaMedicion(){return fechaMedicion;} public void setFechaMedicion(LocalDate v){this.fechaMedicion=v;}
  public String getNota(){return nota;} public void setNota(String v){this.nota=v;}
}
