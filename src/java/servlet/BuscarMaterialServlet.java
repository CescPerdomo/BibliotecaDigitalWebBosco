package servlet;

import dao.MaterialDAO;
import modelo.Material;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/buscar-material")
public class BuscarMaterialServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        // Obtener palabra clave del formulario
        String keyword = request.getParameter("busqueda");

        // Llamar al DAO para buscar materiales
        MaterialDAO dao = new MaterialDAO();
        List<Material> resultados = dao.buscarPorTituloOCategoria(keyword);

        // Enviar resultados a buscar.jsp
        request.setAttribute("materiales", resultados);

        request.setAttribute("buscado", keyword); // por si quieres mostrarlo en la vista

        // Redirige de vuelta al JSP
        request.getRequestDispatcher("buscar.jsp").forward(request, response);
    }

    // Para permitir búsqueda también por método GET si lo deseas
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.getRequestDispatcher("buscar.jsp").forward(request, response);
    }
}
