package servlet;

import dao.UsuarioDAO;
import modelo.Mora;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/simular-mora")
public class SimularMoraServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario actual = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        if (actual == null || !"ADMIN".equals(actual.getRol().name())) {
            response.sendRedirect("dashboard.jsp");
            return;
        }

        String usuario = request.getParameter("usuario");
        String diasTexto = request.getParameter("dias");
        String ejemplaresTexto = request.getParameter("ejemplares");

        if (usuario == null || diasTexto == null || diasTexto.isEmpty()) {
            response.sendRedirect("ver-moras?error=faltan_datos");
            return;
        }

        int diasMora = Integer.parseInt(diasTexto.trim());
        Usuario u = usuarioDAO.buscarPorNombre(usuario.trim());

        if (u == null) {
            response.sendRedirect("ver-moras?error=usuario_no_encontrado");
            return;
        }

        // ✅ Usar ejemplares ingresados manualmente si se proporcionó
        int ejemplares = (ejemplaresTexto != null && !ejemplaresTexto.trim().isEmpty())
            ? Integer.parseInt(ejemplaresTexto.trim())
            : u.getEjemplaresPrestados();

        double monto = diasMora * 0.25 + ejemplares * 0.25;

        // Crear objeto Mora simulado
        Mora mora = new Mora();
        mora.setUsuario(usuario.trim());
        mora.setDiasMora(diasMora);
        mora.setEjemplaresPrestados(ejemplares);
        mora.setMontoMora(monto);
        mora.setFechaCalculo(Timestamp.from(Instant.now()));
        mora.setIdMaterial(0); // no aplica
        mora.setPrestamoId(0); // no aplica

        // Guardar en sesión para mostrar en mora.jsp
        List<Mora> simuladas = (List<Mora>) session.getAttribute("morasSimuladas");
        if (simuladas == null) {
            simuladas = new ArrayList<>();
        }
        simuladas.add(mora);
        session.setAttribute("morasSimuladas", simuladas);

        response.sendRedirect("ver-moras");
    }
}

