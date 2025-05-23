package dao;

import modelo.Mora;
import util.ConexionBD;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MoraDAO {

    public List<Mora> listarPorTipo(String tipo) {
        List<Mora> lista = new ArrayList<>();

        String sql = "SELECT p.id AS prestamo_id, p.id_material, p.usuario_presta, p.fecha_prestamo, u.ejemplares_prestados " +
                     "FROM prestamo p " +
                     "JOIN usuarios u ON p.usuario_presta = u.usuario " +
                     "WHERE p.estado = 'ACTIVO' AND u.tipo_usuario = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tipo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Timestamp fechaPrestamo = rs.getTimestamp("fecha_prestamo");

                // Fecha límite: fecha préstamo + 3 o 15 días según tipo
                int limiteDias = tipo.equalsIgnoreCase("ALUMNO") ? 3 : 15;

                LocalDateTime fechaLimite = fechaPrestamo.toLocalDateTime().plusDays(limiteDias);
                LocalDateTime ahora = LocalDateTime.now();

                if (ahora.isAfter(fechaLimite)) {
                    long diasRetraso = ChronoUnit.DAYS.between(fechaLimite, ahora);

                    Mora mora = new Mora();
                    mora.setPrestamoId(rs.getInt("prestamo_id"));
                    mora.setIdMaterial(rs.getInt("id_material"));
                    mora.setUsuario(rs.getString("usuario_presta"));
                    mora.setEjemplaresPrestados(rs.getInt("ejemplares_prestados"));
                    mora.setDiasMora((int) diasRetraso);
                    mora.setMontoMora(diasRetraso * 0.25 + rs.getInt("ejemplares_prestados") * 0.25);
                    mora.setFechaCalculo(Timestamp.valueOf(LocalDateTime.now()));

                    lista.add(mora);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}

