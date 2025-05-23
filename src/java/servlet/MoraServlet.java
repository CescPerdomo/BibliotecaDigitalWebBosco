package servlet;

import dao.MoraDAO;
import dao.MoraResumenDAO;
import modelo.Mora;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@WebServlet("/ver-moras")
public class MoraServlet extends HttpServlet {

    private MoraDAO moraDAO = new MoraDAO();
    private MoraResumenDAO resumenDAO = new MoraResumenDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        // 🔒 Acceso solo para ADMIN
        if (usuario == null || !usuario.getRol().name().equals("ADMIN")) {
            response.sendRedirect("dashboard.jsp");
            return;
        }

        // Obtener listas de moras por tipo
        List<Mora> morasAlumnos = moraDAO.listarPorTipo("ALUMNO");
        List<Mora> morasProfesores = moraDAO.listarPorTipo("PROFESOR");

        // (Opcional) Guardar totales en mora_resumen
        resumenDAO.limpiarResumen();
        Map<String, Double> totales = new HashMap<>();

        for (Mora m : morasAlumnos) {
            totales.put(m.getUsuario(),
                totales.getOrDefault(m.getUsuario(), 0.0) + m.getMontoMora());
        }
        for (Mora m : morasProfesores) {
            totales.put(m.getUsuario(),
                totales.getOrDefault(m.getUsuario(), 0.0) + m.getMontoMora());
        }
        for (Map.Entry<String, Double> entry : totales.entrySet()) {
            resumenDAO.guardarOModificar(entry.getKey(), entry.getValue());
        }

        // Pasar datos a la vista
        request.setAttribute("morasAlumnos", morasAlumnos);
        request.setAttribute("morasProfesores", morasProfesores);

        request.getRequestDispatcher("mora.jsp").forward(request, response);
    }
}

