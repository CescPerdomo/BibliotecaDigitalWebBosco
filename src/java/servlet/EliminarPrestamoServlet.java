package servlet;

import dao.PrestamoDAO;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/eliminar-prestamo")
public class EliminarPrestamoServlet extends HttpServlet {

    private PrestamoDAO dao = new PrestamoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario actual = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        if (actual == null || !"ADMIN".equals(actual.getRol().name())) {
            response.sendRedirect("login.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        dao.eliminar(id);

        response.sendRedirect("mis-prestamos");
    }
}
