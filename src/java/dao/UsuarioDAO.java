package dao;

import modelo.Usuario;
import modelo.Role;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // ✅ Listar todos los usuarios
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ✅ Agregar un nuevo usuario
    public boolean agregar(Usuario u) {
        String sql = "INSERT INTO usuarios (usuario, contrasena, tipo_usuario, ejemplares_prestados) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getContrasena());
            ps.setString(3, u.getRol().name());
            ps.setInt(4, u.getEjemplaresPrestados());

            int filas = ps.executeUpdate();
            if (filas == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    u.setId(rs.getInt(1));
                }
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Actualizar usuario
    public boolean actualizar(Usuario u) {
        String sql = "UPDATE usuarios SET contrasena = ?, tipo_usuario = ?, ejemplares_prestados = ? WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getContrasena());
            ps.setString(2, u.getRol().name());
            ps.setInt(3, u.getEjemplaresPrestados());
            ps.setInt(4, u.getId());
            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Eliminar usuario por ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Resetear contraseña por ID
    public boolean resetearContrasena(int id, String nuevaContrasena) {
        String sql = "UPDATE usuarios SET contrasena = ? WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevaContrasena);
            ps.setInt(2, id);
            return ps.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Buscar usuario por nombre de usuario
    public Usuario buscarPorNombre(String nombre) {
        Usuario u = null;
        String sql = "SELECT * FROM usuarios WHERE usuario = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = mapearUsuario(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    // ✅ Obtener rol de usuario
    public Role obtenerRol(String usuario) {
        String sql = "SELECT tipo_usuario FROM usuarios WHERE usuario = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Role.valueOf(rs.getString("tipo_usuario"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Método privado para mapear ResultSet a Usuario
    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setUsuario(rs.getString("usuario"));
        u.setContrasena(rs.getString("contrasena"));
        u.setRol(Role.valueOf(rs.getString("tipo_usuario")));
        u.setEjemplaresPrestados(rs.getInt("ejemplares_prestados"));
        return u;
    }
    
    public Usuario buscarPorId(int id) {
    String sql = "SELECT * FROM usuarios WHERE id = ?";
    try (Connection conn = ConexionBD.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return mapearUsuario(rs);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    
    
    
    
    
    
}




