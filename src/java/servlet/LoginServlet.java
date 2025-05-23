package servlet;

import dao.AuthDAO;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String user = request.getParameter("usuario");
        String pass = request.getParameter("contrasena");

        Usuario u = AuthDAO.validarCredenciales(user, pass);

        if (u != null) {
            request.getSession().setAttribute("usuario", u);
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
