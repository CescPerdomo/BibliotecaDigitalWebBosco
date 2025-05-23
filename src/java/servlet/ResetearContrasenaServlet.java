package servlet;

import dao.UsuarioDAO;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/resetear-contrasena")
public class ResetearContrasenaServlet extends HttpServlet {

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
        String nueva = "1234"; // Contraseña por defecto
        dao.resetearContrasena(id, nueva);

        response.sendRedirect("usuarios");
    }
}
