package servlet;

import dao.PrestamoDAO;
import modelo.Prestamo;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/devoluciones")
public class DevolucionesServlet extends HttpServlet {

    private PrestamoDAO dao = new PrestamoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Usuario admin = (Usuario) request.getSession().getAttribute("usuario");

        if (admin == null || !"ADMIN".equals(admin.getRol().name())) {
            response.sendRedirect("dashboard.jsp");
            return;
        }

        List<Prestamo> prestamos = dao.listarActivos();
        request.setAttribute("prestamos", prestamos);
        request.getRequestDispatcher("devoluciones.jsp").forward(request, response);
    }
}

