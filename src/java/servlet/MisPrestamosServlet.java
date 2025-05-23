package servlet;

import dao.PrestamoDAO;
import modelo.Prestamo;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/mis-prestamos")
public class MisPrestamosServlet extends HttpServlet {

    private PrestamoDAO dao = new PrestamoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Aseguramos formato consistente del usuario
        String username = usuario.getUsuario().trim().toLowerCase();

        List<Prestamo> prestamos = dao.listarPorUsuario(username);

        request.setAttribute("prestamos", prestamos);
        request.getRequestDispatcher("misPrestamos.jsp").forward(request, response);
    }
}
