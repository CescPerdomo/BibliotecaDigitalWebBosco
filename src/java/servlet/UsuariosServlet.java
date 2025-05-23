package servlet;

import dao.UsuarioDAO;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/usuarios")
public class UsuariosServlet extends HttpServlet {

    private UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario admin = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        if (admin == null || !"ADMIN".equals(admin.getRol().name())) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Usuario> usuarios = dao.listar();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("usuarios.jsp").forward(request, response);
    }
}

