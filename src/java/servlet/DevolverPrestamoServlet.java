package servlet;

import dao.PrestamoDAO;
import modelo.Prestamo;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet("/devolver-prestamo")
public class DevolverPrestamoServlet extends HttpServlet {

    private PrestamoDAO dao = new PrestamoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Usuario admin = (Usuario) request.getSession().getAttribute("usuario");

        if (admin == null || !"ADMIN".equals(admin.getRol().name())) {
            response.sendRedirect("dashboard.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        Prestamo p = dao.buscarPorId(id);

        if (p != null && "ACTIVO".equals(p.getEstado())) {
            p.setEstado("DEVUELTO");
            p.setFechaDevolucion(Timestamp.valueOf(LocalDateTime.now()));
            dao.actualizar(p);
        }

        response.sendRedirect("devoluciones");
    }
}

