package servlet;

import dao.PrestamoDAO;
import modelo.Prestamo;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet("/prestar-material")
public class PrestarMaterialServlet extends HttpServlet {

    private PrestamoDAO dao = new PrestamoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int idMaterial = Integer.parseInt(request.getParameter("idMaterial"));
        int prestamosActuales = dao.countActiveByUser(usuario.getUsuario());

        int limite = (usuario.getRol().name().equals("ALUMNO")) ? 5 : 10;

        if (prestamosActuales >= limite) {
            response.sendRedirect("detalle?id=" + idMaterial + "&mensaje=limite");
            return;
        }

        // Crear objeto Prestamo como en versión escritorio
        Prestamo p = new Prestamo();
        p.setIdMaterial(idMaterial);
        p.setUsuarioPresta(usuario.getUsuario().trim().toLowerCase());
        p.setFechaPrestamo(Timestamp.valueOf(LocalDateTime.now()));
        p.setEstado("ACTIVO");

        boolean exito = dao.agregar(p);

        if (exito) {
            response.sendRedirect("mis-prestamos"); // ir directo a mis prestamos
        } else {
            response.sendRedirect("detalle?id=" + idMaterial + "&mensaje=error");
        }
    }
}

