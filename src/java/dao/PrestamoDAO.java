package dao;

import modelo.Prestamo;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public boolean agregar(Prestamo p) {
        String sql = "INSERT INTO prestamo (id_material, usuario_presta, fecha_prestamo, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, p.getIdMaterial());
            ps.setString(2, p.getUsuarioPresta());
            ps.setTimestamp(3, p.getFechaPrestamo());
            ps.setString(4, p.getEstado());

            int filas = ps.executeUpdate();
            if (filas == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Prestamo> listarPorUsuario(String usuario) {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamo WHERE LOWER(TRIM(usuario_presta)) = ? ORDER BY fecha_prestamo DESC";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.trim().toLowerCase());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearPrestamo(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Prestamo buscarPorId(int id) {
        String sql = "SELECT * FROM prestamo WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapearPrestamo(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Prestamo p) {
        String sql = "UPDATE prestamo SET fecha_devolucion = ?, estado = ?, fecha_prestamo = ? WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, p.getFechaDevolucion());
            ps.setString(2, p.getEstado());
            ps.setTimestamp(3, p.getFechaPrestamo());
            ps.setInt(4, p.getId());
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Prestamo> listarActivos() {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamo WHERE estado = 'ACTIVO' ORDER BY fecha_prestamo DESC";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearPrestamo(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int countActiveByUser(String usuario) {
        String sql = "SELECT COUNT(*) FROM prestamo WHERE usuario_presta = ? AND estado = 'ACTIVO'";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Prestamo mapearPrestamo(ResultSet rs) throws SQLException {
        Prestamo p = new Prestamo();
        p.setId(rs.getInt("id"));
        p.setIdMaterial(rs.getInt("id_material"));
        p.setUsuarioPresta(rs.getString("usuario_presta"));
        p.setFechaPrestamo(rs.getTimestamp("fecha_prestamo"));
        p.setFechaDevolucion(rs.getTimestamp("fecha_devolucion"));
        p.setEstado(rs.getString("estado"));
        return p;
    }
    
    public boolean eliminar(int id) {
        String sql = "DELETE FROM prestamo WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
        return ps.executeUpdate() == 1;
        } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

    
    
    
    
    
    
}

