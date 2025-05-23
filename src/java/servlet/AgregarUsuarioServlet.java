package servlet;

import dao.UsuarioDAO;
import modelo.Role;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/agregar-usuario")
public class AgregarUsuarioServlet extends HttpServlet {

    private UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario actual = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        if (actual == null || !"ADMIN".equals(actual.getRol().name())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String usuario = request.getParameter("usuario").trim();
        String contrasena = request.getParameter("contrasena").trim();
        Role rol = Role.valueOf(request.getParameter("rol"));
        int ejemplares = Integer.parseInt(request.getParameter("ejemplares"));

        Usuario nuevo = new Usuario();
        nuevo.setUsuario(usuario);
        nuevo.setContrasena(contrasena);
        nuevo.setRol(rol);
        nuevo.setEjemplaresPrestados(ejemplares);

        dao.agregar(nuevo);

        response.sendRedirect("usuarios");
    }
}
