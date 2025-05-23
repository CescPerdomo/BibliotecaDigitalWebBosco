package dao;

import util.ConexionBD;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MoraResumenDAO {

    // ✅ Obtener resumen de mora por usuario
    public Map<String, Double> obtenerResumen() {
        Map<String, Double> resumen = new HashMap<>();
        String sql = "SELECT usuario, total_mora FROM mora_resumen ORDER BY usuario";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                resumen.put(rs.getString("usuario"), rs.getDouble("total_mora"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resumen;
    }

    // ✅ Insertar o actualizar el total de mora de un usuario
    public void guardarOModificar(String usuario, double totalMora) {
        String sql = "INSERT INTO mora_resumen (usuario, total_mora) VALUES (?, ?) " +
                     "ON DUPLICATE KEY UPDATE total_mora = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setDouble(2, totalMora);
            ps.setDouble(3, totalMora);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Limpiar tabla completa (opcional, para simulaciones)
    public void limpiarResumen() {
        String sql = "DELETE FROM mora_resumen";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
