package com.tecmilenio.imctracker.dao;
import com.tecmilenio.imctracker.model.MedicionIMC;
import java.time.LocalDate;
import java.util.List;
public interface MedicionDAO {
  Long create(MedicionIMC m);
  List<MedicionIMC> listByUsuario(Long uid, int limit, int offset);
  MedicionIMC findLastByUsuario(Long uid);
  void delete(Long uid, Long medicionId);
  boolean existsByUsuarioAndFecha(Long uid, LocalDate fecha);
}
