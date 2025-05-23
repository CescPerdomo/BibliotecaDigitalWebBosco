<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario" %>
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
    <title>Gestión de Usuarios</title>
    <style>
        table {
            width: 90%;
            margin: 30px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
        }
        th {
            background-color: #1565c0;
            color: white;
        }
        h2 {
            text-align: center;
        }
    </style>
</head>
<body>

    <h2>👥 Gestión de Usuarios</h2>

    <%
        List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
        if (usuarios != null && !usuarios.isEmpty()) {
    %>
        <div style="text-align:center; margin-bottom: 20px;">
            <a href="agregarUsuario.jsp"
                    accesskey=""style="padding: 10px 15px; background-color: #0d47a1; color: white; text-decoration: none;">
                                    ➕ Agregar Usuario</a>
        </div>

  
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Usuario</th>
                    <th>Contraseña</th>
                    <th>Rol</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% for (Usuario user : usuarios) { %>
                
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getUsuario() %></td>
                    <td><%= user.getContrasena() %></td>
                    <td><%= user.getRol() %></td>
                    <td>
                        <form action="editar-usuario" method="get" style="display:inline;">
                            <input type="hidden" name="id" value="<%= user.getId() %>" />
                            <button type="submit">✏️</button>
                        </form>

                        <form action="eliminar-usuario" method="post" style="display:inline;" onsubmit="return confirm('¿Eliminar este usuario?');">
                            <input type="hidden" name="id" value="<%= user.getId() %>" />
                            <button type="submit">❌</button>
                        </form>

                        <form action="resetear-contrasena" method="post" style="display:inline;" onsubmit="return confirm('¿Resetear contraseña a 1234?');">
                            <input type="hidden" name="id" value="<%= user.getId() %>" />
                            <button type="submit">🔒</button>
                        </form>
                    </td>
                </tr>    
                    
                <% } %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <p style="text-align:center;">No hay usuarios registrados.</p>
    <%
        }
    %>

    <div style="text-align:center;">
        <a href="dashboard.jsp">← Volver al panel</a>
    </div>

</body>
</html>
