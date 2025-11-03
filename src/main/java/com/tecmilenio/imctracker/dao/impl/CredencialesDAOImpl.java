package com.tecmilenio.imctracker.dao.impl;
import com.tecmilenio.imctracker.dao.CredencialesDAO;
import com.tecmilenio.imctracker.model.Credenciales;
import com.tecmilenio.imctracker.model.Rol;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import java.sql.*;

@RequestScoped
public class CredencialesDAOImpl implements CredencialesDAO {
  @Resource(lookup = "jdbc/imctrackerDS")
  private DataSource ds;
  @Override
  public void create(Credenciales c) {
    String sql = "INSERT INTO imctracker.credenciales (usuario_id, hash_password, rol, activo) " +
             "VALUES (?, ?, ?::imctracker.rol_enum, ?)";
    try (Connection cn = ds.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setLong(1, c.getUsuarioId());
      ps.setString(2, c.getHashPassword());
      ps.setString(3, c.getRol().name());
      ps.setBoolean(4, c.getActivo());
      ps.executeUpdate();
    } catch (SQLException e) { throw new RuntimeException(e); }
  }
  @Override
  public Credenciales findByUsuarioId(Long uid) {
    String sql = "SELECT id,usuario_id,hash_password,rol,activo,ultimo_acceso FROM imctracker.credenciales WHERE usuario_id=?";
    try (Connection cn = ds.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setLong(1, uid);
      try(ResultSet rs = ps.executeQuery()){
        if(rs.next()){
          Credenciales c = new Credenciales();
          c.setId(rs.getLong(1));
          c.setUsuarioId(rs.getLong(2));
          c.setHashPassword(rs.getString(3));
          c.setRol(Rol.valueOf(rs.getString(4)));
          c.setActivo(rs.getBoolean(5));
          Timestamp ts = rs.getTimestamp(6);
          c.setUltimoAcceso(ts!=null? ts.toLocalDateTime(): null);
          return c;
        }
      }
    } catch (SQLException e) { throw new RuntimeException(e); }
    return null;
  }
  @Override
  public Credenciales findByUsername(String username) {
    String sql = "SELECT c.id,c.usuario_id,c.hash_password,c.rol,c.activo,c.ultimo_acceso FROM imctracker.credenciales c JOIN imctracker.usuarios u ON u.id=c.usuario_id WHERE u.nombre_usuario=?";
    try (Connection cn = ds.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setString(1, username);
      try(ResultSet rs = ps.executeQuery()){
        if(rs.next()){
          Credenciales c = new Credenciales();
          c.setId(rs.getLong(1));
          c.setUsuarioId(rs.getLong(2));
          c.setHashPassword(rs.getString(3));
          c.setRol(Rol.valueOf(rs.getString(4)));
          c.setActivo(rs.getBoolean(5));
          Timestamp ts = rs.getTimestamp(6);
          c.setUltimoAcceso(ts!=null? ts.toLocalDateTime(): null);
          return c;
        }
      }
    } catch (SQLException e) { throw new RuntimeException(e); }
    return null;
  }
  @Override
  public void updateUltimoAcceso(Long uid) {
    String sql = "UPDATE imctracker.credenciales SET ultimo_acceso=NOW() WHERE usuario_id=?";
    try (Connection cn = ds.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setLong(1, uid);
      ps.executeUpdate();
    } catch (SQLException e) { throw new RuntimeException(e); }
  }
}
