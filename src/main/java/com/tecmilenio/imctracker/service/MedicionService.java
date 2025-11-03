package com.tecmilenio.imctracker.service;
import com.tecmilenio.imctracker.dao.MedicionDAO;
import com.tecmilenio.imctracker.dao.UsuarioDAO;
import com.tecmilenio.imctracker.model.MedicionIMC;
import com.tecmilenio.imctracker.model.Usuario;
import com.tecmilenio.imctracker.util.IMCCalculadora;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.time.LocalDate;

@RequestScoped
public class MedicionService {
  @Inject private MedicionDAO medicionDAO;
  @Inject private UsuarioDAO usuarioDAO;
  public Long registrarMedicion(Long uid, double pesoKg, LocalDate fecha, String nota){
    if (pesoKg <= 0) throw new IllegalArgumentException("Peso inválido");
    Usuario u = usuarioDAO.findById(uid);
    double imc = IMCCalculadora.calcularIMC(pesoKg, u.getEstaturaM());
    if (medicionDAO.existsByUsuarioAndFecha(uid, fecha)) throw new IllegalStateException("Ya existe medición para esa fecha");
    MedicionIMC m = new MedicionIMC();
    m.setUsuarioId(uid); m.setPesoKg(pesoKg); m.setImc(imc); m.setFechaMedicion(fecha); m.setNota(nota);
    return medicionDAO.create(m);
  }
}
