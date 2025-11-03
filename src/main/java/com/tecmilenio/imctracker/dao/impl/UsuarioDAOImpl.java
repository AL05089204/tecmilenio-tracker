package com.tecmilenio.imctracker.dao.impl;
import com.tecmilenio.imctracker.dao.UsuarioDAO;
import com.tecmilenio.imctracker.model.Sexo;
import com.tecmilenio.imctracker.model.Usuario;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import java.sql.*;
@RequestScoped
public class UsuarioDAOImpl implements UsuarioDAO {
  @Resource(lookup = "jdbc/imctrackerDS")
  private DataSource ds;
  @Override
  public Long create(Usuario u) {
 
    String sql =
    "INSERT INTO imctracker.usuarios " +
    "(nombre_completo,nombre_usuario,edad,sexo,estatura_m) " +
    "VALUES (?,?,?,?::imctracker.sexo_enum,?) RETURNING id";
    try (Connection cn = ds.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setString(1, u.getNombreCompleto());
        ps.setString(2, u.getNombreUsuario());
        ps.setInt(3, u.getEdad());
        ps.setString(4, u.getSexo().name());      // 👈 texto, pero el SQL lo castea al enum
        ps.setDouble(5, u.getEstaturaM());
        try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return rs.getLong(1); }
      }catch (SQLException e) { throw new RuntimeException(e); }
    return null;
  }
  @Override
  public Usuario findByNombreUsuario(String username) {
    String sql = "SELECT id,nombre_completo,nombre_usuario,edad,sexo,estatura_m FROM imctracker.usuarios WHERE nombre_usuario=?";
    try (Connection cn = ds.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setString(1, username);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          Usuario u = new Usuario();
          u.setId(rs.getLong("id"));
          u.setNombreCompleto(rs.getString("nombre_completo"));
          u.setNombreUsuario(rs.getString("nombre_usuario"));
          u.setEdad(rs.getInt("edad"));
          u.setSexo(Sexo.valueOf(rs.getString("sexo")));
          u.setEstaturaM(rs.getDouble("estatura_m"));
          return u;
        }
      }
    } catch (SQLException e) { throw new RuntimeException(e); }
    return null;
  }
  @Override
  public Usuario findById(Long id) {
    String sql = "SELECT id,nombre_completo,nombre_usuario,edad,sexo,estatura_m FROM imctracker.usuarios WHERE id=?";
    try (Connection cn = ds.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setLong(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          Usuario u = new Usuario();
          u.setId(rs.getLong("id"));
          u.setNombreCompleto(rs.getString("nombre_completo"));
          u.setNombreUsuario(rs.getString("nombre_usuario"));
          u.setEdad(rs.getInt("edad"));
          u.setSexo(Sexo.valueOf(rs.getString("sexo")));
          u.setEstaturaM(rs.getDouble("estatura_m"));
          return u;
        }
      }
    } catch (SQLException e) { throw new RuntimeException(e); }
    return null;
  }
  @Override
  public void update(Usuario u) {
    String sql =
  "UPDATE imctracker.usuarios " +
  "SET nombre_completo=?, edad=?, sexo=?::imctracker.sexo_enum, estatura_m=? " +
  "WHERE id=?";
try (Connection cn = ds.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
  ps.setString(1, u.getNombreCompleto());
  ps.setInt(2, u.getEdad());
  ps.setString(3, u.getSexo().name());      // 👈 texto, SQL castea
  ps.setDouble(4, u.getEstaturaM());
  ps.setLong(5, u.getId());
  ps.executeUpdate();
} catch (SQLException e) { throw new RuntimeException(e); }
  }
}
