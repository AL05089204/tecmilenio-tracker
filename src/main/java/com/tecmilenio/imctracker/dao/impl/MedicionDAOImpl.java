package com.tecmilenio.imctracker.dao.impl;

import com.tecmilenio.imctracker.dao.MedicionDAO;
import com.tecmilenio.imctracker.model.MedicionIMC;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import java.sql.*; 
import java.time.LocalDate; 
import java.util.*;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class MedicionDAOImpl implements MedicionDAO {
  @Resource(lookup = "jdbc/imctrackerDS")
  private DataSource ds;
  @Override
  public Long create(MedicionIMC m) {
    String sql = "INSERT INTO imctracker.mediciones_imc(usuario_id,peso_kg,imc,fecha_medicion,nota) VALUES (?,?,?,?,?) RETURNING id";
    try (Connection cn = ds.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setLong(1, m.getUsuarioId());
      ps.setDouble(2, m.getPesoKg());
      ps.setDouble(3, m.getImc());
      ps.setDate(4, java.sql.Date.valueOf(m.getFechaMedicion()));
      ps.setString(5, m.getNota());
      try(ResultSet rs = ps.executeQuery()){ if (rs.next()) return rs.getLong(1); }
    } catch (SQLException e) { throw new RuntimeException(e); }
    return null;
  }
  @Override
  public List<MedicionIMC> listByUsuario(Long uid, int limit, int offset) {
    String sql = "SELECT id,usuario_id,peso_kg,imc,fecha_medicion,nota FROM imctracker.mediciones_imc WHERE usuario_id=? ORDER BY fecha_medicion DESC LIMIT ? OFFSET ?";
    List<MedicionIMC> out = new ArrayList<>();
    try (Connection cn = ds.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setLong(1, uid); ps.setInt(2, limit); ps.setInt(3, offset);
      try(ResultSet rs = ps.executeQuery()){
        while(rs.next()){
          MedicionIMC m = new MedicionIMC();
          m.setId(rs.getLong(1)); m.setUsuarioId(rs.getLong(2)); m.setPesoKg(rs.getDouble(3));
          m.setImc(rs.getDouble(4)); m.setFechaMedicion(rs.getDate(5).toLocalDate()); m.setNota(rs.getString(6));
          out.add(m);
        }
      }
    } catch (SQLException e) { throw new RuntimeException(e); }
    return out;
  }
  @Override
  public MedicionIMC findLastByUsuario(Long uid) {
    String sql = "SELECT id,usuario_id,peso_kg,imc,fecha_medicion,nota FROM imctracker.mediciones_imc WHERE usuario_id=? ORDER BY fecha_medicion DESC LIMIT 1";
    try (Connection cn = ds.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setLong(1, uid); try(ResultSet rs = ps.executeQuery()){ if(rs.next()){
        MedicionIMC m = new MedicionIMC();
        m.setId(rs.getLong(1)); m.setUsuarioId(rs.getLong(2)); m.setPesoKg(rs.getDouble(3));
        m.setImc(rs.getDouble(4)); m.setFechaMedicion(rs.getDate(5).toLocalDate()); m.setNota(rs.getString(6));
        return m; }}
    } catch (SQLException e) { throw new RuntimeException(e); }
    return null;
  }
  @Override
  public void delete(Long uid, Long medicionId) {
    String sql = "DELETE FROM imctracker.mediciones_imc WHERE id=? AND usuario_id=?";
    try (Connection cn = ds.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setLong(1, medicionId); ps.setLong(2, uid); ps.executeUpdate();
    } catch (SQLException e) { throw new RuntimeException(e); }
  }
  @Override
public boolean existsByUsuarioAndFecha(Long uid, LocalDate fecha) {
    String sql = "SELECT 1 FROM imctracker.mediciones_imc WHERE usuario_id=? AND fecha_medicion=?";
    try (Connection cn = ds.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setLong(1, uid);
        ps.setDate(2, java.sql.Date.valueOf(fecha)); 
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
}
