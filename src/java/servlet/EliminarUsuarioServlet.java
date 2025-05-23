package servlet;

import dao.UsuarioDAO;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/eliminar-usuario")
public class EliminarUsuarioServlet extends HttpServlet {

    private UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Usuario actual = (Usuario) request.getSession().getAttribute("usuario");

        if (actual == null || !"ADMIN".equals(actual.getRol().name())) {
            response.sendRedirect("dashboard.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        dao.eliminar(id);

        response.sendRedirect("usuarios");
    }
}
