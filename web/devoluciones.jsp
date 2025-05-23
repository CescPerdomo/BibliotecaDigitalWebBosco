<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Prestamo, modelo.Usuario" %>
<%@ page import="java.util.List" %>
<%
    Usuario u = (Usuario) session.getAttribute("usuario");
    if (u == null || !"ADMIN".equals(u.getRol().name())) {
        response.sendRedirect("dashboard.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Devoluciones y Renovaciones</title>
    <style>
        table {
            width: 90%;
            margin: 30px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #1565c0;
            color: white;
        }
        h2 {
            text-align: center;
        }
        .volver {
            text-align: center;
            margin-top: 30px;
        }
    </style>
</head>
<body>

    <h2>↩️ Devoluciones y Renovaciones (solo ADMIN)</h2>

    <%
        List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamos");
        if (prestamos != null && !prestamos.isEmpty()) {
    %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Material</th>
                    <th>Usuario</th>
                    <th>Fecha Préstamo</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% for (Prestamo p : prestamos) { %>
                    <tr>
                        <td><%= p.getId() %></td>
                        <td><%= p.getIdMaterial() %></td>
                        <td><%= p.getUsuarioPresta() %></td>
                        <td><%= p.getFechaPrestamo() %></td>
                        <td>
                            <form action="devolver-prestamo" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= p.getId() %>" />
                                <button type="submit">✅ Devolver</button>
                            </form>

                            <form action="renovar-prestamo" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= p.getId() %>" />
                                <button type="submit">🔄 Renovar</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p style="text-align:center;">No hay préstamos activos registrados.</p>
    <% } %>

    <div class="volver">
        <a href="dashboard.jsp">← Volver al panel</a>
    </div>

</body>
</html>
