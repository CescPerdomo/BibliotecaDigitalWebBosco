package servlet;

import dao.MoraDAO;
import dao.PrestamoDAO;
import dao.UsuarioDAO;
import modelo.Prestamo;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@WebServlet("/renovar-prestamo")
public class RenovarPrestamoServlet extends HttpServlet {

    private PrestamoDAO prestamoDAO = new PrestamoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private MoraDAO moraDAO = new MoraDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Usuario admin = (Usuario) request.getSession().getAttribute("usuario");

        if (admin == null || !"ADMIN".equals(admin.getRol().name())) {
            response.sendRedirect("dashboard.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        Prestamo p = prestamoDAO.buscarPorId(id);

        if (p != null && "ACTIVO".equals(p.getEstado())) {
            // Verificar si está en mora
            long dias = ChronoUnit.DAYS.between(p.getFechaPrestamo().toLocalDateTime(), LocalDateTime.now());

            Usuario u = usuarioDAO.buscarPorNombre(p.getUsuarioPresta());

            int maxDias = u.getRol().name().equals("ALUMNO") ? 3 : 15;

            if (dias <= maxDias) {
                // Renovar préstamo
                p.setEstado("RENOVADO");
                p.setFechaPrestamo(Timestamp.valueOf(LocalDateTime.now()));
                prestamoDAO.actualizar(p);
            }
        }

        response.sendRedirect("devoluciones");
    }
}
