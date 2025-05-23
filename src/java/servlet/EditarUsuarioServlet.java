package servlet;

import dao.UsuarioDAO;
import modelo.Usuario;
import modelo.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/editar-usuario")
public class EditarUsuarioServlet extends HttpServlet {

    private UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Usuario actual = (Usuario) request.getSession().getAttribute("usuario");
        if (actual == null || !"ADMIN".equals(actual.getRol().name())) {
            response.sendRedirect("dashboard.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        Usuario u = dao.buscarPorId(id);  // ✅ este método ya lo incluimos en el DAO

        if (u != null) {
            request.setAttribute("usuarioEditar", u);
            request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
        } else {
            response.sendRedirect("usuarios");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String contrasena = request.getParameter("contrasena");
        Role rol = Role.valueOf(request.getParameter("rol"));
        int ejemplares = Integer.parseInt(request.getParameter("ejemplares"));

        Usuario u = new Usuario();
        u.setId(id);
        u.setContrasena(contrasena);
        u.setRol(rol);
        u.setEjemplaresPrestados(ejemplares);

        dao.actualizar(u);
        response.sendRedirect("usuarios");
    }
}

