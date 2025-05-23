package servlet;

import dao.MaterialDAO;
import modelo.Material;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import modelo.Usuario;

@WebServlet("/detalle")
public class VerDetalleServlet extends HttpServlet {

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    try {
        int id = Integer.parseInt(request.getParameter("id"));
        Material material = new MaterialDAO().findById(id);

        if (material != null) {
            request.setAttribute("material", material);

            // ✅ Agregar usuario logueado para que detalle.jsp lo reciba
            HttpSession session = request.getSession(false);
            Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;
            request.setAttribute("usuario", usuario);

            request.getRequestDispatcher("detalle.jsp").forward(request, response);
        } else {
            response.sendRedirect("buscar.jsp");
            }

        } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("buscar.jsp");
    }
    }

}
