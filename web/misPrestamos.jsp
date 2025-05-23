<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Prestamo, java.util.List" %>
<%@ page import="modelo.Usuario" %>
<%@ page import="java.sql.Timestamp" %>
<%
    Usuario u = (Usuario) session.getAttribute("usuario");
    if (u == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Prestamo> prestamos = (List<Prestamo>) request.getAttribute("prestamos");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Mis Préstamos</title>
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
        input[type="submit"] {
            padding: 5px 10px;
            margin: 2px;
        }
    </style>
</head>
<body>

<h2>📚 Mis Préstamos - <%= u.getUsuario() %></h2>

<%
    if (prestamos != null && !prestamos.isEmpty()) {
%>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>ID Material</th>
                <th>Fecha Préstamo</th>
                <th>Fecha Devolución</th>
                <th>Estado</th>
                <% if ("ADMIN".equals(u.getRol().name())) { %>
                    <th>Acciones</th>
                <% } %>
            </tr>
        </thead>
        <tbody>
        <% for (Prestamo p : prestamos) {
               boolean renovable = "ACTIVO".equals(p.getEstado()) && 
                                   (p.getFechaDevolucion() == null || p.getFechaDevolucion().after(new Timestamp(System.currentTimeMillis())));
        %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getIdMaterial() %></td>
                <td><%= p.getFechaPrestamo() %></td>
                <td><%= (p.getFechaDevolucion() != null) ? p.getFechaDevolucion() : "Pendiente" %></td>
                <td><%= p.getEstado() %></td>

                <% if ("ADMIN".equals(u.getRol().name())) { %>
                <td>
                    <% if (renovable) { %>
                    <!-- Botón Renovar -->
                    <form action="renovar-prestamo" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= p.getId() %>">
                        <input type="submit" value="🔄 Renovar" style="background-color: #2e7d32; color: white;">
                    </form>
                    <% } %>

                    <!-- Botón Eliminar -->
                    <form action="eliminar-prestamo" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= p.getId() %>">
                        <input type="submit" value="❌ Eliminar"
                               onclick="return confirm('¿Eliminar este préstamo?');"
                               style="background-color: #b71c1c; color: white;">
                    </form>
                </td>
                <% } %>
            </tr>
        <% } %>
        </tbody>
    </table>
<%
    } else {
%>
    <p style="text-align:center;">No tienes préstamos registrados.</p>
<%
    }
%>

<div style="text-align:center;">
    <a href="dashboard.jsp">← Volver al panel</a>
</div>

</body>
</html>
